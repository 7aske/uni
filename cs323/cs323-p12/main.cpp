#include <iostream>
#include <map>
#include <vector>
#include <string>
#include <iterator>
using namespace std;


template<class T>
class Stack {
private:
	vector<T> elems;
public:
	void push(T const &elem) {
		elems.push_back(elem);
	};

	T pop() {
		if (!elems.empty()) {
			T x = elems.back();
			elems.pop_back();
			return x;
		}
	};

	T peek() const {
		if (!elems.empty()) elems.back();
	};

	bool empty() const {
		return elems.empty();
	}
};


int main() {
	map<string, string> studenti;
	studenti["3698"] = "Nikola Tasic";
	studenti["3801"] = "Aleksandra Blagojevic";
	studenti["3611"] = "Dusan Stankovic";
	studenti["3659"] = "Aleksa Lukic";

	cout << studenti["3698"] << endl;
	cout << studenti["3611"] << endl << endl;

	cout << "Iteracija kroz mapu" << endl;
	for (auto studentiIterator = studenti.begin(); studentiIterator != studenti.end(); ++studentiIterator) {
		cout << studentiIterator->first << " => " << studentiIterator->second << endl;
	}
	cout << endl;
	Stack<int> stack;
	for (int i = INT32_MAX - 1, c = 10; c > 0; i -= 8, c--) {
		stack.push(i);
	}

	while (!stack.empty()) {
		printf("0x%x\n", stack.pop());
	}

	return 0;
}