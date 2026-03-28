import java.util.ArrayList;
import java.util.List;

// Observer Pattern
// Define a one-to-many dependency: when one object changes state,
// all dependents are notified and updated automatically.

interface EventObserver {
    void onEvent(String eventType, Object data);
}

interface EventEmitter {
    void subscribe(String event, EventObserver observer);
    void unsubscribe(String event, EventObserver observer);
    void emit(String event, Object data);
}

class EventBus implements EventEmitter {
    private final java.util.Map<String, List<EventObserver>> listeners = new java.util.HashMap<>();

    @Override
    public void subscribe(String event, EventObserver observer) {
        listeners.computeIfAbsent(event, k -> new ArrayList<>()).add(observer);
    }

    @Override
    public void unsubscribe(String event, EventObserver observer) {
        List<EventObserver> obs = listeners.get(event);
        if (obs != null) obs.remove(observer);
    }

    @Override
    public void emit(String event, Object data) {
        List<EventObserver> obs = listeners.getOrDefault(event, List.of());
        for (EventObserver o : obs) o.onEvent(event, data);
    }
}

// Concrete observers
class EmailNotifier implements EventObserver {
    private final String email;
    EmailNotifier(String email) { this.email = email; }
    @Override public void onEvent(String event, Object data) {
        System.out.println("Email [" + email + "] " + event + ": " + data);
    }
}

class SmsNotifier implements EventObserver {
    @Override public void onEvent(String event, Object data) {
        System.out.println("SMS: " + event + " -> " + data);
    }
}

class AuditLog implements EventObserver {
    @Override public void onEvent(String event, Object data) {
        System.out.println("[AUDIT] " + java.time.LocalTime.now() + " | " + event + " | " + data);
    }
}

// Real-world example: stock price watcher
class StockMarket {
    private final EventBus bus = new EventBus();
    private final java.util.Map<String, Double> prices = new java.util.HashMap<>();

    void addWatcher(String ticker, EventObserver observer) { bus.subscribe(ticker, observer); }
    void removeWatcher(String ticker, EventObserver observer) { bus.unsubscribe(ticker, observer); }

    void updatePrice(String ticker, double newPrice) {
        double old = prices.getOrDefault(ticker, 0.0);
        prices.put(ticker, newPrice);
        bus.emit(ticker, String.format("%.2f (was %.2f)", newPrice, old));
    }
}

public class Observer {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        EventObserver email = new EmailNotifier("admin@example.com");
        EventObserver sms = new SmsNotifier();
        EventObserver audit = new AuditLog();

        bus.subscribe("user.login", email);
        bus.subscribe("user.login", audit);
        bus.subscribe("order.placed", email);
        bus.subscribe("order.placed", sms);
        bus.subscribe("order.placed", audit);

        System.out.println("=== User Login ===");
        bus.emit("user.login", "alice");

        System.out.println("\n=== Order Placed ===");
        bus.emit("order.placed", "Order#1001 for $49.99");

        System.out.println("\n=== After SMS unsubscribed from order.placed ===");
        bus.unsubscribe("order.placed", sms);
        bus.emit("order.placed", "Order#1002 for $89.00");

        System.out.println("\n=== Stock Market ===");
        StockMarket market = new StockMarket();
        EventObserver trader1 = (e, d) -> System.out.println("Trader1 on " + e + ": " + d);
        EventObserver trader2 = (e, d) -> System.out.println("Trader2 on " + e + ": " + d);

        market.addWatcher("AAPL", trader1);
        market.addWatcher("AAPL", trader2);
        market.addWatcher("GOOGL", trader1);

        market.updatePrice("AAPL", 182.50);
        market.updatePrice("GOOGL", 140.30);

        market.removeWatcher("AAPL", trader2);
        market.updatePrice("AAPL", 185.00);
    }
}
