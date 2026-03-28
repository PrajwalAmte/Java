// Strategy Pattern
// Define a family of algorithms, encapsulate each, and make them interchangeable.
// Allows the algorithm to vary independently from the clients that use it.

interface SortStrategy {
    void sort(int[] arr);
}

class BubbleSort implements SortStrategy {
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) { int t = arr[j]; arr[j] = arr[j+1]; arr[j+1] = t; }
        System.out.println("Bubble sort applied");
    }
}

class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] arr) { quickSort(arr, 0, arr.length - 1); System.out.println("Quick sort applied"); }

    private void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = arr[hi], i = lo - 1;
        for (int j = lo; j < hi; j++) if (arr[j] <= pivot) { int t = arr[++i]; arr[i] = arr[j]; arr[j] = t; }
        int t = arr[i + 1]; arr[i + 1] = arr[hi]; arr[hi] = t;
        int p = i + 1;
        quickSort(arr, lo, p - 1);
        quickSort(arr, p + 1, hi);
    }
}

class JavaBuiltinSort implements SortStrategy {
    @Override
    public void sort(int[] arr) { java.util.Arrays.sort(arr); System.out.println("Java built-in sort applied"); }
}

// Context: uses a strategy
class Sorter {
    private SortStrategy strategy;

    Sorter(SortStrategy strategy) { this.strategy = strategy; }
    void setStrategy(SortStrategy strategy) { this.strategy = strategy; }
    void sort(int[] arr) { strategy.sort(arr); }
}

// Payment system (classic strategy example)
interface PaymentStrategy {
    void pay(double amount);
}
class CreditCard implements PaymentStrategy {
    @Override public void pay(double amount) { System.out.printf("Charged $%.2f to credit card%n", amount); }
}
class PayPal implements PaymentStrategy {
    @Override public void pay(double amount) { System.out.printf("Paid $%.2f via PayPal%n", amount); }
}
class Crypto implements PaymentStrategy {
    @Override public void pay(double amount) { System.out.printf("Paid $%.2f in cryptocurrency%n", amount); }
}

class Checkout {
    private PaymentStrategy payment;
    void setPayment(PaymentStrategy p) { this.payment = p; }
    void pay(double amount) { payment.pay(amount); }
}

public class Strategy {
    public static void main(String[] args) {
        int[] data = {5, 2, 8, 1, 9, 3, 7, 4, 6};

        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sort(data.clone());

        sorter.setStrategy(new QuickSort());
        sorter.sort(data.clone());

        sorter.setStrategy(new JavaBuiltinSort());
        int[] copy = data.clone();
        sorter.sort(copy);
        System.out.println("Sorted: " + java.util.Arrays.toString(copy));

        System.out.println();

        Checkout cart = new Checkout();
        cart.setPayment(new CreditCard()); cart.pay(99.95);
        cart.setPayment(new PayPal());     cart.pay(45.00);
        cart.setPayment(new Crypto());     cart.pay(200.00);
    }
}
