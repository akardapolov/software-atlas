# Variables and Types in C

## Overview

C uses **static, weak typing** — types are checked at compile time
(static) but implicit conversions happen freely (weak).

## Code

See `main.c` in this directory.

## How to Run

```bash
gcc -Wall -Wextra -o variables main.c && ./variables
```

## Key Concepts

- **Static typing** — every variable must have a declared type
- **Weak typing** — implicit conversions between numeric types (and even pointers!)
- **Fixed-size types** — `int` is typically 32 bits, but size is platform-dependent
- **Manual memory** — no garbage collector; programmer manages allocation
- **No strings** — strings are null-terminated `char` arrays
- **Pointers** — direct memory addresses as first-class values
- **Undefined behaviour** — accessing uninitialised memory, buffer overflows, etc.

## Historical Context

C was designed by Dennis Ritchie at Bell Labs (1972) as a "portable
assembly" — close to hardware but usable across architectures. Its
type system trusts the programmer, performing few safety checks.
This design influenced C++, Java (stronger types), Go, and Rust
(much stronger types + ownership).

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
