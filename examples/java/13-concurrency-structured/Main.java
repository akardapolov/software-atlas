package org.example;

import java.util.concurrent.*;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.FailedException;

/**
 * Java Concurrency Evolution: Future → CompletableFuture → Structured Concurrency
 *
 * This file demonstrates three concurrency patterns using all three APIs:
 *
 *   PATTERN A — Sequential Pipeline:
 *     Fetch → Transform → Save (tasks depend on each other, run one after another)
 *
 *   PATTERN B — Async Processing:
 *     Non-blocking chain: supplyAsync → thenApplyAsync → thenRun
 *
 *   PATTERN C — Parallel Pipelines:
 *     Two independent pipelines run in parallel, results are combined at the end:
 *       Pipeline 1: fetchUser → fetchUserRole
 *       Pipeline 2: fetchProduct → calculateDiscount
 *       Combined:   "Role: X, Price: Y"
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * THE CHECKED EXCEPTION CONFLICT: COMPLETABLEFUTURE VS STRUCTURED CONCURRENCY
 * ─────────────────────────────────────────────────────────────────────────────
 * - CompletableFuture's supplyAsync/thenApply take Supplier/Function, which DO NOT
 *   allow checked exceptions. This leads to "Dirty" code with try-catch boilerplate.
 *   We solve this in production using "Clean Wrapper Helpers" (see examples).
 *
 * - Structured Concurrency's scope.fork() accepts Callable, which naturally
 *   allows checked exceptions (throws Exception). You can call throwing methods
 *   directly without any wrappers or try-catch boilerplate!
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * Requires Java 26 (StructuredTaskScope is preview in Java 24-26).
 * Compile with: --enable-preview --release 26
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // ══════════════════════════════════════════════════════════════════════
        //  PATTERN A — SEQUENTIAL PIPELINE
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  PATTERN A — SEQUENTIAL PIPELINE                ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        System.out.println("\n--- A1: Future (sequential, manual chaining) ---");
        patternA_Future();

        System.out.println("\n--- A2: CompletableFuture (single virtual thread) ---");
        patternA_CompletableFuture();

        System.out.println("\n--- A3: Structured Concurrency (single virtual thread via scope.fork) ---");
        patternA_StructuredConcurrency();

        // ══════════════════════════════════════════════════════════════════════
        //  PATTERN B — ASYNC PROCESSING
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║  PATTERN B — ASYNC PROCESSING                   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        System.out.println("\n--- B1: Future (blocking main thread between steps) ---");
        patternB_Future();

        System.out.println("\n--- B2: CompletableFuture (thenApplyAsync chain) ---");
        patternB_CompletableFuture();

        System.out.println("\n--- B3: Structured Concurrency (sequential inside one fork) ---");
        patternB_StructuredConcurrency();

        // ══════════════════════════════════════════════════════════════════════
        //  PATTERN C — PARALLEL PIPELINES (THE EXCEPTION COMPOSABILITY SHOWCASE)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║  PATTERN C — PARALLEL PIPELINES                 ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        System.out.println("\n--- C1: Future (parallel submit, blocking join) ---");
        patternC_Future();

        System.out.println("\n--- C2 (Dirty): CompletableFuture with inline try-catch noise ---");
        patternC_CompletableFuture_Dirty();

        System.out.println("\n--- C2 (Clean): CompletableFuture utilizing clean helper wrappers ---");
        patternC_CompletableFuture_Clean();

        System.out.println("\n--- C3 (Native): Structured Concurrency (Forks call throwing methods directly) ---");
        patternC_StructuredConcurrency();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  PATTERN A — SEQUENTIAL PIPELINE
    // ══════════════════════════════════════════════════════════════════════════

    private static void patternA_Future() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> fetchFuture = executor.submit(() -> fetchValue());
            String rawData = fetchFuture.get(); // blocks current thread

            Future<String> transformFuture = executor.submit(() -> transform(rawData));
            String processedData = transformFuture.get(); // blocks again

            Future<?> saveFuture = executor.submit(() -> {
                save(processedData);
                return null;
            });
            saveFuture.get(); // blocks again
        }
    }

    private static void patternA_CompletableFuture() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
                try {
                    String rawData = fetchValue();
                    String processedData = transform(rawData);
                    save(processedData);
                    return (Void) null;
                } catch (Exception e) {
                    throw new RuntimeException("Pipeline failed: " + e.getMessage(), e);
                }
            }, executor);

            cf.get(); // IN TESTS: block main thread so JVM doesn't exit.
        }
    }

    private static void patternA_StructuredConcurrency() {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (var scope = StructuredTaskScope.<Void>open()) {
            scope.fork(() -> {
                String rawData  = fetchValue();
                String processed = transform(rawData);
                save(processed);
                return null;
            });

            scope.join(); // Correct production pattern when owner is a Virtual Thread
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (FailedException e) {
            System.err.println("Pipeline failed: " + e.getCause().getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  PATTERN B — ASYNC PROCESSING
    // ══════════════════════════════════════════════════════════════════════════

    private static void patternB_Future() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> step1 = executor.submit(() -> {
                System.out.println("  Step 1 on: " + Thread.currentThread().getName());
                return "New value";
            });
            String result1 = step1.get(); // blocks main thread

            Future<String> step2 = executor.submit(() -> {
                System.out.println("  Step 2 on: " + Thread.currentThread().getName());
                return result1.toLowerCase();
            });
            String result2 = step2.get(); // blocks again

            Future<?> step3 = executor.submit(() -> {
                System.out.println("  Step 3 (all done) on: " + Thread.currentThread().getName() + " val=" + result2);
            });
            step3.get();
        }
    }

    private static void patternB_CompletableFuture() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture<Void> cf = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("  Step 1 on: " + Thread.currentThread().getName());
                    return "New value";
                }, executor)
                .thenApplyAsync(result -> {
                    System.out.println("  Step 2 on: " + Thread.currentThread().getName());
                    return result.toLowerCase();
                }, executor)
                .thenRun(() ->
                             System.out.println("  Step 3 (all done) on: " + Thread.currentThread().getName())
                );

            cf.join(); // IN TESTS: keeps main thread alive.
        }
    }

    private static void patternB_StructuredConcurrency() {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (var scope = StructuredTaskScope.<Void>open()) {
            scope.fork(() -> {
                System.out.println("  Step 1 on: " + Thread.currentThread().getName());
                String result = "New value";
                Thread.sleep(50);

                System.out.println("  Step 2 on: " + Thread.currentThread().getName());
                String processed = result.toLowerCase();

                System.out.println("  Step 3 (all done) on: " + Thread.currentThread().getName() + " val=" + processed);
                return null;
            });

            scope.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  PATTERN C — PARALLEL PIPELINES
    // ══════════════════════════════════════════════════════════════════════════

    private static void patternC_Future() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> p1step1 = executor.submit(() -> fetchUser());
            Future<String> p2step1 = executor.submit(() -> fetchProduct());

            // Manual step resolution requires inline try-catches if blocking fails
            String user    = p1step1.get();
            Future<String> p1step2 = executor.submit(() -> fetchUserRole(user));

            String product = p2step1.get();
            Future<Double> p2step2 = executor.submit(() -> calculateDiscount(product));

            String role  = p1step2.get();
            Double price = p2step2.get();

            System.out.println("  Result: Role=" + role + ", Price=" + price);
        }
    }

    /**
     * C2 (Dirty) — CompletableFuture with messy inline exception wrapping.
     *
     * Because 'fetchUser' throws a checked InterruptedException, we cannot use
     * method references directly. Every single stage gets polluted with try-catch.
     */
    private static void patternC_CompletableFuture_Dirty() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            CompletableFuture<String> pipeline1 = CompletableFuture.supplyAsync(() -> {
                try {
                    return fetchUser(); // checked exception must be caught!
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new CompletionException(e);
                }
            }, executor).thenApplyAsync(user -> {
                try {
                    return fetchUserRole(user);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new CompletionException(e);
                }
            }, executor);

            CompletableFuture<Double> pipeline2 = CompletableFuture.supplyAsync(() -> {
                try {
                    return fetchProduct();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new CompletionException(e);
                }
            }, executor).thenApplyAsync(product -> {
                try {
                    return calculateDiscount(product);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new CompletionException(e);
                }
            }, executor);

            CompletableFuture<String> combined = pipeline1.thenCombine(pipeline2,
                                                                       (role, price) -> "Role=" + role + ", Price=" + price);

            String result = combined.join();
            System.out.println("  Result: " + result);
        }
    }

    /**
     * C2 (Clean) — CompletableFuture using decoupled utility wrappers.
     *
     * By calling the custom `*Clean()` wrapper methods (located at the bottom of the file),
     * we can restore the expressive method references and functional purity of our pipeline.
     */
    private static void patternC_CompletableFuture_Clean() throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            CompletableFuture<String> pipeline1 =
                CompletableFuture
                    .supplyAsync(Main::fetchUserClean, executor)
                    .thenApplyAsync(Main::fetchUserRoleClean, executor);

            CompletableFuture<Double> pipeline2 =
                CompletableFuture
                    .supplyAsync(Main::fetchProductClean, executor)
                    .thenApplyAsync(Main::calculateDiscountClean, executor);

            CompletableFuture<String> combined =
                pipeline1.thenCombine(pipeline2,
                                      (role, price) -> "Role=" + role + ", Price=" + price);

            String result = combined.join();
            System.out.println("  Result: " + result);
        }
    }

    /**
     * C3 (Native) — Structured Concurrency with zero boilerplate.
     *
     * StructuredTaskScope's fork() receives a Callable. This means we can call
     * methods declaring checked exceptions ('throws InterruptedException') directly
     * inside the scope without wrappers OR inline try-catch blocks.
     */
    private static void patternC_StructuredConcurrency() {
        System.out.println("Start: " + Thread.currentThread().getName());

        try (var scope = StructuredTaskScope.<Object>open()) {

            // Fork 1: Calls throwing methods directly
            Subtask<String> pipeline1 = scope.fork(() -> {
                String user = fetchUser(); // Direct call! No try-catch needed.
                return fetchUserRole(user);
            });

            // Fork 2: Calls throwing methods directly
            Subtask<Double> pipeline2 = scope.fork(() -> {
                String product = fetchProduct();
                return calculateDiscount(product);
            });

            scope.join(); // Block owner thread until both pipelines finish

            String role  = (String) pipeline1.get();
            Double price = (Double) pipeline2.get();

            System.out.println("  Result: Role=" + role + ", Price=" + price);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (FailedException e) {
            System.err.println("Pipeline failed: " + e.getCause().getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  RAW THROWING METHODS (checked exception-based business logic)
    // ══════════════════════════════════════════════════════════════════════════

    private static String fetchValue() throws InterruptedException {
        Thread.sleep(100);
        return "New Value";
    }

    private static String transform(String value) {
        return value.toLowerCase();
    }

    private static void save(String value) throws InterruptedException {
        Thread.sleep(50);
    }

    private static String fetchUser() throws InterruptedException {
        Thread.sleep(50);
        return "Ivan";
    }

    private static String fetchUserRole(String user) throws InterruptedException {
        Thread.sleep(50);
        return user + "_ADMIN";
    }

    private static String fetchProduct() throws InterruptedException {
        Thread.sleep(40);
        return "Laptop";
    }

    private static double calculateDiscount(String product) throws InterruptedException {
        Thread.sleep(60);
        return 999.99;
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  CLEAN HELPER UTILITIES FOR COMPLETABLEFUTURE (WRAP CHECKED EXCEPTIONS)
    // ══════════════════════════════════════════════════════════════════════════

    private static String fetchUserClean() {
        try {
            return fetchUser();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CompletionException(e);
        }
    }

    private static String fetchUserRoleClean(String user) {
        try {
            return fetchUserRole(user);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CompletionException(e);
        }
    }

    private static String fetchProductClean() {
        try {
            return fetchProduct();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CompletionException(e);
        }
    }

    private static double calculateDiscountClean(String product) {
        try {
            return calculateDiscount(product);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CompletionException(e);
        }
    }
}