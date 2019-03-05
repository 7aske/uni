#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#define LC_MIN 97
#define LC_MAX 122
#define UC_MIN 65
#define UC_MAX 90

int main(void)
{
    for (int i = 0; i < 10; i++) {
        if (i == 0) {
            putchar(rand() % ((UC_MAX + 1 - UC_MIN) + UC_MIN));
        } else {
            putchar(rand() % ((LC_MAX + 1 - LC_MIN) + LC_MIN));
        }
    }
}