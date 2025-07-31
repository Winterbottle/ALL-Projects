#include "Square.h"
#include <sstream>

Square::Square(const std::string& name, bool containsWarpSpace, const std::pair<int, int>& topLeft, int sideLength)
    : Rectangle(name, containsWarpSpace, topLeft, {topLeft.first + sideLength, topLeft.second - sideLength}) {}

double Square::computeArea() const {
    int sideLength = bottomRight.first - topLeft.first; // Width or height of the square
    return static_cast<double>(sideLength * sideLength);
}

std::vector<std::pair<int, int>> Square::generatePerimeterPoints() const {
    std::vector<std::pair<int, int>> perimeterPoints;

    // Exclude vertices from perimeter points
    for (int x = topLeft.first + 1; x < bottomRight.first; ++x) {
        perimeterPoints.emplace_back(x, topLeft.second);      // Top edge
        perimeterPoints.emplace_back(x, bottomRight.second);  // Bottom edge
    }
    for (int y = bottomRight.second + 1; y < topLeft.second; ++y) {
        perimeterPoints.emplace_back(topLeft.first, y);       // Left edge
        perimeterPoints.emplace_back(bottomRight.first, y);   // Right edge
    }

    return perimeterPoints;
}

std::vector<std::pair<int, int>> Square::generateInnerPoints() const {
    return Rectangle::generateInnerPoints(); // Reuse Rectangle's implementation
}

std::string Square::toString() const {
    return Rectangle::toString(); // Reuse Rectangle's implementation for output
}

