"""
Student Number: 10241485 
Name: Jeslyn Ho Ka Yan 
Date: 26 Oct 2024 
Assignment 1, Part2
"""

import hashlib
import time
import random
import sys
import json

# File to store generated PINs and their expiration times
PIN_FILE = "generated_pins.json"

def generate_pin(username, password):
    """Generate a one-time 6-digit PIN based on username, password, and a random seed."""
    seed = random.randint(0, 1000)  # Random seed for additional randomness
    hash_input = f"{username}{password}{seed}".encode()  # Combine inputs and encode
    pin = int(hashlib.sha256(hash_input).hexdigest(), 16) % 1000000  # Generate 6-digit PIN from hash
    return f"{pin:06d}"  # Return PIN as a zero-padded 6-digit string

def save_pins(generated_pins):
    """Save the dictionary of generated PINs and expiration times to a file."""
    with open(PIN_FILE, 'w') as f:
        json.dump(generated_pins, f)  # Write the dictionary to the file in JSON format

def load_pins():
    """Load the dictionary of generated PINs and expiration times from a file."""
    try:
        with open(PIN_FILE, 'r') as f:
            return json.load(f)  # Load the dictionary from the JSON file
    except FileNotFoundError:
        return {}  # Return an empty dictionary if the file doesn't exist

def main():
    # Ensure proper usage with two arguments (username and password)
    if len(sys.argv) != 3:
        print("Usage: Device <username> <password>")
        sys.exit(1)

    # Read the username and password from command-line arguments
    username = sys.argv[1]
    password = sys.argv[2]

    print("Starting device...")

    # Load existing PINs to maintain continuity across program runs
    generated_pins = load_pins()

    try:
        while True:
            # Generate a new PIN for the given username and password
            pin = generate_pin(username, password)
            timestamp = time.time() + 15  # Set expiration time for 15 seconds from now
            generated_pins[pin] = timestamp  # Store the PIN with its expiration time
            
            # Save the current state of generated PINs to file
            save_pins(generated_pins)

            print(f"Device: {pin}")  # Display the generated PIN
            
            # Identify and remove expired PINs
            expired_pins = [p for p, t in generated_pins.items() if t < time.time()]
            for p in expired_pins:
                del generated_pins[p]  # Delete each expired PIN

            # Save the updated state after removing expired PINs
            save_pins(generated_pins)

            time.sleep(15)  # Wait for 15 seconds before generating the next PIN
    except KeyboardInterrupt:
        # Handle exit gracefully if the user stops the program
        print("\nDevice stopped by user.")
        sys.exit(0)

if __name__ == "__main__":
    main()
