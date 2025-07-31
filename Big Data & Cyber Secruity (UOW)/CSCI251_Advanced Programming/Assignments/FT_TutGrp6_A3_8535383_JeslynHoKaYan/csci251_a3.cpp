// Name: JESLYN HO KA YAN
// UOW ID: 8535383
// Module: CSCI251 Advanced Programming
// Assingment 3

#include <termios.h>
#include <unistd.h>
#include <iostream>
#include <fstream>
#include <vector>
#include <iomanip>
#include <algorithm>
#include <cstring>
#include <set>
#include <tuple>

#include "csci251_a3.h"
#include "MyTemplates.h"

using namespace std;

bool fileLoaded = false; // Global variable to check if data is loaded

// Function to pause execution until a key is pressed
void waitForAnyKey() {
    struct termios oldt, newt;
    tcgetattr(STDIN_FILENO, &oldt);
    newt = oldt;
    newt.c_lflag &= ~(ICANON | ECHO);
    tcsetattr(STDIN_FILENO, TCSANOW, &newt);
    getchar();
    tcsetattr(STDIN_FILENO, TCSANOW, &oldt);
}


// Comparator for sorting Line3D objects
struct Line3DComparator {
    bool operator()(const Line3D &a, const Line3D &b) const {
        if (a.getPt1().getX() != b.getPt1().getX()) return a.getPt1().getX() < b.getPt1().getX();
        if (a.getPt1().getY() != b.getPt1().getY()) return a.getPt1().getY() < b.getPt1().getY();
        if (a.getPt1().getZ() != b.getPt1().getZ()) return a.getPt1().getZ() < b.getPt1().getZ();
        if (a.getPt2().getX() != b.getPt2().getX()) return a.getPt2().getX() < b.getPt2().getX();
        if (a.getPt2().getY() != b.getPt2().getY()) return a.getPt2().getY() < b.getPt2().getY();
        return a.getPt2().getZ() < b.getPt2().getZ();
    }
};



int main() {
    string studentID = "8535383";
    string studentName = "Jeslyn Ho Ka Yan";

    vector<Point2D> point2DList;
    vector<Point3D> point3DList;
    vector<Line2D> line2DList;
    vector<Line3D> line3DList;

    vector<tuple<string, string, string>> viewHistory;  // Store filtering & sorting history

	// Default filter 
    string currentFilter = "Point2D";
    string currentCriteria = "x-coordinate"; // Default sorting criteria
    string currentOrder = "ASC";

    string filename; // Declare filename variable

    int choice;

    while (true) {
	// Display menu options
        cout << "\n\n-------------------------------------------" << endl;
        cout << "Student ID    : " << studentID << endl;
        cout << "Student Name  : " << studentName << endl;
        cout << "-------------------------------------------" << endl;
        cout << "Welcome to Assn3 program!\n" << endl;
        cout << "1) Read in data" << endl;
        cout << "2) Specify filtering criteria (current: " << currentFilter << ")" << endl;
        cout << "3) Specify sorting criteria (current: " << currentCriteria << ")" << endl;
        cout << "4) Specify sorting order (current: " << currentOrder << ")" << endl;
        cout << "5) View data" << endl;
        cout << "6) Store data" << endl;
        cout << "7) Exit" << endl;
        cout << "\nPlease enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1: {
                cout << "\nPlease enter filename: ";
                cin >> filename;
                readData(filename, point2DList, point3DList, line2DList, line3DList);
                break;
            }
            case 2:
                specifyFilteringCriteria(currentFilter, currentCriteria);
                break;
            case 3:
                specifySortingCriteria(currentCriteria, currentFilter);
                break;
            case 4:
                specifySortingOrder(currentOrder);
                break;
            case 5:
                viewData(point2DList, point3DList, line2DList, line3DList, currentFilter, currentCriteria, currentOrder);
                viewHistory.push_back(make_tuple(currentFilter, currentCriteria, currentOrder)); 
                break;
            case 6: {
                cout << "\nPlease enter filename to save data: ";
                cin >> filename; //Get filename from user before storing
                storeData(filename, point2DList, point3DList, line2DList, line3DList, viewHistory);
                cout << "\nGoing back to main menu...";
                break;
            }
            case 7:
                cout << "Exiting program. Goodbye!" << endl;
                return 0;
            default:
                cout << "Invalid choice. Please try again." << endl;
        }
    }
    return 0;
}



