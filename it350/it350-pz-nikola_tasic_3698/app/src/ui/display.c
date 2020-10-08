//
// Created by nik on 12/29/19.
//
#include "ui/display.h"

void display(state_t* state) {
	switch (state->ctx) {
		case ROOT_CTX:
			display_root(state);
			break;
		case POPUP_CTX:
			display_popup(state);
			break;
		case LIST_CTX:
			display_list(state);
			break;
		case WINDOW_CTX:
		case FORM_CTX:
			break;
	}
	DBORDER(state->win)
	mvwprintw(state->win, 0, 3, "%s", state->title);
	if (state->child != NULL) {
		display(state->child);
	}
}

void display_root(state_t* state) {
	assert(state != NULL);
	assert(state->win != NULL);
	WINDOW* win = state->win;
	mvwprintw(win, 3, 3, "Press 'L' to open up a new list.");
	mvwprintw(win, 4, 3, "Press 'Q' to quit the program or exit the list view.");
	mvwprintw(win, 5, 3, "Use arrow keys to navigate and switch tabs.");
}

void display_popup(state_t* state) {
	assert(state->win != NULL);
	WINDOW* win;

	win = state->win;

	mvwprintw(win, 2, 3, "%s", state->ps.ques);
}

void display_form(state_t* state) {

}

void display_list(state_t* state) {
	if (state->ls.list == NULL || state->ctx != LIST_CTX) {
		return;
	}
	int wrows, wcols;

	getmaxyx(state->win, wrows, wcols);

	#define HEAD_LINES 2
	#define FOOT_LINES 1
	#define PER_SCR (wrows - 2 - HEAD_LINES - FOOT_LINES)
	wmove(state->win, 0, 0);

	init_pair(1, COLOR_RED, COLOR_WHITE);
	init_pair(2, COLOR_MAGENTA, COLOR_BLACK);
	init_pair(3, COLOR_WHITE, COLOR_GREEN);
	int col, row, k;
	int count = alist_size(state->ls.list);

	WINDOW* win = state->win;

	void* curr;
	int start = state->ls.sel_idx / PER_SCR * PER_SCR;
	int end = start + PER_SCR;

	for (k = start, row = HEAD_LINES + 1, col = 1; k < end; ++k, row++) {
		wmove(win, row, col);
		wclrtoeol(win);
	}

	for (k = start, row = HEAD_LINES + getbegy(win) + 1, col = getbegx(win) + 1; k < end && k < count; ++k, row++) {
		curr = alist_get(state->ls.list, k);
		wmove(win, row, col);
		wclrtoeol(win);

		if (state->ls.sel_idx == k) {
			wattron(win, COLOR_PAIR(1));
		}
		print_list_item(win, row, col, curr, state->ls.type, k);
		wattroff(win, COLOR_PAIR(1));
	}

	wmove(win, 1, 1);
	wclrtoeol(win);
	for (int j = 0; j < wcols - 1; ++j) {
		wattron(win, COLOR_PAIR(3));
		waddch(win, ' ');
		wattroff(win, COLOR_PAIR(3));
	}

	/*HEADER*/
	for (int i = 0; i < ETYPE_LEN; ++i) {
		if (i == state->ls.type) {
			wattron(win, COLOR_PAIR(1));
		} else {
			wattron(win, COLOR_PAIR(3));
		}
		mvwprintw(win, 1, 5 + i * (APP_COL / ETYPE_LEN), "%s", list_type_str(list_types[i]));
		wattroff(win, COLOR_PAIR(1));
		wattroff(win, COLOR_PAIR(3));
	}
	wmove(win, 2, 1);
	wclrtoeol(win);

	/*HEADER*/
	wattron(win, COLOR_PAIR(2));
	print_list_header(win, state->ls.type);
	wattroff(win, COLOR_PAIR(2));

	print_list_footer(win);
	wmove(win, 0, 0);
	#undef HEAD_LINES
}

