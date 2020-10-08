//
// Created by nik on 12/28/19.
//

#include "ui/util.h"

char* trimws(char* strp, int maxlen) {
	char* end;

	// trim leading space
	while (isspace(*strp))
		strp++;

	if (*strp == 0) // all spaces?
		return strp;

	// trim trailing space
	end = strp + strnlen(strp, maxlen) - 1;

	while (end > strp && isspace(*end))
		end--;

	// write new null terminator
	*(end + 1) = '\0';

	return strp;
}


void list_free_noref(alist_t** list, list_type_e type) {
	assert(list != NULL);
	if (*list != NULL) {
		void* elem;
		for (int i = 0; i < alist_size(*list); ++i) {
			elem = alist_get(*list, i);
			type_free_ref(elem, type);
		}
		alist_destroy(list);
	}
}

const char* list_type_str(list_type_e type) {
	switch (type) {
		case ADDRESS_TYPE:
			return "ADDR";
		case AUTHOR_TYPE:
			return "ATHR";
		case AUTHOR_BOOK_TYPE:
			return "ATBK";
		case BOOK_TYPE:
			return "BOOK";
		case BOOK_SPECIMEN_TYPE:
			return "BKSP";
		case EMPLOYEE_TYPE:
			return "EMPL";
		case LIBRARY_TYPE:
			return "LIBR";
		case MUNICIPALITY_TYPE:
			return "MUNI";
		case PERSON_TYPE:
			return "PERS";
		case READER_TYPE:
			return "READ";
		case REGION_TYPE:
			return "REGI";
		case RENT_TYPE:
			return "RENT";
	}
}


const char* _fmt_date(struct tm* ts) {
	#define DATE_FMT "%02d-%02d-%04d"
	static char fmt[12];
	snprintf(fmt, 12, DATE_FMT, ts->tm_mday, ts->tm_mon, ts->tm_year);
	return fmt;
	#undef DATE_FMT
}

void _fmt_date_buf(char* buf, struct tm* ts) {
	#define DATE_FMT "%02d-%02d-%04d"
	snprintf(buf, 12, DATE_FMT, ts->tm_mday, ts->tm_mon, ts->tm_year);
	#undef DATE_FMT
}

void tmcpystr(struct tm* ts, char* str) {
	#define DATE_FMT "%d-%d-%d"
	sscanf(str, DATE_FMT, &ts->tm_mday, &ts->tm_mon, &ts->tm_year);
	#undef DATE_FMT
}

void res_to_list(SQL_RESULT* res, alist_t* list) {
	assert(res != NULL);
	assert(list != NULL);
	struct sql_result_row* curr;
	curr = res->results;
	while (curr != NULL) {
		assert(curr->data != NULL);
		alist_add(list, curr->data);
		curr = curr->next;
	}
	// clean memory without freeing references within structures
	// they will be used by newly copied elements in the list
	mysql_res_free_noref(&res);
}

unsigned long type_get_size(list_type_e type) {
	switch (type) {
		case REGION_TYPE:
			return sizeof(REGION);
		case MUNICIPALITY_TYPE:
			return sizeof(MUNICIPALITY);
		case ADDRESS_TYPE:
			return sizeof(ADDRESS);
		case LIBRARY_TYPE:
			return sizeof(LIBRARY);
		case EMPLOYEE_TYPE:
			return sizeof(EMPLOYEE);
		case PERSON_TYPE:
			return sizeof(PERSON);
		case AUTHOR_TYPE:
			return sizeof(AUTHOR);
		case AUTHOR_BOOK_TYPE:
			return sizeof(AUTHOR_BOOK);
		case BOOK_TYPE:
			return sizeof(BOOK);
		case BOOK_SPECIMEN_TYPE:
			return sizeof(BOOK_SPECIMEN);
		case READER_TYPE:
			return sizeof(READER);
		case RENT_TYPE:
			return sizeof(RENT);
		default:
			perror("Unknown type to get size.");
			abort();
	}
}

