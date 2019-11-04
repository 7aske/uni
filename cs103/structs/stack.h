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
#define _ptr(x, size) &((size)){(x)}
#endif

#define STACK_CAP 16

typedef struct stack {
	uint size;
	uint cap;
	void* top;
	void* data;
} stack_t;

void _stack_resize(stack_t* stack) {
	int32_t delta = stack->top - stack->data;
	stack->cap = stack->cap * 2 + 1;
	stack->data = reallocarray(stack->data, stack->cap, stack->size);
	stack->top = stack->data + delta;
}

void stack_destroy(stack_t* stack) {
	free(stack->data);
	free(stack);
}

stack_t* stack_new(uint size) {
	stack_t* newstack = (stack_t*) calloc(1, sizeof(stack_t));
	newstack->size = size;
	newstack->cap = STACK_CAP;
	newstack->data = calloc(newstack->cap, newstack->size);
	newstack->top = newstack->data;
}

stack_t* stack_copy(stack_t* stack) {
	stack_t* newstack = (stack_t*) calloc(1, sizeof(stack_t));
	newstack->size = stack->size;
	newstack->cap = stack->cap;
	newstack->data = calloc(newstack->cap, newstack->size);
	memcpy(newstack->data, stack->data, newstack->cap);
	newstack->top = newstack->data + (stack->top - stack->data);
}

int32_t stack_size(stack_t* stack) {
	return (stack->top - stack->data) / stack->size;
}

int stack_isempty(stack_t* stack) {
	return stack->data == stack->top;
}

int stack_isfull(stack_t* stack) {
	return stack->top == stack->data + stack->cap * stack->size;
}

void stack_push(stack_t* stack, void* data) {
	if (stack_isfull(stack)) {
		_stack_resize(stack);
	}
	memcpy(stack->top, data, stack->size);
	stack->top += stack->size;
}

void* stack_pop(stack_t* stack) {
	if (stack->top != stack->data) {
		void* elem = calloc(1, sizeof(stack->size));
		memcpy(elem, stack->top - stack->size, stack->size);
		stack->top -= stack->size;
		return elem;
	} else {
		return NULL;
	}
}

void* stack_peek(stack_t* stack) {
	if (stack->top != stack->data) {
		return stack->top - stack->size;
	} else {
		return NULL;
	}
}


#endif //STRUCTS_STACK_H
