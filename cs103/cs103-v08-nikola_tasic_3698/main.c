#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "../structs/queue.h"

typedef struct avl_node {
	void* data;
	struct avl_node* left;
	struct avl_node* right;
	int height;
} avl_node_t;

typedef struct avl_tree {
	avl_node_t* root;
	unsigned int size;
} avl_tree_t;

void prtfunc(const void* node) {
	printf("\033[%dB\033[0m", ((avl_node_t*) node)->height);
	printf("%d(%d)", *(int*) ((avl_node_t*) node)->data, ((avl_node_t*) node)->height);
	printf("\033[%dA\033[0m", ((avl_node_t*) node)->height);
}

static void _inorder(avl_node_t* temp, void (* prtfunc)(const void*)) {
	if (temp == NULL) {
		return;
	}
	_inorder(temp->left, prtfunc);
	prtfunc(temp);
	_inorder(temp->right, prtfunc);
}

extern void avl_inorder(avl_tree_t* tree, void (* prtfunc)(const void*)) {
	assert(prtfunc != NULL);
	_inorder(tree->root, prtfunc);
}

static avl_node_t* tnode_new(void* data, unsigned int size) {
	avl_node_t* newnode = (avl_node_t*) calloc(1, sizeof(avl_node_t));
	newnode->data = malloc(size);
	memcpy(newnode->data, data, size);
	newnode->left = NULL;
	newnode->right = NULL;
	return newnode;
}

extern avl_tree_t* avltree_new(uint size) {
	avl_tree_t* newtree = (avl_tree_t*) calloc(1, sizeof(avl_tree_t));
	newtree->root = NULL;
	newtree->size = size;
}


extern void avl_add(avl_tree_t* tree, void* data) {
	avl_node_t* newnode = tnode_new(data, tree->size);
	if (tree->root == NULL) {
		tree->root = newnode;
		return;
	}
	queue_t* nodequeue = queue_new(sizeof(avl_node_t*));
	queue_enqueue(nodequeue, &(tree->root));

	while (!queue_isempty(nodequeue)) {
		avl_node_t** temp = (avl_node_t**) queue_dequeue(nodequeue);
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


int avl_height(avl_node_t* node) {
	if (node == NULL) {
		return 0;
	}

	int h1 = 1 + avl_height(node->left);
	int h2 = 1 + avl_height(node->right);
	int max = h2 > h1 ? h2 : h1;
	node->height = max;
	return max;
}

void avl_calcdb(avl_tree_t* tree) {
	int sum = avl_height(tree->root);
	printf("%d\n", sum);
}

int main() {
	avl_tree_t* tree = avltree_new(sizeof(int));

	avl_add(tree, _ptr(11, int));
	avl_add(tree, _ptr(21, int));
	avl_add(tree, _ptr(31, int));
	avl_add(tree, _ptr(41, int));
	avl_add(tree, _ptr(51, int));
	avl_add(tree, _ptr(61, int));
	avl_add(tree, _ptr(71, int));
	avl_add(tree, _ptr(81, int));
	avl_add(tree, _ptr(91, int));
	avl_add(tree, _ptr(12, int));
	avl_add(tree, _ptr(22, int));
	avl_add(tree, _ptr(32, int));
	avl_add(tree, _ptr(42, int));
	avl_add(tree, _ptr(52, int));
	avl_add(tree, _ptr(62, int));
	avl_add(tree, _ptr(72, int));
	avl_add(tree, _ptr(82, int));
	avl_add(tree, _ptr(92, int));
	avl_add(tree, _ptr(13, int));
	avl_add(tree, _ptr(23, int));
	avl_add(tree, _ptr(33, int));
	avl_add(tree, _ptr(43, int));
	avl_add(tree, _ptr(53, int));
	avl_add(tree, _ptr(63, int));
	avl_add(tree, _ptr(73, int));

	printf("%d @ %p\n", *(int*) tree->root->left->data, tree->root->left);
	printf("%d @ %p\n", *(int*) tree->root->right->data, tree->root->right);

	printf("%d @ %p\n", *(int*) tree->root->right->left->data, tree->root->right->left);
	printf("%d @ %p\n", *(int*) tree->root->right->right->data, tree->root->right->right);

	avl_calcdb(tree);
	avl_inorder(tree, prtfunc);
	return 0;
}