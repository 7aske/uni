#include <iostream>

#include "shop.h"
#include "item.h"

int main() {
    std::cout << "Nadam se da nece biti vise alt+insert domacih :/" << std::endl;
    std::cout << "Broj objekata: "<< Item::getObjectCount() << "\n";
    return 0;
}