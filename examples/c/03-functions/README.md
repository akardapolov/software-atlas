# Functions in C

## Overview

C functions are **not first-class** — they cannot be nested, assigned,
or returned from other functions. However, C supports **function pointers**,
which allow passing functions as arguments and storing them in data structures.

## Code

See `main.c` in this directory.

## How to Run

```bash
gcc main.c -o functions && ./functions
```

## Key Concepts

- **Static typing** — parameter types and return type must be declared
- **No nested functions** — functions cannot be defined inside other functions
- **Function pointers** — allow passing functions as arguments
- **Variadic functions** — `printf`-style with `stdarg.h`
- **Inline functions** — compiler hint with `inline` keyword
- **Function qualifiers** — `static` (file scope), `const` parameters
- **Callbacks** — common pattern with function pointers
- **Macro functions** — preprocessor text substitution

## Historical Context

C (1972, Dennis Ritchie) was designed for systems programming.
Functions in C are minimal and close to hardware — a function call
is essentially a jump with a new stack frame. Function pointers enable
patterns like `qsort` callbacks and event-driven programming.

C's function model influenced C++, Objective-C, and later languages
that added first-class functions.

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
