// Zadatak 1. Napraviti dve klase. Prva klasa je Vozac koja sadrži ime i prezime i broj godina
// vozača, a druga klasa je Vozilo koja sadrži naziv, registarski broj vozila i vozača (objekat klase
// Vozac). Prikazati rad klasa korišćenjem pokazivača na klase.
#ifndef VOZILO_H
#define VOZILO_H
#pragma once

#include "Vozac.h"


using namespace std;

class Vozilo {
public:
	explicit Vozilo() {};

	Vozilo(const char* naziv, const char* regbr, Vozac &vozac) :
			naziv(naziv), regbr(regbr), vozac(vozac) {};

	const string &getNaziv() const {
		return naziv;
	}

	void setNaziv(const string &naziv) {
		Vozilo::naziv = naziv;
	}

	const string &getRegbr() const {
		return regbr;
	}

	void setRegbr(const string &regbr) {
		Vozilo::regbr = regbr;
	}

	const Vozac &getVozac() const {
		return vozac;
	}

	void setVozac(const Vozac &vozac) {
		Vozilo::vozac = vozac;
	}

private:
	string naziv;
	string regbr;
	Vozac vozac;
};

#endif