// Function to read data from a file
void readData(const string &filename, vector<Point2D> &point2DList,
              vector<Point3D> &point3DList, vector<Line2D> &line2DList,
              vector<Line3D> &line3DList) {
    ifstream file(filename);
    if (!file) {
        cout << "Error: Cannot open file." << endl;
        return;
    }

    int recordCount = 0; // Counter for total records read
    string line;

    while (getline(file, line)) {
        istringstream iss(line);
        string type;
        getline(iss, type, ','); // Extract type

        // Remove spaces and commas from type
        type.erase(remove(type.begin(), type.end(), ' '), type.end());
        type.erase(remove(type.begin(), type.end(), ','), type.end());

        if (type == "Point2D") {
            int x, y;
            char ignoreChar;
            iss >> ignoreChar >> x >> ignoreChar >> y >> ignoreChar;
            Point2D p(x, y);
            point2DList.push_back(p);
            recordCount++;
        } 
        else if (type == "Point3D") {
            int x, y, z;
            char ignoreChar;
            iss >> ignoreChar >> x >> ignoreChar >> y >> ignoreChar >> z >> ignoreChar;
            Point3D p(x, y, z);
            point3DList.push_back(p);
            recordCount++;
        } 
        else if (type == "Line2D") {
            int x1, y1, x2, y2;
            char ignoreChar;
            iss >> ignoreChar >> x1 >> ignoreChar >> y1 >> ignoreChar >> ignoreChar; // Read [x1, y1]
            iss >> ignoreChar >> x2 >> ignoreChar >> y2 >> ignoreChar; // Read [x2, y2]
            Line2D newLine(Point2D(x1, y1), Point2D(x2, y2));
            line2DList.push_back(newLine);
            recordCount++;
        }
        else if (type == "Line3D") {
            vector<int> values;
            char *token;

            // Convert the line to a C-string for `strtok()`
            char lineCStr[100];
            strcpy(lineCStr, line.c_str());

            // Skip "Line3D"
            token = strtok(lineCStr, " ,[]");

            // Extract all numbers dynamically
            while ((token = strtok(NULL, " ,[]")) != NULL) {
                values.push_back(atoi(token));
            }

            // Ensure we have exactly 6 values (x1, y1, z1, x2, y2, z2)
            if (values.size() != 6) {
                cout << "Error: Invalid Line3D format! Expected 6 values but got " << values.size() << endl;
                return;
            }

            // Assign the values
            int x1 = values[0], y1 = values[1], z1 = values[2];
            int x2 = values[3], y2 = values[4], z2 = values[5];

            Line3D newLine(Point3D(x1, y1, z1), Point3D(x2, y2, z2));
            line3DList.push_back(newLine);
            recordCount++;
        }
    }

    file.close();
    fileLoaded = true;
    cout << recordCount << " records read in successfully!" << endl;
    cout << "\nGoing back to main menu ..." << endl;
}




