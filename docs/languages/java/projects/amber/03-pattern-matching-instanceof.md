# Pattern Matching for `instanceof`

> **Project:** Amber
> **Java version:** 16 (final)
> **Status:** Released

Pattern matching for `instanceof` eliminates the boilerplate of type testing and casting, combining `instanceof`, cast, and variable declaration into a single operation.

---

## History and Evolution

### The Type Test + Cast Pattern

Before Java 16, checking an object's type and using it required three steps:

```java
// Java 15 and earlier: verbose and error-prone
if (obj instanceof String) {
    String s = (String) obj;  // redundant cast after instanceof
    System.out.println(s.toUpperCase());
}
```

Problems:
- **Redundancy** — The cast repeats the type already checked
- **Scope leakage** — The variable `s` is available after the `if` block
- **Error-prone** — It's easy to cast to the wrong type (e.g., `(String) obj` vs `(CharSequence) obj`)

### The Amber Solution

Pattern matching introduces a **binding variable** directly in the `instanceof` expression:

```java
// Java 16+: clean, safe, scoped
if (obj instanceof String s) {
    System.out.println(s.toUpperCase()); // s is in scope here
}
// s is NOT in scope here
```

The variable `s` is:
- **Declared** and **bound** in the `instanceof` expression
- **Scoped** to the `true` branch of the `if`
- **Smart-cast** — the compiler knows its type, so explicit cast is unnecessary

## Implementation Deep Dive

### The Pattern Variable

```java
// Pattern:  Type variable
//           ──── ────────
//           │    │
if (obj instanceof String s) {
    //         │      │
    //         │      └─ binding variable (in scope in true branch)
    //         └─ type pattern (matches if obj is a String)
}
```

The `String s` part is a **type pattern**: it matches if the value is an instance of `String`, and binds the cast result to `s`.

### Scope and Flow-Sensitive Typing

The scope of the pattern variable follows definite assignment and control flow analysis:

```java
if (!(obj instanceof String s)) {
    // s is NOT in scope here (we know obj is NOT a String)
    return;
}
// s IS in scope here (control only reaches here if instanceof was true)
System.out.println(s.toUpperCase());
```

This **flow-scoping** is a key innovation: the compiler tracks which branches guarantee the pattern match.

### Combining with Logical Operators

```java
if (obj instanceof String s && s.length() > 5) {
    // s is in scope because && short-circuits:
    // s.length() is only evaluated if instanceof matched
    System.out.println(s);
}

if (obj instanceof String s || obj instanceof Integer i) {
    // Neither s nor i is in scope here —
    // we don't know which branch matched
}
```

### Null Handling

`instanceof` always returns `false` for `null`, and the pattern variable is never bound:

```java
Object obj = null;
if (obj instanceof String s) {
    // NOT executed — instanceof returns false for null
}
```

## Pattern Matching as a Foundation

This feature was the foundation for the broader pattern matching effort in Amber:

1. **Java 16**: Pattern matching for `instanceof` (type patterns)
2. **Java 17–21**: Record patterns (deconstruction)
3. **Java 21**: Pattern matching for `switch` (guards, exhaustiveness)

The type pattern (`instanceof String s`) is the simplest form. Record patterns (`case Point(int x, int y)`) extend this to deconstruct objects.

## See Also

- [Pattern Matching — main feature page](../../07-pattern-matching.md)
- [Sealed Classes](02-sealed-classes.md) — exhaustiveness checking
- [Records](01-records.md) — deconstruction targets
- [Pattern Matching for switch](04-pattern-matching-switch.md) — the next evolution
