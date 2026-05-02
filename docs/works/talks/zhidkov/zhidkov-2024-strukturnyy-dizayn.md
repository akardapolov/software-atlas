# Структурный дизайн. Древний секрет простого и быстрого кода

## Overview

An exploration of **Structural Design** (by Larry Constantine) and the principle of **balanced system form** as an objective design criterion for object-oriented code.

## Key Points

### The Search for Objective Design Criteria

Zhidkov studied mainstream literature from *Clean Code* to *DDD* but found approaches too subjective:
- No finite-time method to answer "how many abstraction levels in this function?"
- No stable method to find boundaries between bounded contexts or aggregates

### Functional Programming Attempt

In 2016, he explored functional programming where determinism was clearer:
- No side effects = good code
- Side effects = bad code

But free monads and their interpreters proved difficult to apply commercially.

### Rediscovery of Structural Design (2020)

In Larry Constantine's *Structural Design* (1966–1975), Zhidkov found:
- A simple, understandable principle of **balanced system form**
- An objective, checkable standard for design decisions
- The ability to quickly and unambiguously determine if code follows the principle

### Balanced Form in Practice

The article presents three case studies showing results of applying balanced form in commercial projects.

## Related Pages

- [Alexander Zhidkov](../../authors/alexander-zhidkov.md)
- [OOP & Design](../../topics/design/index.md)
- [Ergonomic Approach](https://ergowiki.azhidkov.pro/docs/structural-design/)
