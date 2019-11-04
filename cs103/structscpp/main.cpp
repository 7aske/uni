#include <iostream>
#include "ArrayList.h"
#include "LinkedList.h"
#include "Stack.h"

int main() {
	ArrayList<int> arrayList(32);
	for (int i = 0; i < 10000; ++i) {
		arrayList.add(i);
	}

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

	Stack<int> intstack = Stack<int>();
	intstack.push(10);
	intstack.push(20);
	intstack.push(30);
	for (int k = 0; k < 100; ++k) {
		intstack.push(k);
	}
	std::cout << intstack.peek() << std::endl;
	std::cout << intstack.pop() << std::endl;
	std::cout << intstack.pop() << std::endl;
	std::cout << intstack.pop() << std::endl;
	return 0;
}