int type_get_id(void* elem, list_type_e list_type) {
	switch (list_type) {
		case REGION_TYPE:
			return ((REGION*) elem)->id_region;
		case MUNICIPALITY_TYPE:
			return ((MUNICIPALITY*) elem)->id_municipality;
		case ADDRESS_TYPE:
			return ((ADDRESS*) elem)->id_address;
		case LIBRARY_TYPE:
			return ((LIBRARY*) elem)->id_library;
		case EMPLOYEE_TYPE:
			return ((EMPLOYEE*) elem)->id_employee;
		case PERSON_TYPE:
			return ((PERSON*) elem)->id_person;
		case AUTHOR_TYPE:
			return ((AUTHOR*) elem)->id_author;
		case AUTHOR_BOOK_TYPE:
			return ((AUTHOR_BOOK*) elem)->id_author_book;
		case BOOK_TYPE:
			return ((BOOK*) elem)->id_book;
		case BOOK_SPECIMEN_TYPE:
			return ((BOOK_SPECIMEN*) elem)->id_book_specimen;
		case READER_TYPE:
			return ((READER*) elem)->id_reader;
		case RENT_TYPE:
			return ((RENT*) elem)->id_rent;
	}
}

void type_free(void** elem, list_type_e list_type) {
	switch (list_type) {
		case MUNICIPALITY_TYPE:
			municipality_free((MUNICIPALITY**) elem);
			break;
		case ADDRESS_TYPE:
			address_free((ADDRESS**) elem);
			break;
		case REGION_TYPE:
			region_free((REGION**) elem);
			break;
		case LIBRARY_TYPE:
			library_free((LIBRARY**) elem);
			break;
		case AUTHOR_TYPE:
			author_free((AUTHOR**) elem);
			break;
		case AUTHOR_BOOK_TYPE:
			author_book_free((AUTHOR_BOOK**) elem);
			break;
		case BOOK_TYPE:
			book_free((BOOK**) elem);
			break;
		case BOOK_SPECIMEN_TYPE:
			book_specimen_free((BOOK_SPECIMEN**) elem);
			break;
		case EMPLOYEE_TYPE:
			employee_free((EMPLOYEE**) elem);
			break;
		case PERSON_TYPE:
			person_free((PERSON**) elem);
			break;
		case READER_TYPE:
			reader_free((READER**) elem);
			break;
		case RENT_TYPE:
			rent_free((RENT**) elem);
			break;
	}
}

void type_free_ref(void* elem, list_type_e list_type) {
	switch (list_type) {
		case MUNICIPALITY_TYPE:
			region_free(&((MUNICIPALITY*) elem)->region);
			break;
		case ADDRESS_TYPE:
			municipality_free(&((ADDRESS*) elem)->municipality);
			break;
		case LIBRARY_TYPE:
			address_free(&((LIBRARY*) elem)->address);
			break;
		case AUTHOR_TYPE:
			person_free(&((AUTHOR*) elem)->person);
			break;
		case AUTHOR_BOOK_TYPE:
			book_free(&((AUTHOR_BOOK*) elem)->book);
			author_free(&((AUTHOR_BOOK*) elem)->author);
			break;
		case BOOK_SPECIMEN_TYPE:
			book_free(&((BOOK_SPECIMEN*) elem)->book);
			library_free(&((BOOK_SPECIMEN*) elem)->library);
			break;
		case EMPLOYEE_TYPE:
			library_free(&((EMPLOYEE*) elem)->library);
			person_free(&((EMPLOYEE*) elem)->person);
			break;
		case RENT_TYPE:
			book_specimen_free(&((RENT*) elem)->book_specimen);
			reader_free(&((RENT*) elem)->reader);
			break;
		default:
			break;
	}
}

