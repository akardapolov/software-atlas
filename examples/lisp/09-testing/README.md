# Testing in Lisp (Common Lisp)

## Overview

Common Lisp has no standard testing framework. **FiveAM** and
**Lisp-Unit** are popular libraries. \`assert\` macro provides
basic runtime checking. Testing conventions vary by implementation.

## Code

See \`main.lisp\` in this directory.

## How to Run

\`\`\`bash
sbcl --load main.lisp --eval '(fiveam:run! (list :my-tests))'
# Or with clisp
clisp -x (run-tests)
\`\`\`

## Key Concepts

- **FiveAM** — modern testing framework
- **Lisp-Unit** — older xUnit-style testing
- **assert macro** — basic runtime assertions
- **Fixtures** — setup/teardown functions
- **Check functions** — \`(is (= expected actual))\`
- **Tests as lists** — \`(defvar *my-tests* '(test1 test2))\`
- **Implementation-specific** — each Lisp has different tools

## Historical Context

Common Lisp (1984) predates modern testing conventions. Testing frameworks
were developed by communities. FiveAM (2006) and Lisp-Unit (1990s)
provide modern test organization. Lisp influenced Python's doctest with
its \`eval\` capabilities.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
