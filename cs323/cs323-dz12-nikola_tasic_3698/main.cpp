// Za izradu ovog zadatka neophodno je da imate urađen 10. ili 11. domaći zadatak.
// Naime, neophodno je da uzmete kreiranu klasu iz 10. (ili 11.) zadatka i da pomoću nje testirate rad
// 		odgovarajuće klase/algoritma iz STL biblioteke. Stoga, vaša klasa treba da bude tip koji će moći da se
// 		čuva u odgovarajućem kontejneru. Npr. ako ste imali klase Student (bazna klasa) i BachelorStudent
// 		(izvedena iz klase Student) onda ćete kreirati kontejnere koji će moći da prihvate objekte tipa
// BachelorStudent (Prethodno je samo primer, svako radi sa svojim klasama koje ste dobili postavkom
// 10 ili 11 zadatka!).
// Zadaci:
// 1. Kreirati vektor od 5 proizvoljnih objekata vaše klase. Zatim ubaciti (insertovati) novi objekat na 3
// mesto, a obrisati poslednji objekat. Odštampati rezultujući vektor na ekranu.
// 2. Kreirati novi objekat O1 i inicijalizovati ga podacima. Ispitati da li se u kreiranom vektoru nalazi
// objekat O1. Primeniti konstrukciju:
// vector<VasaKlasa> vec;
// … // punjenje vektora podacima prema 1.
// … // ubacivanje na 3. mesto novog, brisanje poslednjeg, štampa rezultujućeg vektora.
// VasaKlasa O1(…); // konstruktor treba da inicijalizuje podatke objekta
// vector <VasaKlasa>::iterator it;
// it = find(vec.begin(), vec.end(), O1);
// Ispisati na kraju poruku o statusu (“objekat je pronađen” ili “objekat nije pronađen”).
// Napomena: Da bi funkcija find mogla da ispita da li se O1 nalazi u vektoru, neophodno je da vaša
// 		klasa ima implementiran preklopljeni operator ==.
// 3. Iz fajla “ulaz.txt” učitati N objekata vaše klase i spakovati ih u stek. Zatim ih u inverznom poretku
// 		odštampati u fajl “izlaz.txt”.
// Napomena: Kretanje kroz kontejnere izvršiti isključivo primenom iteratora!
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <stack>
#include <algorithm>

#include "HandTools.h"

int main() {

	HandTools ht0("Hammer1", "q3rtsgd3r1", 4500, 18);
	HandTools ht1("Hammer2", "q5325teess", 4100, 18);
	HandTools ht2("Hammer3", "mstrgaet33", 4300, 18);
	HandTools ht3("Hammer4", "2353426twe", 4200, 18);
	HandTools ht4("Hammer5", "q3tewy5555", 5000, 18);

	HandTools ht5("Hammer6", "adgaherq43", 5100, 18);

	std::vector<HandTools> tools;

	tools.push_back(ht0);
	tools.push_back(ht1);
	tools.push_back(ht2);
	tools.push_back(ht3);
	tools.push_back(ht4);

	tools.insert(tools.begin() + 2, ht5);
	tools.pop_back();

	for (auto &tool : tools) {
		tool.Info();
	}

	HandTools O1("Hammer7", "adgafasq33", 18, 6000);

	std::vector<HandTools>::iterator it;

	it = std::find(tools.begin(), tools.end(), O1);

	if (*it == O1) {
		std::cout << "element O1 found\n";
	} else {
		std::cout << "element O1 not found\n";
	}


	/* Treci deo zadataka*/
	std::stack<HandTools> stack;
	std::ifstream infile("input.txt");
	std::string line;
	while (std::getline(infile, line)) {
		std::istringstream iss(line);
		std::string name;
		std::string sn;
		double price;
		unsigned int age;

		iss >> name >> sn >> price >> age;

		HandTools tool(name, sn, price, age);
		stack.push(tool);
	}

	std::ofstream outfile("output.txt");
	while (!stack.empty()) {
		auto tool = stack.top();
		outfile << tool.getName() << " " << tool.getSn() << " " << tool.getPrice() << " " << tool.getMinAge() << "\n";
		stack.pop();
	}
	return 0;
}