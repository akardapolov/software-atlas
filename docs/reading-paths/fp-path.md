# Reading Path — Functional Programming

Goal: understand FP as a line of ideas (not a set of "language features"):
- computation as function application
- composition as modularity
- types/ADTs as domain modeling
- effects at the boundary (FC/IS)

## Steps

### 1) Foundation: λ-calculus
- Read: [Church (1936)](../works/papers/church-1936-lambda.md)
- Question: why are "stateless functions" a powerful computation model?

### 2) Critique of von Neumann style
- Read: [Backus (1978)](../works/papers/backus-1978-liberated.md)
- Focus: why do assignment and state hinder equivalent transformations?

### 3) Positive argument: modularity
- Read: [Hughes (1989)](../works/papers/hughes-1989-why-fp.md)
- Focus: higher-order functions and (optionally) lazy evaluation as "glue"

### 4) Modern practice: boundaries and simplicity
- Watch: [Hickey — Simple Made Easy (2011)](../works/talks/hickey-2011-simple-made-easy.md)
- Watch: [Bernhardt — Boundaries (2012)](../works/talks/bernhardt-2012-boundaries.md)

### 5) FP + DDD as an engineering modeling methodology
- Read: [Wlaschin — Domain Modeling Made Functional (2018)](../works/books/wlaschin-2018-dmf.md)

## Mini-project

Model a simple domain (e.g., "user registration"):

1. Types:
   - `EmailAddress`, `UserId`, `UnvalidatedUser`, `ValidatedUser`
2. Errors as a type:
   - `Result<ValidatedUser, ValidationError>`
3. Workflow as function composition:
   - `validate → enrich → persist → publish event`

## Related

- [Functional topic](../topics/functional/index.md)
- [Types topic](../topics/types/index.md)
- [Paradigms](../topics/paradigms/index.md)
