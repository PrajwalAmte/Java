// DIP: Dependency Inversion Principle
// High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// BAD: high-level OrderService is tightly coupled to a specific low-level EmailSenderBad
class EmailSenderBad {
    void send(String msg) { System.out.println("Email: " + msg); }
}

class OrderServiceBad {
    private final EmailSenderBad emailSender = new EmailSenderBad();  // hardcoded dependency
    void placeOrder(String item) {
        System.out.println("Order placed: " + item);
        emailSender.send("Your order for " + item + " is confirmed");
    }
}

// GOOD: both OrderService and senders depend on the MessageSender abstraction
interface MessageSender {
    void send(String message);
}

class EmailSender implements MessageSender {
    @Override public void send(String msg) { System.out.println("Email: " + msg); }
}

class SMSSender implements MessageSender {
    @Override public void send(String msg) { System.out.println("SMS: " + msg); }
}

class PushNotifier implements MessageSender {
    @Override public void send(String msg) { System.out.println("Push: " + msg); }
}

// High-level module: depends on abstraction (MessageSender), not a concrete class
class OrderService {
    private final MessageSender sender;

    OrderService(MessageSender sender) { this.sender = sender; }  // injected via constructor

    void placeOrder(String item) {
        System.out.println("Order placed: " + item);
        sender.send("Your order for " + item + " is confirmed");
    }
}

public class DIP {
    public static void main(String[] args) {
        System.out.println("--- BAD: OrderService hardcodes EmailSender ---");
        new OrderServiceBad().placeOrder("Laptop");

        System.out.println("\n--- GOOD: sender injected via abstraction ---");
        new OrderService(new EmailSender()).placeOrder("Phone");
        new OrderService(new SMSSender()).placeOrder("Tablet");
        new OrderService(new PushNotifier()).placeOrder("Watch");
    }
}
