//
// Created by nik on 12/29/19.
//

#ifndef IT350_PZ_APP_DISPLAY_H
#define IT350_PZ_APP_DISPLAY_H

#pragma once

#define DBORDER(win) wborder(win, ACS_VLINE, ACS_VLINE, ACS_HLINE, ACS_HLINE, ACS_ULCORNER, ACS_URCORNER, ACS_LLCORNER, ACS_LRCORNER);

#include "ui/state.h"
#include "ui/format.h"
#include "ui/util.h"
#include "ui/views/list.h"


void display(state_t* state);

void display_list(state_t* state);

void display_root(state_t* state);

void display_popup(state_t* state);

void display_form(state_t* state);

#endif //IT350_PZ_APP_DISPLAY_H
