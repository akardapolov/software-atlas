// FP Features in Java
// ================
// lambdas, streams, Optional, method references

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        // ── Lambda expressions ───────────────────────────

        System.out.println("--- Lambda expressions ---");

        Predicate<Integer> isEven = x -> x % 2 == 0;
        Function<Integer, Integer> square = x -> x * x;
        Consumer<Integer> print = x -> System.out.println(x);

        System.out.println("4 is even? " + isEven.test(4));
        System.out.println("Square of 5: " + square.apply(5));
        print.accept(42);


        // ── Method references ─────────────────────────────

        System.out.println("\n--- Method references ---");

        Function<String, String> upper = String::toUpperCase;
        Consumer<String> printer = System.out::println;

        printer.accept(upper.apply("hello"));


        // ── Streams ───────────────────────────────────

        System.out.println("\n--- Streams ---");

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // map
        List<Integer> squares = numbers.stream()
                .map(x -> x * x)
                .toList();
        System.out.println("Squares: " + squares);

        // filter
        List<Integer> evens = numbers.stream()
                .filter(x -> x % 2 == 0)
                .toList();
        System.out.println("Evens: " + evens);

        // reduce
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        // flatMap
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("Flattened: " + flat);

        // collect: grouping
        Map<Integer, List<Integer>> byMod = numbers.stream()
                .collect(Collectors.groupingBy(x -> x % 3));
        System.out.println("Grouped by mod 3: " + byMod);


        // ── Function composition ─────────────────────

        System.out.println("\n--- Function composition ---");

        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> triple = x -> x * 3;

        // andThen: apply addOne, then triple
        Function<Integer, Integer> addThenTriple = addOne.andThen(triple);
        System.out.println("addOne.andThen(triple)(5): " + addThenTriple.apply(5));

        // compose: apply triple, then addOne
        Function<Integer, Integer> tripleComposeAddOne = triple.compose(addOne);
        System.out.println("triple.compose(addOne)(5): " + tripleComposeAddOne.apply(5));


        // ── Optional (null safety) ─────────────────────

        System.out.println("\n--- Optional ---");

        Optional<String> present = Optional.of("hello");
        Optional<String> absent = Optional.empty();

        System.out.println("Present.map(upper): " + present.map(String::toUpperCase));
        System.out.println("Absent.map(upper): " + absent.map(String::toUpperCase));
        System.out.println("Absent.orElse: " + absent.orElse("default"));

        // Chaining Optionals
        String result = Optional.of("42")
                .map(Integer::parseInt)
                .filter(n -> n > 0)
                .map(n -> "Positive: " + n)
                .orElse("Not positive");
        System.out.println("Optional chain: " + result);


        // ── Closures ───────────────────────────────────

        System.out.println("\n--- Closures ---");

        int multiplier = 3;
        Function<Integer, Integer> timesThree = x -> x * multiplier;
        // multiplier is effectively final
        System.out.println("5 * 3 = " + timesThree.apply(5));


        // ── Collectors ───────────────────────────────

        System.out.println("\n--- Collectors ---");

        List<String> words = List.of("apple", "banana", "cherry", "date");

        // Partitioning
        Map<Boolean, List<String>> byLength = words.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 5));
        System.out.println("Long (>5): " + byLength.get(true));
        System.out.println("Short (<=5): " + byLength.get(false));

        // To Map
        Map<Integer, String> byLengthMap = words.stream()
                .collect(Collectors.toMap(String::length, s -> s));
        System.out.println("By length map: " + byLengthMap);


        // ── Summary ─────────────────────────────────────

        System.out.println("\n--- Summary ---");
        System.out.println("Java FP features:");
        System.out.println("  - Lambdas: () -> {}");
        System.out.println("  - Functional interfaces: Function, Predicate, Consumer");
        System.out.println("  - Method references: Class::method");
        System.out.println("  - Streams: map, filter, reduce, flatMap");
        System.out.println("  - Optional: null-safe container");
        System.out.println("  - Composition: andThen, compose");
        System.out.println("  - Collectors: groupingBy, partitioningBy");
        System.out.println("  - Closures: capture effectively final variables");
        System.out.println("  - Records (Java 16+): immutable data classes");
        System.out.println("  - Pattern matching (Java 21+): switch expressions");
    }
}
