#include <iostream>
#include "CPolygon.h"
#include "CRectangle.h"
#include "CTriangle.h"
#include "XClass.h"
#include "YClass.h"

using namespace std;

int add(int, int);

int subtract(int, int);

int multiply(int, int);


int main() {
	CRectangle rect;
	CTriangle trgl;
	CPolygon* ppoly1 = &rect;
	CPolygon* ppoly2 = &trgl;
	ppoly1->set_values(4, 5);
	ppoly2->set_values(4, 5);
	cout << rect.area() << endl;
	cout << trgl.area() << endl;


// Create a function pointer and make it point to the add function
	int (* pFcn)(int, int) = add;

	int x = 3, y = 5;

	for (int i = 0; i < 3; i++){
		switch (i) {
			case 0:
				pFcn = add;
				break;
			case 1:
				pFcn = subtract;
				break;
			case 2:
				pFcn = multiply;
				break;
		}
		std::cout << "The answer is: " << pFcn(x, y)
				  << std::endl;
	}
	return 0;
}

int add(int x, int y) {
	return x + y;
}

int subtract(int x, int y) {
	return x - y;
}

int multiply(int x, int y) {
	return x * y;
}
