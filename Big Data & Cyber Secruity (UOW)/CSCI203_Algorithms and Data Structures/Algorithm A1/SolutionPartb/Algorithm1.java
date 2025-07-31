
//Name: Ho Ka Yan Jeslyn
//UOW ID: 8535383
//Assignment 1 Part2 question 6a



import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Algorithm1 {

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false; // Reset the swapped flag for each iteration
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);// Continue the loop as long as swaps are happening
    }

    private static int[] generateArrayA(int lengthA) {
        int[] arr = new int[lengthA]; // Initialize an array for A with specified length
        Set<Integer> uniqueNumbers = new HashSet<>();// Set to keep track of unique numbers
        Random random = new Random();

        for (int i = 0; i < lengthA; i++) {
            int uniqueRandom;
            do {
                uniqueRandom = random.nextInt(100) + 1; // Generate random number in the range 1 to 10
            } while (!uniqueNumbers.add(uniqueRandom)); // Add returns false if the value is already present

            arr[i] = uniqueRandom;
        }

        return arr;
    }

    private static int[] generateArrayB(int lengthA, int lengthB) {
        int[] arr = new int[lengthB]; // Initialize an array for B with specified length
        Set<Integer> uniqueNumbers = new HashSet<>(); // Set to keep track of unique numbers
        Random random = new Random();

        for (int i = 0; i < lengthB; i++) {
            int uniqueRandom;
            do {
                uniqueRandom = random.nextInt(lengthA) + 1; // Generate random number in the range 1 to lengthA
            } while (!uniqueNumbers.add(uniqueRandom)); // Add returns false if the value is already present

            arr[i] = uniqueRandom;
        }

        return arr;
    }

    public static int[] algorithm1(int[] arrayA, int[] arrayB) {
        bubbleSort(arrayA);
        bubbleSort(arrayB); // the only differences between Algorithm 1 and 2
        int[] result = new int[arrayB.length];

        for (int i = 0; i < arrayB.length; i++) {
            int index = arrayB[i] - 1; //Since arrayB entries fall between 1 and lengthA, adjust the index.
            result[i] = arrayA[index]; // Extract the relevant element from array A that has been sorted.
        }

        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();

        // Generate random lengths for arrays A and B
        int lengthA = random.nextInt(10) + 1;
        int lengthB = random.nextInt(lengthA) + 1;

        int[] arrayA = generateArrayA(lengthA);
        int[] arrayB = generateArrayB(lengthA, lengthB);

        System.out.println("Generated Array A: " + Arrays.toString(arrayA));
        System.out.println("Generated Array B: " + Arrays.toString(arrayB));

        int[] result = algorithm1(arrayA, arrayB); // apply algorithm1 to generate result array


        System.out.println("Sorted Array A: " + Arrays.toString(arrayA));
        System.out.println("Sorted Array B: " + Arrays.toString(arrayB));
        System.out.println("Result: " + Arrays.toString(result));
    }
}













