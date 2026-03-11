package basics;

import java.util.Arrays;

/**
 * Topic 4: Arrays
 *
 * Covers declaration, initialization, common operations,
 * multi-dimensional arrays, and java.util.Arrays utilities.
 */
public class T04_ArrayBasics {

    public static void main(String[] args) {

        System.out.println("=== 1. ARRAY DECLARATION & INITIALIZATION ===\n");

        // Method 1: Declare then initialize
        int[] arr1 = new int[5]; // All elements default to 0
        arr1[0] = 10;
        arr1[1] = 20;

        // Method 2: Inline initialization
        int[] arr2 = {10, 20, 30, 40, 50};

        // Method 3: new with values
        int[] arr3 = new int[]{10, 20, 30};

        // Valid declaration styles (bracket can go after name)
        int arr4[] = {1, 2, 3}; // C-style, less preferred
        int[] arr5;              // Preferred Java style

        System.out.println("arr2: " + Arrays.toString(arr2));
        System.out.println("arr2.length: " + arr2.length); // length is a field, not method!

        System.out.println("\n=== 2. ACCESSING & MODIFYING ===\n");

        int[] nums = {10, 20, 30, 40, 50};
        System.out.println("First element: " + nums[0]);
        System.out.println("Last element: " + nums[nums.length - 1]);

        nums[2] = 99;
        System.out.println("After modifying index 2: " + Arrays.toString(nums));

        // ArrayIndexOutOfBoundsException if out of range
        try {
            int bad = nums[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }

        System.out.println("\n=== 3. ITERATING ARRAYS ===\n");

        String[] fruits = {"Apple", "Banana", "Cherry"};

        // Standard for loop
        System.out.print("For loop: ");
        for (int i = 0; i < fruits.length; i++) {
            System.out.print(fruits[i] + " ");
        }
        System.out.println();

        // Enhanced for-each
        System.out.print("For-each: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        System.out.println("\n=== 4. MULTI-DIMENSIONAL ARRAYS ===\n");

        // 2D array (matrix)
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("matrix[1][2] = " + matrix[1][2]); // 6

        // Print the matrix
        System.out.println("Full matrix:");
        for (int[] row : matrix) {
            System.out.println("  " + Arrays.toString(row));
        }

        // Jagged array (rows of different lengths!)
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1, 2};
        jagged[1] = new int[]{3, 4, 5};
        jagged[2] = new int[]{6};

        System.out.println("\nJagged array:");
        for (int[] row : jagged) {
            System.out.println("  " + Arrays.toString(row));
        }

        System.out.println("\n=== 5. ARRAYS UTILITY CLASS ===\n");

        int[] data = {5, 2, 8, 1, 9, 3};

        // Sort
        int[] sorted = data.clone(); // Clone first to keep original
        Arrays.sort(sorted);
        System.out.println("Original: " + Arrays.toString(data));
        System.out.println("Sorted:   " + Arrays.toString(sorted));

        // Binary search (array MUST be sorted first)
        int index = Arrays.binarySearch(sorted, 8);
        System.out.println("Index of 8 in sorted: " + index);

        // Fill
        int[] filled = new int[5];
        Arrays.fill(filled, 42);
        System.out.println("Filled:   " + Arrays.toString(filled));

        // Compare
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println("Arrays.equals(a, b): " + Arrays.equals(a, b)); // true
        System.out.println("a == b: " + (a == b)); // false! Different objects

        // Copy
        int[] original = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(original, 3);        // First 3 elements
        int[] rangeCopy = Arrays.copyOfRange(original, 1, 4); // Index 1 to 3
        System.out.println("copyOf(3):         " + Arrays.toString(copy));
        System.out.println("copyOfRange(1, 4): " + Arrays.toString(rangeCopy));

        System.out.println("\n=== 6. COMMON ALGORITHMS ===\n");

        int[] values = {3, 7, 1, 9, 4, 6};

        // Find max
        int max = values[0];
        for (int v : values) {
            if (v > max) max = v;
        }
        System.out.println("Max of " + Arrays.toString(values) + ": " + max);

        // Sum
        int sum = 0;
        for (int v : values) {
            sum += v;
        }
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + (double) sum / values.length);

        // Reverse
        int[] reversed = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            reversed[i] = values[values.length - 1 - i];
        }
        System.out.println("Reversed: " + Arrays.toString(reversed));
    }
}
