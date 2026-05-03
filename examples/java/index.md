# Java Examples

Runnable code examples for the [Java language profile](../../docs/languages/java/index.md).

Each example is self-contained: one directory, one `README.md` with explanation,
one or more `.java` files you can compile and run directly.

---

## How to Run Any Example

```bash
cd examples/java/<example-dir>
javac Main.java && java Main          # most examples
javac HelloWorld.java && java HelloWorld   # 01-hello-world
javac VarianceTest.java && java VarianceTest   # 17-variance-generics
```

No external dependencies required for examples 01–08, 14–17.
Example 09 (testing) uses JUnit (provided as a JAR).

---

## Examples

| #  | Directory                                                              | Topic                                        | Run                                        |
|----|------------------------------------------------------------------------|----------------------------------------------|--------------------------------------------|
| 01 | [01-hello-world](01-hello-world/README.md)                             | Class structure, `main` method               | `javac HelloWorld.java && java HelloWorld` |
| 02 | [02-variables-and-types](02-variables-and-types/README.md)             | Primitives, wrappers, `var`                  | `javac Main.java && java Main`             |
| 03 | [03-functions](03-functions/README.md)                                 | Methods, overloading, recursion              | `javac Main.java && java Main`             |
| 04 | [04-control-flow](04-control-flow/README.md)                           | Loops, conditionals, switch expressions      | `javac Main.java && java Main`             |
| 05 | [05-data-structures](05-data-structures/README.md)                     | Lists, Maps, Sets, records                   | `javac Main.java && java Main`             |
| 06 | [06-oop-modules](06-oop-modules/README.md)                             | Packages, interfaces, inheritance            | `javac Main.java && java Main`             |
| 07 | [07-fp-features](07-fp-features/README.md)                             | Lambdas, streams, `Optional`                 | `javac Main.java && java Main`             |
| 08 | [07-error-handling](07-error-handling/README.md)                       | Checked/unchecked, try-with-resources        | `javac Main.java && java Main`             |
| 09 | [08-concurrency](08-concurrency/README.md)                             | Threads, executors, virtual threads          | `javac Main.java && java Main`             |
| 10 | [09-testing](09-testing/README.md)                                     | JUnit 5, Mockito, AssertJ                    | `javac -cp junit.jar Main.java && java …`  |
| 14 | [14-streams-advanced](14-streams-advanced/README.md)                   | Reduce, mapMulti, takeWhile, merge           | `javac Main.java && java Main`             |
| 15 | [15-concurrency-thread-states](15-concurrency-thread-states/README.md) | BLOCKED, WAITING, TIMED_WAITING              | `javac Main.java && java Main`             |
| 16 | [16-concurrency-structured](16-concurrency-structured/README.md)       | ShutdownOnFailure, ShutdownOnSuccess         | `javac Main.java && java Main`             |
| 17 | [17-variance-generics](17-variance-generics/README.md)                 | Invariance, covariance, contravariance, PECS | `javac VarianceTest.java && java …`        |

---

## Source Files

| Example                                                                | Source                                                                  |
|------------------------------------------------------------------------|-------------------------------------------------------------------------|
| [01-hello-world](01-hello-world/README.md)                             | [HelloWorld.java](01-hello-world/HelloWorld.java)                       |
| [02-variables-and-types](02-variables-and-types/README.md)             | [Main.java](02-variables-and-types/Main.java)                           |
| [03-functions](03-functions/README.md)                                 | [Main.java](03-functions/Main.java)                                     |
| [04-control-flow](04-control-flow/README.md)                           | [Main.java](04-control-flow/Main.java)                                  |
| [05-data-structures](05-data-structures/README.md)                     | [Main.java](05-data-structures/Main.java)                               |
| [06-oop-modules](06-oop-modules/README.md)                             | [Main.java](06-oop-modules/Main.java)                                   |
| [07-fp-features](07-fp-features/README.md)                             | [main.java](07-fp-features/main.java)                                   |
| [07-error-handling](07-error-handling/README.md)                       | [main.java](07-error-handling/main.java)                                |
| [08-concurrency](08-concurrency/README.md)                             | [Main.java](08-concurrency/Main.java)                                   |
| [09-testing](09-testing/README.md)                                     | [main.java](09-testing/main.java)                                       |
| [14-streams-advanced](14-streams-advanced/README.md)                   | [Main.java](14-streams-advanced/Main.java)                              |
| [15-concurrency-thread-states](15-concurrency-thread-states/README.md) | [Main.java](15-concurrency-thread-states/Main.java)                     |
| [16-concurrency-structured](16-concurrency-structured/README.md)       | [Main.java](16-concurrency-structured/Main.java)                        |
| [17-variance-generics](17-variance-generics/README.md)                 | [VarianceTest.java](17-variance-generics/VarianceTest.java)             |

---

*Back to [Java language profile](../../docs/languages/java/index.md) ·
[All examples](../README.md)*
