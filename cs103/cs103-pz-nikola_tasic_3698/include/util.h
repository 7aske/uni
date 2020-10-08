//
// Created by nik on 11/23/19.
//

#ifndef AGAME_UTIL_H
#define AGAME_UTIL_H

#pragma once

#include <stdlib.h>
#include <math.h>
#include <assert.h>

#include "structs/astack.h"
#include "structs/queue.h"
#include "cleanup_functions.h"

extern int bresenham(int x0, int y0, int x1, int y1, char const* level, int width, int boundary_block);

extern astack_t*
backtrack_find(int x0, int y0, int x1, int y1, char const* level, int width, int height, int boundary);

extern int
_backtrack_find(int x0, int y0, int x1, int y1, char const* level, char* visited, int width, int height, int boundary,
				astack_t* stack);

float euclidean_dist(int sx, int sy, int dx, int dy);

int manhattan_dist(int sx, int sy, int dx, int dy);

void reverse_astack(astack_t* astack);


#endif //AGAME_UTIL_H
