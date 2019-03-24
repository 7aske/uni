#include <stdio.h>

void izbaci(int[], int*, int);

void main(void)
{
    int n = 20, a = 0, x;
    int arr[20];
    printf("Unesite velicinu niza: ");
    scanf("%d", &a);
    if (a > 20) {
        printf("Duzina mora ne veca od 20\n");
        return;
    }
    for (int i = 0; i < a; i++) {
        printf("Unesite vrednost elementa [%d]: ", i);
        scanf("%d", &arr[i]);
    }
    printf("Unesite vrednost za izbacivanje: ");
    scanf("%d", &x);
    izbaci(arr, &a, x);
    for (int i = 0; i < a; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void izbaci(int arr[], int* a, int x)
{
    int c = *a;
    int newc = c;
    for (int i = 0; i < c; i++) {
        if (arr[i] == x) {
            arr[i] = arr[i + 1];
            i++;
            newc--;
        }
    }
    *a = newc;
}