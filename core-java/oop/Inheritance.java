class Animal {
    String name;

    Animal(String name) { this.name = name; }

    void sound() { System.out.println(name + " makes a sound"); }

    @Override
    public String toString() { return "Animal(" + name + ")"; }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    @Override
    void sound() { System.out.println(name + " barks"); }

    void fetch() { System.out.println(name + " fetches the ball"); }
}

class GuideDog extends Dog {
    GuideDog(String name) { super(name, "Labrador"); }

    @Override
    void sound() {
        super.sound();
        System.out.println(name + " is a trained guide dog");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Animal a = new Animal("Generic");
        Dog d = new Dog("Rex", "Husky");
        GuideDog g = new GuideDog("Buddy");

        a.sound();
        d.sound();
        d.fetch();
        g.sound();

        // instanceof checks
        System.out.println("d instanceof Animal: " + (d instanceof Animal));
        System.out.println("g instanceof Dog: " + (g instanceof Dog));
        System.out.println("a instanceof Dog: " + (a instanceof Dog));

        // Upcasting and downcasting
        Animal ref = new Dog("Max", "Poodle");  // upcast
        ref.sound();                             // calls Dog's sound()
        Dog downcasted = (Dog) ref;             // explicit downcast
        downcasted.fetch();
    }
}
