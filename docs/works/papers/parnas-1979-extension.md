# Designing Software for Ease of Extension and Contraction

| | |
|---|---|
| **Author** | David Parnas |
| **Year** | 1979 |
| **Publication** | IEEE Transactions on Software Engineering |
| **Topic(s)** | Software design, modularity, information hiding |

## Summary

This paper extends Parnas's seminal 1972 work *"On the Criteria to Be Used in Decomposing Systems into Modules"* by examining how to design software that can be **extended** (add new features) and **contracted** (remove features) without disrupting the overall structure.

## Key Ideas

### Extension and Contraction

Parnas introduces two critical software properties:

- **Ease of extension** — can add features without widespread changes
- **Ease of contraction** — can remove features cleanly

These properties are enabled by:

- **Module boundaries** — each module has clear responsibilities
- **Information hiding** — modules hide design decisions
- **Interface stability** — stable contracts between modules

### Design for Change

The paper argues that **change is inevitable**, so design should:

- **Isolate volatile parts** — code likely to change is encapsulated
- **Define stable interfaces** — contracts that can survive changes
- **Minimize coupling** — fewer dependencies mean less ripple effect

### Real-World Example

Parnas uses the example of a **program with multiple features**:

- Some features (calculations) may be optional
- Core features (data structures) remain stable
- Plug-in architecture enables adding/removing optional features

This foreshadows:
- **Plugin architectures** — Eclipse, VS Code, browser extensions
- **Feature toggles** — dynamically enabling/disabling features
- **Microservices** — independent services that can be added/removed

## Historical Significance

### Plugin Architecture Foundation

Parnas's extension/contraction thinking directly influenced:

- **Plugin systems** — Eclipse RCP, VS Code extensions
- **Modular applications** — OSGi, .NET assemblies
- **Configuration-based features** — feature flags, dark launching

### Modularity Principles

The paper reinforced Parnas's core principles:

- **Information hiding** — modules hide implementation details
- **Interface-based design** — contracts over concrete classes
- **Single responsibility** — modules do one thing well

## Legacy

Parnas's extension principles are foundational:

- **Plugin architectures** — all modern extensible applications
- **Software modularity** — microservices, modular design
- **API design** — stable interfaces for extension

## Connections

- **Builds on:** [Information Hiding (1972)](../papers/parnas-1972-modules.md)
- **Influenced:** Plugin architecture, modularity thinking
- **Related topic:** [Architecture](../../topics/architecture/index.md)
- **Author:** [David Parnas](../../authors/david-parnas.md)
