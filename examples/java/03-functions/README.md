# Functions in Java

## Overview

Java gained lambda expressions and functional interfaces in Java 8 (2014).
Before that, anonymous inner classes were required to pass behaviour.
Java functions are **not first-class** in the traditional sense —
lambdas are syntactic sugar for functional interface implementations.

## Code

See `Main.java` in this directory.

## How to Run

```bash
javac Main.java && java Main
```

## Key Concepts

- **Functional interfaces** — interfaces with one abstract method (`Function`, `Predicate`, `Consumer`)
- **Lambda expressions** — `(x) -> x * 2` syntax for inline functions
- **Method references** — `String::toUpperCase` as shorthand for lambdas
- **Streams** — higher-order functions: `map`, `filter`, `reduce`, `collect`
- **Function composition** — `andThen` and `compose` methods
- **Closures** — lambdas capture "effectively final" variables
- **Optional** — functional null-safety wrapper
- **Generics** — type parameters for generic functions

## Historical Context

Java 8 (2014) was a major milestone bringing functional programming
to Java. The Streams API and lambda expressions enabled declarative
data transformation patterns similar to LINQ (C#) and functional
languages. Influenced by Scala, Clojure, and the functional programming
movement.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
