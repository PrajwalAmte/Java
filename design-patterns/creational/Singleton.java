// Singleton Pattern
// Ensure a class has only one instance and provide a global access point to it.
// Uses double-checked locking for thread safety.

class Logger {
    private static volatile Logger instance;

    private Logger() { System.out.println("Logger created (only once)"); }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) instance = new Logger();
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Alternative: Enum Singleton (thread-safe, serialization-safe by default)
enum AppConfig {
    INSTANCE;

    private final String dbUrl = "jdbc:mysql://localhost:3306/mydb";
    private int maxConnections = 10;

    public String getDbUrl() { return dbUrl; }
    public int getMaxConnections() { return maxConnections; }
    public void setMaxConnections(int n) { this.maxConnections = n; }
}

public class Singleton {
    public static void main(String[] args) {
        Logger l1 = Logger.getInstance();
        l1.log("Application started");

        Logger l2 = Logger.getInstance();
        l2.log("User logged in");

        System.out.println("Same instance: " + (l1 == l2));  // true

        // Enum singleton
        AppConfig config = AppConfig.INSTANCE;
        System.out.println("DB URL: " + config.getDbUrl());
        config.setMaxConnections(20);
        System.out.println("Max connections: " + AppConfig.INSTANCE.getMaxConnections());
    }
}