void viewData(const vector<Point2D> &point2DList, 
              const vector<Point3D> &point3DList,
              const vector<Line2D> &line2DList,
              const vector<Line3D> &line3DList,
              const string &currentFilter, 
              const string &currentCriteria, 
              const string &currentOrder) {

    std::cout << "\n\n[ View data ... ]" << std::endl;
    std::cout << "Filtering criteria: " << currentFilter << std::endl;
    std::cout << "Sorting criteria: " << currentCriteria << std::endl;
    std::cout << "Sorting order: " << currentOrder << std::endl;
	
    if (!fileLoaded) {
        cout << "Error: No data loaded. Please read in data first." << endl;
        return;
    }

	if (currentFilter == "Point2D") {
		if (point2DList.empty()) {
		    cout << "Error: No Point2D records found!" << endl;
		    return;
		}

		// Remove duplicates for Point2D
		vector<Point2D> uniquePoint2DList = point2DList;
		sort(uniquePoint2DList.begin(), uniquePoint2DList.end());
		uniquePoint2DList.erase(unique(uniquePoint2DList.begin(), uniquePoint2DList.end()), uniquePoint2DList.end());

		vector<Point2D> sortedList = uniquePoint2DList;

		// Sort by Dist. Fr Origin
		if (currentCriteria == "Dist. Fr Origin") {
		    sort(sortedList.begin(), sortedList.end(), [](const Point2D &a, const Point2D &b) {
		        return a.getScalarValue() < b.getScalarValue();
		    });
		}

		if (currentCriteria == "y-ordinate") {
			sort(sortedList.begin(), sortedList.end(), [](const Point2D &a, const Point2D &b) {
				if (a.getY() != b.getY()) return a.getY() < b.getY();  // Primary: Sort by Y
				return a.getX() < b.getX();  // Secondary: Sort by X if Y is equal
			});
		}

		if (currentCriteria == "x-ordinate") {
		    sort(sortedList.begin(), sortedList.end(), [](const Point2D &a, const Point2D &b) {
		        if (a.getX() != b.getX()) return a.getX() < b.getX();  // Primary: Sort by X
		        return a.getY() < b.getY();  // Secondary: Sort by Y if X is equal
		    });
		}

		// Apply DESC order if needed
		if (currentOrder == "DESC") {
		    reverse(sortedList.begin(), sortedList.end());
		}

		// Display the sorted data
		cout << "    X     Y    Dist. Fr Origin" << endl;
		cout << "- - - - - - - - - - - - - - - -" << endl;
		for (const auto &point : sortedList) {
		    cout << "[" << setw(4) << point.getX() << ", " << setw(4) << point.getY() << "]"
		         << setw(10) << fixed << setprecision(3) << point.getScalarValue() << endl;
		}
	}
	else if (currentFilter == "Point3D") {
		if (point3DList.empty()) {
		    cout << "Error: No Point3D records found!" << endl;
		    return;
		}

		vector<Point3D> sortedList = point3DList; // Copy original list

		// Remove duplicates
		sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
		    return std::make_tuple(a.getX(), a.getY(), a.getZ()) < std::make_tuple(b.getX(), b.getY(), b.getZ());

		});

		sortedList.erase(unique(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
		    return a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ();
		}), sortedList.end());

		// Sorting based on selected criteria
		if (currentCriteria == "x-ordinate") {
			sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
				if (a.getX() != b.getX()) return a.getX() < b.getX();  // Primary: Sort by X
				if (a.getY() != b.getY()) return a.getY() < b.getY();  // Secondary: Sort by Y
				return a.getZ() < b.getZ();  // Tertiary: Sort by Z
			});

		} 
		else if (currentCriteria == "y-ordinate") {
		    sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
		        if (a.getY() != b.getY()) return a.getY() < b.getY();
		        if (a.getX() != b.getX()) return a.getX() < b.getX();
		        return a.getZ() < b.getZ();
		    });
		} 
		else if (currentCriteria == "z-ordinate") {
		    sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
		        if (a.getZ() != b.getZ()) return a.getZ() < b.getZ();
		        if (a.getX() != b.getX()) return a.getX() < b.getX();
		        return a.getY() < b.getY();
		    });
		} 
		else if (currentCriteria == "Dist. Fr Origin") {
		    sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
		        return a.getScalarValue() < b.getScalarValue();
		    });
		}

		// Reverse if order is DESC
		if (currentOrder == "DESC") {
		    reverse(sortedList.begin(), sortedList.end());
		}

		// Display sorted data
		cout << "    X     Y     Z    Dist. Fr Origin" << endl;
		cout << "- - - - - - - - - - - - - - - - - - -" << endl;
		for (const auto &point : sortedList) {
		    cout << "[" << setw(4) << point.getX() << ", " 
		         << setw(4) << point.getY() << ", " 
		         << setw(4) << point.getZ() << "]"
		         << setw(10) << fixed << setprecision(3) << point.getScalarValue() << endl;
		}
	 } 
	else if (currentFilter == "Line2D") {
		if (line2DList.empty()) {
		    cout << "Error: No Line2D records found!" << endl;
		    return;
		}

		// Remove duplicates for Line2D
		vector<Line2D> uniqueLine2DList = line2DList;
		sort(uniqueLine2DList.begin(), uniqueLine2DList.end());
		uniqueLine2DList.erase(unique(uniqueLine2DList.begin(), uniqueLine2DList.end()), uniqueLine2DList.end());

		vector<Line2D> sortedList = uniqueLine2DList;

		// Sorting logic for Line2D
		if (currentCriteria == "Pt. 1") {
		    sort(sortedList.begin(), sortedList.end(), [](const Line2D &a, const Line2D &b) {
		        if (a.getPt1().getX() == b.getPt1().getX()) {
		            return a.getPt1().getY() < b.getPt1().getY();
		        }
		        return a.getPt1().getX() < b.getPt1().getX();
		    });
		} else if (currentCriteria == "Pt. 2") {
		    sort(sortedList.begin(), sortedList.end(), [](const Line2D &a, const Line2D &b) {
		        if (a.getPt2().getX() == b.getPt2().getX()) {
		            return a.getPt2().getY() < b.getPt2().getY();
		        }
		        return a.getPt2().getX() < b.getPt2().getX();
		    });
		} else if (currentCriteria == "Length") {
		    sort(sortedList.begin(), sortedList.end(), [](const Line2D &a, const Line2D &b) {
		        return a.getScalarValue() < b.getScalarValue();
		    });
		}

		// Apply DESC order if needed
		if (currentOrder == "DESC") {
		    reverse(sortedList.begin(), sortedList.end());
		}

		// Display the sorted data
		cout << " P1-X  P1-Y     P2-X  P2-Y    Length" << endl;
		cout << "- - - - - - - - - - - - - - - - - - -" << endl;
		for (const auto &line : sortedList) {
		    cout << "[" << setw(4) << line.getPt1().getX() << ", " << setw(4) << line.getPt1().getY() << "]"
		         << "   [" << setw(4) << line.getPt2().getX() << ", " << setw(4) << line.getPt2().getY() << "]"
		         << setw(10) << fixed << setprecision(3) << line.getScalarValue() << endl;
		}

	}

	else if (currentFilter == "Line3D") { 
		vector<Line3D> sortedList = line3DList;  // ✅ Copy the list for sorting
		// ✅ Step 2: Remove duplicates using std::set
		set<Line3D, Line3DComparator> uniqueLines(sortedList.begin(), sortedList.end());
		sortedList.assign(uniqueLines.begin(), uniqueLines.end());

		// ✅ Step 3: Apply sorting **AGAIN** after removing duplicates
		if (currentCriteria == "Pt. 1") {
		    sort(sortedList.begin(), sortedList.end(), [](const Line3D &a, const Line3D &b) {
		        if (a.getPt1().getX() != b.getPt1().getX())
		            return a.getPt1().getX() < b.getPt1().getX();
		        if (a.getPt1().getY() != b.getPt1().getY())
		            return a.getPt1().getY() < b.getPt1().getY();
		        return a.getPt1().getZ() < b.getPt1().getZ();
		    });
		} 
		else if (currentCriteria == "Pt. 2") {
		    sort(sortedList.begin(), sortedList.end(), [](const Line3D &a, const Line3D &b) {
		        if (a.getPt2().getX() != b.getPt2().getX())
		            return a.getPt2().getX() < b.getPt2().getX();
		        if (a.getPt2().getY() != b.getPt2().getY())
		            return a.getPt2().getY() < b.getPt2().getY();
		        return a.getPt2().getZ() < b.getPt2().getZ();
		    });
		} 
		else if (currentCriteria == "Length") {
		    sort(sortedList.begin(), sortedList.end(), [](const Line3D &a, const Line3D &b) {
		        return a.getScalarValue() < b.getScalarValue();
		    });
		}

		// ✅ Step 4: Apply DESC order if needed
		if (currentOrder == "DESC") {
		    reverse(sortedList.begin(), sortedList.end());
		}


		// ✅ Display sorted data
		cout << " P1-X  P1-Y  P1-Z     P2-X  P2-Y  P2-Z    Length" << endl;
		cout << "- - - - - - - - - - - - - - - - - - - - - - - - -" << endl;
		for (const auto &line : sortedList) {
		    cout << "[" << setw(4) << line.getPt1().getX() << ", " << setw(4) << line.getPt1().getY() << ", " << setw(4) << line.getPt1().getZ() << "]"
		         << "   [" << setw(4) << line.getPt2().getX() << ", " << setw(4) << line.getPt2().getY() << ", " << setw(4) << line.getPt2().getZ() << "]"
		         << setw(10) << fixed << setprecision(3) << line.getScalarValue() << endl;
		}

		//cout << "Press any key to go back to the main menu ..." << endl;
	}

    cout << "\nPress any key to go back to the main menu ..." << endl;
    cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Clear the input buffer
    waitForAnyKey();
}




