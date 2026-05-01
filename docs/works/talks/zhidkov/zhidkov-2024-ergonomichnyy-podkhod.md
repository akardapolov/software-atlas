# Эргономичный подход к разработке информационных систем v1.0M1

... или как писать программы, которые приносят больше положительных эмоций.

## Overview

The inaugural article introducing the **Ergonomic Approach** — a systematic methodology for building maintainable, well-tested codebases with high cohesion and low coupling.

## Key Points

The Ergonomic Approach is a set of principles, models, techniques, and patterns for:
- Designing and developing codebases fully covered by automated tests
- Achieving high cohesion and low coupling in implementation

### Three Core Areas

1. **Testing**
   - Tests work through public API
   - 100% of system behaviour is covered
   - Mocks only for unmanaged dependencies or hard-to-reproduce behaviour
   - Fast tests: < 10s per test, < 300s for full suite

2. **Domain Modeling**
   - Split domain into small, loosely-coupled aggregates
   - Aggregates and relationships form a DAG
   - Aggregates are immutable objects in mutable repositories

3. **Behaviour Design**
   - Separate business logic and I/O
   - Business logic as pure functions without side effects

## Context

Zhidkov began developing the Ergonomic Approach in spring 2020 after returning to standard Spring projects after a four-year break. On contrast with his own projects, he experienced frustration with excessive effort and fear of introducing regressions — symptoms he traced to common practices: layer packaging, connected JPA entity graphs, global component scanning, mock-based testing.

## Related Pages

- [Alexander Zhidkov](../../authors/alexander-zhidkov.md)
- [Ergonomic Approach](https://ergowiki.azhidkov.pro/)
