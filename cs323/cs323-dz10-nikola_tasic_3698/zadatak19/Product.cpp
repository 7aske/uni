#include <iostream>
#include "Product.h"

void Product::Info() {
	std::cout << Product::name << " " << Product::price << " " << Product::expiry << std::endl;
}