void specifyFilteringCriteria(std::string &currentFilter, std::string &currentCriteria) {
    std::cout << "[ Specifying filtering criteria (current: " << currentFilter << ") ]" << std::endl;
    std::cout << "a) Point2D records\nb) Point3D records\nc) Line2D records\nd) Line3D records" << std::endl;
    std::cout << "Please enter your criteria (a-d): ";
    char filterChoice;
    std::cin >> filterChoice;

    switch (filterChoice) {
        case 'a': 
            currentFilter = "Point2D"; 
            currentCriteria = "x-ordinate"; // Default sorting for Point2D
            break;
        case 'b': 
            currentFilter = "Point3D"; 
            currentCriteria = "x-ordinate"; // Default sorting for Point3D
            break;
        case 'c': 
            currentFilter = "Line2D"; 
            currentCriteria = "Pt. 1"; // Default sorting for Line2D
            break;
        case 'd': 
            currentFilter = "Line3D"; 
            currentCriteria = "Pt. 1 "; // Default sorting for Line3D
            break;
        default: 
            std::cout << "Invalid choice. No changes made." << std::endl;
            return;
    }

    std::cout << "Filter criteria successfully set to '" << currentFilter << "'!" << std::endl;

}


void specifySortingCriteria(string &currentCriteria, const string &currentFilter) {
    cout << "[ Specifying sorting criteria (current: " << currentCriteria << ") ]" << endl;

    // Display options based on the current filtering criteria
    if (currentFilter == "Point2D") {
        cout << "a) X ordinate value" << endl;
        cout << "b) Y ordinate value" << endl;
        cout << "c) Dist. Fr Origin value" << endl;
    } else if (currentFilter == "Point3D") {
        cout << "a) X ordinate value " << endl;
        cout << "b) Y ordinate value" << endl;
        cout << "c) Z ordinate value" << endl;
        cout << "d) Dist. Fr Origin value" << endl;
    } else if (currentFilter == "Line2D") {
        cout << "a) Pt. 1's (x, y) values" << endl;
        cout << "b) Pt. 2's (x, y) values" << endl;
        cout << "c) Length value" << endl;
    } else if (currentFilter == "Line3D") {
        cout << "a) Pt. 1's (x, y, z) values" << endl;
        cout << "b) Pt. 2's (x, y, z) values" << endl;
        cout << "c) Length value" << endl;
    } else {
        cout << "Invalid filter type for sorting criteria." << endl;
        return;
    }

    // Take user input for sorting criteria
    cout << "Please enter your criteria (a";
    if (currentFilter == "Point3D" || currentFilter == "Line3D") {
        cout << "-d";
    } else {
        cout << "-c";
    }
    cout << "): ";
    char criteriaChoice;
    cin >> criteriaChoice;

    // Set currentCriteria based on input
    if (currentFilter == "Point2D") {
        switch (criteriaChoice) {
            case 'a': currentCriteria = "x-ordinate"; break;
            case 'b': currentCriteria = "y-ordinate"; break;
            case 'c': currentCriteria = "Dist. Fr Origin"; break;
            default: cout << "Invalid choice. No changes made." << endl; return;
        }
    } else if (currentFilter == "Point3D") {
        switch (criteriaChoice) {
            case 'a': currentCriteria = "x-ordinate"; break;
            case 'b': currentCriteria = "y-ordinate"; break;
            case 'c': currentCriteria = "z-ordinate"; break;
            case 'd': currentCriteria = "Dist. Fr Origin"; break;
            default: cout << "Invalid choice. No changes made." << endl; return;
        }
    } else if (currentFilter == "Line2D") {
        switch (criteriaChoice) {
            case 'a': currentCriteria = "Pt. 1"; break;  // ✅ Remove extra space
            case 'b': currentCriteria = "Pt. 2"; break;  // ✅ Remove extra space
            case 'c': currentCriteria = "Length"; break;
            default: cout << "Invalid choice. No changes made." << endl; return;
        }
    } else if (currentFilter == "Line3D") {
        switch (criteriaChoice) {
            case 'a': currentCriteria = "Pt. 1"; break;
            case 'b': currentCriteria = "Pt. 2"; break;  // ✅ Remove extra space
            case 'c': currentCriteria = "Length"; break;
            default: cout << "Invalid choice. No changes made." << endl; return;
        }
    }

    cout << "Sorting criteria successfully set to '" << currentCriteria << "'!" << endl;
}

