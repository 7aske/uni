// Zadatak 7. Kreirati C aplikaciju koja za unetu dužinu stranica pravougaonika ispisuje njegovu
// površinu i obim. Razdvojite izračunavanje u posebne funkcije.
#include <stdio.h>

int pwr(int, int);

int main(void)
{
    int a, b;
    printf("Enter base and power (x y): ");
    scanf("%d %d", &a, &b);
    printf("Result is: %d\n", pwr(a, b));
    return 0; 
}

int pwr(int a, int b)
{
    int out = 1;
    for(int i = 0; i < b; i++)
    {
        out *= a;
    }
    return out;
}