# Variables and Types in Rust

## Overview

Rust uses **static, strong typing** with **type inference**, **ownership**,
and **no null** — replaced by `Option<T>`.

## Code

See `main.rs` in this directory.

## How to Run

```bash
# With cargo (recommended)
cargo run

# Or directly
rustc main.rs -o variables
./variables
```

## Key Concepts

- **Static typing** — all types known at compile time
- **Type inference** — compiler infers types when unambiguous
- **Immutable by default** — variables are immutable; use `mut` to allow mutation
- **Ownership** — each value has exactly one owner; when the owner goes out of scope, value is dropped
- **No null** — `Option<T>` replaces null (Some(value) or None)
- **Algebraic data types** — enums with data (sum types)
- **Pattern matching** — exhaustive `match` on enums

## Historical Context

Rust was designed for memory safety without garbage collection. Its type
system draws from ML (algebraic types, pattern matching), Haskell (traits,
no null), and C++ (zero-cost abstractions, systems-level control). The
ownership system is Rust's unique contribution — it prevents data races
and use-after-free at compile time.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
