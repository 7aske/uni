//
// Created by nik on 11/23/19.
//
#include "entity/entity.h"

int entity_move(entity_t* e, char const* lvl, int width, int bound, enum dir dir) {
	assert(e != (void*) 0);
	assert(lvl != (void*) 0);

	int dx, dy;
	switch (dir) {
		case DIR_RIGHT:
			dx = 1;
			dy = 0;
			break;
		case DIR_LEFT:
			dx = -1;
			dy = 0;
			break;
		case DIR_DOWN:
			dx = 0;
			dy = 1;
			break;
		case DIR_UP:
			dx = 0;
			dy = -1;
			break;
		case DIR_NONE:
			dx = 0;
			dy = 0;
			break;
		default:
			dx = 0;
			dy = 0;
	}

	if (lvl[(e->y + dy) * width + (e->x + dx)] != bound) {
		e->y += dy;
		e->x += dx;
		return 1;
	}
	return 0;
}

void alist_rm_e_type(alist_t* list, enum entities type) {
	int x;
	entity_t e;
	e.type = type;
	while ((x = alist_idxof_cmp(list, &e, alist_rm_e_type_cmp)) != -1) {
		alist_rm_idx(list, x);
	}
}

int alist_rm_e_type_cmp(const void* e1, const void* e2, unsigned long size) {
	enum entities t1 = ((entity_t*) e1)->type;
	enum entities t2 = ((entity_t*) e2)->type;
	if (t1 > t2) {
		return 1;
	} else if (t1 < t2) {
		return -1;
	} else {
		return 0;
	}
}

