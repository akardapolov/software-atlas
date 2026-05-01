# Data Structures in C

## Overview

C provides **arrays** (fixed-size sequences) and **structs** (custom data
types). Dynamic data structures require manual memory management with `malloc`/`free`. The programmer controls memory layout explicitly.

## How to Run

```bash
# Compile
gcc main.c -o data-structures

# Run
./data-structures

# Or with clang
clang main.c -o data-structures
./data-structures
```

## Key Concepts

- **Arrays** — fixed-size contiguous memory blocks
- **Structs** — custom data types with value semantics
- **Pointers** — memory addresses, manual indirection
- **malloc/free** — dynamic memory allocation
- **Linked lists** — nodes with explicit `next` pointers
- **Manual memory management** — no garbage collection, must free allocations
- **sizeof** — compile-time size of types

## Historical Context

C (1972, Dennis Ritchie) provides low-level control over memory. Manual memory management enables efficiency but requires careful programming. Arrays and structs map directly to memory layout, making C suitable for systems programming and performance-critical code.

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
