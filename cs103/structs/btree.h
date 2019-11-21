//
// Created by nik on 11/21/19.
//

#ifndef STRUCTS_BTREE_H
#define STRUCTS_BTREE_H

#pragma once

#include <stdint.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

#include "queue.h"

#ifndef _ptr
#define _ptr(x, size) &(size){(x)}
#endif

typedef struct tnode {
	void* data;
	struct tnode* left;
	struct tnode* right;
} tnode_t;

typedef struct btree {
	tnode_t* root;
	uint size;
} btree_t;


static tnode_t* tnode_new(void* data, uint size) {
	tnode_t* newnode = (tnode_t*) calloc(1, sizeof(tnode_t));
	newnode->data = malloc(size);
	memcpy(newnode->data, data, size);
	newnode->left = NULL;
	newnode->right = NULL;
	return newnode;
}

extern btree_t* btree_new(uint size) {
	btree_t* newtree = (btree_t*) calloc(1, sizeof(btree_t));
	newtree->root = NULL;
	newtree->size = size;
}

extern void btree_add(btree_t* tree, void* data) {
	tnode_t* newnode = tnode_new(data, tree->size);
	if (tree->root == NULL) {
		tree->root = newnode;
		return;
	}
	queue_t* nodequeue = queue_new(sizeof(tnode_t*));
	queue_enqueue(nodequeue, &(tree->root));

	while (!queue_isempty(nodequeue)) {
		tnode_t** temp = (tnode_t**) queue_dequeue(nodequeue);
		if ((*temp)->left == NULL) {
			(*temp)->left = newnode;
			free(temp);
			break;
		} else {
			queue_enqueue(nodequeue, &((*temp)->left));
		}

		if ((*temp)->right == NULL) {
			(*temp)->right = newnode;
			free(temp);
			break;
		} else {
			queue_enqueue(nodequeue, &((*temp)->right));
		}
		free(temp);
	}
	queue_destroy(nodequeue);
}

extern void _btree_destroy(tnode_t* temp) {
	if (temp == NULL) {
		return;
	}

	_btree_destroy(temp->left);
	_btree_destroy(temp->right);
	free(temp->data);
	free(temp);
}

extern void btree_destroy(btree_t* tree) {
	_btree_destroy(tree->root);
	free(tree);
}

static void inorder(tnode_t* temp, void (* prtfunc)(const void*)) {
	if (temp == NULL) {
		return;
	}

	inorder(temp->left, prtfunc);
	prtfunc(temp->data);
	inorder(temp->right, prtfunc);
}

extern void btree_inorder(btree_t* tree, void (* prtfunc)(const void*)) {
	assert(prtfunc != NULL);
	inorder(tree->root, prtfunc);
}

#endif //STRUCTS_BTREE_H
