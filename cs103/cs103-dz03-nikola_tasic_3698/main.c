#include <stdio.h>

// 3. Za dati prirodan broj pomoću rekurzivne funkcije ispisati
// broj koji ima iste cifre, ali u obrnutom poretku.

void rec_print(int x) {
	if (x < 9)
		printf("%d", x);
	else {
		printf("%d", x % 10);
		rec_print(x / 10);
	}

}

int main() {
	rec_print(123);
	return 0;
}
