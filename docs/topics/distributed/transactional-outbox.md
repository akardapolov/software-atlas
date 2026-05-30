# Transactional Outbox

**Category:** Distributed Systems / Reliability  
**Source:** Industry pattern, widely adopted in microservices (2010s)

> Guarantee that events are never lost when state changes and messaging must happen together.

The fundamental problem in distributed systems: saving data to a database and sending a message to a broker are two separate operations with no atomic guarantee. If the database commit succeeds but the network call to the broker fails, the system is inconsistent — downstream services never learn about the change.

The **Transactional Outbox** pattern solves this by making the event part of the same local database transaction as the business write. A separate **Message Relay** then reads the outbox table and publishes events to the message broker.

---

## The Big Picture

```mermaid
flowchart LR
    subgraph ServiceA["Service A (Sender)"]
        direction TB
        App["Application Logic"]
        subgraph TXA["DB Transaction"]
            direction TB
            Biz[(Business Table)]
            Outbox[(Outbox Table)]
        end
    end

    subgraph Relay["Message Relay"]
        Poller["Polling Publisher / CDC"]
    end

    subgraph Broker["Message Broker"]
        Topic["Topic / Partition"]
    end

    App -->|1. save entity| Biz
    App -->|1. same TX: insert event| Outbox
    Outbox -->|2. read PENDING| Poller
    Poller -->|3. publish| Topic
    Poller -.->|4. mark SENT| Outbox

    style TXA fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px
    style Broker fill:#fff3e0,stroke:#ef6c00,stroke-width:2px
    style Relay fill:#e3f2fd,stroke:#1565c0,stroke-width:2px
```

**Flow:**
1. Business logic writes to the business table **and** inserts an event into the outbox table — inside **one local transaction**.
2. The relay reads pending events from the outbox.
3. The relay publishes each event to the broker.
4. The relay marks the event as sent (or deletes it).

---

## The Problem

Without an outbox, a service typically does this:

```
1. BEGIN DB transaction
2. INSERT INTO orders ...
3. COMMIT
4. SEND event to Kafka        ← network call, can fail
```

If step 4 fails, the order exists in the database but no other service knows about it. The system is now inconsistent.

**Reverse order is equally broken:**

```
1. SEND event to Kafka        ← succeeds
2. BEGIN DB transaction
3. INSERT INTO orders ...     ← fails and rolls back
```

Now downstream services processed an event for an order that was never created.

Neither ordering is safe. The only safe approach is to make the event emission part of the same atomic unit as the state change — the local database transaction.

---

## The Pattern

The outbox table is a regular database table that lives alongside business tables:

```sql
CREATE TABLE outbox (
    id           SERIAL PRIMARY KEY,
    event_id     UUID NOT NULL,
    event_type   TEXT NOT NULL,
    aggregate_id TEXT NOT NULL,
    payload      JSONB NOT NULL,
    created_at   TIMESTAMP DEFAULT NOW(),
    status       TEXT DEFAULT 'PENDING'   -- PENDING | SENT
);
```

**Atomic write:**

```sql
BEGIN TRANSACTION;

INSERT INTO orders (id, user_id, total)
VALUES ('order_123', 'user_99', 1500);

INSERT INTO outbox (event_id, event_type, aggregate_id, payload, status)
VALUES (
    'event_abc',
    'OrderCreated',
    'order_123',
    '{"id": "order_123", "total": 1500}',
    'PENDING'
);

COMMIT;
```

Both inserts succeed or both fail. There is no intermediate inconsistent state.

---

## The Message Relay

The relay is a separate process responsible for reading the outbox and publishing to the broker. Two main approaches:

### Polling Publisher

The relay periodically queries the database:

```sql
SELECT * FROM outbox WHERE status = 'PENDING' LIMIT 100;
```

After successful publication, it updates the status:

```sql
UPDATE outbox SET status = 'SENT' WHERE id = ?;
-- or: DELETE FROM outbox WHERE id = ?;
```

| Strength | Weakness |
|----------|----------|
| Simple to implement | Adds query load to the database |
| Works with any database | Latency bounded by polling interval |
| No extra infrastructure | Can miss events if polling stops |

### Change Data Capture (CDC)

A more production-grade approach. Tools like **Debezium** read the database transaction log (WAL in PostgreSQL, binlog in MySQL) and stream changes directly to Kafka.

| Strength | Weakness |
|----------|----------|
| No polling load on the database | Requires log-level access |
| Near-real-time latency | Additional infrastructure (Debezium, Kafka Connect) |
| Captures all changes, including deletions | More complex to operate |

**When to choose which:**

