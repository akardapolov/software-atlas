# Variables and Types in Go

## Overview

Go uses **static, strong typing** with **type inference** and
**structural typing** for interfaces.

## Code

See `main.go` in this directory.

## How to Run

```bash
go run main.go
```

## Key Concepts

- **Static typing** — types are checked at compile time
- **Type inference** — `:=` infers type from of right-hand side
- **No implicit conversions** — even `int32` → `int64` requires explicit cast
- **Zero values** — every type has a default zero value (no uninitialised variables)
- **Structural typing** — interfaces are satisfied implicitly (no `implements` keyword)
- **No generics until 1.18** — historically, `interface{}` (now `any`) was used for generic code

## Historical Context

Go was designed at Google by Robert Griesemer, Rob Pike, and Ken Thompson
for simplicity and fast compilation. The type system is deliberately
minimal — no inheritance, no exceptions, no method overloading. Types
serve clarity and safety without of complexity of Haskell or Rust.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
