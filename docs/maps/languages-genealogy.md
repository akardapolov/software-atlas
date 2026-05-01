# Languages Genealogy Map

Visual diagram showing how programming languages evolved and influenced each other.

## Language Family Tree

```mermaid
flowchart TD
    subgraph Theoretical["🔬 Theoretical Foundations"]
        Lambda["Lambda Calculus<br/>Church, 1936"]
        Turing["Turing Machine<br/>Turing, 1936\n[→](../authors/alan-turing.md)"]
    end

    subgraph FirstGen["📜 First Generation: 1950s"]
        Fortran["Fortran<br/>1957"]
        Lisp["Lisp<br/>McCarthy, 1958"]
        Algol["Algol<br/>1958"]
    end

    subgraph Structured["🏗 Structured Era: 1960s-70s"]
        Simula["Simula<br/>Dahl & Nygaard, 1962"]
        C["C<br/>Ritchie, 1972"]
        Smalltalk["Smalltalk<br/>Kay, 1972"]
        ML["ML<br/>Milner, 1978"]
        Scheme["Scheme<br/>Sussman, 1975"]
    end

    subgraph OOPEra["📦 OOP Era: 1980s"]
        Cpp["C++<br/>Stroustrup, 1983"]
        Erlang["Erlang<br/>Armstrong, 1986"]
        Perl["Perl<br/>Wall, 1987"]
    end

    subgraph ModernOOP["☕ Modern OOP: 1990s"]
        Haskell["Haskell<br/>Committee, 1990"]
        Python["Python<br/>van Rossum, 1991"]
        Java["Java<br/>Sun, 1995"]
        Ruby["Ruby<br/>Matsumoto, 1995"]
        JavaScript["JavaScript<br/>Eich, 1995"]
    end

    subgraph TwentyFirst["🚀 21st Century"]
        CSharp["C#<br/>Microsoft, 2000"]
        Scala["Scala<br/>Odersky, 2003"]
        Clojure["Clojure<br/>Hickey, 2007"]
        Go["Go<br/>Google, 2009"]
        Rust["Rust<br/>Mozilla, 2010"]
        TypeScript["TypeScript<br/>Microsoft, 2012"]
        Swift["Swift<br/>Apple, 2014"]
        Kotlin["Kotlin<br/>JetBrains, 2016"]
    end

    %% Theoretical → First Gen
    Lambda --> Lisp
    Turing --> Fortran

    %% First Gen → Structured
    Algol --> Simula
    Algol --> C
    Lisp --> Scheme
    Lisp --> ML
    Simula --> Smalltalk

    %% Structured → OOP Era
    C --> Cpp
    Smalltalk --> Cpp
    Lisp --> Erlang
    C --> Perl
    ML --> Haskell

    %% OOP Era → Modern OOP
    C --> Python
    Smalltalk --> Python
    Smalltalk --> Ruby
    Cpp --> Java
    Smalltalk --> Java
    Scheme --> JavaScript
    Self --> JavaScript
    ML --> Haskell

    %% Modern → 21st Century
    Java --> CSharp
    Java --> Scala
    Haskell --> Scala
    Lisp --> Clojure
    Haskell --> Clojure
    C --> Go
    Erlang --> Go
    Cpp --> Rust
    Haskell --> Rust
    ML --> Rust
    JavaScript --> TypeScript
    Cpp --> Swift
    Haskell --> Swift
    Java --> Kotlin
    Scala --> Kotlin

    style Theoretical fill:#e3f2fd
    style FirstGen fill:#fff3e0
    style Structured fill:#f3e5f5
    style OOPEra fill:#e8f5e9
    style ModernOOP fill:#fce4ec
    style TwentyFirst fill:#e0f7fa
```

## Language Families

### λ Functional Family

```mermaid
flowchart LR
    Lisp["Lisp<br/>1958"] --> Scheme["Scheme<br/>1975"]
    Lisp --> ML["ML<br/>1978"]
    ML --> Haskell["Haskell<br/>1990"]
    ML --> OCaml["OCaml<br/>1996"]
    Lisp --> Clojure["Clojure<br/>2007"]
    Haskell --> Clojure
    Haskell --> Elm["Elm<br/>2012"]
    ML --> Rust["Rust (influenced)<br/>2010"]

    style Lisp fill:#c8e6c9
    style Haskell fill:#81c784
    style Clojure fill:#4caf50,color:#fff
```

**Characteristics:** Immutability, first-class functions, pattern matching, type inference (ML-family).

### 📦 OOP Family

