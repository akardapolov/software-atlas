# A History of C++: 1979–1991

| | |
|---|---|
| **Author** | Bjarne Stroustrup |
| **Year** | 1993 |
| **Publication** | ACM SIGPLAN Notices (HOPL II) |
| **Topic(s)** | C++, language design, programming language history |

## Summary

This HOPL II paper provides a **comprehensive history of C++'s first decade** (1979–1991). Stroustrup describes the evolution from **"C with Classes"** to a mature, standardized language with templates, exceptions, and the Standard Template Library (STL).

## Key Ideas

### "C with Classes" (1979)

Stroustrup's initial goal:

- **Simula features in C** — classes, inheritance in systems language
- **Zero abstraction cost** — pay only for what you use
- **C compatibility** — all existing C code works
- **Systems programming** — maintain low-level control and efficiency

```
class Shape {
    double area();
    virtual void draw();
};
```

### Language Evolution (1979–1987)

Key milestones:

| Year | Feature | Impact |
|------|----------|---------|
| 1979 | Classes in C | Foundation for OOP |
| 1982 | C++ name adopted | "C with Classes" → "C++" |
| 1983 | Multiple inheritance | Powerful but introduces diamond problem |
| 1984 | Templates | Compile-time polymorphism |
| 1985 | References | Safer pointers, operator overloading |
| 1987 | Overloading | `operator+` syntax |
| 1990 | Exceptions | `try`/`catch` structured error handling |

### The Standard Template Library (STL)

STL became part of C++ standard (1994):

- **Generic algorithms** — containers, iterators, algorithms
- **Efficiency design** — zero abstraction cost principle
- **Iterator pattern** — `for (auto& item : container)` syntax
- **Foundation for modern C++** — template-based generic programming

### Standardization (1991)

ANSI/ISO C++ standard established:

- **Language stability** — breaking changes controlled
- **Cross-compiler compatibility** — standard ABI
- **Global adoption** — standardized C++ worldwide

## Historical Significance

### Making OOP Mainstream

C++ was the **first OOP language** to achieve mainstream adoption:

- **Systems programming** — C++ retained C's efficiency
- **Industry adoption** — C++ became language for games, systems
- **Language influence** — Java, C#, D inherited C++ syntax
- **Template libraries** — STL inspired Java generics, C# generics

### Design Philosophy

Stroustrup's principles:

- **You don't pay for what you don't use**
- **Multi-paradigm** — procedural, OOP, generic, templates coexist
- **Evolution, not revolution** — incremental improvements
- **Local opt-in** — features not forced on users

## Legacy

C++'s influence continues:

- **Template metaprogramming** — modern C++, D concepts (concepts)
- **Zero-cost abstractions** — principle for high-performance languages
- **Generic programming** — STL became model for modern libraries
- **Language design** - influence on Rust, Go, Java generics

## Connections

- **Builds on:** [Simula](../../languages/simula/index.md)
- **Preceded by:** [Ole-Johan Dahl](../../authors/ole-johan-dahl.md) · [Kristen Nygaard](../../authors/kristen-nygaard.md)
- **Influenced:** Java, C#, D, Rust
- **Related works:** [Design and Evolution of C++ (1994)](../papers/stroustrup-1994-design-evolution-cpp.md)
- **Author:** [Bjarne Stroustrup](../../authors/bjarne-stroustrup.md)
