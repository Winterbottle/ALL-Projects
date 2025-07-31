#ifndef LINE3D_H
#define LINE3D_H

#include "Point3D.h"

class Line3D {
private:
    Point3D pt1, pt2;
    double length;

public:
    // Constructor initializes the line with two points and calculates its length.
    Line3D(Point3D pt1 = Point3D(), Point3D pt2 = Point3D());

    // Computes and updates the length of the line.
    void setLength();
    
    // Returns the length of the line.
    double getScalarValue() const; 
    
    // Getter methods for endpoints.
    Point3D getPt1() const;  
    Point3D getPt2() const;  
    
    // Operator overloads
    bool operator<(const Line3D& other) const;   // Declare operator<
    bool operator==(const Line3D& other) const;  // Declare operator==
};

#endif // LINE3D_H

