# Error Handling in Clojure

## Overview

Clojure error handling uses **exceptions**, **conditionals**, and
**keyword-based results**. Exceptions inherit from \`Exception\`.
Clojure encourages defensive programming with \`nil\`-punning.

## Code

See \`main.clj\` in this directory.

## How to Run

\`\`\`bash
clj main.clj
# Or with leiningen
lein run
\`\`\`

## Key Concepts

- **throw** — \`throw (ex-info "message" data)\`
- **try-catch-finally** — \`(try ... (catch Exception e ...))\`
- **catch specific types** — \`catch ArithmeticException ...\`
- **finally** — always executes
- **ex-info** — structured exception with data map
- **nil-punning** — \`nil\` as failure (use \`some\` instead)
- **keyword results** — \`::success\` vs \`::error{\` for APIs
- **assert** — \`assert expr message\` for precondition checking
- **when** — \`when pred expr ...\` for conditional error handling
- **with-open** — resource cleanup like \`with-open\`

## Historical Context

Clojure (2007, Rich Hickey) inherits Java's exception model.
The \`ex-info\` pattern adds structure to errors. \`nil\`-punning
encourages explicit error handling. Clojure's immutability makes
error recovery more predictable than in mutable languages.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
