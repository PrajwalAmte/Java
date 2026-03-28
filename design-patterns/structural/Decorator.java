// Decorator Pattern
// Attach additional responsibilities to an object dynamically.
// Provides a flexible alternative to subclassing for extending functionality.

interface Coffee {
    String description();
    double cost();
}

class SimpleCoffee implements Coffee {
    @Override public String description() { return "Coffee"; }
    @Override public double cost() { return 1.00; }
}

class Espresso implements Coffee {
    @Override public String description() { return "Espresso"; }
    @Override public double cost() { return 1.50; }
}

// Base decorator (wraps a Coffee)
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;
    CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
}

// Concrete decorators
class Milk extends CoffeeDecorator {
    Milk(Coffee c) { super(c); }
    @Override public String description() { return coffee.description() + ", Milk"; }
    @Override public double cost() { return coffee.cost() + 0.25; }
}

class Sugar extends CoffeeDecorator {
    Sugar(Coffee c) { super(c); }
    @Override public String description() { return coffee.description() + ", Sugar"; }
    @Override public double cost() { return coffee.cost() + 0.10; }
}

class Caramel extends CoffeeDecorator {
    Caramel(Coffee c) { super(c); }
    @Override public String description() { return coffee.description() + ", Caramel"; }
    @Override public double cost() { return coffee.cost() + 0.50; }
}

class WhippedCream extends CoffeeDecorator {
    WhippedCream(Coffee c) { super(c); }
    @Override public String description() { return coffee.description() + ", Whipped Cream"; }
    @Override public double cost() { return coffee.cost() + 0.75; }
}

public class Decorator {
    static void print(Coffee c) { System.out.printf("%-45s $%.2f%n", c.description(), c.cost()); }

    public static void main(String[] args) {
        Coffee plain = new SimpleCoffee();
        print(plain);

        Coffee withMilk = new Milk(plain);
        print(withMilk);

        Coffee latte = new WhippedCream(new Caramel(new Milk(new Espresso())));
        print(latte);

        // Decorators are composable in any order
        Coffee sweetEspresso = new Sugar(new Sugar(new Espresso()));  // double sugar
        print(sweetEspresso);
    }
}
