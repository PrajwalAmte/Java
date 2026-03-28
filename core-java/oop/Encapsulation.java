// Encapsulation: bundle data with methods that operate on it;
// restrict direct access to internal state via access modifiers

class BankAccount {
    private final String owner;
    private double balance;

    BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = Math.max(initialBalance, 0);
    }

    public String getOwner() { return owner; }
    public double getBalance() { return balance; }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount target, double amount) {
        if (!withdraw(amount)) return false;
        target.deposit(amount);
        return true;
    }

    @Override
    public String toString() { return String.format("%s: $%.2f", owner, balance); }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        this.name = name.strip();
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) throw new IllegalArgumentException("Invalid age: " + age);
        this.age = age;
    }

    @Override
    public String toString() { return name + " (age " + age + ")"; }
}

public class Encapsulation {
    public static void main(String[] args) {
        BankAccount alice = new BankAccount("Alice", 1000);
        BankAccount bob = new BankAccount("Bob", 500);

        System.out.println(alice);
        System.out.println(bob);

        alice.deposit(250);
        alice.withdraw(100);
        System.out.println("After transactions: " + alice);

        System.out.println("Transfer $300: " + alice.transfer(bob, 300));
        System.out.println(alice);
        System.out.println(bob);

        System.out.println("Overdraft: " + alice.withdraw(99999));

        Person p = new Person("Alice", 30);
        System.out.println(p);
        p.setAge(31);
        System.out.println(p);
    }
}
