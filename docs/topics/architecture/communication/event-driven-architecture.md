# Event-Driven Architecture

**Category:** Communication  
**Source:** Industry pattern, popularised by enterprise integration (2000s)

> Components communicate by producing and consuming events, enabling loose coupling and scalability.

In an event-driven system, components do not call each other directly. Instead, they publish events to an event bus or broker, and interested components subscribe to those events and react asynchronously.

**Core concepts:**

- **Event** — an immutable record that something happened (past tense)
- **Producer / Publisher** — emits events when state changes
- **Consumer / Subscriber** — reacts to events of interest
- **Event Bus / Broker** — routes events from producers to consumers (Kafka, RabbitMQ, NATS)

**Common patterns within EDA:**

| Pattern | Description |
|---------|-------------|
| **Event Notification** | Lightweight event saying "something happened"; consumers fetch details |
| **Event-Carried State Transfer** | Event carries full state so consumers don't need to query back |
| **Event Sourcing** | State is derived from the full log of events (see [Event Sourcing](../data/event-sourcing.md)) |

**Strengths:**

- Loose coupling between producers and consumers
- Natural scalability (consumers can be added independently)
- Resilience (consumers can catch up after downtime)
- Extensibility (new consumers added without changing producers)

**Weaknesses:**

- Eventual consistency
- Harder to reason about global system state
- Need for schema evolution and versioning
- Debugging and tracing become distributed concerns

## See Also

- [Message-Driven Architecture](message-driven-architecture.md)
- [Publish-Subscribe](publish-subscribe.md)
- [Event Sourcing](../data/event-sourcing.md)
- [CQRS](../data/cqrs.md)
- [Saga Pattern](../resilience/saga-pattern.md)
