# Name: Jeslyn ho
# SIM ID:10241485
# Assingment 3 Task 2
#

#Query 1: 
# Retrieve all available information for the product with product number 101, listing each version in a separate cell. 
get 'task2', 'product101', {COLUMN => 'PRODUCT', VERSIONS => 4}

#Query 2: 
# Retrieve all available information about sales of product 101 made by customer 007, listing each version in a separate cell.
scan 'task2', {FILTER => "PrefixFilter('sale101-customer007')", VERSIONS => 4}


#Query 3: 
# Retrieve the name, location (address), and age group of all customers, listing each customer in a separate cell.
scan 'task2', {COLUMNS => ['CUSTOMER:customerName', 'CUSTOMER:location', 'CUSTOMER:ageGroup']}


#Query 4: 
# Retrieve all information about products under the brand ‘Samsung’, listing each product in a separate cell. 
scan 'task2', {FILTER => "SingleColumnValueFilter('PRODUCT', 'brand', =, 'binary:SAMSUNG')"}


#Task 1: Add a new column family named SELLER that will store seller information (name, address, and contact person) and insert data for at least two sellers. 
alter 'task2', NAME => 'SELLER'
put 'task2', 'seller1', 'SELLER:name', 'Seller A'
put 'task2', 'seller1', 'SELLER:address', '123 Seller St'
put 'task2', 'seller1', 'SELLER:contact', 'John Doe'
put 'task2', 'seller2', 'SELLER:name', 'Seller Two'
put 'task2', 'seller2', 'SELLER:address', '456 Market Rd'
put 'task2', 'seller2', 'SELLER:contact', 'Jane Smith'

# Task 1 - Scan to verify seller information
scan 'task2', {COLUMNS => ['SELLER']}


#Task 2: Add information for two sales transactions, each by a different seller, with reasonable values.
put 'task2', 'sale001', 'SALES-TRX:seller', 'seller1'
put 'task2', 'sale001', 'SALES-TRX:quantitySold', '10'
put 'task2', 'sale001', 'SALES-TRX:totalSales', '200'

put 'task2', 'sale002', 'SALES-TRX:seller', 'seller2'
put 'task2', 'sale002', 'SALES-TRX:quantitySold', '5'
put 'task2', 'sale002', 'SALES-TRX:totalSales', '150'

# Task 2 - Scan to verify sales transaction data
scan 'task2', {FILTER => "PrefixFilter('sale')", COLUMNS => ['SALES-TRX']}


#Task 3: Increase the total number of versions in each cell of the PRODUCT column family to 4.
alter 'task2', {NAME => 'PRODUCT', VERSIONS => 4}

