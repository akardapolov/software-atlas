# Go To Statement Considered Harmful

| | |
|---|---|
| **Author** | Edsger W. Dijkstra |
| **Year** | 1968 |
| **Publication** | *Communications of the ACM*, 11(3), 147–148 |
| **Topic(s)** | Structured programming, control flow |
| **PDF** | [Local copy](../../../library/open-access-papers/dijkstra-1968-goto.pdf) · [ACM](https://dl.acm.org/doi/10.1145/362929.362947) |

## Summary

In this remarkably short letter (barely two pages), Dijkstra argued that
the `goto` statement should be **abolished** from high-level programming
languages because it makes programs almost impossible to reason about.

The letter catalysed the **structured programming** revolution — one of
the most significant shifts in software development history. Within a
decade, `goto` went from ubiquitous to deprecated or absent in most
new languages.

## Key Ideas

### 1. The Quality of Programs Depends on Control Flow

Dijkstra observed that a programmer's ability to understand a program
depends on being able to trace its execution mentally. When a program
uses only **sequential composition**, **selection** (if/else), and
**iteration** (while/for), the control flow mirrors the textual
structure — you can understand the program by reading it top to bottom.

When `goto` is available, control can jump **anywhere**. The textual
structure no longer reflects the execution flow. Reading the code
tells you almost nothing about what it does.

### 2. The Coordinate System for Progress

Dijkstra introduced the idea of a "textual index" — your position in
the source code — as a coordinate for understanding where the program
is during execution:

- With **sequential code**, one coordinate (line number) suffices
- With **procedure calls**, you need a call stack (a sequence of positions)
- With **loops**, you need to line number plus a loop variable
- With **`goto`**, the number of possible positions is essentially
  unlimited and unpredictable

The key insight: **structured control flow** limits the relationship
between source code position and program state to something a human
can reason about. `goto` destroys this relationship.

### 3. Programmer Skill Is Limited

Dijkstra framed the argument not as a matter of preference but as a
consequence of **human cognitive limitations**:

> "Our intellectual powers are rather geared to master static
> relations and our powers to visualize processes evolving in time
> are relatively poorly developed."

Since humans are better at understanding structure than tracing
execution, we should write programs whose structure makes execution
obvious.

## Historical Context

### Before This Paper

In 1968, most programming was done in assembly language, Fortran, or
COBOL. All of these relied heavily on `goto` (or its equivalent —
`JUMP`, `GO TO`). Even simple loops were written as:

```fortran
C     Fortran — before structured programming
      I = 0
   10 I = I + 1
      IF (I .GT. 10) GO TO 20
      TOTAL = TOTAL + I
      GO TO 10
   20 CONTINUE
```

This style produced "spaghetti code" — programs where the flow of
control tangled into an unreadable mess.

### The Title

The famous title — "X Considered Harmful" — was actually chosen by
Niklaus Wirth (the editor), not by Dijkstra. Dijkstra's submitted title
was "A Case against the GO TO Statement." Wirth's more provocative
phrasing became one of the most imitated title templates in computing
("X Considered Harmful" has been used hundreds of times since).

### The Reaction

The paper was enormously controversial. Donald Knuth responded with
"Structured Programming with go to Statements" (1974), arguing for a
more moderate position — that `goto` is occasionally useful but should
be used sparingly and systematically.

The structured programming movement ultimately won. By the 1980s, most
new languages either lacked `goto` entirely (Smalltalk, ML, Haskell,
Python) or strongly discouraged its use (C, where `goto` exists but
is rarely used).

## Impact and Legacy

### Immediate Impact

- **Structured programming** became the dominant paradigm
- New languages adopted `if/else`, `while`, `for` as primary constructs
- Pascal (Wirth, 1970) was designed explicitly around structured principles
- The Böhm–Jacopini theorem (1966) proved that `goto` is unnecessary —
  any program can be expressed with sequence, selection, and iteration

### Long-Term Impact

- **Readability as a design goal** — Dijkstra established that programs
  should be written for humans to read, not just for machines to execute
- **Structured programming → OOP → FP** — each paradigm further
  restricts uncontrolled state and control flow
- **The "Considered Harmful" genre** — created a template for provocative
  technical arguments

### What Dijkstra Got Right

- Programs should have clear, predictable structure
- Human cognitive limitations should guide language design
- Fewer ways to express a program → easier to understand

### The Modern Perspective

Most modern languages have no `goto`. The exceptions:

- **C/C++** — `goto` exists, used rarely (cleanup in C, error handling)
- **Go** — has `goto` but restricts it (cannot jump over variable declarations)
- **C#** — has `goto` for switch statement fall-through

Even in these languages, `goto` usage is considered a code smell.
The structured programming revolution is complete.

## Connections

- **Builds on:** Böhm–Jacopini theorem (1966), Dijkstra's own work on THE system
- **Led to:** [Parnas — Information Hiding (1972)](parnas-1972-modules.md) ·
  Wirth — Pascal (1970) ·
  Structured Programming book (Dijkstra, Dahl, Hoare, 1972)
- **Author:** [Edsger Dijkstra](../../authors/edsger-dijkstra.md)
- **Related topic:** [Paradigms](../../topics/paradigms/) ·
  [Architecture & Modularity](../../topics/architecture/)
