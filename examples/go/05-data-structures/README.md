# Data Structures in Go

## Overview

Go provides **slices** (dynamic arrays), **maps** (hash tables), and
**structs** (custom data types). Built-in collections are efficient with
automatic memory management (garbage collection). `container/heap` provides
priority queue implementation.

## How to Run

```bash
go run main.go
```

## Key Concepts

- **Slice** — dynamic array with automatic resizing
- **Map** — hash table for key-value pairs
- **Struct** — custom data type with named fields
- **Pointer** — explicit memory reference (`*T`)
- **make()** — efficient pre-allocation for slices/maps
- **Range** — iteration over slices, maps, channels
- **Interfaces** — duck typing for polymorphism

## Historical Context

Go (2009, Google) combines C-like syntax with modern features like garbage collection and built-in concurrency. Slices provide safer alternative to C arrays, and maps use hash tables for efficient lookups.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
