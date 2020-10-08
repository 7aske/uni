//
// Created by nik on 11/25/19.
//

#ifndef AGAME_EVENT_H
#define AGAME_EVENT_H

#pragma once

#include "state.h"
#include "entity/enemy.h"
#include "entity/player.h"
#include "entity/spawner.h"

enum ev_type {
	EV_DEFAULT,
	EV_LEVEL_RESTART,
	EV_LEVEL_START,
	EV_LEVEL_NEXT,
	EV_GAME_RESTART,
	EV_GAME_START,
	EV_RECALC_GRPH,
	EV_SCORE_INCR,
	EV_SCORE_RESET,
	EV_ENEMY_DESTROY,
	EV_ENEMY_SPAWN,
} ev_type_e;


typedef struct event {
	void (* callback)(state_t*, ...);

	enum ev_type type;
} event_t;

char const* get_ev_type(event_t* ev);

void ev_score_incr(state_t* state, ...);

void ev_score_reset(state_t* state, ...);

void ev_enemies_destroy(state_t* state, ...);

void ev_enemy_spawn(state_t* state, ...);

void ev_level_restart(state_t* state, ...);

void ev_level_start(state_t* state, ...);

void ev_level_next(state_t* state, ...);

void ev_recalc_grph(state_t* state, ...);

void ev_game_start(state_t* state, ...);

void ev_game_restart(state_t* state, ...);

void event_dispatch(state_t* state, enum ev_type type, void (* callback)(state_t*, ...));

#endif //AGAME_EVENT_H
