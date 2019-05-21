//
// Created by nik on 5/16/19.
//

#ifndef CS323_P11_CRECTANGLE_H
#define CS323_P11_CRECTANGLE_H


class CRectangle : public CPolygon {
public:
	int area() { return (width * height); }
};
#endif //CS323_P11_CRECTANGLE_H
