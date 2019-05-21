//
// Created by nik on 5/16/19.
//

#ifndef CS323_P11_CTRIANGLE_H
#define CS323_P11_CTRIANGLE_H

class CTriangle: public CPolygon {
public:
	int area ()
	{ return (width * height / 2); }
};

#endif //CS323_P11_CTRIANGLE_H
