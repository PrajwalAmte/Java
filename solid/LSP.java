// LSP: Liskov Substitution Principle
// Subtypes must be substitutable for their base types without breaking program correctness.

// BAD: Square extends Rectangle — violates LSP
// Setting width on Square also changes height, breaking the Rectangle contract
class RectangleBad {
    protected int width, height;
    void setWidth(int w) { this.width = w; }
    void setHeight(int h) { this.height = h; }
    int area() { return width * height; }
}

class SquareBad extends RectangleBad {
    @Override void setWidth(int w) { this.width = this.height = w; }   // breaks contract
    @Override void setHeight(int h) { this.width = this.height = h; }  // breaks contract
}

// GOOD: Avoid forcing an incorrect hierarchy; use a proper abstraction
interface Shape {
    double area();
}

class Rectangle implements Shape {
    private final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    @Override public double area() { return w * h; }
    @Override public String toString() { return String.format("Rectangle(%.1f×%.1f)", w, h); }
}

class Square implements Shape {
    private final double side;
    Square(double side) { this.side = side; }
    @Override public double area() { return side * side; }
    @Override public String toString() { return String.format("Square(%.1f)", side); }
}

class Circle implements Shape {
    private final double radius;
    Circle(double r) { this.radius = r; }
    @Override public double area() { return Math.PI * radius * radius; }
    @Override public String toString() { return String.format("Circle(r=%.1f)", radius); }
}

public class LSP {
    static void printArea(Shape s) {
        System.out.printf("%-18s area = %.2f%n", s, s.area());
    }

    public static void main(String[] args) {
        System.out.println("--- BAD: Square breaks Rectangle's contract ---");
        RectangleBad r = new SquareBad();
        r.setWidth(4);
        r.setHeight(5);
        System.out.println("Expected area=20, got: " + r.area());  // prints 25!

        System.out.println("\n--- GOOD: all shapes safely substitutable for Shape ---");
        Shape[] shapes = {new Rectangle(4, 5), new Square(5), new Circle(3)};
        for (Shape s : shapes) printArea(s);
    }
}
