import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class LambdasStreams {
    public static void main(String[] args) {
        // --- Functional interfaces and lambda expressions ---
        Runnable r = () -> System.out.println("Running!");
        r.run();

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> strLen = String::length;       // method reference
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Consumer<String> greet = name -> System.out.println("Hello, " + name);
        Supplier<List<String>> listFactory = ArrayList::new;

        System.out.println("isEven(4): " + isEven.test(4));
        System.out.println("strLen(\"hello\"): " + strLen.apply("hello"));
        System.out.println("multiply(3,4): " + multiply.apply(3, 4));
        greet.accept("World");
        System.out.println("Supplier: " + listFactory.get().getClass().getSimpleName());

        // Composing predicates and functions
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("4 is even & positive: " + isEvenAndPositive.test(4));
        System.out.println("-2 is even & positive: " + isEvenAndPositive.test(-2));

        // --- Stream API ---
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter + map + collect
        List<Integer> evenSquares = nums.stream()
            .filter(isEven)
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Even squares: " + evenSquares);

        // reduce
        int sum = nums.stream().reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        // sorted, distinct, limit, skip
        List<Integer> raw = Arrays.asList(5, 3, 1, 4, 1, 5, 9, 2, 6, 5);
        List<Integer> processed = raw.stream()
            .distinct().sorted().skip(1).limit(5)
            .collect(Collectors.toList());
        System.out.println("Distinct→sorted→skip1→limit5: " + processed);

        // anyMatch, allMatch, noneMatch, count, findFirst
        System.out.println("Any > 5: " + nums.stream().anyMatch(n -> n > 5));
        System.out.println("All > 0: " + nums.stream().allMatch(n -> n > 0));
        System.out.println("Count even: " + nums.stream().filter(isEven).count());
        nums.stream().filter(n -> n > 7).findFirst()
            .ifPresent(v -> System.out.println("First > 7: " + v));

        // min, max via Comparator
        Optional<Integer> max = nums.stream().max(Integer::compareTo);
        System.out.println("Max: " + max.orElse(-1));

        // Collectors.groupingBy
        List<String> words = List.of("apple", "bat", "cat", "avocado", "banana", "cherry");
        Map<Character, List<String>> byLetter = words.stream()
            .collect(Collectors.groupingBy(w -> w.charAt(0)));
        new TreeMap<>(byLetter).forEach((k, v) -> System.out.println(k + ": " + v));

        // Collectors.joining
        String joined = words.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined: " + joined);

        // flatMap: flatten nested lists
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4), List.of(5, 6));
        List<Integer> flat = nested.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("FlatMapped: " + flat);

        // mapToInt for primitive stream
        int[] arr = {1, 2, 3, 4, 5};
        int total = Arrays.stream(arr).filter(n -> n % 2 != 0).sum();
        System.out.println("Sum of odd: " + total);
    }
}
