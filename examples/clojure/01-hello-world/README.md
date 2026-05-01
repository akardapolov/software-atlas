# Hello World in Clojure

## Overview

A simple Hello World program in Clojure, a modern Lisp on the JVM.

## Code

```clojure
(println "Hello, World!")
```

## How to Run

```bash
# Using Babashka (simplest for scripts)
bb hello.clj

# Using Clojure CLI (load in REPL)
clj
user=> (load-file "hello.clj")
Hello, World!
nil

# Using Clojure CLI (inline)
clj -e '(println "Hello, World!")'
```

## Key Concepts

- Lisp syntax: code is data (homoiconicity)
- Runs on JVM (interoperability with Java)
- Immutable data structures by default
- Functional programming with controlled mutation via atoms, refs, agents

## Historical Context

Clojure was created by Rich Hickey and released in 2007. Key influences:
- Lisp (syntax and macros)
- Haskell (immutability, lazy sequences)
- Java (JVM platform, libraries)
- Erlang (concurrency philosophy)

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
