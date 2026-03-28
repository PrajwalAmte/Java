// SRP: Single Responsibility Principle
// A class should have only one reason to change.

// BAD: One class handles user creation, persistence, email, AND reporting
class UserManagerBad {
    void createUser(String name) { System.out.println("Creating: " + name); }
    void saveToDatabase(String name) { System.out.println("Saving to DB: " + name); }
    void sendWelcomeEmail(String name) { System.out.println("Emailing: " + name); }
    void generateReport() { System.out.println("Generating report"); }
}

// GOOD: Each class has exactly one responsibility
class UserService {
    void createUser(String name) { System.out.println("Creating user: " + name); }
}

class UserRepository {
    void save(String name) { System.out.println("Saving to DB: " + name); }
    String findByName(String name) { return "User(" + name + ")"; }
}

class EmailService {
    void sendWelcome(String name) { System.out.println("Welcome email sent to: " + name); }
}

class ReportService {
    void generateUserReport() { System.out.println("User report generated"); }
}

class UserRegistrationFacade {
    private final UserService userService = new UserService();
    private final UserRepository repo = new UserRepository();
    private final EmailService emailService = new EmailService();

    void register(String name) {
        userService.createUser(name);
        repo.save(name);
        emailService.sendWelcome(name);
    }
}

public class SRP {
    public static void main(String[] args) {
        System.out.println("--- BAD: single class does everything ---");
        UserManagerBad bad = new UserManagerBad();
        bad.createUser("Alice");
        bad.saveToDatabase("Alice");
        bad.sendWelcomeEmail("Alice");
        bad.generateReport();

        System.out.println("\n--- GOOD: each class has one job ---");
        UserRegistrationFacade facade = new UserRegistrationFacade();
        facade.register("Bob");
        new ReportService().generateUserReport();
    }
}
