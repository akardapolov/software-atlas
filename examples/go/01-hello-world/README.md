# Hello World in Go

## Overview

A simple Hello World program in Go (Golang).

## Code

```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, World!")
}
```

## How to Run

```bash
# Run directly
go run main.go

# Or build and run
go build -o hello
./hello
```

## Key Concepts

- `package main` - Package declaration (main is executable)
- `import "fmt"` - Import formatting package
- `func main()` - Entry point function
- Strong typing but with type inference
- Built-in concurrency (goroutines, channels)

## Historical Context

Go was created by Robert Griesemer, Rob Pike, and Ken Thompson at Google in 2009. Key goals:
- Fast compilation
- Concurrency via CSP (Communicating Sequential Processes)
- Simplicity over features
- Good for cloud-native and distributed systems

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
