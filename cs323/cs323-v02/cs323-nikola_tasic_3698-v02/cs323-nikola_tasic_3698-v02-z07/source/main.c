// Zadatak 7. Kreirati C aplikaciju koja za unetu dužinu stranica pravougaonika ispisuje njegovu
// površinu i obim. Razdvojite izračunavanje u posebne funkcije.
#include <stdio.h>

int mul(int, int);

int main(void)
{
    int a, b, c, p;
    printf("Enter a and b (a b): ");
    scanf("%d %d", &a, &b);
    c = mul(2, a) + mul(2, b);
    p = mul(a, b);
    printf("Circumference: %d\nArea: %d\n", c, p);
    return 0; 
}

int mul(int a, int b)
{
    return a * b;
}