# Modular Monolith

**Category:** Structural  
**Source:** Industry pattern, popularised as pragmatic alternative to microservices (2010s)

> A single deployable unit composed of well-defined, loosely coupled modules with clear boundaries.

A modular monolith keeps the **simplicity of deployment** of a monolith while gaining the **boundary clarity** of microservices. The codebase is divided into modules (often aligned with bounded contexts), each with:

- Its own domain logic
- Clear public API
- Enforced boundaries (package-private internals, no cross-module direct DB access)

**The path to microservices:**

Many teams start with a modular monolith and extract services only when a module needs independent scaling or deployment.

```
┌─────────────────────────────────────────┐
│           Modular Monolith              │
│  ┌─────────┐ ┌─────────┐ ┌─────────┐   │
│  │ Orders  │ │ Catalog │ │ Users   │   │
│  │ Module  │ │ Module  │ │ Module  │   │
│  │         │ │         │ │         │   │
│  │ (own    │ │ (own    │ │ (own    │   │
│  │  model) │ │  model) │ │  model) │   │
│  └─────────┘ └─────────┘ └─────────┘   │
│         ↕ shared kernel / API layer     │
└─────────────────────────────────────────┘
```

**Strengths:**

- Simple deployment and operations
- Refactoring across modules is easier than across services
- No network latency or distributed failure modes
- Can migrate to microservices incrementally

**Weaknesses:**

- Single codebase can grow unwieldy
- Requires discipline to enforce module boundaries
- Cannot scale or deploy modules independently

## See Also

- [Monolith](monolith.md)
- [Microservices](microservices.md)
- [Domain-Driven Design](../index.md#domain-driven-design-ddd-2003)
