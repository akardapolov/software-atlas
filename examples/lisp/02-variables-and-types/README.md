# Variables and Types in Lisp

## Overview

Lisp uses **dynamic typing** — variables (symbols) can reference any
value. Lisp is **homoiconic**: code and data have the same
representation (lists), enabling powerful meta-programming.

## How to Run

```bash
# Using SBCL
sbcl --script main.lisp

# Using Emacs Lisp (SLIME)
M-x slime-eval-file
```

## Key Concepts

- **Dynamic typing** — symbols can reference any value
- **Homoiconicity** — code and data are both lists
- **Symbols** — variable names that evaluate to their bound values
- **Quoting** — `'` prevents evaluation, enabling code-as-data
- **Alists** — association lists for key-value pairs
- **Bignum** — arbitrary precision integers (no overflow)
- **Ratios** — exact rational numbers (1/2, not 0.5)

## Historical Context

Lisp (LISt Processing) was created by John McCarthy in 1958. Key innovations:
- First language with garbage collection
- Recursive functions as primary control flow
- Code and data share same structure (lists)
- Influenced functional programming languages

Dynamic typing enables flexibility (REPL-driven development) while homoiconicity
enables powerful macros and code generation.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
