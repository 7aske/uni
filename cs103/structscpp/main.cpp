#include <iostream>
#include "ArrayList.h"
#include "LinkedList.h"
#include <array>

int main() {
	ArrayList<int> arrayList(32);
	for (int i = 0; i < 10000; ++i) {
		arrayList.add(i);
	}
	std::array<int, sizeof(int)> arr();

	for (int j = 0; j < arrayList.size(); ++j) {
		std::cout << arrayList.get(j) << std::endl;
	}

	LinkedList<int> linkedList;
	linkedList.add(10);
	linkedList.add(20);
	linkedList.add(30);
	linkedList.add(40);
	linkedList.add(22, 4);
	linkedList.remove(10);
	std::cout << linkedList.get(0) << std::endl;
	std::cout << linkedList.get(1) << std::endl;
	std::cout << linkedList.get(2) << std::endl;
	std::cout << linkedList.get(3) << std::endl;
	// std::cout << linkedList.indexOf(30) << std::endl;
	return 0;
}