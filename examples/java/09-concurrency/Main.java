// Concurrency in Java
// ==================
// Java: threads, executors, futures, CompletableFuture

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // ── Basic thread ────────────────────────────
        System.out.println("--- Basic thread ---");

        Thread thread = new Thread(() -> System.out.println("Hello from thread!"));
        thread.start();
        thread.join();

        // ── Runnable (preferred) ─────────────────────
        System.out.println("\n--- Runnable ---");

        Runnable task = () -> System.out.println("Running in thread");
        new Thread(task).start();
        Thread.sleep(100);

        // ── ExecutorService (thread pool) ─────────────
        System.out.println("\n--- ExecutorService ---");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running on " + Thread.currentThread().getName());
            });
        }

        Thread.sleep(500);
        executor.shutdown();

        // ── Callable + Future ─────────────────────────
        System.out.println("\n--- Callable + Future ---");

        ExecutorService executor2 = Executors.newSingleThreadExecutor();

        Callable<Integer> computeTask = () -> {
            Thread.sleep(200);
            return 42;
        };

        Future<Integer> future = executor2.submit(computeTask);
        System.out.println("Computing...");
        Integer result = future.get();  // blocks until complete
        System.out.println("Result: " + result);

        executor2.shutdown();

        // ── CompletableFuture (chainable) ────────────
        System.out.println("\n--- CompletableFuture ---");

        CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(200); } catch (Exception e) {}
                    return "Hello";
                })
                .thenApplyAsync(String::toUpperCase)
                .thenAcceptAsync(msg -> System.out.println("Message: " + msg))
                .join();

        // thenCompose — chain dependent async operations
        System.out.println("\n--- CompletableFuture thenCompose ---");

        CompletableFuture<String> userFuture = CompletableFuture
                .supplyAsync(() -> 42L)
                .thenCompose(id -> CompletableFuture.supplyAsync(() -> "User#" + id));
        System.out.println("Chained result: " + userFuture.join());

        // allOf — wait for multiple futures
        System.out.println("\n--- CompletableFuture allOf ---");

        CompletableFuture<Integer> price = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> stock = CompletableFuture.supplyAsync(() -> 50);
        CompletableFuture<Void> all = CompletableFuture.allOf(price, stock);
        all.thenRun(() -> {
            int p = price.resultNow();
            int s = stock.resultNow();
            System.out.println("Price: " + p + ", Stock: " + s);
        }).join();

        // anyOf — first to complete wins
        System.out.println("\n--- CompletableFuture anyOf ---");

        CompletableFuture<Object> fastest = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(300); } catch (Exception e) {}
                    return "primary";
                }),
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(100); } catch (Exception e) {}
                    return "fallback";
                })
        );
        System.out.println("Fastest: " + fastest.join());

        // exceptionally — recover from error
        System.out.println("\n--- CompletableFuture exceptionally ---");

        CompletableFuture<String> withFallback = CompletableFuture
                .<String>supplyAsync(() -> { throw new RuntimeException("oops"); })
                .exceptionally(ex -> "recovered: " + ex.getMessage());
        System.out.println("Result: " + withFallback.join());

        // orTimeout — fail if not completed in time (Java 9+)
        System.out.println("\n--- CompletableFuture orTimeout ---");

        try {
            CompletableFuture<String> timeout = CompletableFuture
                    .supplyAsync(() -> {
                        try { Thread.sleep(5000); } catch (Exception e) {}
                        return "too late";
                    })
                    .orTimeout(100, TimeUnit.MILLISECONDS);
            System.out.println("Result: " + timeout.join());
        } catch (CompletionException e) {
            System.out.println("Timed out as expected: " + e.getCause().getClass().getSimpleName());
        }

        // ── Parallel stream ───────────────────────────
        System.out.println("\n--- Parallel stream ---");

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> squares = numbers.parallelStream()
                .map(x -> x * x)
                .toList();

        System.out.println("Squares (parallel): " + squares);

        // ── Synchronized block ─────────────────────────
        System.out.println("\n--- Synchronized ---");

        Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) t.join();
        System.out.println("Counter value: " + counter.getValue());

        // ── ReentrantLock ─────────────────────────────
        System.out.println("\n--- ReentrantLock ---");

        ReentrantLock lock = new ReentrantLock();
        SharedData data = new SharedData();

        Runnable writer = () -> {
            lock.lock();
            try {
                data.setValue(data.getValue() + 1);
            } finally {
                lock.unlock();
            }
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(writer);

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Shared data: " + data.getValue());

        // ── CountDownLatch ────────────────────────────
        System.out.println("\n--- CountDownLatch ---");

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                System.out.println("Worker " + id + " started");
                latch.countDown();
            }).start();
        }

        latch.await();  // wait for all 3 workers
        System.out.println("All workers completed");

        // ── Producer-consumer with BlockingQueue ─────
        System.out.println("\n--- Producer-consumer ---");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {}
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer item = queue.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {}
        };

        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(2000);

        // ── Summary ─────────────────────────────────────
        System.out.println("\n--- Summary ---");
        System.out.println("Java concurrency:");
        System.out.println("  - Thread class: basic thread creation");
        System.out.println("  - Runnable: preferred over extending Thread");
        System.out.println("  - ExecutorService: thread pool management");
        System.out.println("  - Future: handle async results");
        System.out.println("  - CompletableFuture: chainable async operations");
        System.out.println("  - synchronized: intrinsic locking");
        System.out.println("  - ReentrantLock: explicit lock");
        System.out.println("  - BlockingQueue: thread-safe collections");
    }

    static class Counter {
        private int value = 0;

        public synchronized void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }

    static class SharedData {
        private int value = 0;

        public void setValue(int v) { value = v; }
        public int getValue() { return value; }
    }
}
