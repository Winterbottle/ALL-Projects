# Name: Jeslyn ho
# SIM ID:10241485
# Assingment 3 Task 1
#

# Disable the table 'JOBAPP', and drop it
disable 'JOBAPP'
drop 'JOBAPP'

#Create a table 'JOBAPP'
create 'JOBAPP', 'Applicant', 'Application', 'Employer', 'Position'


# Insert Applicant 1 data
put 'JOBAPP', 'applicant1', 'Applicant:applicant-number', 'A001'
put 'JOBAPP', 'applicant1', 'Applicant:full-name', 'John Doe'
put 'JOBAPP', 'applicant1', 'Applicant:date-of-birth', '1990-01-01'

# Insert Applicant 2 data
put 'JOBAPP', 'applicant2', 'Applicant:applicant-number', 'A002'
put 'JOBAPP', 'applicant2', 'Applicant:full-name', 'Jane Smith'
put 'JOBAPP', 'applicant2', 'Applicant:date-of-birth', '1992-02-02'

# Insert Employer 1 data
put 'JOBAPP', 'employer1', 'Employer:ename', 'TechCorp'
put 'JOBAPP', 'employer1', 'Employer:phone', '1234567890'

# Insert Employer 2 data
put 'JOBAPP', 'employer2', 'Employer:ename', 'Innovate Ltd'
put 'JOBAPP', 'employer2', 'Employer:phone', '0987654321'

# Insert Position 1 data
put 'JOBAPP', 'position1', 'Position:position-number', 'P001'
put 'JOBAPP', 'position1', 'Position:title', 'Software Engineer'
put 'JOBAPP', 'position1', 'Position:salary', '70000'

# Insert Position 2 data
put 'JOBAPP', 'position2', 'Position:position-number', 'P002'
put 'JOBAPP', 'position2', 'Position:title', 'Data Scientist'
put 'JOBAPP', 'position2', 'Position:salary', '80000'


# Insert Application data
put 'JOBAPP', 'application1', 'Application:application-number', 'APP001'
put 'JOBAPP', 'application1', 'Application:total-skills', '5'
put 'JOBAPP', 'application1', 'Application:education-level', 'Bachelor'
put 'JOBAPP', 'application1', 'Applicant:applicant-number', 'A001'
put 'JOBAPP', 'application1', 'Employer:ename', 'TechCorp'
put 'JOBAPP', 'application1', 'Position:position-number', 'P001'

put 'JOBAPP', 'application2', 'Application:application-number', 'APP002'
put 'JOBAPP', 'application2', 'Application:total-skills', '3'
put 'JOBAPP', 'application2', 'Application:education-level', 'Bachelor'
put 'JOBAPP', 'application2', 'Applicant:applicant-number', 'A002'
put 'JOBAPP', 'application2', 'Employer:ename', 'TechCorp'
put 'JOBAPP', 'application2', 'Position:position-number', 'P002'

put 'JOBAPP', 'application3', 'Application:application-number', 'APP003'
put 'JOBAPP', 'application3', 'Application:total-skills', '4'
put 'JOBAPP', 'application3', 'Application:education-level', 'Master'
put 'JOBAPP', 'application3', 'Applicant:applicant-number', 'A002'
put 'JOBAPP', 'application3', 'Employer:ename', 'Innovate Ltd'
put 'JOBAPP', 'application3', 'Position:position-number', 'P001'

#Scan the table 'COURSEWORK' for verfication
scan 'JOBAPP'