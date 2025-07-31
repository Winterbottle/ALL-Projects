## Assignment 3, CSCI 262 System Security, 
## Student Name: Jeslyn Ho Ka Yan, ID: 10241485, UPID: 8535383
## Student Name: CHEA Darayuth,    ID: 10240675, UPID: 8550864

import json
import random
import math


# Load stats data from a file
def load_stats(file_name):
    stats = {}
    with open(file_name, "r") as stats_file:
        lines = stats_file.readlines()
        for line in lines[1:]:
            line = line.strip()
            # Skip invalid or empty lines
            if not line or line.count(":") < 2:
                print(f"Skipping invalid or empty line: '{line}'")
                continue
            try:
                # Extract event name, mean, and std_dev
                parts = line.split(":")
                event_name = parts[0]
                mean = float(parts[1])
                std_dev = float(parts[2])
                stats[event_name] = {
                    "mean": mean,
                    "std_dev": std_dev
                }
            except ValueError as e:
                print(f"Error processing line: '{line}'. Details: {e}")
                continue
    return stats


# Load events data from a file
def load_events(file_name):
    events = {}
    with open(file_name, "r") as events_file:
        lines = events_file.readlines()
        for line in lines[1:]:
            line = line.strip()
            # Skip invalid or empty lines
            if not line or line.count(":") < 4:
                print(f"Skipping invalid or empty line: '{line}'")
                continue
            try:
                parts = line.split(":")
                event_name = parts[0]
                event_type = parts[1]
                min_val = float(parts[2]) if parts[2] else 0
                max_val = float(parts[3]) if parts[3] else float('inf')
                weight = int(parts[4])
                events[event_name] = {
                    "type": event_type,
                    "min": min_val,
                    "max": max_val,
                    "weight": weight
                }
            except ValueError as e:
                print(f"Error processing line: '{line}'. Details: {e}")
                continue
    return events


# Generate activity data for the specified number of days
def generate_data(events, stats, days):
    log_data = []
    for _ in range(days):
        daily_log = {}
        for event, properties in events.items():
            mean = stats[event]["mean"]
            std_dev = stats[event]["std_dev"]
            if properties["type"] == "D":
                value = int(random.gauss(mean, std_dev))
            else:
                value = round(random.gauss(mean, std_dev), 2)
            # Clamp the value within the specified range
            value = max(properties["min"], min(value, properties["max"]))
            daily_log[event] = value
        log_data.append(daily_log)
    return log_data


# Detect anomalies in generated data
def detect_anomalies(log_data, stats, events):
    results = []
    sum_of_weights = sum(event["weight"] for event in events.values())
    threshold = 2 * sum_of_weights

    for day, daily_log in enumerate(log_data, start=1):
        anomaly_counter = 0
        for event, value in daily_log.items():
            mean = stats[event]["mean"]
            std_dev = stats[event]["std_dev"]
            weight = events[event]["weight"]
            deviation = abs(value - mean) / std_dev
            anomaly_counter += deviation * weight
        results.append({
            "day": day,
            "anomaly_counter": anomaly_counter,
            "threshold": threshold,
            "status": "flagged" if anomaly_counter >= threshold else "okay"
        })
    return results


# Main Alert Engine Workflow
def alert_engine():
    while True:
        # Input setup
        events_file = input("Enter the path to Events.txt: ")
        stats_file = input("Enter the path to Stats.txt: ")
        days = int(input("Enter the number of days to simulate: "))
        
        # Load events and stats
        print("Loading events...")
        events = load_events(events_file)
        print("Loading stats...")
        stats = load_stats(stats_file)

        # Ensure events and stats match
        missing_events = [event for event in events if event not in stats]
        if missing_events:
            print(f"Warning: The following events are missing in Stats.txt: {', '.join(missing_events)}")
        
        # Generate activity data
        print(f"Generating simulated data for {days} days...")
        log_data = generate_data(events, stats, days)

        # Save generated log data
        with open("log_data.json", "w") as log_file:
            json.dump(log_data, log_file, indent=4)
        print("Simulated log data saved to 'log_data.json'.")

        # Detect anomalies
        print("Detecting anomalies...")
        anomalies = detect_anomalies(log_data, stats, events)

        # Report anomalies
        print("\nDaily Anomaly Report:")
        for anomaly in anomalies:
            print(f"Day {anomaly['day']}: Anomaly Counter = {anomaly['anomaly_counter']:.2f}, "
                  f"Threshold = {anomaly['threshold']}, Status = {anomaly['status']}")

        # Save anomaly report
        with open("anomaly_report.json", "w") as report_file:
            json.dump(anomalies, report_file, indent=4)
        print("Anomaly report saved to 'anomaly_report.json'.")

        # Repeat or quit
        repeat = input("Would you like to process another file? (yes/no): ").lower()
        if repeat != "yes":
            break


# Run the alert engine
alert_engine()
