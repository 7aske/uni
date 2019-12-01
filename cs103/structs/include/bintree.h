//
// Created by nik on 11/21/19.
//

#ifndef STRUCTS_BINTREE_H
#define STRUCTS_BINTREE_H

#pragma once

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

typedef struct bintree {
	tnode_t* root;
	int size;
} bintree_t;


static tnode_t* tnode_new(void* data, int size) {
	tnode_t* newnode = (tnode_t*) calloc(1, sizeof(tnode_t));
	newnode->data = malloc(size);
	memcpy(newnode->data, data, size);
	newnode->left = NULL;
	newnode->right = NULL;
	return newnode;
}

static bintree_t* bintree_new(int size) {
	bintree_t* newtree = (bintree_t*) calloc(1, sizeof(bintree_t));
	newtree->root = NULL;
	newtree->size = size;
}

static void bintree_add(bintree_t* tree, void* data) {
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
	queue_destroy(&nodequeue);
}

static void _bintree_destroy(tnode_t* temp) {
	if (temp == NULL) {
		return;
	}

	_bintree_destroy(temp->left);
	_bintree_destroy(temp->right);
	free(temp->data);
	free(temp);
}

static void bintree_destroy(bintree_t* tree) {
	_bintree_destroy(tree->root);
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

static void bintree_inorder(bintree_t* tree, void (* prtfunc)(const void*)) {
	assert(prtfunc != NULL);
	inorder(tree->root, prtfunc);
}

#endif //STRUCTS_BINTREE_H
