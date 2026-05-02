# The Open-Closed Principle

| | |
|---|---|
| **Author** | Robert C. Martin |
| **Year** | 1996 |
| **Publication** | C++ Report |
| **Topic(s)** | SOLID, OOP design, software principles |

## Summary

Martin's seminal paper introduces the **Open-Closed Principle (OCP)** — one of the five **SOLID** principles of object-oriented design. OCP states that **software entities (classes, modules, functions, etc.) should be open for extension but closed for modification**. This principle became foundational for designing maintainable, extensible software systems.

## Key Ideas

### Two Rules of OCP

Martin states OCP with two rules:

1. **Open for Extension**
   - New functionality can be added
   - Without modifying existing code
   - Through inheritance, composition, or plugins
2. **Closed for Modification**
   - Existing code remains stable
   - Changes don't ripple through codebase
   - Existing clients aren't broken

### Module Stability

OCP enables:

- **Independent development** — new features as separate modules
- **Plugin architectures** - extend functionality without touching core
- **Version compatibility** - new versions don't break existing clients
- **Team coordination** — teams can work on different modules independently

### Implementation Approaches

Martin identifies ways to achieve OCP:

| Approach | Description | Example |
|---------|-------------|---------|
| **Abstract base classes** | Define interfaces, inherit for new behavior | Shape interface |
| **Composition over inheritance** | Combine objects dynamically | UI components |
| **Delegation** | Forward requests to helper objects | Proxy pattern |
| **Strategy pattern** | Pluggable algorithms | Sorting strategies |
| **Template method** | Skeleton code with variation points | Data access |

## Historical Significance

### SOLID Foundation

OCP was Martin's formalization of **Meyer's principle** (from OOSC 1988). While Meyer described open/closed for modules, Martin extended it to general OO principle and included it in SOLID.

### Design Impact

OCP influenced:

- **Plugin systems** - Eclipse, VS Code extensions
- **Framework design** - Spring, .NET extensibility
- **API design** - REST API extension points
- **Microservices** - independent services with stable contracts

### Anti-Pattern: God Object

OCP is the antidote to **God Object** anti-pattern:

- **God Object** — does everything, closed to change
- **OCP compliance** — focused objects do one thing, open for extension
- **Composability** — complex systems built from small, focused objects

## Legacy

OCP remains influential:

- **Extensible systems** - WordPress, VS Code, browser extensions
- **Framework design** - modular architecture principles
- **API-first design** - stable interfaces for extension
- **Microservices** - independent services with versioned contracts

## Connections

- **Preceded by:** [Meyer (OOSC)](../books/meyer-1997-oosc2.md)
- **Co-created SOLID:** [SRP (Liskov)](../papers/liskov-wing-1994-subtyping.md)
- **Influenced:** Plugin architectures, framework design, microservices
- **Author:** [Robert C. Martin](../../authors/robert-c-martin.md)
- **Related Works:** [Agile Software Development (2003)](../books/martin-2003-agile-ppp.md)
- **Related:** [Liskov Substitution](../papers/liskov-wing-1994-subtyping.md)
- **Related topic:** [SOLID](../../topics/design/index.md) (if exists)
