//
// Created by nik on 5/16/19.
//

#ifndef CS323_P11_CPOLYGON_H
#define CS323_P11_CPOLYGON_H

class CPolygon {
protected:
	int width, height;
public:
	void set_values(int a, int b) {
		width = a;
		height = b;
	}
	int area() { return 0; };
};

#endif //CS323_P11_CPOLYGON_H
