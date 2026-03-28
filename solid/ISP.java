// ISP: Interface Segregation Principle
// Clients should not be forced to depend on interfaces they do not use.
// Prefer many small, focused interfaces over one large fat interface.

// BAD: one fat interface forces Robot to throw for irrelevant methods
interface WorkerBad {
    void work();
    void eat();
    void sleep();
    void attendMeeting();
}

class HumanBad implements WorkerBad {
    @Override public void work() { System.out.println("Human working"); }
    @Override public void eat() { System.out.println("Human eating"); }
    @Override public void sleep() { System.out.println("Human sleeping"); }
    @Override public void attendMeeting() { System.out.println("Human in meeting"); }
}

class RobotBad implements WorkerBad {
    @Override public void work() { System.out.println("Robot working"); }
    @Override public void eat() { throw new UnsupportedOperationException("Robots don't eat"); }
    @Override public void sleep() { throw new UnsupportedOperationException("Robots don't sleep"); }
    @Override public void attendMeeting() { throw new UnsupportedOperationException(); }
}

// GOOD: segregated interfaces — each client implements only what it needs
interface Workable { void work(); }
interface Eatable { void eat(); }
interface Sleepable { void sleep(); }
interface Meetable { void attendMeeting(); }

class Human implements Workable, Eatable, Sleepable, Meetable {
    @Override public void work() { System.out.println("Human working"); }
    @Override public void eat() { System.out.println("Human eating"); }
    @Override public void sleep() { System.out.println("Human sleeping"); }
    @Override public void attendMeeting() { System.out.println("Human in meeting"); }
}

class Robot implements Workable {
    @Override public void work() { System.out.println("Robot working"); }
}

class Intern implements Workable, Eatable, Meetable {
    @Override public void work() { System.out.println("Intern working"); }
    @Override public void eat() { System.out.println("Intern eating"); }
    @Override public void attendMeeting() { System.out.println("Intern in meeting"); }
}

public class ISP {
    public static void main(String[] args) {
        System.out.println("--- BAD ---");
        WorkerBad human = new HumanBad();
        human.work();
        try { new RobotBad().eat(); } catch (UnsupportedOperationException e) {
            System.out.println("Robot.eat() -> " + e.getClass().getSimpleName());
        }

        System.out.println("\n--- GOOD ---");
        Workable[] workers = {new Human(), new Robot(), new Intern()};
        for (Workable w : workers) w.work();

        Eatable[] eaters = {new Human(), new Intern()};
        for (Eatable e : eaters) e.eat();
    }
}
