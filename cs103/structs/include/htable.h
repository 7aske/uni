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


struct htable_node {
	void* key;
	void* data;
};

struct htable {
	struct htable_node** data;
	size_t dsize;
	size_t ksize;
	int nmemb;

	unsigned long (* hashfunc)(long nmemb, void* data, size_t size);

	unsigned long (* probefunc)(int nmemb, unsigned long hash);
};

typedef struct htable htable_t;
typedef struct htable_node htable_node_t;

static htable_t* htable_new(size_t dsize, size_t ksize);

static void htable_add(htable_t* table, void* key, void* data);

static void* htable_get(htable_t* table, void* key);

static void htable_remove(htable_t* table, void* key);

static int htable_size(htable_t* table);

static void htable_destroy(htable_t** table);

static void _resize(htable_t* table);

static void _rehash(htable_t* table, int old_nmemb);

static unsigned long _linear_probe(int nmemb, unsigned long hash);

static unsigned long _quadratic_probe(int nmemb, unsigned long hash);

static unsigned long _hashfunc(long nmemb, void* data, size_t size);


htable_t* htable_new(size_t ksize, size_t dsize) {
	htable_t* newhtable = calloc(1, sizeof(htable_t));
	newhtable->nmemb = HTABLE_NMEMB;
	newhtable->ksize = ksize;
	newhtable->dsize = dsize;
	newhtable->data = calloc(newhtable->nmemb, sizeof(struct htable_node*));
	newhtable->hashfunc = _hashfunc;
	newhtable->probefunc = _linear_probe;
	return newhtable;
}

void htable_destroy(htable_t** table) {
	assert(table != NULL);
	int i;
	htable_node_t* temp;
	for (i = 0; i < (*table)->nmemb; ++i) {
		temp = (*table)->data[i];
		if (temp != NULL) {
			free(temp->data);
			free(temp->key);
			free(temp);
		}
	}
	free((*table)->data);
	free(*table);
	*table = NULL;
}

void htable_add(htable_t* table, void* key, void* data) {
	if ((float) htable_size(table) / (float) table->nmemb > HTABLE_THOLD) {
		_resize(table);
	}
	unsigned long hash = _hashfunc(table->nmemb, key, table->ksize);
	htable_node_t* node = calloc(1, sizeof(struct htable_node));
	node->data = malloc(table->dsize);
	node->key = malloc(table->ksize);
	memcpy(node->data, data, table->dsize);
	memcpy(node->key, key, table->ksize);

	while (table->data[hash] != NULL)
		hash = table->probefunc(table->nmemb, hash);
	table->data[hash] = node;
}


void* htable_get(htable_t* table, void* key) {
	unsigned long hash = _hashfunc(table->nmemb, key, table->ksize);
	htable_node_t* ptr = table->data[hash];
	if (ptr != NULL) {
		return ptr->data;
	} else {
		return NULL;
	}
}


void htable_remove(htable_t* table, void* key) {
	unsigned long hash = _hashfunc(table->nmemb, key, table->ksize);
	htable_node_t* ptr = table->data[hash];
	if (ptr != NULL) {
		free(ptr->data);
		free(ptr->key);
		free(ptr);
		table->data[hash] = NULL;
	}
}

void _rehash(htable_t* table, int old_nmemb) {
	int i;
	htable_node_t** temp = table->data;
	table->data = calloc(table->nmemb, sizeof(htable_node_t*));

	for (i = 0; i < old_nmemb; ++i) {
		if (temp[i] != NULL) {
			htable_add(table, temp[i]->key, temp[i]->data);
			free(temp[i]->key);
			free(temp[i]->data);
			free(temp[i]);
		}
	}
	free(temp);
}

void _resize(htable_t* table) {
	int n = table->nmemb;
	table->nmemb *= 2;
	_rehash(table, n);
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
	return hash == nmemb - 1 ? 0 : hash + 1;
}

unsigned long _quadratic_probe(int nmemb, unsigned long hash) {
	static unsigned long c = 0;
	c++;
	return (hash + (c * c)) % nmemb;
}


#endif //STRUCTS_HTABLE_H
