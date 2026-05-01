# John Hughes

| | |
|---|---|
| **Born** | 1958 |
| **Fields** | Functional programming, property-based testing |
| **Known for** | "Why Functional Programming Matters", QuickCheck, Haskell design |

## Biography

John Hughes is a British-Swedish computer scientist and a professor at
Chalmers University of Technology in Gothenburg, Sweden. He is known for
two major contributions: the paper "Why Functional Programming Matters"
(1989), which provided the clearest practical argument for FP, and
**QuickCheck** (2000), which pioneered property-based testing.

Hughes was a member of the Haskell committee and co-designed several
features of the language. He later co-founded Quviq, a company that
applies property-based testing to industrial systems (including testing
Ericsson's telecom software, Volvo's autonomous driving systems, and
AUTOSAR automotive standards).

## Key Contributions

### "Why Functional Programming Matters" (1989)

This paper is the best practical argument for functional programming ever
written. While Backus's 1978 lecture argued on theoretical grounds, Hughes
argued on **engineering** grounds — specifically, **modularity**.

Hughes's thesis: functional programming provides two powerful "glues" for
building modular programs:

1. **Higher-order functions** — functions that take other functions as
   arguments. This allows you to separate *what to do* from *how to
   traverse a data structure*. For example, `foldr` captures the pattern
   of processing a list element-by-element; the caller provides only the
   specific operation.

2. **Lazy evaluation** — expressions are evaluated only when their results
   are needed. This allows you to separate *generation* from *selection*.
   You can generate a potentially infinite structure (all moves in a game
   tree) and let the consumer decide how much to evaluate (search to
   depth N).

These glues enable **modular solutions that are impossible in imperative
languages** — you can write general-purpose components and compose them
in ways that cross-cutting concerns prevent in imperative code.

The paper demonstrates this with concrete examples:

- Newton-Raphson square root as lazy list generation + convergence detection
- Alpha-beta game tree search as tree generation + pruning + evaluation

### QuickCheck (2000)

QuickCheck, created with Koen Claessen, introduced **property-based
testing**: instead of writing individual test cases, the programmer writes
**properties** — logical statements that should hold for all inputs. The
framework then generates random inputs automatically and searches for
counterexamples.

Example (Haskell):
```haskell
prop_reverse :: [Int] -> Bool
prop_reverse xs = reverse (reverse xs) == xs
```

QuickCheck generates hundreds of random lists and checks that reversing
twice always yields the original. When a counterexample is found,
QuickCheck **shrinks** it to the smallest failing case.

Property-based testing has been ported to every major language:

- **Hypothesis** (Python)
- **ScalaCheck** (Scala)
- **test.check** (Clojure)
- **FsCheck** (F#)
- **fast-check** (TypeScript/JavaScript)
- **proptest** (Rust)
- **Rapid** (Go)

### Haskell Design

Hughes served on the Haskell committee and contributed to the design of
type classes, monadic IO, and the overall language philosophy of
principled, pure functional programming.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1989 | "Why Functional Programming Matters" | Paper | [→](../works/papers/hughes-1989-why-fp.md) |
| 2000 | "QuickCheck: A Lightweight Tool for Random Testing of Haskell Programs" (with Claessen) | Paper | [→](../works/papers/hughes-claessen-2000-quickcheck.md) |
| 2007 | "QuickCheck Testing for Fun and Profit" | Paper | — |

## Influence

### Influenced by

- **John Backus** — FP manifesto provided the motivation; Hughes provided the engineering argument
- **Robin Milner** — ML's type system and functional approach
- **Peter Henderson** — functional geometry and modular design

### Influenced

- **Haskell community** — the paper is often the first recommended FP reading
- **Property-based testing** — an entire testing methodology now used in industry
- **Rich Hickey** — Clojure's emphasis on composition and simplicity echoes Hughes's modularity argument
- **David MacIver** — created Hypothesis (Python QuickCheck) based on Hughes's ideas

## Quotes

> "Functional programs deal with values, not effects, making them
> easier to understand and to reason about."

> "The ways in which one can divide up the original problem depend
> directly on the ways in which one can glue solutions together."

## Further Reading

- [Wikipedia: John Hughes](https://en.wikipedia.org/wiki/John_Hughes_(computer_scientist))
- [Chalmers: John Hughes](https://www.cse.chalmers.se/~rjmh/)
- [Why FP Matters (PDF)](https://www.cs.kent.ac.uk/people/staff/dat/miranda/whyfp90.pdf)
- [Quviq (QuickCheck company)](http://www.quviq.com/)

## Related Pages

- [Why FP Matters](../works/papers/hughes-1989-why-fp.md)
- [Functional Programming](../topics/functional/)
- [Haskell](../languages/haskell/)
- [John Backus](john-backus.md)
- [Robin Milner](robin-milner.md)
