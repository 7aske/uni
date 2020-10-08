//
// Created by nik on 12/29/19.
//

#include "ui/views/list.h"

void change_list(state_t* state, int inc, MYSQL* conn) {
	if (state->ls.list != NULL) {
		list_free_noref(&state->ls.list, state->ls.type);
	}

	if (state->ls.type == list_types[0] && inc == -1) {
		state->ls.type = ETYPE_LEN - 1;
	} else {
		state->ls.type = (state->ls.type + inc) % ETYPE_LEN;
	}

	switch (state->ls.type) {
		case MUNICIPALITY_TYPE:
			state->ls.list = alist_new(sizeof(MUNICIPALITY));
			res_to_list(municipality_find_all(conn), state->ls.list);
			break;
		case ADDRESS_TYPE:
			state->ls.list = alist_new(sizeof(ADDRESS));
			res_to_list(address_find_all(conn), state->ls.list);
			break;
		case REGION_TYPE:
			state->ls.list = alist_new(sizeof(REGION));
			res_to_list(region_find_all(conn), state->ls.list);
			break;
		case LIBRARY_TYPE:
			state->ls.list = alist_new(sizeof(LIBRARY));
			res_to_list(library_find_all(conn), state->ls.list);
			break;
		case AUTHOR_TYPE:
			state->ls.list = alist_new(sizeof(AUTHOR));
			res_to_list(author_find_all(conn), state->ls.list);
			break;
		case AUTHOR_BOOK_TYPE:
			state->ls.list = alist_new(sizeof(AUTHOR_BOOK));
			res_to_list(author_book_find_all(conn), state->ls.list);
			break;
		case BOOK_TYPE:
			state->ls.list = alist_new(sizeof(BOOK));
			res_to_list(book_find_all(conn), state->ls.list);
			break;
		case BOOK_SPECIMEN_TYPE:
			state->ls.list = alist_new(sizeof(BOOK_SPECIMEN));
			res_to_list(book_specimen_find_all(conn), state->ls.list);
			break;
		case EMPLOYEE_TYPE:
			state->ls.list = alist_new(sizeof(EMPLOYEE));
			res_to_list(employee_find_all(conn), state->ls.list);
			break;
		case PERSON_TYPE:
			state->ls.list = alist_new(sizeof(PERSON));
			res_to_list(person_find_all(conn), state->ls.list);
			break;
		case READER_TYPE:
			state->ls.list = alist_new(sizeof(READER));
			res_to_list(reader_find_all(conn), state->ls.list);
			break;
		case RENT_TYPE:
			state->ls.list = alist_new(sizeof(RENT));
			res_to_list(rent_find_all(conn), state->ls.list);
			break;
	}
}

void print_list_item(WINDOW* win, int row, int col, void* curr, enum list_type type, int k) {
	switch (type) {
		case MUNICIPALITY_TYPE:
			mvwprintw(win, row, col, MUNICIPALITY_FMT, k + 1,
					  ((MUNICIPALITY*) curr)->id_municipality,
					  ((MUNICIPALITY*) curr)->name,
					  ((MUNICIPALITY*) curr)->region->name);
			break;
		case REGION_TYPE:
			mvwprintw(win, row, col, REGION_FMT, k + 1,
					  ((REGION*) curr)->id_region,
					  ((REGION*) curr)->name);
			break;
		case ADDRESS_TYPE:
			mvwprintw(win, row, col, ADDRESS_FMT, k + 1,
					  ((ADDRESS*) curr)->id_address,
					  ((ADDRESS*) curr)->street,
					  ((ADDRESS*) curr)->number,
					  ((ADDRESS*) curr)->municipality->name);
			break;
		case LIBRARY_TYPE:
			mvwprintw(win, row, col, LIBRARY_FMT, k + 1,
					  ((LIBRARY*) curr)->id_library,
					  ((LIBRARY*) curr)->name,
					  ((LIBRARY*) curr)->address->street,
					  ((LIBRARY*) curr)->address->number,
					  ((LIBRARY*) curr)->address->municipality->name);
			break;
		case AUTHOR_TYPE:
			mvwprintw(win, row, col, AUTHOR_FMT, k + 1,
					  ((AUTHOR*) curr)->id_author,
					  ((AUTHOR*) curr)->person->first_name,
					  ((AUTHOR*) curr)->person->last_name,
					  ((AUTHOR*) curr)->description);
			break;
		case AUTHOR_BOOK_TYPE:
			mvwprintw(win, row, col, AUTHOR_BOOK_FMT, k + 1,
					  ((AUTHOR_BOOK*) curr)->id_author_book,
					  ((AUTHOR_BOOK*) curr)->author->person->first_name,
					  ((AUTHOR_BOOK*) curr)->author->person->last_name,
					  ((AUTHOR_BOOK*) curr)->book->name,
					  ((AUTHOR_BOOK*) curr)->book->isbn);
			break;
		case BOOK_TYPE:
			mvwprintw(win, row, col, BOOK_FMT, k + 1,
					  ((BOOK*) curr)->id_book,
					  ((BOOK*) curr)->name,
					  ((BOOK*) curr)->isbn,
					  _fmt_date(&((BOOK*) curr)->publish_date));
			break;
		case BOOK_SPECIMEN_TYPE:
			mvwprintw(win, row, col, BOOK_SPECIMEN_FMT, k + 1,
					  ((BOOK_SPECIMEN*) curr)->id_book_specimen,
					  ((BOOK_SPECIMEN*) curr)->book->name,
					  ((BOOK_SPECIMEN*) curr)->book->isbn,
					  _fmt_date(&((BOOK_SPECIMEN*) curr)->book->publish_date),
					  ((BOOK_SPECIMEN*) curr)->library->name);
			break;
		case EMPLOYEE_TYPE:
			mvwprintw(win, row, col, EMPLOYEE_FMT, k + 1,
					  ((EMPLOYEE*) curr)->id_employee,
					  ((EMPLOYEE*) curr)->person->first_name,
					  ((EMPLOYEE*) curr)->person->last_name,
					  ((EMPLOYEE*) curr)->library->name,
					  ((EMPLOYEE*) curr)->position);
			break;
		case PERSON_TYPE:
			mvwprintw(win, row, col, PERSON_FMT, k + 1,
					  ((PERSON*) curr)->id_person,
					  ((PERSON*) curr)->first_name,
					  ((PERSON*) curr)->last_name,
					  ((PERSON*) curr)->jmbg);
			break;
		case READER_TYPE:
			mvwprintw(win, row, col, READER_FMT, k + 1,
					  ((READER*) curr)->id_reader,
					  ((READER*) curr)->username,
					// ((READER*) curr)->password);
					  "******************");
			break;
		case RENT_TYPE:
			mvwprintw(win, row, col, RENT_FMT, k + 1,
					  ((RENT*) curr)->id_rent,
					  ((RENT*) curr)->reader->username,
					  ((RENT*) curr)->book_specimen->book_serial,
					  ((RENT*) curr)->book_specimen->book->name,
					  _fmt_date(&((RENT*) curr)->due_date));
			break;
	}
}

