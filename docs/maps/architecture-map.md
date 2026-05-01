# Architecture Map

How software architecture patterns evolved.

## Architecture Evolution

```mermaid
flowchart TD
    subgraph Era1["🏛 Monolithic Era"]
        Monolith["Monolith<br/>Single deployable"]
        Layered["Layered Architecture<br/>UI → Business → Data"]
    end

    subgraph Era2["🔌 Modular Era"]
        Hexagonal["Hexagonal / Ports & Adapters<br/>Cockburn, 2005"]
        Clean["Clean Architecture<br/>Martin, 2012"]
        Onion["Onion Architecture<br/>Palermo, 2008"]
    end

    subgraph Era3["🌐 Distributed Era"]
        SOA["SOA<br/>2000s"]
        Microservices["Microservices<br/>2014"]
        EventDriven["Event-Driven<br/>Event Sourcing, CQRS"]
    end

    subgraph Era4["☁️ Cloud Native Era"]
        Serverless["Serverless / FaaS"]
        Mesh["Service Mesh"]
        CellBased["Cell-Based Architecture"]
    end

    Monolith --> Layered
    Layered --> Hexagonal
    Hexagonal --> Clean
    Hexagonal --> Onion
    Layered --> SOA
    SOA --> Microservices
    Microservices --> EventDriven
    Microservices --> Serverless
    Microservices --> Mesh

    style Era1 fill:#ffcdd2
    style Era2 fill:#fff9c4
    style Era3 fill:#c8e6c9
    style Era4 fill:#bbdefb
```

## Architecture Patterns Deep Dive

### 🏛 Layered Architecture

The traditional "N-tier" approach.

```mermaid
flowchart TD
    subgraph Layers
        Presentation["Presentation Layer<br/>UI, Controllers"]
        Business["Business Layer<br/>Services, Domain Logic"]
        Persistence["Persistence Layer<br/>Repositories, DAOs"]
        Database["Database"]
    end

    Presentation --> Business
    Business --> Persistence
    Persistence --> Database
```

| Aspect | Description |
|--------|-------------|
| **Key idea** | Horizontal separation of concerns |
| **Pros** | Simple, well-understood, good for CRUD |
| **Cons** | Database-centric, business logic scattered |
| **When to use** | Simple applications, form-based UIs |

### 🔌 Hexagonal Architecture (Ports & Adapters)

Domain at the center, infrastructure at the edges.

```mermaid
flowchart TD
    subgraph Adapters["Adapters (Infrastructure)"]
        WebAdapter["Web Adapter<br/>(REST, GraphQL)"]
        DBAdapter["DB Adapter<br/>(Postgres, Mongo)"]
        MsgAdapter["Messaging Adapter<br/>(Kafka, RabbitMQ)"]
    end

    subgraph Ports["Ports (Interfaces)"]
        InPort["Input Ports<br/>(Use Cases)"]
        OutPort["Output Ports<br/>(Repositories)"]
    end

    subgraph Core["Domain Core"]
        Domain["Domain Model<br/>Entities, Value Objects"]
        Services["Domain Services"]
    end

    WebAdapter --> InPort
    InPort --> Domain
    Domain --> OutPort
    OutPort --> DBAdapter
    Domain --> OutPort
    OutPort --> MsgAdapter

    style Core fill:#c8e6c9
    style Ports fill:#fff9c4
    style Adapters fill:#e1f5fe
```

| Aspect | Description |
|--------|-------------|
| **Key idea** | Domain doesn't depend on infrastructure |
| **Invented by** | Alistair Cockburn, 2005 |
| **Pros** | Testable core, pluggable adapters |
| **Cons** | More complex initially |
| **When to use** | Complex business logic, multiple integrations |

### 🌐 Microservices Architecture

```mermaid
flowchart LR
    subgraph Microservices
        subgraph ServiceA["Service A"]
            A_Domain["Domain Logic"]
            A_Data["Database A"]
        end

        subgraph ServiceB["Service B"]
            B_Domain["Domain Logic"]
            B_Data["Database B"]
        end

        subgraph ServiceC["Service C"]
            C_Domain["Domain Logic"]
            C_Data["Database C"]
        end

        API["API Gateway"]
        Events["Event Bus"]

        API --> A_Domain
        API --> B_Domain
        API --> C_Domain

        A_Domain <--> Events
        B_Domain <--> Events
        C_Domain <--> Events
    end

    style ServiceA fill:#c8e6c9
    style ServiceB fill:#fff9c4
    style ServiceC fill:#e1f5fe
```

