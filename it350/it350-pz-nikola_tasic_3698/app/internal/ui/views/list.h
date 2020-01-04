//
// Created by nik on 12/29/19.
//

#ifndef IT350_PZ_APP_LIST_H
#define IT350_PZ_APP_LIST_H

#include "ui/state.h"
#include "ui/format.h"

void change_list(state_t* state, int inc, MYSQL* conn);

void print_list_item(WINDOW* win, int row, int col, void* curr, enum list_type type, int k);

void print_list_header(WINDOW* win, enum list_type type);

void print_list_footer(WINDOW* win);

#endif //IT350_PZ_APP_LIST_H
