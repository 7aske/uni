#include <stdio.h>

// 9. [C/C++] Dat je niz celih brojeva, dužine n. Odrediti podniz datog niza čiji je zbir elemenata maksimalan, a
// u kome nema susednih elemenata. (Traženo rešenje mora biti najmanje složenosti)

int subarrmax(const int arr[], int len) {
	int incl = arr[0];
	int excl = 0;
	int curr_excl;

	// nisam imao pojma kako da resim a ovo je jedina implementacija
	// koju sam uspeo da nadjem i koju ne razumem bas najbolje :(
	for (int i = 1; i < len; ++i) {
		curr_excl = incl > excl ? incl : excl;
		incl = excl + arr[i];
		excl = curr_excl;
	}

	return incl > excl ? incl : excl;
}


int main() {

	int input1[] = {4, -1, 3, 8, 7, -1, -5, 6};
	int input2[] = {-4, 1, -3, -8, -7, 1, 5, -6};
	int len = 8;
	int sum;

	sum = subarrmax(input1, len);
	printf("%d\n", sum);

	sum = subarrmax(input2, len);
	printf("%d\n", sum);

	return 0;
}