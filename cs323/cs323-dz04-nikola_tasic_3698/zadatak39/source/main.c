#include <stdio.h>
#include <string.h>
void main(void)
{
    int n;
    char str[20];
    char out[20 * 10 + 10];
    printf("Unesite broj ponavljanja: ");
    scanf("%d", &n);
    if (n > 10) {
        printf("Broj mora biti manji od 10.\n");
        return;
    }
    printf("Unesite string: ");
    scanf("%s", &str);
    strcat(str, "_");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < strlen(str); j++) {
            out[i * strlen(str) + j] = str[j];
        }
    }
    for (int i = strlen(str) * n - 1; i < strlen(out); i++) {
        out[i] = ' ';
    }
    printf("%s\n", out);
}