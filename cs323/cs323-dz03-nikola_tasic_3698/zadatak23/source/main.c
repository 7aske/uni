// 23. Napisati program koji umeće string s2 u string s1 počev od pozicije p.
#include <stdio.h>
#include <string.h>
char* insert(char*, char*, char*, int);
int main(void)
{
    char *str1 = "something", *str2 = "some", str3[50];
    printf("%s\n", insert(str1, str2, str3, 4));
}

char* insert(char* str1, char* str2, char* str3, int p)
{
    strncpy(str3, str1, p);
    str3[p] = '\0';
    strcat(str3, str2);
    strcat(str3, str1 + p);
}