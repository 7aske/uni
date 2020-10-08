#include <stdio.h>
#include <string.h>

#define CHARS 256
static int prime = 37;

int rabinkarp(char const* search, char const* str) {
	int len1 = (int) strlen(search);
	int len2 = (int) strlen(str);
	int i, j;
	int search_hash = 0;
	int str_hash = 0;
	int h = 1;

	for (i = 0; i < len1 - 1; i++) {
		h = (h * CHARS) % prime;
	}

	for (i = 0; i < len1; i++) {
		search_hash = (CHARS * search_hash + search[i]) % prime;
		str_hash = (CHARS * str_hash + str[i]) % prime;
	}

	for (i = 0; i <= len2 - len1; i++) {
		if (search_hash == str_hash) {
			for (j = 0; j < len1; j++) {
				if (str[i + j] != search[j])
					break;
			}
			if (j == len1) {
				return i;
			}
		}
		if (i < len2 - len1) {
			str_hash = (CHARS * (str_hash - str[i] * h) + str[i + len1]) % prime;
			if (str_hash < 0) {
				str_hash = (str_hash + prime);
			}
		}
	}
	return -1;
}

int main() {
	char const* string = "kadsuprelaziliprekopreprekenasmejaseprepredeno";
	char const* search = "prepreden";

	printf("Substring found at %d\n", rabinkarp(search, string));
	return 0;
}