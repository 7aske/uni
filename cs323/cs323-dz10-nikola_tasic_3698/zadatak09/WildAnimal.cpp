#pragma once

#include "WildAnimal.h"
#include "Animal.h"

WildAnimal::WildAnimal() {}

WildAnimal::WildAnimal(std::string &name, int age, std::string &food, std::string &color, std::string &sex,
					   std::string &hunting_ground) : name(name), age(age), food(food), color(color), sex(sex),
													  hunting_ground(hunting_ground) {
}

std::string WildAnimal::Info() {
	return std::string("Name: '" + WildAnimal::name + "' Hunting ground: '" + WildAnimal::hunting_ground + "'" /* i ostali atributi */);
}

WildAnimal::~WildAnimal() {

}

