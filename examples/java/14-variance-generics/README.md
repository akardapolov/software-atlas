# Variance & Generics in Java

## Overview

Variance in Java generics determines how generic types with type relationships relate to each other.

- **Invariance** — `List<T>` only accepts exact type `T`
- **Covariance** — `List<? extends T>` accepts `T` and all subtypes (Producer)
- **Contravariance** — `List<? super T>` accepts `T` and all supertypes (Consumer)

## Code

See `VarianceTest.java` in this directory.

## How to Run

```bash
# Compile
javac -cp .:junit-platform-console-standalone.jar VarianceTest.java

# Run with JUnit
java -jar junit-platform-console-standalone.jar -cp . --scan-class-path
```

## Key Concepts

### PECS Mnemonic
**P**roducer **E**xtends, **C**onsumer **S**uper

- Use `<? extends T>` when you only **read** (produce) from a collection
- Use `<? super T>` when you only **write** (consume) into a collection
- If you both read and write, don't use wildcards (invariant)

### Invariance (`List<T>`)
- Most common and restrictive
- Read and write operations are type-safe
- `List<Integer>` is NOT compatible with `List<Number>`

### Covariance (`List<? extends T>`)
- "Producer" — you can read values as `T`
- Cannot safely write (you don't know the exact subtype)
- `List<? extends Number>` can hold `List<Integer>`, `List<Double>`, etc.

### Contravariance (`List<? super T>`)
- "Consumer" — you can write values of type `T`
- Can only read as `Object` (you don't know the exact supertype)
- `List<? super Integer>` can hold `List<Integer>`, `List<Number>`, `List<Object>`

## Historical Context

Generics were introduced in Java 5 (2004). Java uses **type erasure** — generic type parameters are removed at compile time, which causes certain restrictions (no arrays of generic types, no `new T()`, etc.).

The PECS principle was popularized by Joshua Bloch in *Effective Java* as a way to remember when to use extends vs super wildcards.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
