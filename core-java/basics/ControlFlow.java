public class ControlFlow {
    public static void main(String[] args) {
        // if-else
        int score = 75;
        if (score >= 90) System.out.println("Grade: A");
        else if (score >= 75) System.out.println("Grade: B");
        else System.out.println("Grade: C");

        // switch (classic)
        int day = 3;
        switch (day) {
            case 1: System.out.println("Monday"); break;
            case 2: System.out.println("Tuesday"); break;
            case 3: System.out.println("Wednesday"); break;
            default: System.out.println("Other");
        }

        // for loop
        int sum = 0;
        for (int i = 1; i <= 10; i++) sum += i;
        System.out.println("Sum 1-10: " + sum);

        // while loop
        int n = 5, factorial = 1;
        while (n > 0) factorial *= n--;
        System.out.println("5! = " + factorial);

        // do-while
        int count = 1;
        do {
            System.out.print(count + " ");
        } while (++count <= 5);
        System.out.println();

        // enhanced for
        int[] primes = {2, 3, 5, 7, 11};
        for (int p : primes) System.out.print(p + " ");
        System.out.println();

        // break and continue
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) continue;
            if (i > 7) break;
            System.out.print(i + " ");
        }
        System.out.println();

        // ternary operator
        int a = 10, b = 20;
        System.out.println("Max: " + (a > b ? a : b));
    }
}
