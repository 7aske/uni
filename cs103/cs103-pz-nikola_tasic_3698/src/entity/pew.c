//
// Created by nik on 11/25/19.
//

#include "entity/pew.h"


int pew_move(entity_t* e, char const* lvl, int width, int bound) {
	assert(e != (void*) 0);
	assert(e->type == E_PEW);
	return entity_move(e, lvl, width, bound, e->pew.dir);
}
