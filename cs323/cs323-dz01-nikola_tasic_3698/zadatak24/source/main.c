// 24. Računanje bruto plate. Treba napraviti program u kome korisnik upisuje platu u dinarima (npr.
// 45200), a program automatski izračunava bruto platu. Bruto plata se računa kao
// Plata + (Plata * 0.70).
#include <stdio.h>

int main(int argc, int *argv[])
{
    float k = 0.7;
    float p;
    printf("Unesite platu: ");
    scanf("%f", &p);
    printf("Bruto plata: RSD %.2f\n", p + p * k);
    return 0;
}