| Context | Recommendation |
|---------|---------------|
| New project, small scale | Polling Publisher |
| High throughput, low latency | CDC with Debezium |
| Existing PostgreSQL / MySQL | Either works; CDC is preferred for production |
| Cloud-managed databases (RDS, Cloud SQL) | Polling (CDC requires custom config) |

---

## At-Least-Once Delivery

The outbox pattern guarantees **at-least-once** delivery, not exactly-once. Duplicates are expected and must be handled downstream.

**Where duplicates originate:**

1. **Relay crash after publish, before mark:** The relay publishes an event to Kafka, Kafka acknowledges, but the relay crashes before updating the outbox row to `SENT`. On restart, the relay sees the same `PENDING` row and publishes again.

2. **Database rollback on mark:** The relay publishes, then attempts to mark `SENT`, but the `UPDATE` fails or the transaction rolls back. The row remains `PENDING`.

> **Better a duplicate event than a lost event.** This is the core trade-off of the pattern.

---

## Producer Configuration

When using Kafka as the broker, the producer (relay) should be configured for durability:

| Setting | Value | Why |
|---------|-------|-----|
| `acks` | `all` (or `-1`) | Wait for all in-sync replicas to acknowledge |
| `enable.idempotence` | `true` | Prevents duplicates caused by producer retries (network-level only) |
| `retries` | `> 0` | Retry on transient failures |
| `max.in.flight.requests` | `5` (default with idempotence) | Allow pipelining without reordering |

`enable.idempotence=true` prevents the *producer* from creating duplicates due to its own retries, but it does not prevent duplicates caused by the relay reading the same outbox row twice. That is why the **Inbox** pattern on the consumer side is essential.

---

## Broker Portability

The outbox pattern is not tied to Kafka. It works with any message broker:

| Broker | Notes |
|--------|-------|
| **Kafka** | Event streaming, durable log, replay capability |
| **RabbitMQ** | Classical queues, explicit ack/nack, routing flexibility |
| **NATS** | Lightweight, JetStream for persistence |
| **Pulsar** | Tiered storage, geo-replication |
| **AWS SQS** | Managed, simple, at-least-once by design |
| **Redis Streams** | In-memory, good for low-latency scenarios |

### Kafka vs RabbitMQ for Outbox

| Criterion | Kafka | RabbitMQ |
|-----------|-------|----------|
| **Model** | Event streaming (durable log) | Message queue (remove on ack) |
| **Persistence** | Retains history (configurable) | Removes on consumer ack |
| **Throughput** | Millions of messages/sec | Hundreds of thousands/sec |
| **Routing** | Simple (key → partition) | Flexible (exchanges, routing keys) |
| **Replay** | Yes — reset offset and re-read | No — message is gone after ack |
| **Best for** | Event-driven systems, analytics, logs | Task queues, RPC, job scheduling |

---

## The Philosophy

> This architecture does not try to avoid duplicates in the network. It makes their occurrence expected, controlled, and absolutely safe.

In distributed systems, network partitions and process crashes are inevitable (CAP theorem). Attempting to build a network with 100% exactly-once guarantees is prohibitively expensive and practically impossible under failure.

Instead, the pragmatic approach:
1. **At-least-once at the transport layer** — accept that a message may be sent 2–3 times during failure recovery, but never lost.
2. **Idempotency at the receiver** — make the consumer duplicate-safe. A duplicate arrives? The database silently ignores it. A new message arrives? Process it normally.

---

## See Also

- [Leased Outbox](leased-outbox.md) — high-throughput, non-transactional variant for NoSQL
- [Transactional Inbox](transactional-inbox.md) — the receiver-side counterpart
- [Idempotency](idempotency.md) — the property that makes duplicate events safe
- [Event-Driven Architecture](../architecture/communication/event-driven-architecture.md) — where outbox is most commonly applied
- [Saga Pattern](../architecture/resilience/saga-pattern.md) — long-running transactions across services
- [Message-Driven Architecture](../architecture/communication/message-driven-architecture.md)

## Further Reading

- Kleppmann — *Designing Data-Intensive Applications* (2017), Chapter 11 — "Stream Processing" and the outbox pattern
- Helland — *Life Beyond Distributed Transactions* (2007) — why to avoid 2PC
- Debezium documentation — CDC-based outbox implementation
- "The Outbox Pattern" — Chris Richardson, Microservices.io

## Related Topics

- [Distributed Systems](index.md) — consistency models, CAP theorem, consensus
- [Databases](../databases/index.md) — transactions, isolation levels
- [Architecture & Modularity](../architecture/index.md) — microservices, system boundaries
- [Containers & Orchestration](../containers/index.md) — runtime substrate for relays and consumers
