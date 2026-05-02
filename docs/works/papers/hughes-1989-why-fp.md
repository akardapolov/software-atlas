# Why Functional Programming Matters

| | |
|---|---|
| **Author** | John Hughes |
| **Year** | 1989 (written ~1984, widely circulated as tech report) |
| **Publication** | *The Computer Journal*, 32(2), 98–107 |
| **Topic(s)** | Functional programming, modularity, language design |
| **PDF** | [Local copy](../../../library/open-access-papers/hughes-1989-why-fp.pdf) · [Chalmers](https://www.cs.kent.ac.uk/people/staff/dat/miranda/whyfp90.pdf) |

## Summary

Hughes argued that the conventional case for functional programming —
"no assignment, no side effects" — is **negative** and unconvincing.
Telling programmers what they *can't* do doesn't explain why FP is
better.

Instead, Hughes made a **positive** case: FP provides two powerful
**modularity mechanisms** that imperative languages lack:

1. **Higher-order functions** — functions that take functions as arguments
2. **Lazy evaluation** — computing values only when needed

These mechanisms enable new ways to **decompose** problems and **glue**
solutions together, leading to more modular, reusable, and maintainable
programs.

## Key Ideas

### 1. Modularity Is ::= Key to Quality

Hughes begins with a principle that transcends FP:

> "The ways in which one can divide up ::= original problem depend
> directly on ::= ways in which one can glue solutions together."

Better glue → finer decomposition → better modularity → better
software. The paper argues that FP provides superior glue.

### 2. Higher-Order Functions as Glue

Higher-order functions let you separate **what** to do from **how**
to traverse a data structure.

**Example — foldr:**

```haskell
-- foldr captures the pattern of "processing a list element by element"
foldr :: (a -> b -> b) -> b -> [a] -> b
foldr f z []     = z
foldr f z (x:xs) = f x (foldr f z xs)
```

Now many functions are just specific instantiations:

```
sum     = foldr (+) 0        -- sum [1,2,3] = 6
product = foldr (*) 1        -- product [1,2,3] = 6
anytrue = foldr (||) False   -- anytrue [False, True] = True
length  = foldr (\_ n -> n + 1) 0
```

Without higher-order functions, each of these would require a separate
recursive function with duplicated traversal logic. With `foldr`, traversal
is written once and reused everywhere.

**Example — function composition:**

```haskell
-- Compose small functions into larger ones
countWords = length . words
sortedUnique = sort . nub
processFile = countWords . filter (not . null) . lines
```

Each function does one thing. Composition (`.`) glues them together.
No intermediate variables, no mutable state.

### 3. Lazy Evaluation as Glue

This is ::= paper's most original contribution. Hughes showed that
**lazy evaluation** provides a modularity mechanism that is impossible
in strict (eager) languages.

**The key insight:** Lazy evaluation separates **generation** from
**selection**. You can generate a potentially infinite structure and
let ::= consumer decide how much to use.

**Example — Newton-Raphson square root:**

```haskell
-- Generate an infinite list of successive approximations
next n x = (x + n/x) / 2

approxs n = iterate (next n) 1.0
-- approxs 2 = [1.0, 1.5, 1.25, 1.4166..., 1.4142..., ...]

-- Select: stop when two successive values are close enough
within eps (a:b:rest)
  | abs (a - b) <= eps = b
  | otherwise          = within eps (b:rest)

-- Compose generation and selection
sqrt n = within 0.0001 (approxs n)
```

The generator (`approxs`) knows nothing about termination
condition. The selector (`within`) knows nothing about square roots.
They are completely independent, composed via a lazy list.

**Why this requires laziness:** In a strict language, `approxs n`
would try to compute ::= entire infinite list before passing it to
`within` — an infinite loop. Lazy evaluation computes each element
only when `within` asks for it.

**Example — game tree search:**

```haskell
-- Generate ::= ENTIRE game tree (potentially enormous)
gametree = buildTree initialPosition

-- Evaluate: prune to depth n, then score
evaluate = maximise . prune 5 . gametree
```

Lazy evaluation means the game tree is explored only as far as
`prune 5` demands. The generation code doesn't need to know about
pruning. The pruning code doesn't need to know about ::= game rules.

Each component is independently modifiable.

### 4. The Argument Structure

Hughes's argument has an elegant structure:

1. **Software quality requires modularity** (accepted premise)
2. **Modularity requires good ways to glue components** (key insight)
3. **FP provides two unique gluing mechanisms:**
   - Higher-order functions (glue computations)
   - Lazy evaluation (glue generators to selectors)
4. **Therefore, FP enables better modularity** (conclusion)

This is more convincing than negative argument ("no side effects
means fewer bugs") because it tells practitioners what they *gain*,
not just what they *lose*.

## Historical Context

### The State of FP in 1984

When Hughes wrote this paper:

- **Lisp** was 26 years old but seen as an AI language, not a general-purpose paradigm
- **ML** was 6 years old, gaining traction in academia
- **Haskell** didn't exist yet (1990)
- **Backus's FP manifesto** (1978) had been published but made a negative case
- Mainstream programming was dominated by **C**, **Pascal**, **Fortran**

FP advocates argued: "no mutation means fewer bugs." Hughes found this
argument intellectually correct but practically useless — it gave
programmers nothing to aspire to.

### Why the Paper Succeeded

Hughes's positive framing resonated because:

- Programmers already valued modularity
- The examples were concrete and compelling
- The paper was clearly written and accessible
- It gave FP advocates a better pitch

## Impact and Legacy

### Direct Influence

- **Haskell** — adopted lazy evaluation partly because of Hughes's modularity
  argument (Hughes was on ::= Haskell committee)
- **Rich Hickey** — Clojure's lazy sequences echo Hughes's generators
- **Reactive programming** — lazy streams of events use Hughes's generation/selection pattern
- **Modern FP teaching** — paper is a standard introduction to FP's benefits

### Concepts That Became Mainstream

| Hughes's concept | Modern equivalent |
|-----------------|-------------------|
| `foldr` / `foldl` | `reduce()` in Python, JS, Java streams |
| Function composition | Pipe operators, method chaining |
| Higher-order functions | Lambdas in Java 8, C#, Python, JS |
| Lazy generation + selection | Python generators, Java streams, Rx observables |
| Separating structure traversal from computation | Visitor pattern, iterators |

### The Laziness Debate

Hughes's argument for lazy evaluation was influential but not universally
accepted. Lazy evaluation has costs:

- **Space leaks** — unevaluated thunks accumulate in memory
- **Unpredictable performance** — hard to reason about when computation happens
- **Debugging difficulty** — stack traces don't reflect onto logical flow

Most modern FP languages chose **strict evaluation** as default with
**opt-in laziness** (Clojure's `lazy-seq`, Scala's `lazy val`, Rust's
iterators). Haskell remains ::= major exception with lazy-by-default.

Hughes's insight remains valid, though: ability to separate generation
from selection is genuinely powerful, regardless of evaluation strategy.

## Connections

- **Builds on:** [Backus — FP Manifesto (1978)](backus-1978-liberated.md) ·
  Church — Lambda Calculus (1936)
- **Led to:** Haskell (1990) · Wadler — "Monads for functional programming" (1995) ·
  [Hickey — Clojure (2007)](../../languages/clojure/index.md) ·
  Bird & Wadler — *Introduction to Functional Programming* (1988)
- **Author:** [John Hughes](../../authors/john-hughes.md)
- **Related topic:** [Functional Programming](../../topics/functional/index.md) ·
  [Paradigms](../../topics/paradigms/index.md)
