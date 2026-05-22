# Context

| Section | Content |
| :--- | :--- |
| **Description** | The `context` package provides a way to carry deadlines, cancellation signals, and request-scoped values across API boundaries and goroutines. It is essential for building robust, cancellable concurrent programs. |
| **API Purpose** | Propagating cancellation, timeouts, and request metadata through call chains and goroutine hierarchies. |
| **Terminology** | `context.Context`, `context.Background`, `context.TODO`, `context.WithCancel`, `context.WithTimeout`, `context.WithDeadline`, `context.WithValue`. |
| **Notes** | Contexts form a tree where cancelling a parent cancels all children. `context.WithValue` should be used sparingly — prefer explicit function parameters. Context is the first parameter convention in Go. |

```mermaid
flowchart TD
    BG["context.Background()"] -->|"WithCancel"| C1["ctx (cancelable)"]
    BG -->|"WithTimeout"| T1["ctx (timeout)"]
    C1 -->|"WithCancel"| C2["child ctx"]
    C1 -->|"WithValue"| V1["ctx + key/value"]
    T1 -->|"WithCancel"| C3["child ctx"]

    CANCEL["cancel()"] -.->|cancels| C1
    CANCEL -.->|cancels| C2
    CANCEL -.->|cancels| V1
    TIMEOUT["timeout"] -.->|cancels| T1
    TIMEOUT -.->|cancels| C3

    style BG fill:#e1f5fe,stroke:#0288d1
    style C1 fill:#e8f5e9,stroke:#388e3c
    style C2 fill:#e8f5e9,stroke:#388e3c
    style T1 fill:#fff3e0,stroke:#f4a261
    style C3 fill:#e8f5e9,stroke:#388e3c
    style V1 fill:#f3e5f5,stroke:#7b1fa2
```

## Cancellation

```go
func main() {
    ctx, cancel := context.WithCancel(context.Background())
    defer cancel()

    go worker(ctx)

    time.Sleep(2 * time.Second)
    cancel() // signal worker to stop
    time.Sleep(500 * time.Millisecond)
}

func worker(ctx context.Context) {
    for {
        select {
        case <-ctx.Done():
            fmt.Println("Worker shutting down:", ctx.Err())
            return
        default:
            fmt.Println("Working...")
            time.Sleep(500 * time.Millisecond)
        }
    }
}
```

## Timeout

```go
func fetchWithTimeout(url string) error {
    ctx, cancel := context.WithTimeout(context.Background(), 3*time.Second)
    defer cancel()

    req, err := http.NewRequestWithContext(ctx, "GET", url, nil)
    if err != nil {
        return err
    }

    resp, err := http.DefaultClient.Do(req)
    if err != nil {
        return err // may return context.DeadlineExceeded
    }
    defer resp.Body.Close()
    return nil
}
```

## Values

```go
// Store request-scoped values (use sparingly)
type key string
const userKey key = "user"

func WithUser(ctx context.Context, userID string) context.Context {
    return context.WithValue(ctx, userKey, userID)
}

func UserFromContext(ctx context.Context) (string, bool) {
    userID, ok := ctx.Value(userKey).(string)
    return userID, ok
}
```

> **Best Practice:** Pass `context.Context` as the first parameter to functions. Do not store contexts in structs — pass them explicitly.

---

Examples: [Concurrency](../../../examples/go/09-concurrency/README.md)
