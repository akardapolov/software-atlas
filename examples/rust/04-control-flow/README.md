# Control Flow in Rust

## Overview

Rust uses `if` as an expression, `match` for exhaustive branching,
and `loop`/`while`/`for`. `break` can return a value from a loop.

## How to Run

```bash
rustc main.rs -o control && ./control
```

## Key Concepts

- **`if`** — conditional expression (returns value)
- **`match`** — exhaustive branching (must handle all cases)
- **`loop`/`while`/`for`** — looping constructs
- **`break`** — can return a value from loops
- **Pattern-based control flow** — via `if let` and `while let`
