// Firma se bavi proizvodnjom alata. O svakom alatu čuvamo informacije o imenu alata, serijskom
// broju kao i ceni. Klasa Alat treba biti kreirana kao apstraktna i da sadrži i čisto virtuelnu funkciju
// Info(). Alati mogu biti Ručni, koji kao dodatak imaju i podatak: minimalne godine za korišćenje.
// Klasa Ručni Alat treba da sadrži i funkciju Info() koja osim svih podataka o alatu štampa i
// podatak o godinama za korišćenje.

#include <iostream>
#include "HandTools.h"

int main() {
	HandTools ht0;
	Tools* ht0ptr = &ht0;

	ht0.setName("Hammer");
	ht0.setSn("q3rtsgd3r1");
	ht0.setMinAge(18);
	ht0.setPrice(4000);

	std::cout << "standard Info() call: \n";
	ht0.Info();
	std::cout << "using base class ptr: \n";
	ht0ptr->Info();

	return 0;
}