#define _XOPEN_SOURCE 700

#include <db/util.h>

void mysql_timecpy(MYSQL_TIME* mysqlTime, struct tm* ts) {
	mysqlTime->day = ts->tm_mday;
	mysqlTime->month = ts->tm_mon;
	mysqlTime->year = ts->tm_year;
	mysqlTime->hour = ts->tm_hour;
	mysqlTime->minute = ts->tm_min;
	mysqlTime->second = ts->tm_sec;
}

void mysql_timecpystr(struct tm* ts, char* sqlstr) {
	// strptime(sqlstr, "%Y-%m-%d", ts);
	sscanf(sqlstr, "%d-%d-%d", &ts->tm_year, &ts->tm_mon, &ts->tm_mday);
}

#undef _XOPEN_SOURCE