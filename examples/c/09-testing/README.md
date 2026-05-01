# Testing in C

## Overview

C has no built-in testing framework. Testing is done with external
libraries like **Check**, **Unity**, **CppUTest** (for C++), or
custom assertion macros. Test organization follows file structure and build systems.

## Code

See `main.c` in this directory.

## How to Run

```bash
gcc -DTESTING main.c -o test && ./test
```

## Key Concepts

- **Assertions** — `assert()` macro for runtime checks
- **Test frameworks** — Check, Unity, Criterion
- **Test doubles** — fakes, mocks, stubs
- **Test doubles in C** — function pointers for mocking
- **Fixtures** — setup/teardown functions
- **Parameterized tests** — loops with different inputs
- **Memory testing** — Valgrind, AddressSanitizer
- **Coverage** — GCC gcov, LLVM llvm-cov

## Historical Context

C (1972) has no standard testing — testing evolved as external
tools and patterns. The `assert()` macro originates from early C
libraries. Modern C testing uses external frameworks influenced by JUnit
and xUnit patterns.

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
