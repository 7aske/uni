//
// Created by nik on 12/4/19.
//

#ifndef AGAME_SPAWNER_H
#define AGAME_SPAWNER_H

#pragma once

#include "entity/entity.h"
#include "state.h"
#include "event/event.h"

int spawner_spawn(entity_t* e, state_t* state);

entity_t spawner_new();

#endif //AGAME_SPAWNER_H
