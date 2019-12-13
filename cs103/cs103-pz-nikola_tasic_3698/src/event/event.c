//
// Created by nik on 11/25/19.
//


#include "event/event.h"

void ev_game_start(state_t* state, ...) {
	entity_t player;
	entity_t spawner;

	state->entities = alist_new(sizeof(entity_t));
	state->render_graph = 0;
	state->render_graph_h = 0;

	state->spawn_enemies = 1;

	player = player_new(1, 1);
	memcpy(&state->player, &player, sizeof(entity_t));
	spawner = spawner_new();
	alist_add(state->entities, &spawner);

	state->level.doodads = NULL;
	state->level.maze = NULL;

	state->light_mode = L_LOS;
	state->ren_mode = REN_ALL;
}

void ev_level_start(state_t* state, ...) {
}

void ev_level_restart(state_t* state, ...) {
	#define LIGHT_COUNT 10
	int y, x, i;
	state->curr_graph = NULL;
	state->render_graph = 0;
	queue_clear(state->events);
	state->player.x = 1;
	state->player.y = 1;
	maze_clear(&state->level);
	maze_t maze = maze_new(state->player.x, state->player.y);
	memcpy(&state->level, &maze, sizeof(maze_t));
	stack_destroy(solve_astar(state->level.mgraph));
	for (i = 0; i < alist_size(state->entities); i++) {
		alist_rm_e_type(state->entities, E_LIGHT);
	}

	entity_t e;
	for (i = 0; i < LIGHT_COUNT; ++i) {
		while (state->level.maze[(y = rand() % state->level.h) * state->level.w + (x = rand() % state->level.w)] !=
			   state->level.b_wall);
		e.x = x;
		e.y = y;
		e.light.intensity = 1.0f;
		e.type = E_LIGHT;
		alist_add(state->entities, &e);
	}


	ev_enemies_destroy(state);
	state->curr_graph = state->level.mgraph;
	// overlay_solution(state->level.maze, state->player.x, state->player.y, state->level.exit_x, state->level.exit_y);
}

void ev_level_next(state_t* state, ...) {
	state->levelc++;
	state->score += 10 + state->levelc;
	ev_level_restart(state);
}

void ev_game_restart(state_t* state, ...) {
	ev_level_restart(state);
	state->levelc = 0;
	state->score = 0;
	state->start_time = time(0);
}

void ev_enemy_spawn(state_t* state, ...) {
	va_list argp;
	int x, y;
	void* origin;
	entity_t e;
	va_start(argp, state);
	x = va_arg(argp,
			   int);
	y = va_arg(argp,
			   int);
	origin = va_arg(argp,
					void*);
	va_end(argp);
	if (!((x > -1 && x < state->level.w) || (y > -1 && y < state->level.h)))
		while (state->level.maze[(y = rand() % state->level.h) * state->level.w + (x = rand() % state->level.w)] !=
			   state->level.b_wall);
	e = enemy_new(0, 0, origin);
	e.x = x;
	e.y = y;
	enemy_search(&e, &state->player, &state->level, 1);
	printf("ENEMY   SEARCH_%-7s (%02d, %02d) -> (%02d, %02d) %ld\n","FORCE", e.x,
		   e.y, state->player.x, state->player.y,
		   time(0) - state->start_time);
	alist_add(state->entities, &e);
}

void ev_enemies_destroy(state_t* state, ...) {
	assert(state != NULL);
	assert(state->level.maze != NULL);
	assert(state->entities != NULL);
	entity_t* eptr;
	int i;
	// clear existing enemies
	for (i = 0; i < alist_size(state->entities); ++i) {
		eptr = alist_get(state->entities, i);
		assert(eptr != NULL);
		switch (eptr->type) {
			case E_ENEMY:
				if (eptr->enemy.path != NULL) {
					stack_destroy(eptr->enemy.path);
				}
				if (eptr->enemy.mgraph != NULL) {
					mgraph_destroy(&eptr->enemy.mgraph);
				}
				alist_rm_idx(state->entities, i--);
				break;
			case E_SPAWNER:
				eptr->spawner.enemy_count = 0;
				break;
		}
	}
}

void ev_recalc_grph(state_t* state, ...) {
	mgraph_destroy(&state->level.mgraph);
	state->level.mgraph = to_graph(state->level.maze, state->level.w, state->level.h,
								   (char) state->level.b_wall, state->player.x, state->player.y,
								   state->level.exit_x, state->level.exit_y);
	if (state->level.mgraph->start && state->level.mgraph->end) {
		solve_astar(state->level.mgraph);
		// stack_destroy();
	}
}

void ev_score_reset(state_t* state, ...) {
	state->score = 0;
}

void ev_score_incr(state_t* state, ...) {
	state->score++;
}

void event_dispatch(state_t* state, enum ev_type type, void (* callback)(state_t*, ...)) {
	event_t ev;
	ev.type = type;
	ev.callback = callback;
	queue_enqueue(state->events, &ev);
}

char const* get_ev_type(event_t* ev) {
	switch (ev->type) {
		case EV_DEFAULT:
			return "EV_DEFAULT";
		case EV_SCORE_INCR:
			return "EV_SCORE_INCR";
		case EV_SCORE_RESET:
			return "EV_SCORE_RESET";
		case EV_ENEMY_DESTROY:
			return "EV_ENEMY_DESTROY";
		case EV_ENEMY_SPAWN:
			return "EV_ENEMY_SPAWN";
		case EV_LEVEL_RESTART:
			return "EV_LEVEL_RESTART";
		case EV_LEVEL_START:
			return "EV_LEVEL_START";
		case EV_LEVEL_NEXT:
			return "EV_LEVEL_NEXT";
		case EV_GAME_RESTART:
			return "EV_GAME_RESTART";
		case EV_GAME_START:
			return "EV_GAME_START";
		case EV_RECALC_GRPH:
			return "EV_RECALC_GRPH";
	}
}
