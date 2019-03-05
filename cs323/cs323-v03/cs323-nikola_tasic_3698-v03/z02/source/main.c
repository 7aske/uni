#include <stdio.h>
#include <stdlib.h>
#define ARR_LEN 100
int main(void)
{
    int arr1[ARR_LEN];
    for (int i = 0; i < ARR_LEN; i++) {
        arr1[i] = 2 * i * 2 * i;
        printf("%d\n", arr1[i]);
    }
}