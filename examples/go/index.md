# Go Examples

Runnable code examples for the [Go language profile](../../docs/languages/go/index.md).

Each example is self-contained: one directory, one `README.md` with explanation,
one `main.go` file you can run directly.

---

## How to Run Any Example

```bash
cd examples/go/<example-dir>
go run main.go
```

Requires Go 1.18+ for examples using generics (07-fp-features).
No external dependencies required.

---

## Examples

| #  | Directory                                                  | Topic                                          | Run              |
|----|------------------------------------------------------------|------------------------------------------------|------------------|
| 01 | [01-hello-world](01-hello-world/README.md)                 | Package, entry point, basic syntax             | `go run main.go` |
| 02 | [02-variables-and-types](02-variables-and-types/README.md) | Type inference, zero values, pointers          | `go run main.go` |
| 03 | [03-functions](03-functions/README.md)                     | Multiple returns, defer, error handling        | `go run main.go` |
| 04 | [04-control-flow](04-control-flow/README.md)               | For/range loops, select statements             | `go run main.go` |
| 05 | [05-data-structures](05-data-structures/README.md)         | Slices, maps, structs                          | `go run main.go` |
| 06 | [06-oop-modules](06-oop-modules/README.md)                 | Interfaces, methods, packages                  | `go run main.go` |
| 07 | [07-fp-features](07-fp-features/README.md)                 | First-class functions, closures, generics      | `go run main.go` |
| 08 | [08-error-handling](08-error-handling/README.md)           | Multi-return values, error type, panic/recover | `go run main.go` |
| 09 | [09-concurrency](09-concurrency/README.md)                 | Goroutines, channels, select                   | `go run main.go` |
| 10 | [10-testing](10-testing/README.md)                         | testing package, table-driven tests            | `go run main.go` |

---

## Source Files

| Example                                                    | Source                                    |
|------------------------------------------------------------|-------------------------------------------|
| [01-hello-world](01-hello-world/README.md)                 | [main.go](01-hello-world/main.go)         |
| [02-variables-and-types](02-variables-and-types/README.md) | [main.go](02-variables-and-types/main.go) |
| [03-functions](03-functions/README.md)                     | [main.go](03-functions/main.go)           |
| [04-control-flow](04-control-flow/README.md)               | [main.go](04-control-flow/main.go)        |
| [05-data-structures](05-data-structures/README.md)         | [main.go](05-data-structures/main.go)     |
| [06-oop-modules](06-oop-modules/README.md)                 | [main.go](06-oop-modules/main.go)         |
| [07-fp-features](07-fp-features/README.md)                 | [main.go](07-fp-features/main.go)         |
| [08-error-handling](08-error-handling/README.md)           | [main.go](08-error-handling/main.go)      |
| [09-concurrency](09-concurrency/README.md)                 | [main.go](09-concurrency/main.go)         |
| [10-testing](10-testing/README.md)                         | [main.go](10-testing/main.go)             |

---

*Back to [Go language profile](../../docs/languages/go/index.md) ·
[All examples](../README.md)*
