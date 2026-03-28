// Polymorphism: same interface, different behavior

// --- Compile-time polymorphism: method overloading ---
class MathHelper {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
    String add(String a, String b) { return a + b; }
}

// --- Runtime polymorphism: method overriding ---
abstract class Shape {
    abstract double area();
    abstract String name();
    void describe() { System.out.printf("%s area = %.2f%n", name(), area()); }
}

class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    @Override public double area() { return Math.PI * radius * radius; }
    @Override public String name() { return "Circle(r=" + radius + ")"; }
}

class Rectangle extends Shape {
    double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    @Override public double area() { return w * h; }
    @Override public String name() { return "Rectangle(" + w + "x" + h + ")"; }
}

class Triangle extends Shape {
    double base, height;
    Triangle(double b, double h) { this.base = b; this.height = h; }
    @Override public double area() { return 0.5 * base * height; }
    @Override public String name() { return "Triangle(b=" + base + ",h=" + height + ")"; }
}

public class Polymorphism {
    public static void main(String[] args) {
        // Compile-time: overloading
        MathHelper m = new MathHelper();
        System.out.println(m.add(2, 3));
        System.out.println(m.add(2.5, 3.5));
        System.out.println(m.add(1, 2, 3));
        System.out.println(m.add("Hello", " World"));

        // Runtime: overriding via base reference
        Shape[] shapes = {new Circle(5), new Rectangle(4, 6), new Triangle(3, 8)};
        for (Shape s : shapes) s.describe();

        // Dynamic dispatch: reference type vs object type
        Shape s = new Circle(3);
        System.out.println("Ref type: Shape, Object type: " + s.getClass().getSimpleName());
        s.describe();  // calls Circle.area()
    }
}
