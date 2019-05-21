#ifndef CS323_DZ10_NIKOLA_TASIC_3698_ANIMAL_H
#define CS323_DZ10_NIKOLA_TASIC_3698_ANIMAL_H


class Animal {
public:
	Animal();

	Animal(std::string &name, int age, std::string &food, std::string &color, std::string &sex):
			name(name), age(age), food(food), color(color), sex(sex){};
	std::string Info();

	virtual ~Animal();

private:
	std::string name;
	int age;
	std::string food;
	std::string color;
	std::string sex;
};



#endif //CS323_DZ10_NIKOLA_TASIC_3698_ANIMAL_H
