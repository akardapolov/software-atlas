# FP Features in Rust

## Overview

Rust combines FP features with ownership semantics. Functions are
first-class, closures capture by value/reference, and iterators
provide lazy evaluation. Rust 1.39+ added async/await syntax.

## Code

See \`main.rs\` in this directory.

## How to Run

\`\`\`bash
cargo run
# Or
rustc main.rs && ./main
\`\`\`

## Key Concepts

- **First-class functions** — assignable, passable, returnable
- **Closures** — capture by \`move\`, \`ref\`, or copy
- **Iterators** — lazy sequences: map, filter, fold
- **Higher-order functions** — functions taking/returning functions
- **Functional traits** — Fn, FnMut, FnOnce for capture semantics
- **Pattern matching** — match expressions
- **Option/Result** — explicit error handling (no null)
- **Immutability by default** — variables are immutable without \`mut\`
- **Async/await** — zero-cost futures (Rust 1.39+)
- **Iter methods** — .map(), .filter(), .collect()
- **No garbage collection** — memory safety through ownership

## Historical Context

Rust (2010, Graydon Hoare) combines ML-style FP with
systems programming. The \`Fn\` traits encode closure capture at
type level. Iterators provide lazy evaluation with zero abstraction
cost. Rust influenced by Haskell, OCaml, and functional
research.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
