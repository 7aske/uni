#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <stdint.h>

// utils
void memswp(void* a, void* b, uint size) {
	void* t = malloc(size);
	memcpy(t, a, size);
	memcpy(a, b, size);
	memcpy(b, t, size);
	free(t);
}

int64_t badd(void* num, uint size) {
	int64_t sum = 0;
	int64_t n = *(int64_t*) num;
	uint bmask = ((uint) 1 << (size * CHAR_BIT - 1));
	uint msb = n & bmask;
	if (msb) {
		n = ~n + 1;
		for (uint i = bmask; i > 0; i = i / 2) { // (n & i) ? printf("1") : printf("0");
			sum -= i * !!(n & i);
		}
	} else {
		for (uint i = bmask; i > 0; i = i / 2) { // (n & i) ? printf("1") : printf("0");
			sum += i * !!(n & i);
		}
	}
	return sum;
}


int partition(void* arr, uint size, int low, int high) {
	void* pivot = malloc(size);
	memcpy(pivot, arr + high * size, size);
	int i = low - 1;

	for (int j = low; j <= high - 1; j++) {
		if (badd(arr + j * size, size) < badd(pivot, size)) {
			i++;
			memswp(arr + i * size, arr + j * size, size);
		}
	}
	memswp(arr + (i + 1) * size, arr + high * size, size);
	free(pivot);
	return i + 1;
}

void quickSort(void* arr, uint size, int low, int high) {
	if (low < high) {
		int pi = partition(arr, size, low, high);
		quickSort(arr, size, low, pi - 1);
		quickSort(arr, size, pi + 1, high);
	}
}
// end utils


// zadatak1
int zadatak1(char* str1, char* str2) {
	char* temp_str1 = calloc(strlen(str1), sizeof(char));
	char* temp_str2 = calloc(strlen(str2), sizeof(char));
	strcpy(temp_str1, str1);
	strcpy(temp_str2, str2);
	quickSort(temp_str1, sizeof(char), 0, (int) strlen(str1) - 1);
	quickSort(temp_str2, sizeof(char), 0, (int) strlen(str2) - 1);
	printf("%s %s\n", temp_str1, temp_str2);
	int res = strcmp(temp_str1, temp_str2);
	free(temp_str1);
	free(temp_str2);
	return (res);
}
// end zadatak1

// zadatak2

int zadatak2(long* arr, int len) {
	long max = 0;
	quickSort(arr, sizeof(*arr), 0, len);
	for (int i = 0; i < len - 1; ++i) {
		printf("%ld ", arr[i]);
		long res = labs(arr[i] - arr[i + 1]);
		if (res <= max) {
			max = res;
		}
	}
	printf("\n");
	return max;
}

// end zadatak2


int main() {
	// zadatak1
	char* str1 = "listen";
	char* str2 = "silent";
	char* str3 = "hello";
	char* str4 = "world";
	printf("%s & %s %s anagrams\n", str1, str2, (zadatak1(str1, str2) == 0 ? "are" : "are not"));
	printf("%s & %s %s anagrams\n", str3, str4, (zadatak1(str3, str4) == 0 ? "are" : "are not"));
	// end zadatak1


	// zadatak2
	#define z2max 255
	long* arr = (long*) calloc(z2max, sizeof(long));
	for (int i = 0; i < z2max; ++i) {
		arr[i] = random() % 1024;
		printf("%ld ", arr[i]);
	}
	printf("\n");
	int max = zadatak2(arr, z2max);
	printf("%d\n", max);
	free(arr);
	// end zadatak2

	return 0;
}