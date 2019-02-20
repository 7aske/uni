#include <stdio.h>

int main() {
    int choice;
    printf("Enter a number(1-7): ");
    scanf("%d", &choice);
    switch (choice) {
        case 1:
            printf("%s\n", "Monday");
            break;
        case 2:
            printf("%s\n", "Tuesday");
            break;
        case 3:
            printf("%s\n", "Wednesday");
            break;
        case 4:
            printf("%s\n", "Thursday");
            break;
        case 5:
            printf("%s\n", "Friday");
            break;
        case 6:
            printf("%s\n", "Saturday");
            break;
        case 7:
            printf("%s\n", "Sunday");
            break;
        default:
            printf("Invalid input\n");
            return 1;

    }
    return 0;
}