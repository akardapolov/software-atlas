# Scott Wlaschin

| | |
|---|---|
| **Fields** | Functional programming, domain-driven design, F# |
| **Known for** | *Domain Modeling Made Functional*, fsharpforfunandprofit.com |

## Biography

Scott Wlaschin is a developer, author, and speaker known for making
functional programming accessible to mainstream developers. His website
**fsharpforfunandprofit.com** is one of the most comprehensive resources
for learning F# and functional thinking. His book *Domain Modeling Made
Functional* (2018) demonstrated that FP and DDD are a natural fit.

## Key Contributions

### Domain Modeling Made Functional (2018)

Wlaschin's book bridges two worlds — Eric Evans's Domain-Driven Design
and ML-family functional programming (specifically F#). The central
thesis:

> **Use types to make illegal states unrepresentable.**

Instead of relying on runtime validation, encode business rules directly in
the type system. If the code compiles, the domain invariants hold.

**Key techniques:**

- **Algebraic data types for domain modeling:**

  ```fsharp
  type ContactInfo =
      | EmailOnly of EmailAddress
      | PostalOnly of PostalAddress
      | EmailAndPostal of EmailAddress * PostalAddress
  // A contact MUST have at least one way to reach them.
  // The type system enforces this — no "empty contact" is possible.
  ```

- **Single-case union types for domain primitives:**

  ```fsharp
  type EmailAddress = EmailAddress of string
  type OrderId = OrderId of int
  // Can't accidentally pass an OrderId where an EmailAddress is expected.
  ```

- **Function types as workflows:**

  ```fsharp
  type PlaceOrder = UnvalidatedOrder -> Result<OrderPlaced list, ValidationError list>
  // The function signature IS the documentation.
  ```

- **Railway-oriented programming** — error handling through composition
  of `Result` types, avoiding exceptions for expected failures.
- **Total functions** — every function handles every possible input.
  No partial functions, no exceptions for missing cases.

### Railway-Oriented Programming (ROP)

Wlaschin coined this term for a pattern of error handling using `Result`
types:

```
Input → [Validate] → [Process] → [Save] → Output
           ↓              ↓          ↓
        Error track    Error track  Error track
```

Each step either continues on the "happy path" or diverts to the "error
track." The composition happens automatically through `bind` / `andThen`.

This is essentially monadic composition (bind / flatMap), but Wlaschin
deliberately avoids category theory jargon, making the pattern accessible
to developers without FP backgrounds.

### fsharpforfunandprofit.com

Wlaschin's website contains an extensive series of articles and talks
that have introduced thousands of developers to functional programming:

- "Thinking Functionally" — a series on FP fundamentals
- "Designing with Types" — using the type system for domain modelling
- "Railway-Oriented Programming" — error handling without exceptions
- "Understanding Computation Expressions" — F#'s version of do-notation
- Comparison articles (FP vs OOP, F# vs C#)

### Talks

Wlaschin is a frequent conference speaker. Notable talks:

- "Domain Modeling Made Functional" — the book condensed into a talk
- "The Power of Composition" — how function composition replaces inheritance
- "Designing with Capabilities" — capability-based security through types
- "Thirteen Ways of Looking at a Turtle" — the same problem solved 13 ways, demonstrating different FP patterns

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2018 | *Domain Modeling Made Functional* | Book | [→](../works/books/wlaschin-2018-dmf.md) |
| 2013– | fsharpforfunandprofit.com | Website | — |

## Influence

### Influenced by

- **Eric Evans** — DDD patterns (bounded contexts, value objects, aggregates)
- **Robin Milner** — ML type system (algebraic data types, type inference)
- **Rich Hickey** — simplicity, values over objects
- **Haskell community** — monads, type-level reasoning
- **F# language** — practical typed FP on .NET

### Influenced

- **FP + DDD community** — showed that functional DDD is not just possible but often superior
- **Mainstream developers** — made FP accessible without requiring category theory
- **Type-driven design** — "make illegal states unrepresentable" is now widely quoted
- **Error handling patterns** — Railway-Oriented Programming adopted in many languages
- **Kotlin, TypeScript, Rust developers** — applying Wlaschin's patterns in non-ML languages

## Quotes

> "Make illegal states unrepresentable."

> "A type is not a class. A type is a set of possible values."

> "If you can make something a compile-time error instead of a
> runtime error, do it."

> "The best documentation is the type signature."

## Further Reading

- [fsharpforfunandprofit.com](https://fsharpforfunandprofit.com/)
- [Domain Modeling Made Functional (Pragmatic Bookshelf)](https://pragprog.com/titles/swdddf/domain-modeling-made-functional/)
- [Scott Wlaschin talks on YouTube](https://www.youtube.com/results?search_query=scott+wlaschin)

## Related Pages

- [Domain Modeling Made Functional](../works/books/wlaschin-2018-dmf.md)
- [Functional Programming](../topics/functional/)
- [Type Systems](../topics/types/)
- [Architecture & Modularity](../topics/architecture/)
- [ML](../languages/ml/)
- [Eric Evans](eric-evans.md)
- [Robin Milner](robin-milner.md)
- [Rich Hickey](rich-hickey.md)
