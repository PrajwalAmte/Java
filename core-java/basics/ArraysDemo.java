import java.util.Arrays;

// File: ArraysDemo.java
public class ArraysDemo {
    public static void main(String[] args) {
        // 1D array
        int[] arr = {5, 2, 8, 1, 9, 3};
        System.out.println("Original: " + Arrays.toString(arr));

        Arrays.sort(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        int idx = Arrays.binarySearch(arr, 8);
        System.out.println("Index of 8: " + idx);

        int[] slice = Arrays.copyOfRange(arr, 1, 4);
        System.out.println("Slice [1,4): " + Arrays.toString(slice));

        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("Filled: " + Arrays.toString(filled));

        // 2D array (matrix)
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Matrix:");
        for (int[] row : matrix) System.out.println("  " + Arrays.toString(row));

        // Matrix transpose
        int rows = matrix.length, cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                transposed[j][i] = matrix[i][j];
        System.out.println("Transposed:");
        for (int[] row : transposed) System.out.println("  " + Arrays.toString(row));

        // Varargs
        System.out.println("Sum: " + sum(1, 2, 3, 4, 5));
    }

    static int sum(int... nums) {
        int total = 0;
        for (int n : nums) total += n;
        return total;
    }
}
