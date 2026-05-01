# On Formally Undecidable Propositions of Principia Mathematica and Related Systems

| | |
|---|---|
| **Author** | Kurt Gödel |
| **Year** | 1931 |
| **Publication** | *Monatshefte für Mathematik und Physik*, 38: 173–198 |
| **Topic(s)** | Mathematical logic, incompleteness theorems, formal systems |
| **PDF** | [Various sources online](https://en.wikipedia.org/wiki/G%C3%B6del%27s_incompleteness_theorems) |

## Summary

In this groundbreaking paper, Kurt Gödel proved his **incompleteness theorems** — results that fundamentally changed our understanding of mathematics, logic, and computation. Gödel showed that any sufficiently powerful formal system contains statements that are true but cannot be proved within the system itself.

This result shattered David Hilbert's dream of a complete and provably consistent foundation for mathematics, and established inherent limitations on what formal systems can achieve.

## Key Ideas

### 1. Gödel Numbering

Gödel's key technical innovation was a method for encoding logical formulas as natural numbers. Each symbol in the formal language is assigned a unique number, and each formula is encoded using prime factorisation.

Example encoding:

| Symbol | Gödel Number |
|--------|--------------|
| 0 | 1 |
| S (successor) | 2 |
| = | 3 |
| ¬ (not) | 4 |
| → (implies) | 5 |

For a formula like "¬(0 = 0)", we encode each symbol and use prime powers:

```
¬(0 = 0) → 2^4 × 3^1 × 5^3 × 7^1 × 11^3 = 2,160,900
```

This allowed Gödel to talk about formulas **within** the formal system itself, creating self-reference.

### 2. The Gödel Sentence

Using Gödel numbering, Gödel constructed a special sentence G that says:

> "This sentence cannot be proved."

If G is provable, then it's false (because it says it can't be proved). If G is not provable, then it's true (because it correctly states that it can't be proved). Therefore:

- **G is true but unprovable** within the system

This is the **First Incompleteness Theorem**: any consistent formal system powerful enough for elementary arithmetic contains true statements that cannot be proved.

### 3. The Second Incompleteness Theorem

Gödel's second theorem states that no consistent system powerful enough for arithmetic can prove its own consistency.

In other words: **A system cannot prove that it won't produce contradictions.**

To prove consistency, you need a stronger system. But that stronger system also can't prove its own consistency. This creates an infinite hierarchy of systems, each relying on the one above.

### 4. Consistency Assumption

Gödel's proofs assume that the formal system is **consistent** — it doesn't prove contradictions. If a system is inconsistent, it can prove anything (by the principle of explosion), so the concept of "unprovable truth" becomes meaningless.

This is why the second theorem is significant: it tells us that **consistency cannot be established from within**.

## Historical Context

In the early 20th century, David Hilbert launched his program to establish mathematics on a firm, formal foundation. Hilbert's goals were:

1. **Completeness**: Every true mathematical statement can be proved
2. **Consistency**: Mathematics contains no contradictions
3. **Decidability**: There's an algorithm to determine truth of any statement

Hilbert famously declared: *"We must know. We shall know."*

Gödel's 1931 paper shattered the first two goals:

- **Incompleteness**: Some true statements cannot be proved
- **Unprovability of consistency**: A system cannot prove its own consistency

Later, Church and Turing (1936) showed the third goal was also impossible — the Entscheidungsproblem is undecidable.

## Impact and Legacy

### Foundations of Mathematics

Gödel's theorems revealed fundamental limitations of formal systems. Mathematics is not a closed, complete system that can be fully captured by axioms and rules.

### Computer Science

Gödel numbering is the foundation of encoding information — every computer program, digital image, and encrypted file is ultimately a number. The techniques Gödel developed for self-reference and encoding are essential to:

- **Computability theory**: Understanding what can and cannot be computed
- **Proof theory**: Understanding the structure of mathematical proofs
- **Type theory**: The connection between types and logic (Curry-Howard correspondence)

### The Church-Turing Thesis

Gödel's work directly influenced Alonzo Church and Alan Turing. Both Church (using lambda calculus) and Turing (using Turing machines) proved the undecidability of the Entscheidungsproblem, building on Gödel's insights about formal systems.

### Philosophical Implications

Gödel's theorems have profound philosophical implications:

- **Limits of formal systems**: No formal system can capture all mathematical truth
- **Human vs. machine**: Some argue that Gödel's results show human minds can see truths that machines cannot prove
- **The nature of mathematics**: Mathematics is not just a game of formal manipulation — it has content beyond any axiomatisation

## Connections

- **Builds on:** Hilbert's program, Principia Mathematica (Russell & Whitehead)
- **Influenced:** [Alonzo Church](../../authors/alonzo-church.md) · [Alan Turing](../../authors/alan-turing.md) · [Church-Turing thesis](../../topics/functional/)
- **Related author:** [Kurt Gödel](../../authors/kurt-godel.md)
- **Related topic:** [Type Systems](../../topics/types/) · [Mathematical Logic](../../topics/types/)