// Function to specify sorting order
void specifySortingOrder(string &currentOrder) {
    cout << "[ Specifying sorting order (current: " << currentOrder << ") ]" << endl;
    cout << "a) ASC (Ascending)\nb) DESC (Descending)" << endl;
    cout << "Please enter your choice (a-b): ";
    char orderChoice;
    cin >> orderChoice;

	// Process user input and set sorting order accordingly
    switch (orderChoice) {
        case 'a': currentOrder = "ASC"; break;
        case 'b': currentOrder = "DESC"; break;
        default: cout << "Invalid choice. No changes made." << endl; return;
    }
    // Confirm sorting order change
    cout << "Sorting order successfully set to '" << currentOrder << "'!" << endl;
}




void storeData(const string &filename, 
               const vector<Point2D> &point2DList, 
               const vector<Point3D> &point3DList, 
               const vector<Line2D> &line2DList, 
               const vector<Line3D> &line3DList, 
               const vector<tuple<string, string, string>> &viewHistory) {
    
    // Open the output file for writing
    ofstream file(filename);
    if (!file) {
        cout << "Error: Unable to open file for writing." << endl;
        return;
    }

    int recordCount = 0; // Counter for total records written

    // Loop through viewHistory to write multiple outputs
    for (size_t i = 0; i < viewHistory.size(); ++i) { 
        string filter = std::get<0>(viewHistory[i]);    // Extract filtering criteria
        string criteria = std::get<1>(viewHistory[i]);  // Extract sorting criteria
        string order = std::get<2>(viewHistory[i]);     // Extract sorting order

        // Write header information for this data set
        file << "\n[ View data ... ]\n";
        file << "Filtering criteria: " << filter << "\n";
        file << "Sorting criteria: " << criteria << "\n";
        file << "Sorting order: " << order << "\n\n";

        // Process Point2D records
        if (filter == "Point2D") {
            set<Point2D> uniquePoints(point2DList.begin(), point2DList.end()); // Remove duplicates
            vector<Point2D> sortedList(uniquePoints.begin(), uniquePoints.end());

            // Apply sorting based on criteria
            if (criteria == "x-ordinate") {
                sort(sortedList.begin(), sortedList.end(), [](const Point2D &a, const Point2D &b) {
                    return a.getX() < b.getX();
                });
            } else if (criteria == "y-ordinate") {
                sort(sortedList.begin(), sortedList.end(), [](const Point2D &a, const Point2D &b) {
                    return a.getY() < b.getY();
                });
            } else if (criteria == "Dist. Fr Origin") {
                sort(sortedList.begin(), sortedList.end(), [](const Point2D &a, const Point2D &b) {
                    return a.getScalarValue() < b.getScalarValue();
                });
            }

            // Reverse list for descending order
            if (order == "DESC") {
                reverse(sortedList.begin(), sortedList.end());
            }

            // Write header
            file << "    X     Y    Dist. Fr Origin\n";
            file << "------------------------------------\n";
            for (const auto &point : sortedList) {
                file << "[" << setw(4) << point.getX() << ", " << setw(4) << point.getY() << "]"
                     << setw(10) << fixed << setprecision(3) << point.getScalarValue() << endl;
                recordCount++;
            }
            file << "-------------------------------------------------\n";
        }

        // Process Point3D records
        else if (filter == "Point3D") {
            set<Point3D> uniquePoints(point3DList.begin(), point3DList.end()); // Remove duplicates
            vector<Point3D> sortedList(uniquePoints.begin(), uniquePoints.end());

            // Apply sorting based on criteria
            if (criteria == "x-ordinate") {
                sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
                    return a.getX() < b.getX();
                });
            } else if (criteria == "y-ordinate") {
                sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
                    return a.getY() < b.getY();
                });
            } else if (criteria == "z-ordinate") {
                sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
                    return a.getZ() < b.getZ();
                });
            } else if (criteria == "Dist. Fr Origin") {
                sort(sortedList.begin(), sortedList.end(), [](const Point3D &a, const Point3D &b) {
                    return a.getScalarValue() < b.getScalarValue();
                });
            }

            // Reverse list for descending order
            if (order == "DESC") {
                reverse(sortedList.begin(), sortedList.end());
            }

            // Write header
            file << "    X     Y     Z    Dist. Fr Origin\n";
            file << "------------------------------------\n";
            for (const auto &point : sortedList) {
                file << "[" << setw(4) << point.getX() << ", " << setw(4) << point.getY() << ", " << setw(4) << point.getZ() << "]"
                     << setw(10) << fixed << setprecision(3) << point.getScalarValue() << endl;
                recordCount++;
            }
            file << "-------------------------------------------------\n";
        }

        // Process Line2D records
        else if (filter == "Line2D") {
            set<Line2D> uniqueLines(line2DList.begin(), line2DList.end()); //  Remove duplicates
            vector<Line2D> sortedList(uniqueLines.begin(), uniqueLines.end());

            // Apply sorting based on criteria
            if (criteria == "Pt. 1") {
                sort(sortedList.begin(), sortedList.end(), [](const Line2D &a, const Line2D &b) {
                    return a.getPt1().getX() < b.getPt1().getX();
                });
            } else if (criteria == "Pt. 2") {
                sort(sortedList.begin(), sortedList.end(), [](const Line2D &a, const Line2D &b) {
                    return a.getPt2().getX() < b.getPt2().getX();
                });
            } else if (criteria == "Length") {
                sort(sortedList.begin(), sortedList.end(), [](const Line2D &a, const Line2D &b) {
                    return a.getScalarValue() < b.getScalarValue();
                });
            }

            // Reverse list for descending order
            if (order == "DESC") {
                reverse(sortedList.begin(), sortedList.end());
            }

            // Write header
            file << " P1-X  P1-Y     P2-X  P2-Y    Length\n";
            file << "-------------------------------------\n";
            for (const auto &line : sortedList) {
                file << "[" << setw(4) << line.getPt1().getX() << ", " << setw(4) << line.getPt1().getY() << "]"
                     << "   [" << setw(4) << line.getPt2().getX() << ", " << setw(4) << line.getPt2().getY() << "]"
                     << setw(10) << fixed << setprecision(3) << line.getScalarValue() << endl;
                recordCount++;
            }
            file << "-------------------------------------------------\n";
        }

        // Process Line3D records
        else if (filter == "Line3D") {
            file << " P1-X  P1-Y  P1-Z     P2-X  P2-Y  P2-Z    Length\n";
            file << "-------------------------------------------------\n";

            set<Line3D> uniqueLines(line3DList.begin(), line3DList.end()); // Remove duplicates
            vector<Line3D> sortedList(uniqueLines.begin(), uniqueLines.end());

            // Apply sorting based on criteria
            if (criteria == "Pt. 1") {
                sort(sortedList.begin(), sortedList.end(), [](const Line3D &a, const Line3D &b) {
                    return a.getPt1().getX() < b.getPt1().getX();
                });
            } else if (criteria == "Pt. 2") {
                sort(sortedList.begin(), sortedList.end(), [](const Line3D &a, const Line3D &b) {
                    return a.getPt2().getX() < b.getPt2().getX();
                });
            } else if (criteria == "Length") {
                sort(sortedList.begin(), sortedList.end(), [](const Line3D &a, const Line3D &b) {
                    return a.getScalarValue() < b.getScalarValue();
                });
            }

            // Reverse list for descending order
            if (order == "DESC") reverse(sortedList.begin(), sortedList.end());

            // Write sorted records
            for (const auto &line : sortedList) {
                file << "[" << setw(4) << line.getPt1().getX() << ", " << setw(4) << line.getPt1().getY() << ", " << setw(4) << line.getPt1().getZ() << "]"
                     << "   [" << setw(4) << line.getPt2().getX() << ", " << setw(4) << line.getPt2().getY() << ", " << setw(4) << line.getPt2().getZ() << "]"
                     << setw(10) << fixed << setprecision(3) << line.getScalarValue() << endl;
                recordCount++;
            }
        }
    }

    file.close();
    cout << recordCount << " records output successfully to '" << filename << "'!" << endl;
}


