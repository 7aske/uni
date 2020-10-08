//
// Created by nik on 12/29/19.
//

#include "ui/input.h"

void root_ctx_handler(state_t* state, int input, volatile int* running) {
	switch (input) {
		case 'q':
			*running = false;
			break;
		case 'l':
			state->child = create_state_ctx(state, LIST_CTX);
			change_list(state->child, 0, state->conn);
			break;
		case 'p':
			state->child = create_state_ctx(state, POPUP_CTX);
			break;
	}
}

void list_ctx_handler(state_t* state, int input) {
	switch (input) {
		case 'q':
			delete_state_ctx(state);
			break;
		case KEY_UP:
			if (state->ls.line_pos - 1 > 0) {
				state->ls.line_pos--;
			}
			if (state->ls.sel_idx > 0) {
				state->ls.sel_idx--;
			}
			break;
		case KEY_DOWN:
			if (state->ls.line_pos + 1 < APP_ROW - 2) {
				state->ls.line_pos++;
			}
			if (state->ls.sel_idx < alist_size(state->ls.list) - 1) {
				state->ls.sel_idx++;
			}
			break;
		case KEY_LEFT:
			if (state->ls.type >= 0) {
				change_list(state, -1, state->conn);
				state->ls.line_pos = 0;
				state->ls.sel_idx = 0;
			}
			wclear(state->win);
			break;
		case KEY_RIGHT:
			if (state->ls.type < ETYPE_LEN) {
				change_list(state, 1, state->conn);
				state->ls.line_pos = 0;
				state->ls.sel_idx = 0;
			}
			wclear(state->win);
			break;
		case 'd':
			state->child = create_state_ctx(state, POPUP_CTX);
			popup_set_question(state->child, "Are you sure you want to delete?");
			state->child->ps.action = type_delete_action(state->ls.type);
			state->child->ps.arg = alist_get(state->ls.list, state->ls.sel_idx);
			break;
		case 'l':
			state->child = create_state_ctx(state, LIST_CTX);
			change_list(state->child, 0, state->conn);
			break;
		case 'c':
			state->child = create_state_ctx(state, FORM_CTX);
			form_set_type(state->child, FORM_CREATE);
			delete_state_ctx(state->child);
			break;
		case 'e':
			state->child = create_state_ctx(state, FORM_CTX);
			form_set_type(state->child, FORM_UPDATE);
			delete_state_ctx(state->child);
			break;
		default:
			break;
	}
}

void form_ctx_handler(state_t* state, int input) {

}

void popup_ctx_handler(state_t* state, int input) {
	switch (input) {
		case 'q':
			delete_state_ctx(state);
			break;
		case 'y':
			assert(state->parent != NULL);
			state->parent->ptype = POPUP_PLOAD;
			popup_action(state);
			delete_state_ctx(state);
			break;
		case 'n':
			assert(state->parent != NULL);
			state->parent->ptype = POPUP_PLOAD;
			delete_state_ctx(state);
			break;
	}
}
