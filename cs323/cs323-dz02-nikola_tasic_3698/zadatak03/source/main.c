#include <stdio.h>

void check_char(char*);
float get_stats(int);

int a, e, i, o, u, tot;

int main()
{
    char inp;
    do {
        if (inp != 10) {
            printf(">");
            inp = getchar();
            tot++;
            check_char(&inp);
        } else {
            inp = 0;
        }
    } while (inp != 36);
    printf("%.2f\n", get_stats(a));
    return 0;
}

void check_char(char* c)
{
    // printf("%d\n", *c);
    switch (*c) {
    case 'A':
        a++;
        break;
    case 'a':
        a++;
        break;
    case 'E':
        e++;
        break;
    case 'e':
        e++;
        break;
    case 'I':
        i++;
        break;
    case 'i':
        i++;
        break;
    case 'O':
        o++;
        break;
    case 'o':
        o++;
        break;
    case 'U':
        u++;
        break;
    case 'u':
        u++;
        break;
    case '$':
        return;
    }
}

float get_stats(int x)
{
    float out;
    if (tot > 0) {
        printf("%d %d\n", x, tot);
        out = (x / tot) * 100;
    }
    return out;
}