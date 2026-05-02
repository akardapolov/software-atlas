# Alonzo Church

| | |
|---|---|
| **Born** | 1903, Washington, D.C., USA |
| **Died** | 1995, Hudson, Ohio, USA |
| **Fields** | Mathematical logic, computability theory |
| **Known for** | Lambda calculus, Church–Turing thesis, Church encoding |

## Biography

Alonzo Church was an American mathematician and logician who spent most of
his career at Princeton University (1929–1967) and later at UCLA (1967–1990).
He is one of the founders of theoretical computer science.

Church's most famous contribution is **lambda calculus** (1936), a formal
system for expressing computation based on function abstraction and
application. He used it to prove that the Entscheidungsproblem (decision
problem) is undecidable — independently of Alan Turing, who arrived at the
same result via a different model (Turing machines). Their combined insight
became the **Church–Turing thesis**: any effectively computable function can
be computed by a Turing machine (or equivalently, expressed in lambda calculus).

Church also supervised an extraordinary list of doctoral students, including
Alan Turing, Stephen Kleene, Michael Rabin, Dana Scott, and Hartley Rogers.

## Key Contributions

### Lambda Calculus (1936)

A formal system built on just three constructs:

- **Variable**: `x`
- **Abstraction** (function definition): `λx. body`
- **Application** (function call): `f x`

Despite this extreme simplicity, lambda calculus is **Turing-complete** — it
can express any computable function. It became the theoretical foundation for:

- Functional programming languages (Lisp, ML, Haskell, etc.)
- Type theory (simply-typed lambda calculus, System F)
- Programming language semantics (denotational semantics)

### Church–Turing Thesis

The assertion that lambda calculus and Turing machines define the same class
of computable functions. This established the modern understanding of what
"computation" means.

### Church Encoding

A technique for representing data structures (booleans, natural numbers,
pairs, lists) as pure lambda terms — showing that functions alone are
sufficient to represent any data.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1936 | "An Unsolvable Problem of Elementary Number Theory" | Paper | [→](../works/papers/church-1936-lambda.md) |
| 1941 | *The Calculi of Lambda Conversion* | Monograph | — |

## Influence

### Influenced by

- **David Hilbert** — Entscheidungsproblem that Church set out to solve
- **Gottlob Frege** — function-based foundations for logic

### Influenced

- **Alan Turing** — Church's doctoral student; proved equivalence of their models
- **John McCarthy** — created Lisp (1958) directly inspired by lambda calculus
- **Robin Milner** — ML type system rooted in typed lambda calculus
- **Haskell Curry** — combinatory logic, currying; Haskell language named after him
- **All functional programming** — every FP language traces back to Church's lambda calculus

## Quotes

> "There are only two hard problems — naming and binding."
> _(paraphrased from lambda calculus concepts)_

## Further Reading

- [Wikipedia: Alonzo Church](https://en.wikipedia.org/wiki/Alonzo_Church)
- [Wikipedia: Lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus)
- [Stanford Encyclopedia of Philosophy: Church's Type Theory](https://plato.stanford.edu/entries/type-theory-church/)

## Related Pages

- [Lambda Calculus paper](../works/papers/church-1936-lambda.md)
- [Functional Programming](../topics/functional/index.md)
- [Type Systems](../topics/types/index.md)
- [Lisp](../languages/lisp/index.md)
- [Haskell](../languages/haskell/index.md)
- [John McCarthy](john-mccarthy.md)
- [Robin Milner](robin-milner.md)

## Quotes

> "I was trying to talk about how to write programs for machines, not how
> to communicate with human beings."
> _(paraphrased — Church focused on mathematical precision over memorable quotes)_

## On Church's Style

Church was known for extreme precision and formality rather than memorable
quotes. His writing is terse and mathematical. The opening of his 1936 paper
captures his approach — stating an impossibility result with no fanfare:

> "There is no recursive function of two formulas A and B which has the
> value 2 if and only if A → B is provable."

His student Stephen Kleene recalled that Church demanded absolute rigour
in every proof and every definition — a standard that shaped an entire
generation of logicians and computer scientists.
