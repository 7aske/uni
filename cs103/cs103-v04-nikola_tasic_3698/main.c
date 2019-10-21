#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// utils
void swap(char* a, char* b) {
	char t = *a;
	*a = *b;
	*b = t;
}

int partition(char* arr, int low, int high) {
	char pivot = arr[high];
	int i = low - 1;

	for (int j = low; j <= high - 1; j++) {
		if (arr[j] < pivot) {
			i++;
			swap(&arr[i], &arr[j]);
		}
	}
	swap(&arr[i + 1], &arr[high]);
	return i + 1;
}

void quickSort(char* arr, int low, int high) {
	if (low < high) {
		int pi = partition(arr, low, high);
		quickSort(arr, low, pi - 1);
		quickSort(arr, pi + 1, high);
	}
}
// end utils


// zadatak1
int zadatak1(char* str1, char* str2) {
	char* temp_str1 = calloc(strlen(str1), sizeof(char));
	char* temp_str2 = calloc(strlen(str2), sizeof(char));
	strcpy(temp_str1, str1);
	strcpy(temp_str2, str2);
	quickSort(temp_str1, 0, (int) strlen(str1) - 1);
	quickSort(temp_str2, 0, (int) strlen(str2) - 1);
	int res = strcmp(temp_str1, temp_str2);
	free(temp_str1);
	free(temp_str2);
	return (res);
}
// end zadatak1

// zadatak2

int zadatak2(char* arr, int len) {
	int max = 0;
	quickSort(arr, 0, len);
	for (int i = 0; i < 127; ++i) {
		int res = abs(arr[i] - arr[i + 1]);
		if (res >= max) {
			max = res;
		}
	}
	return max;
}

// end zadatak2

int main() {
	// zadatak1
	char* str1 = "listen";
	char* str2 = "silent";
	printf("%s & %s %s anagrams\n", str1, str2, (zadatak1(str1, str2) == 0 ? "are" : "are not"));
	// end zadatak1


	// zadatak2
	char* arr = (char*) malloc(128);
	arr[127] = '\0';
	for (int i = 0; i < 128; ++i) {
		arr[i] = random() % 128;
	}
	int max = zadatak2(arr, 128);
	printf("%d\n", max);
	// end zadatak2

	return 0;
}