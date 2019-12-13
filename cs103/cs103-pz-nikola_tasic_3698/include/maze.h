//
// Created by nik on 11/23/19.
//

#ifndef SDLGAME_MAZE_H
#define SDLGAME_MAZE_H

#pragma once

#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <time.h>
#include <stdio.h>

#include "macro_definitions.h"
#include "graph.h"

#define BRICK_COUNT 80
#define GRATE_COUNT 30
#define OOZE_COUNT 30
#define SKULL_COUNT 10
#define PIPE_COUNT 30
#define TORCH_COUNT 10

#define NBIT(x, n) (x & ( 1 << n )) >> n

enum blocks_e {
	B_NONE,
	B_PATH,
	B_WALL,
	B_FLOOR,
	B_EXIT
};

enum doodads_e {
	D_NONE,
	D_SKULL,
	D_PIPE1,
	D_PIPE2,
	D_OOZE,
	D_GRATE,
	D_BRICK,
	D_TORCH
};
//  000 00

typedef struct maze {
	char* maze;
	struct mgraph* mgraph;
	char* doodads;
	int w;
	int h;
	int b_wall;
	int exit_x;
	int exit_y;
} maze_t;

static char* solution = NULL;

static int _is_safe(char const* maze, int x, int y);

static int _solve(char* maze, int x, int y, char* sol, int exit_x, int exit_y);

static void maze_solve(char* maze, int start_x, int start_y, int exit_x, int exit_y);

extern void overlay_solution(char* maze, int start_x, int start_y, int exit_x, int exit_y);

extern void maze_carve(char* maze, int width, int height, int x, int y);

extern maze_t maze_new(int start_x, int start_y);

extern void maze_clear(maze_t* maze);

extern char* generate_maze();

extern char* generate_doodads(char const* maze);

extern void maze_test_macros();

#endif //SDLGAME_MAZE_H
