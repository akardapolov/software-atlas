# The Open-Closed Principle

| | |
|---|---|
| **Author** | Robert C. Martin |
| **Year** | 1996 |
| **Publication** | C++ Report |
| **Topic(s)** | SOLID, OOP design, software principles |

## Summary

Martin's seminal paper introduces the **Open-Closed Principle (OCP)** — one of the five **SOLID** principles of object-oriented design. OCP states that software entities (classes, modules, functions) should be **open for extension** (new capabilities can be added) but **closed for modification** (existing behavior must be preserved). This principle enables **plug-in architectures** and **evolutionary development**.

## Key Ideas

### Definition

Martin states OCP formally:

> "Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification."

- **Open for extension:** adding new features without requiring changes to existing code
- **Closed for modification:** existing clients can't modify existing entities in breaking ways

### Bertrand Meyer's Influence

OCP is an evolution of Meyer's **Open-Closed Principle** (OCP):

- **Meyer (1988)** - classes open for extension
- **Martin (1996)** - OCP formalized OCP
- **Liskov (1994)** - behavioral subtyping supports OCP

### Real-World Examples

Martin provides practical examples:

| Scenario | Violation | Fix |
|---------|-------------|---------|----------|
| **Plugin system** | New plugin adds features | Core remains stable |
| **Database schema** | New table columns | Existing data unchanged |
| **API changes** | New methods added carefully | **Library version** | Backward compatible |

### Benefits of OCP

OCP enables:

- **Plugin architectures** — Eclipse, VS Code, browser extensions
- **Version evolution** - multiple implementations coexist
- **Backward compatibility** - stable contracts between versions
- **Modular design** - independent components can evolve independently

### Design Guidelines

Martin provides OCP principles:

- **Separate interface** - define stable contracts
- **Prevent unintended modification** - mark abstractions appropriately
- **Document extension points** - where new features are intended
- **Version management** - use semantic versioning

## Historical Significance

### SOLID Foundation

Martin's paper **formalized** OCP:

- **From principles** — Meyer's OCP
1988
 **To standard** — SOLID became one of five SOLID principles
- **To language design** - Java, C# influenced by OCP
- **To frameworks** - Spring, .NET embrace extension

### Design Influence

OCP influenced:

- **Java interfaces** - stable contracts between modules
- **C# attributes** - `[virtual]` for extension methods
- **.NET interfaces** - explicit interface contracts
- **Plugin architectures** - enable extension without modification
- **Design patterns** - Strategy, Decorator built on OCP

## Legacy

OCP remains influential:

- **Java/C# design** - primary mechanism for extensibility
- **Modern frameworks** - Spring, ASP.NET, OSGi
- **API design** - REST APIs often use OCP
- **Microservices** - independent services with stable contracts
- **Versioned contracts** - language extensions and semvers

## Connections

- **Preceded by:** [Liskov (ADT, 1974)](../papers/liskov-1974-adt.md)
- **Co-authored:** [Meyer (OOSC)](../books/meyer-1997-oosc2.md)
- **Co-created OCP:** [Bertrand Meyer](../authors/bertrand-meyer.md)
- **Influenced:** Java, C#, interface design, plugin architectures
- **Related principles:** [LSP](../topics/design/) (if exists)
- **Author:** [Robert C. Martin](../../authors/robert-c-martin.md)
- **Related:** [Clean Architecture](../topics/architecture/) (if exists)
