#include "ShapeTwoD.h"
#include <sstream>

// Implement the toString method
std::string ShapeTwoD::toString() const {
    std::ostringstream oss;

    oss << "Shape Name: " << name << "\n";
    oss << "Special Type: " << (containsWarpSpace ? "WS" : "NS") << "\n";
    oss << "Area: " << computeArea() << " units square\n";

    return oss.str();
}


ShapeTwoD::ShapeTwoD(const std::string& name, bool containsWarpSpace)
    : name(name), containsWarpSpace(containsWarpSpace) {}

std::string ShapeTwoD::getName() const {
    return name;
}

bool ShapeTwoD::getContainsWarpSpace() const {
    return containsWarpSpace;
}

void ShapeTwoD::setName(const std::string& name) {
    this->name = name;
}

void ShapeTwoD::setContainsWarpSpace(bool containsWarpSpace) {
    this->containsWarpSpace = containsWarpSpace;
}

