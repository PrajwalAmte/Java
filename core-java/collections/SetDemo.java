import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        // HashSet: no duplicates, no order, O(1) add/remove/contains
        Set<String> fruits = new HashSet<>(Arrays.asList("Apple", "Banana", "Apple", "Cherry"));
        System.out.println("HashSet (no dup): " + fruits);

        fruits.add("Date");
        fruits.remove("Banana");
        System.out.println("After add/remove: " + fruits);
        System.out.println("Contains Cherry: " + fruits.contains("Cherry"));

        // TreeSet: sorted (natural order or Comparator), O(log n) ops
        Set<Integer> numbers = new TreeSet<>(Arrays.asList(5, 3, 8, 1, 9, 3, 2));
        System.out.println("\nTreeSet (sorted): " + numbers);

        TreeSet<Integer> treeSet = new TreeSet<>(numbers);
        System.out.println("First: " + treeSet.first() + ", Last: " + treeSet.last());
        System.out.println("HeadSet(<5): " + treeSet.headSet(5));
        System.out.println("TailSet(>=5): " + treeSet.tailSet(5));

        // LinkedHashSet: maintains insertion order
        Set<String> ordered = new LinkedHashSet<>(Arrays.asList("C", "A", "B", "A"));
        System.out.println("\nLinkedHashSet (insertion order): " + ordered);

        // Set operations
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        Set<Integer> union = new HashSet<>(A);
        union.addAll(B);
        System.out.println("\nA = " + new TreeSet<>(A));
        System.out.println("B = " + new TreeSet<>(B));
        System.out.println("Union: " + new TreeSet<>(union));

        Set<Integer> intersection = new HashSet<>(A);
        intersection.retainAll(B);
        System.out.println("Intersection: " + new TreeSet<>(intersection));

        Set<Integer> difference = new HashSet<>(A);
        difference.removeAll(B);
        System.out.println("A - B: " + new TreeSet<>(difference));
    }
}
