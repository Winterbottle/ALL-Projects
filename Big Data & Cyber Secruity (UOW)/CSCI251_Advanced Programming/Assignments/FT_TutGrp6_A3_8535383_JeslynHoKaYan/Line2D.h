#ifndef LINE2D_H
#define LINE2D_H

#include "Point2D.h"

class Line2D {
private:
    Point2D pt1, pt2;
    double length;

public:
 	// Constructor initializes the line with two points and calculates length.
    Line2D(Point2D pt1 = Point2D(), Point2D pt2 = Point2D());
    
    // Computes and updates the length of the line.
    void setLength();
    
    // Returns the length of the line.
    double getScalarValue() const;
    
    // Getter methods for endpoints.
    Point2D getPt1() const;
    Point2D getPt2() const;
    
    // Operator overloads for equality and ordering.
    bool operator==(const Line2D& other) const;
    bool operator<(const Line2D& other) const;  
    
    // Static comparison functions for sorting by second point.
    static bool compareByPt2ASC(const Line2D &a, const Line2D &b);
    static bool compareByPt2DSC(const Line2D &a, const Line2D &b);
};

#endif // LINE2D_H

