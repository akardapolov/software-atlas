# Functions in Go

## Overview

Go functions are **not first-class** — you cannot define functions
inside other functions. However, Go supports **function values** that
can be assigned, passed, and returned. Closures are supported and
capture variables from the enclosing scope.

## Code

See `main.go` in this directory.

## How to Run

```bash
go run main.go
```

## Key Concepts

- **Multiple return values** — functions can return multiple values without structs
- **Named return values** — declare return names for documentation and convenience
- **Function values** — functions are values, assignable to variables
- **Closures** — inner functions capture outer variables
- **Anonymous functions** — inline function literals `func(x) { ... }`
- **Variadic functions** — `...` prefix for any number of arguments
- **Defer** — schedule function execution at scope exit
- **Panic and recover** — error handling mechanism (no exceptions)

## Historical Context

Go (2009, Pike, Thompson, Griesemer) was designed for simplicity.
The function model is intentionally minimal: no method overloading,
no default parameters, no generics until Go 1.18. Multiple return
values and closures provide a clean way to handle common patterns.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
