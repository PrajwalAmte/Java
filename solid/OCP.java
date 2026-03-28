// OCP: Open/Closed Principle
// Software entities should be open for extension, but closed for modification.

// BAD: Adding a new shape requires modifying AreaCalculatorBad
class AreaCalculatorBad {
    double calculate(Object shape) {
        if (shape instanceof CircleBad) return Math.PI * ((CircleBad) shape).radius * ((CircleBad) shape).radius;
        if (shape instanceof RectangleBad) return ((RectangleBad) shape).w * ((RectangleBad) shape).h;
        // Adding Triangle requires modifying this method -> violates OCP
        throw new IllegalArgumentException("Unknown shape");
    }
}
class CircleBad { double radius; CircleBad(double r) { radius = r; } }
class RectangleBad { double w, h; RectangleBad(double w, double h) { this.w = w; this.h = h; } }

// GOOD: New shapes are added by extension (new class), not by modifying existing code
interface Shape {
    double area();
    String name();
}

class Circle implements Shape {
    private final double radius;
    Circle(double r) { this.radius = r; }
    @Override public double area() { return Math.PI * radius * radius; }
    @Override public String name() { return "Circle(r=" + radius + ")"; }
}

class Rectangle implements Shape {
    private final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    @Override public double area() { return w * h; }
    @Override public String name() { return "Rectangle(" + w + "x" + h + ")"; }
}

class Triangle implements Shape {
    private final double base, height;
    Triangle(double b, double h) { this.base = b; this.height = h; }
    @Override public double area() { return 0.5 * base * height; }
    @Override public String name() { return "Triangle(b=" + base + ",h=" + height + ")"; }
}

// Adding Pentagon: zero changes to existing code
class Pentagon implements Shape {
    private final double side;
    Pentagon(double s) { this.side = s; }
    @Override public double area() { return (Math.sqrt(25 + 10 * Math.sqrt(5)) / 4) * side * side; }
    @Override public String name() { return "Pentagon(s=" + side + ")"; }
}

class AreaPrinter {
    void printAll(Shape[] shapes) {
        for (Shape s : shapes)
            System.out.printf("%-25s area = %.2f%n", s.name(), s.area());
    }
}

public class OCP {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(5), new Rectangle(4, 6), new Triangle(3, 8), new Pentagon(4)};
        new AreaPrinter().printAll(shapes);
    }
}
