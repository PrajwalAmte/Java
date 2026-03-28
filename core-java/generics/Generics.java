import java.util.*;

// Generic class with two type parameters
class Pair<A, B> {
    private final A first;
    private final B second;

    Pair(A first, B second) { this.first = first; this.second = second; }

    A getFirst() { return first; }
    B getSecond() { return second; }

    @Override
    public String toString() { return "(" + first + ", " + second + ")"; }
}

// Generic class with bounded type parameter
class MinMaxFinder<T extends Comparable<T>> {
    private final List<T> items;

    MinMaxFinder(List<T> items) { this.items = items; }

    T min() { return items.stream().min(Comparator.naturalOrder()).orElseThrow(); }
    T max() { return items.stream().max(Comparator.naturalOrder()).orElseThrow(); }
}

// Generic stack
class Stack<T> {
    private final List<T> data = new ArrayList<>();

    void push(T item) { data.add(item); }
    T pop() {
        if (data.isEmpty()) throw new EmptyStackException();
        return data.remove(data.size() - 1);
    }
    T peek() { return data.get(data.size() - 1); }
    boolean isEmpty() { return data.isEmpty(); }
    int size() { return data.size(); }

    @Override public String toString() { return data.toString(); }
}

// Generic utility methods
class Utils {
    // Generic method
    static <T extends Comparable<T>> T max(T a, T b) { return a.compareTo(b) >= 0 ? a : b; }

    // Wildcard: upper bounded (read-only)
    static double sum(List<? extends Number> list) {
        return list.stream().mapToDouble(Number::doubleValue).sum();
    }

    // Wildcard: lower bounded (write-compatible)
    static void addNumbers(List<? super Integer> list) {
        list.add(1); list.add(2); list.add(3);
    }
}

public class Generics {
    public static void main(String[] args) {
        // Generic class
        Pair<String, Integer> person = new Pair<>("Alice", 30);
        System.out.println("Pair: " + person);

        // Bounded generic class
        MinMaxFinder<Integer> finder = new MinMaxFinder<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Min: " + finder.min() + ", Max: " + finder.max());

        MinMaxFinder<String> strFinder = new MinMaxFinder<>(Arrays.asList("banana", "apple", "cherry"));
        System.out.println("Min string: " + strFinder.min());

        // Generic stack
        Stack<String> stack = new Stack<>();
        stack.push("A"); stack.push("B"); stack.push("C");
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());

        // Generic methods
        System.out.println("max(3, 7): " + Utils.max(3, 7));
        System.out.println("max(\"cat\", \"dog\"): " + Utils.max("cat", "dog"));

        // Upper bounded wildcard
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println("Sum ints: " + Utils.sum(ints));
        System.out.println("Sum doubles: " + Utils.sum(doubles));

        // Lower bounded wildcard
        List<Number> numbers = new ArrayList<>();
        Utils.addNumbers(numbers);
        System.out.println("Added numbers: " + numbers);
    }
}
