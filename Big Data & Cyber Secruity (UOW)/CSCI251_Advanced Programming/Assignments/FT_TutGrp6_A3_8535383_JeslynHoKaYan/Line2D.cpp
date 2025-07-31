#include "Line2D.h"

// Constructor initializes the line with two points and computes its length.
Line2D::Line2D(Point2D pt1, Point2D pt2) : pt1(pt1), pt2(pt2) {
    setLength();
}

// Computes the Euclidean distance between the two points.
void Line2D::setLength() {
    int dx = pt1.getX() - pt2.getX();
    int dy = pt1.getY() - pt2.getY();
    length = std::sqrt(dx * dx + dy * dy);
}



// Returns the precomputed scalar value of the line's length.
double Line2D::getScalarValue() const {
    return length;
}

Point2D Line2D::getPt1() const {
    return pt1;
}

Point2D Line2D::getPt2() const {
    return pt2;
}


// Compares two lines for equality based on their endpoints.
bool Line2D::operator==(const Line2D& other) const {
    return (pt1 == other.pt1 && pt2 == other.pt2);
}

// Less than operator for ordering based on endpoint coordinates.
bool Line2D::operator<(const Line2D& other) const {
    if (pt1.getX() != other.pt1.getX()) return pt1.getX() < other.pt1.getX();
    if (pt1.getY() != other.pt1.getY()) return pt1.getY() < other.pt1.getY();
    if (pt2.getX() != other.pt2.getX()) return pt2.getX() < other.pt2.getX();
    return pt2.getY() < other.pt2.getY();
}


// Static method to compare by second point in ascending order.
bool Line2D::compareByPt2ASC(const Line2D &a, const Line2D &b) {
    if (a.getPt2().getX() != b.getPt2().getX()) 
        return a.getPt2().getX() < b.getPt2().getX();
    return a.getPt2().getY() < b.getPt2().getY();
}

// Static method to compare by second point in descending order.
bool Line2D::compareByPt2DSC(const Line2D &a, const Line2D &b) {
    if (a.getPt2().getX() != b.getPt2().getX()) 
        return a.getPt2().getX() > b.getPt2().getX();
    return a.getPt2().getY() > b.getPt2().getY();
}
