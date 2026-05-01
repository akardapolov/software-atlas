# Hello World in Rust

## Overview

A simple Hello World program in Rust, a systems programming language focused on safety.

## Code

```rust
fn main() {
    println!("Hello, World!");
}
```

## How to Run

```bash
# Run directly
cargo run

# Or compile and run
rustc main.rs -o hello
./hello
```

## Key Concepts

- `fn main()` - Entry point function
- `println!` - Macro for formatted output (note the `!`)
- Ownership system for memory safety without GC
- Strong static typing with type inference

## Historical Context

Rust was started by Graydon Hoare in 2006, sponsored by Mozilla from 2009, and 1.0 released in 2015. Key features:
- Memory safety without garbage collection
- Zero-cost abstractions
- Influenced by: C++, Haskell, OCaml, Cyclone

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
