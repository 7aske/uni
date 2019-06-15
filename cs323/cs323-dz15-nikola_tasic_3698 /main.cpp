// Formirati dva niza A[] i B[] od po 10 elemenata. Treći niz C[] se automatski formira od dva uneta
// niza tako što se u njega prvo upisuju svi parni elementi nizova A i B, a zatim svi neparni elementi.
// Prikazati elemente sva tri niza. Zadatak rešiti primenom std::array klase i for-each petlje (C++11).

#include <iostream>
#include <algorithm>
#include <array>
#include <vector>


int main() {
	std::array<int, 10> a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	std::array<int, 10> b = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	// std::array<int, 20> c{};
	std::vector<int> c;

	auto lambda_even = [&c](const int &n) -> void {
		if (n % 2 == 0) {
			c.push_back(n);
		}
	};

	auto lambda_odd = [&c](const int &n) -> void {
		if (n % 2 == 1) {
			c.push_back(n);
		}
	};

	// nisam znao koju pa eto oba for-each
	// operator [] kod array vraca const& tako da nisam znao
	// kako da mu promenim vrednosti (je l' uopste moze?)

	std::for_each(a.begin(), a.end(), lambda_even);
	std::for_each(b.begin(), b.end(), lambda_even);

	std::for_each(a.begin(), a.end(), lambda_odd);
	std::for_each(b.begin(), b.end(), lambda_odd);

	for (auto x : c) {
		std::cout << x << std::endl;
	}

	return 0;
}
