#include "db/dbc.h"


void __attribute__((noreturn)) mysql_panic(MYSQL* c) {
	fprintf(stderr, "%s\n", mysql_error(c));
	if (c != NULL) {
		mysql_close(c);
	}
	exit(1);
}


MYSQL* db_init() {
	MYSQL* conn = mysql_init(NULL);
	if (conn == NULL) {
		mysql_panic(conn);
	}

	if (mysql_real_connect(conn, SQL_ADDR, SQL_USER, SQL_PASS, SQL_DB, SQL_PORT, NULL, 0) == NULL) {
		mysql_panic(conn);
	}
	return conn;
}

void mysql_con_cleanup(MYSQL** conn) {
	assert(conn != NULL);
	mysql_close(*conn);
	*conn = NULL;
}

void mysql_res_cleanup(MYSQL_RES** res) {
	assert(res != NULL);
	mysql_free_result(*res);
	*res = NULL;
}

void mysql_bind_free(MYSQL_BIND* bind) {
	if (bind->buffer != NULL) {
		free(bind->buffer);
	}
	if (bind->error != NULL) {
		free(bind->error);
	}
}

void mysql_stmt_cleanup(MYSQL_STMT** stmt) {
	assert(stmt != NULL);
	mysql_stmt_close(*stmt);
	*stmt = NULL;
}

void mysql_bind_cleanup(MYSQL_BIND** bind) {
	assert(bind != NULL);
	mysql_bind_free(*bind);
}

void mysql_res_free_noref(SQL_RESULT** res) {
	assert(res != NULL);
	SQL_RESULT_ROW* curr;
	SQL_RESULT_ROW* prev;
	if (*res != NULL) {
		curr = (*res)->results;
		while (curr != NULL) {
			prev = curr;
			curr = curr->next;
			free(prev->data);
			free(prev);
		}
		free(*res);
		*res = NULL;
	}
}
void mysql_res_free(SQL_RESULT** res) {
	assert(res != NULL);
	SQL_RESULT_ROW* curr;
	SQL_RESULT_ROW* prev;
	void (* free_func)(void**) = NULL;

	if (*res != NULL) {
		switch((*res)->type){
		
			case ADDRESS_E:
			free_func = (void (*)(void**)) address_free;
			break;
			case AUTHOR_E:
			free_func = (void (*)(void**)) author_free;
			break;
			case AUTHOR_BOOK_E:
			free_func = (void (*)(void**)) author_book_free;
			break;
			case BOOK_E:
			free_func = (void (*)(void**)) book_free;
			break;
			case BOOK_SPECIMEN_E:
			free_func = (void (*)(void**)) book_specimen_free;
			break;
			case EMPLOYEE_E:
			free_func = (void (*)(void**)) employee_free;
			break;
			case LIBRARY_E:
			free_func = (void (*)(void**)) library_free;
			break;
			case MUNICIPALITY_E:
			free_func = (void (*)(void**)) municipality_free;
			break;
			case PERSON_E:
			free_func = (void (*)(void**)) person_free;
			break;
			case READER_E:
			free_func = (void (*)(void**)) reader_free;
			break;
			case REGION_E:
			free_func = (void (*)(void**)) region_free;
			break;
			case RENT_E:
			free_func = (void (*)(void**)) rent_free;
			break;
		}
		curr = (*res)->results;
		while (curr != NULL) {
			assert(free_func != NULL);
			
			prev = curr;
			curr = curr->next;
			free_func(&prev->data);
			free(prev);
		}
		free(*res);
		*res = NULL;
	}

} 
	