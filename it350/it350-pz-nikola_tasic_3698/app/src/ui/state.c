
#include "ui/state.h"


void init_state(state_t* state) {
	static const char* title = "LIBRARY SYSTEM";
	strncpy(state->title, title, 16);
	state->win = NULL;
	state->child = NULL;
	state->parent = NULL;
	state->conn = db_init();
	state->ctx = ROOT_CTX;
}

void delete_state_ctx(state_t* state) {
	assert(state != NULL);
	delwin(state->win);
	state->win = NULL;
	window_count--;
	switch (state->ctx) {
		case LIST_CTX:
			if (state->ls.list != NULL)
				list_free_noref(&state->ls.list, state->ls.type);
			break;
		case FORM_CTX:
			if (state->fs.data != NULL) {
				if (state->fs.ftype == FORM_CREATE) {
					if (type_get_id(state->fs.data, state->fs.type) != 0) {
						type_free_ref(state->fs.data, state->fs.type);
					}
					free(state->fs.data);
				}
			}
			break;
		case ROOT_CTX:
		case WINDOW_CTX:
		case POPUP_CTX:
			break;
	}
	if (state->parent) {
		free(state);
		state->parent->child = NULL;
		wclear(state->parent->win);
	}
}

state_t* append_list_ctx(state_t* state) {
	#define PWIN state->parent->win
	int wrow, wcol;
	getmaxyx(PWIN, wrow, wcol);

	state->win = newwin(wrow, wcol, 0, 0);
	keypad(state->win, TRUE);

	state->ctx = LIST_CTX;
	state->ls.type = REGION_TYPE;
	state->ls.list = NULL;
	state->ls.line_pos = 1;
	state->ls.sel_idx = 0;

	return state;
	#undef PWIN
}

state_t* append_form_ctx(state_t* state) {
	state->ctx = FORM_CTX;
	state->fs.type = state->parent->ls.type;
	state->fs.ftype = FORM_CREATE;
	state->fs.data = NULL;
	return state;
}

state_t* append_popup_ctx(state_t* state) {
	#define PWIN state->parent->win

	state->win = newwin(APP_ROW / 2, APP_COL / 2, LINES / 2 - APP_ROW / 4, COLS / 2 - APP_COL / 4);
	keypad(state->win, TRUE);

	state->ctx = POPUP_CTX;
	state->ps.action = NULL;
	state->ps.arg = NULL;
	state->ps.ques[0] = '\0';

	return state;
	#undef PWIN
}

state_t* create_state_ctx(state_t* parent, ctx_e ctx) {
	assert(parent->win != NULL);
	#define BUF_LEN 16
	char buf[BUF_LEN];

	state_t* newstate = calloc(1, sizeof(state_t));

	snprintf(buf, BUF_LEN, "WINDOW %02d", ++window_count);
	strncpy(newstate->title, buf, BUF_LEN);


	newstate->ctx = WINDOW_CTX;
	newstate->child = NULL;
	newstate->parent = parent;
	newstate->conn = parent->conn;

	switch (ctx) {
		case ROOT_CTX:
		case WINDOW_CTX:
			return newstate;
		case LIST_CTX:
			return append_list_ctx(newstate);
		case FORM_CTX:
			return append_form_ctx(newstate);
		case POPUP_CTX:
			return append_popup_ctx(newstate);
	}


}