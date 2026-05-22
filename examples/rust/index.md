# Rust Examples

Runnable code examples for the [Rust language profile](../../docs/languages/rust/index.md).

Each example is self-contained: one directory, one `README.md` with explanation,
one or more `.rs` files you can compile and run with `cargo` or `rustc`.

---

## How to Run Any Example

```bash
cd examples/rust/<example-dir>
cargo run              # most examples with Cargo.toml
cargo run --manifest-path ../Cargo.toml  # if shared
```

No external dependencies required for examples 01–09.
Example 09 (testing) uses `cargo test`.

---

## Examples

| #  | Directory                                                  | Topic                                     | Run           |
|----|------------------------------------------------------------|-------------------------------------------|---------------|
| 01 | [01-hello-world](01-hello-world/README.md)                 | Cargo, main, println                        | `cargo run`   |
| 02 | [02-variables-and-types](02-variables-and-types/README.md) | Ownership, mutability, types              | `cargo run`   |
| 03 | [03-functions](03-functions/README.md)                     | Closures, higher-order functions          | `cargo run`   |
| 04 | [04-control-flow](04-control-flow/README.md)               | Loops, match, if-let                      | `cargo run`   |
| 05 | [05-data-structures](05-data-structures/README.md)         | Structs, enums, pattern matching          | `cargo run`   |
| 06 | [06-oop-modules](06-oop-modules/README.md)                 | Traits, impl blocks, modules              | `cargo run`   |
| 07 | [07-fp-features](07-fp-features/README.md)                 | Iterators, closures, functional style     | `cargo run`   |
| 08 | [08-concurrency](08-concurrency/README.md)                 | Threads, channels, Arc, Mutex             | `cargo run`   |
| 09 | [09-testing](09-testing/README.md)                         | Unit tests, integration tests             | `cargo test`  |

---

## Source Files

| Example                                                    | Source                                  |
|------------------------------------------------------------|-----------------------------------------|
| [01-hello-world](01-hello-world/README.md)                 | [main.rs](01-hello-world/main.rs)       |
| [02-variables-and-types](02-variables-and-types/README.md) | [main.rs](02-variables-and-types/main.rs) |
| [03-functions](03-functions/README.md)                     | [main.rs](03-functions/main.rs)         |
| [04-control-flow](04-control-flow/README.md)               | [main.rs](04-control-flow/main.rs)      |
| [05-data-structures](05-data-structures/README.md)         | [src/main.rs](05-data-structures/src/main.rs) |
| [06-oop-modules](06-oop-modules/README.md)                 | [src/main.rs](06-oop-modules/src/main.rs) |
| [07-fp-features](07-fp-features/README.md)                 | [main.rs](07-fp-features/main.rs)       |
| [08-concurrency](08-concurrency/README.md)                 | [main.rs](08-concurrency/main.rs)       |
| [09-testing](09-testing/README.md)                         | [main.rs](09-testing/main.rs)           |

---

*Back to [Rust language profile](../../docs/languages/rust/index.md) ·
[All examples](../README.md)*
