//
// Created by nik on 11/4/19.
//

#ifndef STRUCTS_QUEUE_H
#define STRUCTS_QUEUE_H

#pragma once

#include "structs/linkedlist.h"

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif

#include <stdint.h>
#include <stdlib.h>
#include <string.h>


typedef struct queue {
	int size;
	llist_t* data;
} queue_t;

static queue_t* queue_new(int size) {
	queue_t* newqueue = (queue_t*) calloc(1, sizeof(queue_t));
	newqueue->data = llist_new(size);
	newqueue->size = size;

}

static void queue_destroy(queue_t* queue) {
	llist_destroy(queue->data);
	free(queue);
}

static void queue_enqueue(queue_t* queue, void* elem) {
	llist_add_back(queue->data, elem);
}

static void* queue_dequeue(queue_t* queue) {
	if (queue->data->head != NULL) {
		void* elem = calloc(1, queue->size);
		memcpy(elem, queue->data->head->data, queue->size);
		llist_rm_front(queue->data);
		return elem;
	} else {
		return NULL;
	}
}

static void* queue_front(queue_t* queue) {
	if (queue->data->head != NULL) {
		return queue->data->head->data;
	} else {
		return NULL;
	}
}

static int queue_isempty(queue_t* queue) {
	return llist_isempty(queue->data);
}


static void queue_clear(queue_t* queue) {
	void* ptr;
	while (!queue_isempty(queue)) {
		ptr = queue_dequeue(queue);
		free(ptr);
	}
}

#endif //STRUCTS_QUEUE_H
