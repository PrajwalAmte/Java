import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileIO {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("demo.txt");

        // Write (creates or overwrites)
        Files.writeString(file, "Line 1\nLine 2\nLine 3\n");
        System.out.println("Written: " + file.toAbsolutePath());

        // Read all at once
        String content = Files.readString(file);
        System.out.println("Content:\n" + content.strip());

        // Read line by line with BufferedReader
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            int n = 1;
            while ((line = reader.readLine()) != null)
                System.out.println(n++ + ": " + line);
        }

        // Read all lines into List
        List<String> lines = Files.readAllLines(file);
        System.out.println("Lines: " + lines.size());

        // Append
        Files.writeString(file, "Line 4\n", StandardOpenOption.APPEND);
        System.out.println("After append, lines: " + Files.readAllLines(file).size());

        // Write with BufferedWriter
        Path output = Path.of("output.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("Hello from BufferedWriter");
            writer.newLine();
            writer.write("Second line");
        }
        System.out.println("BufferedWriter output: " + Files.readString(output));

        // File metadata
        System.out.println("Exists: " + Files.exists(file));
        System.out.println("Size: " + Files.size(file) + " bytes");
        System.out.println("Is regular file: " + Files.isRegularFile(file));

        // Copy, move
        Path backup = Path.of("demo-backup.txt");
        Files.copy(file, backup, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Backup created: " + Files.exists(backup));

        // Cleanup
        Files.deleteIfExists(file);
        Files.deleteIfExists(output);
        Files.deleteIfExists(backup);
        System.out.println("Cleaned up.");
    }
}
