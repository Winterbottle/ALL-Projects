
#pragma once			// Ensures this header file is included only once during compilation
#include "ShapeTwoD.h"
#include <utility>
#include <string>


// The Circle class represents a 2D circle shape and inherits from ShapeTwoD
class Circle : public ShapeTwoD {
private:
    std::pair<int, int> center;		// Coordinates of the circle's center (x, y)
    int radius;

public:
    // Constructor: Initializes a Circle object with its name, warp space status, center, and radius
    Circle(const std::string& name, bool containsWarpSpace, std::pair<int, int> center, int radius);

    // Computes and returns the area of the circle
    double computeArea() const override;

    // Generates and returns a list of points that form the perimeter of the circle
    std::vector<std::pair<int, int>> generatePerimeterPoints() const override;

    // Generates and returns a list of points inside the circle
    std::vector<std::pair<int, int>> generateInnerPoints() const override;

    // Checks if a given point (x, y) is inside the circle
    bool isPointInShape(int x, int y) const override;

    // Checks if a given point (x, y) is on the circle's perimeter
    bool isPointOnShape(int x, int y) const override;

    // Returns a string representation of the circle's details (e.g., area, center, radius)
    std::string toString() const override;
};

