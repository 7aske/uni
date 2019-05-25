//
// Created by nik on 5/24/19.
//

#ifndef CS323_DZ11_NIKOLA_TASIC_3698_TOOLS_H
#define CS323_DZ11_NIKOLA_TASIC_3698_TOOLS_H


#include <string>

class Tools {
public:

	~Tools() {};

	virtual void Info() = 0;

protected:
	explicit Tools() = default;;

	Tools(const std::string &name, const std::string &sn, double price) : name(name), sn(sn), price(price) {};

private:
	std::string name;
	std::string sn;
	double price = 0;
public:
	const std::string &getName() const {
		return name;
	}

	void setName(const std::string &name) {
		Tools::name = name;
	}

	const std::string &getSn() const {
		return sn;
	}

	void setSn(const std::string &sn) {
		Tools::sn = sn;
	}

	double getPrice() const {
		return price;
	}

	void setPrice(double price) {
		Tools::price = price;
	}
};


#endif //CS323_DZ11_NIKOLA_TASIC_3698_TOOLS_H
