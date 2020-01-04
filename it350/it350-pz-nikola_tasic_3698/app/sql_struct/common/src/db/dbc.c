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