// Zadatak 1. Napraviti dve klase. Prva klasa je Vozac koja sadrži ime i prezime i broj godina
// vozača, a druga klasa je Vozilo koja sadrži naziv, registarski broj vozila i vozača (objekat klase
// Vozac). Prikazati rad klasa korišćenjem pokazivača na klase.
#ifndef VOZAC_H
#define VOZAC_H
using namespace std;

class Vozac {
public:
	Vozac() = default;;;

	Vozac(const char* ime, const char* prezime, int brgod) :
			ime(ime), prezime(prezime), brgod(brgod) {};

	const string &getIme() const {
		return ime;
	}

	void setIme(const string &ime) {
		Vozac::ime = ime;
	}

	const string &getPrezime() const {
		return prezime;
	}

	void setPrezime(const string &prezime) {
		Vozac::prezime = prezime;
	}

	int getBrgod() const {
		return brgod;
	}

	void setBrgod(int brgod) {
		Vozac::brgod = brgod;
	}

private:
	string ime;
	string prezime;
	int brgod;
};

#endif