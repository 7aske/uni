#include <stdio.h>

void check_char(char);
float get_stats(int);
void prompt_inp();

int a, e, i, o, u, tot;
char inp;

int main()
{
    prompt_inp();
    printf("%5.2f%% A\n%5.2f%% E\n%5.2f%% I\n%5.2f%% O\n%5.2f%% U\n", get_stats(a), get_stats(e), get_stats(i), get_stats(o), get_stats(u));
    return 0;
}

void prompt_inp()
{
    if (inp != 10)
        printf("> ");
    inp = getchar();
    if (inp != 10 && inp != 36)
        check_char(inp);
    if (inp != 36) {
        prompt_inp();
    }
}

void check_char(char c)
{
    switch (c) {
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
    }
    tot++;
}
float get_stats(int x)
{
    float out;
    if (tot > 0) {
        out = ((float)x / (float)tot) * 100;
    }
    return out;
}