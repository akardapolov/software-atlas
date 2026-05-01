# OOP and Modules in Python

## Overview

Python supports multiple OOP paradigms: **class-based** inheritance,
**duck typing** (polymorphism via behaviour), and **dataclasses** (3.7+).
Modules are simply `.py` files that can be imported.

## How to Run

```bash
python3 main.py
```

## Key Concepts

- **Classes** — `class` keyword, `__init__` constructor, `self` reference
- **Objects** — instances of classes, dot notation access
- **Inheritance** — `class Child(Parent)` for single inheritance
- **Multiple inheritance** — `class Child(Base1, Base2)` with MRO
- **Encapsulation** — `__attr` for private (name mangling)
- **Polymorphism** — duck typing (no interfaces required)
- **Abstract base classes** — `ABC` module, `@abstractmethod`
- **Properties** — `@property` decorator for computed attributes
- **Static/class methods** — `@staticmethod`, `@classmethod`
- **Magic methods** — `__str__`, `__repr__`, `__eq__`, etc.
- **Dataclasses** — type-safe containers (Python 3.7+)
- **Modules** — import via `import module`, `from module import`

## Historical Context

Python's OOP evolved from adding classes to Python 2.2 (1991). Duck typing enables flexibility without explicit interfaces. Multiple inheritance with MRO solves the diamond problem. Python 3.7 (2020) added dataclasses for cleaner type-safe data containers.

For more on Python, see [docs/languages/python/](../../../docs/languages/python/)
