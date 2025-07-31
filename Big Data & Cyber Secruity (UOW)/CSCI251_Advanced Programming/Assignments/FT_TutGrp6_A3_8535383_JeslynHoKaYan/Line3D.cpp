#include <cmath>     
#include "Line3D.h"

// Constructor initializes the two 3D points and computes the length of the line.
Line3D::Line3D(Point3D pt1, Point3D pt2) : pt1(pt1), pt2(pt2) {
    setLength();  
}

// Computes and stores the Euclidean distance between pt1 and pt2.
void Line3D::setLength() {
    int dx = pt1.getX() - pt2.getX();
    int dy = pt1.getY() - pt2.getY();
    int dz = pt1.getZ() - pt2.getZ();
    length = sqrt(dx * dx + dy * dy + dz * dz);
}

// Getter methods for accessing private members
Point3D Line3D::getPt1() const { return pt1; }
Point3D Line3D::getPt2() const { return pt2; }
double Line3D::getScalarValue() const { return length; }

// Operator < for sorting/comparing Line3D objects
bool Line3D::operator<(const Line3D& other) const {
    if (this->getScalarValue() != other.getScalarValue()) {
        return this->getScalarValue() < other.getScalarValue();
    }
    if (this->getPt1().getX() != other.getPt1().getX()) {
        return this->getPt1().getX() < other.getPt1().getX();
    }
    if (this->getPt1().getY() != other.getPt1().getY()) {
        return this->getPt1().getY() < other.getPt1().getY();
    }
    if (this->getPt1().getZ() != other.getPt1().getZ()) {
        return this->getPt1().getZ() < other.getPt1().getZ();
    }
    if (this->getPt2().getX() != other.getPt2().getX()) {
        return this->getPt2().getX() < other.getPt2().getX();
    }
    if (this->getPt2().getY() != other.getPt2().getY()) {
        return this->getPt2().getY() < other.getPt2().getY();
    }
    return this->getPt2().getZ() < other.getPt2().getZ();
}

// Operator == for checking equality of Line3D objects
bool Line3D::operator==(const Line3D& other) const {
    return pt1 == other.pt1 && pt2 == other.pt2;
}

