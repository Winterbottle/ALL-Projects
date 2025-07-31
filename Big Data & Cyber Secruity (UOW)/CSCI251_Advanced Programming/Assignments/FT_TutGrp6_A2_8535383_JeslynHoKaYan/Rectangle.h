#pragma once
#include "ShapeTwoD.h"
#include <utility>

class Rectangle : public ShapeTwoD {
protected:
    std::pair<int, int> topLeft;
    std::pair<int, int> bottomRight;

public:
    Rectangle(const std::string& name, bool containsWarpSpace, std::pair<int, int> topLeft, std::pair<int, int> bottomRight);

    double computeArea() const override;
    std::vector<std::pair<int, int>> generatePerimeterPoints() const override;
    std::vector<std::pair<int, int>> generateInnerPoints() const override;
    bool isPointInShape(int x, int y) const override;
    bool isPointOnShape(int x, int y) const override;
    std::string toString() const override;
};

