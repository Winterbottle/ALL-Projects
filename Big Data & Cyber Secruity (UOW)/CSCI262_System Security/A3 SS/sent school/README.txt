## Assignment 3, CSCI 262 System Security, 
## Student Name: Jeslyn Ho Ka Yan, ID: 10241485, UPID: 8535383
## Student Name: CHEA Darayuth,    ID: 10240675, UPID: 8550864

## Requirement:
- Make sure python 3 is installed
- Make sure all the python file in same fold / directory
- we run it in VMware environment

## To run all the python file using terminal
- cd to directory where it contains all the python files, Events.txt and Stats.txt (both txt is provided)
## Assume that we already change directory to the folder we store our python files

Initial Input:
==============
## Open the terminal and run:
seed@VM:~/Desktop$ python3 ids.py Events.txt Stats.txt
# need to contains both files for the program to read in 

Log Generator:
==============
## In same terminal we run:
seed@VM:~/Desktop$ python3 log_generator.py
# this will generate some of the log and save it into a JSON file (same directory as where your python file is)

Analysis Engine:
================
## In terminal, run:
seed@VM:~/Desktop$ python3 Analysis.py
# it will show the processing that it is analyzing 
# then i will generate another JSON file and store the result inside
# the generated file named 'analysis_results.json' in same directory

Alert Engine:
=============
## In terminal run:
seed@VM:~/Desktop$ python3 alert_engine.py

## Then it ask to prompts the files directory for it to locate the files and generate the 
Enter the path to Events.txt: /Your/Path/to/Events.txt
Enter the path to Stats.txt: /Your/Path/to/NewStats.txt (data similar to Stats.txt) 
Enter the number of days to simulate: AnyNumber

# the sample output would be like these:
# Sample input 3 days
Daily Anomaly Report:
Day 1: Anomaly Counter = 5.03, Threshold = 18, Status = okay
Day 2: Anomaly Counter = 2.34, Threshold = 18, Status = okay
Day 3: Anomaly Counter = 7.28, Threshold = 18, Status = okay

## for few amount of days input you might see most of the Anomaly Counter is less than threshold so there is no flagged at all but you can try 10000 to test about it <3