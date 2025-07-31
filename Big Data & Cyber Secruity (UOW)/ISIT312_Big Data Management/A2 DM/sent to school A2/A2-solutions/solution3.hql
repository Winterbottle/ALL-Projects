-- Student Number: 10241485
-- Name: Jeslyn Ho Ka Yan
-- Date: 13 Oct 2024
-- Assignment 2, Task 3


-- Step 1: Create an internal table in Hive
create table student_evaluations (
    student_id INT,
    subjects MAP<STRING, INT>
)
STORED AS TEXTFILE;

-- Step 2: Insert sample data into the table
INSERT INTO student_evaluations
SELECT 1111, MAP('CSIT111', 1, 'CSIT121', 23, 'CSIT101', 50, 'CSIT235', 99, 'ISIT312', 2) UNION ALL
SELECT 1112, MAP('CSIT101', 56, 'CSIT111', 78, 'CSIT115', 10, 'ISIT312', 5) UNION ALL
SELECT 1113, MAP('CSIT121', 76, 'CSIT235', 87, 'ISIT312', 49) UNION ALL
SELECT 1114, MAP('CSIT111', 50, 'ISIT312', 45) UNION ALL
SELECT 1115, MAP('ISIT115', 67, 'CSCI235', 45, 'CSIT127', 56) UNION ALL
SELECT 1118, MAP('CSIT127', 48, 'CSIT115', 88) UNION ALL
SELECT 1119, MAP('CSCI235', 100, 'ISIT115', 80, 'CSIT121', 75) UNION ALL
SELECT 1120, MAP('CSIT101', 45, 'CSIT111', 89);

-- Step 3: Select statement to list the contents of the table
SELECT student_id, subjects
FROM student_evaluations
LIMIT 5;