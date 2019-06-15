#include <utility>

#include <utility>

// 9. Sortirati leksikografski (pomoću funkcije strcmp()) reči u
// datoteci Ulaz.txt. Sortirani tekst ispisati u datoteku Sort.txt.
// Iskoristiti algoritam za sortiranje i STL biblioteke „algorithm“.

#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>

namespace zad09 {
	namespace pred {
		// previse sam radio C# i kasno sam shvatio da mi ne treba "strcmp()" nego "strcmp" kao arugment za sort
		bool strcmp(const std::string &str1, const std::string &str2) {
			return std::strcmp(str1.c_str(), str2.c_str()) < 0;
		}
	}

	int main() {

		std::vector<std::string> input_vec;
		std::ifstream input_file("input.txt");

		if (!input_file.is_open()) {
			std::cerr << "Unable to open file for reading" << std::endl;
			return 1;
		}

		std::string word;
		while (input_file >> word) {
			input_vec.push_back(word);
		}

		std::sort(input_vec.begin(), input_vec.end(), pred::strcmp);

		// ili lambda

		// std::sort(input_vec.begin(), input_vec.end(), [](const std::string &str1, const std::string &str2) {
		// 	return std::strcmp(str1.c_str(), str2.c_str()) < 0;
		// });

		std::ofstream output_file("output.txt");


		if (!output_file.is_open()) {
			std::cerr << "Unable to open file for writing" << std::endl;
			return 1;
		}

		for (const auto &w : input_vec) {
			output_file << w << std::endl;
		}

		return 0;
	}

}


namespace zad14 {
	// 14. Napraviti program za upis podataka o automobilima (Model, Proizvođač, Cena, Godište)
	// u fajl, kao i prikaz tih podataka. Koristiti funkcije write i read za rad sa binarnim fajlovima.
	// Korisnik ima opciju da dobije listu svih automobila koji nisu skuplji od unete cene i imaju godište
	// veće od zadatog.
	class Car {
	public:
		Car(std::string model, std::string manufacturer, double price, int year) :
				model(std::move(model)),
				manufacturer(std::move(manufacturer)),
				price(price),
				year(year) {}

		~Car() = default;;

		std::string model;
		std::string manufacturer;
		double price;
		int year;

		friend std::ostream &operator<<(std::ostream &os, const Car &car) {
			os << car.model << "|"
			   << car.manufacturer << "|"
			   << car.price << "|"
			   << car.year << "\n";

			return os;
		}

		static Car from_string(const std::string &str) {
			// nisam mogao da se setim kako da napravim string array
			char params[4][32];

			for (int i = 0; i < 4; ++i) {
				int n;
				std::string del;
				if (i == 3) {
					del = "\n";
				} else {
					del = "|";
				}
				strncpy(params[i], str.substr(0, str.find(del)).c_str(), 32);

			}
			return Car(params[0], params[1], strtol(params[2], nullptr, 10), strtol(params[2], nullptr, 10));
		}
	};

	int main() {
		Car car0("Model 0", "Manufacturer 0", 14501, 1995);
		Car car1("Model 1", "Manufacturer 1", 4501, 1980);
		Car car2("Model 2", "Manufacturer 2", 44556, 1924);

		std::vector<Car> car_vec_out;
		car_vec_out.push_back(car0);
		car_vec_out.push_back(car1);
		car_vec_out.push_back(car2);

		std::ofstream output_file("cars_output.txt");

		if (!output_file.is_open()) {
			std::cerr << "Unable to open file for reading" << std::endl;
			return 1;
		}

		for (const auto &c : car_vec_out) {
			output_file << c;
		}

		std::vector<Car> car_vec_in;

		std::ifstream input_file("cars_output.txt");

		if (!input_file.is_open()) {
			std::cerr << "Unable to open file for reading" << std::endl;
			return 1;
		}

		/**
		 *
		 *
		 * Iz nekog razloga ni C i C++ nacin za citanje fajlova nije radio
		 * Ni binary ni regularan nacin pisanja/citanja
		 * Da radi prosledio bih svaki buf funkciji Car::from_string() i
		 * rezulat stavlja u vektor. Iz vektora lako izvlacimo sta nam je
		 * potrebno for petljom
		 *
		 * */

		FILE* fp = fopen("cars_output.txt", "r");
		char buf[256];
		while (fgets(buf, 256, fp) != nullptr)
			std::cout << buf << std::endl;

		return 0;
	}
}

int main() {
	// zad09::main();
	// zad14::main();
	std::cout << "Odkomentarisati odgovarajuci main func za pokretanje programa" << std::endl;
}

