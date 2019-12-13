//
// Created by nik on 12/6/19.
//

#ifndef AGAME_GRAPH_H
#define AGAME_GRAPH_H

#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <float.h>
#include <limits.h>
#include <SDL2/SDL.h>

#include "structs/pqueue.h"
#include "draw_misc.h"
#include "util.h"

#define VISITED_COLOR SDL_SetRenderDrawColor(ren, 183, 65, 14, 200);
#define SPECIAL_COLOR SDL_SetRenderDrawColor(ren, 248, 177, 13, 200);
#define PATH_COLOR    SDL_SetRenderDrawColor(ren, 0, 158, 96, 200);
#define DEFAULT_COLOR SDL_SetRenderDrawColor(ren, 91, 52, 46, 200);
#define TEXT_COLOR &(SDL_Color){253, 183, 100, 255}

enum gnode_type {
	GNS = 1, // start
	GNE = 2, // end
	GNN = 3, // node
};

struct gnode {
	int x;
	int y;

	int h; // heuristic
	int f; // heuristic
	int g; // heuristic

	int visited;
	int rendered;
	int path;

	struct gnode* came_from;

	enum gnode_type type;
	struct gnode* nodes[4];
};

struct mgraph {
	struct gnode* start;
	struct gnode* end;
	llist_t* mem;
};

// got tired not being able to properly free the graph
// malloc wrapper that saves all references to a list for easy free-ing
static void* nalloc(struct mgraph* graph, size_t size) {
	void* chunk = malloc(size);
	if (graph->mem == NULL) {
		graph->mem = llist_new(sizeof(void*));
	}
	llist_add_front(graph->mem, &chunk);
	return chunk;
}

static struct gnode* gnode_new(int x, int y, enum gnode_type type, struct mgraph* graph) {
	struct gnode* newgnode = (struct gnode*) nalloc(graph, sizeof(struct gnode));
	newgnode->type = type;
	newgnode->x = x;
	newgnode->y = y;

	newgnode->visited = 0;
	newgnode->rendered = 0;

	newgnode->h = 0;
	newgnode->f = 0;
	newgnode->g = 0;

	newgnode->came_from = NULL;
	newgnode->path = 0;

	newgnode->nodes[0] = NULL; // north
	newgnode->nodes[1] = NULL; // east
	newgnode->nodes[2] = NULL; // south
	newgnode->nodes[3] = NULL; // west
	return newgnode;
}


static struct mgraph*
to_graph(char const* maze, int width, int height, char wall, int start_x, int start_y, int exit_x, int exit_y) {
	int curr, prev, next, x, y;
	struct mgraph* mgraph = (struct mgraph*) calloc(1, sizeof(struct mgraph));
	struct gnode* node = NULL,
			* top = NULL,
			* leftnode = NULL;
	// temporary top row of every search
	struct gnode** topnodes = (struct gnode**) calloc(width, sizeof(struct gnode*));

	for (y = 1; y < height - 1; ++y) {
		prev = wall;
		curr = wall;
		next = maze[y * width + 1];
		leftnode = NULL;
		for (x = 1; x < width - 1; ++x) {
			prev = curr;
			curr = next;
			next = maze[y * width + x + 1];
			node = NULL;

			// if we're on the wall do nothing
			if (curr == wall) {
				continue;
			}


			if (prev != wall) {
				if (next != wall) {
					// path path path
					if ((maze[(y - 1) * width + x] != wall) || (maze[(y + 1) * width + x] != wall)) {
						node = gnode_new(x, y, GNN, mgraph);
						leftnode->nodes[1] = node;// left node east -> node
						node->nodes[3] = leftnode; // nod west -> left node
						leftnode = node;
					}
				} else {
					// path path wall
					node = gnode_new(x, y, GNN, mgraph);
					leftnode->nodes[1] = node;
					node->nodes[3] = leftnode;
					leftnode = node;
				}

			} else {
				// wall path path
				if (next != wall) {
					node = gnode_new(x, y, GNN, mgraph);
					leftnode = node;
				} else {
					// wall path wall
					if ((maze[(y - 1) * width + x] == wall) || (maze[(y + 1) * width + x] == wall)) {
						node = gnode_new(x, y, GNN, mgraph);
					} else {
						// one edge case where we'd miss the exit/start node
						if ((x == exit_x && y == exit_y) || (x == start_x && y == start_y)) {
							node = gnode_new(x, y, GNE, mgraph);
							leftnode = node;
						}
					}
				}
			}

			// if we're at the exit/start nodes add them
			// and update corresponding graph ptrs
			if (x == exit_x && y == exit_y) {
				if (node != NULL) {
					node->type = GNE;
				} else {
					node = gnode_new(x, y, GNE, mgraph);
					leftnode->nodes[1] = node;
					node->nodes[3] = leftnode;
					leftnode = node;
				}
				mgraph->end = node;
			} else if (x == start_x && y == start_y) {
				if (node != NULL) {
					node->type = GNS;
				} else {
					// printf("%d %d %d\n", prev, curr, next);
					node = gnode_new(x, y, GNS, mgraph);
					leftnode->nodes[1] = node;
					node->nodes[3] = leftnode;
					leftnode = node;

				}
				mgraph->start = node;
			}


			if (node != NULL) {
				// connect the queued up top node
				if ((maze[(y - 1) * width + x] != wall)) {
					top = topnodes[x];
					top->nodes[2] = node;
					node->nodes[0] = top;
				}
				// set this as a possible top node if there is no wall below
				if ((maze[(y + 1) * width + x] != wall)) {
					topnodes[x] = node;
				} else {
					topnodes[x] = NULL;
				}

			}
		}
	}
	free(topnodes);
	return mgraph;
}

