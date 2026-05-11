# Micro-frontends

**Category:** Presentation / Structural  
**Source:** ThoughtWorks Technology Radar (2016); Michael Geers — *Micro Frontends in Action* (2020)

> Extend the microservices idea to frontend development: decompose a web application into independently deployable, autonomous frontend modules.

Each micro-frontend is a self-contained unit — it owns a page, a section of a page, or a vertical feature slice. Teams can choose their own frameworks, deploy independently, and scale their development without blocking each other.

```
┌─────────────────────────────────────────────────────────────┐
│  Container App (shell) — routing, shared layout, auth       │
├─────────────┬─────────────┬─────────────────────────────────┤
│  Catalog    │   Basket    │        Checkout                 │
│  (React)    │  (React)    │        (Vue)                    │
│  Team A     │  Team A     │        Team B                   │
├─────────────┴─────────────┴─────────────────────────────────┤
│  Footer (shared web component)                              │
└─────────────────────────────────────────────────────────────┘
```

**Integration approaches:**

| Approach | Mechanism | Trade-off |
|----------|-----------|-----------|
| **Build-time** | Compose modules into a single bundle before deployment | Tight coupling, single deploy |
| **Run-time (iframes)** | Each module loads in its own iframe | Strong isolation, poor UX (resizing, accessibility) |
| **Run-time (Web Components)** | Each module exposes a custom element | Framework-agnostic, standard-based |
| **Run-time (module federation)** | Webpack/Vite dynamically loads remote modules | Seamless integration, requires shared dependency management |

**Strengths:**

- Independent deployment and release cadence per team
- Technology diversity: each team picks the right tool
- Fault isolation: a bug in one module does not crash the entire application
- Aligns with Conway's Law and bounded contexts

**Weaknesses:**

- Shared dependencies (frameworks, design systems) require careful versioning
- Cross-module communication and state sharing are non-trivial
- Performance: multiple framework instances on one page
- Consistent user experience across modules requires strong design systems

## See Also

- [Microservices](../structural/microservices.md) — the backend analogue
- [Component-Based Architecture](component-based.md) — components within a single application
- [API Gateway](../integration/api-gateway.md) — the entry point that routes to backend services
