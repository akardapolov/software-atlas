# Concurrency in Go

## Overview

Go's concurrency model is built on **CSP (Communicating Sequential Processes)**:
goroutines for lightweight concurrency and channels for safe communication.
Go encourages "share memory by communicating" rather than communicating by sharing.

## Code

See `main.go` in this directory.

## How to Run

```bash
go run main.go
```

## Key Concepts

- **Goroutines** — lightweight threads, spawned with `go func()`
- **Channels** — typed conduits for sending and receiving values
- **Select** — wait on multiple channel operations
- **Buffered channels** — channels with capacity, non-blocking send until full
- **Mutex** — traditional mutex from `sync` package
- **WaitGroup** — wait for multiple goroutines to complete
- **Context** — cancellation and deadlines across goroutines
- **Race detector** — `go run -race` detects data races

## Historical Context

Go (2009) was designed at Google to address C++ complexity and Java
limitations in large-scale services. The goroutine/channel model, inspired
by Hoare's CSP (1978), makes concurrent programming more approachable
and less error-prone than traditional thread + lock approaches.

For more on Go, see [docs/languages/go/](../../../docs/languages/go/)
