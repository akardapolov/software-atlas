# Sandi Metz (2012 POODR Edition)

| | |
|---|---|
| **Born** | 1965 |
| **Fields** | Object-oriented design, Ruby programming, SOLID principles |

## Biography

Sandi Metz is a software engineer and author known for *Practical Object-Oriented Design in Ruby* (POODR, 2012 edition). She is a long-time contributor to the Ruby community and has extensive experience in object-oriented design and testing.

Metz's work bridges the gap between formal design principles (SOLID) and practical application in dynamically-typed languages like Ruby, demonstrating how these principles apply regardless of type system.

## Key Contributions

### Practical Object-Oriented Design in Ruby (2012)

Metz's book provides practical guidance on applying OOP design principles in Ruby:

| Principle | Description | Ruby Application |
|-----------|-------------|-----------------|
| **Single Responsibility** | One class, one reason | Modules with clear purpose |
| **Open/Closed Principle** | Extend via inheritance/modules | Dynamic typing via duck types |
| **Liskov Substitution** | Substitutable base types | Duck typing & behavior checking |
| **Interface Segregation** | Small, focused interfaces | Mixins over complex inheritance |
| **Dependency Inversion** | Depend on abstractions | Dependency injection |

**Key Ruby-specific techniques:**
- **Mixins** — shared behavior without inheritance
- **Duck typing** — structural compatibility
- **Metaprogramming** — class_eval, method_missing for dynamic features
- **Testing** — RSpec and best practices

### SOLID in Dynamic Languages

Metz demonstrates how SOLID principles, originally formulated for statically-typed languages, apply to Ruby:

| SOLID | Static Languages | Ruby (Dynamic) |
|------|----------------|---------------------|
| **SRP** | Enforced by class structure | Enforced via module design |
| **OCP** | Interfaces and inheritance | Modules and mixins |
| **LSP** | Type system enforces | Duck typing + behavior checks |
| **ISP** | Explicit interfaces | Mixins and module boundaries |
| **DIP** | Dependency injection | Dependency injection containers |

## Influence

### Influenced by

- **Robert C. Martin** — SOLID principles
- **Kent Beck** — testing and XP practices

### Influenced

- **Ruby community** — Improved OOP design practices
- **Dynamic language communities** — SOLID applications beyond static typing
- **Testing practices** — Practical object-oriented testing approaches
- **Design patterns adoption** — Applied GoF patterns in Ruby context

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2002 | Practical Object-Oriented Design in Ruby | Book | [→](../works/books/metz-2012-poodr.md) |
| 2018 | Practical Object-Oriented Design in Ruby (updated) | Book | [→](../works/books/metz-2018-poodr.md) |

## Related Pages

- [OOP & Design topic](../topics/design/index.md) — SOLID principles and object-oriented design
- [Robert C. Martin](robert-c-martin.md) — SOLID principles creator
- [Kent Beck](kent-beck.md) — TDD and testing practices
- [Ruby language page](../languages/ruby/index.md) — Ruby OOP patterns

## Further Reading

- Metz — *Practical Object-Oriented Design in Ruby* (2012)
- Metz — *Practical Object-Oriented Design in Ruby* (2018)
- Martin — *Clean Code* (2008)
- Metz — *Practical Object-Oriented Design in Ruby with Test::Unit* (2009)
- Martin — *Agile Software Development, Principles, Patterns, and Practices* (2003)

## Quotes

> "Design is about trade-offs. The best design for one context may be terrible for another."
> — Sandi Metz

> "In dynamic languages, you get duck typing for free. Use it wisely."
> — Sandi Metz on Ruby's type system

> "SOLID principles are guidelines, not laws. Understand the intent, apply where it makes sense."
> — Sandi Metz on SOLID
