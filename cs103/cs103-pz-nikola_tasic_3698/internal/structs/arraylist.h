//
// Created by nik on 10/28/19.
//

#ifndef STRUCTS_ARRAYLIST_H
#define STRUCTS_ARRAYLIST_H

#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif

#define ALIST_CAP 16


typedef struct alist_t {
	int len;
	int cap;
	size_t size;
	void* data;

	int (* cmpfunc)(const void*, const void*, unsigned long);
} alist_t;


static void alist_destroy(alist_t* list) {
	free(list->data);
	free(list);
}

static alist_t* alist_new(int size) {
	alist_t* list = malloc(sizeof(alist_t));
	list->len = 0;
	list->size = size;
	list->cap = ALIST_CAP;
	list->data = calloc(list->cap, list->size);
	list->cmpfunc = memcmp;
	return list;
}

static alist_t* alist_copy(alist_t* list) {
	alist_t* newlist = malloc(sizeof(alist_t));
	newlist->len = list->len;
	newlist->size = list->size;
	newlist->cap = list->cap;
	newlist->data = calloc(newlist->cap, newlist->size);
	newlist->cmpfunc = list->cmpfunc;
	memcpy(newlist->data, list->data, newlist->cap * newlist->size);
	return newlist;
}

static void alist_set_cmp(alist_t* list, int(* cmpfunc)(const void*, const void*, unsigned long)) {
	assert(cmpfunc != NULL);
	list->cmpfunc = cmpfunc;
}

static void _alist_resize(alist_t* list) {
	list->cap *= 2;
	list->data = reallocarray(list->data, list->cap, list->size);
}

static void alist_add(alist_t* list, void* data) {
	if (list->len == list->cap)
		_alist_resize(list);
	memcpy(list->data + list->len++ * list->size, data, list->size);
}

static void alist_add_at(alist_t* list, void* data, int index) {
	if (index <= list->len) {
		if (list->len + 1 == list->cap) {
			_alist_resize(list);
		}
		list->len++;
		memmove(list->data + (index + 1) * list->size, list->data + index * list->size,
				list->size * (list->len - index));
		memcpy(list->data + index * list->size, data, list->size);
	}
}


static void alist_clear(alist_t* list) {
	list->len = 0;
	free(list->data);
	list->data = calloc(list->cap, list->size);
}

static void* alist_get(alist_t* list, int index) {
	if (index < list->len)
		return list->data + index * list->size;
	else
		return NULL;
}

static int alist_idxof(alist_t* list, void* elem) {
	for (int i = 0; i < list->len; ++i) {
		if (list->cmpfunc(list->data + i * list->size, elem, list->size) == 0) {
			return i;
		}
	}
	return -1;
}

static int alist_idxof_ptr(alist_t* list, void* elem) {
	for (int i = 0; i < list->len; ++i) {
		if (list->data + i * list->size == elem) {
			return i;
		}
	}
	return -1;
}

static int alist_idxof_cmp(alist_t* list, void* elem, int( * cmpfunc)(const void*, const void*, unsigned long)) {
	for (int i = 0; i < list->len; ++i) {
		if (cmpfunc(list->data + i * list->size, elem, list->size) == 0) {
			return i;
		}
	}
	return -1;
}

static int alist_isempty(alist_t* list) {
	return list->len == 0;
}

static void alist_rm_idx(alist_t* list, int index) {
	if (index == list->len - 1) {
		list->len--;
	} else if (index < list->len) {
		memmove(list->data + index * list->size, list->data + (index + 1) * list->size,
				(list->len - index) * list->size);
		list->len--;
	}
}

static void alist_rm(alist_t* list, void* elem) {
	int index = alist_idxof(list, elem);
	if (index > -1) {
		alist_rm_idx(list, index);
	}
}

static void alist_rm_cmp(alist_t* list, void* elem, int(* cmpfunc)(const void*, const void*, unsigned long)) {
	assert(cmpfunc != NULL);
	int index = alist_idxof_cmp(list, elem, cmpfunc);
	if (index > -1) {
		alist_rm_idx(list, index);
	}
}

static void alist_set(alist_t* list, void* elem, int index) {
	if (index < list->len) {
		memcpy(list->data + index * list->size, elem, list->size);
	}
}

static int alist_size(alist_t* list) {
	return list->len;
}

static void alist_shrink(alist_t* list) {
	list->cap = list->len;
	list->data = reallocarray(list->data, list->len, list->size);
}

static void alist_print(alist_t* list, void(* _printfunc)(const void*)) {
	assert(_printfunc != NULL);
	for (int i = 0; i < list->len; ++i) {
		_printfunc(list->data + i * list->size);
	}
}

#endif //STRUCTS_ARRAYLIST_H
