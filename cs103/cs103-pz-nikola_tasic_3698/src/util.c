//
// Created by nik on 11/23/19.
//

#include "util.h"

int bresenham(int x0, int y0, int x1, int y1, char const* level, int width, int boundary_block) {
	int dx = abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
	int dy = -abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
	int err = dx + dy, e2; /* error value e_xy */

	for (;;) {
		if (x0 == x1 && y0 == y1) break;
		e2 = 2 * err;
		if (e2 >= dy) {
			err += dy;
			x0 += sx;
		}
		if (e2 <= dx) {
			err += dx;
			y0 += sy;
		}
		if (x0 == x1 && y0 == y1) return 1;
		if (level[y0 * width + x0] == boundary_block) return 0;
	}
}


float euclidean_dist(int sx, int sy, int dx, int dy) {
	return sqrtf(powf((float) sx - (float) dx, 2) + powf((float) sy - (float) dy, 2));
}

int manhattan_dist(int sx, int sy, int dx, int dy) {
	return abs(sx - dx) + abs(sy - dy);
}

astack_t* backtrack_find(int x0, int y0, int x1, int y1, char const* level, int width, int height, int boundary) {
	astack_t* sol_stack = stack_new(sizeof(int[2]));
	void* __attribute__((cleanup(_afree))) visited = calloc(width * height, sizeof(*level));
	_backtrack_find(x0, y0, x1, y1, level, visited, width, height, boundary, sol_stack);
	return sol_stack;
}

int _backtrack_find(int x0, int y0, int x1, int y1, char const* level, char* visited, int width, int height,
					int boundary,
					astack_t* stack) {
	#define VISITED 1
	assert(level != NULL);
	int temp[2] = {x0, y0};

	if (x0 == x1 && y0 == y1) {
		visited[y0 * width + x0] = VISITED;
		stack_push(stack, temp);
		return 1;
	}

	if (x0 >= 1 && x0 < width - 1 && y0 >= 1 && y0 < height - 1 &&
		(level[y0 * width + x0] != boundary && visited[y0 * width + x0] != VISITED)) {
		visited[y0 * width + x0] = VISITED;
		stack_push(stack, temp);

		if (_backtrack_find(x0 + 1, y0, x1, y1, level, visited, width, height, boundary, stack)) {
			return 1;
		}
		if (_backtrack_find(x0, y0 + 1, x1, y1, level, visited, width, height, boundary, stack)) {
			return 1;
		}
		if (_backtrack_find(x0 - 1, y0, x1, y1, level, visited, width, height, boundary, stack)) {
			return 1;
		}
		if (_backtrack_find(x0, y0 - 1, x1, y1, level, visited, width, height, boundary, stack)) {
			return 1;
		}
		visited[y0 * width + x0] = !VISITED;
		void* __attribute__((cleanup(_afree))) ptr = stack_pop(stack);
		return 0;
	}
	return 0;
}

void reverse_astack(astack_t* stack) {
	queue_t* queue = queue_new(stack->size);
	void* temp;
	while (!stack_isempty(stack)) {
		temp = stack_pop(stack);
		queue_enqueue(queue, temp);
		free(temp);
	}
	while (!queue_isempty(queue)) {
		temp = queue_dequeue(queue);
		stack_push(stack, temp);
		free(temp);
	}
	queue_destroy(queue);
}
