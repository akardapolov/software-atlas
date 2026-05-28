# Bulkhead

**Category:** Resilience  
**Source:** Michael Nygard — *Release It!* (2007)

> Isolate failures by partitioning resources so that a failure in one partition does not exhaust resources for others.

Named after ship bulkheads — watertight compartments that prevent a breach in one section from flooding the entire vessel. In software, bulkheads partition thread pools, connection pools, or memory so that a runaway consumer cannot starve others.

## Without Bulkhead — Problem

```mermaid
flowchart TD
    subgraph pool["Shared Thread Pool"]
        direction LR
        A["🔴 A (slow)"]
        B["🟢 B"]
        C["🟢 C"]
        D["🟢 D"]
    end

    A -.- starved["⚠️ B, C, D starved because A hogs resources"]

    style pool fill:#FDF2E9,stroke:#E8A87C,stroke-width:2px
    style A fill:#F4CCCC,stroke:#CC6666,stroke-width:2px
    style B fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style C fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style D fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style starved fill:#FFF3E0,stroke:#FFB74D,stroke-width:1px,stroke-dasharray:5 5
```

## With Bulkhead — Solution

```mermaid
flowchart LR
    subgraph PA["Pool A"]
        A["🔴 A (slow)"]
    end
    subgraph PB["Pool B"]
        B["🟢 B"]
    end
    subgraph PC["Pool C"]
        C["🟢 C"]
    end

    A -.- ok["✅ B and C unaffected by A"]

    style PA fill:#FDF2E9,stroke:#E8A87C,stroke-width:2px
    style PB fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style PC fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style A fill:#F4CCCC,stroke:#CC6666,stroke-width:2px
    style B fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style C fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style ok fill:#E8F8E8,stroke:#6AA84F,stroke-width:1px,stroke-dasharray:5 5
```

## See Also

- [Circuit Breaker](circuit-breaker.md)
- [Retry Pattern](retry-pattern.md)
