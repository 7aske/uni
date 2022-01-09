/**
 * Generated by nik on 04/01/2020
 */
#ifndef __nik_LIBRARY_DB_ENTITY_STRUCT_PERSON_H
#define __nik_LIBRARY_DB_ENTITY_STRUCT_PERSON_H

#pragma once


#include "db/orm/entity.h"

struct person {
	uint id_person;
	char first_name[256];
	char last_name[256];
	char jmbg[14];
};

typedef struct person PERSON;

uint person_insert(MYSQL* conn, PERSON* personT);

SQL_RESULT* person_execute_find(MYSQL* conn, char const* query, MYSQL_BIND* params, uint param_count);

PERSON* person_find_by_id(MYSQL* conn, uint id);

int person_update(MYSQL* conn, PERSON* personT);

int person_execute(MYSQL* conn, char const* query, MYSQL_BIND* params, uint param_count);

int person_delete(MYSQL* conn, PERSON* personT);

SQL_RESULT* person_find_all(MYSQL* conn);

void person_free(PERSON** ptr);

#endif