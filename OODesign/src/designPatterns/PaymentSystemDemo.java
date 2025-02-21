package designPatterns;
import java.util.Scanner;

/**
 * Demonstrates the Strategy Pattern by implementing a payment system.
 * The payment strategy can be selected dynamically at runtime.
 */
public class PaymentSystemDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart(); 

        // Prompt user to choose a payment method
        System.out.println("Choose a payment method: 1. Credit Card  2. PayPal  3. UPI");
        int choice = scanner.nextInt();

        PaymentMethod paymentMethod;
        
        // Assign appropriate payment strategy based on user choice
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

        // Set selected payment method in the shopping cart
        cart.setPaymentMethod(paymentMethod);
        cart.checkout(500);  // Example amount
    }
}

/**
 * Context class that holds a reference to a PaymentMethod strategy.
 */
class ShoppingCart {
    private PaymentMethod paymentMethod;
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void checkout(double amount) {
        paymentMethod.pay(amount);
    }
}

/**
 * Strategy interface representing a generic payment method.
 */
interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class UPIPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}
