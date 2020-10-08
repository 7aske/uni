//
// Created by nik on 11/4/19.
//

#ifndef STRUCTS_STACK_H
#define STRUCTS_STACK_H

#pragma once

#include <stdint.h>
#include <stdlib.h>
#include <string.h>

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif

#define STACK_CAP 16

typedef struct astack {
	int size;
	int cap;
	void* top;
	void* data;
} astack_t;

static void _stack_resize(astack_t* stack) {
	int32_t delta = stack->top - stack->data;
	stack->cap = stack->cap * 2 + 1;
	stack->data = reallocarray(stack->data, stack->cap, stack->size);
	stack->top = stack->data + delta;
}

static void stack_destroy(astack_t* stack) {
	free(stack->data);
	free(stack);
}

static astack_t* stack_new(int size) {
	astack_t* newstack = (astack_t*) calloc(1, sizeof(astack_t));
	newstack->size = size;
	newstack->cap = STACK_CAP;
	newstack->data = calloc(newstack->cap, newstack->size);
	newstack->top = newstack->data;
}

static astack_t* stack_copy(astack_t* stack) {
	astack_t* newstack = (astack_t*) calloc(1, sizeof(astack_t));
	newstack->size = stack->size;
	newstack->cap = stack->cap;
	newstack->data = calloc(newstack->cap, newstack->size);
	memcpy(newstack->data, stack->data, newstack->cap);
	newstack->top = newstack->data + (stack->top - stack->data);
}

static int stack_size(astack_t* stack) {
	return (stack->top - stack->data) / stack->size;
}

static int stack_isempty(astack_t* stack) {
	return stack->data >= stack->top;
}

static int stack_isfull(astack_t* stack) {
	return stack->top >= stack->data + stack->cap * stack->size;
}

static void stack_push(astack_t* stack, void* data) {
	if (stack_isfull(stack)) {
		_stack_resize(stack);
	}
	memcpy(stack->top, data, stack->size);
	stack->top += stack->size;
}

static void* stack_pop(astack_t* stack) {
	if (stack->top > stack->data) {
		void* elem = calloc(1, sizeof(stack->size));
		memcpy(elem, stack->top - stack->size, stack->size);
		stack->top -= stack->size;
		return elem;
	} else {
		return NULL;
	}
}

static void* stack_peek(astack_t* stack) {
	if (stack->top != stack->data) {
		return stack->top - stack->size;
	} else {
		return NULL;
	}
}


#endif //STRUCTS_STACK_H
