
#include "Circle.h"
#include <sstream>
#include <cmath>
#include <iomanip>

// Constructor for the Circle class, initializes the name, warp space status, center, and radius
Circle::Circle(const std::string& name, bool containsWarpSpace, std::pair<int, int> center, int radius)
    : ShapeTwoD(name, containsWarpSpace), center(center), radius(radius) {}

// Computes the area of the circle using the formula πr²
double Circle::computeArea() const {
    return M_PI * radius * radius;
}

// Generates all points on the perimeter of the circle
std::vector<std::pair<int, int>> Circle::generatePerimeterPoints() const {
    std::vector<std::pair<int, int>> perimeterPoints;

    // Loop through a square bounding box around the circle
    for (int x = center.first - radius; x <= center.first + radius; ++x) {
        for (int y = center.second - radius; y <= center.second + radius; ++y) {
            // Check if the point is approximately on the perimeter of the circle
            if (std::abs(std::sqrt(std::pow(x - center.first, 2) + std::pow(y - center.second, 2)) - radius) < 1e-6) {
                perimeterPoints.emplace_back(x, y);
            }
        }
    }

    return perimeterPoints;
}

// Generates all points within the circle (excluding the perimeter)
std::vector<std::pair<int, int>> Circle::generateInnerPoints() const {
    std::vector<std::pair<int, int>> innerPoints;

   // Loop through the inner square region of the circle
    for (int x = center.first - radius + 1; x < center.first + radius; ++x) {
        for (int y = center.second - radius + 1; y < center.second + radius; ++y) {
            // Check if the point is strictly inside the circle
            if (std::sqrt(std::pow(x - center.first, 2) + std::pow(y - center.second, 2)) < radius) {
                innerPoints.emplace_back(x, y);
            }
        }
    }

    return innerPoints;
}



// Checks if a given point (x, y) is inside the circle
bool Circle::isPointInShape(int x, int y) const {
    // Point is inside if its distance from the center is less than the radius
    return std::sqrt(std::pow(x - center.first, 2) + std::pow(y - center.second, 2)) < radius;
}

// Checks if a given point (x, y) is on the perimeter of the circle
bool Circle::isPointOnShape(int x, int y) const {
    // Point is on the perimeter if its distance from the center is approximately equal to the radius
    return std::abs(std::sqrt(std::pow(x - center.first, 2) + std::pow(y - center.second, 2)) - radius) < 1e-6;
}




// Generates a string representation of the circle with details===============================================
std::string Circle::toString() const {
    std::ostringstream oss;
    oss << std::fixed << std::setprecision(4); // Format numbers with 4 decimal places
    oss << "Shape Name: " << getName() << "\n";
    oss << "Special Type: " << (getContainsWarpSpace() ? "WS" : "NS") << "\n";
    oss << "Area: " << computeArea() << " units square\n";
    oss << "Center: (" << center.first << ", " << center.second << ")\n";
    oss << "Radius: " << radius << "\n";


    // Append all points on the perimeter
    oss << "Points on Perimeter: ";
    for (const auto& p : generatePerimeterPoints()) {
        oss << "(" << p.first << ", " << p.second << ") ";
    }
    oss << "\n";


    // Append all points inside the circle
    oss << "Points within Shape: ";
    for (const auto& p : generateInnerPoints()) {
        oss << "(" << p.first << ", " << p.second << ") ";
    }
    oss << "\n";

    return oss.str();
}

