# Paradigms Map

How programming paradigms relate and evolved over time.

## Paradigm Overview

```mermaid
flowchart TD
    subgraph Imperative["⚡ Imperative"]
        Procedural["Procedural<br/>C, Go, Pascal"]
    end

    subgraph OOP["📦 Object-Oriented"]
        ClassBased["Class-based<br/>Java, C++, C#"]
        ProtoBased["Prototype-based<br/>JavaScript, Self"]
    end

    subgraph Functional["λ Functional"]
        PureFP["Pure FP<br/>Haskell, Elm"]
        ImpureFP["Impure FP<br/>Lisp, Clojure, ML"]
    end

    subgraph Declarative["📋 Declarative"]
        Logic["Logic<br/>Prolog"]
        Query["Query<br/>SQL"]
        Reactive["Reactive<br/>Rx, Elm"]
    end

    subgraph Concurrent["🔄 Concurrent"]
        Actor["Actor Model<br/>Erlang, Akka"]
        CSP["CSP<br/>Go, Clojure core.async"]
    end

    %% Evolution arrows
    Procedural --> ClassBased
    Functional --> ClassBased
    Procedural --> Actor
    Functional --> PureFP
    ClassBased --> ProtoBased
    PureFP --> Reactive

    style Imperative fill:#ffcdd2
    style OOP fill:#fff3e0
    style Functional fill:#c8e6c9
    style Declarative fill:#e1f5fe
    style Concurrent fill:#f3e5f5
```

## Paradigm Deep Dive

### ⚡ Imperative Programming

**Core Idea:** Programs as sequences of commands that change state.

| Aspect | Description |
|--------|-------------|
| **Focus** | How to do something (step by step) |
| **State** | Mutable, explicit |
| **Control** | Loops, conditionals, goto |
| **Languages** | C, Go, Pascal, Fortran |

```c
// Imperative: explicit steps
int sum = 0;
for (int i = 0; i < n; i++) {
    sum += arr[i];
}
```

**Strengths:** Direct hardware mapping, performance, explicit control flow.

**Weaknesses:** State management complexity, harder to parallelize.

### 📦 Object-Oriented Programming

**Core Idea:** Programs as collections of objects that communicate via messages.

| Aspect | Description |
|--------|-------------|
| **Focus** | Data + behavior bundled together |
| **State** | Encapsulated in objects |
| **Key concepts** | Encapsulation, inheritance, polymorphism |
| **Languages** | Java, C++, C#, Python, Ruby |

```java
// OOP: objects with behavior
class Account {
    private double balance;

    public void deposit(double amount) {
        this.balance += amount;
    }
}
```

**Strengths:** Modeling real-world domains, code reuse, encapsulation.

**Weaknesses:** Inheritance hierarchies can be rigid, shared mutable state.

#### Subparadigms

- **Class-based** (Java, C++): Objects are instances of classes
- **Prototype-based** (JavaScript, Self): Objects clone from other objects

### λ Functional Programming

**Core Idea:** Programs as composition of pure functions transforming immutable data.

| Aspect | Description |
|--------|-------------|
| **Focus** | What to compute (transformations) |
| **State** | Immutable values |
| **Key concepts** | Pure functions, higher-order functions, recursion |
| **Languages** | Haskell, Clojure, Erlang, ML, Lisp |

```haskell
-- Functional: declarative transformation
sum = foldr (+) 0
```

**Strengths:** Testability, concurrency safety, reasoning about code.

**Weaknesses:** Learning curve, performance overhead (mitigated in practice).

#### Subparadigms

- **Pure FP** (Haskell): All side effects tracked in type system
- **Impure FP** (Clojure, Lisp): FP style with pragmatic escape hatches

### 📋 Declarative Programming

**Core Idea:** Describe what you want, not how to get it.

| Aspect | Description |
|--------|-------------|
| **Focus** | Specification of desired result |
| **Languages** | SQL, Prolog, HTML/CSS, Terraform |

```sql
-- Declarative: what, not how
SELECT name FROM users WHERE age > 18
```

**Strengths:** Expressiveness, optimization opportunities, domain focus.

**Weaknesses:** Less control, can be opaque.

### 🔄 Concurrent Programming Paradigms

#### Actor Model

**Core Idea:** Isolated actors communicate via async messages.

| Aspect | Description |
|--------|-------------|
| **Key insight** | No shared state between actors |
| **Languages** | Erlang, Elixir, Akka (Scala/Java) |

```erlang
% Erlang: actors receive messages
loop(State) ->
    receive
        {add, X} -> loop(State + X);
        {get, Pid} -> Pid ! State, loop(State)
    end.
```

#### CSP (Communicating Sequential Processes)

**Core Idea:** Processes communicate via channels, synchronously.

| Aspect | Description |
|--------|-------------|
| **Key insight** | Channel is the synchronization point |
| **Languages** | Go, Clojure (core.async) |

```go
// Go: CSP-style channels
ch := make(chan int)
go func() { ch <- 42 }()
value := <-ch
```

## Languages by Paradigm

```mermaid
flowchart LR
    subgraph Pure["Pure Paradigms"]
        P_Imp["Imperative<br/>Assembly, C"]
        P_FP["Pure FP<br/>Haskell, PureScript"]
        P_OOP["Pure OOP<br/>Smalltalk"]
    end

    subgraph Multi["Multi-Paradigm"]
        M_Modern["Modern Multi<br/>Rust, Kotlin, Swift"]
        M_Script["Scripting<br/>Python, Ruby, JS"]
        M_JVM["JVM<br/>Scala, Clojure"]
    end

    P_Imp --> M_Modern
    P_FP --> M_Modern
    P_OOP --> M_Script
    P_FP --> M_JVM

    style Pure fill:#e0e0e0
    style Multi fill:#a5d6a7
```

## Paradigm Selection Guide

| Problem Domain | Recommended Paradigm | Why |
|----------------|----------------------|-----|
| System utilities | Imperative (Go, Rust) | Direct, efficient |
| Business apps | OOP + FP mix (Kotlin, C#) | Domain modeling |
| Data pipelines | Functional (Clojure, Elixir) | Transformations |
| Highly concurrent | Actor/CSP (Erlang, Go) | Isolation |
| Proofs/safety | Pure FP (Haskell, Idris) | Mathematical properties |
| Quick scripting | Multi-paradigm (Python) | Flexibility |

## The Synthesis: Modern Multi-Paradigm

Modern languages blend paradigms:

```mermaid
flowchart TD
    FP["FP Ideas"] --> Modern["Modern Languages"]
    OOP["OOP Ideas"] --> Modern
    Imperative["Imperative"] --> Modern

    Modern --> Rust["Rust<br/>Systems + Ownership + FP"]
    Modern --> Kotlin["Kotlin<br/>OOP + FP + Coroutines"]
    Modern --> Swift["Swift<br/>OOP + FP + Safety"]
    Modern --> TypeScript["TypeScript<br/>OOP + FP + Structural"]

    style Modern fill:#81c784
```

**Key insight:** The best modern code uses:
- **Immutability by default** (from FP)
- **Encapsulation** (from OOP)
- **Explicit effects** (from Pure FP)
- **Pragmatic escape hatches** (from Imperative)

## See Also

- [Languages Genealogy](./languages-genealogy.md)
- [Ideas Evolution](./ideas-evolution.md)
- [Functional Programming Topic](../topics/paradigms/functional/index.md)
