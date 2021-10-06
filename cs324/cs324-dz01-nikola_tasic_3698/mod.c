#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int main(void) {
	char buf[16];

	printf("Enter a number: ");
	fgets(buf, 16, stdin);
	long num = strtol(buf, NULL, 10);

	double log2    = log(2);
	double log_num = log(num);

	int res = (int)(log_num / log2) == log_num / log2;

	printf("%ld is%s a power of 2\n", num, res ? "" : " NOT");
}
