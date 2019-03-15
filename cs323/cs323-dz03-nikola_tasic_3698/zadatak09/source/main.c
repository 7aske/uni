//9. Formirati slučajnim izborom niz a[] od 100 elemenata iz intervala od 1 - 200. Iz takvog niza
//  izdvojiti podniz b[] koji će sadržati samo dvocifrene brojeve niza a[].
#include <stdio.h>
#include <stdlib.h>
#define MAX 200
int main(void)

{
    int arr[MAX];
    int arr2[MAX];
    for (int i = 0; i < MAX; i++) {
        arr[i] = rand() % 200;
        // printf("%d\n", arr[i]);
    }
    int c = 0;
    for (int j = 0; j < MAX; j++) {
        int num = arr[j];
        if (num > 9 && num < 100) {
            arr2[c] = num;
            printf("%d\n", arr2[c]);
            c++;
        }
    }
}