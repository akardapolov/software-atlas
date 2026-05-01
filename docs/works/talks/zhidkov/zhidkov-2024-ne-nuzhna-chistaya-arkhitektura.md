# Вам не нужна Чистая архитектура. Скорее всего

## Overview

A critique of Clean/Hexagonal/Onion Architecture in the context of application development, proposing **Industrial Functional Architecture** as an alternative.

## Key Points

### The Dependency Inversion Principle (DIP)

The article first examines DIP before addressing Clean Architecture:
- DIP introduces unnecessary ceremony in application development
- The "program to an interface, not implementation" maxim from GoF is often misunderstood

### Clean Architecture Critique

For backend application development, Clean Architecture often:
- Brings unnecessary ceremony
- Slows development without providing meaningful benefits
- Is not the best default choice for most applications

### Industrial Functional Architecture

Zhidkov's alternative:
- Works as his default architecture for the last 3 years
- Integrates functional style with practical concerns
- Focuses on business logic as pure functions
- Avoids the complexity of Clean Architecture

## Related Pages

- [Alexander Zhidkov](../../authors/alexander-zhidkov.md)
- [Architecture & Modularity](../../topics/architecture/)
- [OOP & Design](../../topics/design/)
