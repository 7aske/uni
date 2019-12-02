//
// Created by nik on 11/4/19.
//

#ifndef STRUCTS_ASTACK_H
#define STRUCTS_ASTACK_H

#pragma once

#include <stdlib.h>
#include <string.h>

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif

#define STACK_CAP 16

typedef struct astack {
	long size;
	int cap;
	void* top;
	void* data;
} astack_t;

static void _astack_resize(astack_t* astack) {
	long delta = astack->top - astack->data;
	astack->cap = astack->cap * 2 + 1;
	astack->data = reallocarray(astack->data, astack->cap, astack->size);
	astack->top = astack->data + delta;
}

static void astack_destroy(astack_t** astack) {
	assert(astack != NULL);
	free((*astack)->data);
	free(*astack);
	*astack = NULL;
}

static astack_t* astack_new(int size) {
	astack_t* newastack = (astack_t*) calloc(1, sizeof(astack_t));
	newastack->size = size;
	newastack->cap = STACK_CAP;
	newastack->data = calloc(newastack->cap, newastack->size);
	newastack->top = newastack->data;
}

static astack_t* astack_copy(astack_t* astack) {
	astack_t* newastack = (astack_t*) calloc(1, sizeof(astack_t));
	newastack->size = astack->size;
	newastack->cap = astack->cap;
	newastack->data = calloc(newastack->cap, newastack->size);
	memcpy(newastack->data, astack->data, newastack->cap);
	newastack->top = newastack->data + (astack->top - astack->data);
}

static int astack_size(astack_t* astack) {
	return (astack->top - astack->data) / astack->size;
}

static int astack_isempty(astack_t* astack) {
	return astack->data == astack->top;
}

static int astack_isfull(astack_t* astack) {
	return astack->top == astack->data + astack->cap * astack->size;
}

static void astack_push(astack_t* astack, void* data) {
	if (astack_isfull(astack)) {
		_astack_resize(astack);
	}
	memcpy(astack->top, data, astack->size);
	astack->top += astack->size;
}

static void* astack_pop(astack_t* astack) {
	if (astack->top != astack->data) {
		void* elem = calloc(1, sizeof(astack->size));
		memcpy(elem, astack->top - astack->size, astack->size);
		astack->top -= astack->size;
		return elem;
	} else {
		return NULL;
	}
}

static void* astack_peek(astack_t* astack) {
	if (astack->top != astack->data) {
		return astack->top - astack->size;
	} else {
		return NULL;
	}
}


#endif //STRUCTS_ASTACK_H
