#pragma once

#include "Circle.h"

class Cylinder {
	Circle base;
	double height;
public:
	explicit Cylinder(double r, double h)
			: base(r), height(h) {}

	double volume() { return base.area() * height; }
};