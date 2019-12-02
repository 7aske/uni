#include <stdio.h>
#include <assert.h>
#include <time.h>
#include "htable.h"
#include "quicksort.h"
#include "arraylist.h"
#include "linkedlist.h"
#include "astack.h"
#include "queue.h"
#include "bintree.h"

int cmpfunc(const void* a, const void* b, unsigned long size) {
	return *(int*) a > *(int*) b;
}

int cmpfunc2(const void* a, const void* b, unsigned long size) {
	return *(int*) a == *(int*) b;
}

void inttreeprint(const void* data) {
	printf("%d ", *(int*) data);
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

void printfunc(const void* data) {
	printf("P %d\n", *(int*) data);
}

int main() {
	int a = 10, b = 20, c = 30, cc = 33, x = 0, y = 55, i;
	srand(time(0));


	alist_t* intlist = alist_new(sizeof(int));
	alist_add(intlist, &a);
	alist_add(intlist, &b);
	alist_add(intlist, &c);
	alist_add_at(intlist, &cc, 3);
	alist_set(intlist, &y, 1);
	memcpy(&x, alist_get(intlist, 0), intlist->size);

	assert(intlist->size == sizeof(int));
	assert(10 == *(int*) alist_get(intlist, 0));
	assert(55 == *(int*) alist_get(intlist, 1));
	assert(30 == *(int*) alist_get(intlist, 2));
	assert(33 == *(int*) alist_get(intlist, 3));
	assert(3 == alist_idxof(intlist, &cc));
	assert(3 == alist_idxof_cmp(intlist, &cc, &cmpfunc2));
	alist_shrink(intlist);
	assert(intlist->len == intlist->cap);

	for (i = 1; i < 10000; ++i) {
		alist_add(intlist, _ptr(rand() % i, int));
	}
	assert(intlist->len == 10003);
	assert(intlist->cap == 16384);

	alist_t* intlist2 = alist_copy(intlist);
	assert(intlist2->len == 10003);
	assert(intlist2->cap == 16384);

	for (i = 0; i < intlist2->len; ++i) {
		assert(*(int*) alist_get(intlist, i) == *(int*) alist_get(intlist2, i));
	}

	quicksort(intlist->data, intlist->len, intlist->size, cmpfunc);
	for (i = 0; i < intlist->len - 1; ++i) {
		assert(*(int*) alist_get(intlist, i) >= *(int*) alist_get(intlist, i + 1));
	}


	llist_t* charlist = llist_new(sizeof(char));
	char ca = 32, cb = 33, cd = 34, ce = 55;
	llist_add_front(charlist, &ca);
	llist_add_front(charlist, &cb);
	llist_add_front(charlist, &cd);
	llist_add_back(charlist, &ce);

	assert(34 == *(char*) llist_get(charlist, 0));
	assert(33 == *(char*) llist_get(charlist, 1));
	assert(32 == *(char*) llist_get(charlist, 2));
	assert(55 == *(char*) llist_get(charlist, 3));
	assert(4 == llist_size(charlist));

	llist_rm_idx(charlist, 0);
	assert(33 == *(char*) llist_get(charlist, 0));

	llist_set(charlist, _ptr(88, char), 2);
	assert(88 == *(char*) llist_get(charlist, 2));

	llist_add_at(charlist, _ptr(67, char), 3);
	assert(67 == *(char*) llist_get(charlist, 3));
	assert(33 == *(char*) llist_get(charlist, 0));

	assert(4 == llist_size(charlist));
	assert(-1 == llist_idxof(charlist, &ce));

	llist_rm(charlist, llist_get(charlist, 2));
	assert(3 == llist_size(charlist));

	llist_destroy(&charlist);
	alist_destroy(&intlist);
	alist_destroy(&intlist2);
	assert(charlist == NULL);
	assert(intlist == NULL);
	assert(intlist2 == NULL);

	alist_t* fraclist = alist_new(sizeof(frac_t));
	frac_t f1 = {1, 2};
	frac_t f2 = {3, 4};
	frac_t f3 = {1, 5};
	frac_t f4 = {3, 2};
	alist_add(fraclist, &f1);
	alist_add(fraclist, &f2);
	alist_add(fraclist, &f3);
	alist_add(fraclist, &f4);

	assert(1 == ((frac_t*) (alist_get(fraclist, 0)))->numerator);
	assert(2 == ((frac_t*) (alist_get(fraclist, 0)))->denominator);
	assert(3 == ((frac_t*) (alist_get(fraclist, 1)))->numerator);
	assert(4 == ((frac_t*) (alist_get(fraclist, 1)))->denominator);
	assert(1 == ((frac_t*) (alist_get(fraclist, 2)))->numerator);
	assert(5 == ((frac_t*) (alist_get(fraclist, 2)))->denominator);
	assert(3 == ((frac_t*) (alist_get(fraclist, 3)))->numerator);
	assert(2 == ((frac_t*) (alist_get(fraclist, 3)))->denominator);

	quicksort(fraclist->data, fraclist->len, fraclist->size, frac_cmp);

	assert(3 == ((frac_t*) (alist_get(fraclist, 0)))->numerator);
	assert(2 == ((frac_t*) (alist_get(fraclist, 0)))->denominator);
	assert(3 == ((frac_t*) (alist_get(fraclist, 1)))->numerator);
	assert(4 == ((frac_t*) (alist_get(fraclist, 1)))->denominator);
	assert(1 == ((frac_t*) (alist_get(fraclist, 2)))->numerator);
	assert(2 == ((frac_t*) (alist_get(fraclist, 2)))->denominator);
	assert(1 == ((frac_t*) (alist_get(fraclist, 3)))->numerator);
	assert(5 == ((frac_t*) (alist_get(fraclist, 3)))->denominator);

	alist_destroy(&fraclist);


	astack_t* intastack = astack_new(sizeof(int));
	astack_push(intastack, _ptr(10, int));
	astack_push(intastack, _ptr(20, int));
	astack_t* intastack2 = astack_copy(intastack);

	assert(20 == *(int*) (intastack->top - intastack->size));
	assert(10 == *(int*) (intastack->data));
	assert(2 == astack_size(intastack2));
	assert(20 == *(int*) (intastack2->top - intastack->size));
	assert(10 == *(int*) (intastack2->data));

	int* elem1 = astack_pop(intastack2);
	int* elem2 = astack_pop(intastack2);

	assert(20 == *(int*) (elem1));
	assert(10 == *(int*) (elem2));
	free(elem1);
	free(elem2);

	for (i = 0; i < 1000; ++i) {
		astack_push(intastack2, _ptr(i, int));
	}
	int* elem;
	for (i = 0; i < 1000; ++i) {
		elem = astack_pop(intastack2);
		free(elem);
	}
	assert(0 == astack_size(intastack2));

	astack_destroy(&intastack);
	astack_destroy(&intastack2);

	assert(NULL == intastack);
	assert(NULL == intastack2);

	queue_t* intqueue = queue_new(sizeof(int));

	queue_enqueue(intqueue, _ptr(10, int));
	queue_enqueue(intqueue, _ptr(20, int));
	int* qelem1 = queue_dequeue(intqueue);
	int* qelem2 = queue_dequeue(intqueue);
	assert(10 == *(int*) qelem1);
	assert(20 == *(int*) qelem2);
	free(qelem1);
	free(qelem2);

	assert(1 == queue_isempty(intqueue));
	queue_enqueue(intqueue, _ptr(30,
								 int));
	assert(30 == *(int*) queue_front(intqueue));

	queue_destroy(&intqueue);
	assert(NULL == intqueue);

	bintree_t* inttree = bintree_new(sizeof(int));

	bintree_add(inttree, _ptr(10, int));
	bintree_add(inttree, _ptr(20, int));
	bintree_add(inttree, _ptr(30, int));
	bintree_add(inttree, _ptr(40, int));
	bintree_add(inttree, _ptr(50, int));
	bintree_add(inttree, _ptr(60, int));
	bintree_add(inttree, _ptr(70, int));

	bintree_destroy(&inttree);
	assert(NULL == inttree);

	long tabletest[] = {1, 2, 3, 4, 5, 6, 7};

	htable_t* inttable = htable_new(sizeof(long));
	htable_add(inttable, &tabletest[0]);
	htable_add(inttable, &tabletest[1]);
	htable_add(inttable, &tabletest[2]);
	htable_add(inttable, &tabletest[3]);
	htable_add(inttable, &tabletest[4]);
	htable_add(inttable, &tabletest[5]);
	
	assert(6 == htable_size(inttable));
	return 0;
}