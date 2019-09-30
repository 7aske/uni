#include <iostream>
#include <cstdlib>
#include <cstring>

using namespace std;

// zadatak 4

bool isDividedBy(int x, int n) {
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
		return nullptr;
	}
	char* ret = nullptr;
	char* p1 = str1;
	char* p2 = str2;

	int n = 0;
	while (*p1++ == *p2++) {
		n++;
	}

	if (n > 0) {
		ret = (char*) malloc(n + 1);
		mempcpy(ret, str1, n);
	}

	return ret;
}

// zadatak 2
bool isMarkovMatrix(double* mat, int n) {
	for (int i = 0; i < n; ++i) {
		double sum = 0;
		for (int j = 0; j < n; ++j) {
			if (mat[i * n + j] < 0) return false;
			sum += mat[j * n + i];
		}
		if (sum > 1){
			return false;
		}
	}
	return true;
}

int main() {
	int mat_size = 3;
	double mat[] = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
	cout << isMarkovMatrix(mat, mat_size) << "\n";
	cout << longestPrefix((char*) "division", (char*) "divisor") << "\n";
	cout << divisorSum(10) << "\n";
	return 0;
}


