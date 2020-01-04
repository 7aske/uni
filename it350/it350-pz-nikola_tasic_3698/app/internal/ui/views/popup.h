//
// Created by nik on 12/29/19.
//

#ifndef IT350_PZ_APP_POPUP_H
#define IT350_PZ_APP_POPUP_H

#pragma once

#include "ui/state.h"

void popup_set_question(state_t* state, const char* question);

int popup_action(state_t* state);

#endif //IT350_PZ_APP_POPUP_H
