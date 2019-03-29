// 39. Napisati funkciju koja izdvaja sve neparne elemente unetog niza
// u drugi dinamički alocirani niz. Pokazivač na dinamički alocirani niz
// vratiti kao rezultat funkcije.
// Kao argument funkcije vratiti, korišćenjem pokazivača, I dimenziju novonastalog niza.
// Testirati funkciju u glavnom programu.

#include <stdio.h>
#include <stdlib.h>
#define INPMAX 5

int* izdvoji(int[], int, int*);

void main(void)
{
    int input;
    int oddlen = 0;
    int arr[10];

    for (int i = 0; i < INPMAX; i++) {
        printf("unestite element: ");
        scanf("%d", &input);
        arr[i] = input;
    }

    // for (int j = 0; j < INPMAX; j++) {
    //     printf("%d\n", arr[j]);
    // }
    int* odd = izdvoji(arr, INPMAX, &oddlen);
    for (int j = 0; j < oddlen; j++) {
        printf("%d\n", odd[j]);
    }
    free(odd);
}
// Kao argument funkcije vratiti, korišćenjem pokazivača, I dimenziju novonastalog niza.
// ja sam ovako shvatio ne znam da li je ispravno
int* izdvoji(int arr[], int len, int* len2)
{
    int* newarr = (int*)malloc(sizeof(int));
    int count = 0;
    for (int i = 0; i < len; i++) {
        if (arr[i] % 2 != 0) {
            newarr[count] = arr[i];
            count++;
            newarr = realloc(newarr, count);
        }
    }
    *len2 = count;
    return newarr;
}
