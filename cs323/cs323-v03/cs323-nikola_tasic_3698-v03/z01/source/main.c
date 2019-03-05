#include <stdio.h>
#include <stdlib.h>
#define ARR_LEN 10
int main(void)
{
    int arr1[ARR_LEN];
    int arr2[ARR_LEN];
    int oddc = 1;
    for (int i = 0; i < ARR_LEN; i++, oddc += 2) {
        arr1[i] = oddc;
        arr2[i] = arr1[i] * 2;
        printf("%d <-> %d\n", arr1[i], arr2[i]);
    }
}