# OOP and Modules in Go

## Overview

Go uses **structs** with methods (value or pointer receivers).
Interfaces provide **duck typing** for polymorphism. **Embedding**
enables composition (no inheritance). Modules are packages (`package main`).

## How to Run

```bash
go run main.go
```

## Key Concepts

- **Structs** — custom data types with methods
- **Interfaces** — duck typing with explicit contracts
- **Embedding** — composition (not inheritance)
- **No inheritance** — Go deliberately omits inheritance
- **Exported** — capitalized names are public
- **Unexported** — lowercase names are package-private
- **Goroutines** — lightweight threads with channels
- **Select** — multiplex multiple channels
- **Multiple return values** — `(value, error)` pattern
- **Pointers** — explicit memory management

## Historical Context

Go (2009, Google) was designed for simplicity and fast compilation.
Interfaces provide duck typing without inheritance. Embedding replaces inheritance.
Goroutines and channels enable CSP-style concurrency. Explicit pointers give memory control
when needed.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
