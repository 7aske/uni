#include <stdlib.h>
#include <stdint.h>
#include <string.h>

typedef struct node {
	void* data;
	struct node* next;
	struct node* prev;
} node_t;

typedef struct llist_t {
	uint size;
	node_t* head;
	node_t* tail;
} llist_t;


extern llist_t* llist_new(uint size) {
	llist_t* list = (llist_t*) calloc(1, sizeof(llist_t));
	list->size = size;
	list->head = NULL;
	list->tail = NULL;
	return list;
}

static node_t* _newnode(void* data, uint size) {
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

extern void llist_add_front(llist_t* list, void* data) {
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

extern void llist_add_back(llist_t* list, void* data) {
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

extern void* llist_get(llist_t* list, uint index) {
	uint _index = 0;
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

extern void* llist_getr(llist_t* list, uint index) {
	uint _index = 0;
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


extern uint32_t llist_idxof(llist_t* list, void* data) {
	uint32_t _index = 0;
	node_t* current = list->head;
	while (current != NULL) {
		if (memcmp(current->data, data, list->size) == 0) {
			return _index;
		}
		current = current->next;
		_index++;
	}
	return -1;
}


extern void llist_rm(llist_t* list, uint index) {
	uint _index = 0;
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

extern void llist_set(llist_t* list, void* data, uint index) {
	uint _index = 0;
	node_t* current = list->head;
	while (current != NULL && _index <= index) {
		if (_index == index) {
			memcpy(current->data, data, list->size);
		}
		current = current->next;
		_index++;
	}
}

extern uint llist_size(llist_t* list) {
	uint len = 0;
	node_t* current = list->head;
	while (current != NULL) {
		len++;
		current = current->next;
	}
	return len;
}

extern int8_t llist_isempty(llist_t* list) {
	return list->tail == NULL && list->head == NULL;
}

extern void llist_print_front(llist_t* list) {
	node_t* current = list->head;
	if (current != NULL) {
		while (current->next != NULL) {
			printf("%d\n", *(char*) current->data);
			current = current->next;
		}
		printf("%d\n", *(char*) current->data);
	}
}

extern void llist_print_back(llist_t* list) {
	node_t* current = list->tail;
	if (current != NULL) {
		while (current->prev != NULL) {
			printf("%d\n", *(char*) current->data);
			current = current->prev;
		}
		printf("%d\n", *(char*) current->data);
	}
}
