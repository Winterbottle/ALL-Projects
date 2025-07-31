## Assignment 3, CSCI 262 System Security, 
## Student Name: Jeslyn Ho Ka Yan, ID: 10241485, UPID: 8535383
## Student Name: CHEA Darayuth,    ID: 10240675, UPID: 8550864


import json
import math

# Load log data from a file
def load_logs(file_name):
    print("Loading log data...")
    with open(file_name, "r") as log_file:
        return json.load(log_file)

# Analyze log data: compute daily totals, mean, and standard deviation
def analyze_logs(log_data):
    print("Starting analysis phase...")
    event_totals = {}
    daily_stats = {}
    
    # Initialize totals for each event
    for event in log_data[0].keys():
        event_totals[event] = []

    # Process daily totals
    print("Processing daily totals...")
    for day_log in log_data:
        for event, value in day_log.items():
            event_totals[event].append(value)

    # Compute statistics for each event
    print("Calculating mean and standard deviation...")
    for event, daily_values in event_totals.items():
        total = sum(daily_values)
        mean = total / len(daily_values)
        variance = sum((x - mean) ** 2 for x in daily_values) / len(daily_values)
        std_dev = math.sqrt(variance)

        # Store results
        daily_stats[event] = {
            "daily_totals": daily_values,
            "mean": round(mean, 2),
            "std_dev": round(std_dev, 2)
        }

    print("Analysis complete.")
    return daily_stats

# Save analyzed data to a file
def save_analysis(data, file_name="analysis_results.json"):
    print(f"Saving analysis results to {file_name}...")
    with open(file_name, "w") as result_file:
        json.dump(data, result_file, indent=4)
    print("Analysis results saved successfully.")

# Execute the analysis process
log_file = "event_logs.json"  # Input file with generated event logs
analysis_file = "analysis_results.json"  # Output file for analyzed results

log_data = load_logs(log_file)  # Load logs
analysis_results = analyze_logs(log_data)  # Analyze logs
save_analysis(analysis_results, analysis_file)  # Save results
