#include <stdio.h>
#include <stdlib.h>

struct treenode {
	int val;
	int height;
	struct treenode* left;
	struct treenode* right;
};

int max(int a, int b) {
	return a >= b ? a : b;
}

int height(struct treenode* node) {
	if (node == NULL) {
		return 0;
	}
	int h1 = 1 + height(node->left);
	int h2 = 1 + height(node->right);
	int m = max(h1, h2);
	node->height = m;
	return m;
}

void rr_rotate(struct treenode* root) {
	struct treenode* node = root->left;
	root->left = node->left;
	root->left->right = node;
	node->left = NULL;

	height(root);
	height(node);
}


int main() {
	struct treenode root;
	root.val = 10;
	root.left = calloc(1, sizeof(struct treenode));
	root.left->val = 8;
	root.left->left = calloc(1, sizeof(struct treenode));
	root.left->left->val = 4;
	root.left->left->left = calloc(1, sizeof(struct treenode));
	root.left->left->left->val = 2;
	root.right = calloc(1, sizeof(struct treenode));
	root.right->val = 12;
	height(&root);

	printf("before rotate \tval: (%d) lchild: (%d) rchild: (%d)\n", root.left->val, root.left->left ? root.left->left->val : -1,
		   root.left->right ? root.left->right->val : -1);
	printf("before rotate \troot height: %d\n", root.height);
	rr_rotate(&root);
	printf("after rotate  \tval: (%d) lchild: (%d) rchild: (%d)\n", root.left->val, root.left->left ? root.left->left->val : -1,
		   root.left->right ? root.left->right->val : -1);
	printf("after rotate  \troot height: %d\n", root.height);
	return 0;
}
