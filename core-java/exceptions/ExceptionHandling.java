// Custom checked exception
class InsufficientFundsException extends Exception {
    private final double amount;
    InsufficientFundsException(double amount) {
        super("Insufficient funds: required " + amount);
        this.amount = amount;
    }
    double getAmount() { return amount; }
}

// Custom unchecked exception
class InvalidAgeException extends RuntimeException {
    InvalidAgeException(int age) { super("Invalid age: " + age); }
}

class Wallet {
    private double balance;
    Wallet(double balance) { this.balance = balance; }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) throw new InsufficientFundsException(amount);
        balance -= amount;
        System.out.printf("Withdrew %.2f, balance: %.2f%n", amount, balance);
    }
}

public class ExceptionHandling {
    static void validateAge(int age) {
        if (age < 0 || age > 150) throw new InvalidAgeException(age);
        System.out.println("Valid age: " + age);
    }

    public static void main(String[] args) {
        // Basic try-catch-finally
        try {
            int[] arr = new int[3];
            arr[10] = 1;  // throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally: always runs");
        }

        // Multi-catch
        try {
            String s = null;
            s.length();  // throws NullPointerException
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Multi-catch: " + e.getClass().getSimpleName());
        }

        // Checked exception (must be caught or declared)
        Wallet wallet = new Wallet(100);
        try {
            wallet.withdraw(50);
            wallet.withdraw(200);  // throws InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Custom checked: " + e.getMessage());
        }

        // Unchecked exception
        try {
            validateAge(25);
            validateAge(-5);  // throws InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Custom unchecked: " + e.getMessage());
        }

        // try-with-resources (AutoCloseable — resource is closed automatically)
        try (var reader = new java.io.StringReader("hello world")) {
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) sb.append((char) ch);
            System.out.println("Read: " + sb);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        // Exception chaining
        try {
            try {
                int result = Integer.parseInt("abc");
            } catch (NumberFormatException e) {
                throw new RuntimeException("Parsing failed", e);  // wrap original
            }
        } catch (RuntimeException e) {
            System.out.println("Chained: " + e.getMessage() + " caused by: " + e.getCause().getClass().getSimpleName());
        }
    }
}
