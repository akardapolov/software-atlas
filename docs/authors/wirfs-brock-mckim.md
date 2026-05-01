# Wirfs-Brock & McKim

| | |
|---|---|
| **Fields** | Object-oriented design, responsibility-driven design |

## Rebecca Wirfs-Brock

See [Rebecca Wirfs-Brock](rebecca-wirfs-brock.md) for full biography.

## Dorothy Lane McKim

| | |
|---|---|
| **Born** | 1953 |
| **Fields** | Object-oriented design, responsibility-driven design |

## Biography

Dorothy Lane McKim is a software engineer and author known for co-authoring *Object Design: Roles, Responsibilities, and Collaborations* (2003) with Rebecca Wirfs-Brock. McKim's work focuses on expanding and refining the principles of object-oriented design, particularly in the areas of responsibilities, collaborations, and design heuristics.

McKim's work provides practical guidance for designers and developers on how to create well-structured object-oriented systems with clear separation of concerns and defined interfaces between objects.

## Key Contributions

### Object Design (2003)

With Rebecca Wirfs-Brock, McKim expanded on key design concepts:

**Responsibilities and Collaborations:**
| Concept | Description |
|-----------|-------------|
| **Responsibilities** - Obligations of a class (what it knows or does) | Well-defined contracts |
| **Collaborations** - Other classes a class interacts with | Clear interfaces |
| **Cardinality** - Number of related objects (1-1, 1-n, 0-n, n-n) | Specified multiplicities |
| **Operation visibility** - Public vs. private interface | Access modifiers control visibility |
| **Exception handling** - How errors are communicated | Try-catch patterns |

**Design Heuristics:**
| **Law of Demeter** - Only talk to immediate neighbors | Reduce coupling |
| **Don't talk to strangers** | Access only through public interfaces | **Prefer composition over inheritance** | Behavior composition over deep hierarchies |
| **Encapsulate what varies** - Hide implementation behind stable interface | **Favor aggregation over association** | When appropriate for containment

### Patterns for Object Design

McKim documented design patterns as solutions to common problems:

| Pattern | Problem | Solution |
|---------|----------|----------|
| **Facade** | Simplify complex subsystem with single interface | One class, many methods |
| **Strategy** | Encapsulate algorithms, make them interchangeable | Context-based selection |
| **Observer** | One-to-many notification | Event-driven communication |
| **Decorator** - Add behavior to objects dynamically | Dynamic extension |
| **Adapter** | Bridge between incompatible interfaces | Interface translation |
| **Composite** - Treat individual and groups uniformly | Tree structure |

## Influence

### Influenced by

- **Barbara Liskov** - Liskov Substitution Principle (LSP)
- **Robert C. Martin** - SOLID principles
- **Rebecca Wirfs-Brock** - CRC cards, responsibility-driven design

### Influenced

- **Object-oriented design** - Practical application of design principles
- **Design patterns** - Pattern language in everyday use
- **CRC methodology** - Collaborative object modeling technique

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2003 | Object Design: Roles, Responsibilities, and Collaborations | Book (co-authored) | [→](../works/books/wirfs-brock-2003-object-design.md) |

## Related Pages

- [OOP & Design topic](../topics/design/) — SOLID principles and OOP design
- [Barbara Liskov](barbara-liskov.md) — LSP and behavioral subtyping
- [Robert C. Martin](robert-c-martin.md) — SOLID principles
- [Rebecca Wirfs-Brock](rebecca-wirfs-brock.md) — CRC cards, responsibility-driven design

## Further Reading

- Wirfs-Brock & McKim — *Object Design: Roles, Responsibilities, and Collaborations* (2003)
- Martin — *Clean Code* (2008) — Design chapters on SRP and OOP
- Liskov — *A Behavioral Notion of Subtyping* (1987)

## Quotes

> "Responsibilities should be cohesive and focused."
> — Dorothy Lane McKim (echoing Rebecca Wirfs-Brock)

> "The law of Demeter says: don't talk to strangers."
> — Dorothy Lane McKim

> "Prefer composition over inheritance for flexibility."
> — Dorothy Lane McKim

> "Encapsulate what varies behind a stable interface."
> — Dorothy Lane McKim

> "The goal of object design is managing complexity through structure."
> — Dorothy Lane McKim
