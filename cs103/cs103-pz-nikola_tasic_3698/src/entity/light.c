//
// Created by nik on 12/4/19.
//

#include "state.h"
#include "entity/light.h"

float light_calc(entity_t* source, int x, int y, float current_light, state_t* state) {
	assert(source != NULL);
	float intensity = 3;
	if (source->type == E_LIGHT) {
		intensity = source->light.intensity;
	}
	float rel_light = 0, dist, a;
	a = 1.0f - (50.0f - (rand() % 25)) / 100.0f;
	dist = euclidean_dist(source->x, source->y, x, y);

	switch (state->light_mode) {
		case L_NONE:
			rel_light = 0.0f;
			break;
		case L_LOS:
			if (bresenham(source->x, source->y, x, y, state->level.maze, state->level.w, state->level.b_wall)) {
				rel_light = 255.0f / (dist / intensity) * a;
				rel_light = rel_light > 255 ? 255 : rel_light;
			} else {
				rel_light = 170.0f / dist * a;
				rel_light = rel_light > 170 ? 170 : rel_light;
			}
			break;
		case L_AREA:
			rel_light = 255.0f / (dist / intensity) * a;
			if (rel_light > 255) {
				rel_light = 255;
			} else if (rel_light < 40) {
				rel_light = 0;
			}

			break;
		case L_ALL:
			rel_light = 255.0f;
			break;
	}
	return fmaxf(rel_light, current_light);
}
