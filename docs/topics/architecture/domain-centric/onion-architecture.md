# Onion Architecture

**Category:** Domain-Centric  
**Source:** Jeffrey Palermo — *Onion Architecture* (2008)

> Place the domain model at the centre with all dependencies pointing inward.

Jeffrey Palermo introduced Onion Architecture as a refinement of the layered idea, with the domain model at the centre and all dependencies pointing inward. Unlike classic layers, the infrastructure sits on the outside and the domain has no dependency on it.

```
┌────────────────────────────────────────────────┐
│  Infrastructure / UI                           │
│  ┌──────────────────────────────────────────┐  │
│  │  Application Services                    │  │
│  │  ┌────────────────────────────────────┐  │  │
│  │  │  Domain Services                   │  │  │
│  │  │  ┌──────────────────────────────┐  │  │  │
│  │  │  │   Domain Model               │  │  │  │
│  │  │  │  (entities, value objects)   │  │  │  │
│  │  │  └──────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└────────────────────────────────────────────────┘
              ◀── dependencies point inward ──
```

Onion Architecture directly influenced Clean Architecture (2012). The primary difference is emphasis: Onion stresses layering around the domain model; Clean Architecture adds explicit naming of Use Cases and Interface Adapters as distinct rings.

**Strengths:**

- Domain model has zero infrastructure dependencies
- Application services orchestrate use cases without knowing storage details
- Clear separation between domain logic and application coordination

**Weaknesses:**

- Less prescriptive than Clean Architecture about naming and ring count
- Can be interpreted differently across teams

## See Also

- [Layered Architecture](layered-architecture.md)
- [Hexagonal Architecture](hexagonal-architecture.md)
- [Clean Architecture](clean-architecture.md)