static void draw_node(SDL_Renderer* ren, TTF_Font* font, int xoff, int yoff, struct gnode* node, int ren_h) {
	int x1, y1, x2, y2, i;
	char buf[6];
	struct gnode* n;
	SDL_Rect rect;

	// convert block sized coordinates into acutal on-screen pixels
	y1 = (node->y - yoff) * BSIZE + BSIZE / 2;
	x1 = (node->x - xoff) * BSIZE + BSIZE / 2;

	if (ren_h && node->visited) {
		// drawing text has a big performance hit, so don't heuristics for nodes that are not on screen
		if ((((node->x > xoff) && (node->x < xoff + SCR_W)) && ((node->y > yoff) && (node->y < yoff + SCR_H)))) {
			snprintf(buf, 6, "%d", node->f);
			draw_text(ren, font, buf, x1 - 32, y1 - 24, TEXT_COLOR);
		}
	}

	node->rendered = 1;

	for (i = 0; i < 4; ++i) {
		n = node->nodes[i];
		if (n != NULL) {
			x2 = (n->x - xoff) * BSIZE + BSIZE / 2;
			y2 = (n->y - yoff) * BSIZE + BSIZE / 2;
			DEFAULT_COLOR
			if (node->visited && n->visited)
				VISITED_COLOR
			if (node->path && n->path) {
				PATH_COLOR
				if (y1 != y2 && !n->rendered) {
					if (y1 > y2) {
						// facing up
						SDL_RenderDrawLine(ren, x2, y2 + 4, x2 + 8, y2 + 14);
						SDL_RenderDrawLine(ren, x2, y2 + 4, x2 + 7, y2 + 14);
						SDL_RenderDrawLine(ren, x2, y2 + 4, x2 - 8, y2 + 14);
						SDL_RenderDrawLine(ren, x2, y2 + 4, x2 - 7, y2 + 14);
					} else {
						// facing down
						SDL_RenderDrawLine(ren, x2, y2 - 4, x2 - 8, y2 - 14);
						SDL_RenderDrawLine(ren, x2, y2 - 4, x2 - 7, y2 - 14);
						SDL_RenderDrawLine(ren, x2, y2 - 4, x2 + 8, y2 - 14);
						SDL_RenderDrawLine(ren, x2, y2 - 4, x2 + 7, y2 - 14);
					}
				} else if (x1 != x2 && !n->rendered) {
					if (x1 > x2) {
						// facing left
						SDL_RenderDrawLine(ren, x2 + 4, y2, x2 + 14, y2 - 8);
						SDL_RenderDrawLine(ren, x2 + 4, y2, x2 + 14, y2 - 7);
						SDL_RenderDrawLine(ren, x2 + 4, y2, x2 + 14, y2 + 8);
						SDL_RenderDrawLine(ren, x2 + 4, y2, x2 + 14, y2 + 7);
					} else {
						// facing right
						SDL_RenderDrawLine(ren, x2 - 4, y2, x2 - 14, y2 - 8);
						SDL_RenderDrawLine(ren, x2 - 4, y2, x2 - 14, y2 - 7);
						SDL_RenderDrawLine(ren, x2 - 4, y2, x2 - 14, y2 + 8);
						SDL_RenderDrawLine(ren, x2 - 4, y2, x2 - 14, y2 + 7);
					}
				}
			}
			if (!n->rendered) {
				SDL_RenderDrawLine(ren, x1, y1, x2, y2);
				SDL_RenderDrawLine(ren, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
			}


			SDL_SetRenderDrawColor(ren, 12, 12, 12, 255);
			// recurse and draw non-rendered nodes
			if (!n->rendered) {
				draw_node(ren, font, xoff, yoff, n, ren_h);
			}
		}
	}

	// prepare rectangle to draw as a node
	rect.x = x1 - 4;
	rect.y = y1 - 4;
	rect.w = 8;
	rect.h = 8;
	DEFAULT_COLOR
	if (node->visited) {
		VISITED_COLOR
	}
	if (node->path) {
		// yellow blocks for end and exit (a bit bigger)
		if (node->type == GNS || node->type == GNE) {
			SPECIAL_COLOR
			rect.x = x1 - 8;
			rect.y = y1 - 8;
			rect.w = 16;
			rect.h = 16;
		} else {
			PATH_COLOR
		}
	}


	SDL_RenderFillRect(ren, &rect);
	SDL_SetRenderDrawColor(ren, 12, 12, 12, 255);

	node->rendered = 0;
}

// function used to compare node heuristic priorities
// in A* algorithm
static int gnodecmp(void const* n1, void const* n2, size_t size) {
	int p1 = *(int*) n1;
	int p2 = *(int*) n2;

	if (p1 < p2) {
		return 1;
	} else if (p1 > p2) {
		return -1;
	} else {
		return 0;
	}
}

static astack_t* solve_astar(struct mgraph* graph) {
	struct gnode* start = graph->start;
	struct gnode* end = graph->end;
	struct gnode** node = NULL;
	struct gnode* n = NULL;
	int coord[2];
	// initialize solution stack (used for enemies)
	astack_t* solution = stack_new(sizeof(int[2]));
	int i;

	node = &graph->start;
	(*node)->came_from = NULL;
	(*node)->path = 1;

	// push the start node to the solution stack
	coord[0] = graph->start->x;
	coord[1] = graph->start->y;
	stack_push(solution, coord);

	pqueue_t* visited = pqueue_new(sizeof(struct gnode*), sizeof(int));
	pqueue_set_cmp(visited, gnodecmp);

	pqueue_enqueue(visited, &start, &(*node)->f);

	while (!pqueue_isempty(visited)) {
		node = (struct gnode**) pqueue_dequeue(visited);
		(*node)->path = 0;
		(*node)->visited = 1;
		if ((*node)->x == end->x && (*node)->y == end->y) {
			// printf("holy shit\n");
			break;
		}

		for (i = 0; i < 4; ++i) {
			n = (*node)->nodes[i];
			if (n != NULL) {
				// calculate heuristics based on distance to exit and
				// relative distance from current node and last node
				n->h = (int) euclidean_dist(n->x, n->y, end->x, end->y) * 2;
				n->g = manhattan_dist(n->x, n->y, (*node)->x, (*node)->y);
				// apparently better results by not factoring in the previous node cost
				n->f = /*(*node)->f +*/ n->g + n->h;
				if (!n->visited) {
					// leave some breadcrumbs of how we got here
					n->came_from = *node;
					pqueue_enqueue(visited, &n, &n->f);
				}
			}
		}
		free(node);
	}
	n = *node;
	// free(node);
	n->path = 1;
	coord[0] = end->x;
	coord[1] = end->y;
	stack_push(solution, coord);

	// reverse the solution queue into a stack;
	while (n != NULL) {
		n->path = 1;
		coord[0] = n->x;
		coord[1] = n->y;
		stack_push(solution, coord);
		n = n->came_from;
	}
	pqueue_destroy(&visited);
	return solution;
}

static void mgraph_destroy(struct mgraph** graph) {
	assert(graph != NULL);
	if ((*graph)->mem != NULL) {
		while (!llist_isempty((*graph)->mem)) {
			void** ptr = llist_get_last((*graph)->mem);
			free(*ptr);
			llist_rm_back((*graph)->mem);
		}
		llist_destroy((*graph)->mem);
	}

	free(*graph);
	*graph = NULL;
}

static void connect_nodes(astack_t* nodes) {
	int x, y;
	astack_t* helper = stack_new(nodes->size);
	int* elem, * next;
	int newelem[2];
	while (!stack_isempty(nodes)) {
		elem = stack_pop(nodes);
		// printf("%d %d\n", elem[0], elem[1]);
		stack_push(helper, elem);
		free(elem);
	}
	while (!stack_isempty(helper)) {
		elem = stack_pop(helper);
		x = elem[0];
		y = elem[1];
		stack_push(nodes, elem);
		next = stack_peek(helper);
		if (next != NULL) {
			while (x != next[0] || y != next[1]) {
				if (x < next[0]) {
					x++;
				} else if (x > next[0]) {
					x--;
				} else if (y < next[1]) {
					y++;
				} else if (y > next[1]) {
					y--;
				}
				newelem[0] = x;
				newelem[1] = y;
				stack_push(nodes, newelem);
			}
		}
		free(elem);
	}
	stack_destroy(helper);
}

#endif //AGAME_GRAPH_H
