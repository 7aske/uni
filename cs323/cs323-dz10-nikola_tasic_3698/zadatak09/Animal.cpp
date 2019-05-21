#pragma once

#include "Animal.h"

Animal::Animal() {}

std::string Animal::Info() {
	return std::string("Name: '" + Animal::name + "'" /* i ostali atributi */);
}

Animal::~Animal() {

}
