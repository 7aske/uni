#ifndef APP_DBC_H
#define APP_DBC_H

#pragma once

#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#include <mysql/mysql.h>

#include "sql_cred.h"

#include "db/util.h"
#include "db/sql_result.h"
#include "db/orm/entity.h"

extern MYSQL*
db_init();

extern void __attribute__((used))
mysql_con_cleanup(MYSQL** conn);

extern void __attribute__((used))
mysql_res_cleanup(MYSQL_RES** res);

extern void __attribute__((used))
mysql_stmt_cleanup(MYSQL_STMT** stmt);

extern void __attribute__((noreturn))
mysql_panic(MYSQL* c);

extern void __attribute__((used))
mysql_bind_free(MYSQL_BIND* bind);

extern void __attribute__((used))
mysql_bind_cleanup(MYSQL_BIND** bind);

extern void __attribute__((used))
mysql_res_free_noref(SQL_RESULT** res);

extern void __attribute__((used))
mysql_res_free(SQL_RESULT** res);

#endif //IT350_PZ_APP_DBC_H
