# Почему следует избегать использования JPA/Hibernate в продакшене

## Overview

An argument against using JPA/Hibernate in production, advocating for JDBC-based alternatives.

## Key Points

### JPA Is Widespread

JPA is the most common database technology on the Java platform, but it's also the least suitable for developing fast, maintainable systems.

### The Core Problem

JPA's nature includes:
- **Persistence context** — manages entity lifecycles
- **Dirty checking** — automatically detects changes

These features do not support **immutable data models** — an essential part of functional programming style.

### What To Use Instead

Zhidkov advocates for:
- **Spring Data JDBC** — simpler, supports immutability
- Plain JDBC when appropriate
- Approaches that keep data objects immutable

### Cost Implications

The argument ultimately comes down to development cost:
- Immutability reduces bugs and maintenance burden
- JPA's mutable model fights against this principle
- The friction translates to higher cost and slower development

## Related Pages

- [Alexander Zhidkov](../../authors/alexander-zhidkov.md)
- [Functional Programming](../../topics/functional/)
- [Type Systems](../../topics/types/)
