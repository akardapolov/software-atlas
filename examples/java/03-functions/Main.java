import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Functions in Java
 * =================
 * Java added lambdas and functional interfaces in Java 8 (2014).
 * Before that, anonymous inner classes were the only way to pass
 * behaviour. Java functions are NOT first-class in the traditional
 * sense — lambdas are syntactic sugar for functional interface
 * implementations.
 */
public class Main {
    public static void main(String[] args) {

        // ── Basic methods ────────────────────────────

        System.out.println(greet("Atlas"));

        // ── Lambda expressions (Java 8+) ─────────────

        System.out.println("\n--- Lambda expressions ---");

        // Functional interface: an interface with one abstract method
        // Java provides many: Function, Predicate, Consumer, Supplier, etc.

        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> doubleIt = x -> x * 2;

        System.out.println("square(5) = " + square.apply(5));
        System.out.println("doubleIt(5) = " + doubleIt.apply(5));

        // Predicate: T -> boolean
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println("isEven(4) = " + isEven.test(4));
        System.out.println("isEven(5) = " + isEven.test(5));

        // BiFunction: (T, U) -> R
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("add(3, 4) = " + add.apply(3, 4));

        // ── Method references ────────────────────────

        System.out.println("\n--- Method references ---");

        // Instead of lambda: x -> x.toUpperCase()
        Function<String, String> upper = String::toUpperCase;
        System.out.println("upper(\"hello\") = " + upper.apply("hello"));

        // Static method reference
        Function<String, Integer> parse = Integer::parseInt;
        System.out.println("parse(\"42\") = " + parse.apply("42"));

        // ── Streams (higher-order functions) ─────────

        System.out.println("\n--- Streams ---");

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // map
        List<Integer> squares = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println("squares = " + squares);

        // filter
        List<Integer> evens = numbers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("evens = " + evens);

        // reduce (fold)
        int total = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("sum = " + total);

        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("product = " + product);

        // Chaining
        int sumOfSquaresOfEvens = numbers.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .reduce(0, Integer::sum);
        System.out.println("sum of squares of evens = " + sumOfSquaresOfEvens);

        // Collectors
        String joined = List.of("hello", "functional", "java").stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        System.out.println("joined = " + joined);

        // any/all
        boolean hasNegative = numbers.stream().anyMatch(x -> x < 0);
        boolean allPositive = numbers.stream().allMatch(x -> x > 0);
        System.out.println("any negative? " + hasNegative);
        System.out.println("all positive? " + allPositive);

        // ── Function composition ─────────────────────

        System.out.println("\n--- Function composition ---");

        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> triple = x -> x * 3;

        // andThen: first this, then that
        Function<Integer, Integer> addOneThenTriple = addOne.andThen(triple);
        System.out.println("addOne.andThen(triple)(5) = " + addOneThenTriple.apply(5));

        // compose: first that, then this
        Function<Integer, Integer> tripleComposeAddOne = triple.compose(addOne);
        System.out.println("triple.compose(addOne)(5) = " + tripleComposeAddOne.apply(5));

        // ── Closures (capturing variables) ───────────

        System.out.println("\n--- Closures ---");

        int factor = 3;
        // Lambdas can capture effectively final variables
        Function<Integer, Integer> timesThree = x -> x * factor;
        System.out.println("timesThree(5) = " + timesThree.apply(5));

        // factor = 4;  // ERROR: variable used in lambda must be effectively final

        // Returning functions (factory pattern)
        Function<Integer, Integer> timesTwo = makeMultiplier(2);
        Function<Integer, Integer> timesFive = makeMultiplier(5);
        System.out.println("timesTwo(7) = " + timesTwo.apply(7));
        System.out.println("timesFive(7) = " + timesFive.apply(7));

        // ── Optional (functional null handling) ──────

        System.out.println("\n--- Optional ---");

        Optional<String> present = Optional.of("hello");
        Optional<String> absent = Optional.empty();

        System.out.println("present.map(upper) = " + present.map(String::toUpperCase));
        System.out.println("absent.map(upper) = " + absent.map(String::toUpperCase));
        System.out.println("absent.orElse(\"default\") = " + absent.orElse("default"));

        // Chaining Optionals
        String result = Optional.of("42")
                .map(Integer::parseInt)
                .filter(n -> n > 0)
                .map(n -> "Positive: " + n)
                .orElse("Not positive");
        System.out.println("Optional chain = " + result);

        // ── Custom higher-order function ─────────────

        System.out.println("\n--- Custom HOF ---");

        System.out.println("applyTwice(x+1, 5) = " + applyTwice(x -> x + 1, 5));
        System.out.println("applyTwice(x*2, 3) = " + applyTwice(x -> x * 2, 3));

        // ── Sorting with comparators ─────────────────

        System.out.println("\n--- Sorting ---");

        List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry", "date"));

        words.sort(Comparator.comparingInt(String::length));
        System.out.println("by length = " + words);

        words.sort(Comparator.naturalOrder());
        System.out.println("alphabetical = " + words);

        words.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println("by length desc = " + words);

        // ── Summary ──────────────────────────────────

        System.out.println("\n--- Summary ---");
        System.out.println("Java functions:");
        System.out.println("  - Lambdas since Java 8 (2014)");
        System.out.println("  - Functional interfaces: Function, Predicate, Consumer");
        System.out.println("  - Method references: String::toUpperCase");
        System.out.println("  - Streams: map, filter, reduce, collect");
        System.out.println("  - andThen/compose for function composition");
        System.out.println("  - Closures capture effectively final variables only");
        System.out.println("  - Optional for functional null handling");
    }

    static String greet(String name) {
        return "Hello, " + name + "!";
    }

    static Function<Integer, Integer> makeMultiplier(int factor) {
        return x -> x * factor;
    }

    static <T> T applyTwice(Function<T, T> f, T x) {
        return f.apply(f.apply(x));
    }
}
