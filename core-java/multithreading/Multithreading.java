import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Multithreading {

    // Thread-safe counter using AtomicInteger (no synchronized needed)
    static class Counter {
        private final AtomicInteger count = new AtomicInteger(0);
        void increment() { count.incrementAndGet(); }
        int get() { return count.get(); }
    }

    // Shared resource demonstrating synchronized
    static class SharedResource {
        private int value = 0;
        synchronized void safeIncrement() { value++; }
        int getValue() { return value; }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1. Thread via Runnable (lambda)
        Thread t1 = new Thread(() -> System.out.println("Thread via lambda: " + Thread.currentThread().getName()));
        t1.start();
        t1.join();  // wait for thread to finish

        // 2. Thread subclass
        Thread t2 = new Thread() {
            @Override public void run() { System.out.println("Thread subclass: " + getName()); }
        };
        t2.start();
        t2.join();

        // 3. AtomicInteger across multiple threads
        Counter counter = new Counter();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) counter.increment();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("AtomicCounter (5 threads × 1000): " + counter.get());  // always 5000

        // 4. synchronized method
        SharedResource res = new SharedResource();
        Thread[] syncThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) res.safeIncrement();
            });
            syncThreads[i].start();
        }
        for (Thread t : syncThreads) t.join();
        System.out.println("Synchronized (5 threads × 1000): " + res.getValue());  // always 5000

        // 5. ExecutorService with Callable and Future
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(100);
            return 42;
        });
        System.out.println("Future result: " + future.get());

        // 6. Multiple futures
        List<Future<String>> futures = new java.util.ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            final int id = i;
            futures.add(executor.submit(() -> "Task-" + id + " done by " + Thread.currentThread().getName()));
        }
        for (Future<String> f : futures) System.out.println(f.get());

        executor.shutdown();

        // 7. ScheduledExecutorService
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> System.out.println("Delayed task executed"), 200, TimeUnit.MILLISECONDS);
        Thread.sleep(400);
        scheduler.shutdown();
    }
}
