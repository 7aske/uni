//
// Created by nik on 11/25/19.
//

#include "entity/player.h"

void player_shoot(entity_t* e, alist_t* entities) {
	assert(e != (void*) 0);
	assert(e->type == E_PLAYER);
	if (e->player.next_shot > 0) {
		return;
	}
	entity_t newpew;
	newpew.x = e->x;
	newpew.y = e->y;
	newpew.hp = 10.0f;
	newpew.type = E_PEW;
	newpew.pew.dir = e->player.dir;
	newpew.pew.dmg = e->player.dmg;
	alist_add(entities, &newpew);
	e->player.next_shot = E_DEF_PNEXT_SHOT;
}

entity_t player_new(int x, int y) {
	entity_t newplayer;
	newplayer.x = x;
	newplayer.y = y;
	newplayer.hp = E_DEF_HP;
	newplayer.next_move = E_DEF_PNEXT_MOVE;
	newplayer.type = E_PLAYER;
	newplayer.player.dmg = E_DEF_DMG;
	newplayer.player.next_shot = E_DEF_PNEXT_SHOT;
	newplayer.player.dir = DIR_DOWN;
	return newplayer;
}

void player_move(entity_t* e, SDL_Scancode code, maze_t* level) {
	assert(e->type == E_PLAYER);
	switch (code) {
		case SDL_SCANCODE_W:
		case SDL_SCANCODE_UP:
			e->player.dir = DIR_UP;
			if (level->maze[((e->y - 1) * level->w) + e->x] != level->b_wall && e->next_move == 0) {
				e->y -= 1;
				e->next_move = E_DEF_PNEXT_MOVE;
			}
			break;
		case SDL_SCANCODE_A:
		case SDL_SCANCODE_LEFT:
			e->player.dir = DIR_LEFT;
			if (level->maze[(e->y * level->w) + e->x - 1] != level->b_wall && e->next_move == 0) {
				e->x -= 1;
				e->next_move = E_DEF_PNEXT_MOVE;
			}
			break;
		case SDL_SCANCODE_S:
		case SDL_SCANCODE_DOWN:
			e->player.dir = DIR_DOWN;
			if (level->maze[((e->y + 1) * level->w) + e->x] != level->b_wall && e->next_move == 0) {
				e->y += 1;
				e->next_move = E_DEF_PNEXT_MOVE;
			}
			break;
		case SDL_SCANCODE_D:
		case SDL_SCANCODE_RIGHT:
			e->player.dir = DIR_RIGHT;
			if (level->maze[(e->y * level->w) + e->x + 1] != level->b_wall && e->next_move == 0) {
				e->x += 1;
				e->next_move = E_DEF_PNEXT_MOVE;
			}
			break;
		default:
			break;
	}
}
