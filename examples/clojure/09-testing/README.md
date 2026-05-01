# Testing in Clojure

## Overview

Clojure uses **clojure.test** built-in library with \`deftest\`,
\`is\`, and \`are\` assertions. **test.check** provides property-based
testing. Testing conventions follow \`*_test.clj\` naming.

## Code

See \`main.clj\` in this directory.

## How to Run

\`\`\`bash
lein test
# Or with deps.edn
clojure -X:test
# Or REPL
(require '[clojure.test :as t])
(t/run-tests)
\`\`\`

## Key Concepts

- **deftest** — define test functions
- **is/are** — assertion macros
- **testing** — group related assertions
- **fixtures** — use-fixtures, join-fixtures
- **check assertions** — test.check for property-based testing
- **mocking** — with-redefs for function stubbing
- **test.check** — property-based testing from Haskell's QuickCheck
- **Spec testing** — test \`defspec\` conformances

## Historical Context

Clojure (2007) included \`clojure.test\` from the start. The testing
approach is Lisp-like with \`is\`/\`are\` macros. \`test.check\`
(2010) added property-based testing, ported from Haskell's QuickCheck.
Spec (2014) added data specification and conformance testing.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
