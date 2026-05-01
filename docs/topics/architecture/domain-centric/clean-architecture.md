# Clean Architecture

**Category:** Domain-Centric  
**Source:** Robert C. Martin — *Clean Architecture* (2012)

> Concentric rings with the Dependency Rule: source code dependencies point inward only.

Robert C. Martin synthesised hexagonal, onion, and DCI architectures into **Clean Architecture** — concentric rings with the **Dependency Rule**: source code dependencies point **inward** only.

```
┌──────────────────────────────────────────────────┐
│  Frameworks & Drivers                            │  outermost: DB, Web, UI
│  ┌────────────────────────────────────────────┐  │
│  │  Interface Adapters                        │  │  controllers, presenters,
│  │  ┌──────────────────────────────────────┐  │  │  gateways
│  │  │  Use Cases                           │  │  │  application-specific
│  │  │  ┌────────────────────────────────┐  │  │  │  business rules
│  │  │  │   Entities                     │  │  │  │  enterprise-wide
│  │  │  │  (domain model)               │  │  │  │  business rules
│  │  │  └────────────────────────────────┘  │  │  │
│  │  └──────────────────────────────────────┘  │  │
│  └────────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
                 ── dependencies point inward ──▶
```

→ [Robert C. Martin](../../authors/robert-c-martin.md)

## See Also

- [Layered Architecture](layered-architecture.md)
- [Hexagonal Architecture](hexagonal-architecture.md)
- [Onion Architecture](onion-architecture.md)
