#include <ncurses.h>
#include <stdbool.h>
#include <signal.h>

#include "db/dbc.h"
#include "ui/state.h"
#include "ui/display.h"
#include "ui/input.h"

volatile static int running = true;

void abrtendwin(int signum) {
	if (signum == SIGABRT || signum == SIGSEGV) {
		endwin();
		abort();
	}
}

int handle_input(state_t* state);

int main() {
	system("env | grep -i TERM");

	atexit((void (*)(void)) endwin);
	signal(SIGABRT, abrtendwin);
	signal(SIGSEGV, abrtendwin);

	state_t state;
	init_state(&state);

	state.win = initscr();
	cbreak();
	noecho();
	start_color();
	keypad(stdscr, TRUE);
	wresize(state.win, LINES > APP_ROW ? LINES : APP_ROW, COLS > APP_COL ? COLS : APP_COL);

	while (running) {
		display(&state);
		handle_input(&state);

		if (state.win == NULL) {
			running = false;
		}

	}
	if (state.conn != NULL)
		mysql_close(state.conn);

	return 0;
}

int handle_input(state_t* state) {
	state_t* curr = state;
	int input;
	WINDOW* win;
	while (curr->child != NULL) {
		curr = curr->child;
	}
	win = curr->win;

	input = wgetch(win);

	switch (curr->ctx) {
		case ROOT_CTX:
			root_ctx_handler(curr, input, &running);
			break;
		case LIST_CTX:
			list_ctx_handler(curr, input);
			break;
		case POPUP_CTX:
			popup_ctx_handler(curr, input);
			break;
		case WINDOW_CTX:
		case FORM_CTX:
			break;

	}
	wmove(win, 0, 0);
}

