#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include "quicksort.h"

// 13. Napisati program koji će učitati dimenziju niza (n<20), elemente niza realnih brojeva
// uređene u neopadajućem poretku, realni broj x I ispisati na standardni izlaz niz sa ubačenim
// elementom x tako da niz ostane u neopadajućem poretku.


// Pise ucitati u neopadajucem poretku i zbog toga nisam koristio sortiranje

int main() {
	char buf[32];
	double val = INT32_MIN;
	double last = INT32_MIN;
	double* arr = NULL;
	double x = INT32_MIN;
	uint8_t maxlen = UINT8_MAX;
	uint8_t i = 0;

	while (maxlen > 20) {
		printf("Enter the arr length: ");
		fgets(buf, 31, stdin);
		maxlen = (uint8_t) strtol(buf, NULL, 10);
	}

	arr = (double*) calloc(maxlen, sizeof(double));

	while (val <= last && i < maxlen) {
		printf("Enter an element: ");
		fgets(buf, 31, stdin);
		val = strtod(buf, NULL);
		if (val >= last) {
			printf("arr[%d] = %.2f\n", i, val);
			last = val;
			arr[i] = val;
			i++;
		}
	}


	while (x == INT32_MIN || x == 0) {
		printf("Enter X: ");
		fgets(buf, 31, stdin);
		x = strtod(buf, NULL);
		printf("%.2f\n", x);
	}


	arr = reallocarray(arr, maxlen + 1, sizeof(double));

	for (int j = 0; j < maxlen; ++j) {
		if (arr[j + 1] >= x) {
			printf("%.2f %.2f\n", arr[j], x);
			if (j <= maxlen - 1) {
				memmove(&arr[j+1], &arr[j], (maxlen - j + 1) * sizeof(double));
				arr[j] = x;
			}
			break;
		}
		if (j == maxlen - 1){
			arr[maxlen] = x;
		}
	}

	for (int j = 0; j < maxlen + 1; ++j) {
		printf("%.2f ", arr[j]);
	}
	free(arr);
	printf("\n");
}