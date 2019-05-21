//
// Created by nik on 5/14/19.
//

#ifndef ZADATAK19_PROIZVOD_H
#define ZADATAK19_PROIZVOD_H


class Product {
public:
	Product(std::string name, double price, double expiry) : name(name), price(price), expiry(expiry) {};

	~Product() {};

	void Info();
private:
	std::string name;
	double price;
	double expiry;
	double PDV = 20;
};


#endif //ZADATAK19_PROIZVOD_H
