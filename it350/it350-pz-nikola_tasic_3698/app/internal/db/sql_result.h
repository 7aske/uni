
#ifndef __nik_LIBRARY_APP_SQL_RESULT_H
#define __nik_LIBRARY_APP_SQL_RESULT_H

typedef unsigned int uint;

enum entity_type {
	ADDRESS_E,
	AUTHOR_E,
	AUTHOR_BOOK_E,
	BOOK_E,
	BOOK_SPECIMEN_E,
	EMPLOYEE_E,
	LIBRARY_E,
	MUNICIPALITY_E,
	PERSON_E,
	READER_E,
	REGION_E,
	RENT_E,
};

struct sql_result_row {
	void* data;
	struct sql_result_row* next;
};

struct sql_result {
	struct sql_result_row* results;
	uint count;
	enum entity_type type;
};

typedef enum entity_type ENTITY_TYPE;
typedef struct sql_result SQL_RESULT;
typedef struct sql_result_row SQL_RESULT_ROW;

#endif //nik_APP_SQL_RESULT_H
