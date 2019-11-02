#include <stdio.h>
#include "quicksort.h"
#include "arraylist.h"
#include "linkedlist.h"

int cmpfunc(void* a, void* b, uint size) {
	return *(int*) a > *(int*) b;
}


int main() {
	int a = 10, b = 20, c = 30, cc = 30;
	alist_t* intlist = alist_new(sizeof(int));
	alist_add(intlist, &a);
	alist_add(intlist, &b);
	alist_add(intlist, &c);
	alist_add_at(intlist, &c, 3);
	int x, y = 55;
	alist_set(intlist, &y, 1);
	// alist_rm_idx(intlist, 1);
	memcpy(&x, alist_get(intlist, 0), intlist->size);
	printf("%p\n", alist_get(intlist, 0));
	printf("%d [%d]\n", 0, *(int*) alist_get(intlist, 0));
	printf("%p\n", alist_get(intlist, 1));
	printf("%d [%d]\n", 1, *(int*) alist_get(intlist, 1));
	printf("%p\n", alist_get(intlist, 2));
	printf("%d [%d]\n", 2, *(int*) alist_get(intlist, 2));
	printf("%p\n", alist_get(intlist, 3));
	printf("%d [%d]\n", 3, *(int*) alist_get(intlist, 3));
	printf("[%d]\n", alist_idxof(intlist, &cc));
	printf("[%d]\n", alist_idxof_cmp(intlist, &cc, &cmpfunc));

	printf("cap: %d\n", intlist->cap);
	alist_shrink(intlist);
	printf("cap: %d\n", intlist->cap);

	for (int i = 1; i < 10000; ++i) {
		int x = random() % i;
		alist_add(intlist, &x);
	}
	quicksort(intlist->data, intlist->len, intlist->size, NULL);
	alist_t* intlist2 = alist_copy(intlist);
	printf("done %d\n", intlist->len);
	for (int j = 0; j < intlist2->len; ++j) {
		printf("%d [%d]\n", j, *(int*) alist_get(intlist2, j));
	}


	llist_t* charlist = llist_new(sizeof(char));
	char ca = 32, cb = 33, cd = 34, ce = 55;
	llist_add_front(charlist, &ca);
	llist_add_front(charlist, &cb);
	llist_add_front(charlist, &cd);
	llist_add_back(charlist, &ce);
	llist_print_front(charlist);
	printf("len: %d\n", llist_size(charlist));
	// llist_print_back(charlist);
	// printf("get: %d\n", *(char*)llist_get(charlist, 4));
	llist_rm(charlist, 0);
	llist_set(charlist, _ptr(88,char), 2);
	llist_add_at(charlist, _ptr(67, char), 3);
	llist_print_front(charlist);
	printf("len: %d\n", llist_size(charlist));
	printf("idx: %d\n", llist_idxof(charlist, &ce));
	llist_destroy(charlist);
	alist_destroy(intlist);
	alist_destroy(intlist2);
	return 0;
}