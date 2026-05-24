# Unnamed Variables and Patterns

> **Project:** Amber
> **Java version:** 22 (final)
> **Status:** Released

Unnamed variables and patterns use the underscore character `_` to indicate intentionally unused bindings, eliminating the need for dummy variable names like `unused` or `ignored`.

---

## History and Evolution

### The Unused Variable Problem

Java has always required a variable name for every binding, even when the value is never used:

```java
// Before Java 22: dummy variable names
switch (shape) {
    case Rectangle(double w, double h) -> System.out.println("Rectangle");
    case Circle(double r) -> System.out.println("Circle");
}

// In a catch block
try {
    process();
} catch (IOException ex) {  // 'ex' is unused
    System.out.println("IO error");
}

// In a lambda
list.forEach((item, index) -> System.out.println(item)); // 'index' unused
```

IDEs flag unused variables with warnings, but developers still need to name them.

### Single-Underscore in Java History

The underscore `_` has an interesting history in Java:

- **Java 7**: `_` was a valid identifier (e.g., `int _ = 5;`)
- **Java 8**: `_` became a reserved keyword (compile error as identifier) — preparing for lambdas
- **Java 9**: `_` became a keyword — preparing for future use
- **Java 22**: `_` finally gets meaning as an unnamed variable/pattern

## Implementation Deep Dive

### Unnamed Pattern Variables

```java
// Ignore the second component of a record pattern
if (obj instanceof Point(int x, _)) {
    System.out.println("x = " + x);
}

// Ignore components in switch
switch (point) {
    case Point(int x, _) -> System.out.println("x = " + x);
    case Point(_, int y) -> System.out.println("y = " + y);
}
```

The `_` binding:
- Can appear multiple times in the same pattern (unlike named variables)
- Cannot be referenced later (it's not a real variable)
- Signals intent: "I know this component exists but don't need it"

### Unnamed Catch Parameters

```java
try {
    Files.readString(path);
} catch (IOException _) {
    // I know an IO exception occurred, but don't need the details
    System.out.println("Failed to read file");
}
```

This is particularly useful when:
- The exception type is sufficient information
- The error is handled uniformly regardless of details
- You're overriding a method that declares a checked exception you don't use

### Unnamed Lambda Parameters

```java
// Ignore the exception in a Consumer
list.forEach(_ -> System.out.println("Processing..."));

// Ignore the second parameter in a BiConsumer
map.forEach((key, _) -> System.out.println(key));
```

### Unnamed Variables in Assignment

```java
// Ignore the second return value
var result = compute();
int value = result.value();
_ = result.metadata();  // explicitly unused
```

## Benefits

1. **Readability**: `_` clearly signals "intentionally unused"
2. **No warnings**: IDEs and compilers won't flag `_` as unused
3. **Multiple uses**: `_` can appear multiple times; named variables cannot
4. **Self-documenting**: Future readers know the value was considered and discarded

## See Also

- [Pattern Matching — main feature page](../../07-pattern-matching.md)
- [Pattern Matching for instanceof](03-pattern-matching-instanceof.md)
- [Pattern Matching for switch](04-pattern-matching-switch.md)
- [Records](01-records.md) — common use case for unnamed patterns
