public class Recursion {

    static long factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    static int fibonacci(int n) {
        return n <= 1 ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Fibonacci with memoization
    static long fibMemo(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }

    // Binary search (recursive)
    static int binarySearch(int[] arr, int target, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (arr[mid] == target) return mid;
        return arr[mid] > target
            ? binarySearch(arr, target, lo, mid - 1)
            : binarySearch(arr, target, mid + 1, hi);
    }

    // Tower of Hanoi
    static void hanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1: " + from + " -> " + to);
            return;
        }
        hanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + ": " + from + " -> " + to);
        hanoi(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
        System.out.println("10! = " + factorial(10));

        System.out.print("Fibonacci(10): ");
        for (int i = 0; i < 10; i++) System.out.print(fibonacci(i) + " ");
        System.out.println();

        long[] memo = new long[50];
        System.out.println("Fib(45) memoized: " + fibMemo(45, memo));

        int[] sorted = {1, 3, 5, 7, 9, 11, 15};
        System.out.println("BinarySearch(7): index " + binarySearch(sorted, 7, 0, sorted.length - 1));
        System.out.println("BinarySearch(6): index " + binarySearch(sorted, 6, 0, sorted.length - 1));

        System.out.println("Tower of Hanoi (3 disks):");
        hanoi(3, 'A', 'C', 'B');
    }
}
