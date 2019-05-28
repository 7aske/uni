#include <iostream>
#include <stdio.h>
#include <exception>

float division(float num, float den) {
	if (den == 0) {
		throw std::runtime_error("Math error: Attempted to divide by Zero\n");
	}
	return (num / den);
}

int main() {
	float a, b, result = 0;
	std::cin >> a;
	std::cin >> b;
	try {
		result = division(a, b);
		std::cout << "The quotient is "
				  << result << std::endl;
	}
	catch (std::runtime_error &e) {
		std::cout << "Exception occurred" << std::endl
				  << e.what();
	}
	return 0;
}