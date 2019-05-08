#include <iostream>
#include "Vozac.h"
#include "Vozilo.h"

int main() {
	Vozac pera("pera", "peric", 54);
	Vozilo kamion("FAP Kamion", "PK-1242", pera);
	cout << pera.getIme() << " " << pera.getPrezime() << " vozi " << kamion.getNaziv() << "\n";
	return 0;
}