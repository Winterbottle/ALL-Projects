"""
Student Number: 10241485 
Name: Jeslyn Ho Ka Yan 
Date: 26 Oct 2024 
Assignment 1, Part2
"""

import time
import random
import sys
import json
import os
import re

# Files to store user credentials and generated PINs
PASSWORD_FILE = "Passwords.txt"
PIN_FILE = "generated_pins.json"

def is_valid_password(password):
    """Check if the password meets basic criteria."""
    # Password must be at least 8 characters long, have at least one digit, and one special character.
    if len(password) < 8:
        print("Password must be at least 8 characters long.")
        return False
    if not re.search(r"\d", password):
        print("Password must contain at least one number.")
        return False
    if not re.search(r"[!@#$%^&*(),.?\":{}|<>]", password):
        print("Password must contain at least one special character.")
        return False
    return True

def register_user(username, password):
    """Register a new user and save the password in cleartext."""
    # Check if username already exists in the file
    if os.path.exists(PASSWORD_FILE):
        with open(PASSWORD_FILE, 'r') as file:
            for line in file:
                if line.startswith(username + ":"):
                    print("Username already exists.")
                    return
    
    # Save the username:password pair to the file in cleartext
    with open(PASSWORD_FILE, 'a') as file:
        file.write(f"{username}:{password}\n")
    
    print("User registered successfully.")

def validate_user(username, password):
    """Validate user credentials against the stored passwords."""
    # Check if the credentials file exists
    if not os.path.exists(PASSWORD_FILE):
        print("User not found.")
        return False
    
    with open(PASSWORD_FILE, 'r') as file:
        for line in file:
            stored_username, stored_password = line.strip().split(":")
            if stored_username == username and stored_password == password:
                return True
    return False

def generate_pin(username, password):
    """Generate a one-time PIN based on username and password."""
    seed = random.randint(0, 1000)
    hash_input = f"{username}{password}{seed}".encode()
    pin = int(hashlib.sha256(hash_input).hexdigest(), 16) % 1000000  # 6-digit PIN
    return f"{pin:06d}"

def save_pins(generated_pins):
    """Save generated PINs and their expiration times to a file."""
    with open(PIN_FILE, 'w') as f:
        json.dump(generated_pins, f) # Store the data in JSON format

def load_pins():
    """Load generated PINs and their expiration times from a file."""
    try:
        with open(PIN_FILE, 'r') as f:
            return json.load(f) # Read from JSON file if it exists
    except FileNotFoundError:
        return {} # Return an empty dictionary if the file is missing

def check_pin(pin):
    """Check if the provided PIN is valid and hasn't expired or been used."""
    current_time = time.time()
    
    # Load generated pins from the file
    try:
        with open(PIN_FILE, 'r') as f:
            generated_pins = json.load(f)
    except FileNotFoundError:
        return False

    if pin in generated_pins and generated_pins[pin] > current_time:
        # Remove the pin to ensure it can't be reused
        del generated_pins[pin]

        # Save the updated list of pins
        with open(PIN_FILE, 'w') as f:
            json.dump(generated_pins, f)

        return True
    return False

def main():
    """Main function to run the connect program."""
    if len(sys.argv) < 3:
        print("Usage: python3 connect.py <username> <password> [<pin>]")
        return

    username = sys.argv[1]
    password = sys.argv[2]

    if password.lower() == "new":  # Register new user
        new_password = input("Enter new password: ")
        confirm_password = input("Confirm new password: ")
        if new_password == confirm_password:
            if is_valid_password(new_password):  # Validate the password here
                register_user(username, new_password)
            else:
                print("Invalid password. Please try again.")
        else:
            print("Password confirmation failed.")
    elif len(sys.argv) == 4:  # Authenticate user
        pin = sys.argv[3]
        if not pin.isdigit() or len(pin) != 6:
            print("Invalid PIN format. It should be a 6-digit number.")
            return
        
        # Validate user credentials and check if the PIN is valid
        if validate_user(username, password):
            # Only print the success message if the PIN is valid
            if check_pin(pin):
                print("User authenticated successfully.")
                print("PIN is valid and used successfully.")
            else:
                print("Invalid or expired PIN.")
        else:
            print("Invalid username or password.")

if __name__ == "__main__":
    main()
