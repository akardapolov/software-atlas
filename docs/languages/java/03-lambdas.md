# Lambdas and Functional Interfaces

**Java 8** introduced lambda expressions as a concise way to implement
**functional interfaces** — interfaces with exactly one abstract method.

```java
// Functional interface (can be a lambda target)
@FunctionalInterface
interface Transformer<T, R> {
    R transform(T input);
}

// Lambda implementation
Transformer<String, Integer> length = s -> s.length();
Transformer<Integer, Integer> square = n -> n * n;

System.out.println(length.transform("hello"));   // 5
System.out.println(square.transform(7));         // 49
```

## Built-in functional interfaces (`java.util.function`)

| Interface           | Signature     | Description       |
|---------------------|---------------|-------------------|
| `Predicate<T>`      | `T → boolean` | Test / filter     |
| `Function<T,R>`     | `T → R`       | Transform         |
| `Consumer<T>`       | `T → void`    | Side effect       |
| `Supplier<T>`       | `() → T`      | Produce value     |
| `BiFunction<T,U,R>` | `T, U → R`    | Two-arg transform |

## Key characteristics

- Lambdas can only capture external local variables if they are **final or effectively final**.
- Method references (`System.out::println`, `String::length`) provide an even more concise syntax when a lambda simply delegates to an existing method.
- Lambdas compile to invokedynamic bytecode, not anonymous inner classes, avoiding the per-instance class overhead.

---

## Examples

- [FP Features example](../../../examples/java/07-fp-features/README.md) — Lambdas, method references, functional interfaces
