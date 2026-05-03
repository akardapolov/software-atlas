package org.example;

import java.util.*;
import java.util.stream.*;

/**
 * Advanced Java Stream API Examples
 *
 * This class demonstrates advanced Stream operations including:
 * - Reduce operations (sum, max, concatenation)
 * - FlatMap to primitives (IntStream, DoubleStream)
 * - MapMulti (efficient one-to-many transformation)
 * - TakeWhile/DropWhile (conditional termination)
 * - ForEachOrdered (parallel stream ordering)
 * - Sorted (various sorting strategies)
 * - Merge operations (toMap with merge function, groupingBy)
 */
public class Main {

    // ===== Record Definitions =====
    record Person(String name, int age) {}
    record Order(String id, List<String> items) {}
    record Product(String name, String category, double price) {}
    record Transaction(String id, String type, double amount) {}
    record Sale(String product, int quantity, double price) {}
    record Item(String name, double price) {}
    record OrderWithPrices(String id, List<Item> items) {}

    public static void main(String[] args) {
        System.out.println("\n===== Reduce Examples =====");
        reduceExamples();

        System.out.println("\n===== FlatMap To Primitive Examples =====");
        flatMapToPrimitiveExamples();

        System.out.println("\n===== MapMulti Examples =====");
        mapMultiExamples();

        System.out.println("\n===== TakeWhile/DropWhile Examples =====");
        whileExamples();

        System.out.println("\n===== ForEachOrdered Examples =====");
        forEachOrderedExamples();

        System.out.println("\n===== Sorted Examples =====");
        sortedExamples();

        System.out.println("\n===== Merge Function Examples =====");
        mergeExamples();

        System.out.println("\n===== Collect Examples =====");
        collectExamples();

        System.out.println("\n===== Count Examples =====");
        countExamples();

        System.out.println("\n===== Sum Examples =====");
        sumExamples();
    }

    // ===== Reduce Examples =====
    /**
     * Reduce combines stream elements into a single result.
     * Demonstrates sum, max, and concatenation.
     */
    private static void reduceExamples() {
        List<Integer> numbers = createNumbers();

        // Sum reduction: combine all numbers into their sum
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        // Max reduction: find the maximum value
        int max = numbers.stream()
                .reduce(Integer::max)
                .orElse(0);
        System.out.println("Max: " + max);

        // Concatenation reduction: combine strings
        String concatenated = createWords().stream()
                .reduce("", (s1, s2) -> s1 + s2);
        System.out.println("Concatenated: " + concatenated);
    }

    // ===== FlatMap To Primitive Examples =====
    /**
     * FlatMap to primitive streams (IntStream, DoubleStream) for efficiency.
     * Avoids boxing/unboxing overhead for numeric operations.
     */
    private static void flatMapToPrimitiveExamples() {
        // FlatMap to IntStream - get all word lengths
        List<Integer> allLengths = createNestedWords().stream()
                .flatMapToInt(list -> list.stream().mapToInt(String::length))
                .boxed()
                .toList();
        System.out.println("All lengths: " + allLengths);

        // FlatMap to DoubleStream - get all prices from orders
        List<Double> allPrices = createOrdersWithPrices().stream()
                .flatMapToDouble(order -> order.items().stream().mapToDouble(Item::price))
                .boxed()
                .toList();
        System.out.println("All prices: " + allPrices);
    }

    // ===== MapMulti Examples =====
    /**
     * MapMulti is a more efficient alternative to flatMap for one-to-many transformations.
     * It avoids creating intermediate collections by pushing elements directly to the downstream.
     */
    private static void mapMultiExamples() {
        // Expand each number to [n, n*2]
        List<Integer> expanded = createNumbers().stream()
                .<Integer>mapMulti((num, consumer) -> {
                    consumer.accept(num);
                    consumer.accept(num * 2);
                })
                .toList();
        System.out.println("Expanded numbers: " + expanded);

        // Expand to doubles [n, n*0.5]
        List<Double> expandedDoubles = createNumbers().stream()
                .mapMultiToDouble((num, consumer) -> {
                    consumer.accept(num);
                    consumer.accept(num * 0.5);
                })
                .boxed()
                .toList();
        System.out.println("Expanded doubles: " + expandedDoubles);
    }

