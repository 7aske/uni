//
// Created by nik on 11/28/19.
//

#include "state.h"


void state_change_ren(state_t* state, int step) {
	assert(state != (void*) 0);
	assert(step == 1 || step == -1);
	// REN_BLOCKS, REN_ENTITIES, REN_SOLUTION
	switch (state->ren_mode) {
		case REN_ENTITIES:
			if (step == 1) {
				state->ren_mode = REN_SOLUTION;
			} else {
				state->ren_mode = REN_BLOCKS;
			}
			break;
		case REN_SOLUTION:
			if (step == 1) {
				state->ren_mode = REN_BLOCKS;
			} else {
				state->ren_mode = REN_ENTITIES;
			}
			break;
		case REN_BLOCKS:
		case REN_ALL:
			if (step == 1) {
				state->ren_mode = REN_ENTITIES;
			} else {
				state->ren_mode = REN_SOLUTION;
			}
			break;
		default:
			state->ren_mode = REN_BLOCKS;
	}
}

void state_change_light(state_t* state, int step) {
	assert(state != (void*) 0);
	assert(step == 1 || step == -1);
	// L_NONE, L_LOS, L_AREA, L_ALL
	switch (state->light_mode) {
		case L_NONE:
			break;
		case L_LOS:
			if (step == 1) {
				state->light_mode = L_AREA;
			} else {
				state->light_mode = L_ALL;
			}
			break;
		case L_AREA:
			if (step == 1) {
				state->light_mode = L_ALL;
			} else {
				state->light_mode = L_LOS;
			}
			break;
		case L_ALL:
			if (step == 1) {
				state->light_mode = L_LOS;
			} else {
				state->light_mode = L_AREA;
			}
			break;
	}
}

char const* get_ren_mode(enum render_mode mode) {
	switch (mode) {
		case REN_BLOCKS:
			return "Blocks";
		case REN_ENTITIES:
			return "Entities";
		case REN_SOLUTION:
			return "Solution";
		case REN_ALL:
			return "All";
	}
}

char const* get_light_mode(enum lights light) {
	switch (light) {
		case L_NONE:
			return "None";
		case L_LOS:
			return "LOS";
		case L_AREA:
			return "Area";
		case L_ALL:
			return "All";
		default:
			return "None";
	}
}

void state_change_graph(state_t* state, int dir) {
	assert(state != NULL);
	int i, j;
	entity_t* e1 = NULL, * e2 = NULL, * curr_e = NULL;
	if (dir == 1) {
		for (i = 0; i < alist_size(state->entities); ++i) {
			e1 = alist_get(state->entities, i);
			if (e1->type == E_ENEMY) {
				if (e1->enemy.mgraph == state->curr_graph) {
					curr_e = e1;
					break;
				}
			}
		}
		if (curr_e == NULL) {
			for (i = 0; i < alist_size(state->entities); ++i) {
				e1 = alist_get(state->entities, i);
				if (e1->type == E_ENEMY) {
					curr_e = e1;
					state->curr_graph = e1->enemy.mgraph;
					break;
				}
			}
		}

		for (i = alist_idxof_ptr(state->entities, curr_e) + 1; i < alist_size(state->entities); ++i) {
			e1 = alist_get(state->entities, i);
			if (e1->type == E_ENEMY) {
				state->curr_graph = e1->enemy.mgraph;
				break;
			}
		}
	} else if (dir == -1) {
		for (i = alist_size(state->entities) - 1; i >= 0; --i) {
			e1 = alist_get(state->entities, i);
			if (e1->type == E_ENEMY) {
				if (e1->enemy.mgraph == state->curr_graph) {
					curr_e = e1;
					break;
				}
			}
		}
		if (curr_e == NULL) {
			for (i = alist_size(state->entities) - 1; i >= 0; --i) {
				e1 = alist_get(state->entities, i);
				if (e1->type == E_ENEMY) {
					curr_e = e1;
					state->curr_graph = e1->enemy.mgraph;
					break;
				}
			}
		}

		for (i = alist_idxof_ptr(state->entities, curr_e) - 1; i >= 0; --i) {
			e1 = alist_get(state->entities, i);
			if (e1->type == E_ENEMY) {
				state->curr_graph = e1->enemy.mgraph;
				break;
			}
		}
	} else if (dir == 0) {
		state->curr_graph = state->level.mgraph;
	}

}
