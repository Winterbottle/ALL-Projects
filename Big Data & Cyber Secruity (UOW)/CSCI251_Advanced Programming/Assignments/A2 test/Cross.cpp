#include "Cross.h"
#include <set>
#include <climits>
#include <algorithm>
#include <cmath>
#include <sstream> // For std::ostringstream

// Constructor
Cross::Cross(const std::string& name, bool containsWarpSpace, const std::vector<std::pair<int, int>>& vertices)
    : ShapeTwoD(name, containsWarpSpace), vertices(vertices) {}

// Compute the area of the cross
double Cross::computeArea() const {
    int area = 0;
    int n = vertices.size();
    for (int i = 0; i < n; ++i) {
        int j = (i + 1) % n; // Next vertex (wrapping around)
        area += vertices[i].first * vertices[j].second;
        area -= vertices[j].first * vertices[i].second;
    }
    return std::abs(area / 2.0);
}

// Generate perimeter points
std::vector<std::pair<int, int>> Cross::generatePerimeterPoints() const {
    std::set<std::pair<int, int>> perimeterSet;
    int n = vertices.size();
    for (int i = 0; i < n; ++i) {
        int x1 = vertices[i].first, y1 = vertices[i].second;
        int x2 = vertices[(i + 1) % n].first, y2 = vertices[(i + 1) % n].second;

        if (x1 == x2) { // Vertical line
            int startY = std::min(y1, y2), endY = std::max(y1, y2);
            for (int y = startY + 1; y < endY; ++y) { // Exclude vertices
                perimeterSet.emplace(x1, y);
            }
        } else if (y1 == y2) { // Horizontal line
            int startX = std::min(x1, x2), endX = std::max(x1, x2);
            for (int x = startX + 1; x < endX; ++x) { // Exclude vertices
                perimeterSet.emplace(x, y1);
            }
        }
    }
    return std::vector<std::pair<int, int>>(perimeterSet.begin(), perimeterSet.end());
}

// Generate inner points
std::vector<std::pair<int, int>> Cross::generateInnerPoints() const {
    std::set<std::pair<int, int>> innerSet;
    int xMin = INT_MAX, xMax = INT_MIN, yMin = INT_MAX, yMax = INT_MIN;

    // Find bounding box
    for (const auto& vertex : vertices) {
        xMin = std::min(xMin, vertex.first);
        xMax = std::max(xMax, vertex.first);
        yMin = std::min(yMin, vertex.second);
        yMax = std::max(yMax, vertex.second);
    }

    // Check points within the bounding box
    for (int x = xMin + 1; x < xMax; ++x) {
        for (int y = yMin + 1; y < yMax; ++y) {
            std::pair<int, int> point = {x, y};
            // Ensure point is inside the shape and not on the perimeter or a vertex
            if (isPointInShape(x, y) &&
                !isPointOnShape(x, y) &&
                std::find(vertices.begin(), vertices.end(), point) == vertices.end()) {
                innerSet.emplace(point);
            }
        }
    }
    return std::vector<std::pair<int, int>>(innerSet.begin(), innerSet.end());
}


// Check if a point is inside the cross
bool Cross::isPointInShape(int x, int y) const {
    int intersections = 0;
    int n = vertices.size();

    for (int i = 0; i < n; ++i) {
        int x1 = vertices[i].first, y1 = vertices[i].second;
        int x2 = vertices[(i + 1) % n].first, y2 = vertices[(i + 1) % n].second;

        if ((y1 <= y && y2 > y) || (y2 <= y && y1 > y)) {
            double intersectX = x1 + (y - y1) * (x2 - x1) / (double)(y2 - y1);
            if (intersectX > x) {
                ++intersections;
            }
        }
    }

    return intersections % 2 != 0; // Odd intersections mean the point is inside
}

// Check if a point is on the shape's perimeter
bool Cross::isPointOnShape(int x, int y) const {
    int n = vertices.size();
    for (int i = 0; i < n; ++i) {
        int x1 = vertices[i].first, y1 = vertices[i].second;
        int x2 = vertices[(i + 1) % n].first, y2 = vertices[(i + 1) % n].second;

        if (x1 == x2 && x == x1 && y > std::min(y1, y2) && y < std::max(y1, y2)) {
            return true; // Vertical line
        } else if (y1 == y2 && y == y1 && x > std::min(x1, x2) && x < std::max(x1, x2)) {
            return true; // Horizontal line
        }
    }
    return false;
}

// To string representation
std::string Cross::toString() const {
    std::ostringstream oss;
    oss << ShapeTwoD::toString();
    oss << "\nVertices:\n";
    for (size_t i = 0; i < vertices.size(); ++i) {
        oss << "Point [" << i << "]: (" << vertices[i].first << ", " << vertices[i].second << ")\n";
    }

    auto perimeterPoints = generatePerimeterPoints();
    oss << "Points on Perimeter: ";
    for (const auto& point : perimeterPoints) {
        oss << "(" << point.first << ", " << point.second << ") ";
    }

    auto innerPoints = generateInnerPoints();
    oss << "\nPoints within Shape: ";
    for (const auto& point : innerPoints) {
        oss << "(" << point.first << ", " << point.second << ") ";
    }

    return oss.str();
}

