# Concurrency in Lisp (Common Lisp)

## Overview

Common Lisp concurrency is implementation-dependent. SBCL provides
**threads** and **mutexes** for shared memory concurrency. There's no
standard concurrency model across implementations, unlike modern languages.

## Code

See `main.lisp` in this directory.

## How to Run

```bash
sbcl --script main.lisp
```

## Key Concepts

- **sb-thread:make-thread** — spawn threads (SBCL specific)
- **sb-thread:join-thread** — wait for thread completion
- **sb-thread:make-mutex** — create mutex lock
- **sb-thread:with-mutex** — execute with mutex held
- **sb-thread:make-condition-variable** — condition variables
- **sb-thread:condition-wait / signal** — wait/signal pattern
- **Implementation-specific** — each Lisp has its own concurrency API
- **No standard** — portable concurrency requires libraries

## Historical Context

Common Lisp (1984) predates widespread concurrency concerns.
Concurrency was added by individual implementations:
SBCL (1999+), CMUCL, Clojure, ECL. Lisp influenced actor
model research but never standardized concurrency. Modern Lisps
(Clojure, Racket) have more coherent concurrency stories.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
