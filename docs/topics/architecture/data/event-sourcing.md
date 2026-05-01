# Event Sourcing

**Category:** Data  
**Source:** Industry pattern, documented by Martin Fowler (2005)

> Store the full sequence of events that led to the current state, rather than storing the state itself.

In event sourcing, the **event store** is the source of truth. The current state of an entity is derived by replaying all events that have affected it. Events are append-only, immutable, and ordered.

```
┌─────────────────────────────────────────┐
│           Event Store                   │
│  ┌─────────────────────────────────┐    │
│  │ OrderCreated  (id=42, items=...)│    │
│  │ OrderSubmitted (id=42)          │    │
│  │ PaymentReceived (id=42, amt=...)│    │
│  │ OrderShipped (id=42)            │    │
│  └─────────────────────────────────┘    │
│                ↓                        │
│         Replay / Project                │
│                ↓                        │
│      ┌───────────────┐                  │
│      │ Current State │                  │
│      │  Order #42    │                  │
│      └───────────────┘                  │
└─────────────────────────────────────────┘
```

**Strengths:**

- Complete audit trail of how state evolved
- Ability to reconstruct past states
- Temporal queries ("what was the state at time T?")
- Natural fit with [Event-Driven Architecture](../communication/event-driven-architecture.md)

**Weaknesses:**

- Event schema evolution is hard
- Replaying large event streams is slow (requires snapshots)
- Debugging requires understanding event sequences
- Not all domains fit the event-centric model

## See Also

- [CQRS](cqrs.md)
- [Event-Driven Architecture](../communication/event-driven-architecture.md)
- [Saga Pattern](../resilience/saga-pattern.md)
