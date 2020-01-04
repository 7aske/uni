
#include "ui/views/form.h"

void form_add_data(state_t* state, void* data, size_t size) {
	assert(state->ctx == FORM_CTX);
	assert(state->fs.ftype == FORM_UPDATE);
	memcpy(state->fs.data, data, size);
}

state_t* form_set_type(state_t* state, form_type_e ftype) {
	assert(state->parent->ctx == LIST_CTX);
	state->fs.ftype = ftype;
	unsigned long size = type_get_size(state->fs.type);
	if (ftype == FORM_UPDATE) {
		// form_add_data(state, alist_get(state->parent->ls.list, state->parent->ls.sel_idx), size);
		state->fs.data = alist_get(state->parent->ls.list, state->parent->ls.sel_idx);
	} else {
		state->fs.data = calloc(1, size);
	}
	switch (state->fs.type) {
		case REGION_TYPE:
			region_form_construct(state);
			break;
		case MUNICIPALITY_TYPE:
			municipality_form_construct(state);
			break;
		case ADDRESS_TYPE:
			address_form_construct(state);
			break;
		case LIBRARY_TYPE:
			library_form_construct(state);
			break;
		case EMPLOYEE_TYPE:
			employee_form_construct(state);
			break;
		case PERSON_TYPE:
			person_form_construct(state);
			break;
		case AUTHOR_TYPE:
			author_form_construct(state);
			break;
		case AUTHOR_BOOK_TYPE:
			author_book_form_construct(state);
			break;
		case BOOK_TYPE:
			book_form_construct(state);
			break;
		case BOOK_SPECIMEN_TYPE:
			book_specimen_form_construct(state);
			break;
		case READER_TYPE:
			reader_form_construct(state);
			break;
		case RENT_TYPE:
			rent_form_construct(state);
			break;
	}
	return state;
}

void print_form_footer(WINDOW* win) {
	int y, x;
	getmaxyx(win, y, x);
	wmove(win, y - 2, 1);
	wattron(win, COLOR_PAIR(3));
	for (int j = 0; j < x - 2; ++j) {
		waddch(win, ' ');
	}
	mvwprintw(win, y - 2, 2, "^X - Commit | ^D Discard");
	wattroff(win, COLOR_PAIR(3));
}

