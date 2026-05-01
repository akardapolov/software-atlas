# CQRS (Command Query Responsibility Segregation)

**Category:** Data  
**Source:** Greg Young — popularised the pattern (2010)

> Separate the models for reading and writing data, allowing each to be optimised independently.

Traditional CRUD uses the same model for reads and writes. CQRS splits this into two:

- **Commands** — change state (writes); validated, business-rule-enriched
- **Queries** — read state; optimised for specific views, often denormalised

```
         ┌─────────────┐
         │   Client    │
         └──────┬──────┘
                │
       ┌────────┴────────┐
       ▼                 ▼
┌─────────────┐   ┌─────────────┐
│   Command   │   │    Query    │
│   Model     │   │    Model    │
│             │   │             │
│ - Validate  │   │ - Optimised │
│ - Enforce   │   │   views     │
│   rules     │   │ - Denormal. │
└──────┬──────┘   └──────┬──────┘
       │                 │
       ▼                 ▼
┌─────────────┐   ┌─────────────┐
│ Write DB    │   │  Read DB    │
│ (normalised)│   │ (denormal.) │
└─────────────┘   └─────────────┘
```

**Strengths:**

- Read models can be shaped precisely for UI needs
- Write models stay clean of query optimisation concerns
- Different databases can be used for reads and writes
- Natural fit with [Event Sourcing](event-sourcing.md)

**Weaknesses:**

- Added complexity (two models to maintain)
- Eventual consistency between read and write sides
- Risk of over-engineering for simple domains

## See Also

- [Event Sourcing](event-sourcing.md)
- [Event-Driven Architecture](../communication/event-driven-architecture.md)
- [Microservices](../structural/microservices.md)
