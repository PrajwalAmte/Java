import java.util.Arrays;

public class Strings {
    public static void main(String[] args) {
        String s = "Hello, World!";

        // Common methods
        System.out.println("Length: " + s.length());
        System.out.println("Upper: " + s.toUpperCase());
        System.out.println("Lower: " + s.toLowerCase());
        System.out.println("Substring(7): " + s.substring(7));
        System.out.println("Contains 'World': " + s.contains("World"));
        System.out.println("Replace: " + s.replace("World", "Java"));
        System.out.println("Trim: " + "  hi  ".trim());
        System.out.println("Strip: " + "  hi  ".strip());
        System.out.println("StartsWith: " + s.startsWith("Hello"));
        System.out.println("CharAt(0): " + s.charAt(0));
        System.out.println("IndexOf('o'): " + s.indexOf('o'));
        System.out.println("Split: " + Arrays.toString(s.split(", ")));
        System.out.println("IsBlank: " + "  ".isBlank());

        // String comparison
        String a = new String("Java");
        String b = new String("Java");
        System.out.println("== (reference): " + (a == b));       // false
        System.out.println("equals (value): " + a.equals(b));    // true
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase("java"));

        // String formatting
        String formatted = String.format("Name: %s, Score: %d, GPA: %.2f", "Alice", 95, 3.85);
        System.out.println(formatted);

        // StringBuilder for mutable string building
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) sb.append(i).append(i < 5 ? ", " : "");
        System.out.println("Built: " + sb);
        sb.reverse();
        System.out.println("Reversed: " + sb);

        // String to char array and back
        char[] chars = "hello".toCharArray();
        chars[0] = 'H';
        System.out.println("Modified: " + new String(chars));
    }
}
