//
// Created by nik on 11/23/19.
//

#include "maze.h"

#ifndef print_maze
#define print_maze(lvl) if(lvl != NULL)\
for (int lvly = 0; lvly < LVL_H; ++lvly){\
for (int lvlx = 0; lvlx < LVL_W; ++lvlx) putc(lvl[lvly * LVL_W + lvlx], stdout);\
putc('\n', stdout);}
#endif

int _is_safe(char const* maze, int x, int y) {
	assert(maze != NULL);
	if (x >= 1 && x < LVL_W - 1 && y >= 1 && y < LVL_H - 1 && maze[y * LVL_W + x] == B_FLOOR)
		return 1;
	return 0;
}


int _solve(char* maze, int x, int y, char* sol, int exit_x, int exit_y) {
	assert(maze != NULL);
	assert(sol != NULL);

	if (x == exit_x && y == exit_y) {
		sol[y * LVL_W + x] = B_PATH;
		return 1;
	}
	if (_is_safe(sol, x, y)) {
		sol[y * LVL_W + x] = B_PATH;
		if (_solve(maze, x + 1, y, sol, exit_x, exit_y)) {
			return 1;
		}
		if (_solve(maze, x, y + 1, sol, exit_x, exit_y)) {
			return 1;
		}
		if (_solve(maze, x - 1, y, sol, exit_x, exit_y)) {
			return 1;
		}
		if (_solve(maze, x, y - 1, sol, exit_x, exit_y)) {
			return 1;
		}
		sol[y * LVL_W + x] = B_FLOOR;
		return 0;
	}
	return 0;
}

void maze_solve(char* maze, int start_x, int start_y, int exit_x, int exit_y) {
	assert(maze != NULL);
	assert(exit_x > 0);
	assert(exit_y > 0);

	if (solution != NULL)
		free(solution);
	solution = malloc(LVL_W * LVL_H);
	memcpy(solution, maze, LVL_W * LVL_H);
	_solve(maze, start_x, start_y, solution, exit_x, exit_y);
}

void overlay_solution(char* maze, int start_x, int start_y, int exit_x, int exit_y) {
	assert(maze != NULL);
	assert(exit_x > 0);
	assert(exit_y > 0);

	maze_solve(maze, start_x, start_y, exit_x, exit_y);
	for (int y = 0; y < LVL_H; ++y) {
		for (int x = 0; x < LVL_W; ++x) {
			if (solution[y * LVL_W + x] == B_PATH) {
				if (maze[y * LVL_W + x] != B_EXIT) {
					maze[y * LVL_W + x] = maze[y * LVL_W + x] == B_PATH ? B_FLOOR : B_PATH;
				}
			}
		}
	}
}

void maze_carve(char* maze, int width, int height, int x, int y) {
	int x1, y1;
	int x2, y2;
	int dx, dy;
	int dir, count;

	dir = rand() % 4;
	count = 0;
	while (count < 4) {
		dx = 0;
		dy = 0;
		switch (dir) {
			case 0:
				dx = 1;
				break;
			case 1:
				dy = 1;
				break;
			case 2:
				dx = -1;
				break;
			default:
				dy = -1;
				break;
		}
		x1 = x + dx;
		y1 = y + dy;
		x2 = x1 + dx;
		y2 = y1 + dy;
		if (x2 > 0 && x2 < width - 1 &&
			y2 > 0 && y2 < height - 1 &&
			maze[y1 * width + x1] == B_WALL &&
			maze[y2 * width + x2] == B_WALL) {
			maze[y1 * width + x1] = B_FLOOR;
			maze[y2 * width + x2] = B_FLOOR;
			x = x2;
			y = y2;
			dir = rand() % 4;
			count = 0;
		} else {
			dir = (dir + 1) % 4;
			count += 1;
		}
	}

}

