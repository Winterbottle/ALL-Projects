#ifndef A3_JESLYN_H
#define A3_JESLYN_H

#include <vector>
#include <string>
#include "Point2D.h"
#include "Point3D.h"
#include "Line2D.h"
#include "Line3D.h"

using namespace std;



// Reads data from a file and populates the provided vectors with 2D points, 3D points, 2D lines, and 3D lines.
void readData(const string &filename, vector<Point2D> &point2DList, vector<Point3D> &point3DList,
              vector<Line2D> &line2DList, vector<Line3D> &line3DList);

//Allows the user to specify the current filtering criteria (e.g., filtering by a specific property).
void specifyFilteringCriteria(std::string &currentFilter, std::string &currentCriteria);

// Lets the user define sorting criteria based on the chosen filter (e.g., sorting by distance, coordinates, etc.).
void specifySortingCriteria(std::string &currentCriteria, const std::string &currentFilter);

// Allows the user to specify whether sorting should be in ascending or descending order.
void specifySortingOrder(string &currentOrder);

// Displays the data based on the current filter, sorting criteria, and sorting order.
void viewData(const vector<Point2D> &point2DList, const vector<Point3D> &point3DList,
              const vector<Line2D> &line2DList, const vector<Line3D> &line3DList,
              const string &currentFilter, const string &currentCriteria, const string &currentOrder);

// Stores the filtered and sorted data into a file along with a history of previous views.
void storeData(const std::string &filename, const std::vector<Point2D> &point2DList,
               const std::vector<Point3D> &point3DList, const std::vector<Line2D> &line2DList,
               const std::vector<Line3D> &line3DList, 
               const std::vector<std::tuple<std::string, std::string, std::string>> &viewHistory);

#endif // A3_JESLYN_H

