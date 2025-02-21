/**
 * The Strategy Pattern is a behavioral design pattern that allows selecting an algorithm's behavior at runtime. 
 * It defines a family of algorithms, encapsulates each one as a separate class, and makes them interchangeable without altering the client code. 
 * This promotes flexibility, maintainability, and adherence to the Open/Closed Principle.
 */

/**
 * Demonstrates the Strategy Pattern by implementing a payment system.
 * The payment strategy can be selected dynamically at runtime.
 */
package designPatterns;
import java.util.Scanner;

public class PaymentSystemDemo {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Choose a payment method: 1. Credit Card  2. PayPal  3. UPI");
        int choice = scanner.nextInt();

        PaymentMethod paymentMethod;
        switch (choice) {
            case 1:
                paymentMethod = new CreditCardPayment();
                break;
            case 2:
                paymentMethod = new PayPalPayment();
                break;
            case 3:
                paymentMethod = new UPIPayment();
                break;
            default:
                System.out.println("Invalid choice! Using default (Credit Card).");
                paymentMethod = new CreditCardPayment();
        }

        cart.setPaymentMethod(paymentMethod);
        cart.checkout(500);  // Example amount
        
        scanner.close();
    }
}

// Context Class
class ShoppingCart {
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void checkout(double amount) {
        paymentMethod.pay(amount);
    }
}

// Abstract Strategy Class
interface PaymentMethod {
    void pay(double amount);
}

// Concrete Strategy: Credit Card Payment
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Concrete Strategy: PayPal Payment
class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

// Concrete Strategy: UPI Payment
class UPIPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}
