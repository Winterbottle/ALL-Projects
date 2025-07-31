#ifndef POINT3D_H
#define POINT3D_H

#include <cmath>
#include "Point2D.h"

class Point3D : public Point2D {
private:
    int z;
    double distFrOrigin;

public:
    Point3D(int x = 0, int y = 0, int z = 0);
    void setDistFrOrigin();
    double getScalarValue() const;
    int getZ() const { return z; }
    bool operator==(const Point3D& other) const;
};

#endif // POINT3D_H

