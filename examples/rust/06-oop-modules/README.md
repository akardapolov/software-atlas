# OOP and Modules in Rust

## Overview

Rust uses **structs** with `impl` blocks for methods. **Traits** provide
interfaces with default implementations and associated types. Modules are files/directories. **No inheritance** — use composition instead.

## How to Run

```bash
cargo run
```

## Key Concepts

- **Structs** — custom data types with impl blocks
- **Traits** — interfaces with default impls and associated types
- **Visibility** — `pub` for public, no modifier for private
- **Associated types** — types scoped to traits
- **Generics** — `<T>` type parameters
- **Newtype pattern** — zero-cost wrapper
- **Self-referential** — `Option<Box<T>>` for graphs
- **Ownership** — compile-time memory safety
- **Borrow checker** — references with lifetime tracking

## Historical Context

Rust (2010, Mozilla) combines C-like performance with memory safety through ownership. Traits provide interfaces without inheritance. The borrow checker prevents data races at compile time.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
