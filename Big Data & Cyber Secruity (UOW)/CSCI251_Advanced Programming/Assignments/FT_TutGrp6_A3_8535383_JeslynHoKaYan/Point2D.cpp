#include "Point2D.h"

// ✅ Constructor initializes x and y, then calculates distance from origin
Point2D::Point2D(int x, int y) : x(x), y(y) {
    setDistFrOrigin();
}

// ✅ Computes and stores the distance from the origin
void Point2D::setDistFrOrigin() {
    distFrOrigin = std::sqrt(x * x + y * y);
}

// ✅ Getter function for the scalar value (distance from origin)
double Point2D::getScalarValue() const {
    return distFrOrigin;
}

// ✅ Compares if two 2D points are equal (both x and y must match)
bool Point2D::operator==(const Point2D& other) const {
    return (x == other.x && y == other.y);
}

// ✅ Compares points based on x first, then y (useful for sorting)
bool Point2D::operator<(const Point2D& other) const {
    if (x != other.x) return x < other.x;
    return y < other.y;
}

