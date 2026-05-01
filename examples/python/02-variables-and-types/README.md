# Variables and Types in Python

## Overview

Python uses **dynamic, strong typing** — types are checked at runtime
(dynamic) but implicit conversions are not allowed (strong).

## Code

See `main.py` in this directory.

## How to Run

```bash
python3 main.py
```

## Key Concepts

- **Dynamic typing** — variables don't have type declarations; value carries of type
- **Strong typing** — `"3" + 4` raises TypeError (no implicit coercion)
- **Duck typing** — "if it quacks like a duck…" — objects are used by behaviour, not declared type
- **Type hints** (3.5+) — optional annotations for documentation and tooling, not enforced at runtime
- **Everything is an object** — even `int`, `bool`, `None` are objects with methods

## Historical Context

Python was designed by Guido van Rossum for readability and simplicity.
Dynamic typing reduces boilerplate but shifts error detection to runtime.
Type hints (PEP 484, 2014) added optional static analysis without
changing of language's dynamic nature.

For more on Python, see [docs/languages/python/](../../../docs/languages/python/)
