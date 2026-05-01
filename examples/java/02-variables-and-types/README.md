# Variables and Types in Java

## Overview

Java uses **static, strong, nominal typing** — types are checked at
compile time, implicit conversions are limited, and type compatibility
is determined by declared names (not structure).

## Code

See `Main.java` in this directory.

## How to Run

```bash
javac Main.java && java Main
```

## Key Concepts

- **Static typing** — every variable has a declared type (or inferred with `var` since Java 10)
- **Strong typing** — no implicit narrowing conversions; widening only
- **Nominal typing** — a class must explicitly `implements`/`extends` to be compatible
- **Primitive vs reference types** — `int` vs `Integer`, stack vs heap
- **Null** — "billion-dollar mistake"; reference types can be null
- **Generics** (Java 5) — parameterised types with type erasure
- **Records** (Java 16) — immutable data classes
- **Sealed classes** (Java 17) — restricted class hierarchies (toward ADTs)

## Historical Context

Java was designed by James Gosling (1995) for safety and portability.
Its type system is stricter than C's but less expressive than Haskell's
or Rust's. Java has been steadily adopting FP features: lambdas (8),
`var` (10), records (16), sealed classes (17), pattern matching (21+).

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
