#include <iostream>
#include "Cylinder.h"
#include "Circle.h"
#include "Point2D.h"
#include "Creature.h"
#include "Teacher.h"
#include "Department.h"

void composition();


int main() {
	composition();
	return 0;
}

void composition() {
	Cylinder c(10, 10);
	std::cout << "c volume: " << c.volume() << "\n";
	std::cout << "Enter a name: ";
	std::string cName;
	std::cin >> cName;
	Creature cCreature(cName, Point2D(4, 7));
	while (1) {
		std::cout << cCreature << std::endl;
		std::cout << "Enter new X (-1 to quit): ";
		int nX=0;
		std::cin >> nX;
		if (nX == -1)  break;

		std::cout << "Enter new Y (-1 to quit): ";
		int nY=0;
		std::cin >> nY;
		if (nY == -1) break;
		cCreature.MoveTo(nX, nY);
		std::cout << cCreature << std::endl;
	}
	Teacher *pTeacher = new Teacher("Bob");
	{
		Department cDept(pTeacher);
		std::cout << "Teacher name: "  << cDept.m_pcTeacher->GetName() << "\n";
	}
	delete pTeacher;
}
