//
// Created by nik on 5/24/19.
//

#ifndef CS323_DZ11_NIKOLA_TASIC_3698_HANDTOOLS_H
#define CS323_DZ11_NIKOLA_TASIC_3698_HANDTOOLS_H


#include <ostream>
#include "Tools.h"

class HandTools : public Tools {
public:
	explicit HandTools() = default;;

	HandTools(const std::string &name, const std::string &sn, double price, unsigned int min_age)
			: Tools(name, sn, price), min_age(min_age) {}

	~HandTools() {};

	void Info() override {
		std::cout << " name: " << Tools::getName()
				  << " sn: " << Tools::getSn()
				  << " price: " << Tools::getPrice()
				  << " min_age: " << HandTools::min_age
				  << std::endl;
	}


private:
	unsigned int min_age = 0;
public:
	unsigned int getMinAge() const {
		return min_age;
	}

	void setMinAge(unsigned int minAge) {
		min_age = minAge;
	}
};


#endif //CS323_DZ11_NIKOLA_TASIC_3698_HANDTOOLS_H
