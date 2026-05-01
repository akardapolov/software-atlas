# FP Features in Lisp (Common Lisp)

## Overview

Common Lisp has functional features but is multi-paradigm.
Functions are first-class, closures capture lexical scope. Data
can be mutable (not purely FP). No standard FP abstractions
built-in.

## Code

See \`main.lisp\` in this directory.

## How to Run

\`\`\`bash
sbcl --script main.lisp
# Or with other implementations:
clisp -x (run-tests)
\`\`\`

## Key Concepts

- **First-class functions** — assignable, passable, returnable
- **Closures** — lambda captures lexical scope
- **Higher-order functions** — mapcar, remove-if, reduce
- **Cons cells** — linked lists (O(1) cons)
- **Property lists** — assoc for key-value pairs
- **Destructuring** — bind in lambda/let
- **Function composition** — compose functions
- **Recursion** — primary iteration mechanism
- **Tail recursion** — implementation-dependent optimization
- **Optional FP libraries** — FiveAM, Lisp-Unit

## Historical Context

Common Lisp (1984) predates modern FP movement.
It influenced Scheme, Clojure, and Emacs Lisp. The lambda
concept (McCarthy 1958) was first implemented in Lisp.
Modern Lisps (Clojure, Racket) are more FP-focused.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
