# Data Structures in Rust

## Overview

Rust provides `Vec` (dynamic arrays), `HashMap` (hash tables), `BTreeMap`
(ordered maps), and `HashSet`. `Option` replaces nullable types, and `Result`
handles errors. The ownership system ensures memory safety without garbage collection.

## How to Run

```bash
cargo run
```

## Key Concepts

- **Vec** — dynamic array with automatic resizing
- **HashMap** — hash table for key-value pairs
- **HashSet** — hash set for unique elements
- **BTreeMap/BTreeSet** — ordered map/set (red-black tree)
- **VecDeque** — double-ended queue
- **BinaryHeap** — priority queue (min/max)
- **Structs** — custom data types with named fields
- **Enums** — algebraic data types with variants
- **Option** — nullable values (no null pointer)
- **Result** — error handling (`Ok` or `Err`)
- **Ownership** — compile-time memory safety checks
- **Borrow checker** — references with lifetime tracking

## Historical Context

Rust (2010, Mozilla) combines C-like performance with memory safety through ownership. Vec provides safe dynamic arrays, and the standard library includes efficient collections. The borrow checker prevents data races at compile time.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
