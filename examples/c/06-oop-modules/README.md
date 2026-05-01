# OOP/Modules in C

## Overview

C has **no built-in OOP** — no classes, inheritance, or methods.
However, OOP patterns can be simulated with structs, function pointers,
and header files. C++ added OOP, influencing object design
across C-based codebases.

## Code

See `main.c` in this directory.

## How to Run

```bash
gcc main.c -o oop && ./oop
```

## Key Concepts

- **Structs** — aggregate data (like fields)
- **Function pointers** — methods as struct members
- **Encapsulation** — header files hide implementation
- **Separate compilation** — each `.c` compiled to `.o`
- **Constructor patterns** — functions named `struct_create()`
- **Destructor patterns** — functions named `struct_destroy()`
- **Inheritance simulation** — composition or struct embedding
- **Polymorphism** — function pointers or unions
- **Modules** — header files as module boundaries

## Historical Context

C (1972) was designed for systems programming, not OOP.
OOP patterns in C evolved from Smalltalk/Simula influence.
C++ (1983) added classes to C, showing demand for OOP
in systems languages. Many C codebases use OOP conventions
(structs + function pointers).

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