maze_t maze_new(int start_x, int start_y) {
	maze_t maze;
	// assert(maze != (void*) 0);
	int x, y;
	maze.maze = generate_maze();
	maze.doodads = generate_doodads(maze.maze);
	maze.maze[1 * LVL_W + 1] = B_FLOOR;
	// avoiding x % 0 SIGFPE
	if (WRLD_H == 1 || WRLD_W == 1) {
		while (maze.maze[(y = (rand() % LVL_H)) * LVL_W + (x = (rand() % LVL_W))] ==
			   B_WALL);
	} else {
		while (maze.maze[(y = (rand() % (LVL_H - SCR_H)) + SCR_H) * LVL_W + (x = (rand() % (LVL_W - SCR_W)) + SCR_W)] ==
			   B_WALL);
	}

	maze.exit_x = x;
	maze.exit_y = y;
	maze.maze[maze.exit_y * LVL_W + maze.exit_x] = B_EXIT;
	maze.h = LVL_H;
	maze.w = LVL_W;
	maze.b_wall = B_WALL;
	maze.mgraph = to_graph(maze.maze, LVL_W, LVL_H, (char) B_WALL, start_x, start_y, x, y);
	assert(maze.mgraph->end->x == x && maze.mgraph->end->y == y);
	assert(maze.mgraph->start->x == start_x && maze.mgraph->start->y == start_y);
	return maze;
}

char* generate_maze() {
	int x, y;
	char* maze = (char*) malloc(LVL_H * LVL_W);
	memset(maze, B_WALL, LVL_H * LVL_W);
	srand(time(NULL));
	for (y = 1; y < LVL_H; y += 2) {
		for (x = 1; x < LVL_W; x += 2) {
			maze_carve(maze, LVL_W, LVL_H, x, y);
		}
	}
	return maze;
}

char* generate_doodads(char const* maze) {
	assert(maze != NULL);
	char* dd = calloc(LVL_H * LVL_W, sizeof(char));
	int i, dx, dy;

	srand(time(NULL));
	for (i = 0; i < BRICK_COUNT; ++i) {
		while (maze[(dy = rand() % LVL_H) * LVL_W + (dx = rand() % LVL_W)] != B_WALL);
		dd[dy * LVL_W + dx] = D_BRICK;
	}
	for (i = 0; i < GRATE_COUNT; ++i) {
		while (maze[(dy = rand() % LVL_H) * LVL_W + (dx = rand() % LVL_W)] != B_WALL);
		dd[dy * LVL_W + dx] = D_GRATE;
	}
	for (i = 0; i < OOZE_COUNT; ++i) {
		while (maze[(dy = rand() % LVL_H) * LVL_W + (dx = rand() % LVL_W)] != B_FLOOR);
		dd[dy * LVL_W + dx] = D_OOZE;
	}
	for (i = 0; i < SKULL_COUNT; ++i) {
		while (maze[(dy = rand() % LVL_H) * LVL_W + (dx = rand() % LVL_W)] != B_FLOOR);
		dd[dy * LVL_W + dx] = D_SKULL;
	}
	for (i = 0; i < PIPE_COUNT; ++i) {
		while (maze[(dy = rand() % LVL_H) * LVL_W + (dx = rand() % LVL_W)] != B_WALL);
		dd[dy * LVL_W + dx] = rand() % 2 ? D_PIPE1 : D_PIPE2;
	}
	return dd;
}

void maze_test_macros() {
	printf("LVL_H    = %3d\n", LVL_H);
	printf("LVL_W    = %3d\n", LVL_W);
	printf("B_WALL   = %3d\n", B_WALL);
	printf("B_FLOOR  = %3d\n", B_FLOOR);
	printf("B_PATH   = %3d\n", B_PATH);
	printf("B_EXIT   = %3d\n", B_EXIT);
}

void maze_clear(maze_t* maze) {
	assert(maze != NULL);
	if (maze->maze != NULL) {
		free(maze->maze);
		maze->maze = NULL;
	}
	if (maze->doodads != NULL) {
		free(maze->doodads);
		maze->doodads = NULL;
	}
	if (maze->mgraph != NULL) {
		mgraph_destroy(&maze->mgraph);
	}
}
