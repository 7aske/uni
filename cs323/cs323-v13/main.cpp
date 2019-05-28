#include <utility>

#include <iostream>
#include <string>

class IndexOutOfBounds : public std::exception {
public:
	explicit IndexOutOfBounds(std::string e_msg) : e_message(std::move(e_msg)) {};

	const char* what() const noexcept override {
		return this->e_message.c_str();
	}

protected:
	IndexOutOfBounds() = default;;
private:
	std::string e_message;
};

void testFunction(int arr_size = 15) {
	if (arr_size < 0 || arr_size >= 20) {
		throw IndexOutOfBounds("Index out of bounds");
	}
}

int main() {
	try {
		testFunction(25);
	} catch (IndexOutOfBounds e) {
		std::cerr << e.what() << std::endl;
	}
	std::cout << "Hello, World!" << std::endl;
	return 0;
}