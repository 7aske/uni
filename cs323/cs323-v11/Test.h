#ifndef CS323_V11_TEST_H
#define CS323_V11_TEST_H


class Animal {
public:
	~Animal() {};

	virtual void speak() = 0;

	const std::string &getName() const {
		return name;
	}

	void setName(const std::string &name) {
		Animal::name = name;
	}

protected:
	std::string name;

	Animal() {};

	Animal(std::string name) { this->name = name; };
};

class Dog : public Animal {
public:
	Dog() {};

	Dog(std::string name) : Animal(name) {}

	~Dog() {};

	void speak() { std::cout << "Dog" << std::endl; }

private:
};


#endif //CS323_V11_TEST_H
