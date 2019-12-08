//
// Created by nik on 12/2/19.
//

#ifndef STRUCTS_HTABLE_H
#define STRUCTS_HTABLE_H

#include <stdlib.h>
#include <string.h>

#ifndef HTABLE_NMEMB
#define HTABLE_NMEMB 4
#endif

#ifndef HTABLE_THOLD
#define HTABLE_THOLD 0.5f
#endif

struct htable {
	void** data;
	size_t size;
	int nmemb;

	unsigned long (* hashfunc)(long nmemb, void* data, size_t size);

	unsigned long (* probefunc)(int nmemb, unsigned long hash);
};

typedef struct htable htable_t;

static htable_t* htable_new(size_t size);

static void htable_add(htable_t* table, void* data);

static int htable_size(htable_t* table);

static void htable_destroy(htable_t** table);

static void _resize(htable_t* table);

static unsigned long _linear_probe(int nmemb, unsigned long hash);

static unsigned long _quadratic_probe(int nmemb, unsigned long hash);

static unsigned long _hashfunc(long nmemb, void* data, size_t size);


htable_t* htable_new(size_t size) {
	htable_t* newhtable = calloc(1, sizeof(htable_t));
	newhtable->nmemb = HTABLE_NMEMB;
	newhtable->size = size;
	newhtable->data = calloc(newhtable->nmemb, sizeof(void*));
	newhtable->hashfunc = _hashfunc;
	newhtable->probefunc = _linear_probe;
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
	if ((float) htable_size(table) / (float) table->nmemb > HTABLE_THOLD) {
		_resize(table);
	}
	unsigned long hash = _hashfunc(table->nmemb, data, table->size);
	void* dataptr = malloc(table->size);
	memcpy(dataptr, data, table->size);

	while (table->data[hash] != NULL)
		hash = table->probefunc(table->nmemb, hash);
	table->data[hash] = dataptr;
}


void _resize(htable_t* table) {
	int i = 0, n = table->nmemb;
	void** temp = table->data;
	table->nmemb *= 2;
	table->data = calloc(table->nmemb, sizeof(void*));

	for (i = 0; i < n; ++i) {
		if (temp[i] != NULL) {
			htable_add(table, temp[i]);
			free(temp[i]);
		}
	}
	free(temp);
}

unsigned long _hashfunc(long nmemb, void* data, size_t size) {
	unsigned long hash = 5381;
	unsigned long n = 0;
	unsigned char byte = 0u;
	while (n++ < size) {
		memcpy(&byte, data + n - 1, 1);
		hash = ((hash << 5u) + hash) + byte;
	}
	return hash % nmemb;
}

int htable_size(htable_t* table) {
	int i, count = 0;
	for (i = 0; i < table->nmemb; ++i) {
		count += table->data[i] != NULL;
	}
	return count;
}

unsigned long _linear_probe(int nmemb, unsigned long hash) {
	printf("%lu\n", hash);
	return hash == nmemb - 1 ? 0 : hash + 1;
}

unsigned long _quadratic_probe(int nmemb, unsigned long hash) {
	static unsigned long c = 0;
	c++;
	return (hash + (c * c)) % nmemb;
}


#endif //STRUCTS_HTABLE_H
