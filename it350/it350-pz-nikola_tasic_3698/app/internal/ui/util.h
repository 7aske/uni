//
// Created by nik on 12/28/19.
//

#ifndef IT350_PZ_APP_UTIL_H
#define IT350_PZ_APP_UTIL_H

#pragma once

#include <time.h>
#include <stdio.h>

#include "db/dbc.h"
#include "ui/state.h"
#include "structs/arraylist.h"

char* trimws(char* strp, int maxlen);

const char* list_type_str(list_type_e type);

void res_to_list(SQL_RESULT* res, alist_t* list);

const char* _fmt_date(struct tm* ts);

void _fmt_date_buf(char* buf, struct tm* ts);

void tmcpystr(struct tm* ts, char* str);

void type_free(void** elem, list_type_e list_type);

void type_free_ref(void* elem, list_type_e list_type);

void list_free_noref(alist_t** list, list_type_e type);

int type_get_id(void* elem, list_type_e list_type);

unsigned long type_get_size(list_type_e type);

int (* type_delete_action(enum list_type type))(MYSQL* conn, void* arg);

int (* type_update_action(enum list_type type))(MYSQL* conn, void* arg);

int (* type_insert_action(enum list_type type))(MYSQL* conn, void* arg);

#endif //IT350_PZ_APP_UTIL_H
