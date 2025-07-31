// Name: JESLYN HO KA YAN
// UOW ID: 8535383
// Module: CSCI251 Advanced Programming
// Assingment 2


#include <iostream>
#include <vector>
#include <memory>
#include <algorithm>
#include <cctype>
#include <sstream>
#include "A2_Jeslyn.h" // Central header file for all shape-related classes


// Function to display the main menu
void displayMenu() {
    std::cout << "\n\nStudent ID    : 8535383\n";
    std::cout << "Student Name  : Jeslyn Ho Ka Yan\n";
    std::cout << "------------------------------------\n";
    std::cout << "Welcome to Assn2 program!\n\n";
    std::cout << "1) Input sensor data\n";
    std::cout << "2) Compute area (for all records)\n";
    std::cout << "3) Print shapes report\n";
    std::cout << "4) Sort shapes data\n";
    std::cout << "5) Exit\n";
    std::cout << "------------------------------------\n";
}

// Helper function to convert a string to lowercase
std::string toLowerCase(const std::string& str) {
    std::string lower;
    for (char c : str) {
        lower += std::tolower(c); // Convert each character to lowercase
    }
    return lower;
}

int main() {
    std::vector<std::shared_ptr<ShapeTwoD>> shapes; // Vector to store all shape objects
    int choice;

    do {
        displayMenu();
        std::cout << "Please enter your choice: ";
        std::cin >> choice;

        switch (choice) {
			case 1: { // Input sensor data
				std::string name, specialType;
				bool containsWarpSpace;

				std::cout << "\n[ Input sensor data ]\n";
				std::cout << "Please enter name of shape: ";
				std::cin >> name;
				name = toLowerCase(name);

				// Loop until the user provides a valid special type
				while (true) {
					std::cout << "Please enter special type (NS/WS): ";
					std::cin >> specialType;
					specialType = toLowerCase(specialType);

					if (specialType == "ns" || specialType == "ws") {
						containsWarpSpace = (specialType == "ws");
						break; // Valid input, exit loop
					} else {
						std::cout << "Error: Invalid special type. Please enter 'NS' or 'WS' only.\n";
					}
				}

				if (name == "cross") {
					std::vector<std::pair<int, int>> vertices(12);
					for (int i = 0; i < 12; ++i) {
						std::cout << "Please enter x-ordinate of pt. " << (i + 1) << ": ";
						std::cin >> vertices[i].first;
						std::cout << "Please enter y-ordinate of pt. " << (i + 1) << ": ";
						std::cin >> vertices[i].second;
					}
					shapes.push_back(std::make_shared<Cross>("Cross", containsWarpSpace, vertices));
				} else if (name == "circle") {
					int x, y, radius;
					std::cout << "Please enter x-ordinate of center: ";
					std::cin >> x;
					std::cout << "Please enter y-ordinate of center: ";
					std::cin >> y;
					std::cout << "Please enter radius (units): ";
					std::cin >> radius;
					shapes.push_back(std::make_shared<Circle>("Circle", containsWarpSpace, std::make_pair(x, y), radius));
				} else if (name == "rectangle") {
					std::vector<std::pair<int, int>> vertices(4);
					for (int i = 0; i < 4; ++i) {
						std::cout << "Please enter x-ordinate of pt. " << (i + 1) << ": ";
						std::cin >> vertices[i].first;
						std::cout << "Please enter y-ordinate of pt. " << (i + 1) << ": ";
						std::cin >> vertices[i].second;
					}
					shapes.push_back(std::make_shared<Rectangle>("Rectangle", containsWarpSpace, vertices[0], vertices[2]));
				} else if (name == "square") {
					std::vector<std::pair<int, int>> vertices(4);
					for (int i = 0; i < 4; ++i) {
						std::cout << "Please enter x-ordinate of pt. " << (i + 1) << ": ";
						std::cin >> vertices[i].first;
						std::cout << "Please enter y-ordinate of pt. " << (i + 1) << ": ";
						std::cin >> vertices[i].second;
					}
					int sideLength = std::abs(vertices[1].second - vertices[0].second); // Vertical difference
					shapes.push_back(std::make_shared<Square>("Square", containsWarpSpace, vertices[0], sideLength));
				} else {
					std::cout << "Invalid shape name. Please try again.\n";
				}

				std::cout << "\nRecord successfully stored. Going back to main menu ...\n";
				break;
			}

        case 2: { // Compute area for all shapes
            for (const auto& shape : shapes) {
                shape->computeArea();
            }
            std::cout << "\nComputation completed! (" << shapes.size() << " records were updated)\n";
            break;
    }
			case 3: {
			    for (size_t i = 0; i < shapes.size(); ++i) {
			        std::cout << shapes[i]->toString() << "\n";
			    }
			    break;
			}
			case 4: { // Sort shapes data
				if (shapes.empty()) {
					std::cout << "No shapes available to sort. Please input data first.\n";
					break;
				}

				char sortOption;
				do {
					std::cout << "\na) Sort by area (ascending)\n";
					std::cout << "b) Sort by area (descending)\n";
					std::cout << "c) Sort by special type and area\n\n";
					std::cout << "Please select sort option ('q' to go main menu): ";
					std::cin >> sortOption;

					switch (sortOption) {
					case 'a':
						std::sort(shapes.begin(), shapes.end(), [](const std::shared_ptr<ShapeTwoD>& a, const std::shared_ptr<ShapeTwoD>& b) {
						    return a->computeArea() < b->computeArea();
						});
						std::cout << "\nShapes sorted by area (largest to smallest):\n\n";
						break;
					case 'b':
						std::sort(shapes.begin(), shapes.end(), [](const std::shared_ptr<ShapeTwoD>& a, const std::shared_ptr<ShapeTwoD>& b) {
						    return a->computeArea() > b->computeArea();
						});
						std::cout << "\nShapes sorted by area (smallest to largest):\n\n";
						break;
					case 'c':
						std::sort(shapes.begin(), shapes.end(), [](const std::shared_ptr<ShapeTwoD>& a, const std::shared_ptr<ShapeTwoD>& b) {
						    if (a->getContainsWarpSpace() == b->getContainsWarpSpace()) {
						        return a->computeArea() > b->computeArea();
						    }
						    return a->getContainsWarpSpace();
						});
						std::cout << "\nShapes sorted by special type and area:\n\n";
						break;
					case 'q':
						std::cout << "Returning to main menu.\n";
						break;
					default:
						std::cout << "Invalid option. Please try again.\n";
						std::cin.clear();  // Clear error flag
						std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); // Ignore remaining input
					}

					if (sortOption == 'a' || sortOption == 'b' || sortOption == 'c') {
						for (size_t i = 0; i < shapes.size(); ++i) {
						    std::cout << "\nShape [" << i + 1 << "]\n"; // Add index with spacing
						    std::cout << shapes[i]->toString() << "\n";
						}
					}
				} while (sortOption != 'q');

				break;
			}


        case 5: // Exit
            std::cout << "Exiting program.\n";
            break;
        default:
            std::cout << "Invalid choice. Try again.\n";
        }
    } while (choice != 5);

    return 0;
}

