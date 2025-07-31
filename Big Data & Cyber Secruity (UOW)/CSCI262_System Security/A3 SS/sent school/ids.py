## Assignment 3, CSCI 262 System Security, 
## Student Name: Jeslyn Ho Ka Yan, ID: 10241485, UPID: 8535383
## Student Name: CHEA Darayuth,    ID: 10240675, UPID: 8550864

import re
import sys

# Check if the required number of file paths are provided as command-line arguments
if len(sys.argv) < 3:
    print("Usage: python3 ids.py <Events.txt path> <Stats.txt path>")
    sys.exit(1)

# Assign the provided file paths to variables for easier access
events_file = sys.argv[1]
stats_file = sys.argv[2]


events = {} 
stats = {}   

# Function to parse Events.txt
def parse_events(file_path):
    print(f"Parsing Events file: {file_path}")
    try:
        # Open the events file and read all lines
        with open(file_path, 'r') as file:
            lines = file.readlines()
            # The first line contains the number of events
            num_events = int(lines[0].strip())
            print(f"Number of events in Events.txt: {num_events}")
            
            # Iterate through the remaining lines to extract event data
            for line in lines[1:]:
                # Regex pattern to match event data, allowing for optional max field
                match = re.match(r"(\w+[\s\w]*):([CD]):(\d*):(\d*|):(\d+):?", line.strip())
                if match:
                    # Unpack matched groups into variables
                    event_name, event_type, min_val, max_val, weight = match.groups()
                    # Store event data in the dictionary, converting types as necessary
                    events[event_name] = {
                        "type": event_type,
                        "min": int(min_val) if min_val else 0,  # Default min to 0 if not provided
                        "max": int(max_val) if max_val else float('inf'),  # Default max to infinity if not provided
                        "weight": int(weight)  # Convert weight to integer
                    }
                else:
                    # Warning for lines that do not match the expected format
                    print(f"Warning: Line in Events.txt did not match expected format: {line.strip()}")
        print("Events parsed successfully:", events)
    except FileNotFoundError:
        # Error handling if the file is not found
        print(f"Error: {file_path} not found.")
        sys.exit(1)

# Function to parse Stats.txt
def parse_stats(file_path):
    print(f"Parsing Stats file: {file_path}")
    try:
        # Open the statistics file and read all lines
        with open(file_path, 'r') as file:
            lines = file.readlines()
            # The first line contains the number of events
            num_events = int(lines[0].strip())
            print(f"Number of events in Stats.txt: {num_events}")
            
            # Iterate through the remaining lines to extract statistics data
            for line in lines[1:]:
                # Regex pattern to capture decimal values for mean and standard deviation accurately
                match = re.match(r"(\w+[\s\w]*):([\d.]+):([\d.]+):", line.strip())
                if match:
                    # Unpack matched groups into variables
                    event_name, mean, std_dev = match.groups()
                    # Store statistics data in the dictionary, converting types as necessary
                    stats[event_name] = {
                        "mean": float(mean),   # Convert mean to float
                        "std_dev": float(std_dev)  # Convert standard deviation to float
                    }
                else:
                    # Warning for lines that do not match the expected format
                    print(f"Warning: Line in Stats.txt did not match expected format: {line.strip()}")
        print("Stats parsed successfully:", stats)
    except FileNotFoundError:
        # Error handling if the file is not found
        print(f"Error: {file_path} not found.")
        sys.exit(1)

# Function to check for inconsistencies between Events.txt and Stats.txt
def check_inconsistencies():
    print("Checking for inconsistencies...")
    inconsistencies = []  # List to hold any inconsistencies found
    
    # Check for events that are in Events.txt but not in Stats.txt
    for event_name in events:
        if event_name not in stats:
            inconsistencies.append(f"Event '{event_name}' in Events.txt missing in Stats.txt.")
    
    # Check for events that are in Stats.txt but not in Events.txt
    for event_name in stats:
        if event_name not in events:
            inconsistencies.append(f"Event '{event_name}' in Stats.txt missing in Events.txt.")
    
    # Check for type inconsistencies based on the event type
    for event_name in events:
        if event_name in stats:
            event_type = events[event_name]["type"]
            if event_type == 'D' and not stats[event_name]["mean"].is_integer():
                # Discrete event should have an integer mean
                inconsistencies.append(f"Discrete event '{event_name}' has non-integer mean in Stats.txt.")
            elif event_type == 'C' and stats[event_name]["mean"].is_integer():
                # Continuous event should not have an integer mean
                inconsistencies.append(f"Continuous event '{event_name}' has integer mean in Stats.txt.")
    
    print("Inconsistencies found:", inconsistencies)
    return inconsistencies


parse_events(events_file) 
parse_stats(stats_file)   
inconsistencies = check_inconsistencies()  
print("Parsed Events:", events)  #
print("Parsed Stats:", stats)    
print("Inconsistencies found:", inconsistencies)  
