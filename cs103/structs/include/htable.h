//
// Created by nik on 12/2/19.
//

#ifndef STRUCTS_HTABLE_H
#define STRUCTS_HTABLE_H

#include <stdlib.h>
#include <string.h>

#define DEF_NMEMB 4
#define T_TRESHHOLD 0.5f

struct htable {
	void** data;
	size_t size;
	int nmemb;
};

typedef struct htable htable_t;

static htable_t* htable_new(size_t size);

static void htable_add(htable_t* table, void* data);

static int htable_size(htable_t* table);

static void htable_destroy(htable_t** table);

static void _linear_probe(htable_t* table);

static void _quadratic_probe(htable_t* table);

static unsigned long _hashfunc(long nmemb, void* data, size_t size);


htable_t* htable_new(size_t size) {
	htable_t* newhtable = calloc(1, sizeof(htable_t));
	newhtable->nmemb = DEF_NMEMB;
	newhtable->size = size;
	newhtable->data = calloc(newhtable->nmemb, sizeof(void*));
	return newhtable;
}

void htable_destroy(htable_t** table) {
	assert(table != NULL);
	int i;
	void* temp;
	for (i = 0; i < (*table)->nmemb; ++i) {
		temp = (*table)->data[i];
		if (temp != NULL) {
			free(temp);
		}
	}
	free((*table)->data);
	free(*table);
	*table = NULL;
}

void htable_add(htable_t* table, void* data) {
	if ((float) htable_size(table) / (float) table->nmemb > T_TRESHHOLD) {
		_linear_probe(table);
	}
	unsigned long hash = _hashfunc(table->nmemb, data, table->size);
	void* dataptr = calloc(1, table->size);
	memcpy(dataptr, data, table->size);

	while (table->data[hash] != NULL)
		hash = hash == table->nmemb - 1 ? 0 : hash + 1;
	table->data[hash] = dataptr;
}

void _linear_probe(htable_t* table) {
	int i = 0, n = table->nmemb;
	void** temp = table->data;
	table->nmemb *= 2;
	table->data = calloc(table->nmemb, sizeof(void*));

	for (i = 0; i < n; ++i) {
		if (temp[i] != NULL) {
			htable_add(table, temp[i]);
		}
	}
	free(temp);
}

unsigned long _hashfunc(long nmemb, void* data, size_t size) {
	unsigned long hash = 5381;
	int n = 0;
	unsigned char* byte = (unsigned char*) data;
	while (n++ < size)
		hash = ((hash << 5u) + hash) + *(byte + n);
	return hash % nmemb;
}


void _quadratic_probe(htable_t* table) {
	// TODO: quadratic probe
}


int htable_size(htable_t* table) {
	int i, count = 0;
	for (i = 0; i < table->nmemb; ++i) {
		count += table->data[i] != NULL;
	}
	return count;
}

#endif //STRUCTS_HTABLE_H
