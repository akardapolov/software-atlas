## Overview

Go error handling uses **multi-return values** and the \`error\` type.
Go has no exceptions — errors are values that must be checked.
Go 1.13+ added \`errors.Is()\` for error comparison.

## Code

See \`main.go\` in this directory.

## How to Run

\`\`\`bash
go run main.go
\`\`\`

## Key Concepts

- **Multi-return values** — \`result, err := function()\`
- **error type** — \`type error struct { msg string }\`
- **Error construction** — \`errors.New("message")\`
- **nil vs error** — \`nil\` is not error, check \`err != nil\`
- **Panic** — \`panic("message")\` for unrecoverable errors
- **recover()** — \`defer func() { if r := recover(); ... }\`
- **errors.Is()** — compare wrapped errors: \`errors.Is(err, target)\`
- **errors.As()** — unwrap wrapped errors
- **defer** — always executes, panic or not
- **Custom error types** — implementing \`error\` interface
- **Error wrapping** — \`fmt.Errorf("format %v", args)\`

## Historical Context

Go (2009) chose explicit error values over exceptions for simplicity.
The \`error\` interface and multi-return values make error handling
visible in type signatures. \`errors.Is()\` and \`errors.As()\`
were added in Go 1.13 for better error handling.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