    // ===== TakeWhile/DropWhile Examples =====
    /**
     * TakeWhile: stops taking when predicate becomes false
     * DropWhile: drops elements while predicate is true, then takes rest
     */
    private static void whileExamples() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1);

        // Take while < 4: stops at first element >= 4
        List<Integer> taken = numbers.stream()
                .takeWhile(n -> n < 4)
                .toList();
        System.out.println("Take while < 4: " + taken);

        // Drop while < 4: drops until first element >= 4
        List<Integer> dropped = numbers.stream()
                .dropWhile(n -> n < 4)
                .toList();
        System.out.println("Drop while < 4: " + dropped);
    }

    // ===== ForEachOrdered Examples =====
    /**
     * ForEachOrdered: maintains encounter order even in parallel streams.
     * Use when order matters and you're processing in parallel.
     */
    private static void forEachOrderedExamples() {
        List<Integer> numbers = createNumbers();

        // Parallel forEach: order is NOT guaranteed
        System.out.print("Parallel forEach: ");
        numbers.parallelStream()
                .forEach(n -> System.out.print(n + " "));

        // Parallel forEachOrdered: order IS guaranteed
        System.out.print("\nParallel forEachOrdered: ");
        numbers.parallelStream()
                .forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();
    }

    // ===== Sorted Examples =====
    /**
     * Sorted: sorts stream elements using natural order or custom comparators.
     * Note: sorting is a stateful operation that requires buffering.
     */
    private static void sortedExamples() {
        // Natural order (alphabetical for strings)
        List<String> naturalOrder = createWords().stream()
                .sorted()
                .toList();
        System.out.println("Natural order: " + naturalOrder);

        // Reverse order
        List<String> reverseOrder = createWords().stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("Reverse order: " + reverseOrder);

        // Sort by length
        List<String> byLength = createWords().stream()
                .sorted(Comparator.comparing(String::length))
                .toList();
        System.out.println("By length: " + byLength);

        // Multi-level sort: by length then alphabetically
        List<String> multiLevel = createMixedWords().stream()
                .sorted(Comparator.comparing(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println("By length then alpha: " + multiLevel);
    }

    // ===== Merge Function Examples =====
    /**
     * Merge functions resolve key collisions when collecting to maps.
     * Demonstrates toMap, groupingBy, and Map.merge.
     */
    private static void mergeExamples() {
        List<Product> products = createProducts();

        // toMap with merge function - keep most expensive per category
        Map<String, Double> mostExpensiveByCategory = products.stream()
                .collect(Collectors.toMap(
                        Product::category,      // key
                        Product::price,         // value
                        Double::max));          // merge: keep higher price
        System.out.println("Most expensive per category: " + mostExpensiveByCategory);

        // groupingBy with reducing - sum prices per category
        Map<String, Double> totalPerCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.reducing(
                                0.0,
                                Product::price,
                                Double::sum)));
        System.out.println("Total per category:");
        totalPerCategory.forEach((cat, total) ->
                System.out.println("  " + cat + " = $" + total));

        // Map.merge - build frequency map incrementally
        Map<String, Integer> freq = new HashMap<>();
        createWords().forEach(word ->
                freq.merge(word, 1, Integer::sum));
        System.out.println("Word frequencies: " + freq);
    }

    // ===== Collect Examples =====
    private static void collectExamples() {
        List<Product> products = createProducts();

        // Grouping by category
        Map<String, List<Product>> byCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category));
        System.out.println("Grouped by category:");
        byCategory.forEach((cat, prods) ->
                System.out.println("  " + cat + ": " + prods.stream()
                        .map(Product::name).toList()));

        // Partitioning by price threshold
        Map<Boolean, List<Product>> expensive = products.stream()
                .collect(Collectors.partitioningBy(p -> p.price() > 100.00));
        System.out.println("Expensive: " + expensive.get(true).stream()
                .map(Product::name).toList());
        System.out.println("Inexpensive: " + expensive.get(false).stream()
                .map(Product::name).toList());

        // Joining strings
        String joined = products.stream()
                .map(Product::name)
                .collect(Collectors.joining("; "));
        System.out.println("All products: " + joined);
    }

    // ===== Count Examples =====
    private static void countExamples() {
        // Conditional counting
        long creditCount = createTransactions().stream()
                .filter(t -> "CREDIT".equals(t.type()))
                .count();
        System.out.println("Credit transactions: " + creditCount);

        // Distinct type count
        long distinctTypes = createTransactions().stream()
                .map(Transaction::type)
                .distinct()
                .count();
        System.out.println("Distinct types: " + distinctTypes);
    }

    // ===== Sum Examples =====
    private static void sumExamples() {
        // Sum quantities
        int totalQuantity = createSales().stream()
                .mapToInt(Sale::quantity)
                .sum();
        System.out.println("Total quantity: " + totalQuantity);

        // Sum calculated values (revenue)
        double totalRevenue = createSales().stream()
                .mapToDouble(s -> s.quantity() * s.price())
                .sum();
        System.out.println("Total revenue: $" + totalRevenue);

        // Conditional summing (expensive items only)
        double expensiveRevenue = createSales().stream()
                .filter(s -> s.price() > 600.00)
                .mapToDouble(s -> s.quantity() * s.price())
                .sum();
        System.out.println("Expensive revenue: $" + expensiveRevenue);
    }

    // ===== Test Data Helpers =====
    private static List<Integer> createNumbers() {
        return Arrays.asList(5, 3, 8, 1, 6);
    }

    private static List<String> createWords() {
        return Arrays.asList("java", "stream", "api", "functional");
    }

    private static List<String> createMixedWords() {
        return Arrays.asList("banana", "apple", "fig", "date", "cherry");
    }

    private static List<List<String>> createNestedWords() {
        return Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cherry"),
                Arrays.asList("date", "elderberry", "fig")
        );
    }

    private static List<OrderWithPrices> createOrdersWithPrices() {
        return Arrays.asList(
                new OrderWithPrices("A1", Arrays.asList(
                        new Item("Shirt", 25.99),
                        new Item("Pants", 45.99)
                )),
                new OrderWithPrices("B2", Arrays.asList(
                        new Item("Shoes", 89.99)
                ))
        );
    }

    private static List<Product> createProducts() {
        return Arrays.asList(
                new Product("Laptop", "Electronics", 1200.00),
                new Product("Phone", "Electronics", 800.00),
                new Product("Shirt", "Clothing", 35.00),
                new Product("Pants", "Clothing", 45.00),
                new Product("Book", "Books", 15.00)
        );
    }

    private static List<Transaction> createTransactions() {
        return Arrays.asList(
                new Transaction("T1", "CREDIT", 100.00),
                new Transaction("T2", "DEBIT", 50.00),
                new Transaction("T3", "CREDIT", 200.00),
                new Transaction("T4", "DEBIT", 75.00),
                new Transaction("T5", "CREDIT", 150.00)
        );
    }

    private static List<Sale> createSales() {
        return Arrays.asList(
                new Sale("Laptop", 2, 1200.00),
                new Sale("Phone", 5, 800.00),
                new Sale("Tablet", 3, 500.00)
        );
    }
}
