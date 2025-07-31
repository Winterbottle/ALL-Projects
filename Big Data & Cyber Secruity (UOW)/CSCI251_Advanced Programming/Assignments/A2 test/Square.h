#pragma once
#include "Rectangle.h"
#include <string>

class Square : public Rectangle {
public:
    Square(const std::string& name, bool containsWarpSpace, const std::pair<int, int>& topLeft, int sideLength);

    double computeArea() const override;
    std::vector<std::pair<int, int>> generatePerimeterPoints() const override;
    std::vector<std::pair<int, int>> generateInnerPoints() const override;
    std::string toString() const override;
};

