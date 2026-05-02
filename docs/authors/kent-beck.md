# Kent Beck

| | |
|---|---|
| **Born** | 1961 |
| **Fields** | Software engineering, agile methods, testing, design |
| **Known for** | Extreme Programming, TDD, JUnit/SUnit, design patterns |

## Biography

Kent Beck is an American software engineer who has been at the centre of
multiple movements that transformed how software is built. He created
**Extreme Programming (XP)**, rediscovered and popularised **Test-Driven
Development (TDD)**, co-created **JUnit** and **SUnit** testing
frameworks, and was one of the original 17 signatories of the **Agile
Manifesto**.

Beck studied at the University of Oregon and has worked at Tektronix,
Apple, First Class Software, Facebook (Meta), and as an independent
consultant. His work has consistently focused on making software
development more humane, productive, and responsive to change.

## Key Contributions

### Extreme Programming — XP (1996–1999)

XP was the first comprehensive agile methodology. Beck developed it
while working on the Chrysler Comprehensive Compensation System (C3)
project. XP is built on five values:

- **Communication** — continuous conversation between developers and customers
- **Simplicity** — build the simplest thing that works; YAGNI (You Aren't Gonna Need It)
- **Feedback** — short iterations, continuous testing, frequent releases
- **Courage** — make difficult decisions, refactor aggressively, throw away code that doesn't work
- **Respect** — for team members and their contributions

XP practices include:

- **Pair programming** — two programmers at one keyboard
- **Test-Driven Development** — write tests before code
- **Continuous Integration** — integrate and test multiple times per day
- **Refactoring** — continuously improve design
- **Small releases** — deliver working software frequently
- **Collective code ownership** — anyone can change any code
- **Sustainable pace** — no overtime; fresh developers write better code

### Test-Driven Development — TDD (1999–2002)

TDD is a development discipline with three steps:

1. **Red** — write a test that fails (because the feature doesn't exist yet)
2. **Green** — write the simplest code that makes the test pass
3. **Refactor** — improve the design while keeping tests green

TDD is counterintuitive: you write a test *before* code. But this
inverts the usual pain of testing:

- Tests are never skipped (they're written first)
- The design is driven by how code will be *used* (the test is a client)
- You get a regression safety net automatically
- You build confidence that code works, one small step at a time

Beck didn't claim to invent TDD — he said he "rediscovered" it from
early practices at IBM. But he systematised it, wrote the definitive book
(*TDD by Example*, 2002), and made it accessible to the industry.

### SUnit and JUnit

Beck created **SUnit** (Smalltalk Unit, 1994), the first unit testing
framework. With Erich Gamma, he later created **JUnit** (1997) for Java.
JUnit's design became the template for the **xUnit family** of testing
frameworks in virtually every language:

- **NUnit** (C#), **pytest** (Python), **RSpec** (Ruby), **Jest** (JS),
  **go test** (Go), **cargo test** (Rust)

### Design Patterns Contribution

Beck (with Ward Cunningham) was one of the first to apply Christopher
Alexander's architectural patterns to software (1987). This work
eventually led to the GoF *Design Patterns* book. Beck contributed
Smalltalk examples and the pattern community's understanding that patterns
are about **forces and trade-offs**, not just reusable solutions.

### Agile Manifesto (2001)

Beck was one of the 17 authors of the Agile Manifesto. While he didn't
write it alone, XP's values and practices were a major influence on
Manifesto's content.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1999 | *Extreme Programming Explained* | Book | — |
| 2002 | *Test-Driven Development: By Example* | Book | [→](../works/books/beck-2002-tdd.md) |
| 2003 | *Patterns of Enterprise Application Architecture* (with Fowler, contributing) | Book | — |
| 2004 | *JUnit Pocket Guide* | Book | — |
| 2008 | *Implementation Patterns* | Book | — |

## Influence

### Influenced by

- **Ward Cunningham** — long-time collaborator; wiki, CRC cards, pattern community
- **Christopher Alexander** — architectural patterns applied to software
- **W. Edwards Deming** — systems thinking, continuous improvement, respect for workers
- **Smalltalk community** — live environment, rapid feedback, exploratory programming

### Influenced

- **Martin Fowler** — refactoring builds directly on XP's practices
- **Agile movement** — XP was the most concrete of the early agile methods
- **Michael Feathers** — *Working Effectively with Legacy Code* extends TDD to existing systems
- **Continuous Integration / Continuous Delivery** — CI was an XP practice first
- **Every testing framework** — xUnit descends from SUnit/JUnit

## Quotes

> "I'm not a great programmer; I'm just a good programmer with
> great habits."

> "Make it work, make it right, make it fast."

> "Optimism is an occupational hazard of programming; feedback
> is the treatment."

> "Do the simplest thing that could possibly work."

> "Any fool can write code that a computer can understand. Good
> programmers write code that humans can understand."
> *(often attributed to Beck, originally from Fowler's Refactoring dedication)*

## Further Reading

- [Wikipedia: Kent Beck](https://en.wikipedia.org/wiki/Kent_Beck)
- [Kent Beck on Substack](https://tidyfirst.substack.com/)
- [Original Agile Manifesto](https://agilemanifesto.org/)

## Related Pages

- [TDD by Example](../works/books/beck-2002-tdd.md)
- [Process & Testing](../topics/process/index.md)
- [Martin Fowler](martin-fowler.md)
- [Michael Feathers → Legacy Code](../works/books/feathers-2004-legacy.md)
