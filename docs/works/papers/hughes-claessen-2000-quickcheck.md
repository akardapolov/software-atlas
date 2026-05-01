# QuickCheck: A Lightweight Tool for Random Testing of Haskell Programs

| | |
|---|---|
| **Authors** | Koen Claessen, John Hughes |
| **Year** | 2000 |
| **Conference** | ICFP (International Conference on Functional Programming) |
| **Topic(s)** | Property-based testing, random testing |
| **DOI** | 10.1145/351240.351266 |

## Summary

QuickCheck introduced **property-based testing**: instead of writing individual
test cases with concrete inputs, programmers write **properties** — logical
statements that should hold for all inputs. The framework then generates
random inputs automatically and searches for counterexamples.

This paper demonstrated that property-based testing could find bugs that
example-based testing would miss, and introduced the crucial technique
of **shrinking** counterexamples to minimal cases.

## Key Ideas

### 1. Properties as Tests

Instead of test cases, write properties:

```haskell
-- Example-based testing (traditional)
prop_reverse_empty = reverse [] == []

-- Property-based testing (QuickCheck)
prop_reverse :: [Int] -> Bool
prop_reverse xs = reverse (reverse xs) == xs

-- More complex property
prop_sort :: [Int] -> Bool
prop_sort xs = isSorted (sort xs)
  where isSorted [] = True
        isSorted [_] = True
        isSorted (x:y:rest) = x <= y && isSorted (y:rest)
```

QuickCheck generates random lists and checks that the property holds.
If a counterexample is found, it reports the input that caused failure.

### 2. Random Test Generation

QuickCheck uses **arbitrary** typeclass for generating random inputs:

```haskell
class Arbitrary a where
  arbitrary :: Gen a
```

The framework provides instances for common types and allows custom
generators:

```haskell
-- Custom generator for positive integers
genPositive :: Gen Int
genPositive = arbitrary `suchThat` (> 0)

instance Arbitrary Positive where
  arbitrary = Positive <$> genPositive
```

### 3. Shrinking

The paper's most important innovation: **shrinking** counterexamples.

When a test fails, QuickCheck doesn't just report the input that failed.
It runs a **shrinking process** that finds the *simplest* input that still
fails:

```haskell
-- Failing input: [1000, 50, 3, -7, 0]
-- After shrinking: [-7]

-- Failing input: Just (Just (Just Nothing))
-- After shrinking: Nothing
```

Shrinking makes debugging much easier by removing irrelevant details.

### 4. Foralls and Quantifiers

Properties can quantify over multiple values:

```haskell
-- Two independent inputs
prop_append :: [Int] -> [Int] -> Bool
prop_append xs ys = length (xs ++ ys) == length xs + length ys

-- Conditional property
prop_sqrt :: Int -> Property
prop_sqrt n = n >= 0 ==> sqrt (fromIntegral n) >= 0
```

The `==>` operator filters inputs that don't satisfy preconditions.

### 5. Test Distribution

QuickCheck provides statistics on test data:

```haskell
prop_sort :: [Int] -> Bool
prop_sort xs =
  classify (length xs == 0) "empty" .
  classify (length xs < 10) "small" .
  classify (length xs >= 10) "large" $
  isSorted (sort xs)
```

This helps ensure test coverage across different input categories.

## Example: Testing a Binary Tree

```haskell
data Tree a = Empty | Node a (Tree a) (Tree a)

-- Insert property: inserted value is in tree
prop_insert :: Int -> Tree Int -> Bool
prop_insert x t = member x (insert x t)

-- Delete property: deleted value is not in tree
prop_delete :: Int -> Tree Int -> Property
prop_delete x t = member x t ==> not (member x (delete x t))

-- Order preservation
prop_insert_ordered :: Int -> Tree Int -> Property
prop_insert_ordered x t = isOrdered t ==> isOrdered (insert x t)
```

These properties test the tree implementation against *all* inputs,
not just hand-picked examples.

