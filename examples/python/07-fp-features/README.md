# FP Features in Python

## Overview

Python supports **functional programming** with higher-order functions
(`map`, `filter`, `reduce`), **lambda** for anonymous functions.
Immutability is default but can be modified.

## How to Run

```bash
python3 main.py
```

## Key Concepts

- **Higher-order functions** — functions that take functions as arguments
- **Pure functions** — no side effects, deterministic
- **Immutability** — strings/tuples immutable, lists mutable
- **Closures** — inner functions capture enclosing scope
- **Decorators** — `@functools` (`@lru_cache`, etc.)
- **Partial application** — currying (functools.partial)
- **Compose** — function composition
- **Generators** — lazy evaluation with `yield`

## Historical Context

Python was designed with readability and ease of use. Functional features were added gradually. Python 3.7 (2020) added type hints for optional static analysis.

For more on Python, see [docs/languages/python/](../../../docs/languages/python/)
