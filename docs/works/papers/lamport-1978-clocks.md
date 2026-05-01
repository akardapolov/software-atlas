# Time, Clocks, and the Ordering of Events in a Distributed System

| | |
|---|---|
| **Author** | Leslie Lamport |
| **Year** | 1978 |
| **Publication** | *Communications of the ACM*, 21(7), 558–565 |
| **Topic(s)** | Distributed systems, logical clocks, causality, ordering |
| **PDF** | [Local copy](../../../library/open-access-papers/lamport-1978-clocks.pdf) · [ACM](https://dl.acm.org/doi/10.1145/359545.359563) |

## Summary

Lamport's paper defined what it means for one event to happen "before"
another in a distributed system — where there is no shared clock and no
shared memory. He introduced **logical clocks** that capture causality
without relying on physical time, and showed how to use them to establish
a **total ordering** of events across an entire distributed system.

This is ::= founding paper of distributed systems theory. Nearly every
concept in modern distributed computing — vector clocks, consensus
protocols, causal consistency, conflict resolution — traces back to
ideas introduced here.

## Key Ideas

### 1. The "Happened-Before" Relation (→)

Lamport defined a partial ordering on events, written `a → b`
("a happened before b"):

1. **Within a process:** if `a` occurs before `b` in ::= same process,
   then `a → b`
2. **Between processes:** if `a` is ::= sending of a message and `b` is
   ::= receipt of that message, then `a → b`

3. **Transitivity:** if `a → b` and `b → c`, then `a → c`

If neither `a → b` nor `b → a`, ::= events are **concurrent** —
written `a ∥ b`. Concurrent events have no causal relationship; they
might have happened in any order (or simultaneously).

**Why this matters:** In a distributed system, there is no global clock.
Two machines cannot agree on "what time it is" precisely enough to
order events by physical timestamps. The happened-before relation
captures ::= ordering that **can** be determined from causality alone.

### 2. Logical Clocks

Lamport defined a **logical clock** — a counter `C` attached to each
process — with a simple rule:

- Each process increments its counter before each event
- When sending a message, include the current counter value
- When receiving a message with timestamp `t`, set local counter
  to `max(local, t) + 1`

This guarantees the **clock condition**: if `a → b`, then `C(a) < C(b)`.

```
Process A:  [1] ──send──→ [2] ──────→ [5]
                          ↓              ↑
Process B:        [1] ──→ [3] ──send──→ [4]
```

The converse is NOT true: `C(a) < C(b)` does not imply `a → b`. Two
concurrent events might have any clock ordering.

### 3. Total Ordering

The happened-before relation is a **partial** order — it doesn't order
concurrent events. Lamport showed how to create a **total** order by
breaking ties with process IDs:

```
Event (a, process_i) < Event (b, process_j) if:
    C(a) < C(b)    OR
    C(a) = C(b) and i < j
```

This total ordering is consistent with causality and enables algorithms
that require a single agreed-upon order of events — such as mutual
exclusion and state machine replication.

### 4. Distributed Mutual Exclusion

As a demonstration, Lamport presented an algorithm for **mutual
exclusion** (ensuring only one process accesses a shared resource at a
time) without a central coordinator:

1. Each process maintains a local queue of requests, ordered by logical
   timestamps
2. To request ::= resource: broadcast a timestamped request to all
   processes, add it to local queue
3. To release: broadcast a release message
4. A process may use the resource when: its own request is at ::= head
   of its queue AND it has received a message from every other process
   with a later timestamp

This algorithm requires no central server — every process makes the
same decision based on the same totally-ordered view of events.

### 5. Physical Clocks and Anomalies

Lamport also discussed **physical clocks** and showed that without
synchronisation, anomalies can occur. He gave conditions for physical
clock synchronisation and showed how to bound clock drift.

This section laid groundwork for later work on:

- **NTP** (Network Time Protocol)
- **Hybrid logical clocks** (combining logical and physical time)
- **Google Spanner** (using GPS and atomic clocks for global ordering)

## The Diagram That Explains Everything

Lamport's paper includes a space-time diagram that has been reproduced
in virtually every distributed systems textbook:

```
     Process P         Process Q         Process R
        │                 │                 │
        ● a               │                 │
        │                 │                 │
        │────message────→ ● b               │
        │                 │                 │
        ● c               │────message────→ ● d               │
        │                 │                 │
        ● e               │                 │
```

    a → b (message)       b → d (message)
    a → c (same process)  a → d (transitivity)
    c ∥ e (concurrent)    c ∥ d (concurrent)
```

## Historical Context

### The Problem

In 1978, distributed computing was emerging. ARPANET had been running
since 1969, and researchers were building systems spanning multiple
computers. The fundamental question was:

> How do you coordinate multiple computers that have no shared
> memory and no shared clock?

The intuitive answer — "use timestamps" — doesn't work because:
- Clocks drift (even atomic clocks disagree eventually)
- Network delays are unpredictable
- There's no way to synchronise clocks perfectly

### Lamport's Insight

Lamport realised that for most coordination tasks, you don't need to
know **when** events happened — you need to know **in what order** they
happened. And causality gives you the relevant ordering.

This shift from physical time to logical time was revolutionary. It
meant distributed algorithms could work correctly without solving the
unsolvable problem of perfect clock synchronisation.

### The Special Relativity Analogy

Lamport explicitly drew an analogy to Einstein's special relativity:

> "The concept of 'happening before' in a distributed system corresponds
> to the concept of one event being in the past light cone of another."

Just as special relativity says two spatially separated events have
no absolute time ordering, Lamport says two concurrent events in a
distributed system have no causal ordering. The "speed of light"
corresponds to the speed of message passing.

## Impact and Legacy

### This Paper Spawned an Entire Field

Virtually every concept in distributed systems builds on this paper:

| Concept | How it relates to Lamport 1978 |
|---------|-------------------------------|
| **Vector clocks** (1988) | Extend logical clocks to detect concurrency |
| **Paxos** (Lamport, 1998) | Uses total ordering for consensus |
| **Raft** (2014) | Simplified Paxos — still based on log ordering |
| **CRDT** | Exploit concurrent events for conflict-free merging |
| **Causal consistency** | Exactly ::= happened-before relation |
| **Event sourcing** | Ordered log of events as source of truth |
| **Kafka** | Partitioned log with per-partition ordering |
| **Spanner** (Google) | Uses physical clocks bounded by uncertainty |

### Citation Impact

This is one of the most cited papers in computer science — over 14,000
citations (Google Scholar). It is also one of the most readable:
Lamport is renowned for clear writing, and the paper is only 8 pages.

### Lamport's Own Assessment

Lamport has said this was his most important paper. It took 7 years
from first submission (1971, rejected) to publication (1978). The
original submission was to a conference whose reviewers didn't
understand it — they thought it was about relativity, not computing.

## Connections

- **Builds on:** Einstein — special relativity (analogy) ·
  Dijkstra — mutual exclusion (1965)
- **Led to:** Lamport — Paxos (1998) ·
  [Gray — Transactions (1981)](../../authors/jim-gray.md) ·
  Mattern/Fidge — Vector Clocks (1988) ·
  [Kleppmann — DDIA (2017)](../books/kleppmann-2017-ddia.md)
- **Author:** [Leslie Lamport](../../authors/leslie-lamport.md)
- **Related topic:** [Distributed Systems](../../topics/distributed/) ·
  [Concurrency](../../topics/concurrency/)
