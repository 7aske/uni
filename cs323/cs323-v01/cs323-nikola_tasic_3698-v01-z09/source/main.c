#include <stdio.h>

int main() {
    float money;
    float rate;
    float earned;
    printf("Money rate(whitespace delimited) ");
    scanf("%f %f", &money, &rate);
    earned = money * (rate + 1) * 12 - money * 12;
    printf("Money earned: %.3f\n", earned);
    printf("Money earned: %.5f\n", earned);
    printf("Money earned: %e\n", earned);
}

