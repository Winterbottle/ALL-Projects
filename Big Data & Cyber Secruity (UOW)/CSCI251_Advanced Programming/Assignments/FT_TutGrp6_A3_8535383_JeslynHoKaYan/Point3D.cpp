#include "Point3D.h"
#include <tuple>

// ✅ Constructor initializes x, y, z and calculates distance from origin
Point3D::Point3D(int x, int y, int z) : Point2D(x, y), z(z) {
    setDistFrOrigin();
}

// ✅ Computes and stores the distance from the origin for efficiency
void Point3D::setDistFrOrigin() {
    distFrOrigin = std::sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
}

// ✅ Returns the precomputed distance from the origin
double Point3D::getScalarValue() const {
    return distFrOrigin;
}

// ✅ Defines a comparison order for Point3D objects (useful for sorting)
bool Point3D::operator<(const Point3D& other) const {
    if (getX() != other.getX()) return getX() < other.getX();
    if (getY() != other.getY()) return getY() < other.getY();
    return getZ() < other.getZ();
}

