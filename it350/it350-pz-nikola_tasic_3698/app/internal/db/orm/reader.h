/**
 * Generated by nik on 04/01/2020
 */
#ifndef __nik_LIBRARY_DB_ENTITY_STRUCT_READER_H
#define __nik_LIBRARY_DB_ENTITY_STRUCT_READER_H

#pragma once


#include "db/orm/entity.h"

struct reader {
	uint id_reader;
	char username[256];
	char password[256];
};

typedef struct reader READER;

uint reader_insert(MYSQL* conn, READER* readerT);

SQL_RESULT* reader_execute_find(MYSQL* conn, char const* query, MYSQL_BIND* params, uint param_count);

READER* reader_find_by_id(MYSQL* conn, uint id);

int reader_update(MYSQL* conn, READER* readerT);

int reader_execute(MYSQL* conn, char const* query, MYSQL_BIND* params, uint param_count);

int reader_delete(MYSQL* conn, READER* readerT);

SQL_RESULT* reader_find_all(MYSQL* conn);

void reader_free(READER** ptr);

#endif
