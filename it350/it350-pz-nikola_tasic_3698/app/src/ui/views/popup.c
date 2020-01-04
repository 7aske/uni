//
// Created by nik on 12/29/19.
//

#include "ui/views/popup.h"

void popup_set_question(state_t* state, const char* question) {
	assert(state->ctx == POPUP_CTX);
	assert(question != NULL);
	strncpy(state->ps.ques, question, POPUP_QSIZE);
}

int popup_action(state_t* state) {
	assert(state->ctx == POPUP_CTX);
	assert(state->ps.action != NULL);
	int retval = (int) state->ps.action(state->parent->conn, state->ps.arg);
	state->ps.action = NULL;
	state->ps.arg = NULL;
	return retval;
}