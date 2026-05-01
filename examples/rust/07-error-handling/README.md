## Overview

Rust error handling uses **Result<T, E>** and **Option** types.
No exceptions — errors are values that must be handled. The \`?\`
operator propagates errors. \`panic!\` exists for unrecoverable failures.

## Code

See \`main.rs\` in this directory.

## How to Run

\`\`\`bash
cargo run
# Or
cargo build && ./main
\`\`\`

## Key Concepts

- **Result<T, E>** — \`Ok(value)\` or \`Err(error)\`
- **Option** — \`Some(value)\` or \`None\` instead of null
- **unwrap()** — get value or panic
- **unwrap_or()** — get value or default
- **? operator** — \`result? returns value, propagates error\`
- **expect()** — \`result.expect("error message")\`
- **map()/and()** — transform Result values
- **? macro** — \`?expr\` for propagating errors
- **panic!** — \`panic!("message")\` for unrecoverable failures
- **assert!** — \`assert!(condition)\` panics if false
- **assert_eq!** — \`assert_eq!(expected, actual)\`
- **custom error types** — \`#[derive(Debug)] enum Error { msg: String }\`

## Historical Context

Rust (2010) uses algebraic data types for error handling,
inspired by ML and Haskell. The \`Result\` type forces explicit
error handling at compile time. \`panic!\` is rarely used —
most errors are handled via \`Result\` or \`Option\`.

For more on Rust, see [docs/languages/rust/](../../../docs/languages/rust/)
