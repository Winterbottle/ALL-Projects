Student Number: 10241485 
Name: Jeslyn Ho Ka Yan 
Date: 26 Oct 2024 
Assignment 1, Part2

Step 1: Open a terminal and navigate to the directory containing the files
	example- cd /home/seed/mySpace/A1

Step 2: Register a New User
2.1 - 	Create UserName
	python3 connect.py <username> new 	<-- input this inside the terminal

2.2- 	Create a password.
 	Your password must be at least 8 characters long and include at least one number and one special character.
	Enter new password: <password>		<--	The terminal will show this
	Confirm new password:  <password>	

Step 3: Open a terminal and navigate to the directory containing the files
	example- cd /home/seed/mySpace/A1

Step 4: In the 2nd terminal we need to get the 6 digit pin.
	python3 device.py <username> <password>	<-- input this inside the terminal
	
	next it will start the device, 6 digit pin will come in each 15 second

Step 5: Go back the 1st Terminal and input the 6 digit pin
	$ python3 connect.py <username> <password> <6 digit pin>


