 /**
 * AreaCalculator - A simple Java program to calculate the area of different shapes.
 * 
 * Features:
 * - Uses a menu-driven approach for user interaction.
 * - Calculates the area of Circle, Rectangle, Triangle, and Square.
 * - Uses a loop to allow multiple calculations until the user chooses to exit.
 * 
 * Topics Covered:
 * 1. **Java Basics** - Class, Methods, Variables, and Data Types.
 * 2. **Control Statements** - Switch Case and Loop (while loop).
 * 3. **User Input Handling** - Using Scanner class.
 * 4. **Mathematical Operations** - Using arithmetic operators and Math library.
 * 5. **Conditional Statements** - Handling user choices and errors.
 */
package simpleJava;
import java.util.Scanner; 

public class AreaCalculator {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);
        
        while (true) {  // Infinite loop to continuously take user input until exit
            // Displaying menu options
            System.out.println("\nMenu:");
            System.out.println("1. Circle");
            System.out.println("2. Rectangle");
            System.out.println("3. Triangle");
            System.out.println("4. Square");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();  // Reading user choice
            double area;  // Variable to store calculated area
            
            // Switch case to handle different shape choices
            switch (choice) {
                case 1:
                    // Calculating area of Circle (π * r²)
                    System.out.print("Enter radius: ");
                    double radius = sc.nextDouble();
                    area = Math.PI * radius * radius;  // Math.PI for accurate value of π
                    System.out.println("Area of Circle: " + area);
                    break;
                case 2:
                    // Calculating area of Rectangle (length * breadth)
                    System.out.print("Enter length: ");
                    double length = sc.nextDouble();
                    System.out.print("Enter breadth: ");
                    double breadth = sc.nextDouble();
                    area = length * breadth;
                    System.out.println("Area of Rectangle: " + area);
                    break;
                case 3:
                    // Calculating area of Triangle (0.5 * base * height)
                    System.out.print("Enter base: ");
                    double base = sc.nextDouble();
                    System.out.print("Enter height: ");
                    double height = sc.nextDouble();
                    area = 0.5 * base * height;
                    System.out.println("Area of Triangle: " + area);
                    break;
                case 4:
                    // Calculating area of Square (side²)
                    System.out.print("Enter side: ");
                    double side = sc.nextDouble();
                    area = side * side;
                    System.out.println("Area of Square: " + area);
                    break;
                case 5:
                    // Exiting the program
                    System.out.println("Exiting...");
                    sc.close();  // Closing scanner to prevent resource leak
                    return;  // Exiting the main method
                default:
                    // Handling invalid input
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
