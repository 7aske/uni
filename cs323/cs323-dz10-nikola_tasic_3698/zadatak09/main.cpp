#include <iostream>

#include "Animal.cpp"
#include "WildAnimal.cpp"

int main() {
	std::string animal0_name("cat");
	int animal0_age = 10;
	std::string animal0_food("cat food");
	std::string animal0_color("white");
	std::string animal0_sex("male");

	std::string animal1_name("wild pig");
	int animal1_age = 10;
	std::string animal1_food("truffles");
	std::string animal1_color("brown");
	std::string animal1_sex("female");
	std::string animal1_hground("forrest");

	Animal animal0(animal0_name, animal0_age, animal0_food, animal0_color, animal0_sex);
	WildAnimal animal1(animal1_name, animal1_age, animal1_food, animal1_color, animal1_sex, animal1_hground);

	std::cout << animal0.Info() << "\n";
	std::cout << animal1.Info() << "\n";
	return 0;
}