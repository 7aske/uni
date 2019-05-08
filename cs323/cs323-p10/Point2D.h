#ifndef POINT2D_H
#define POINT2D_H

#include <iostream>

class Point2D {
private:
	int m_nX, m_nY;
public:
	Point2D()
			: m_nX(0), m_nY(0) {}

	Point2D(int nX, int nY)
			: m_nX(nX), m_nY(nY) {}

	friend std::ostream &operator<<(std::ostream &out, const Point2D &cPoint) {
		out << "(" << cPoint.GetX() << ", " << cPoint.GetY() << ")";
		return out;
	}

	void SetPoint(int nX, int nY) {
		m_nX = nX;
		m_nY = nY;
	}

	int GetX() const { return m_nX; }

	int GetY() const { return m_nY; }
};

#endif