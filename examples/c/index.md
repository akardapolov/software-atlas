# C Examples

Runnable code examples for the [C language profile](../../docs/languages/c/index.md).

Each example is self-contained: one directory, one `README.md` with explanation,
one or more `.c` files you can compile and run.

---

## How to Run Any Example

```bash
cd examples/c/<example-dir>
gcc main.c -o main && ./main        # most examples
gcc -pthread main.c -o main && ./main  # 08-concurrency
```

No external dependencies required for examples 01–07.
Example 08 (concurrency) requires pthread support (`-pthread` flag).
Example 09 (testing) uses a minimal test framework.

---

## Examples

| #  | Directory                                                  | Topic                                 | Run                          |
|----|------------------------------------------------------------|---------------------------------------|------------------------------|
| 01 | [01-hello-world](01-hello-world/README.md)                 | Basic program, compilation, `printf`  | `gcc main.c -o main && ./main` |
| 02 | [02-variables-and-types](02-variables-and-types/README.md) | Pointers, arrays, structures          | `gcc main.c -o main && ./main` |
| 03 | [03-functions](03-functions/README.md)                     | Parameters, return values, recursion  | `gcc main.c -o main && ./main` |
| 04 | [04-control-flow](04-control-flow/README.md)               | Loops, conditionals, switch           | `gcc main.c -o main && ./main` |
| 05 | [05-data-structures](05-data-structures/README.md)         | Linked lists, arrays, structs         | `gcc main.c -o main && ./main` |
| 06 | [06-oop-modules](06-oop-modules/README.md)                 | Structs with methods, headers         | `gcc main.c -o main && ./main` |
| 07 | [07-fp-features](07-fp-features/README.md)                 | Function pointers, callbacks          | `gcc main.c -o main && ./main` |
| 08 | [08-concurrency](08-concurrency/README.md)                 | pthreads, mutex, threads              | `gcc -pthread main.c -o main && ./main` |
| 09 | [09-testing](09-testing/README.md)                         | Unit testing in C                     | `gcc main.c -o main && ./main` |

---

## Source Files

| Example                                                    | Source                                    |
|------------------------------------------------------------|-------------------------------------------|
| [01-hello-world](01-hello-world/README.md)                 | [main.c](01-hello-world/main.c)           |
| [02-variables-and-types](02-variables-and-types/README.md) | [main.c](02-variables-and-types/main.c)   |
| [03-functions](03-functions/README.md)                     | [main.c](03-functions/main.c)             |
| [04-control-flow](04-control-flow/README.md)               | [main.c](04-control-flow/main.c)          |
| [05-data-structures](05-data-structures/README.md)         | [main.c](05-data-structures/main.c)       |
| [06-oop-modules](06-oop-modules/README.md)                 | [main.c](06-oop-modules/main.c)           |
| [07-fp-features](07-fp-features/README.md)                 | [main.c](07-fp-features/main.c)           |
| [08-concurrency](08-concurrency/README.md)                 | [main.c](08-concurrency/main.c)           |
| [09-testing](09-testing/README.md)                         | [main.c](09-testing/main.c)               |

---

*Back to [C language profile](../../docs/languages/c/index.md) ·
[All examples](../README.md)*
