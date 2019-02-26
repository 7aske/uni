#include <stdio.h>

int main(void) {

    int m,d;

    printf("Enter date (dd, mm): ");
    scanf("%d %d", &d, &m);

    if (d < 1 || d > 31 || m < 1 || m > 12)
    {
        printf("Invalid date\n");
        return 0;
    }
    if (m < 9)
    {
        if (m == 2)
        {
            if (d > 29)
            {
                printf("Invalid date\n");
                return 0;
            }

        }
        else if (m % 2 == 0 && m != 8)
        {
            if (d > 30)
            {
                printf("Invalid date\n");
                return 0;
            }
        }
    }
    else
    {
        if (m % 2)         
            if (d > 30)
            {
                printf("Invalid date\n");
                return 0;
            }
    }

    printf("Your zodiac sign is ");
    switch (m)
    {
        case 3:
            if (d >= 20)
                printf("Aries");
            else if(d < 20)
                printf("Pisces");
            break;
        case 4:
            if (d >= 21)
                printf("Taurus");
            else
                printf("Aries");
            break;
        case 5:
            if (d >= 21)
                printf("Gemini");
            else
                printf("Taurus");
            break;
        case 6:
            if (d >= 21)
                printf("Cancer");
            else
                printf("Gemini");
            break;
        case 7:
            if (d >= 23)
                printf("Leo");
            else
                printf("Gemini");
            break;
        case 8:
            if (d >= 23)
                printf("Virgo");
            else
                printf("Leo");
            break;
        case 9:
            if (d >= 23)
                printf("Libra");
            else
                printf("Virgo");
            break;
        case 10:
            if (d >= 23)
                printf("Scorpio");
            else
                printf("Libra");
            break;
        case 11:
            if (d >= 23)
                printf("Sagittarius");
            else
                printf("Scorpio");
            break;
        case 12:
            if (d >= 20)
                printf("Capricorn");
            else
                printf("Sagittarius");
            break;
        case 1:
            if (d >= 20)
                printf("Aquarius");
            else
                printf("Capricorn");
            break;
        case 2:
            if (d >= 20)
                printf("Pisces");
            else
                printf("Aquarius");
            break;
        default:
            printf("Invalid date");
            break;
    }
    printf("\n");
    return 0;
}