int (* type_delete_action(enum list_type type))(MYSQL* conn, void* arg) {
	switch (type) {
		case REGION_TYPE:
			return (int (*)(MYSQL* conn, void*)) region_delete;
		case MUNICIPALITY_TYPE:
			return (int (*)(MYSQL* conn, void*)) municipality_delete;
		case ADDRESS_TYPE:
			return (int (*)(MYSQL* conn, void*)) address_delete;
		case LIBRARY_TYPE:
			return (int (*)(MYSQL* conn, void*)) library_delete;
		case EMPLOYEE_TYPE:
			return (int (*)(MYSQL* conn, void*)) employee_delete;
		case PERSON_TYPE:
			return (int (*)(MYSQL* conn, void*)) person_delete;
		case AUTHOR_TYPE:
			return (int (*)(MYSQL* conn, void*)) author_delete;
		case AUTHOR_BOOK_TYPE:
			return (int (*)(MYSQL* conn, void*)) author_book_delete;
		case BOOK_TYPE:
			return (int (*)(MYSQL* conn, void*)) book_delete;
		case BOOK_SPECIMEN_TYPE:
			return (int (*)(MYSQL* conn, void*)) book_specimen_delete;
		case READER_TYPE:
			return (int (*)(MYSQL* conn, void*)) reader_delete;
		case RENT_TYPE:
			return (int (*)(MYSQL* conn, void*)) rent_delete;
		default:
			return NULL;
	}
}

int (* type_update_action(enum list_type type))(MYSQL* conn, void* arg) {
	switch (type) {
		case REGION_TYPE:
			return (int (*)(MYSQL* conn, void*)) region_update;
		case MUNICIPALITY_TYPE:
			return (int (*)(MYSQL* conn, void*)) municipality_update;
		case ADDRESS_TYPE:
			return (int (*)(MYSQL* conn, void*)) address_update;
		case LIBRARY_TYPE:
			return (int (*)(MYSQL* conn, void*)) library_update;
		case EMPLOYEE_TYPE:
			return (int (*)(MYSQL* conn, void*)) employee_update;
		case PERSON_TYPE:
			return (int (*)(MYSQL* conn, void*)) person_update;
		case AUTHOR_TYPE:
			return (int (*)(MYSQL* conn, void*)) author_update;
		case AUTHOR_BOOK_TYPE:
			return (int (*)(MYSQL* conn, void*)) author_book_update;
		case BOOK_TYPE:
			return (int (*)(MYSQL* conn, void*)) book_update;
		case BOOK_SPECIMEN_TYPE:
			return (int (*)(MYSQL* conn, void*)) book_specimen_update;
		case READER_TYPE:
			return (int (*)(MYSQL* conn, void*)) reader_update;
		case RENT_TYPE:
			return (int (*)(MYSQL* conn, void*)) rent_update;
		default:
			return NULL;
	}
}

int (* type_insert_action(enum list_type type))(MYSQL* conn, void* arg) {
	switch (type) {
		case REGION_TYPE:
			return (int (*)(MYSQL* conn, void*)) region_insert;
		case MUNICIPALITY_TYPE:
			return (int (*)(MYSQL* conn, void*)) municipality_insert;
		case ADDRESS_TYPE:
			return (int (*)(MYSQL* conn, void*)) address_insert;
		case LIBRARY_TYPE:
			return (int (*)(MYSQL* conn, void*)) library_insert;
		case EMPLOYEE_TYPE:
			return (int (*)(MYSQL* conn, void*)) employee_insert;
		case PERSON_TYPE:
			return (int (*)(MYSQL* conn, void*)) person_insert;
		case AUTHOR_TYPE:
			return (int (*)(MYSQL* conn, void*)) author_insert;
		case AUTHOR_BOOK_TYPE:
			return (int (*)(MYSQL* conn, void*)) author_book_insert;
		case BOOK_TYPE:
			return (int (*)(MYSQL* conn, void*)) book_insert;
		case BOOK_SPECIMEN_TYPE:
			return (int (*)(MYSQL* conn, void*)) book_specimen_insert;
		case READER_TYPE:
			return (int (*)(MYSQL* conn, void*)) reader_insert;
		case RENT_TYPE:
			return (int (*)(MYSQL* conn, void*)) rent_insert;
		default:
			return NULL;
	}
}