# Saga Pattern

**Category:** Resilience  
**Source:** Hector Garcia-Molina & Kenneth Salem — *Sagas* (1987); popularised in microservices by Chris Richardson

> Manage long-running transactions by breaking them into a sequence of local transactions with compensating actions.

In distributed systems, ACID transactions across services are impractical. A saga splits a business transaction into a sequence of local transactions, each updating one service. If a step fails, compensating transactions undo the completed steps.

## Two Approaches

```mermaid
flowchart LR
    subgraph Choreography["🎭 Choreography"]
        direction TB
        O1["Order Service\nemit event"] -->|OrderCreated| P1["Payment Service\nemit event"]
        P1 -->|PaymentProcessed| I1["Inventory Service"]
    end

    subgraph Orchestration["🎯 Orchestration"]
        direction TB
        Orch["Saga Orchestrator"]
        Orch -->|"1. Create Order"| O2["Order Service"]
        Orch -->|"2. Charge Payment"| P2["Payment Service"]
        Orch -->|"3. Reserve Inventory"| I2["Inventory Service"]
    end

    style Choreography fill:#E8F4FD,stroke:#5DADE2,stroke-width:2px
    style Orchestration fill:#D9EAD3,stroke:#6AA84F,stroke-width:2px
    style O1 fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style P1 fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style I1 fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style O2 fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style P2 fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style I2 fill:#F5F5F5,stroke:#999999,stroke-width:2px
    style Orch fill:#FFF3E0,stroke:#FFB74D,stroke-width:2px
```

| Approach | Description |
|----------|-------------|
| **Choreography** | Each service completes its local transaction and emits an event triggering the next service |
| **Orchestration** | A central orchestrator directs each service when to execute its local transaction |

## Example — Order Saga (with Compensation)

```mermaid
sequenceDiagram
    participant Orch as 🎯 Orchestrator
    participant Order as 📦 Order Service
    participant Payment as 💳 Payment Service
    participant Inventory as 📊 Inventory Service

    rect rgb(232, 244, 253)
        note over Orch, Inventory: Happy Path ✅
        Orch->>Order: 1. Create Order
        Order-->>Orch: ✅ Order Created
        Orch->>Payment: 2. Charge Payment
        Payment-->>Orch: ✅ Payment Charged
        Orch->>Inventory: 3. Reserve Inventory
        Inventory-->>Orch: ✅ Inventory Reserved
    end

    rect rgb(253, 242, 233)
        note over Orch, Inventory: Failure Path with Compensation ❌
        Orch->>Order: 1. Create Order
        Order-->>Orch: ✅ Order Created
        Orch->>Payment: 2. Charge Payment
        Payment-->>Orch: ✅ Payment Charged
        Orch->>Inventory: 3. Reserve Inventory
        Inventory-->>Orch: ❌ Out of Stock
        Note over Orch: 💡 Saga compensates...
        Orch->>Payment: 4. Refund Payment (compensation)
        Payment-->>Orch: ✅ Refunded
        Orch->>Order: 5. Cancel Order (compensation)
        Order-->>Orch: ✅ Cancelled
    end
```

## See Also

- [Event-Driven Architecture](../communication/event-driven-architecture.md)
- [Event Sourcing](../data/event-sourcing.md)
- [CQRS](../data/cqrs.md)
- [Circuit Breaker](circuit-breaker.md)
