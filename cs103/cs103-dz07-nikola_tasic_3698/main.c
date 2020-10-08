#include <stdio.h>

#include "../structs/btree.h"

void prtfunc(const void* data) {
	printf("%d ", *(int*) data);
}

int sum_nodes(tnode_t* temp) {
	if (temp == NULL) {
		return 0;
	}

	return  *(int*) temp->data + sum_nodes(temp->left) + sum_nodes(temp->right);
}

void traverse(tnode_t* temp) {
	if (temp == NULL) {
		return;
	}

	int left_sum = sum_nodes(temp->left);
	int right_sum = sum_nodes(temp->right);
	if (left_sum > right_sum) {
		printf("%d @ %p left_sum=%d right_sum=%d\n", *(int*) temp->data, temp, left_sum, right_sum);
	}
	traverse(temp->left);
	traverse(temp->right);
}


int main() {
	btree_t* tree = btree_new(sizeof(int));

	btree_add(tree, _ptr(11, int));
	btree_add(tree, _ptr(21, int));
	btree_add(tree, _ptr(31, int));
	btree_add(tree, _ptr(41, int));
	btree_add(tree, _ptr(51, int));
	btree_add(tree, _ptr(61, int));
	btree_add(tree, _ptr(71, int));
	btree_add(tree, _ptr(81, int));
	btree_add(tree, _ptr(91, int));
	btree_add(tree, _ptr(12, int));
	btree_add(tree, _ptr(22, int));
	btree_add(tree, _ptr(32, int));
	btree_add(tree, _ptr(42, int));
	btree_add(tree, _ptr(52, int));
	btree_add(tree, _ptr(62, int));
	btree_add(tree, _ptr(72, int));
	btree_add(tree, _ptr(82, int));
	btree_add(tree, _ptr(92, int));
	btree_add(tree, _ptr(13, int));
	btree_add(tree, _ptr(23, int));
	btree_add(tree, _ptr(33, int));
	btree_add(tree, _ptr(43, int));

	traverse(tree->root);

	btree_inorder(tree, prtfunc);
	btree_destroy(tree);
	return 0;
}