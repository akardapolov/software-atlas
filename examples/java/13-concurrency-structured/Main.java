package org.example;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * Java Structured Concurrency Examples
 *
 * Structured Concurrency (final in Java 24) provides a high-level abstraction
 * for managing concurrent tasks. Related tasks are treated as a single unit
 * of work, improving error handling, cancellation, and observability.
 *
 * Key benefits:
 * - Tasks forked in a scope are joined before scope exit
 * - If any subtask fails, remaining tasks can be automatically cancelled
 * - The scope ensures all tasks complete (successfully or not) before proceeding
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Example 1: Basic Structured Concurrency ===");
        example1BasicConcurrency();

        System.out.println("\n=== Example 2: Shutdown on First Failure ===");
        example2ShutdownOnFailure();

        System.out.println("\n=== Example 3: Shutdown on First Success ===");
        example3ShutdownOnSuccess();

        System.out.println("\n=== Example 4: Handling Timeouts ===");
        example4HandlingTimeouts();

        System.out.println("\n=== Example 5: Combining Results ===");
        example5CombiningResults();
    }

    /**
     * Example 1: Basic Structured Concurrency
     *
     * Basic pattern using StructuredTaskScope:
     * 1. Fork multiple tasks in parallel
     * 2. Join to wait for all tasks to complete
     * 3. Process results using resultNow()
     *
     * The scope ensures all tasks complete before exiting the try-with-resources block.
     */
    private static void example1BasicConcurrency() {
        try (var scope = new StructuredTaskScope<String>()) {
            Future<String> userFuture = scope.fork(() -> fetchUser());
            Future<String> orderFuture = scope.fork(() -> fetchOrders());

            scope.join(); // Wait for both tasks to complete

            String user = userFuture.resultNow();
            String orders = orderFuture.resultNow();

            System.out.println("User: " + user + ", Orders: " + orders);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Example 2: Shutdown on First Failure
     *
     * Using ShutdownOnFailure policy:
     * - Cancels remaining tasks if any subtask fails
     * - throwIfFailed() propagates the first exception encountered
     * - Useful when partial success is unacceptable
     *
     * This is the "all-or-nothing" pattern.
     */
    private static void example2ShutdownOnFailure() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> future1 = scope.fork(() -> fetchFromServiceA());
            Future<String> future2 = scope.fork(() -> fetchFromServiceB());

            scope.join();
            scope.throwIfFailed(); // Throws if any subtask failed

            // Process results only if all succeeded
            System.out.println(future1.resultNow() + " - " + future2.resultNow());
        } catch (Exception e) {
            System.out.println("Operation failed: " + e.getMessage());
        }
    }

    /**
     * Example 3: Shutdown on First Success
     *
     * Using ShutdownOnSuccess policy:
     * - Cancels remaining tasks after first successful result
     * - result() returns the first successful value
     * - Useful for fallback/redundancy patterns
     *
     * Common use cases:
     * - Querying multiple data sources and using the first to respond
     * - Trying primary, secondary, and fallback servers
     * - Race conditions where only one result is needed
     */
    private static void example3ShutdownOnSuccess() {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(() -> queryPrimaryDataSource());
            scope.fork(() -> querySecondaryDataSource());
            scope.fork(() -> queryFallbackDataSource());

            scope.join();

            String result = scope.result(); // Gets the first successful result
            System.out.println("Obtained result: " + result);
        } catch (Exception e) {
            System.out.println("All attempts failed");
        }
    }

    /**
     * Example 4: Handling Timeouts
     *
     * Using joinUntil() for timeout-based execution:
     * - Wait for tasks with a deadline
     * - Check future state after timeout
     * - Manually shutdown if tasks haven't completed
     *
     * Useful for operations with SLA requirements.
     */
    private static void example4HandlingTimeouts() {
        try (var scope = new StructuredTaskScope<String>()) {
            Future<String> future = scope.fork(() -> longRunningOperation());

            scope.joinUntil(Instant.now().plusSeconds(3));

            if (future.state() == Future.State.SUCCESS) {
                System.out.println("Result: " + future.resultNow());
            } else {
                System.out.println("Operation timed out");
                scope.shutdown(); // Interrupt the thread
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Example 5: Combining Results with Exception Handling
     *
     * Combining results from multiple tasks with proper error handling:
     * - Use ShutdownOnFailure to fail fast
     * - Parse results after successful fetch
     * - Handle parsing exceptions separately from fetch exceptions
     *
     * This pattern shows how to structure a complex workflow with structured concurrency.
     */
    private static void example5CombiningResults() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> userFuture = scope.fork(() -> getUserProfile());
            Future<String> historyFuture = scope.fork(() -> getPurchaseHistory());

            scope.join();
            scope.throwIfFailed();

            try {
                UserProfile profile = parseProfile(userFuture.resultNow());
                PurchaseHistory history = parseHistory(historyFuture.resultNow());
                System.out.println("Combined result: " + new UserData(profile, history));
            } catch (ParsingException e) {
                throw new RuntimeException("Failed to parse data", e);
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch user data: " + e.getMessage());
        }
    }

    // ===== Mock Service Methods =====

    private static String fetchUser() throws InterruptedException {
        Thread.sleep(500);
        return "John Doe";
    }

    private static String fetchOrders() throws InterruptedException {
        Thread.sleep(800);
        return "[Order1, Order2]";
    }

    private static String fetchFromServiceA() throws InterruptedException {
        Thread.sleep(300);
        return "ServiceA Data";
    }

    private static String fetchFromServiceB() throws InterruptedException {
        Thread.sleep(400);
        return "ServiceB Data";
    }

    private static String queryPrimaryDataSource() throws InterruptedException {
        Thread.sleep(1000);
        return "Primary Data";
    }

    private static String querySecondaryDataSource() throws InterruptedException {
        Thread.sleep(500);
        return "Secondary Data";
    }

    private static String queryFallbackDataSource() throws InterruptedException {
        Thread.sleep(200);
        return "Fallback Data";
    }

    private static String longRunningOperation() throws InterruptedException {
        Thread.sleep(5000); // Will timeout in example 4
        return "Long operation completed";
    }

    private static String getUserProfile() throws InterruptedException {
        Thread.sleep(300);
        return "{\"name\":\"Alice\",\"age\":30}";
    }

    private static String getPurchaseHistory() throws InterruptedException {
        Thread.sleep(400);
        return "[{\"id\":1,\"item\":\"Book\"}]";
    }

    // ===== Data Classes =====

    private static class UserProfile {
        String name;
        int age;

        UserProfile(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserProfile{name='" + name + "', age=" + age + "}";
        }
    }

    private static class PurchaseHistory {
        String items;

        PurchaseHistory(String items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "PurchaseHistory{items=" + items + "}";
        }
    }

    private static class UserData {
        UserProfile profile;
        PurchaseHistory history;

        UserData(UserProfile profile, PurchaseHistory history) {
            this.profile = profile;
            this.history = history;
        }

        @Override
        public String toString() {
            return "UserData{" + profile + ", " + history + "}";
        }
    }

    // ===== Parsing Methods =====

    private static UserProfile parseProfile(String json) throws ParsingException {
        // Simplified parsing - in real code, use a JSON library
        return new UserProfile("Alice", 30);
    }

    private static PurchaseHistory parseHistory(String json) throws ParsingException {
        // Simplified parsing
        return new PurchaseHistory("[Book]");
    }

    private static class ParsingException extends Exception {
        ParsingException(String message) {
            super(message);
        }
    }
}
