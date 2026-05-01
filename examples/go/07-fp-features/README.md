# FP Features in Go

## Overview

Go is primarily imperative but supports functional patterns:
first-class functions, closures, and limited higher-order operations.
Go 1.18 added generics. Go emphasizes simplicity over
functional purity.

## Code

See \`main.go\` in this directory.

## How to Run

\`\`\`bash
go run main.go
\`\`\`

## Key Concepts

- **First-class functions** — assignable, passable, returnable
- **Closures** — capture variables from enclosing scope
- **Function types** — \`func(T) T\` signature
- **Higher-order functions** — can accept/return functions
- **Anonymous functions** — inline \`func(x) { ... }\`
- **Multi-return values** — \`func() (int, error)\` for errors
- **Slices** — dynamic arrays with functional operations
- **Generics (Go 1.18+)** — type parameters \`func[T](x) T\`
- **Not pure** — side effects allowed (prints, mutations)
- **No map/filter built-in** — write loops or external packages

## Historical Context

Go (2009) was designed for simplicity, not FP.
Functional features are pragmatic: closures exist, but language
doesn't emphasize purity. Go's multi-return values provide explicit
error handling without exceptions.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
