#ifndef POINT2D_H
#define POINT2D_H

#include <cmath>
#include <iostream>

class Point2D {
private:
    int x, y;
    double distFrOrigin;

public:
    Point2D(int x = 0, int y = 0);
    void setDistFrOrigin();    // Updates distFrOrigin based on x and y
    double getScalarValue() const; // Returns distance from origin

    int getX() const { return x; }
    int getY() const { return y; }

    bool operator==(const Point2D& other) const; // Overloaded equality operator
    bool operator<(const Point2D& other) const;  // Needed for sorting and set operations
};

#endif // POINT2D_H

