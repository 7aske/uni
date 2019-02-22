// 9. Ako se unosi stranica kvadrata a, prikazati poruku:
// Unesi stranicu kvadrata: __
// P = a^2 = __^2 = __ cm2
// O = 4*a = 4 * __ = __cm
#include <stdio.h>

int main(int argc, int *argv[])
{
    float a;
    float r;
    printf("Unesite stranicu kvadrata: ");
    scanf("%f", &a);
    if (a > 0)
    {
        r = a * a;
        printf("P = a^2 = %.2f^2 = %.2f cm^2\n", a, r);
        r = 4 * a;
        printf("O = 4*a = 4 * %.2f = %.2f cm\n", a, r);
        return 0;
    } 
    else 
    {
        printf("Duzina mora biti pozitivna\n");
        return 1;
    }
}