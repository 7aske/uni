//
// Created by nik on 12/5/19.
//

#include "entity/spawner.h"

int spawner_spawn(entity_t* e, state_t* state) {
	assert(e != NULL && state != NULL);
	assert(e->type == E_SPAWNER);
	if (!state->spawn_enemies) {
		return 0;
	}
	if (E_DEF_MAX_ENEMIES(state->levelc) <= e->spawner.enemy_count) {
		return 0;
	}
	if (e->spawner.next_spawn > 0) {
		e->spawner.next_spawn -= e->spawner.rate;
		return 0;
	} else {
		e->spawner.next_spawn = E_DEF_NEXT_SPAWN;
	}
	int x, y;
	while (state->level.maze[(y = rand() % state->level.h) * state->level.w + (x = rand() % state->level.w)] !=
		   B_FLOOR);
	e->spawner.enemy_count++;
	printf("SPAWNER SPAWN          (%02d, %02d)    %02d/%02d    %ld\n", x, y, e->spawner.enemy_count, E_DEF_MAX_ENEMIES(state->levelc),
		   time(0) - state->start_time);
	ev_enemy_spawn(state, x, y, e);
	return 1;
}

entity_t spawner_new() {
	entity_t e;
	e.x = 0;
	e.y = 0;
	e.hp = 1000;
	e.type = E_SPAWNER;
	e.spawner.rate = 3;
	e.spawner.next_spawn = E_DEF_NEXT_SPAWN;
	e.spawner.enemy_count = 0;
	return e;
}
