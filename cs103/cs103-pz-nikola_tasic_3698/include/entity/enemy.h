//
// Created by nik on 11/25/19.
//

#ifndef AGAME_ENEMY_H
#define AGAME_ENEMY_H

#pragma once

#include "entity/entity.h"

entity_t enemy_new(int x, int y, void* origin);

void enemy_search(entity_t* e, entity_t* tar, maze_t const* level, int force_search);

void enemy_randmove(entity_t* e, char const* lvl, int width, int bound);

void enemy_fpath(entity_t* e, char const* lvl, int width, int bound);

void enemy_lockmove(entity_t* e, entity_t* e1, char const* lvl, int width, int bound);


#endif //AGAME_ENEMY_H