void print_list_header(WINDOW* win, enum list_type type) {
	switch (type) {
		case ADDRESS_TYPE:
			mvwprintw(win, 2, 1, ADDRESS_FMTH, "N", "ID", "STREET", "NUMBER", "MUNICIPALITY");
			break;
		case AUTHOR_TYPE:
			mvwprintw(win, 2, 1, AUTHOR_FMTH, "N", "ID", "FIRST NAME", "LAST NAME", "DESCRIPTION");
			break;
		case AUTHOR_BOOK_TYPE:
			mvwprintw(win, 2, 1, AUTHOR_BOOK_FMTH, "N", "ID", "FIRST NAME", "LAST NAME", "BOOK NAME", "ISBN");
			break;
		case BOOK_TYPE:
			mvwprintw(win, 2, 1, BOOK_FMTH, "N", "ID", "BOOK NAME", "ISBN", "PUB DATE");
			break;
		case BOOK_SPECIMEN_TYPE:
			mvwprintw(win, 2, 1, BOOK_SPECIMEN_FMTH, "N", "ID", "NAME", "ISBN", "PUB DATE", "LIBRARY");
			break;
		case EMPLOYEE_TYPE:
			mvwprintw(win, 2, 1, EMPLOYEE_FMTH, "N", "ID", "FIRST NAME", "LAST NAME", "LIBRARY", "POSITION");
			break;
		case LIBRARY_TYPE:
			mvwprintw(win, 2, 1, LIBRARY_FMTH, "N", "ID", "LIBRARY NAME", "STREET", "NUMBER", "MUNICIPALITY");
			break;
		case MUNICIPALITY_TYPE:
			mvwprintw(win, 2, 1, MUNICIPALITY_FMTH, "N", "ID", "NAME", "REGION");
			break;
		case PERSON_TYPE:
			mvwprintw(win, 2, 1, PERSON_FMTH, "N", "ID", "FIRST NAME", "LAST NAME", "JMBG");
			break;
		case READER_TYPE:
			mvwprintw(win, 2, 1, READER_FMTH, "N", "ID", "USERNAME", "PASSWORD");
			break;
		case REGION_TYPE:
			mvwprintw(win, 2, 1, REGION_FMTH, "N", "ID", "NAME");
			break;
		case RENT_TYPE:
			mvwprintw(win, 2, 1, RENT_FMTH, "N", "ID", "USERNAME", "BOOKID", "BOOK NAME", "DUE DATE");
			break;
		default:
			mvprintw(2, 1, "ERROR");
	}
}

void print_list_footer(WINDOW* win) {
	int y, x;
	getmaxyx(win, y, x);
	init_pair(4, COLOR_BLACK, COLOR_BLUE);
	wmove(win, y - 2, 1);
	wattron(win, COLOR_PAIR(3));
	for (int j = 0; j < x - 1; ++j) {
		waddch(win, ' ');
	}
	mvwprintw(win, y - 2, 2, "CREATE");
	wattron(win, COLOR_PAIR(4));
	mvwprintw(win, y - 2, 8, "(c)");
	wattron(win, COLOR_PAIR(3));
	mvwprintw(win, y - 2, 12, "UPDATE");
	wattron(win, COLOR_PAIR(4));
	mvwprintw(win, y - 2, 18, "(e)");
	wattron(win, COLOR_PAIR(3));
	mvwprintw(win, y - 2, 22, "DELETE");
	wattron(win, COLOR_PAIR(4));
	mvwprintw(win, y - 2, 28, "(d)");
	wattroff(win, COLOR_PAIR(4));
	wattroff(win, COLOR_PAIR(3));
}
