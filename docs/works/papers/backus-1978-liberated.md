# Can Programming Be Liberated from the von Neumann Style?

| | |
|---|---|
| **Author** | John Backus |
| **Year** | 1978 |
| **Publication** | *Communications of the ACM*, 21(8), 613–641 (Turing Award Lecture) |
| **Topic(s)** | Functional programming, paradigms, language design |
| **PDF** | [Local copy](../../../library/open-access-papers/backus-1978-liberated.pdf) · [ACM](https://dl.acm.org/doi/10.1145/359576.359579) |

## Summary

In his 1977 Turing Award lecture (published 1978), John Backus —
creator of **Fortran**, most successful imperative language — argued
that the entire imperative programming paradigm was fundamentally limited.
He proposed **functional programming** as an alternative that enables
mathematical reasoning about programs.

This is one of the most remarkable documents in computing history:
the father of imperative programming arguing against his own creation.

## Key Ideas

### 1. The von Neumann Bottleneck

Backus identified a deep problem with conventional (imperative) programming:
both hardware and software are organised around **von Neumann
architecture** — a processor connected to memory by a narrow channel
(the "bus" or "bottleneck") through which data moves word by word.

Programming languages mirror this architecture:

- **Variables** = memory cells
- **Assignment** (`x = x + 1`) = storing a word
- **Sequential execution** = the processor stepping through instructions
- **Loops** = repeated traversal of memory

This "word-at-a-time" style forces programmers to think at the level of
individual memory operations, even when the problem calls for thinking
about entire data structures.

> "Surely there must be a less primitive way of making big changes
> in the store than pushing vast numbers of words back and forth
> through the von Neumann bottleneck."

### 2. The Problem with Statements and State

Backus argued that programs built from **statements** (assignments,
branches, loops) have three fundamental problems:

**a) They cannot be reasoned about algebraically.**

In mathematics, we can manipulate expressions using algebraic laws:
`a + b = b + a`, `a × (b + c) = a × b + a × c`. These laws let us
transform expressions confidently.

Imperative programs have no such laws. The meaning of `x = x + 1`
depends on the current value of `x`, which depends on everything
that happened before. You can't rearrange statements freely.

**b) They are history-sensitive.**

The result of an imperative computation depends on the entire history
of state changes. To understand what a program does at line 100, you
may need to trace execution from line 1.

**c) They couple computation to storage.**

Imperative programs conflate *what* is computed with *where* it is
stored. This makes it hard to separate concerns, parallelise, or
optimise.

### 3. The Functional Alternative

Backus proposed an **FP system** (Functional Programming system) where
programs are built by **combining functions** rather than sequencing
statements:

**Combining forms** (ways to build new functions from existing ones):

| Form | Notation | Meaning |
|------|----------|---------|
| Composition | `f ∘ g` | Apply g, then apply f to the result |
| Construction | `[f, g]` | Apply f and g to the same input, return both results |
| Apply-to-all | `αf` | Apply f to every element of a sequence |
| Condition | `p → f; g` | If p is true, apply f; otherwise apply g |
| Insert | `/f` | Reduce a sequence using f (fold) |

**Example — inner product of two vectors:**

In FP notation:
```
IP ≡ (/+) ∘ (α×) ∘ trans
```

Read right to left:
1. `trans` — transpose (zip) the two vectors into pairs
2. `α×` — multiply each pair
3. `/+` — sum the results

No variables. No loops. No assignment. Just function composition.

### 4. An Algebra of Programs

The most radical claim: because FP programs are built from functions
using combining forms, they obey **algebraic laws** that can be used to
**prove properties** and **transform programs**:

```
f ∘ (g ∘ h) = (f ∘ g) ∘ h          -- composition is associative
αf ∘ αg = α(f ∘ g)                  -- map fusion
(/f) ∘ αg = (/f) ∘ α(f ∘ g)        -- fold-map fusion
```

These laws enable **equational reasoning**: proving that two programs
are equivalent by algebraic manipulation, just like in mathematics.

Imperative programs have no comparable algebra.

## Historical Context

### The Irony

Backus created Fortran (1957) — the language that proved imperative,
von Neumann-style programming was practical. For 20 years, he was the
most prominent figure in imperative language design.

Then he used his Turing Award lecture to argue that the entire approach
was wrong.

This intellectual honesty — publicly critiquing your own most successful
creation — gives this paper extraordinary credibility. Backus wasn't an
FP purist attacking imperative programming from outside; he was an insider
who knew its limitations intimately.

### What Existed at the Time

- **Lisp** (1958) — existed but was seen as an AI language, not a
  paradigm-defining one
- **APL** (1962) — Iverson's array language, which Backus admired for
  its whole-array operations
- **Lambda calculus** (1936) — theory existed but had not been
  connected to practical programming style

Backus's contribution was to **frame the argument**: not just "FP is
nice" but "imperative programming is fundamentally limited, and here's
the specific alternative."

## Impact and Legacy

### Direct Influence

- **John Hughes** — "Why Functional Programming Matters" (1989) extended
  Backus's argument with lazy evaluation and higher-order functions as
  modularity tools
- **Haskell** — designed to be a pure FP language Backus envisioned
- **ML family** — practical typed FP with algebraic reasoning Backus wanted
- **Rich Hickey** — Clojure embodies Backus's critique of state and assignment

### The FP Revolution (Delayed)

Backus's paper was ahead of its time. In 1978, hardware was expensive
and FP was seen as impractical. It took decades for his vision to become
mainstream:

- 1990s — Haskell, growing academic interest
- 2000s — Erlang's industrial success, Scala, Clojure
- 2010s — FP features in mainstream languages (Java 8 lambdas, JavaScript
  arrow functions, Python comprehensions, Rust's functional style)
- 2020s — immutability, pure functions, and composition are now
  standard design advice even in OOP languages

### What Backus Got Right

- Assignment and mutable state are the root cause of most software
  complexity
- Function composition is a more powerful structuring tool than
  statement sequencing
- Programs should be amenable to mathematical reasoning

### What Backus's FP System Lacked

Backus's specific FP system was not widely adopted because:

- It was **point-free** (no named variables) — difficult to read for
  complex programs
- It lacked **types** — the ML/Haskell approach of typed FP proved
  more practical
- It lacked **lazy evaluation** — Hughes showed this was a key missing
  modularity tool

Modern FP languages (Haskell, ML, Clojure) take Backus's philosophical
position but use different technical mechanisms.

## Connections

- **Builds on:** Church — Lambda Calculus (1936); Iverson — APL notation
- **Led to:** [Hughes — "Why FP Matters" (1989)](hughes-1989-why-fp.md) ·
  Haskell (1990) · Clojure (2007)
- **Author:** [John Backus](../../authors/john-backus.md)
- **Related topic:** [Functional Programming](../../topics/functional/) ·
  [Paradigms](../../topics/paradigms/)
