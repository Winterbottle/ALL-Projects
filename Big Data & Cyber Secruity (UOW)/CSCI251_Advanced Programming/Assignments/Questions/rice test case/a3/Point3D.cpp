#include "Point3D.h"

Point3D::Point3D(int x, int y, int z) : Point2D(x, y), z(z) {
    setDistFrOrigin();
}

void Point3D::setDistFrOrigin() {
    distFrOrigin = std::sqrt(getX() * getX() + getY() * getY() + z * z);
}

double Point3D::getScalarValue() const {
    return distFrOrigin;
}

bool Point3D::operator==(const Point3D& other) const {
    return (getX() == other.getX() && getY() == other.getY() && z == other.z);
}

