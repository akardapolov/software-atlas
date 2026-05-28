# Circuit Breaker

**Category:** Resilience  
**Source:** Michael Nygard — *Release It!* (2007)

> Prevent cascading failures by stopping requests to a failing service until it recovers.

When a remote service is failing or slow, every caller waits, threads block, and the entire system can seize up. The Circuit Breaker monitors failures and, when a threshold is exceeded, "opens" the circuit — failing fast instead of waiting.

## State Diagram

```mermaid
stateDiagram-v2
    [*] --> Closed

    Closed --> Open: failures exceed threshold
    Closed --> Closed: request succeeds

    Open --> HalfOpen: timeout expires
    Open --> Open: request rejected (fail fast)

    HalfOpen --> Closed: probe succeeds
    HalfOpen --> Open: probe fails

    note right of Closed
        Normal operation:
        requests pass through
    end note

    note right of Open
        Tripped state:
        requests immediately rejected
    end note

    note left of HalfOpen
        Testing state:
        limited probe requests allowed
    end note

    style Closed fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px,color:#333
    style Open fill:#F4CCCC,stroke:#CC6666,stroke-width:2px,color:#333
    style HalfOpen fill:#FFF3E0,stroke:#FFB74D,stroke-width:2px,color:#333
```

## Strengths

- Prevents cascading failures
- Allows failing services time to recover
- Fails fast, preserving caller resources

## Weaknesses

- Requires careful tuning of thresholds and timeouts
- Can hide transient issues if not monitored
- Adds complexity to testing

→ [Michael Nygard](../../authors/michael-nygard.md)

## See Also

- [Retry Pattern](retry-pattern.md)
- [Bulkhead](bulkhead.md)
- [Saga Pattern](saga-pattern.md)
