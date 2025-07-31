-- Student Number: 10241485
-- Name: Jeslyn Ho Ka Yan
-- Date: 13 Oct 2024
-- Assignment 2, Task 4

-- Step 1: Create the AUTHOR external table
-- Define the AUTHOR table structure based on the schema provided in dbcreate.sql
-- This table will use the data from the author.tbl file in HDFS
CREATE EXTERNAL TABLE IF NOT EXISTS author (
    a_id INT,             -- Unique author ID
    a_fname STRING,       -- First name of author
    a_mname STRING,       -- Middle name of author
    a_lname STRING,       -- Last name of author
    a_dob DATE,           -- Date of birth of author
    a_bio STRING          -- Biography of the author
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '|'
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/a2.db/authors/';  -- HDFS location of the author.tbl file

-- Step 2: Create the ITEM external table
-- Define the ITEM table structure based on the schema provided in dbcreate.sql
-- This table will use the data from the item.tbl file in HDFS
CREATE EXTERNAL TABLE IF NOT EXISTS item (
    i_id INT,             -- Unique ID of item
    i_title STRING,       -- Title of the item
    i_a_id INT,           -- Author ID (foreign key referencing AUTHOR table)
    i_pub_date DATE,      -- Publication date of the item
    i_publisher STRING,   -- Publisher of the item
    i_subject STRING,     -- Subject category of the item
    i_desc STRING,        -- Description of the item
    i_related1 INT,       -- Related item ID 1
    i_related2 INT,       -- Related item ID 2
    i_related3 INT,       -- Related item ID 3
    i_related4 INT,       -- Related item ID 4
    i_related5 INT,       -- Related item ID 5
    i_thumbnail STRING,   -- Path to thumbnail image of the item
    i_image STRING,       -- Path to main image of the item
    i_srp DECIMAL(15,2),  -- Suggested retail price
    i_cost DECIMAL(15,2), -- Cost of the item
    i_avail DATE,         -- Availability date of the item
    i_stock INT,          -- Quantity in stock
    i_isbn STRING,        -- ISBN of the item
    i_page INT,           -- Number of pages (for books)
    i_backing STRING,     -- Book binding type (e.g., paperback, hardback)
    i_dimensions STRING   -- Physical dimensions of the item
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '|'
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/a2.db/items/';  -- HDFS location of the item.tbl file

-- Step 3: Select total number of rows in each table
-- Query to count the total rows in the AUTHOR table
SELECT COUNT(*) AS author_row_count FROM author;

-- Query to count the total rows in the ITEM table
SELECT COUNT(*) AS item_row_count FROM item;

-- Step 4: Select total number of rows in both tables combined
-- This query sums up the row counts from both tables to get the total row count across tables
SELECT SUM(count) AS total_row_count
FROM (
    SELECT COUNT(*) AS count FROM author
    UNION ALL
    SELECT COUNT(*) AS count FROM item
) AS combined_counts;


-- Step 5: Select the first 3 rows from each table
-- Query to retrieve the first 3 rows from the AUTHOR table
SELECT * FROM author LIMIT 3;

-- Query to retrieve the first 3 rows from the ITEM table
SELECT * FROM item LIMIT 3;