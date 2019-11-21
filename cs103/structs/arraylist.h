//
// Created by nik on 10/28/19.
//

#ifndef STRUCTS_ARRAYLIST_H
#define STRUCTS_ARRAYLIST_H

#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <assert.h>

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif


#define ALIST_CAP 16


typedef struct alist_t {
	uint len;
	uint cap;
	size_t size;
	void* data;

	int (* cmpfunc)(const void*, const void*, unsigned long);
} alist_t;


extern void alist_destroy(alist_t* list) {
	free(list->data);
	free(list);
}

extern alist_t* alist_new(uint size) {
	alist_t* list = malloc(sizeof(alist_t));
	list->len = 0;
	list->size = size;
	list->cap = ALIST_CAP;
	list->data = calloc(list->cap, list->size);
	list->cmpfunc = memcmp;
	return list;
}

extern alist_t* alist_copy(alist_t* list) {
	alist_t* newlist = malloc(sizeof(alist_t));
	newlist->len = list->len;
	newlist->size = list->size;
	newlist->cap = list->cap;
	newlist->data = calloc(newlist->cap, newlist->size);
	newlist->cmpfunc = list->cmpfunc;
	memcpy(newlist->data, list->data, newlist->cap * newlist->size);
	return newlist;
}

extern void alist_set_cmp(alist_t* list, int(* cmpfunc)(const void*, const void*, unsigned long)) {
	assert(cmpfunc != NULL);
	list->cmpfunc = cmpfunc;
}

static void _alist_resize(alist_t* list) {
	list->cap *= 2;
	list->data = reallocarray(list->data, list->cap, list->size);
}

extern void alist_add(alist_t* list, void* data) {
	if (list->len == list->cap)
		_alist_resize(list);
	memcpy(list->data + list->len++ * list->size, data, list->size);
}

extern void alist_add_at(alist_t* list, void* data, uint index) {
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


extern void alist_clear(alist_t* list) {
	list->len = 0;
	free(list->data);
	list->data = calloc(list->cap, list->size);
}

extern void* alist_get(alist_t* list, uint index) {
	if (index < list->len)
		return list->data + index * list->size;
	else
		return NULL;
}

extern uint32_t alist_idxof(alist_t* list, void* elem) {
	for (uint i = 0; i < list->len; ++i) {
		if (list->cmpfunc(list->data + i * list->size, elem, list->size) == 0) {
			return i;
		}
	}
	return -1;
}

extern int32_t alist_idxof_cmp(alist_t* list, void* elem, int( * cmpfunc)(const void*, const void*, unsigned long)) {
	for (uint i = 0; i < list->len; ++i) {
		if (cmpfunc(list->data + i * list->size, elem, list->size) == 0) {
			return i;
		}
	}
	return -1;
}

extern int32_t alist_isempty(alist_t* list) {
	return list->len == 0;
}

extern void alist_rm_idx(alist_t* list, uint index) {
	if (index == list->len - 1 && index >= 0) {
		list->len--;
	} else if (index < list->len) {
		memmove(list->data + index * list->size, list->data + (index + 1) * list->size,
				(list->len - index) * list->size);
		list->len--;
	}
}

extern void alist_rm(alist_t* list, void* elem) {
	int32_t index = alist_idxof(list, elem);
	if (index > -1) {
		alist_rm_idx(list, index);
	}
}

extern void alist_rm_cmp(alist_t* list, void* elem, int(* cmpfunc)(const void*, const void*, unsigned long)) {
	assert(cmpfunc != NULL);
	int32_t index = alist_idxof_cmp(list, elem, cmpfunc);
	if (index > -1) {
		alist_rm_idx(list, index);
	}
}

extern void alist_set(alist_t* list, void* elem, uint index) {
	if (index < list->len) {
		memcpy(list->data + index * list->size, elem, list->size);
	}
}

extern uint alist_size(alist_t* list) {
	return list->len;
}

extern void alist_shrink(alist_t* list) {
	list->cap = list->len;
	list->data = reallocarray(list->data, list->len, list->size);
}

extern void alist_print(alist_t* list, void(* _printfunc)(const void*)) {
	assert(_printfunc != NULL);
	for (uint i = 0; i < list->len; ++i) {
		_printfunc(list->data + i * list->size);
	}
}

#endif //STRUCTS_ARRAYLIST_H
