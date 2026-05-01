# Koen Claessen

| | |
|---|---|
| **Fields** | Functional programming, testing tools |
| **Known for** | QuickCheck (co-creator with John Hughes) |

## Biography

Koen Claessen is a computer scientist known for co-creating **QuickCheck** with John Hughes in 2000. QuickCheck introduced **property-based testing**, a testing methodology where programmers write invariants and the framework generates random inputs to find counterexamples.

Claessen's work on QuickCheck demonstrated that automated random generation combined with counterexample shrinking could find bugs that example-based testing would miss.

## Key Contributions

### QuickCheck (2000)

Co-created QuickCheck with John Hughes at Chalmers University of Technology.

QuickCheck's innovations:
- **Random test generation** — framework generates inputs automatically
- **Counterexample shrinking** — finds minimal failing cases
- **Property-based approach** — test invariants, not examples
- **Typeclass-based generators** — easy to extend for new types

The approach has been ported to virtually every major language (Hypothesis, ScalaCheck, FsCheck, test.check, proptest, fast-check, and many others).

### Lazy Smallcheck

Claessen also developed **Lazy Smallcheck**, a variant of Smallcheck that uses lazy evaluation to generate finite test data exhaustively.

### Testing Research

Claessen's research focuses on:
- Property-based testing techniques
- Test generation strategies
- Shrinking algorithms
- Testing for functional and lazy languages

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2000 | "QuickCheck: A Lightweight Tool for Random Testing of Haskell Programs" (with Hughes) | Paper | [→](../works/papers/hughes-claessen-2000-quickcheck.md) |

## Influence

### Influenced by

- **John Hughes** — collaboration on QuickCheck
- **Haskell community** — type system, functional approach

### Influenced

- **Property-based testing ecosystem** — every major language now has a QuickCheck-inspired library
- **Testing tools** — the pattern of random generation + shrinking is now standard in advanced testing tools

## Further Reading

- [Chalmers: Koen Claessen](https://www.cse.chalmers.se/~koen/)

## Related Pages

- [QuickCheck](../works/papers/hughes-claessen-2000-quickcheck.md)
- [John Hughes](john-hughes.md)
- [Property-Based Testing](../topics/process/#property-based-testing)
