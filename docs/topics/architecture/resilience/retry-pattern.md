# Retry Pattern

**Category:** Resilience  
**Source:** Industry pattern; documented in Microsoft *Cloud Design Patterns* (2014)

> Automatically retry failed operations that are likely to succeed on subsequent attempts.

Transient failures — network hiccups, brief service unavailability, throttling — are common in distributed systems. The Retry pattern automatically re-executes a failed operation, often with a backoff strategy.

## Retry Flow

```mermaid
flowchart TD
    Start(["▶ Start Request"]) --> Attempt["Attempt Operation"]
    Attempt --> Success{Success?}
    Success -->|Yes| Done(["✅ Return Result"])
    Success -->|No| CheckRetries{Retries remaining?}
    CheckRetries -->|Yes| Wait["⏳ Wait (backoff delay)"]
    CheckRetries -->|No| Fail(["❌ Return Error"])
    Wait --> Attempt

    style Start fill:#E8F4FD,stroke:#5DADE2,stroke-width:2px
    style Done fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style Fail fill:#F4CCCC,stroke:#CC6666,stroke-width:2px
    style Wait fill:#FFF3E0,stroke:#FFB74D,stroke-width:2px
    style Attempt fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style Success fill:#E8F4FD,stroke:#5DADE2,stroke-width:2px
    style CheckRetries fill:#FFF3E0,stroke:#FFB74D,stroke-width:2px
```

## Backoff Strategies

| Strategy | Description |
|----------|-------------|
| **Fixed** | Wait a constant delay between retries |
| **Linear** | Increase delay by a fixed amount each time |
| **Exponential** | Double the delay after each attempt (most common) |
| **Jitter** | Add randomness to prevent thundering herd |

```mermaid
xychart-beta
    title "Backoff Strategy Comparison (delay in ms)"
    x-axis "Retry Attempt" [1, 2, 3, 4, 5, 6]
    y-axis "Delay (ms)" 0 --> 5000
    line "Fixed" [1000, 1000, 1000, 1000, 1000, 1000]
    line "Linear" [1000, 2000, 3000, 4000, 5000, 6000]
    line "Exponential" [1000, 2000, 4000, 8000, 16000, 32000]
```

## When NOT to Retry

- Non-idempotent operations without deduplication
- Authentication failures
- Client-side validation errors
- Timeouts where the request may have already succeeded

## See Also

- [Circuit Breaker](circuit-breaker.md)
- [Bulkhead](bulkhead.md)
