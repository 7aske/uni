//
// Created by nik on 11/23/19.
//

#ifndef SDLGAME_ENTITY_H
#define SDLGAME_ENTITY_H

#pragma once

#include <assert.h>
#include <stdlib.h>
#include <maze.h>

#include "structs/arraylist.h"
#include "util.h"

#define E_DEF_HP 100.0f
#define E_DEF_NEXT_SEARCH 200
#define E_DEF_NEXT_SPAWN 400
#define E_DEF_MAX_ENEMIES(x) ((x) * 2 + 5)
#define E_DEF_NEXT_MOVE 40
#define E_DEF_PNEXT_MOVE 5
#define E_DEF_PNEXT_SHOT 20
#define E_DEF_DMG 40.0f

#define RAND_DIR ((rand() % 3) + 1)
enum dir {
	DIR_NONE,
	DIR_UP,
	DIR_DOWN,
	DIR_LEFT,
	DIR_RIGHT,
};

enum entities {
	E_NONE,
	E_PLAYER,
	E_LIGHT,
	E_ENEMY,
	E_SPAWNER,
	E_PEW,
};

typedef struct player {
	enum dir dir;
	int next_shot;
	float dmg;
} player_t;

typedef struct light {
	float intensity;
} light_t;

typedef struct enemy {
	int next_search;
	struct mgraph* mgraph;
	astack_t* path;
	void* origin;
} enemy_t;

typedef struct spawner {
	int next_spawn;
	int rate;
	int enemy_count;
} spawner_t;

typedef struct pew {
	enum dir dir;
	float dmg;
} pew_t;

typedef struct entity {
	int x;
	int y;
	float hp;
	int next_move;
	union {
		player_t player;
		enemy_t enemy;
		spawner_t spawner;
		light_t light;
		pew_t pew;
	};
	enum entities type;
} entity_t;

extern int alist_rm_e_type_cmp(const void* e1, const void* e2, unsigned long size);

extern void alist_rm_e_type(alist_t* list, enum entities type);

extern int entity_move(entity_t* e, char const* lvl, int width, int bound, enum dir dir);


#endif //SDLGAME_ENTITY_H
