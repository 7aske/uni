#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEF_NMEMB 4
#define T_TRESHHOLD 0.5f

// bolja implementacija na structs folderu
struct htable {
	void* data;
	size_t size;
	int nmemb;
};

typedef struct htable htable_t;

htable_t* htable_new(size_t size);

void htable_add(htable_t* table, void* data);

int htable_size(htable_t* table);

unsigned long _hashfunc(long nmemb, void* data, size_t size);

void _linear_probe(htable_t* table);

void _quadratic_probe(htable_t* table);

int main(int argc, char** argv) {
	long* test = calloc(6, sizeof(long));
	test[0] = 1;
	test[1] = 2;
	test[2] = 3;
	test[3] = 4;
	test[4] = 5;
	test[5] = 6;
	htable_t* inttable = htable_new(sizeof(long));
	htable_add(inttable, &test[0]);
	htable_add(inttable, &test[1]);
	htable_add(inttable, &test[2]);
	htable_add(inttable, &test[3]);
	htable_add(inttable, &test[4]);
	htable_add(inttable, &test[5]);
	printf("table size: %d \n", htable_size(inttable)); // 6
	return 0;
}


void htable_add(htable_t* table, void* data) {
	if ((float) htable_size(table) / (float) table->nmemb > T_TRESHHOLD) {
		_linear_probe(table);
	}

	unsigned long hash = _hashfunc(table->nmemb, data, table->size);
	printf("size: %d hash: %lu\n", table->nmemb, hash);


	if (*(unsigned long*) (table->data + hash * table->size) == 0) {
		memcpy(table->data + (hash * table->size), data, table->size);
	} else {
		while (*(unsigned long*) (table->data + hash * table->size) != 0) {
			hash = hash == table->nmemb - 1 ? 0 : hash + 1;
		}
		memcpy(table->data + (hash * table->size), data, table->size);
	}
}


void _linear_probe(htable_t* table) {
	void* temp = table->data;
	int newnmemb = table->nmemb * 2 + 1;
	int oldnmemb = table->nmemb;
	int i = 0;

	printf("resizing\n");
	table->data = calloc(newnmemb, table->size * newnmemb);
	table->nmemb = newnmemb;
	for (i = 0; i < oldnmemb; ++i) {
		if (*(unsigned long*) (temp + (i * table->size)) != 0) {
			printf("re-adding %ld \n", *(long*) (temp + (i * table->size)));
			htable_add(table, temp + (i * table->size));
		}
	}
	free(temp);
}

void _quadratic_probe(htable_t* table) {
	// TODO: quadratic probe
}


htable_t* htable_new(size_t size) {
	htable_t* newhtable = calloc(1, sizeof(htable_t));
	newhtable->nmemb = DEF_NMEMB;
	newhtable->size = size;
	newhtable->data = calloc(newhtable->nmemb, size * newhtable->nmemb);
	return newhtable;
}

// djb2 algoritam (mala adaptacija)
unsigned long _hashfunc(long nmemb, void* data, size_t size) {
	unsigned long hash = 5381;
	int n = 0;
	unsigned char* byte = (unsigned char*) data;
	while (n++ < size)
		hash = ((hash << 5u) + hash) + *(byte + n);
	return hash % nmemb;
}

int htable_size(htable_t* table) {
	int i;
	int count = 0;
	for (i = 0; i < table->nmemb; ++i) {
		count += *(unsigned long*) (table->data + (i * table->size)) != 0;
	}
	return count;
}
