#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// zadatak 4

int isDividedBy(int x, int n) {
	return n / x == n / (double) x;
}

int divisorSum(int n) {
	int sum = 0;
	for (int i = 1; i < n; ++i) {
		if (isDividedBy(i, n)) {
			sum += i;
		}
	}
	return sum;
}

// zadatak 5

char* longestPrefix(char* str1, char* str2) {
	if (!str1 || !str2) {
		return NULL;
	}
	char* ret = NULL;
	char* p1 = str1;
	char* p2 = str2;

	int n = 0;
	while (*p1++ == *p2++) {
		n++;
	}

	if (n > 0) {
		ret = (char*) malloc(n + 1);
		memcpy(ret, str1, n);
	}

	return ret;
}

// zadatak 2
int isMarkovMatrix(double const* mat, int n) {
	for (int i = 0; i < n; ++i) {
		double sum = 0;
		for (int j = 0; j < n; ++j) {
			if (mat[i * n + j] < 0) return 0;
			sum += mat[j * n + i];
		}
		if (sum > 1) {
			return 0;
		}
	}
	return 1;
}

int main() {
	int mat_size = 3;
	double mat[] = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
	printf("%d\n", isMarkovMatrix(mat, mat_size));
	printf("%s\n", longestPrefix("division", "divisor"));
	printf("%d\n", divisorSum(10));
	return 0;
}


