// Abstraction: hide implementation details, expose essential behavior
// via abstract classes and interfaces

// Abstract class: partial abstraction (can have state and concrete methods)
abstract class Vehicle {
    protected String brand;
    protected int year;

    Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    abstract void start();
    abstract void fuelType();

    void info() { System.out.println(brand + " (" + year + ")"); }
}

// Interface: pure abstraction (defines a contract)
interface Chargeable {
    void charge();
    default void chargeStatus() { System.out.println("Charging complete"); }
}

interface GPS {
    String getLocation();
}

// Classes implement and extend to fill in details
class ElectricCar extends Vehicle implements Chargeable, GPS {
    private String location;

    ElectricCar(String brand, int year, String location) {
        super(brand, year);
        this.location = location;
    }

    @Override public void start() { System.out.println(brand + " starts silently"); }
    @Override public void fuelType() { System.out.println(brand + " runs on electricity"); }
    @Override public void charge() { System.out.println(brand + " is charging"); }
    @Override public String getLocation() { return location; }
}

class PetrolCar extends Vehicle {
    PetrolCar(String brand, int year) { super(brand, year); }

    @Override public void start() { System.out.println(brand + " roars to life"); }
    @Override public void fuelType() { System.out.println(brand + " runs on petrol"); }
}

public class Abstraction {
    public static void main(String[] args) {
        Vehicle[] fleet = {
            new ElectricCar("Tesla", 2023, "37.7749° N, 122.4194° W"),
            new PetrolCar("BMW", 2020)
        };

        for (Vehicle v : fleet) {
            v.info();
            v.start();
            v.fuelType();
        }

        // Using interface reference
        Chargeable ev = new ElectricCar("Rivian", 2023, "40.7128° N, 74.0060° W");
        ev.charge();
        ev.chargeStatus();

        GPS gps = (GPS) ev;
        System.out.println("Location: " + gps.getLocation());
    }
}
