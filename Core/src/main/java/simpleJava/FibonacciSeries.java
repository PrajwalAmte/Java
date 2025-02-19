/**
 * FibonacciSeries Program
 * 
 * This program generates the Fibonacci series up to the specified number of terms.
 * 
 * Topics Covered:
 * 1. Variables and Data Types
 * 2. Loops (for loop)
 * 3. Conditional Statements
 * 4. Input Handling (Scanner class)
 * 5. Basic Arithmetic Operations
 * 6. Printing Output (System.out.print)
 * 7. Memory Management (Garbage Collection for Scanner)
 * 
 * The Fibonacci series starts with 0 and 1, and each subsequent term is the sum of the previous two terms.
 * Example for n = 5: 0 1 1 2 3
 * 
 */
package simpleJava;

import java.util.Scanner;

public class FibonacciSeries {
	public static void main(String[] args) {
        // Creating Scanner object to take user input
        Scanner sc = new Scanner(System.in);

        // Asking user for the number of terms in the Fibonacci series
        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();

        // Initializing first two terms of Fibonacci series
        int a = 0, b = 1, c;

        // Printing the first two terms
        System.out.print("Fibonacci Series: " + a + " " + b);

        // Loop to generate Fibonacci series
        for (int i = 2; i < n; i++) {
            c = a + b; // Calculate next term
            System.out.print(" " + c); // Print the term
            a = b; // Update value of 'a'
            b = c; // Update value of 'b'
        }

        // Closing the scanner to prevent memory leaks
        sc.close();
    }

}
