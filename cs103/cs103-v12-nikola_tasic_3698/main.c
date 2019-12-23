#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>


static char* get_input() {
	#define BUF_SIZE 256
	static char* buf = NULL;
	unsigned long len;
	if (buf != NULL) {
		memset(buf, 0, BUF_SIZE);
	} else {
		buf = (char*) calloc(BUF_SIZE, sizeof(char));
	}

	do {
		printf("Enter a string: ");
		fgets(buf, BUF_SIZE - 1, stdin);
		len = strnlen(buf, BUF_SIZE);
		assert(len < BUF_SIZE);
	} while (len <= 1);
	buf[len - 1] = '\0';
	return buf;
	# undef BUF_SIZE
}

// ZADATAK 1
char* get_nondesc_substr(const char* str) {
	#define BUF_SIZE 256
	assert(strnlen(str, BUF_SIZE) < BUF_SIZE);
	static char* buf = NULL;

	if (buf != NULL) {
		memset(buf, 0, BUF_SIZE);
	} else {
		buf = (char*) calloc(BUF_SIZE, sizeof(char));
	}

	int max_size = 0;
	int size = 0;
	const char* strptr = str;
	const char* subptr = str;

	while (*strptr != '\0') {
		while ((*subptr > *(subptr - 1)) && *subptr != '\0') {
			subptr++;
			size++;
		}
		if (size > max_size) {
			strncpy(buf, str, size);
			max_size = size;
		}
		strptr++;
		subptr = strptr;
		size = 0;
	}

	return buf;
	#undef BUF_SIZE
}

// ZADATAK 2
char* get_nondesc_subseq(const char* str) {
	#define BUF_SIZE 256
	assert(strnlen(str, BUF_SIZE) < BUF_SIZE);
	static char* buf = NULL;
	char* temp = (char*) calloc(BUF_SIZE, sizeof(char));

	if (buf != NULL) {
		memset(buf, 0, BUF_SIZE);
	} else {
		buf = (char*) calloc(BUF_SIZE, sizeof(char));
	}

	int size = 0;
	const char* strptr = str;
	const char* subptr = str;
	char* bufptr = temp;

	while (*strptr != '\0') {
		while (*subptr != '\0') {
			if (*subptr > *(bufptr - 1)) {
				*bufptr = *subptr;
				bufptr++;
			}
			subptr++;
			size++;
		}
		if (strnlen(temp, BUF_SIZE) > strnlen(buf, BUF_SIZE)) {
			strncpy(buf, temp, size);
		}
		strptr++;
		subptr = strptr;
		memset(temp, 0, BUF_SIZE);
		bufptr = temp;
		size = 0;
	}

	return buf;
	#undef BUF_SIZE
}

// ZADATAK 11

int chrcmp(const void* c1, const void* c2) {
	if (*(char*) c1 > *(char*) c2) {
		return 1;
	} else if (*(char*) c1 > *(char*) c2) {
		return -1;
	} else {
		return 0;
	}
}

int isrot(char const* str1, char const* str2) {
	#define BUF_SIZE 256
	assert(strnlen(str1, BUF_SIZE) < BUF_SIZE);
	assert(strnlen(str2, BUF_SIZE) < BUF_SIZE);
	int retval;
	char* buf1 = (char*) calloc(BUF_SIZE, sizeof(char));
	char* buf2 = (char*) calloc(BUF_SIZE, sizeof(char));

	strncpy(buf1, str1, BUF_SIZE);
	strncpy(buf2, str2, BUF_SIZE);

	qsort(buf1, strnlen(buf1, BUF_SIZE), sizeof(char), chrcmp);
	qsort(buf2, strnlen(buf2, BUF_SIZE), sizeof(char), chrcmp);

	retval = strncmp(buf1, buf2, BUF_SIZE) == 0;

	free(buf1);
	free(buf2);

	return retval;
	#undef BUF_SIZE

}

// ZADATAK 3
int cmp(const char* str1, const char* str2) {
	while ((*str1 != '\0') && (*str2 != '\0')) {
		if (*str1 != *str2) {
			return 0;
		}
		str1++;
		str2++;
	}
	return *str2 == '\0';
}

int indexof(const char* str, const char* substr) {
	const char* subptr = substr;
	const char* strptr = str;
	while (*strptr != '\0') {
		if ((*strptr != '\0' && *subptr != '\0') && cmp(strptr, subptr)) {
			return (int) (strptr - str);
		}
		strptr++;
	}

	return -1;
}

int main() {
	char* input = NULL;
	char* substr = NULL;
	printf("\"Welcome\" should return \"Wel\" and \"Welo\".\n");
	input = get_input();
	printf("%s\n", input);
	substr = get_nondesc_substr(input);
	printf("%s\n", substr);

	substr = get_nondesc_subseq(input);
	printf("%s\n", substr);

	printf("example & ampleex - %s\n", isrot("example", "ampleex") ? "true" : "false");
	printf("indexof(\"Welcome to Java!\", \"come\") -> %d\n", indexof("Welcome to Java!", "come"));
	printf("indexof(\"Some random string.\", \"index\") -> %d\n", indexof("Some random string.", "index"));

	return 0;
}
