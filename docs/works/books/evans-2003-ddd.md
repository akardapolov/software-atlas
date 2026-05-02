# Domain-Driven Design: Tackling Complexity in the Heart of Software

| | |
|---|---|
| **Author** | Eric Evans |
| **Year** | 2003 |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | Domain modelling, architecture, complexity management |
| **ISBN** | 978-0-321-12521-7 |

## Summary

Evans argued that the most critical complexity in software is not
technical but **domain** complexity — the inherent difficulty of the
business problem being solved. He proposed a set of practices for
building software whose structure mirrors the domain, enabling
developers and domain experts to collaborate using a shared language.

DDD operates at two levels: **strategic design** (system boundaries,
team organisation) and **tactical design** (code-level patterns for
modelling domain concepts).

## Key Ideas

### 1. The Domain Is the Heart

Most software projects fail not because of technology but because of
a misunderstanding of the domain. Evans's core thesis:

> "The heart of software is its ability to solve domain-related
> problems for its user."

Therefore: the domain model — not the database schema, not the UI,
not the framework — should drive the design of the software.

### 2. Ubiquitous Language

The most impactful idea in the book. Developers and domain experts
must share a **single language** for discussing the domain:

- The same terms appear in conversations, documentation, AND code
- If the domain expert says "policy," the code has a `Policy` class
- If the code uses a term the expert doesn't recognise, the model is wrong
- Language inconsistencies reveal model weaknesses

```java
// BAD: technical terms that mean nothing to domain experts
class DataProcessor {
    void processRecord(Record r) { ... }
}

// GOOD: domain language
class ClaimAssessor {
    Assessment assess(InsuranceClaim claim) { ... }
}
```

### 3. Strategic Design

**Bounded Context** — the most architecturally significant pattern.
A bounded context is a boundary within which a domain model is
consistent and meaningful:

- Different contexts may have different models of the same concept
- "Customer" in Sales is different from "Customer" in Shipping
- Each context has its own ubiquitous language
- Contexts communicate through well-defined interfaces

**Context Map** — a diagram showing how bounded contexts relate:

| Relationship | Description |
|-------------|-------------|
| **Shared Kernel** | Two contexts share a subset of the model |
| **Customer/Supplier** | Upstream context serves downstream |
| **Conformist** | Downstream adopts upstream's model as-is |
| **Anti-Corruption Layer** | Downstream translates upstream's model |
| **Open Host Service** | Context publishes a protocol for others |
| **Published Language** | A documented, shared data format |
| **Separate Ways** | Contexts have no integration |

### 4. Tactical Design

Patterns for implementing the model in code:

**Entity** — an object defined by its **identity**, not its attributes.
A customer with a changed address is still the same customer.

**Value Object** — an object defined by its **attributes**. Two `Money`
objects with the same amount and currency are interchangeable.
Value objects are immutable.

**Aggregate** — a cluster of objects treated as a unit for data changes.
One entity is the **aggregate root** — the only entry point for
external references.

```java
// Order is the aggregate root
// LineItems exist only within an Order
public class Order {                     // aggregate root
    private OrderId id;                  // identity
    private List<LineItem> items;        // internal entities
    private Money total;                 // value object

    public void addItem(Product p, Quantity qty) {
        // Business rule: max 50 items per order
        if (items.size() >= 50)
            throw new OrderLimitExceeded();
        items.add(new LineItem(p, qty));
        recalculate();
    }
}
```

**Repository** — provides a collection-like interface for accessing
aggregates. Hides the persistence mechanism.

**Domain Service** — business logic that doesn't naturally belong to
any entity or value object.

**Domain Event** — a record of something that happened in the domain.
Immutable. Used to communicate between aggregates and contexts.

### 5. Model-Driven Design

The model is not a diagram that sits on a shelf — it IS the code:

> "If the design, or some central part of it, does not map to the
> domain model, that model is of little value."

The code must reflect the model. If the model evolves, the code must
evolve. If the code diverges from the model, the model is dead.

### 6. Distillation

Large domains need focus. Evans introduced techniques for finding the
**Core Domain** — the part that gives the business its competitive
advantage — and distinguishing it from:

- **Supporting Subdomains** — necessary but not differentiating
- **Generic Subdomains** — commodity (use off-the-shelf solutions)

Invest modelling effort in the core domain. Don't gold-plate generic
subdomains.

## Historical Context

### The Problem in 2003

Enterprise software in 2003 was dominated by:

- **Data-centric design** — start with the database schema, build up
- **Anaemic domain models** — classes with only getters/setters, logic in "services"
- **Framework-driven design** — architecture dictated by EJB, not by the domain

Evans's book was a corrective: put the domain first, make the domain
model the central artefact, and treat technology as a supporting concern.

### Intellectual Roots

| Influence | Contribution to DDD |
|-----------|-------------------|
| Parnas — Information Hiding | Bounded contexts hide domain decisions |
| Kay — OOP as modelling | Objects model real-world domain concepts |
| Cockburn — Hexagonal Architecture | Domain at the centre, technology at the edges |
| Brooks — Essential Complexity | Domain complexity is essential; DDD manages it |

## Impact and Legacy

### The DDD Community

DDD spawned a large community, annual conferences (DDD Europe), and
follow-up books:

- Vernon — *Implementing Domain-Driven Design* (2013) — practical guide
- Vernon — *Domain-Driven Design Distilled* (2016) — concise introduction
- [Wlaschin — *Domain Modeling Made Functional*](wlaschin-2018-dmf.md) (2018) — DDD with FP

### Microservices

DDD's **bounded contexts** became the primary tool for decomposing
monoliths into microservices. Sam Newman's *Building Microservices*
(2015) explicitly builds on DDD.

### Event Sourcing and CQRS

DDD's **domain events** evolved into:

- **Event Sourcing** — store events as the source of truth, derive state
- **CQRS** — separate read and write models
- **Event-Driven Architecture** — systems communicating through events

### Functional DDD

Wlaschin showed that DDD's tactical patterns map naturally to FP:

| DDD concept | OOP implementation | FP implementation |
|-------------|-------------------|-------------------|
| Entity | Class with ID field | Record with ID + functions |
| Value Object | Immutable class | Any immutable type |
| Aggregate | Mutable object graph | Immutable data + pure functions |
| Domain Event | Event class | Immutable record / ADT variant |
| Repository | Interface + implementation | Module with functions |

## Connections

- **Builds on:** [Parnas — Information Hiding](../papers/parnas-1972-modules.md) ·
  [Cockburn — Hexagonal Architecture](../../authors/alistair-cockburn.md) ·
  [Brooks — No Silver Bullet](brooks-1975-mmm.md) ·
  [GoF — Design Patterns](gof-1994-design-patterns.md)
- **Led to:** [Newman — Microservices (2015)](../../authors/sam-newman.md) ·
  [Wlaschin — Domain Modeling FP (2018)](wlaschin-2018-dmf.md) ·
  Vernon — IDDD (2013) · Event Sourcing / CQRS
- **Author:** [Eric Evans](../../authors/eric-evans.md)
- **Related topic:** [Architecture & Modularity](../../topics/architecture/index.md) ·
  [OOP & Design](../../topics/design/index.md)
