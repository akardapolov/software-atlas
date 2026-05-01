# Concurrency in Rust

## Overview

Rust provides **"fearless concurrency"** through ownership, lifetimes,
and zero-cost abstractions. The type system prevents data races at compile
time. Rust supports threads, channels, async/await, and actors.

## Code

See `main.rs` in this directory.

## How to Run

```bash
cargo run
# Or
rustc main.rs && ./main
```

## Key Concepts

- **std::thread::spawn** — spawn new threads
- **std::sync::Mutex** — mutual exclusion with RAII guards
- **std::sync::RwLock** — read-write lock for many readers
- **std::sync::mpsc** — multi-producer, single-consumer channels
- **Arc** — atomic reference counting for shared ownership
- **move closures** — closures can capture by value or reference
- **Send + Sync traits** — type-level thread safety guarantees
- **async/await** — zero-cost futures with async syntax (Rust 1.39+)
- **Data freedom from races** — compile-time prevention of data races

## Historical Context

Rust (2010, Graydon Hoare) took inspiration from Erlang's actor model
and Haskell's type system. The `Send` and `Sync` traits encode
thread safety at the type level. Rust's ownership model ensures that data
races are caught at compile time, a unique feature among systems languages.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
