//
// Created by nik on 12/26/19.
//
#include <stdio.h>
#include <assert.h>
#include "db/dbc.h"

static char* SQL_DB_TEST = "library_test";

#define DELETE_ALL_QUERY(table) "delete from "table" where 1=1;"

#define TEST_REGION(func) { test_region_setup(), func(), test_region_teardown(); }
#define TEST_MUNICIPALITY(func) { test_municipality_setup(), func(), test_municipality_teardown(); }


int test_region_setup() {
	#define QUERY DELETE_ALL_QUERY("region")
	int status;
	MYSQL* conn = db_init();

	status = mysql_query(conn, QUERY);

	mysql_close(conn);
	return status;
	#undef QUERY
}

int test_region_teardown() {
	return test_region_setup();
}


void test_region_insert() {
	REGION* regionptr;
	REGION region = {0, "Ontario"};
	region_insert(&region);

	regionptr = region_find_by_id(region.id_region);

	assert(regionptr != NULL);
	assert(region.id_region == regionptr->id_region);
	assert(strncmp(region.name, regionptr->name, 8) == 0);
}

void test_region_delete() {
	REGION* regionptr;
	REGION region = {0, "Ontario"};
	region_insert(&region);

	region_delete(&region);

	regionptr = region_find_by_id(region.id_region);

	assert(regionptr == NULL);

}

void test_region_update() {
	char* newname = "Quebec";

	REGION* regionptr;
	REGION region = {0, "Ontario"};

	region_insert(&region);
	strncpy(region.name, newname, 8);
	region_update(&region);
	regionptr = region_find_by_id(region.id_region);

	assert(regionptr != NULL);
	assert(region.id_region == regionptr->id_region);
	assert(strncmp(region.name, regionptr->name, 8) == 0);
	assert(strncmp(regionptr->name, "Quebec", 8) == 0);

	region.id_region = 142;
	region_update(&region);
	regionptr = region_find_by_id(region.id_region);

	assert(regionptr == NULL);
}


int test_municipality_setup() {
	#define QUERY1 DELETE_ALL_QUERY("municipality")
	#define QUERY2 DELETE_ALL_QUERY("region")
	int status;

	MYSQL* conn = db_init();

	mysql_query(conn, QUERY1);
	status = mysql_query(conn, QUERY2);

	mysql_close(conn);
	return status;
	#undef QUERY1
	#undef QUERY2
}

int test_municipality_teardown() {
	return test_municipality_setup();
}

void test_municipality_insert() {
	REGION* regionptr;
	MUNICIPALITY* municipalityptr;
	REGION region = {0, "Ontario"};
	MUNICIPALITY municipality = {0, &region, "Waterloo",};
	municipality_insert(&municipality);

	regionptr = region_find_by_id(region.id_region);
	assert(regionptr != NULL);
	assert(regionptr->id_region == municipality.region->id_region);

	municipalityptr = municipality_find_by_id(municipality.id_municipality);

	assert(municipalityptr != NULL);
	assert(municipality.id_municipality == municipalityptr->id_municipality);
	assert(strncmp(municipality.name, municipalityptr->name, 8) == 0);
}

int main(void) {
	SQL_DB = SQL_DB_TEST;

	TEST_REGION(test_region_insert)
	TEST_REGION(test_region_delete)
	TEST_REGION(test_region_update)

	TEST_MUNICIPALITY(test_municipality_insert)

	ADDRESS address1 = {0, NULL, "Cambridge", "CB2 1TJ"};
	LIBRARY library1 = {0, NULL, "Wren Library"};
	BOOK book1 = {.name="Harry Potter and the Chamber of Secrets", .id_book=0, .isbn="1241tgr21", .publish_date={.tm_year=2002, .tm_mon=7, .tm_mday=23}};
	PERSON p1 = {.id_person=0,
			.first_name="John",
			.last_name="Doe",
			.jmbg="1234567890123"
	};
	EMPLOYEE e1 = {
			.person=&p1,
			.library=&library1,
			.position="Librarian"
	};
	return 0;
}