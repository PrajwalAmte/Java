// State Pattern
// Allow an object to alter its behavior when its internal state changes.
// The object appears to change its class.

interface OrderState {
    void next(Order order);
    void cancel(Order order);
    String status();
}

class PendingState implements OrderState {
    @Override public void next(Order o)   { System.out.println("Payment confirmed"); o.setState(new ProcessingState()); }
    @Override public void cancel(Order o) { System.out.println("Order cancelled");   o.setState(new CancelledState()); }
    @Override public String status()      { return "PENDING"; }
}

class ProcessingState implements OrderState {
    @Override public void next(Order o)   { System.out.println("Order shipped");      o.setState(new ShippedState()); }
    @Override public void cancel(Order o) { System.out.println("Order cancelled while processing"); o.setState(new CancelledState()); }
    @Override public String status()      { return "PROCESSING"; }
}

class ShippedState implements OrderState {
    @Override public void next(Order o)   { System.out.println("Order delivered");   o.setState(new DeliveredState()); }
    @Override public void cancel(Order o) { System.out.println("Cannot cancel: already shipped"); }
    @Override public String status()      { return "SHIPPED"; }
}

class DeliveredState implements OrderState {
    @Override public void next(Order o)   { System.out.println("Order already delivered"); }
    @Override public void cancel(Order o) { System.out.println("Cannot cancel: already delivered"); }
    @Override public String status()      { return "DELIVERED"; }
}

class CancelledState implements OrderState {
    @Override public void next(Order o)   { System.out.println("Order is cancelled, cannot proceed"); }
    @Override public void cancel(Order o) { System.out.println("Already cancelled"); }
    @Override public String status()      { return "CANCELLED"; }
}

class Order {
    private OrderState state = new PendingState();
    private final String id;

    Order(String id) { this.id = id; }

    void setState(OrderState state) { this.state = state; }
    void next()   { state.next(this); }
    void cancel() { state.cancel(this); }
    void printStatus() { System.out.println("Order " + id + ": " + state.status()); }
}

// Classic traffic light example for comparison
interface LightState {
    void change(TrafficLight light);
    String color();
}

class RedLight implements LightState {
    @Override public void change(TrafficLight l) { l.setState(new GreenLight()); }
    @Override public String color() { return "RED - Stop!"; }
}
class GreenLight implements LightState {
    @Override public void change(TrafficLight l) { l.setState(new YellowLight()); }
    @Override public String color() { return "GREEN - Go!"; }
}
class YellowLight implements LightState {
    @Override public void change(TrafficLight l) { l.setState(new RedLight()); }
    @Override public String color() { return "YELLOW - Slow down"; }
}

class TrafficLight {
    private LightState state = new RedLight();
    void setState(LightState s) { this.state = s; }
    void change() { System.out.println(state.color()); state.change(this); }
}

public class State {
    public static void main(String[] args) {
        System.out.println("=== Order Lifecycle ===");
        Order order = new Order("#1001");
        order.printStatus();
        order.next();  order.printStatus();
        order.next();  order.printStatus();
        order.cancel(); // too late
        order.next();  order.printStatus();

        System.out.println("\n=== Order Cancellation ===");
        Order order2 = new Order("#1002");
        order2.next();    // pending -> processing
        order2.cancel();  // cancel while processing
        order2.printStatus();

        System.out.println("\n=== Traffic Light (6 changes) ===");
        TrafficLight light = new TrafficLight();
        for (int i = 0; i < 6; i++) light.change();
    }
}
