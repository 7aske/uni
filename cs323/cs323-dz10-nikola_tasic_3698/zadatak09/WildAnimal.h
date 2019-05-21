#ifndef CS323_DZ10_NIKOLA_TASIC_3698_WILDANIMAL_H
#define CS323_DZ10_NIKOLA_TASIC_3698_WILDANIMAL_H


class WildAnimal : private Animal {
public:
	WildAnimal();

	WildAnimal(std::string &name, int age, std::string &food, std::string &color, std::string &sex,
			   std::string &hunting_ground);

	std::string Info();

	virtual ~WildAnimal();

private:
	std::string name;
	int age{};
	std::string food;
	std::string color;
	std::string sex;
	std::string hunting_ground;
};


#endif //CS323_DZ10_NIKOLA_TASIC_3698_WILDANIMAL_H
