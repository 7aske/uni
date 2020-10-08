//
// Created by nik on 11/25/19.
//

#ifndef SDLGAME_STATE_H
#define SDLGAME_STATE_H

#pragma once

#include "entity/entity.h"
#include "maze.h"

typedef enum render_mode {
	REN_BLOCKS,
	REN_ENTITIES,
	REN_SOLUTION,
	REN_ALL
} ren_mode_e;

typedef enum lights {
	L_NONE,
	L_LOS,
	L_AREA,
	L_ALL
} light_e;

typedef struct state {
	entity_t player;

	alist_t* entities;
	queue_t* events;

	maze_t level;

	int spawn_enemies;

	int levelc;
	int score;

	unsigned long start_time;

	light_e light_mode;
	ren_mode_e ren_mode;
	int render_graph;
	int render_graph_h;
	struct mgraph* curr_graph;
} state_t;

void state_change_graph(state_t* state, int dir);

void state_change_ren(state_t* state, int step);

void state_change_light(state_t* state, int step);

char const* get_ren_mode(enum render_mode mode);

char const* get_light_mode(enum lights light);

#endif //SDLGAME_STATE_H
