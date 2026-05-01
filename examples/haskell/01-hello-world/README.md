# Hello World in Haskell

## Overview

A simple Hello World program in Haskell, a purely functional language.

## Code

```haskell
main :: IO ()
main = putStrLn "Hello, World!"
```

## How to Run

```bash
# Using GHC (run Haskell)
runhaskell Main.hs

# Or compile and run
ghc Main.hs -o hello
./hello
```

## Key Concepts

- `main :: IO ()` - Type signature: main is an IO action returning unit
- `putStrLn` - Function to print a string with newline
- Pure functional programming with controlled side effects via IO monad
- Static typing with type inference

## Historical Context

Haskell 1.0 was released in 1990 by a committee. Key influences:
- ML ( Miranda )
- Lisp ( lambda calculus foundation )
- Category theory ( monads, functors )

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
