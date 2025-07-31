print("Solution for Implementation3 Task 2");
print("============================");
print("Student number : 10241485");
print("Name : Ho Ka Yan Jeslyn");
print("Date : 15 May 2024");
print("============================");
print("");
print("");
print("");
print(" =================================================================================================================================");
print("(1)Remove a mechanic John Fox.");
print("(1)Before Remove a mechanic John Fox.")
print('(1)db.transport.find({}, { "EMPLOYEE.name": 1, _id: 0 }).pretty();')
db.transport.find({}, { "EMPLOYEE.name": 1, _id: 0 }).pretty();
print(" ");
print("==========================");
print("(1)Remove a mechanic John Fox.")
print('(1) db.transport.remove({"EMPLOYEE.name":"John Fox"});');
db.transport.remove({"EMPLOYEE.name":"John Fox"}
);
print(" ");
print("==========================");
print("(1)After Remove a mechanic John Fox.")
print('(1)db.transport.find({}, { "EMPLOYEE.name": 1, _id: 0 }).pretty();')
db.transport.find({}, { "EMPLOYEE.name": 1, _id: 0 }).pretty();
print(" ");
print(" ");
print(" =================================================================================================================================");
print("(2)Remove information about a date of birth (dob) from a description of employee number 11.");
print("(2)Before Remove information about a date of birth (dob).");
print('(2)db.transport.find({},{ "EMPLOYEE.e#": 1, _id: 0 , "EMPLOYEE.dob":1}).pretty()');
db.transport.find({},{ "EMPLOYEE.e#": 1, _id: 0 , "EMPLOYEE.dob":1}).pretty()
print(" ");
print("==========================");
print("(2) Remove information about a date of birth (dob).");
print('(2) db.transport.update({"EMPLOYEE.e#":"11"},{$unset:{"EMPLOYEE.dob": null}});');
db.transport.update({"EMPLOYEE.e#":"11"},
			{$unset:{"EMPLOYEE.dob": null}}
);
print(" ");
print("==========================");
print("(2)After Remove information about a date of birth (dob).");
print('(2)db.transport.find({},{ "EMPLOYEE.e#": 1, _id: 0 , "EMPLOYEE.dob":1}).pretty()');
db.transport.find({},{ "EMPLOYEE.e#": 1, _id: 0 , "EMPLOYEE.dob":1}).pretty()
print(" ");
print(" ");
print(" =================================================================================================================================");
print("(3)Remove information about a trip number 7 performed by an employee number 11.");
print("(3)Before Remove information about a trip number 7.");
print('(3)db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();');
db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();
print(" ");
print("==========================");
print("(3)Remove information about a trip number 7.");
print('(3) db.transport.update({"EMPLOYEE.e#":"11"}, {"$pull":{"EMPLOYEE.trips":{"trip_number":7}}});');
db.transport.update({"EMPLOYEE.e#":"11"},
    				{"$pull":{"EMPLOYEE.trips.trip_number":7}}
);
print(" ");
print("==========================");
print("(3)After Remove information about a trip number 7.");
print('(3)db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();');
db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();
print(" ");
print(" ");
print(" =================================================================================================================================");
print("(4)Add information about a new trip performed by an employee number 11. At the moment we only know a trip number 999 and registration of truck used PKR786.");
print("(4)Before Adding information about a new trip.");
print('(4)db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();');
db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();
print(" ");
print("==========================");
print("(4)Adding information about a new trip.");
print('(4) db.transport.update({"EMPLOYEE.e#":"11"}, {"$push":{"EMPLOYEE.trips":{"trip_number":999,"registration": "PKR786" }}});');
db.transport.update({"EMPLOYEE.e#":"11"}, 
    			{"$push":{"EMPLOYEE.trips":{"trip_number":999,"registration": "PKR786" }}}
);
print(" ");
print("==========================");
print("(4)After Adding information about a new trip.");
print('(4)db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();');
db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();
print(" ");
print(" ");
print(" =================================================================================================================================");
print("(5)Change a date of a trip number 15 performed by an employee number 11 to 28-SEP-18.");
print("(5)Before Changing a date of a trip number 15");
print('(5)db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();');
db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();
print(" ");
print("==========================");
print("(5)Changing a date of a trip number 15");
print('(5) db.transport.update( {"EMPLOYEE.e#":"11", "EMPLOYEE.trips.trip_number":15},{"$set": {"EMPLOYEE.trips$.date": "28-SEP-18"}});');
db.transport.update({"EMPLOYEE.e#":"11", "EMPLOYEE.trips.trip number":"15"},
    			{"$set": {"EMPLOYEE.trips.$.trip date": "28-SEP-18"}}
);
print(" ");
print("==========================");
print("(5)After Changing a date of a trip number 15");
print('(5)db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();');
db.transport.find({ "EMPLOYEE.e#": "11" }).pretty();
print(" ");
print(" ");