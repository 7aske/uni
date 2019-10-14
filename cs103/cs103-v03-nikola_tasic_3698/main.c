#include <stdio.h>
#include <string.h>

// zadatak1

double zadatak1(int i) {
	if (i == 1)
		return 1 / 3.0;
	return ((double) i / ((2 * i + 1) + 1)) + zadatak1(i - 1);
}

// end zadatak1

// zadatak2

void zadatak2_util(char* front, char* back) {
	if (front == back) {
		printf("%c\n", *back);
		return;
	}
	printf("%c", *back--);
	zadatak2_util(front, back);
}

void zadatak2(char* str) {
	char* front = str;
	char* back = strchr(str, '\0');
	zadatak2_util(front, --back);
}

// end zadatak2

// zadatak7

int zadatak7_util(char* front, char* back) {
	if (*front == *back)
		return zadatak7_util(++front, --back);
	if (back < front)
		return 1;
	return 0;
}


int zadatak7(char* str) {
	char* front = str;
	char* back = strchr(str, '\0');
	zadatak7_util(front, --back);
}

// end zadatak7

// zadatak5


void zadatak5_util(char* str, int x, int l) {
	if (x == l)
		printf("%s", str);
	else {
		for (int i = x; i <= l; i++) {
			char temp = str[x];
			str[x] = str[i];
			str[i] = temp;

			zadatak5_util(str, x + 1, l);

			temp = str[x];
			str[x] = str[i];
			str[i] = temp;

		}
	}
}

void zadatak5(char* str) {
	zadatak5_util(str, 0, strlen(str) - 1);
}

// end zadatak5

int main() {
	printf("%.2f\n", zadatak1(20));
	char* str1 = "test";
	char* str2 = "anavolimilovana";
	zadatak2(str1);
	zadatak2(str2);

	printf("%s - %d\n", str1, zadatak7(str1));
	printf("%s - %d\n", str2, zadatak7(str2));

	// Segfault kod swap-a
	zadatak5(str1);

	return 0;
}