#pragma ide diagnostic ignored "cert-msc30-c"
#pragma ide diagnostic ignored "cert-msc32-c"

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "../structs/include/pqueue.h"

#define W 3
#define H 3

/*9. Najkraća putanja u mreži. Ako je data matrica NxN sa pozitivnim celim brojevima, pronaći
najkraći put od polja (0, 0) do polja (N-1, N-1) pri čemu je ukupan put zbir brojeva na putanji
(može se kretati samo desno i dole). */

void fillrand(int* matrix, int w, int h) {
	int i, j;
	for (i = 0; i < h; ++i) {
		for (j = 0; j < w; ++j) {
			matrix[i * w + j] = rand() % 10;
		}
	}
}

void printm(int* matrix, int w, int h) {
	int i, j;
	for (i = 0; i < h; ++i) {
		for (j = 0; j < w; ++j) {
			putchar('0' + matrix[i * w + j]);
			putchar(32);
		}
		putchar(10);
	}
}

int intcmp(const int* a, const int* b, size_t size) {
	if (*a > *b) {
		return 1;
	} else if (*a < *b) {
		return -1;
	} else {
		return 0;
	}
}

void foo(int* matrix, int s, int e) {
	int i;
	int vis[W * H];
	memset(vis, 0, sizeof(int) * W * H);
	int* node;
	int n;
	pqueue_t* visited = pqueue_new(sizeof(struct gnode*), sizeof(int));
	pqueue_set_cmp(visited, (int (*)(const void*, const void*, size_t)) intcmp);

	pqueue_enqueue(visited, &s, &matrix[s]);
	vis[s] = 1;
	while (!pqueue_isempty(visited)) {
		node = pqueue_dequeue(visited);
		vis[*node] = 1;
		if (*node == e) {
			printf("holy shit\n");
			break;
		}
		printf("N %d\n", *node);

		// desno
		n = *node + 1;
		if (n < W) {
			if (!vis[n]) {
				pqueue_enqueue(visited, &n, &matrix[n]);
			}
		}

		// dole
		n = *node + W;
		if (n < W * H) {
			if (!vis[n]) {
				pqueue_enqueue(visited, &n, &matrix[n]);
			}
		}
		free(node);
	}
	pqueue_destroy(&visited);
}


int main(void) {
	// srand(time(0));
	int* matrix = calloc(W * H, sizeof(int));
	fillrand(matrix, W, H);
	printm(matrix, W, H);
	putchar(10);
	// TODO lepo prikazivanje putanje
	foo((int*) matrix, 0, 8);
	return 0;
}

