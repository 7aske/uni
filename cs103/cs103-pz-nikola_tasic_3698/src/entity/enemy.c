//
// Created by nik on 11/25/19.
//

#include "entity/enemy.h"

entity_t enemy_new(int x, int y, void* origin) {
	entity_t newenemy;
	newenemy.x = x;
	newenemy.y = y;
	newenemy.type = E_ENEMY;
	newenemy.next_move = E_DEF_NEXT_MOVE;
	newenemy.hp = E_DEF_HP;

	newenemy.enemy.mgraph = NULL;

	newenemy.enemy.next_search = E_DEF_NEXT_SEARCH;
	newenemy.enemy.path = NULL;
	newenemy.enemy.origin = origin;
	return newenemy;
}


void enemy_randmove(entity_t* e, char const* lvl, int width, int bound) {
	assert(e != (void*) 0);
	assert(e->type == E_ENEMY);
	if (e->next_move > 0) {
		e->next_move -= (rand() % 3) + 1;
		return;
	} else {
		e->next_move = E_DEF_NEXT_MOVE;
	}
	while (!entity_move(e, lvl, width, bound, RAND_DIR));
}

void
enemy_search(entity_t* e, entity_t* tar, maze_t const* level, int force_search) {
	if (!force_search) {
		if (e->enemy.next_search > 0) {
			e->enemy.next_search -= (rand() % 3) + 1;
			return;
		} else {
			e->enemy.next_search = E_DEF_NEXT_SEARCH;
		}
	}
	if (e->enemy.path != NULL) {
		stack_destroy(e->enemy.path);
	}
	if (e->enemy.mgraph != NULL) {
		mgraph_destroy(&e->enemy.mgraph);
	}

	e->enemy.mgraph = to_graph(level->maze, level->w, level->h, (char) level->b_wall, e->x, e->y, tar->x, tar->y);
	// astack_t* path = backtrack_find(e->x, e->y, tar->x, tar->y, level->maze, level->w, level->h, level->b_wall);
	astack_t* path = solve_astar(e->enemy.mgraph);
	connect_nodes(path);
	// reverse_astack(path);
	e->enemy.path = path;
}

void enemy_fpath(entity_t* e, char const* lvl, int width, int bound) {
	assert(e != (void*) 0);
	assert(e->type == E_ENEMY);
	if (e->enemy.path == NULL) {
		enemy_randmove(e, lvl, width, bound);
	}

	if (e->next_move > 0) {
		e->next_move -= (rand() % 3) + 1;
		return;
	} else {
		e->next_move = E_DEF_NEXT_MOVE;
	}


	int* temp = stack_pop(e->enemy.path);
	if (temp == NULL) {
		return;
	}

	int x0, y0, x1, y1, dx, dy;
	x0 = e->x;
	y0 = e->y;
	x1 = temp[0];
	y1 = temp[1];
	dx = x0 - x1;
	dy = y0 - y1;

	enum dir dir = -1;
	if (dx == 1 && dy == 0) {
		dir = DIR_LEFT;
	} else if (dx == -1 && dy == 0) {
		dir = DIR_RIGHT;
	} else if (dx == 0 && dy == 1) {
		dir = DIR_UP;
	} else if (dx == 0 && dy == -1) {
		dir = DIR_DOWN;
	} else {
		dir = DIR_NONE;
	}
	entity_move(e, lvl, width, bound, dir);
	free(temp);
}

void enemy_lockmove(entity_t* e, entity_t* e1, char const* lvl, int width, int bound) {
	assert(e != (void*) 0);
	assert(e->type == E_ENEMY);

	int dx, dy;
	enum dir dir;
	dx = e->x - e1->x;
	dy = e->y - e1->y;
	if (e->next_move > 0) {
		e->next_move -= (rand() % 3) + 1;
		return;
	} else {
		e->next_move = E_DEF_NEXT_MOVE;
	}

	if (abs(dx) >= abs(dy)) {
		if (dx >= 0) {
			dir = DIR_LEFT;
		} else {
			dir = DIR_RIGHT;
		}
	} else {
		if (dy > 0) {
			dir = DIR_UP;
		} else {
			dir = DIR_DOWN;
		}
	}

	if (!entity_move(e, lvl, width, bound, dir)) {
		if (dir == DIR_RIGHT || dir == DIR_LEFT) {
			if (dy > 0) {
				dir = DIR_UP;
			} else {
				dir = DIR_DOWN;
			}
			entity_move(e, lvl, width, bound, dir);
		} else {
			if (dx >= 0) {
				dir = DIR_LEFT;
			} else {
				dir = DIR_RIGHT;
			}
			entity_move(e, lvl, width, bound, dir);
		}
	}
}
