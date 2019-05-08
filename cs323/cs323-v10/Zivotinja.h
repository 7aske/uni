Zadatak 4. Napraviti klasu Životinja koja od atributa ima: ime životinje i rasu. Napraviti
metodu zvuk koja treba da ispisuje zvuk koji životinja ispušta. Napraviti klasu Pas i klasu Zmija
koje nasleđuju klasu Životinja a potom promeniti zvuk u zavisnosti od životinje.
#ifndef ZIVOTINJA_H
#define ZIVOTINJA_H

using namespace std;
class Zivotinja {
public:
	void ispustiZvuk()
private:
	string ime;
	string rasa;
};