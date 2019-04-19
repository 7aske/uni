//16. Napisati program kojim se učitavaju N fudbalskih ekipa. Svaka fudbalska ekipa ima podatke: naziv, broj
//        bodova, gol razlika. Odrediti ekipu sa najboljim skorom i njene podatke odštampati na ekran. Ukoliko
//        dve ekipe imaju isti broj bodova gleda se bolja gol razlika. U fajlu “tabela.txt” odštampati trenutnu
//tabelu. Format fajla treba da bude
//        RedBR Ekipa GolRazlika BrojBodova
//          1 Chelsea+37 70
//          2 Arsenal +30 63
//Podatke klase smestiti u privatnoj (private) sekciji a metode klase (get/set metode za pristup privatnim
//članovima, i ostale metode) u javnoj (public) sekciji. Getter metode načiniti konstantnim (const).
//Deklaraciju klase smestiti u fajlu zaglavlja (npr. ImeKlase.h), a definicije funkcija članica klase napisati u
//fajlu ImeKlase.cpp.
//Za klasu kreirati podrazumevajući konstruktor, konstruktor sa parametrima i konstruktor kopiranja.
//Konstruktor sa parametrima implementirati korišćenjem pokazivača this. Navesti i primer konstruktora
//sa parametrima koji koristi listu za inicijalizaciju. Kreirati i destruktor klase u kome se ispisuje poruka
//“Objekat je uništen”.
//Glavni program napisati u main.cpp fajlu.
#include <iostream>
#include <fstream>
#include <string.h>
#include <sys/stat.h>
#include <vector>

#include "team.cpp"
#include "table.cpp"

#define FNAME_S 32

using namespace std;

void panic(char* msg, int code);

int main(int argc, char** argv) {
    char filename_in[FNAME_S];
    char filename_out[FNAME_S] = "table.txt";

    Table* table = new Table;

    if (argv[1] != nullptr) {
        strncpy(filename_in, argv[1], FNAME_S);
        struct stat temp{};
        if (stat(filename_in, &temp) == -1) {
            panic((char*) "ERROR", ENOENT);
        }
    } else {
        panic((char*) "ERROR", EINVAL);
    }


    string line;
    ifstream fp_in(filename_in);
    if (fp_in.is_open()) {
        while (getline(fp_in, line, '\n')) {

            if (strncmp(line.c_str(), "#", 1) != 0) {
                char* tok = strtok((char*) line.c_str(), ",");

                Team* temp = new Team;
                int field_c = 0;

                while (tok != nullptr) {
                    switch (field_c) {
                        case 0:
                            temp->setName(tok);
                            break;
                        case 1:
                            temp->setDiff(atoi(tok));
                            break;
                        case 2:
                            temp->setPts(atoi(tok));
                            break;
                    }
                    field_c++;
                    tok = strtok(nullptr, ",");
                }
                table->addTeam(*temp);
                delete temp;
            }
        }
        cout << *table << "\n";
    } else {
        panic((char*) "ERROR", EIO);
    }
    ofstream fp_out(filename_out, fstream::out);
    if (fp_in.is_open()) {
        fp_out << *table;
    } else {
        panic((char*) "ERROR", EIO);

    }
    delete table;
    return 0;
}


void panic(char* msg, int code) {
    errno = code;
    perror(msg);
    exit(-1);
}

