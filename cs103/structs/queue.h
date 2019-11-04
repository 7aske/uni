//
// Created by nik on 11/4/19.
//

#ifndef STRUCTS_QUEUE_H
#define STRUCTS_QUEUE_H

#pragma once

#include "linkedlist.h"

#include <stdint.h>
#include <stdlib.h>
#include <string.h>


typedef struct queue {
	uint size;
	llist_t* data;
} queue_t;

queue_t* queue_new(uint size) {
	queue_t* newqueue = (queue_t*) calloc(1, sizeof(queue_t));
	newqueue->data = llist_new(size);
	newqueue->size = size;

}

void queue_destroy(queue_t* queue) {
	llist_destroy(queue->data);
	free(queue);
}

void queue_enqueue(queue_t* queue, void* elem) {
	llist_add_back(queue->data, elem);
}

void* queue_dequeue(queue_t* queue) {
	if (queue->data->head != NULL) {
		void* elem = calloc(1, queue->size);
		memcpy(elem, queue->data->head->data, queue->size);
		llist_rm_front(queue->data);
		return elem;
	} else {
		return NULL;
	}
}

void* queue_front(queue_t* queue){
	if (queue->data->head != NULL) {
		return queue->data->head->data;
	} else {
		return NULL;
	}
}

int queue_isempty(queue_t* queue) {
	return llist_isempty(queue->data);
}


#endif //STRUCTS_QUEUE_H
