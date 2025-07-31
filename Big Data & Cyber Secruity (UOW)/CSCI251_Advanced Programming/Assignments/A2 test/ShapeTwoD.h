#pragma once
#include <string>
#include <vector>
#include <utility>

class ShapeTwoD {
protected:
    std::string name;
    bool containsWarpSpace;

public:
    ShapeTwoD(const std::string& name, bool containsWarpSpace);
    virtual ~ShapeTwoD() = default;

    virtual double computeArea() const = 0;
    virtual std::vector<std::pair<int, int>> generatePerimeterPoints() const = 0;
    virtual std::vector<std::pair<int, int>> generateInnerPoints() const = 0;
    virtual bool isPointInShape(int x, int y) const = 0;
    virtual bool isPointOnShape(int x, int y) const = 0;

    std::string getName() const;
    bool getContainsWarpSpace() const;
    void setName(const std::string& name);
    void setContainsWarpSpace(bool containsWarpSpace);

    virtual std::string toString() const = 0;
};

