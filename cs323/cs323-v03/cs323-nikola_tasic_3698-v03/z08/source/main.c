#include <stdio.h>
#define LEN 10
void sort(int*, int);

int main(void)
{
    int arr[] = {
        19,
        29,
        47,
        17,
        0,
        12,
        42,
        124,
        46,
        10
    };
    sort(arr, LEN);
    for (int i = 0; i < LEN; i++) {
        printf("%d ", arr[i]);
    }
}

void sort(int arr[], int len)
{
    for (int i = 0; i < len - 1; i++) {
        for (int j = 1; j < len; j++) {
            if (arr[i] < arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}