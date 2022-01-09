/**
 * Generated by nik on 04/01/2020
 */
#ifndef __nik_LIBRARY_DB_ENTITY_STRUCT_BOOK_H
#define __nik_LIBRARY_DB_ENTITY_STRUCT_BOOK_H

#pragma once


#include "db/orm/entity.h"

struct book {
	uint id_book;
	char isbn[33];
	char name[256];
	struct tm publish_date;
};

typedef struct book BOOK;

uint book_insert(MYSQL* conn, BOOK* bookT);

SQL_RESULT* book_execute_find(MYSQL* conn, char const* query, MYSQL_BIND* params, uint param_count);

BOOK* book_find_by_id(MYSQL* conn, uint id);

int book_update(MYSQL* conn, BOOK* bookT);

int book_execute(MYSQL* conn, char const* query, MYSQL_BIND* params, uint param_count);

int book_delete(MYSQL* conn, BOOK* bookT);

SQL_RESULT* book_find_all(MYSQL* conn);

void book_free(BOOK** ptr);

#endif