## Impact and Legacy

### Language Ports

QuickCheck has been ported to virtually every major language:

| Language | Library | Maintainer |
|----------|---------|------------|
| Python | **Hypothesis** | David MacIver |
| Scala | **ScalaCheck** | Rickard Nilsson |
| Clojure | **test.check** | Clojure core team |
| F# | **FsCheck** | Kurt Schelfthout |
| TypeScript/JavaScript | **fast-check** | Nicolas DUBIEN |
| Rust | **proptest** | The Rust Project Developers |
| Go | **rapid** | rapid team |
| Java | **jqwik** | jqwik team |
| C# | **FsCheck** | .NET port |
| Elixir | **StreamData** | Jose Valim |

### Industry Adoption

QuickCheck and its descendants are used in production at:
- **Ericsson** — telecom protocol testing
- **Volvo** — autonomous driving systems
- **Financial services** — trading algorithms
- **Facebook** — testing distributed systems (using a QuickCheck variant)
- **Google** — property-based testing for critical infrastructure

### Testing Paradigm Shift

The paper established a new testing paradigm:
- **Example-based testing** — write specific inputs and expected outputs
- **Property-based testing** — write invariant properties, let framework find counterexamples

Property-based testing is complementary to TDD:
- **TDD** — small, focused tests for specific behaviours
- **PBT** — broad, invariant-checking tests for general correctness

## Why Property-Based Testing Matters

### 1. Finds Edge Cases

Example-based testing relies on programmer intuition for edge cases.
Property-based testing explores the input space systematically.

```haskell
-- Example-based tests might miss:
prop_reverse_large :: [Int] -> Bool
prop_reverse_large xs = length xs > 10000 ==> reverse (reverse xs) == xs
```

### 2. Documents Invariants

Properties serve as executable documentation of expected behaviour:
- "Sorting always produces ordered output"
- "Reverse is its own inverse"
- "Insertion maintains ordering"

### 3. Encourages Better APIs

To make code testable with properties, APIs must be:
- Pure (no hidden side effects)
- Deterministic (same input → same output)
- Well-defined (clear invariants)

### 4. Shrinking Simplifies Debugging

When a test fails, a shrunk counterexample removes confusion:

```haskell
-- Without shrinking: "Failed for input: [1,2,3,4,5,6,7,8,9,10,...]"
-- With shrinking: "Failed for input: [0]"
```

## Historical Context

### Pre-QuickCheck

Before QuickCheck, testing approaches were:
- **Manual test cases** — write specific examples
- **Fuzz testing** — generate random inputs ( pioneered in security testing, 1980s)
- **Formal verification** — prove correctness mathematically (expensive, limited scope)

### Post-QuickCheck

QuickCheck demonstrated that random testing with shrinking could be:
- **Effective** — finds real bugs
- **Practical** — easy to use in daily development
- **Complementary** — works alongside example-based testing

## Criticism and Limitations

### Stateful Systems

Original QuickCheck focused on pure functions. Testing stateful systems
required extensions:
- **State machine testing** — model state as data type
- **Command generation** — sequences of operations
- **Invariant checking** — pre- and post-conditions

### Learning Curve

Writing good properties requires practice:
- Not all invariants are obvious
- Properties can be too weak (pass when code is wrong)
- Properties can be too strong (fail when code is correct)

### Test Performance

Generating thousands of random inputs can be slow for complex operations.
Careful generator design and shrinking efficiency are important.

## Connections

- **Builds on:** Haskell type system · Random number generation · Shrinking algorithms
- **Led to:** Hypothesis (Python) · ScalaCheck · FsCheck · Property-based testing in industry
- **Authors:** [John Hughes](../../authors/john-hughes.md) · [Koen Claessen](../../authors/koen-claessen.md)
- **Related work:** [Why Functional Programming Matters](hughes-1989-why-fp.md) · TDD
- **Related topic:** [Testing](../../topics/process/#testing) · [Functional Programming](../../topics/functional/)
