print("Solution for Assingnment3 Task 1");
print("============================");
print("Student number :  10241485"    );
print("Name :  Ho Ka Yan Jeslyn"         );
print("Date :  15 May 2024"           );
print("============================");
print("");
use  10241485
db.createCollection("kyjho")
print("");
db.kyjho.insertMany([
    {
        "AIRLINE": {
            "_id": "airline_SQ",
            "airlineCode": "SQ",
            "airlineName": "Singapore Airlines",
            "publishes": [{ "PUBLISHED-FLIGHT": {
                        "flightNum": "SQ0001",
                        "effectiveDate": ISODate("2024-01-01T00:00:00Z"),
                        "expiryDate": ISODate("2024-01-30T00:00:00Z"),
                        "frequency": "Daily",
                        "has": [{ "FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-01-01T00:00:00Z"),
                                    "scheduledDepartureTime": "10:10:10",
                                    "scheduledDuration": "03:30",
                                    "to": [{"AIRPORT": {
                                                "IATACode": "SIN",
                                                "airportName": "Singapore Changi Airport"
 }}]}},
                            {"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-01-01T00:00:00Z"),
                                    "scheduledDepartureTime": "20:45:00",
                                    "scheduledDuration": "10:00",
                                    "to": [{ "AIRPORT": {
                                                "IATACode": "HLP",
                                                "airportName": "Halim Perdana Kusuma Airport Jakarta"
 }}]}}]}},
                {"PUBLISHED-FLIGHT": {
                        "flightNum": "SQ0002",
                        "effectiveDate": ISODate("2024-02-02T00:00:00Z"),
                        "expiryDate": ISODate("2024-02-29T00:00:00Z"),
                        "frequency": "Weekly",
                        "has": [{"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-02-02T00:00:00Z"),
                                    "scheduledDepartureTime": "12:00:00",
                                    "scheduledDuration": "02:00",
                                    "to": [{"AIRPORT": {
                                                "IATACode": "CKG",
                                                "airportName": "Chongqing Jiangbei International Airport"
}}]}},
                            {"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-02-02T00:00:00Z"),
                                    "scheduledDepartureTime": "14:15:02",
                                    "scheduledDuration": "04:00",
                                    "to": [{"AIRPORT": {
                                                "IATACode": "SIN",
                                                "airportName": "Singapore Changi Airport"
}}]}}]}}]}},

    {"AIRLINE": {
            "_id": "airline_CX",
            "airlineCode": "CX",
            "airlineName": "Cathay Pacific",
            "publishes": [{ "PUBLISHED-FLIGHT": {
                        "flightNum": "CX0001",
                        "effectiveDate": ISODate("2024-03-03T00:00:00Z"),
                        "expiryDate": ISODate("2024-03-30T00:00:00Z"),
                        "frequency": "Daily",
                        "has": [{"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-03-03T00:00:00Z"),
                                    "scheduledDepartureTime": "15:15:15",
                                    "scheduledDuration": "03:30",
                                    "to": [{"AIRPORT": {
                                                "IATACode": "SIN",
                                                "airportName": "Singapore Changi Airport"
}}]}},
                            {"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-03-03T00:00:00Z"),
                                    "scheduledDepartureTime": "16:16:16",
                                    "scheduledDuration": "06:00",
                                    "to": [{
                                            "AIRPORT": {
                                                "IATACode": "CKG",
                                                "airportName": "Chongqing Jiangbei International Airport"
}}]}}]}},
                {"PUBLISHED-FLIGHT": {
                        "flightNum": "CX0002",
                        "effectiveDate": ISODate("2024-04-04T00:00:00Z"),
                        "expiryDate": ISODate("2024-04-30T00:00:00Z"),
                        "frequency": "Weekly",
                        "has": [{"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-04-04T00:00:00Z"),
                                    "scheduledDepartureTime": "14:00:00",
                                    "scheduledDuration": "04:00",
                                    "to": [{"AIRPORT": {
                                                "IATACode": "CKG",
                                                "airportName": "Chongqing Jiangbei International Airport"
}}]}},
                            {"FLIGHT-DETAIL": {
                                    "scheduledDepartureDate": ISODate("2024-04-04T00:00:00Z"),
                                    "scheduledDepartureTime": "15:00:00",
                                    "scheduledDuration": "05:00",
                                    "to": [{"AIRPORT": {
                                                "IATACode": "KUL",
                                                "airportName": "Kuala Lumpur International Airport"
}}]}}]}}]}}])

//--------------------------------------------------------------------------
db.kyjho.find().pretty()

