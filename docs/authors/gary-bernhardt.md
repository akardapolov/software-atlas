# Gary Bernhardt

| | |
|---|---|
| **Fields** | Software design, functional programming, testing |
| **Known for** | "Boundaries" talk, Functional Core / Imperative Shell, Destroy All Software |

## Biography

Gary Bernhardt is an American software developer, speaker, and educator
known for his screencast series **Destroy All Software** and his
influential conference talk **"Boundaries"** (2012). He has worked across
multiple technology stacks and is known for concise, thought-provoking
presentations.

## Key Contributions

### "Boundaries" Talk (2012)

Bernhardt's RubyConf 2012 talk introduced the **Functional Core /
Imperative Shell** pattern — a practical architecture that combines FP
and imperative programming:

**Functional Core:**

- Pure functions operating on immutable values
- All business logic lives here
- No side effects — no database calls, no HTTP, no file I/O
- Extremely easy to test (just call functions and check return values)
- Decisions are made here

**Imperative Shell:**

- A thin layer that handles I/O and external interactions
- Calls the functional core to make decisions
- Applies those decisions by performing side effects
- Kept as thin and simple as possible
- Integration tested (fewer, coarser tests)

```
┌──────────────────┐
│    Imperative Shell      │  ← handles I/O, calls core
│  ┌────────────────────┐  │
│  │  Functional Core   │  │  ← pure logic, no I/O
│  │  (values in,       │  │
│  │   values out)      │  │
│  └────────────────────┘  │
└──────────────────────────┘
```

The insight: by structuring code this way, you get **most of the
benefits of functional programming** (testability, understandability, composability)
**without requiring a fully functional language** — you can do this in
Ruby, Python, Java, or any language.

This pattern is essentially the same idea as Hexagonal Architecture
(Cockburn) and Clean Architecture (Martin), but arrived at from a
different direction — from thinking about testing and FP rather than
from thinking about ports and adapters.

### Testing Philosophy

Bernhardt's testing approach follows from FC/IS:

- **Unit tests** for the functional core — fast, plentiful, testing pure logic
- **Integration tests** for the imperative shell — fewer, testing that
  I/O works correctly
- **Few or no "sociable" tests** that test large combinations —
  the pure core can be unit-tested exhaustively, and the thin shell needs
  only coarse integration tests

This contrasts with the "testing pyramid" where many unit tests + some
integration tests + few end-to-end tests are recommended. Bernhardt's
approach makes the distinction along the **pure/impure boundary** rather
than along scope.

### Destroy All Software

Bernhardt's screencast series covers a wide range of software development
topics in short, dense episodes. Notable entries include:

- **"Wat"** — a legendary 4-minute lightning talk exposing JavaScript and
  Ruby's type coercion quirks (over 4 million YouTube views)
- Screencasts on testing, Unix tools, software design, and refactoring

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2012 | "Boundaries" (RubyConf) | Talk | [→](../works/talks/bernhardt-2012-boundaries.md) |
| 2012 | "Wat" (CodeMash) | Lightning talk | — |
| 2011–2015 | *Destroy All Software* (screencasts) | Video series | — |

## Influence

### Influenced by

- **Rich Hickey** — simplicity, values over state, FP thinking
- **Kent Beck** — TDD, testing philosophy
- **J.B. Rainsberger** — "Integrated Tests Are A Scam" (pushing toward isolated unit tests)
- **Hexagonal Architecture** — same structure, different origin story

### Influenced

- **Functional Core / Imperative Shell** — the pattern is now widely taught and applied
- **Brandon Rhodes** — "The Clean Architecture in Python" builds on Bernhardt's ideas
- **Mark Seemann** — functional architecture in .NET references FC/IS
- **Modern architecture discussions** — FC/IS has become a standard reference pattern

## Quotes

> "The imperative shell is so simple it doesn't need unit tests.
> The functional core is so well tested it doesn't need
> integration tests."

> "Separate the things that change from the things that don't.
> The things that don't change are values. The things that change
> are effects."

## Further Reading

- [Destroy All Software](https://www.destroyallsoftware.com/)
- ["Boundaries" talk](https://www.destroyallsoftware.com/talks/boundaries)
- ["Wat" talk (YouTube)](https://www.destroyallsoftware.com/talks/wat)

## Related Pages

- [Boundaries talk](../works/talks/bernhardt-2012-boundaries.md)
- [Functional Programming](../topics/functional/index.md)
- [Architecture & Modularity](../topics/architecture/index.md)
- [OOP & Design](../topics/design/index.md)
- [Rich Hickey](rich-hickey.md)
- [Alistair Cockburn](alistair-cockburn.md)
