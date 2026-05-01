# On Computable Numbers, with an Application to the Entscheidungsproblem

| | |
|---|---|
| **Author** | Alan Turing |
| **Year** | 1936 |
| **Publication** | *Proceedings of the London Mathematical Society*, 2nd series, 42: 230–265 |
| **Topic(s)** | Computability theory, Turing machines, Entscheidungsproblem |
| **PDF** | [London Mathematical Society](https://www.lms.ac.uk/) |

## Summary

In this seminal paper, Alan Turing introduced the concept of the **Turing machine** — an abstract mathematical model of computation. Using this model, Turing proved that the **Entscheidungsproblem** (decision problem), posed by David Hilbert, is undecidable.

A few months earlier, Alonzo Church had proved the same result using **lambda calculus**. Turing then proved that his machines and Church's lambda calculus define **exactly the same class** of computable functions. This equivalence led to the **Church-Turing thesis**, which defines the modern concept of computation.

## Key Ideas

### 1. The Turing Machine

Turing introduced a simple abstract machine consisting of:

| Component | Description |
|-----------|-------------|
| **Tape** | Infinitely long, divided into cells, each containing a symbol |
| **Head** | Reads and writes symbols, moves left or right |
| **State register** | Stores the current state of the machine |
| **Transition table** | Rules determining what to write, where to move, and what state to enter |

The machine operates based on a finite set of rules that specify, for each state and symbol read, what symbol to write, which direction to move, and what state to transition to.

Example — a simple rule: "In state A, if symbol 0 is read, write 1, move right, and go to state B."

### 2. Computable Numbers

Turing defined a "computable number" as one whose decimal expansion can be calculated to arbitrary precision by a finite algorithm. He showed that:

- The set of computable numbers is countably infinite (like the integers)
- The set of all real numbers is uncountably infinite (Cantor's diagonal argument)
- Therefore, **almost all real numbers are uncomputable**

This was a profound result: mathematics is full of numbers that can never be written down or calculated, even in principle.

### 3. The Halting Problem

Within the paper, Turing proved that the halting problem is undecidable: there is no general algorithm that can determine whether any given Turing machine will halt or run forever on any given input.

To prove this, Turing used a **diagonal argument** similar to Cantor's proof that the real numbers are uncountable. He constructed a machine that, if it could decide whether any machine halts, would lead to a contradiction when applied to itself.

### 4. Church-Turing Equivalence

Turing proved that any function that can be computed by lambda calculus can be computed by a Turing machine, and vice versa. This established that:

- **Lambda calculus** and **Turing machines** are equivalent models of computation
- Any "effective procedure" (algorithm) can be performed by a Turing machine
- The Church-Turing thesis: anything computable can be computed by these models

This equivalence gave us two complementary ways to think about computation:
- **Turing machines** → mechanical, step-by-step computation (imperative programming)
- **Lambda calculus** → algebraic, function-based computation (functional programming)

## Historical Context

David Hilbert, in his 1928 address, posed three questions:

1. **Completeness**: Is mathematics complete? Can every true statement be proved?
2. **Consistency**: Is mathematics consistent? Can no false statement be proved?
3. **Decidability**: Is mathematics decidable? Is there an algorithm to determine truth?

Kurt Gödel shattered the first two hopes in 1931 with his **incompleteness theorems**. Turing (and Church) shattered the third in 1936 with their proofs that the **Entscheidungsproblem** is undecidable.

Hilbert had hoped for a finite, complete, and decidable foundation for mathematics. Gödel, Church, and Turing proved this was impossible — mathematics is inherently incomplete and undecidable.

## Impact and Legacy

### Theoretical Computer Science

Turing's paper is considered the founding document of theoretical computer science. Every computer program runs on a machine that is, in essence, a Turing machine.

### The Church-Turing Thesis

The thesis that any effectively computable function can be computed by a Turing machine has become the standard definition of "computation." It's been tested against every proposed model of computation — quantum computing, DNA computing, neural networks — and none have been found to exceed the power of Turing machines (in terms of what's computable, though speed may vary).

### Practical Computing

While Turing machines are abstract, they capture the essence of computation:
- **Stored programs**: Both data and instructions can be stored on the tape
- **Universality**: A universal Turing machine can simulate any other Turing machine (the basis for programmable computers)

### The Halting Problem

The undecidability of the halting problem has profound practical implications:
- **No perfect debugger**: No tool can find all bugs in all programs
- **No perfect optimiser**: No compiler can always produce optimal code
- **No perfect verification**: No algorithm can verify that all programs satisfy their specifications

These are not engineering limitations — they're fundamental limits of computation.

## Connections

- **Builds on:** Gödel's incompleteness theorems, Hilbert's Entscheidungsproblem
- **Contemporary:** Church — "An Unsolvable Problem..." (1936)
- **Led to:** [Church-Turing thesis](../../topics/functional/) · Universal Turing machine · All of computer science
- **Author:** [Alan Turing](../../authors/alan-turing.md)
- **Related topic:** [Computability Theory](../../topics/functional/) · [Alonzo Church](../../authors/alonzo-church.md) · [Kurt Gödel](../../authors/kurt-godel.md)
