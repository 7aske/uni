//
// Created by nik on 12/29/19.
//

#ifndef IT350_PZ_APP_INPUT_H
#define IT350_PZ_APP_INPUT_H

#pragma once

#include "ui/state.h"
#include "ui/views/popup.h"
#include "ui/display.h"
#include "ui/forms/forms.h"
#include "ui/views/form.h"

#ifndef ctrl
#define ctrl(x)           ((x) & 0x1f)
#endif

void root_ctx_handler(state_t* state, int input, volatile int* running);

void list_ctx_handler(state_t* state, int input);

void form_ctx_handler(state_t* state, int input);

void popup_ctx_handler(state_t* state, int input);

#endif //IT350_PZ_APP_INPUT_H
