#ifndef ZADATAK19_MEAT_H
#define ZADATAK19_MEAT_H


class Meat: public Product {
public:
	Meat(const std::string &name, double price, double expiry, std::string animal) : Product(name, price, expiry), animal(animal) {};
	~Meat() {};
	void Info();
private:
	std::string animal;
	double PDV = 8;
};


#endif //ZADATAK19_MEAT_H
