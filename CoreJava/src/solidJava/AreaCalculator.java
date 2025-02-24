package solidJava;

import java.util.Scanner;

interface Shape {
	double area();
}

class Circle implements Shape {
	private final double radius;
	
	Circle(double radius) {
		this.radius = radius;
	}
	@Override
	public double area() {
		return Math.PI*radius*radius;
	}
}

class Rectangle implements Shape {
	private final double length, breadth;
	
	Rectangle(double length, double breadth) {
		this.length = length;
		this.breadth = breadth;
	}
	@Override
	public double area() {
		return length*breadth;
	}
}

class Triangle implements Shape {
	private final double base, height;
	
	Triangle(double base, double height) {
		this.base = base;
		this.height = height;
	}
	@Override
	public double area() {
		return 0.5*base*height;
	}
}

class Square implements Shape {
	private final double length;
	
	Square(double length) {
		this.length = length;
	}
	@Override
	public double area() {
		return length*length;
	}
}

class MenuHandler {
    private final Scanner scanner;

    public MenuHandler() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Circle");
            System.out.println("2. Rectangle");
            System.out.println("3. Triangle");
            System.out.println("4. Square");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            if (choice == 5) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            }

            Shape shape = getShape(choice);
            if (shape != null) {
                AreaCalculator calculator = new AreaCalculator(shape);
                System.out.println("Calculated Area: " + calculator.calculateArea());
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private Shape getShape(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter radius: ");
                return new Circle(scanner.nextDouble());
            case 2:
                System.out.print("Enter length: ");
                double length = scanner.nextDouble();
                System.out.print("Enter breadth: ");
                double breadth = scanner.nextDouble();
                return new Rectangle(length, breadth);
            case 3:
                System.out.print("Enter base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter height: ");
                double height = scanner.nextDouble();
                return new Triangle(base, height);
            case 4:
                System.out.print("Enter side: ");
                return new Square(scanner.nextDouble());
            default:
                return null;
        }
    }
}


public class AreaCalculator {
	private final Shape shape;

    public AreaCalculator(Shape shape) {
        this.shape = shape;
    }

    public double calculateArea() {
        return shape.area();
    }
}
