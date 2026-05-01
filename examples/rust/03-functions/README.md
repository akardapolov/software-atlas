# Functions in Rust

## Overview

Rust functions are **first-class** — assignable, passable, returnable.
Rust supports closures with ownership awareness, iterators with lazy evaluation,
and higher-order functions. Function syntax includes explicit types and
supports generics.

## Code

See `main.rs` in this directory.

## How to Run

```bash
rustc main.rs && ./main
# Or with cargo
cargo run
```

## Key Concepts

- **First-class functions** — functions are values assignable to variables
- **Closures** — anonymous functions that can capture environment
- **Move, borrow, or copy** — closures specify how they capture variables
- **Iterators** — lazy sequence transformations: `map`, `filter`, `fold`
- **Higher-order functions** — functions taking/returning functions
- **Pattern matching** — match expressions in functions
- **Generics** — type parameters: `fn identity<T>(x: T) -> T`
- **Fn, FnMut, FnOnce** — closure traits defining capture semantics
- **Method syntax** — methods with `self` parameter

## Historical Context

Rust (2010, Graydon Hoare) combines ML-style functional features
with systems programming. Closures with ownership awareness are unique —
they enforce memory safety while enabling functional patterns. Iterators
provide zero-abstraction-cost lazy sequences.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
