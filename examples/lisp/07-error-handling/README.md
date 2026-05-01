# Error Handling in Lisp (Common Lisp)

## Overview

Common Lisp has no standard exception mechanism — implementations vary.
**FiveAM** and **Lisp-Unit** provide error handling.
Traditional Lisp uses \`condition-case\` and \`handler-bind\`.

## Code

See \`main.lisp\` in this directory.

## How to Run

\`\`\`bash
sbcl --script main.lisp
# Or with FiveAM
sbcl --load main.lisp --eval '(fiveam:run! (list :my-tests))'
\`\`\`

## Key Concepts

- **condition-case** — \`condition-case () (error (error-form ...))\`
- **handler-bind** — bind handlers to condition types
- **error** — signal for exceptional conditions
- **warn** — warnings (implementation-specific)
- **unwind-protect** — \`unwind-protect\` ensures cleanup
- **return from handler** — \`return-from error-handler\`
- **ignore-errors** — \`ignore-errors (ignore ...)\`
- **check return values** — verify function succeeded
- **restart-case** — pattern matching for state machines
- **Implementation-specific** — each Lisp has different approaches

## Historical Context

Common Lisp (1984) predates modern exception systems.
The \`condition-case\` system was Lisp's error handling mechanism.
Modern Lisps (Clojure, Racket) use exception semantics
influenced by Java. FiveAM (2006) provides modern
exception handling with test support.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
