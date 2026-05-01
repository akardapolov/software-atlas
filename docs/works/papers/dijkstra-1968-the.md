# The Structure of the "THE" Multiprogramming System

| | |
|---|---|
| **Author** | Edsger Dijkstra |
| **Year** | 1968 |
| **Publication** | ACM |
| **Topic(s)** | Operating systems, layered architecture, structured programming |

## Summary

This paper describes the **THE multiprogramming system**, designed by Dijkstra and his team at Technological University Eindhoven. THE was one of the first operating systems to implement **layered architecture**, which became a foundational concept in both operating systems and software architecture.

## Key Ideas

### Layered Architecture

THE organized system into concentric layers:

```
Layer 5: User processes
Layer 4: Message interpreter
Layer 3: I/O
Layer 2: Segment controller
Layer 1: Central processor (CPU)
```

Each layer could only communicate with adjacent layers:
- **Abstraction barriers** — layer hides implementation from layers above
- **Interfaces between layers** — clean communication channels
- **Modularity** — each layer could be designed independently

This layered approach directly influenced:
- **Modern OS design** — Linux, Windows, macOS architecture
- **Network protocols** — OSI model with seven layers
- **Application architecture** — hexagonal/clean/layered architectures

### Semaphores

Dijkstra introduced **semaphores** for synchronization:

- **Atomic operations** — wait (P) and signal (V) operations
- **Mutual exclusion** — ensure only one process uses critical section
- **Resource counting** — semaphores can guard multiple resources

Semaphores became the foundation of:
- **Concurrency control** — locks, mutexes across all languages
- **OS primitives** — Windows, Unix/Linux kernel synchronization

### Multiprogramming

THE demonstrated:

- **Multiple processes** — several programs run simultaneously
- **Process scheduling** — CPU time allocation between processes
- **Memory management** — partitioning memory between processes

This was early work on what we now call **multitasking**.

## Historical Significance

### Foundation of Layered Design

THE's layered architecture established patterns for:
- **Separation of concerns** — each layer handles specific responsibility
- **Interface design** — layers communicate through defined protocols
- **Replaceability** — layer can be reimplemented without affecting others

### Influence on Software Architecture

Dijkstra's layering directly influenced:
- **Parnas (1972)** — Information hiding modules
- **Clean Architecture** — layers with dependencies only inward
- **Hexagonal Architecture** — adapters separate domains

## Legacy

The THE system's architectural principles became standard:

- **OS design** — Linux, Windows, BSD all use layered kernels
- **Network stacks** — TCP/IP, OSI model
- **Application architecture** — Presentation, Business, Data, Persistence layers

## Connections

- **Influenced:** Parnas, OS design, software architecture
- **Related topic:** [Architecture](../../topics/architecture/)
- **Author:** [Edsger Dijkstra](../../authors/edsger-dijkstra.md)
