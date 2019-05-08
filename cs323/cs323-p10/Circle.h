#ifndef CIRCLE_H
#define CIRCLE_H

class Circle {
	double radius;
public:
	explicit Circle(double r)
			: radius(r) {}

	double area() { return radius * radius * 3.14; }
};

#endif