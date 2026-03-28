import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        // HashMap: no guaranteed order, O(1) avg for put/get/remove
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 92);
        scores.put("Charlie", 78);
        scores.put("Alice", 90);   // overwrites
        scores.remove("Charlie");

        System.out.println("HashMap: " + scores);
        System.out.println("Alice: " + scores.get("Alice"));
        System.out.println("Has Bob: " + scores.containsKey("Bob"));
        System.out.println("Has score 92: " + scores.containsValue(92));

        // getOrDefault, putIfAbsent, merge
        System.out.println("Unknown: " + scores.getOrDefault("Unknown", 0));
        scores.putIfAbsent("Dave", 70);
        scores.merge("Alice", 5, Integer::sum);  // Alice += 5
        System.out.println("After merge: " + scores.get("Alice"));

        // Iterate entries
        System.out.println("\nAll scores:");
        for (Map.Entry<String, Integer> e : scores.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }

        // Keys and values
        System.out.println("Keys: " + scores.keySet());
        System.out.println("Values: " + scores.values());

        // TreeMap: sorted by key (natural order or custom Comparator)
        Map<String, Integer> sorted = new TreeMap<>(scores);
        System.out.println("\nTreeMap (key-sorted): " + sorted);

        TreeMap<String, Integer> treeMap = new TreeMap<>(scores);
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());

        // LinkedHashMap: maintains insertion order
        Map<String, Integer> ordered = new LinkedHashMap<>();
        ordered.put("C", 3); ordered.put("A", 1); ordered.put("B", 2);
        System.out.println("\nLinkedHashMap (insertion order): " + ordered);

        // computeIfAbsent: useful for grouping
        Map<Character, List<String>> grouped = new HashMap<>();
        for (String name : new String[]{"Alice", "Bob", "Anna", "Brian", "Carol"}) {
            grouped.computeIfAbsent(name.charAt(0), k -> new ArrayList<>()).add(name);
        }
        System.out.println("\nGrouped by first letter: " + grouped);
    }
}
