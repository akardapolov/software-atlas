# Hexagonal Architecture (Ports & Adapters)

**Category:** Domain-Centric  
**Source:** Alistair Cockburn — *Hexagonal Architecture* (2005)

> Put the domain at the centre, depending on nothing. Everything else — databases, UIs, external services — connects through ports and adapters.

Cockburn inverted the dependency direction. The **domain** is at the centre, depending on nothing. Everything else — databases, UIs, external services — connects through **ports** (interfaces defined by the domain) and **adapters** (implementations provided by infrastructure):

```
                    ┌──────────────────────────┐
                    │                          │
  Driving           │        Domain            │         Driven
  Adapters          │      (pure logic)        │         Adapters
                    │                          │
  ┌─────────┐       │  ┌────────────────────┐  │       ┌──────────┐
  │  REST   │──────▶│  │  «port»            │  │──────▶│  DB      │
  │  API    │       │  │  (interface owned  │  │       │  Adapter │
  └─────────┘       │  │   by the domain)   │  │       └──────────┘
                    │  └────────────────────┘  │
  ┌─────────┐       │  ┌────────────────────┐  │       ┌──────────┐
  │  CLI    │──────▶│  │  «port»            │  │──────▶│  Email   │
  └─────────┘       │  └────────────────────┘  │       │  Adapter │
                    │                          │       └──────────┘
                    └──────────────────────────┘

  Dependency direction:  Adapter ──▶ Port  ◀── Domain
  (adapters depend on ports; domain defines ports; nothing depends on adapters)
```

**Key insight:** The domain defines **ports** (interfaces it needs). Adapters implement those ports. The domain never mentions databases, HTTP, or any technology — it speaks only in domain terms.

**Strengths:**

- Domain logic is technology-independent
- Easy to test (swap real adapters for test doubles)
- Easy to change infrastructure (swap PostgreSQL for MongoDB by writing a new adapter)
- Domain drives design, not database schema

**Weaknesses:**

- More indirection (ports and adapters add code)
- Requires discipline to keep the domain pure
- Over-engineering for simple CRUD applications

→ [Alistair Cockburn](../../authors/alistair-cockburn.md)

## See Also

- [Layered Architecture](layered-architecture.md)
- [Onion Architecture](onion-architecture.md)
- [Clean Architecture](clean-architecture.md)