```mermaid
flowchart LR
    Simula["Simula<br/>1962"] --> Smalltalk["Smalltalk<br/>1972"]
    Simula --> Cpp["C++<br/>1983"]
    Smalltalk --> Java["Java<br/>1995"]
    Smalltalk --> Ruby["Ruby<br/>1995"]
    Smalltalk --> Python["Python (influenced)<br/>1991"]
    Cpp --> Java
    Java --> CSharp["C#<br/>2000"]
    Java --> Kotlin["Kotlin<br/>2016"]

    style Simula fill:#ffcc80
    style Smalltalk fill:#ffa726
    style Java fill:#ff9800,color:#fff
```

**Characteristics:** Classes, inheritance, encapsulation, polymorphism.

### ⚡ Systems Programming Family

```mermaid
flowchart LR
    Algol["Algol<br/>1958"] --> C["C<br/>1972"]
    C --> Cpp["C++<br/>1983"]
    Cpp --> Rust["Rust<br/>2010"]
    C --> Go["Go<br/>2009"]
    C --> Swift["Swift<br/>2014"]

    style C fill:#90caf9
    style Rust fill:#42a5f5,color:#fff
    style Go fill:#1976d2,color:#fff
```

**Characteristics:** Low-level control, memory management, performance focus.

### 🌐 Web & Scripting Family

```mermaid
flowchart LR
    Perl["Perl<br/>1987"] --> PHP["PHP<br/>1995"]
    Perl --> Ruby["Ruby<br/>1995"]
    Scheme --> JavaScript["JavaScript<br/>1995"]
    Self --> JavaScript
    JavaScript --> TypeScript["TypeScript<br/>2012"]
    JavaScript --> CoffeeScript["CoffeeScript<br/>2009"]

    style JavaScript fill:#fff59d
    style TypeScript fill:#ffeb3b
```

**Characteristics:** Dynamic typing, rapid development, web-native.

## Quick Reference

| Language | Year | Primary Paradigm | Influenced By | Influenced |
|----------|------|------------------|---------------|------------|
| Lisp | 1958 | Functional | Lambda calculus | Scheme, ML, Clojure, Ruby |
| Simula | 1962 | OOP | Algol | Smalltalk, C++, all OOP |
| C | 1972 | Imperative | Algol, BCPL | C++, Go, Rust, most systems |
| Smalltalk | 1972 | OOP | Simula | Ruby, Python, Java, ObjC |
| ML | 1978 | Functional | Lisp | Haskell, OCaml, Rust |
| Erlang | 1986 | Functional, Actor | Lisp, Prolog | Go (concurrency), Elixir |
| Haskell | 1990 | Pure Functional | ML, Miranda | Rust, Swift, PureScript |
| Python | 1991 | Multi-paradigm | C, Smalltalk | Julia, Nim |
| Java | 1995 | OOP | C++, Smalltalk | C#, Kotlin, Scala |
| JavaScript | 1995 | Multi-paradigm | Scheme, Self | TypeScript, countless |
| Ruby | 1995 | OOP | Smalltalk, Perl, Lisp | Crystal, Elixir |
| Clojure | 2007 | Functional | Lisp, Haskell | — |
| Go | 2009 | Imperative | C, CSP, Erlang | Zig |
| Rust | 2010 | Multi-paradigm | C++, Haskell, ML | — |
| TypeScript | 2012 | Multi-paradigm | JavaScript, C# | — |

## Language Selection Guide

```mermaid
flowchart TD
    Start["What do you need?"] --> Systems["Systems/Performance"]
    Start --> Web["Web Development"]
    Start --> Data["Data/ML"]
    Start --> Correctness["Type Safety/Correctness"]

    Systems --> SystemsChoice{"Memory control?"}
    SystemsChoice -->|"Manual, max perf"| Rust["Rust"]
    SystemsChoice -->|"GC, simplicity"| Go["Go"]
    SystemsChoice -->|"Legacy/embedded"| C["C/C++"]

    Web --> WebChoice{"Backend or Frontend?"}
    WebChoice -->|"Frontend"| TypeScript["TypeScript"]
    WebChoice -->|"Backend"| BackendChoice{"Team preference?"}
    BackendChoice -->|"JVM"| KotlinJava["Kotlin/Java"]
    BackendChoice -->|"Dynamic"| PythonRuby["Python/Ruby"]
    BackendChoice -->|"Functional"| Clojure["Clojure"]

    Data --> Python["Python"]

    Correctness -->|"Pure FP"| Haskell["Haskell"]
    Correctness -->|"Practical FP"| Scala["Scala/Clojure"]
    Correctness -->|"Modern safe"| Rust

    style Start fill:#e1f5fe
    style Rust fill:#ff8a65
    style Go fill:#64b5f6
    style TypeScript fill:#ffeb3b
    style Python fill:#4caf50,color:#fff
    style Haskell fill:#ab47bc,color:#fff
```

## See Also

- [Master Timeline](./master-timeline.md)
- [Paradigms Map](./paradigms-map.md)
- [Individual Language Pages](../languages/)