| Aspect | Description |
|--------|-------------|
| **Key idea** | Deploy independently owned services |
| **Pros** | Independent deployment, polyglot, failure isolation |
| **Cons** | Distributed complexity, data consistency challenges |
| **When to use** | Large teams, independent lifecycles |

### 📨 Event-Driven Architecture

```mermaid
flowchart TD
    Producer["Producer<br/>(Service A)"] -->|"Event: OrderCreated"| EventBus["Event Bus<br/>(Kafka, RabbitMQ)"]
    EventBus --> Consumer1["Consumer 1<br/>(Email Service)"]
    EventBus --> Consumer2["Consumer 2<br/>(Analytics)"]
    EventBus --> Consumer3["Consumer 3<br/>(Inventory)"]

    style Producer fill:#ffcc80
    style EventBus fill:#c8e6c9
    style Consumer1 fill:#90caf9
    style Consumer2 fill:#90caf9
    style Consumer3 fill:#90caf9
```

| Aspect | Description |
|--------|-------------|
| **Key idea** | Services communicate via events |
| **Patterns** | Event Sourcing, CQRS, Saga |
| **Pros** | Loose coupling, async processing |
| **Cons** | Eventual consistency, debugging complexity |
| **When to use** | High throughput, distributed systems |

### ☁️ Serverless / FaaS

```mermaid
flowchart LR
    Trigger["API Gateway / Event Trigger"] -->|"Invoke"| Functions["Cloud Functions"]
    Functions -->|"Read/Write"| Managed["Managed Services<br/>(DB, Storage)"]
    Functions -->|"Monitor"| Logs["Logs & Metrics"]

    style Trigger fill:#fff9c4
    style Functions fill:#c8e6c9
    style Managed fill:#e1f5fe
```

| Aspect | Description |
|--------|-------------|
| **Key idea** | Execute code on demand, pay per execution |
| **Pros** | No server management, auto-scaling |
| **Cons** | Cold starts, vendor lock-in |
| **When to use** | Event-driven, sporadic workloads |

## Architecture Decision Framework

### Trade-offs Considerations

| Concern | Monolith | Modular | Microservices |
|---------|----------|---------|---------------|
| **Development Speed** | Fast initially, slows over time | Medium | Slower initially |
| **Deployment** | All-or-nothing | Better boundaries | Independent |
| **Scalability** | Scale the whole | Scale components | Scale services independently |
| **Data Consistency** | Strong via transactions | Strong | Eventual across services |
| **Team Size** | Small teams | Medium | Large teams |
| **Organizational Fit** | One team | Few teams | Many teams |

### When to Choose What

```mermaid
flowchart TD
    Start["What are your constraints?"] --> Size{"Team size?"}
    Size -->|"Small (<5)"| Domain{"Domain complexity?"}
    Size -->|"Medium (5-20)"| Modular["Modular Monolith"]
    Size -->|"Large (>20)"| Micro["Microservices"]

    Domain -->|"Simple"| Simple["Layered/Monolith"]
    Domain -->|"Complex"| Modular

    Micro -->|"Independent"| Decentralized["Bounded Contexts"]
    Micro -->|"High coupling"| Modular

    style Start fill:#e1f5fe
    style Simple fill:#c8e6c9
    style Modular fill:#fff9c4
    style Micro fill:#ffcdd2
```

## Architectural Principles

### Core Principles

1. **High Cohesion** — related functionality belongs together
2. **Low Coupling** — minimize dependencies between components
3. **Separation of Concerns** — each component has one responsibility
4. **Dependency Inversion** — depend on abstractions, not concretions
5. **Information Hiding** — hide implementation details

### Conway's Law in Architecture

> "Organizations which design systems are constrained to produce designs which are copies of the communication structures of these organizations."

**Implication:** Align architecture with team structure.

## Cross-References

- **Process:** [Process Map](./process-map.md) — how processes enable architectures
- **Languages:** [Languages Genealogy](./languages-genealogy.md) — languages that enable patterns
- **Individual Papers:**
  - [Parnas — Information Hiding](../works/papers/parnas-1972-modules.md)
  - [Brewer — CAP Theorem](../works/papers/brewer-2000-cap.md)
  - [Helland — Beyond Distributed Transactions](../works/papers/helland-2007-beyond-dt.md)
- **Individual Books:**
  - [Brooks — Mythical Man-Month](../works/books/brooks-1975-mmm.md)
  - [Newman — Building Microservices](../works/books/newman-2015-microservices.md)
