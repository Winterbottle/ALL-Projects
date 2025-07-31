#ifndef CROSS_H
#define CROSS_H

#include "ShapeTwoD.h"
#include <vector>
#include <utility>
#include <string>

class Cross : public ShapeTwoD {
public:
    Cross(const std::string& name, bool containsWarpSpace, const std::vector<std::pair<int, int>>& vertices);
    ~Cross() override = default;

    double computeArea() const override;
    std::vector<std::pair<int, int>> generatePerimeterPoints() const override;
    std::vector<std::pair<int, int>> generateInnerPoints() const override;
    bool isPointInShape(int x, int y) const override;
    bool isPointOnShape(int x, int y) const override;
    std::string toString() const override;

private:
    std::vector<std::pair<int, int>> vertices;
    std::vector<std::pair<int, int>> perimeterPoints;
    std::vector<std::pair<int, int>> innerPoints;

    void calculatePerimeterPoints();
    void calculateInnerPoints();
};

#endif // CROSS_H

