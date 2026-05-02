# A Philosophy of Software Design

| | |
|---|---|
| **Author** | John Ousterhout |
| **Year** | 2018 |
| **Publisher** | Yaknyam Press |
| **Topic(s)** | Design, complexity, modularity |
| **ISBN** | 978-1-733-08368-7 |

## Summary

Ousterhout formulates design as a fight against **complexity**:
complexity grows not from lines of code but from cognitive load.

His main contribution — the concept of **deep modules**:
- simple interface
- powerful/complex implementation inside
- "lots of hidden complexity per API unit"

## Key Ideas

### 1) Complexity is incremental

Complexity accumulates through small decisions:
- "temporarily" added a flag
- "quickly" pushed a dependency
- "later" we'll figure out the API

### 2) Deep vs Shallow modules

| Type | Interface | Implementation | Effect |
|---|---|---|---|
| Deep | simple | complex | reduces system complexity |
| Shallow | complex | simple | shifts complexity to user |

### 3) Information hiding as primary tool

Echoes Parnas:
hide what can change, and what clients don't need to know.

### 4) Comments as part of design

Good comments explain **why** and **invariants**, not repeat code.

## Connections

- **Builds on:** Parnas modularity
- **Related:** SOLID, Refactoring
- **Related topics:** [OOP & Design](../../topics/design/index.md) · [Architecture](../../topics/architecture/index.md)
