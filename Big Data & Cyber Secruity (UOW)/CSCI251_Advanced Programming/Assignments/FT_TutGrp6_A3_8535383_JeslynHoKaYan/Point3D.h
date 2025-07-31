#ifndef POINT3D_H
#define POINT3D_H

#include "Point2D.h"
#include <iostream>
#include <tuple>

class Point3D : public Point2D {
private:
    int z;
    double distFrOrigin; // ✅ Store computed distance separately

public:
    Point3D(int x = 0, int y = 0, int z = 0);
    
    int getZ() const { return z; }
    void setDistFrOrigin();      // Updates distFrOrigin based on x, y, and z
    double getScalarValue() const; // Returns precomputed distance from origin

    bool operator<(const Point3D& other) const; // Overloaded comparison operator
};



#endif // POINT3D_H

