## Overview

Haskell error handling uses **Maybe**, **Either**, and **IO** monads.
Pure functions cannot fail — effects must be explicitly modeled
in the type system. Pattern matching handles different cases.

## Code

See \`Main.hs\` in this directory.

## How to Run

\`\`\`bash
ghc -threaded Main.hs && ./Main
# Or with runhaskell
runhaskell -threaded Main.hs
\`\`\`

## Key Concepts

- **Maybe a** — \`Just value\` or \`Nothing\` for optional values
- **Either a b** — \`Left err\` or \`Right value\` for error/result
- **IO monad** — \`IO String\` wraps side effects
- **Pattern matching** — \`case\` with \`Nothing\`/`Just\`/`Left\`/`Right\`
- **do-notation** — \`do { x <- action ... }\` for monadic sequencing
- **Exceptions via error** — \`error :: Text -> a\` (though rare)
- **String error types** — custom types for specific errors
- **Type safety** — compiler ensures exhaustive pattern matching

## Historical Context

Haskell (1990) pioneered monadic error handling. The Maybe/Either
pattern is unique among mainstream languages. Pattern matching
requires all cases to be handled, preventing uncaught errors
at compile time.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
