# Boundaries

| | |
|---|---|
| **Author** | Gary Bernhardt |
| **Year** | 2012 |
| **Publication** | RubyConf / SCNA |
| **Topic(s)** | Functional programming, architecture, design |
| **Video** | [Watch on YouTube](https://www.youtube.com/watch?v=kM-RZAW7q0) |

## Summary

This influential talk introduces the **Functional Core / Imperative Shell** architectural pattern, which separates **pure functions** (no side effects) from **impure I/O operations** in a thin wrapper layer. This pattern, popularized in the functional programming community, directly influenced modern state management and application architecture.

## Key Ideas

### Functional Core

The **functional core** contains business logic:

```clojure
(defn calculate-total [orders])
  (reduce + (map :total orders)))
```

Characteristics:
- **Pure functions** — no side effects, always return same result
- **Referential transparency** - output depends only on inputs
- **Testability** - easy to test with pure inputs
- **Deterministic** - same inputs always produce same outputs

### Imperative Shell

The **imperative shell** handles I/O and system integration:

```clojure
(defn handle-user-input [input]
  (process-and-save order!))
```

Responsibilities:
- **User interaction** — read from console, display results
- **Persistence** — save orders to database
- **External API calls** — send to payment gateway
- **Error handling** — show user-friendly messages
- **Logging** - record system events

### Separation Principle

Bernhardt advocates strong boundaries:

- **Direct connection** - shell never calls core directly
- **One-way dependency** — core knows about shell, shell doesn't know core
- **Testability** - core tested without shell's I/O complications
- **Independent evolution** - core and shell can change independently

## Historical Significance

### FC/IS Pattern

The talk is associated with **"FC/IS"** (Functional Core, Imperative Shell) pattern:

- **Clear separation** — functional logic isolated from I/O side effects
- **Simplified testing** - core is pure, easy to test
- **Independent evolution** - layers can change separately

### Influence on Architecture

Bernhardt's work influenced:

- **Hexagonal architecture** — clean separation of concerns
- **Domain modeling** — functional core represents business domain
- **State management** - Redux, Elm patterns
- **Pure FP backends** - Clojure, Haskell in production

## Legacy

The talk remains influential:

- **Functional programming** - mainstream adoption of FP patterns
- **State management** - Redux and patterns became standard
- **Architecture thinking** - functional core concept demonstrated value
- **Ruby community** - "Boundaries" presented at RubyConf

## Connections

- **Speaker:** [Gary Bernhardt](../../authors/gary-bernhardt.md)
- **Related Works:** [Destroy All Software](../series/das.md)
- **Related Series:** [FC/IS talks](../talks/index.md)
- **Related:** [Functional Core / Imperative Shell pattern](../../topics/architecture/index.md) (if exists)
