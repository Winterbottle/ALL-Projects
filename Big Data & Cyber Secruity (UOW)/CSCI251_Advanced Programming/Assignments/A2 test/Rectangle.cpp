#include "Rectangle.h"
#include <sstream>

Rectangle::Rectangle(const std::string& name, bool containsWarpSpace, std::pair<int, int> topLeft, std::pair<int, int> bottomRight)
    : ShapeTwoD(name, containsWarpSpace), topLeft(topLeft), bottomRight(bottomRight) {}

double Rectangle::computeArea() const {
    int width = bottomRight.first - topLeft.first;
    int height = topLeft.second - bottomRight.second;
    return static_cast<double>(width * height);
}

std::vector<std::pair<int, int>> Rectangle::generatePerimeterPoints() const {
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

std::vector<std::pair<int, int>> Rectangle::generateInnerPoints() const {
    std::vector<std::pair<int, int>> innerPoints;

    for (int x = topLeft.first + 1; x < bottomRight.first; ++x) {
        for (int y = bottomRight.second + 1; y < topLeft.second; ++y) {
            innerPoints.emplace_back(x, y);
        }
    }

    return innerPoints;
}

bool Rectangle::isPointInShape(int x, int y) const {
    return x > topLeft.first && x < bottomRight.first && y > bottomRight.second && y < topLeft.second;
}

bool Rectangle::isPointOnShape(int x, int y) const {
    for (const auto& point : generatePerimeterPoints()) {
        if (point.first == x && point.second == y) {
            return true;
        }
    }
    return false;
}

std::string Rectangle::toString() const {
    std::ostringstream oss;
    oss << "Shape Name: " << getName() << "\n";
    oss << "Special Type: " << (getContainsWarpSpace() ? "WS" : "NS") << "\n";
    oss << "Area: " << computeArea() << " units square\n";

    oss << "Point [0]: (" << topLeft.first << ", " << topLeft.second << ")\n";
    oss << "Point [1]: (" << topLeft.first << ", " << bottomRight.second << ")\n";
    oss << "Point [2]: (" << bottomRight.first << ", " << bottomRight.second << ")\n";
    oss << "Point [3]: (" << bottomRight.first << ", " << topLeft.second << ")\n";

    oss << "Points on Perimeter: ";
    for (const auto& point : generatePerimeterPoints()) {
        oss << "(" << point.first << ", " << point.second << ") ";
    }
    oss << "\n";

    oss << "Points within Shape: ";
    for (const auto& point : generateInnerPoints()) {
        oss << "(" << point.first << ", " << point.second << ") ";
    }
    oss << "\n";

    return oss.str();
}

