# Pattern Matching for `switch`

> **Project:** Amber
> **Java version:** 21 (final)
> **Status:** Released

Pattern matching for `switch` extends the `switch` statement and expression to work with any type, supports pattern-based cases, and enables compiler-verified exhaustiveness when combined with sealed classes.

---

## History and Evolution

### The Limitations of Traditional switch

Before pattern matching, `switch` was heavily restricted:

```java
// Pre-Java 17: switch only worked on a few types
switch (value) {
    case 1: ...        // int/Integer
    case "hello": ...  // String (Java 7+)
    case RED: ...      // enum
}
```

You could not:
- Switch on arbitrary objects
- Test types in cases
- Deconstruct records
- Use guards (conditions within cases)

### The Evolution Timeline

| Milestone | Release | Feature |
|---|---|---|
| Switch expressions | Java 14 | `switch` returns a value, arrow syntax |
| Pattern matching (preview 1) | Java 17 | Type patterns in switch |
| Pattern matching (preview 2) | Java 18 | Guarded patterns (`when`) |
| Pattern matching (preview 3) | Java 19 | Record patterns in switch |
| Pattern matching (preview 4) | Java 20 | Refinements |
| Finalized | Java 21 | Permanent language feature |

## Implementation Deep Dive

### Switch with Type Patterns

```java
String formatted(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("int %d", i);
        case Long l    -> String.format("long %d", l);
        case Double d  -> String.format("double %f", d);
        case String s  -> String.format("String %s", s);
        default        -> obj.toString();
    };
}
```

### Record Patterns in Switch

```java
record Point(int x, int y) {}

String describe(Object obj) {
    return switch (obj) {
        case Point(int x, int y) -> "Point(" + x + ", " + y + ")";
        default -> "Unknown";
    };
}
```

### Exhaustiveness with Sealed Classes

The most powerful combination:

```java
public sealed interface Expr permits Const, Neg, Add, Mul {}
public record Const(int value) implements Expr {}
public record Neg(Expr expr) implements Expr {}
public record Add(Expr left, Expr right) implements Expr {}
public record Mul(Expr left, Expr right) implements Expr {}

// Compiler verifies ALL cases are covered!
int eval(Expr e) {
    return switch (e) {
        case Const(int n)       -> n;
        case Neg(Expr expr)     -> -eval(expr);
        case Add(Expr l, Expr r)-> eval(l) + eval(r);
        case Mul(Expr l, Expr r)-> eval(l) * eval(r);
    };
}
```

If a new `Expr` type is added, every `switch` on `Expr` becomes a compile error until updated. This is **exhaustiveness checking** — the compiler guarantees you haven't missed a case.

### Guarded Patterns (`when`)

```java
String classify(Object obj) {
    return switch (obj) {
        case Point(int x, int y) when x == 0 && y == 0 -> "origin";
        case Point(int x, int y) when x == y -> "diagonal";
        case Point(int x, int y) -> "point";
        default -> "unknown";
    };
}
```

The `when` clause adds a boolean condition to a pattern case. The pattern matches only if both the type test AND the guard condition are true.

### Dominance and Null

The compiler enforces **dominance ordering**: more specific patterns must come before more general ones.

```java
// COMPILE ERROR: String is more specific than Object
switch (obj) {
    case Object o -> "any object";
    case String s -> "string";  // ERROR: unreachable
}

// CORRECT: specific first
switch (obj) {
    case String s -> "string";
    case Object o -> "any object";
}
```

Null handling:

```java
switch (s) {
    case null -> "null";           // explicit null handling (Java 21+)
    case "foo" -> "foo";
    default -> "other";
}
```

## See Also

- [Pattern Matching — main feature page](../../07-pattern-matching.md)
- [Pattern Matching for instanceof](03-pattern-matching-instanceof.md)
- [Sealed Classes](02-sealed-classes.md) — exhaustiveness checking
- [Records](01-records.md) — deconstruction targets
