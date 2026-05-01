# Advanced Java Stream API Examples

This package demonstrates advanced Java Stream operations introduced in Java 8 and enhanced in later versions.

## Topics Covered

### Reduce Operations
- **Sum reduction**: Combining numeric values using `Integer::sum`
- **Max reduction**: Finding maximum value with `Integer::max`
- **Concatenation**: Merging strings with custom reducer

### FlatMap to Primitives
- **flatMapToInt**: Converting to IntStream for efficient numeric operations
- **flatMapToDouble**: Converting to DoubleStream for floating-point data

### MapMulti (Java 16+)
- Efficient one-to-many transformation that avoids creating intermediate collections
- Alternative to `flatMap` when you need to transform elements based on each input

### TakeWhile / DropWhile (Java 9+)
- **takeWhile**: Stops taking elements when predicate becomes false
- **dropWhile**: Drops elements while predicate is true, then takes rest

### ForEachOrdered
- Maintains encounter order even in parallel streams
- Critical when order matters for correctness

### Sorted
- Natural ordering
- Reverse ordering
- Custom comparator sorting
- Multi-level sorting (e.g., by length then alphabetically)

### Merge Operations
- **toMap with merge function**: Handling key collisions when creating maps
- **groupingBy with reducing**: Aggregating values within groups
- **Map.merge**: Incrementally building frequency maps

### Collect Operations
- **groupingBy**: Grouping elements by a classifier function
- **partitioningBy**: Binary grouping by a predicate
- **joining**: Concatenating strings with delimiters

## Running the Examples

```bash
javac Main.java
java Main
```

## Key Concepts

- **Lazy evaluation**: Intermediate operations only execute when a terminal operation is called
- **Stateful vs stateless**: Operations like `sorted()` and `distinct()` need to buffer elements
- **Short-circuiting**: Operations like `findFirst()`, `anyMatch()`, and `takeWhile()` can terminate early
- **Parallel streams**: Use `parallelStream()` for concurrent processing when order isn't critical
