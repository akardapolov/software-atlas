# Communicating Sequential Processes

| | |
|---|---|
| **Author** | C.A.R. Hoare |
| **Year** | 1985 |
| **Publication** | Prentice-Hall |
| **Topic(s)** | Concurrency, process algebra, formal methods |

## Summary

*"Communicating Sequential Processes"* is Hoare's book-length treatment of the CSP process algebra he introduced in 1978. This formal work provides the mathematical foundation for reasoning about concurrent and distributed systems.

## Key Ideas

### CSP Expanded

The book expands on the original 1978 paper with:

- **Formal semantics** — complete mathematical treatment
- **Divergence** — non-terminating processes
- **Determinism** — choice operators and their properties
- **Refinement** — proving implementations match specifications

### Formal Verification

Hoare introduces methods for:

- **Checking processes** — verify communication correctness
- **Proving properties** — deadlock freedom, livelock
- **Refinement calculus** — stepwise correctness proofs

This formal approach enabled:

- **Verifying concurrent systems** — mathematically proving properties
- **Language-based verification** — CSP-based verification tools
- **Model checking** — exhaustive state exploration

### Design Guidance

Beyond theory, the book discusses:

- **Practical CSP programming** — how to structure real systems
- **Language design** — incorporating CSP features
- **Pattern applications** — bounded buffers, pipelines

## Historical Significance

### Foundation for Formal Methods

The book established CSP as a foundational formal method:

- **Concurrent systems theory** — mathematical basis for concurrency
- **Verification tools** — FDR (Failures-Divergence Refinement)
- **Language design** — occam, Go, others built on CSP

### Influence on Languages

| Language | How CSP Appears |
|-----------|-----------------|
| occam (1983) | Directly implements CSP with PAR, SEQ, ALT |
| Go (2009) | Goroutines + channels + `select` |
| Clojure core.async (2013) | `go` blocks + channels + `alt!` |
| Kotlin coroutines (2018) | Channels with `select` |

## Legacy

Hoare's CSP theory remains influential:

- **Concurrency theory** — alongside Actor model
- **Language design** — message-passing languages
- **Verification** — formal methods for distributed systems

## Connections

- **Builds on:** [CSP paper (1978)](../papers/hoare-1978-csp.md)
- **Influenced:** occam, Go, Clojure, verification tools
- **Related topic:** [Concurrency](../../topics/concurrency/index.md)
- **Author:** [Tony Hoare](../../authors/tony-hoare.md)
