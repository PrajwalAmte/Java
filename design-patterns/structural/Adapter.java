// Adapter Pattern
// Convert the interface of a class into another interface that clients expect.
// Allows classes with incompatible interfaces to work together.

// Target interface (what the client expects)
interface MediaPlayer {
    void play(String filename);
}

// Adaptees: third-party players with incompatible interfaces
class VLCPlayer {
    void playVLC(String file) { System.out.println("VLC  → " + file); }
}

class MP4Player {
    void playMP4(String file) { System.out.println("MP4  → " + file); }
}

class MKVPlayer {
    void open(String file)  { System.out.println("MKV open: " + file); }
    void stream()           { System.out.println("MKV streaming..."); }
}

// Adapters: make each adaptee compatible with MediaPlayer
class VLCAdapter implements MediaPlayer {
    private final VLCPlayer player = new VLCPlayer();
    @Override public void play(String file) { player.playVLC(file); }
}

class MP4Adapter implements MediaPlayer {
    private final MP4Player player = new MP4Player();
    @Override public void play(String file) { player.playMP4(file); }
}

class MKVAdapter implements MediaPlayer {
    private final MKVPlayer player = new MKVPlayer();
    @Override public void play(String file) { player.open(file); player.stream(); }
}

// Client: works only with MediaPlayer, unaware of underlying players
class AudioPlayer {
    void play(String type, String file) {
        MediaPlayer adapter = switch (type.toLowerCase()) {
            case "vlc" -> new VLCAdapter();
            case "mp4" -> new MP4Adapter();
            case "mkv" -> new MKVAdapter();
            default -> throw new IllegalArgumentException("Unsupported format: " + type);
        };
        adapter.play(file);
    }
}

// Object adapter vs Class adapter note:
// Above is "object adapter" (uses composition). "Class adapter" uses multiple inheritance (not possible in Java).

public class Adapter {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("vlc", "movie.vlc");
        player.play("mp4", "video.mp4");
        player.play("mkv", "series.mkv");

        // Directly through adapter interface
        MediaPlayer[] players = {new VLCAdapter(), new MP4Adapter()};
        for (MediaPlayer p : players) p.play("test." + p.getClass().getSimpleName().replace("Adapter", "").toLowerCase());
    }
}
