# Publish-Subscribe (Pub/Sub)

**Category:** Communication  
**Source:** Industry pattern; widely used in messaging systems and distributed computing

> Publishers send messages to topics without knowledge of subscribers; subscribers receive messages matching their interests.

Pub/Sub is a messaging pattern where senders (**publishers**) do not send messages directly to receivers. Instead, messages are categorised into **topics** (or channels), and receivers (**subscribers**) express interest in one or more topics. The message broker delivers a copy of each message to all interested subscribers.

**Key characteristics:**

- **One-to-many** communication — one message can reach many subscribers
- **Decoupling** — publishers and subscribers know nothing about each other
- **Dynamic topology** — subscribers can join or leave without affecting publishers

**Common implementations:**

- Apache Kafka (topic-based)
- RabbitMQ (exchange-based)
- Redis Pub/Sub
- Google Cloud Pub/Sub, AWS SNS

## See Also

- [Event-Driven Architecture](event-driven-architecture.md)
- [Message-Driven Architecture](message-driven-architecture.md)
- [Observer](../../design/behavioral/observer.md)
