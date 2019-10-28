#include <iostream>
#include "ArrayList.h"


int main() {
	ArrayList<int> arrayList(32);
	for (int i = 0; i < 10000; ++i) {
		arrayList.add(i);
	}
	for (int j = 0; j < arrayList.size(); ++j) {
		std::cout << arrayList.get(j) << std::endl;
	}
	return 0;
}