//
// Created by nik on 12/7/19.
//

#ifndef STRUCTS_PQUEUE_H
#define STRUCTS_PQUEUE_H

#include "linkedlist.h"


static int pqueuecmp(void const* p1, void const* p2, size_t size) {
	if (*(int*) p1 > *(int*) p2) {
		return 1;
	} else if (*(int*) p1 > *(int*) p2) {
		return -1;
	} else {
		return 0;
	}
}

typedef struct pqueue {
	size_t dsize;
	size_t psize;
	llist_t* data;

	int (* cmpfunc)(void const* p1, void const* p2, size_t size)
} pqueue_t;

typedef struct pqeueue_node {
	void* data;
	void* prio;
} pqueue_node_t;

static pqueue_node_t* pqueue_node_new(void const* data, void const* prio, size_t dsize, size_t psize) {
	pqueue_node_t* newnode = calloc(1, sizeof(pqueue_node_t));
	newnode->data = malloc(dsize);
	memcpy(newnode->data, data, dsize);
	newnode->prio = malloc(psize);
	if (prio != NULL) {
		memcpy(newnode->prio, prio, psize);
	} else {
		memset(newnode->prio, 255u, psize);
	}
	return newnode;
}

static pqueue_t* pqueue_new(int dsize, int psize) {
	assert(dsize > 0);
	assert(psize >= 0);
	pqueue_t* newpqueue = (pqueue_t*) calloc(1, sizeof(pqueue_t));
	newpqueue->data = llist_new(sizeof(pqueue_node_t));
	newpqueue->dsize = dsize;
	newpqueue->psize = psize == 0 ? (int) sizeof(int) : psize;
	newpqueue->cmpfunc = pqueuecmp;
}

static void pqueue_set_cmp(pqueue_t* pqueue, int(* cmpfunc)(void const*, void const*, size_t)) {
	assert(cmpfunc != NULL);
	pqueue->cmpfunc = cmpfunc;
}

static void pqueue_destroy(pqueue_t** pqueue) {
	assert(pqueue != NULL);
	pqueue_node_t* elem;
	while (!llist_isempty((*pqueue)->data)) {
		elem = llist_get_last((*pqueue)->data);
		assert(elem != NULL);
		free(elem->data);
		free(elem->prio);
		llist_rm_back((*pqueue)->data);
	}
	free((*pqueue)->data);
	free(*pqueue);
	*pqueue = NULL;
}

static void pqueue_enqueue(pqueue_t* pqueue, void const* data, void const* prio) {
	assert(pqueue != NULL);
	assert(data != NULL);
	pqueue_node_t* newnode = pqueue_node_new(data, prio, pqueue->dsize, pqueue->psize);
	node_t* head = llist_get_first_node(pqueue->data);
	node_t* tail = llist_get_last_node(pqueue->data);

	if (llist_isempty(pqueue->data)) {
		llist_add_front(pqueue->data, newnode);
		free(newnode);
		return;
	}

	if (pqueue->cmpfunc(newnode->prio, ((pqueue_node_t*) head->data)->prio, pqueue->psize) >= 0) {
		llist_add_front(pqueue->data, newnode);
		free(newnode);
		return;
	}

	// if (pqueue->cmpfunc(newnode->prio, ((pqueue_node_t*) tail->data)->prio, pqueue->psize) <= 0) {
	// 	llist_add_back(pqueue->data, newnode);
	// 	free(newnode);
	// 	return;
	// }

	while (head != NULL) {
		if (pqueue->cmpfunc(newnode->prio, ((pqueue_node_t*) head->data)->prio, pqueue->psize) >= 0) {
			node_t* newlnode = _newnode(newnode, sizeof(pqueue_node_t));
			free(newnode);
			newlnode->next = head;
			newlnode->prev = head->prev;
			head->prev->next = newlnode;
			head->prev = newlnode;
			return;
		}
		head = head->next;
	}
	llist_add_back(pqueue->data, newnode);
	free(newnode);
}

static void* pqueue_dequeue(pqueue_t* pqueue) {
	if (!llist_isempty(pqueue->data)) {
		void* elem = calloc(1, pqueue->dsize);
		pqueue_node_t* first_node = llist_get_first_node(pqueue->data)->data;
		memcpy(elem, first_node->data, pqueue->dsize);
		free(first_node->data);
		free(first_node->prio);
		llist_rm_front(pqueue->data);
		return elem;
	} else {
		return NULL;
	}
}

static void* pqueue_front(pqueue_t* pqueue) {
	if (!llist_isempty(pqueue->data)) {
		return ((pqueue_node_t*) llist_get_first_node(pqueue->data)->data)->data;
	} else {
		return NULL;
	}
}

static int pqueue_isempty(pqueue_t* pqueue) {
	return llist_isempty(pqueue->data);
}

static int pqueue_size(pqueue_t* pqueue) {
	return llist_size(pqueue->data);
}

#endif //STRUCTS_PQUEUE_H
