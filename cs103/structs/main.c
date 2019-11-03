#include <stdio.h>
#include "quicksort.h"
#include "arraylist.h"
#include "linkedlist.h"

int cmpfunc(const void* a, const void* b, unsigned long size) {
	return *(int*) a < *(int*) b;
}

typedef struct frac {
	double numerator;
	double denominator;
} frac_t;

int frac_cmp(const void* f1, const void* f2, unsigned long size) {
	return ((frac_t*) f1)->numerator / ((frac_t*) f1)->denominator >
		   ((frac_t*) f2)->numerator / ((frac_t*) f2)->denominator;
}

void print_frac(frac_t* frac) {
	printf("[%.2f/%.2f]\n", frac->numerator, frac->denominator);
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

	// for (int i = 1; i < 10000; ++i) {
	// 	int x = random() % i;
	// 	alist_add(intlist, &x);
	// }
	printf("done %d\n", intlist->len);

	alist_t* intlist2 = alist_copy(intlist);
	for (int j = 0; j < intlist2->len; ++j) {
		printf("%d [%d]\n", j, *(int*) alist_get(intlist2, j));
	}

	quicksort(intlist->data, intlist->len, intlist->size, cmpfunc);
	for (int j = 0; j < intlist->len; ++j) {
		printf("%d [%d]\n", j, *(int*) alist_get(intlist, j));
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
	llist_rm_idx(charlist, 0);
	llist_set(charlist, _ptr(88, char), 2);
	llist_add_at(charlist, _ptr(67, char), 3);
	llist_print_front(charlist);
	printf("len: %d\n", llist_size(charlist));
	printf("idx: %d\n", llist_idxof(charlist, &ce));
	char* test = (char*) llist_get(charlist, 2);
	printf("test: %d\n", *test);
	llist_rm(charlist, test);
	printf("test: %d\n", *(char*) llist_get(charlist, 2));


	llist_destroy(charlist);
	alist_destroy(intlist);
	alist_destroy(intlist2);

	alist_t* fraclist = alist_new(sizeof(frac_t));

	frac_t f1 = {1, 2};
	frac_t f2 = {3, 4};
	frac_t f3 = {1, 5};
	frac_t f4 = {3, 2};

	alist_add(fraclist, &f1);
	alist_add(fraclist, &f2);
	alist_add(fraclist, &f3);
	alist_add(fraclist, &f4);

	print_frac(alist_get(fraclist, 0));
	print_frac(alist_get(fraclist, 1));
	print_frac(alist_get(fraclist, 2));
	print_frac(alist_get(fraclist, 3));

	quicksort(fraclist->data, fraclist->len, fraclist->size, frac_cmp);

	print_frac(alist_get(fraclist, 0));
	print_frac(alist_get(fraclist, 1));
	print_frac(alist_get(fraclist, 2));
	print_frac(alist_get(fraclist, 3));
	return 0;
}