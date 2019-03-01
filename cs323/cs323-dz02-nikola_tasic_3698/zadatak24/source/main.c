#include <stdio.h>

unsigned long long int fact(int);

int main(void)
{
    int num;
    printf("Enter a number: ");
    scanf("%d", &num);
    if (num > 0 && num < 66) {
        unsigned long long int out = fact(num);
        printf("Result: %llu\n", out);
        return 0;
    } else {
        printf("Number must be positive and below 66\n");
        return 1;
    }
}

unsigned long long int fact(int num)
{
    unsigned long long int out = 1;
    while (num != 1) {
        out *= num;
        num--;
    }
    return out;
}