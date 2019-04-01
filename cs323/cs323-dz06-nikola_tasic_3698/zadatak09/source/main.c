/*9. Napisati program koji učitava n strukturnih promenljivih ucenik (ime, adresa, razred, odeljenje)
i zapisuje podatke u datoteku ucenik.txt. Podaci jednog učenika su u jednom redu. Slika ispod
ilustruje način upisivanja u datoteci.*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student {
    char name[35];
    char address[50];
    int year;
    int class;
};

void main(void)
{
    const char* fname = "ucenik.txt";
    int stud_s;
    struct student* students;
    struct student* s;
    FILE* f = NULL;
    int c;

    printf("Enter number of students: ");
    scanf("%d", &stud_s);

    students = (struct student*)calloc(stud_s, sizeof(struct student));
    for (int i = 0; i < stud_s; i++, students++) {
        char name[35], address[50];
        int year, class;

        memset(name, 0, 35);
        memset(address, 0, 50);
        // bagiuje input preskace priv fgets tako da ovo
        // upije to prvo preskakanje ::(
        getchar();

        printf("Enter name: ");
        fgets(name, 35, stdin);
        // sklanjanje \n
        strtok(name, "\n");

        printf("Enter address: ");
        fgets(address, 50, stdin);
        // sklanjanje \n
        strtok(address, "\n");

        printf("Enter year: ");
        scanf("%d", &year);

        printf("Enter class: ");
        scanf("%d", &class);

        strcpy(students->name, name);
        strcpy(students->address, address);
        students->year = year;
        students->class = class;
    }
    students -= stud_s;

    f = fopen(fname, "w+");
    if (f == NULL) {
        perror("Error opening file");
        exit(-1);
    }
    for (int j = 0; j < stud_s; j++, students++) {
        printf("0x%x name: %s\n", &students->name, students->name);
        printf("0x%x addr: %s\n", &students->address, students->address);
        printf("0x%x year: %d\n", &students->year, students->year);
        printf("0x%x clss: %d\n\n", &students->class, students->class);
        fprintf(f, "%35s %50s %3d%3d\n", students->name, students->address, students->year, students->class);
    }
    students -= stud_s;

    if (f != NULL) {
        fclose(f);
    }

    free(students);
}