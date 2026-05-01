# Object-Oriented Analysis and Design with Applications

| | |
|---|---|
| **Author** | Grady Booch |
| **Year** | 1994 |
| **Publication** | Addison-Wesley (2nd/3rd ed.) |
| **Topic(s)** | UML, OOAD, software design |

## Summary

This seminal work presents **Booch Method** — one of the first systematic approaches to **object-oriented analysis and design (OOAD)**. Booch's work, along with James Rumbaugh's OMT and Ivar Jacobson's use cases, became the foundation for **UML (Unified Modeling Language)**.

## Key Ideas

### Object-Oriented Analysis and Design

Booch Method provides structured approach to OO analysis and design:

- **Object model** — identify classes, attributes, relationships
- **Dynamic model** — object state changes over time
- **Functional model** — data flow between objects
- **Class diagrams** — visualize class structure
- **State diagrams** — represent object behavior

This became the **standard methodology** for OO analysis in 1990s.

### Booch Notation

Booch introduced a graphical notation for OO modeling:

- **Cloud symbols** — original class notation (cloudy shapes)
- **Relationships** — different arrows for different connections
- **Templates and mechanisms** — parameterized classes, concurrency

Notation evolved into UML class diagrams after 1997.

### Classification of Relationships

Booch identified fundamental object relationships:

| Type | Symbol | Description |
|------|-------|-------------|
| **Association** | Solid line | Objects know each other |
| **Aggregation** | Hollow diamond | "Has-a" relationship |
| **Composition** | Filled diamond | Strong "has-a" (parts don't exist independently) |
| **Dependency** | Dashed arrow | One object depends on another |
| **Inheritance** | Solid triangle | "Is-a" relationship |

This classification became standard for discussing OO design.

### Architectural Styles

Booch contributed to **software architecture thinking**:

- **Layered architecture** - separation of concerns
- **Client-server** - distributed systems
- **Pipe-and-filter** - Unix-like component systems
- **Blackboard pattern** - shared state management

## Historical Significance

### UML Foundation

Booch Method, with Rumbaugh's OMT and Jacobson's use cases:

- **Formed UML** (1997) — Unified Modeling Language standard
- **Unified approaches** — three methods into one standard
- **Industry adoption** - tools like Rational Rose, Enterprise Architect
- **Education** - UML taught in CS courses worldwide

### Rational Unified Process (RUP)

Booch's work led to **Rational Unified Process**:

- **Use-case driven** — requirements from user perspective
- **Iterative development** - multiple use-case driven cycles
- **Component architecture** - reusable, composable systems
- **Tool support** - Rose, RequisitePro, Enterprise Architect

## Legacy

Booch's influence continues:

- **UML standard** — class diagrams remain primary OO modeling tool
- **Software architecture** - architectural patterns and styles
- **Enterprise modeling** - large system design approaches
- **CASE tools** - computer-aided software engineering

## Connections

- **Co-created UML:** [Jacobson (Use Cases)](../books/jacobson-1992-oose.md) · [Rumbaugh (OMT)](../books/rumbaugh-1991-omt.md)
- **Influenced:** [Software Architecture in Practice](../topics/architecture/)
- **Related topic:** [UML](../topics/architecture/) (if exists)
- **Author:** [Grady Booch](../../authors/grady-booch.md)
