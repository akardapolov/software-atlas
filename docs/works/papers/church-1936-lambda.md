# An Unsolvable Problem of Elementary Number Theory

| | |
|---|---|
| **Author** | Alonzo Church |
| **Year** | 1936 |
| **Publication** | *American Journal of Mathematics*, 58(2), 345–363 |
| **Topic(s)** | Lambda calculus, computability theory, mathematical logic |
| **PDF** | [JSTOR](https://www.jstor.org/stable/2371045) (subscription) |

## Summary

In this paper, Alonzo Church proved that the **Entscheidungsproblem**
(decision problem) — posed by David Hilbert — is unsolvable. There is
no general algorithmic procedure that can determine whether an arbitrary
mathematical statement is true or false.

To prove this, Church developed the **lambda calculus** — a formal
system for defining and applying functions — and showed that certain
problems expressible in this system have no algorithmic solution.

This result, published just before Alan Turing's independent proof using
Turing machines, established the fundamental limits of computation and
gave birth to the theory of computability.

## Key Ideas

### 1. Lambda Calculus as a Model of Computation

Church introduced a system built on three constructs:

| Construct | Notation | Meaning |
|-----------|----------|---------|
| Variable | `x` | A name referring to a value |
| Abstraction | `λx. M` | A function with parameter x and body M |
| Application | `M N` | Applying function M to argument N |

Example — the identity function:

```
λx. x
```

Applied to some argument `a`:

```
(λx. x) a → a
```

Despite having only three constructs, this system can express:

- **Booleans:** `TRUE = λx. λy. x` and `FALSE = λx. λy. y`
- **Natural numbers:** `0 = λf. λx. x`, `1 = λf. λx. f x`,
  `2 = λf. λx. f (f x)` (Church numerals)
- **Arithmetic:** addition, multiplication, exponentiation
- **Conditionals, recursion, data structures** — all as pure functions

### 2. The Undecidability Result

Church showed that there is no **effective procedure** (algorithm) that
can determine, for every pair of lambda terms, whether they are
equivalent. Specifically, the problem of deciding whether a lambda term
has a **normal form** (a fully reduced result) is undecidable.

This means there are well-defined mathematical questions that no
algorithm can answer — a profound limitation on what computation can
achieve.

### 3. Church–Turing Thesis

A few months later, Alan Turing (Church's doctoral student) published
his own proof using a different model — **Turing machine**. Turing
then proved that his machines and Church's lambda calculus define
**exactly the same class** of computable functions.

This equivalence led to the **Church–Turing thesis**: any function
that can be computed by any mechanical process can be computed by a
Turing machine (equivalently, expressed in lambda calculus). This thesis
defines the modern concept of "computability."

## Historical Context

In 1928, David Hilbert posed the Entscheidungsproblem as part of his
programme to formalise all of mathematics. He asked: is there a
mechanical procedure that can decide the truth of any mathematical
statement?

Hilbert expected to answer to be "yes." Church (1936) and Turing (1936)
independently proved it was "no" — and in doing so, they created the
theoretical foundations of computer science.

Church's approach was algebraic (lambda calculus), while Turing's was
mechanical (an abstract machine with a tape). The two approaches turned
out to be equivalent, giving us two complementary ways to think about
computation:

- **Lambda calculus** → functional programming
- **Turing machines** → imperative programming

## Impact and Legacy

### Functional Programming

Every functional programming language is a practical realisation of
lambda calculus:

| Lambda calculus concept | Programming language feature |
|------------------------|------------------------------|
| Abstraction (`λx. M`) | Function definition |
| Application (`M N`) | Function call |
| Free variables | Closures |
| Beta reduction | Function evaluation |
| Church numerals | Data as functions |
| No mutation | Immutability |

### Type Theory

Church later developed the **simply-typed lambda calculus** (1940),
which added types to lambda terms. This led to:

- Hindley–Milner type inference (ML, Haskell)
- System F (polymorphic lambda calculus, generics)
- The Curry–Howard correspondence (proofs = programs, types = propositions)

### Programming Language Theory

Lambda calculus became to standard tool for:

- **Denotational semantics** — giving mathematical meaning to programs
- **Compiler theory** — intermediate representations often use lambda calculus
- **Language design** — new language features are often prototyped in lambda calculus first

## Connections

- **Builds on:** Hilbert's Entscheidungsproblem, Frege's function logic
- **Contemporary:** Turing — "On Computable Numbers" (1936)
- **Led to:** [McCarthy — Lisp (1958)](../../languages/lisp/index.md) ·
  [Milner — ML (1978)](../../languages/ml/index.md) ·
  [Haskell (1990)](../../languages/haskell/index.md) ·
  All functional programming
- **Author:** [Alonzo Church](../../authors/alonzo-church.md)
- **Related topic:** [Functional Programming](../../topics/functional/index.md) ·
  [Type Systems](../../topics/types/index.md) ·
  [Paradigms](../../topics/paradigms/index.md)
