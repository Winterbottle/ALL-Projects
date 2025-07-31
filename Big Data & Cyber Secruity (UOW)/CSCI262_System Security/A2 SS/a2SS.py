'''
import matplotlib.pyplot as plt

# Example data for Puzzle A (one sub-puzzle with k = 6)
hashes_a = [1, 2, 3, 4, 5, 6]  # Number of hashes
frequency_a = [12, 25, 18, 30, 10, 5]  # Example frequency for each hash count in Puzzle A

# Example data for Puzzle B (six sub-puzzles with k = 4)
hashes_b = [1, 2, 3, 4]  # Number of hashes for the sub-puzzles
frequency_b = [10, 20, 15, 5]  # Example average frequency for each hash count in Puzzle B

# Aggregate frequency data for six sub-puzzles in Puzzle B
total_frequency_b = [0, 0, 0, 0]
for i in range(6):  # Simulating six sub-puzzles
    total_frequency_b[0] += frequency_b[0]  # Hash count 1
    total_frequency_b[1] += frequency_b[1]  # Hash count 2
    total_frequency_b[2] += frequency_b[2]  # Hash count 3
    total_frequency_b[3] += frequency_b[3]  # Hash count 4

plt.figure(figsize=(12, 6))

# Puzzle A Distribution
plt.subplot(1, 2, 1)
plt.bar(hashes_a, frequency_a, color='blue')
plt.title("Distribution of Hashes for Puzzle A")
plt.xlabel("Number of Hashes")
plt.ylabel("Frequency")

# Puzzle B Distribution
plt.subplot(1, 2, 2)
plt.bar(hashes_b, total_frequency_b, color='green')
plt.title("Distribution of Hashes for Puzzle B")
plt.xlabel("Number of Hashes")
plt.ylabel("Frequency")

plt.tight_layout()
plt.show()



import matplotlib.pyplot as plt

# Time (hours) data points from 0 to 14 hours
time = [6, 6.5, 7, 7.5, 8, 8.5, 9, 9.5, 10, 10.5, 11, 11.5, 12, 12.5, 13, 13.5, 14]

# Worm X infected computers at each time point (using values from the table)
worm_x_infected = [64, 63, 126, 124, 248, 244, 488, 480, 960, 944, 1888, 1856, 3712, 3648, 7296, 7168, 14336]

# Worm W infected computers at each time point (from table)
worm_w_infected = [0, 1, 1, 3, 3, 7, 7, 15, 15, 31, 31, 63, 63, 127, 127, 255, 255]

# Create the plot
plt.figure(figsize=(12, 6))
plt.plot(time, worm_x_infected, label='Worm X Infected Computers', color='red', marker='o')
plt.plot(time, worm_w_infected, label='Worm W Infected Computers', color='blue', marker='x')

# Highlight where N = 0 (this would be hypothetical here; mark end if Worm W fully counteracts)
plt.axhline(y=0, color='black', linestyle='--', label='N=0')

# Add labels and title
plt.xlabel('Time (hours)')
plt.ylabel('Number of Infected Computers')
plt.title('Spread of Worm X and Counteraction by Worm W Over Time')
plt.legend()

# Show the plot
plt.grid(True)
plt.show()
'''


from itertools import product
from collections import Counter

# Define parameters
m = 1  # Number of sub-puzzles
k = 6  # Number of possible hashes per sub-puzzle
hash_count_max = 64  # Maximum hash value

# Generate all possible combinations of hashes
# Each sub-puzzle can take 1 to k hashes, so we use range(1, k+1) for possible values
combinations = list(product(range(1, k + 1), repeat=m))

# Calculate the sum of hashes for each combination
hash_sums = [sum(combo) for combo in combinations]

# Count occurrences of each hash sum
case_counts = Counter(hash_sums)

# Display results for each possible number of hashes from 1 to hash_count_max
for hash_value in range(1, hash_count_max + 1):
    cases = case_counts.get(hash_value, 0)  # Get count or 0 if not present
    print(f"Hashes Needed: {hash_value}, Cases: {cases}")

