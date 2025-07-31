## Assignment 3, CSCI 262 System Security, 
## Student Name: Jeslyn Ho Ka Yan, ID: 10241485, UPID: 8535383
## Student Name: CHEA Darayuth,    ID: 10240675, UPID: 8550864

import re
import random
import json

# Function to parse Events.txt
def parse_events(file_path):

    events = {}
    with open(file_path, 'r') as file:
        lines = file.readlines()
        for line in lines[1:]:  # Skip the first line with the count
            # Match the pattern for each event line
            match = re.match(r"(\w+[\s\w]*):([CD]):(\d*):(\d*|):(\d+):?", line.strip())
            if match:
                event_name, event_type, min_val, max_val, weight = match.groups()
                events[event_name] = {
                    "type": event_type,
                    "min": int(min_val) if min_val else 0,
                    "max": int(max_val) if max_val else float('inf'),
                    "weight": int(weight)
                }
    return events

# Function to parse Stats.txt
def parse_stats(file_path):
    stats = {}
    with open(file_path, 'r') as file:
        lines = file.readlines()
        for line in lines[1:]:  # Skip the first line with the count
            # Match the pattern for each event's stats line
            match = re.match(r"(\w+[\s\w]*):([\d.]+):([\d.]+):", line.strip())
            if match:
                event_name, mean, std_dev = match.groups()
                stats[event_name] = {
                    "mean": float(mean),
                    "std_dev": float(std_dev)
                }
    return stats

# Load events and stats from files
events = parse_events("Events.txt")
stats = parse_stats("Stats.txt")

# Function to generate simulated event data for a specified number of days
def generate_event_data(events, stats, days):

    log_data = [] 

    # Loop over each day to generate logs
    for day in range(1, days + 1):
        daily_log = {}  # Dictionary to store events for the current day
        
        # Loop through each event in the events dictionary
        for event, properties in events.items():
            event_type = properties["type"]  # Type of event ('D' for discrete, 'C' for continuous)
            mean = stats[event]["mean"]  # Mean value for the event
            std_dev = stats[event]["std_dev"]  # Standard deviation for the event
            
            # Generate event value based on event type
            if event_type == 'D':  # For discrete events
                # Generate a value using Gaussian distribution and round to an integer
                value = int(random.gauss(mean, std_dev))
            elif event_type == 'C':  # For continuous events
                # Generate a value using Gaussian distribution and round to 2 decimal places
                value = round(random.gauss(mean, std_dev), 2)
            
            # Ensure the generated value is within the min-max range specified in Events.txt
            value = max(properties["min"], min(value, properties["max"]))
            
            # Store the event value in the daily log
            daily_log[event] = value
        

        log_data.append(daily_log)
        
        print(f"Day {day} log: {daily_log}")  

    return log_data  

# Function to save the generated log data to a JSON file
def save_logs(log_data, file_name="event_logs.json"):
    with open(file_name, "w") as log_file:
        # Write the log data to the file in JSON format
        json.dump(log_data, log_file, indent=4)
        print(f"Logs saved to {file_name}")  # Confirmation message


days = 5

# Generate event data for the specified number of days
log_data = generate_event_data(events, stats, days)

# Save the generated log data to a JSON file
save_logs(log_data)

