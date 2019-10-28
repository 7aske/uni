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

extern int8_t llist_isempty(llist_t* list) {
	return list->tail == NULL && list->head == NULL;
}

extern void print_front(llist_t* list) {
	node_t* current = list->head;
	if (current != NULL) {
		while (current->next != NULL) {
			printf("%d\n", *(char*) current->data);
			current = current->next;
		}
		printf("%d\n", *(char*) current->data);
	}
}

extern void print_back(llist_t* list) {
	node_t* current = list->tail;
	if (current != NULL) {
		while (current->prev != NULL) {
			printf("%d\n", *(char*) current->data);
			current = current->prev;
		}
		printf("%d\n", *(char*) current->data);
	}
}
