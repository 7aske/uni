/*19. Definisati strukturu firma koja sadrži sledeće podatke: ime firme,
vrednost ugovora. Program sadrži funkciju Ugovori koja dobija naziv firme
a vraća ukupnu vrednost svih ugovora potpisanih sa tom firmom.
U glavnom programu učitati podatke i za svaku firmu ispisati ukupnu vrednost ugovora.*/
#include <stdio.h>
#include <string.h>
#define MAXF 2

struct firma {
    char ime[30];
    double ugovori;
};
double ugovori(char[], struct firma[], int);

void main(void)
{
    char input[30];
    struct firma firme[MAXF];
    for (int i = 0; i < MAXF; i++) {
        printf("unesite ime firme: ");
        scanf("%s", &input);
        strcpy(firme[i].ime, input);

        printf("unesite vrednost ugovora: ");
        scanf("%s", &input);
        sscanf(input, "%lf", &(firme[i]).ugovori);
    }

    printf("unesite ime firme cije ugovore zelite da vidite: ");
    scanf("%s", &input);
    printf("%lf\n", ugovori(input, firme, MAXF));
}

double ugovori(char ime[], struct firma firme[], int max)
{
    for (int i = 0; i < max; i++) {
        if (strcmp(ime, firme[i].ime) == 0) {
            return firme[i].ugovori;
        }
    }
    printf("firma %s ne postoji", ime);
    return 0;
}
