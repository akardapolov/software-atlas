# Dan North

| | |
|---|---|
| **Born** | United Kingdom |
| **Fields** | Software engineering, agile, testing |
| **Known for** | Behaviour-Driven Development (BDD), JBehave, Given–When–Then |

## Biography

Daniel Terhorst-North is a British software engineer and independent
consultant. While working with TDD practitioners in the early 2000s he
observed that newcomers consistently got stuck on the word "test" —
they wrote tests about methods rather than about *behaviour*. Reframing
the practice in the language of examples and behaviour became the seed
of BDD.

North has been an active speaker on the agile and software-craftsmanship
conference circuit since the mid-2000s, working with organisations from
ThoughtWorks to investment banks. His later work spans system thinking,
the "deliberate discovery" model, and CUPID — principles for joyful
software.

## Key Contributions

### Behaviour-Driven Development (BDD) (2006)

In *Introducing BDD* (Better Software magazine, 2006) North argued that
the *vocabulary* of testing was the obstacle, not the practice itself.
He proposed:

- **Given–When–Then** — the canonical scenario template; a precondition,
  an event, an outcome.
- **Test method names as sentences** — `should_dispense_cash_when_balance_sufficient`.
- **Examples as the unit of conversation** — between product, dev, and test.

BDD made the bridge from TDD towards Acceptance-Test-Driven Development
(ATDD) and Specification by Example.

### JBehave (2003+)

JBehave was the first BDD framework, written by North for the JVM.
It later inspired the polyglot **Cucumber** ecosystem (Gherkin DSL,
2008+), which now spans Java, JavaScript, .NET, Python, Ruby, Go, and
more.

### Deliberate Discovery and CUPID

Outside BDD, North coined **deliberate discovery** — the idea that
software projects should plan for what they don't yet know — and the
**CUPID** properties for code (Composable, Unix-philosophy, Predictable,
Idiomatic, Domain-based) as an alternative framing to SOLID.

## Key Works

| Year | Title | Type |
|------|-------|------|
| 2006 | *Introducing BDD* | Essay (Better Software magazine) |
| 2003+ | JBehave | Framework |
| 2021 | *CUPID — for joyful coding* | Essay |

## Influence

### Influenced by

- **[Kent Beck](kent-beck.md)** — TDD as the starting point; BDD is a
  reframing of TDD's vocabulary
- **Eric Evans** — Domain-Driven Design; "ubiquitous language" feeds
  directly into Given–When–Then phrasing
- **Chris Matts** — feature injection and example-driven analysis

### Influenced

- **Aslak Hellesøy** — created Cucumber and the Gherkin DSL on top of
  BDD vocabulary
- **Gojko Adzic** — *Specification by Example* (2011) extends BDD into
  living documentation
- **Liz Keogh** — BDD coaching, deliberate discovery
- The broader ATDD / Specification by Example community

## Quotes

> "I had a problem. While using and teaching agile practices like
> test-driven development on projects in different environments, I kept
> coming across the same confusion and misunderstandings. Programmers
> wanted to know where to start, what to test and what not to test, how
> much to test in one go, what to call their tests, and how to understand
> why a test fails."

## Further Reading

- [Introducing BDD (original essay)](https://dannorth.net/introducing-bdd/)
- [dannorth.net](https://dannorth.net/) — blog and talks
- [Wikipedia — Behavior-driven development](https://en.wikipedia.org/wiki/Behavior-driven_development)

## Related Pages

- [Testing](../topics/testing/index.md) — BDD section
- [Kent Beck](kent-beck.md) — TDD lineage
