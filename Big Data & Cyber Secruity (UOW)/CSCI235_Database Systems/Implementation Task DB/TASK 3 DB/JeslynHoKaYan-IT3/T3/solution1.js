print("Solution for Implementation3 Task 1");
print("============================");
print("Student number : 10241485");
print("Name : Ho Ka Yan Jeslyn");
print("Date : 15 May 2024");
print("============================");
print("");
print("(1) Display in a pretty format information about an employee number 11. Do not list information about trips performed by the employee.");
print('(1) db.transport.find({"EMPLOYEE.e#":"11"},{"EMPLOYEE.trips":0}).pretty()');
print(" ");
db.transport.find({"EMPLOYEE.e#":"11"},
		{"EMPLOYEE.trips":0}
).pretty()
print(" ");
print(" =============================================================================");
print("(2) Display in a pretty format information about all mechanics. For each mechanic list only employee number, name, and position.");
print('(2) db.transport.find({"EMPLOYEE.position":"mechanic"},{"EMPLOYEE.e#":1, "EMPLOYEE.name":1,"EMPLOYEE.position":1,"_id":0}).pretty()');
print(" ");
db.transport.find({"EMPLOYEE.position": "mechanic"},
		{"EMPLOYEE.e#":1, "EMPLOYEE.name":1, "EMPLOYEE.position":1,"_id":0}
).pretty()
print(" ");
print(" ");
print(" =============================================================================");
print("(3) Display in a pretty format information about the trucks with the registration numbers PKR768 and PKR008 and SST005. Do not list identifiers of the documents.");
print('(3) db.transport.find({"TRUCK.registration":{$in:["PKR768","PKR008","SST005"]}},{"TRUCK.registration":1, "_id":0}).pretty()');
print(" ");
db.transport.find({"TRUCK.registration":{$in:["PKR768","PKR008","SST005"]}},
		{"TRUCK.registration":1, "_id":0}
).pretty()
print(" ");
print(" ");
print(" =============================================================================");
print("(4) Find the total number of employees.");
print('(4) db.transport.find({"EMPLOYEE: {$exists: true}}).count()');
print(" ");
db.transport.find({"EMPLOYEE": {$exists: true}}
).count()
print(" ");
print(" ");
print(" =============================================================================");
print("(5) Find the total number of mechanics.");
print('(5) db.transport.find({"EMPLOYEE.position":"mechanic"}).count()');
print(" ");
db.transport.find({"EMPLOYEE.position":"mechanic"}
).count()
print(" ");
print(" ");
print(" =============================================================================");
print("(6) Display in a pretty format the names and positions of all employees;");
print('(6) db.transport.find({}, {"EMPLOYEE.name": 1, "EMPLOYEE.position": 1, "_id": 0}).pretty()');
print(" ");
db.transport.find({}, {"EMPLOYEE.name": 1, "EMPLOYEE.position": 1, "_id": 0}
).pretty()
print(" ");
print(" ");
print(" =============================================================================");
print("(7) Display in a pretty format the registrations number of all trucks maintained by John Fox. there is no need to remove duplicates from a listing.");
print('(7) db.transport.find({"EMPLOYEE.name": "John Fox"},{"EMPLOYEE.name": 1,"EMPLOYEE.maintenances": 1, "EMPLOYEE.maintenances.registration":1, "_id": 0}).pretty()');
print(" ");
db.transport.find({"EMPLOYEE.name": "John Fox"},
		{"EMPLOYEE.name": 1,"EMPLOYEE.maintenances": 1, "EMPLOYEE.maintenances.registration":1, "_id": 0}
).pretty()
print(" ");
print(" ");
print(" =============================================================================");
print("(8) Display in a pretty format the names of mechanics who maintained a car with a registration LUCY01.");
print('(8) db.transport.find({"EMPLOYEE.maintenances.registration": "LUCY01", "EMPLOYEE.position": "mechanic"},{"EMPLOYEE.name":1, "_id": 0}).pretty()');
print(" ");
db.transport.find({"EMPLOYEE.maintenances.registration": "LUCY01", "EMPLOYEE.position": "mechanic"},
		{"EMPLOYEE.name":1, "_id": 0}
).pretty()
print(" ");
print(" ");
print(" =============================================================================");
print("(9) Display in a pretty format the names of drivers who performed no trips so far.");
print('(9) db.transport.find({"EMPLOYEE.position":"driver", "EMPLOYEE.trips":[]},{"EMPLOYEE.name":1, "EMPLOYEE.trip":1,"_id": 0}).pretty()');
print(" ");
db.transport.find({"EMPLOYEE.position":"driver", "EMPLOYEE.trips":[]},
		{"EMPLOYEE.name":1, "EMPLOYEE.trip":1,"_id": 0}
).pretty()
print(" ");
print(" ");
print(" =============================================================================");
print("(10) Display in a pretty format names of drivers who started at least on trip in Wollongong.");
print('(10) db.transport.find({"EMPLOYEE.position": "driver", "EMPLOYEE.trips": { $elemMatch: { "legs.0": "Wollongong" }}}, {"EMPLOYEE.name": 1, "_id": 0}).pretty()');
print(" ");
db.transport.find({"EMPLOYEE.position": "driver", "EMPLOYEE.trips": { $elemMatch: { "legs.0": "Wollongong" }}},
    		{"EMPLOYEE.name": 1, "_id": 0}
).pretty()