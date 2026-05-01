# Bulkhead

**Category:** Resilience  
**Source:** Michael Nygard — *Release It!* (2007)

> Isolate failures by partitioning resources so that a failure in one partition does not exhaust resources for others.

Named after ship bulkheads — watertight compartments that prevent a breach in one section from flooding the entire vessel. In software, bulkheads partition thread pools, connection pools, or memory so that a runaway consumer cannot starve others.

```
Without bulkhead:
┌─────────────────────────────────────┐
│         Shared Thread Pool          │
│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐  │
│  │ A   │ │ B   │ │ C   │ │ D   │  │
│  │(slow)│ │     │ │     │ │     │  │
│  └─────┘ └─────┘ └─────┘ └─────┘  │
│  ← B, C, D starved because A hogs │
└─────────────────────────────────────┘

With bulkhead:
┌─────────┐ ┌─────────┐ ┌─────────┐
│ Pool A  │ │ Pool B  │ │ Pool C  │
│ ┌─────┐ │ │ ┌─────┐ │ │ ┌─────┐ │
│ │ A   │ │ │ │ B   │ │ │ │ C   │ │
│ │(slow)│ │ │ │     │ │ │ │     │ │
│ └─────┘ │ │ └─────┘ │ │ └─────┘ │
└─────────┘ └─────────┘ └─────────┘
 ← B and C unaffected by A
```

## See Also

- [Circuit Breaker](circuit-breaker.md)
- [Retry Pattern](retry-pattern.md)
