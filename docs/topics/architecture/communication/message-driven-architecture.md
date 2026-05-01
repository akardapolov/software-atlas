# Message-Driven Architecture

**Category:** Communication  
**Source:** Industry pattern; Hohpe & Woolf — *Enterprise Integration Patterns* (2003)

> Components communicate by sending messages through channels, decoupling sender and receiver in time and space.

In a message-driven system, components send **messages** to **channels** (queues or topics). The sender does not know who will process the message or when. A message broker stores and forwards messages, enabling temporal and spatial decoupling.

**Message types:**

| Type | Purpose |
|------|---------|
| **Command** | Request to perform an action ("do X") |
| **Event** | Notification that something happened ("X happened") |
| **Document** | Transfer of data with no implied action ("here is X") |

**Strengths:**

- Temporal decoupling — sender and receiver need not be available simultaneously
- Load levelling — queue absorbs traffic spikes
- Geographic distribution — components can be anywhere
- Natural retry and dead-letter handling

**Weaknesses:**

- Added infrastructure complexity (broker, monitoring, ops)
- Message ordering and exactly-once delivery are hard problems
- Debugging requires distributed tracing

→ [Gregor Hohpe](../../authors/gregor-hohpe.md) ·
[Enterprise Integration Patterns](../../works/books/hohpe-woolf-2003-eip.md)

## See Also

- [Event-Driven Architecture](event-driven-architecture.md)
- [Publish-Subscribe](publish-subscribe.md)
- [Saga Pattern](../resilience/saga-pattern.md)
