# Engineering

Verifiable principles for building and operating software systems.

Engineering, in this context, is distinguished from **methodology** (how teams organize work) and **architecture** (how systems are structured). Engineering focuses on **what we can know** about a system and **how we verify** that knowledge.

---

## What belongs here

Engineering practices are distinct from methodologies in one key way:

| | Methodology | Engineering Practice |
|---|---|---|
| **Question** | "How do we organize work?" | "How do we know the system behaves as claimed?" |
| **Nature** | Social, context-dependent | Verifiable, reproducible |
| **Examples** | Scrum, Kanban, XP ceremonies | Testing, observability, reliability engineering |

A practice belongs in this section when it:

- Can be **verified independently** of belief or process adherence
- Produces **falsifiable claims** about system behavior
- Connects **measurements to system state**, not just metrics to dashboards

---

## Contents

| # | Topic | Key ideas | What it answers |
|---|-------|-----------|-----------------|
| 1 | [**Verifiable Engineering**](verifiable-engineering.md) | Three questions, falsifiability, system vs event | How do we know what we claim? |
| 2 | [**Testing**](../testing/index.md) | TDD, Pyramid, PBT, BDD, mutation, fuzzing, contracts | How do we verify code behavior? |
| 3 | **Observability** *(planned)* | Logs, metrics, traces, SLOs, percentiles | How do we understand production behavior? |
| 4 | **Reliability** *(planned)* | Fault tolerance, resilience patterns, chaos engineering | How do we keep systems working under failure? |

---

## The Three Questions

At the core of engineering thinking is a simple epistemic framework:

1. **What exactly does this number measure?** — the link between procedure and phenomenon
2. **Under what conditions is this claim false?** — falsifiability as a criterion
3. **Are we looking at system behavior or an event?** — Shewhart's distinction between common and special causes

→ [Verifiable Engineering](verifiable-engineering.md) — full treatment

---

## Relationship to other sections

| This section | Connected to | How |
|--------------|--------------|-----|
| Testing | [Design](../design/index.md) | Testability as a design concern |
| Observability | [Process](../process/index.md) | DevOps, SRE practices |
| Reliability | [Architecture](../architecture/index.md) | Resilience patterns, circuit breakers |
| Verifiable Engineering | [Distributed Systems](../distributed/index.md) | CAP, consensus as falsifiable claims |

---

## Further Reading

- [Walter Shewhart](../../authors/walter-shewhart.md) — Statistical quality control
- [W. Edwards Deming](../../authors/deming.md) — Systems thinking, PDCA
- [Percy Bridgman](../../authors/percy-bridgman.md) — Operationalism
- [Kent Beck](../../authors/kent-beck.md) — TDD as verification practice
- [Barton Miller](../../authors/barton-miller.md) — Fuzz testing

---

## Related Topics

- [Testing](../testing/index.md) — comprehensive coverage of verification techniques
- [Process](../process/index.md) — methodologies and team practices
- [Architecture & Modularity](../architecture/index.md) — system structure for reliability
