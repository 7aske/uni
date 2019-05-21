#include <iostream>

#include "Test.h"

template<typename T>
inline T const &Max(T const &a, T const &b) { return a > b ? a : b; }

template<class T>
class Stack {
public:
	Stack() {
		arr = new T[size];
	};

	Stack(unsigned int size) : size(size) {
		arr = new T[size];
	};

	void Push(T const &e) {
		if (index < size)
		arr[++index] = e;
	}

	T const &Pop() {
		if (index > -1)
			return arr[index--];

	}
	T const& Peek(){
		if (index > -1)
			return arr[index];
	}

private:
	unsigned int size = 20;
	int index = -1;
	T* arr;
};

int main() {
	Dog d("Dog");
	std::cout << d.getName() << "\n";
	std::cout << Max(15, 20) << "\n";
	Stack<std::string> stack;
	stack.Push("Heelo");
	std::cout << stack.Peek() << "\n";
	std::cout << stack.Pop() << "\n";
	return 0;
}