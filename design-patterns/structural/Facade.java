// Facade Pattern
// Provide a simplified, unified interface to a complex subsystem.
// Reduces coupling between clients and the subsystem.

// Subsystem classes (complex, low-level)
class Amplifier {
    void on()               { System.out.println("Amplifier on"); }
    void setVolume(int v)   { System.out.println("Volume: " + v); }
    void off()              { System.out.println("Amplifier off"); }
}

class DVDPlayer {
    void on()               { System.out.println("DVD Player on"); }
    void play(String movie) { System.out.println("Playing: " + movie); }
    void eject()            { System.out.println("DVD ejected"); }
    void off()              { System.out.println("DVD Player off"); }
}

class Projector {
    void on()                   { System.out.println("Projector on"); }
    void setInput(String src)   { System.out.println("Input: " + src); }
    void wideScreenMode()       { System.out.println("Widescreen mode"); }
    void off()                  { System.out.println("Projector off"); }
}

class SmartLights {
    void dim(int pct)   { System.out.println("Lights dimmed to " + pct + "%"); }
    void on()           { System.out.println("Lights on"); }
}

class StreamingService {
    boolean login(String user)      { System.out.println("Logged in as: " + user); return true; }
    void selectMovie(String movie)  { System.out.println("Selected: " + movie); }
}

// Facade: simple interface over the entire home theater subsystem
class HomeTheater {
    private final Amplifier amp     = new Amplifier();
    private final DVDPlayer dvd       = new DVDPlayer();
    private final Projector projector = new Projector();
    private final SmartLights lights  = new SmartLights();
    private final StreamingService streaming = new StreamingService();

    void watchDVD(String movie) {
        System.out.println("\n=== Starting DVD: " + movie + " ===");
        lights.dim(10);
        amp.on(); amp.setVolume(7);
        projector.on(); projector.setInput("DVD"); projector.wideScreenMode();
        dvd.on(); dvd.play(movie);
    }

    void watchOnline(String user, String movie) {
        System.out.println("\n=== Streaming: " + movie + " ===");
        lights.dim(15);
        amp.on(); amp.setVolume(6);
        projector.on(); projector.setInput("HDMI");
        streaming.login(user); streaming.selectMovie(movie);
    }

    void endMovie() {
        System.out.println("\n=== Shutting down ===");
        dvd.off(); amp.off(); projector.off(); lights.on();
    }
}

public class Facade {
    public static void main(String[] args) {
        HomeTheater theater = new HomeTheater();

        theater.watchDVD("Inception");
        theater.endMovie();

        theater.watchOnline("alice", "Interstellar");
        theater.endMovie();
    }
}
