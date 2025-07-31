#ifndef MYTEMPLATES_H
#define MYTEMPLATES_H

#include <cmath>  // For abs()
#include "csci251_a3.h"  // Ensure it has definitions for Point2D, Point3D, Line2D, Line3D

// ✅ Template function for scalar_difference
template <typename T>
double scalar_difference(const T& obj1, const T& obj2) {
    return std::abs(obj1.getScalarValue() - obj2.getScalarValue());
}

// ✅ Template function for equals
template <typename T>
bool equals(const T& obj1, const T& obj2) {
    return obj1 == obj2;  // Requires operator== to be overloaded
}

// ✅ Specialization for primitive types (int, double, etc.)
template <>
bool equals<int>(const int& a, const int& b) {
    return a == b;
}

template <>
bool equals<double>(const double& a, const double& b) {
    return a == b;
}

#endif // MYTEMPLATES_H

