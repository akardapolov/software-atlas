# Testing in Rust

## Overview

Rust has built-in \`assert!\` macros in \`std\`. **Cargo test** discovers
\`#[test]\` functions. **Rust's type system** catches many bugs
at compile time. External libraries provide additional features.

## Code

See \`main.rs\` in this directory.

## How to Run

\`\`\`bash
cargo test
cargo test -- --nocapture
cargo test --release
cargo test -- --ignored  # run ignored tests
\`\`\`

## Key Concepts

- **#[test] attribute** — marks functions as tests
- **assert macros** — \`assert!\`, \`assert_eq!\`, \`assert_ne!\`
- **#[should_panic]** — tests for panic conditions
- **Integration tests** — \`tests/\` directory
- **Doc tests** — code examples in doc comments are tested
- **#[test]\` and \`#[ignore]\` attributes
- **mocking** — mockall crate for trait mocking
- **Property-based testing** — proptest crate
- **Test coverage** — tarpaulin, cargo-tarpaulin

## Historical Context

Rust (2010) built testing into cargo from the start. The
\`assert!\` macros provide compile-time checked assertions. Property-based
testing from Haskell (QuickCheck) influenced Rust's proptest.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/index.md)
