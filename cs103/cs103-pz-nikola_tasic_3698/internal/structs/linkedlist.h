
#ifndef STRUCTS_LINKEDLIST_H
#define STRUCTS_LINKEDLIST_H

#pragma once

#include <stdlib.h>
#include <stdint.h>
#include <string.h>

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif

typedef struct node {
	void* data;
	struct node* next;
	struct node* prev;
} node_t;

typedef struct llist_t {
	int size;
	node_t* head;
	node_t* tail;

	int (* cmpfunc)(const void*, const void*, unsigned long);
} llist_t;


static void llist_destroy(llist_t* list) {
	if (list != NULL) {
		node_t* current = list->head;
		while (current != NULL) {
			free(current->data);
			current->data = NULL;

			node_t* temp = current;
			current = current->next;
			free(temp);
		}
		free(list);
	}
}


static llist_t* llist_new(int size) {
	llist_t* list = (llist_t*) calloc(1, sizeof(llist_t));
	list->size = size;
	list->head = NULL;
	list->tail = NULL;
	list->cmpfunc = memcmp;
	return list;
}

static void llist_set_cmp(llist_t* list, int(* cmpfunc)(const void*, const void*, unsigned long)) {
	assert(cmpfunc != NULL);
	list->cmpfunc = cmpfunc;
}


static node_t* _newnode(void* data, int size) {
	node_t* newnode = (node_t*) calloc(1, sizeof(node_t));
	newnode->data = calloc(1, size);
	memcpy(newnode->data, data, size);
	newnode->next = NULL;
	newnode->prev = NULL;
	return newnode;
}

static void _rmnode(llist_t* list, node_t* current) {
	if (current->prev != NULL && current->next != NULL) {
		current->prev->next = current->next;
		current->next->prev = current->prev;
	} else if (current->prev == NULL && current->next != NULL) {
		current->next->prev = NULL;
		list->head = current->next;
	} else if (current->next == NULL && current->prev != NULL) {
		current->prev->next = NULL;
		list->tail = current->prev;
	} else {
		list->tail = NULL;
		list->head = NULL;
	}
	free(current->data);
	free(current);
}

static void llist_add_front(llist_t* list, void* data) {
	node_t* newnode = _newnode(data, list->size);
	if (list->head == NULL && list->tail == NULL) {
		list->head = newnode;
		list->tail = newnode;
	} else {
		newnode->next = list->head;
		list->head->prev = newnode;
		list->head = newnode;
	}
}

static void llist_add_back(llist_t* list, void* data) {
	node_t* newnode = _newnode(data, list->size);
	if (list->head == NULL && list->tail == NULL) {
		list->head = newnode;
		list->tail = newnode;
	} else {
		newnode->prev = list->tail;
		list->tail->next = newnode;
		list->tail = newnode;
	}
}


static void llist_add_at(llist_t* list, void* data, int index) {
	int _index = 0;
	node_t* current = list->head;
	if (index == 0) {
		llist_add_front(list, data);
	} else {
		while (current != NULL && _index <= index) {
			if (_index == index) {
				node_t* newnode = _newnode(data, list->size);
				newnode->next = current;
				newnode->prev = current->prev;
				current->prev->next = newnode;
				current->prev = newnode;
				break;
			}
			current = current->next;
			_index++;
		}
		if (_index == index) {
			llist_add_back(list, data);
		}
	}
}


static llist_t* llist_copy(llist_t* list) {
	llist_t* newlist = (llist_t*) calloc(1, sizeof(llist_t));
	newlist->size = list->size;
	newlist->head = NULL;
	newlist->tail = NULL;
	node_t* current = list->head;
	while (current != NULL) {
		llist_add_back(newlist, current->data);
		current = current->next;
	}

	return newlist;
}

static void* llist_get(llist_t* list, int index) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL && _index <= index) {
		if (_index == index) {
			return current->data;
		}
		current = current->next;
		_index++;
	}
	return NULL;
}

static node_t* llist_get_node(llist_t* list, int index) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL && _index <= index) {
		if (_index == index) {
			return current;
		}
		current = current->next;
		_index++;
	}
	return NULL;
}

static void* llist_getr(llist_t* list, int index) {
	int _index = 0;
	node_t* current = list->tail;
	while (current != NULL && _index <= index) {
		if (_index == index) {
			return current->data;
		}
		current = current->prev;
		_index++;
	}
	return NULL;
}

static node_t* llist_get_last_node(llist_t* list) {
	return list->tail;
}

static node_t* llist_get_first_node(llist_t* list) {
	return list->head;
}


static void* llist_get_last(llist_t* list) {
	return list->tail->data;
}

static void* llist_get_first(llist_t* list) {
	return list->head->data;
}


static int llist_idxof(llist_t* list, void* data) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL) {
		if (list->cmpfunc(current->data, data, list->size) == 0) {
			return _index;
		}
		current = current->next;
		_index++;
	}
	return -1;
}

static int llist_idxof_cmp(llist_t* list, void* data, int(* cmpfunc)(const void*, const void*, unsigned long)) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL) {
		if (cmpfunc(current->data, data, list->size) == 0) {
			return _index;
		}
		current = current->next;
		_index++;
	}
	return -1;
}


static void llist_rm_idx(llist_t* list, int index) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL && _index <= index) {
		if (_index == index) {
			_rmnode(list, current);
			break;
		}
		current = current->next;
		_index++;
	}
}

static void llist_rm(llist_t* list, void* elem) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL) {
		if (list->cmpfunc(current->data, elem, list->size) == 0) {
			_rmnode(list, current);
			break;
		}
		current = current->next;
		_index++;
	}
}

static void llist_rm_front(llist_t* list) {
	if (list->head != NULL) {
		_rmnode(list, list->head);
	}
}

static void llist_rm_back(llist_t* list) {
	if (list->tail != NULL) {
		_rmnode(list, list->tail);
	}
}

static void llist_set(llist_t* list, void* data, int index) {
	int _index = 0;
	node_t* current = list->head;
	while (current != NULL && _index <= index) {
		if (_index == index) {
			memcpy(current->data, data, list->size);
		}
		current = current->next;
		_index++;
	}
}

static int llist_size(llist_t* list) {
	int len = 0;
	node_t* current = list->head;
	while (current != NULL) {
		len++;
		current = current->next;
	}
	return len;
}

static int llist_isempty(llist_t* list) {
	return list->tail == NULL && list->head == NULL;
}

static void llist_print_front(llist_t* list, void (*_printfunc)(const void*)) {
	assert(_printfunc != NULL);
	node_t* current = list->head;
	if (current != NULL) {
		while (current != NULL) {
			_printfunc(current->data);
			current = current->next;
		}
	}
}

static void llist_print_back(llist_t* list, void (*_printfunc)(const void*)) {
	assert(_printfunc != NULL);
	node_t* current = list->tail;
	if (current != NULL) {
		while (current != NULL) {
			_printfunc(current->data);
			current = current->prev;
		}
	}
}

#endif