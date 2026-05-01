# Robert C. Martin

| | |
|---|---|
| **Born** | 1952 |
| **Fields** | Software design, OOP, agile methods |
| **Known for** | SOLID principles, *Clean Code*, *Clean Architecture*, "Uncle Bob" |

## Biography

Robert Cecil Martin, known as "Uncle Bob," is an American software
engineer, author, and speaker. He has been programming since 1970 and has
become one of the most prominent advocates for software craftsmanship and
disciplined design.

Martin was one of the 17 signatories of the **Agile Manifesto** (2001) and
later authored the **Software Craftsmanship Manifesto**. He founded Object
Mentor and later Clean Coders, producing educational video content on
software development practices.

## Key Contributions

### SOLID Principles (2000–2003)

Martin compiled five object-oriented design principles into the SOLID
acronym (the acronym itself was coined by Michael Feathers):

**S — Single Responsibility Principle (SRP)**
:   A class should have only one reason to change. Each class should
    encapsulate one responsibility (one "actor" it serves).

**O — Open-Closed Principle (OCP)**
:   Software entities should be open for extension but closed for
    modification. Add new behaviour through new code, not by changing
    existing code. (Originally from Bertrand Meyer, 1988.)

**L — Liskov Substitution Principle (LSP)**
:   Subtypes must be substitutable for their base types without altering
    correctness. (From Barbara Liskov and Jeannette Wing, 1994.)

**I — Interface Segregation Principle (ISP)**
:   No client should be forced to depend on methods it does not use.
    Prefer many small, specific interfaces over one large general interface.

**D — Dependency Inversion Principle (DIP)**
:   High-level modules should not depend on low-level modules. Both should
    depend on abstractions. Abstractions should not depend on details.

SOLID became the most widely taught set of OOP design principles, appearing
in university curricula, coding bootcamps, and interview questions worldwide.

### Clean Code (2008)

Martin's book *Clean Code* codified practices for writing readable,
maintainable code:

- Meaningful names
- Small functions that do one thing
- Few function arguments
- No side effects in functions that appear pure
- DRY (Don't Repeat Yourself)
- The Boy Scout Rule ("Leave the code cleaner than you found it")

The book is one of the best-selling software development books ever and
is often the first book recommended to junior developers.

### Clean Architecture (2017)

Martin synthesised architectural principles from Hexagonal Architecture
(Cockburn), Onion Architecture (Palermo), and his own work into
**Clean Architecture**:

- **Independence from frameworks** — the architecture doesn't depend on libraries
- **Testable** — business rules can be tested without UI, database, or external services
- **Independence from UI** — the UI can change without changing business rules
- **Independence from database** — business rules aren't bound to a specific database
- **The Dependency Rule** — source code dependencies point inward (toward policies, away from mechanisms)

### Agile Manifesto (2001)

Martin was one of the 17 signatories and has been a vocal advocate for
agile values. He later argued that the agile movement had lost its way,
with many organisations adopting agile processes (Scrum ceremonies) without
the technical practices (TDD, refactoring, pair programming) that make
agility possible.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2002 | *Agile Software Development: Principles, Patterns, and Practices* | Book | — |
| 2008 | *Clean Code: A Handbook of Agile Software Craftsmanship* | Book | — |
| 2011 | *The Clean Coder: A Code of Conduct for Professional Programmers* | Book | — |
| 2017 | *Clean Architecture* | Book | — |
| 2019 | *Clean Agile: Back to Basics* | Book | — |

## Influence

### Influenced by

- **David Parnas** — information hiding → Single Responsibility Principle
- **Barbara Liskov** — LSP directly adopted
- **Bertrand Meyer** — Open-Closed Principle directly adopted
- **Alistair Cockburn** — Hexagonal Architecture → Clean Architecture
- **Kent Beck** — TDD, XP practices

### Influenced

- **Industry-wide** — SOLID is taught in virtually every OOP course
- **Software craftsmanship movement** — Martin is its most visible advocate
- **Clean Architecture variants** — widely adopted in mobile (Android, iOS) and backend development

## Quotes

> "The only way to go fast is to go well."

> "It is not enough for code to work."

> "A long function is where classes go to hide."

## Further Reading

- [Wikipedia: Robert C. Martin](https://en.wikipedia.org/wiki/Robert_C._Martin)
- [Clean Coder blog](https://blog.cleancoder.com/)
- [Clean Coders (videos)](https://cleancoders.com/)

## Related Pages

- [OOP & Design](../topics/design/)
- [Architecture](../topics/architecture/)
- [David Parnas](david-parnas.md)
- [Barbara Liskov](barbara-liskov.md)
- [Bertrand Meyer](bertrand-meyer.md)
- [Kent Beck](kent-beck.md)
- [Alistair Cockburn](alistair-cockburn.md)
