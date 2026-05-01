{-
  Control Flow in Haskell
  =======================
  Haskell is expression-oriented: no statements, only expressions.
  Branching via if/then/else, guards, and case. No loops — use
  recursion or higher-order functions instead.
-}

module Main where

-- ── Helper ──────────────────────────────────────────

section :: String -> IO ()
section title = putStrLn ("\n--- " ++ title ++ " ---")

-- ── if/then/else ────────────────────────────────────

classify :: Int -> String
classify n =
    if n < 0 then "negative"
    else if n == 0 then "zero"
    else "positive"

-- ── Guards ──────────────────────────────────────────

grade :: Int -> Char
grade score
    | score >= 90 = 'A'
    | score >= 80 = 'B'
    | score >= 70 = 'C'
    | score >= 60 = 'D'
    | otherwise   = 'F'

-- ── case expression ─────────────────────────────────

describe :: Either Int String -> String
describe value = case value of
    Left n  -> "integer: " ++ show n
    Right s -> "string: " ++ s

-- ── Recursion (countdown) ───────────────────────────

countdown :: Int -> IO ()
countdown 0 = putStrLn "blastoff!"
countdown n = do
    putStrLn (show n ++ "...")
    countdown (n - 1)

-- ── Tail recursion (sum) ────────────────────────────

sumUpTo :: Int -> Int
sumUpTo n = go n 0
  where
    go 0 acc = acc
    go k acc = go (k - 1) (acc + k)

-- ── Higher-order iteration ──────────────────────────

printSquares :: [Int] -> IO ()
printSquares nums = mapM_ (\x -> putStrLn ("square of " ++ show x ++ " = " ++ show (x * x))) nums

-- ── List comprehensions ─────────────────────────────

-- Generate even numbers from 1 to 20
evens :: [Int]
evens = [x | x <- [1..20], x `mod` 2 == 0]

-- Pythagorean triples
pythag :: [(Int, Int, Int)]
pythag = [(a, b, c) | c <- [1..20], b <- [1..c], a <- [1..b], a*a + b*b == c*c]

-- ── Main ────────────────────────────────────────────

main :: IO ()
main = do
    putStrLn (classify (-5))
    putStrLn (classify 0)
    putStrLn (classify 7)

    section "guards"
    putStrLn $ "grade 95 = " ++ [grade 95]
    putStrLn $ "grade 82 = " ++ [grade 82]
    putStrLn $ "grade 45 = " ++ [grade 45]

    section "case expression"
    putStrLn $ describe (Left 42)
    putStrLn $ describe (Right "hello")

    section "recursion (countdown)"
    countdown 5

    section "tail recursion"
    putStrLn $ "sum 1..10 = " ++ show (sumUpTo 10)

    section "higher-order (map)"
    let squares = map (^2) [1..5]
    putStrLn $ "squares [1..5] = " ++ show squares

    section "filter"
    putStrLn $ "evens [1..10] = " ++ show (filter even [1..10])

    section "list comprehensions"
    putStrLn $ "evens 1..20 = " ++ show evens
    putStrLn $ "pythagorean triples = " ++ show pythag

    section "fold"
    putStrLn $ "sum [1..10] = " ++ show (foldl (+) 0 [1..10])
    putStrLn $ "product [1..5] = " ++ show (foldl (*) 1 [1..5])

    section "takeWhile / dropWhile"
    putStrLn $ "takeWhile (< 5) [1..] = " ++ show (takeWhile (< 5) [1..])
    putStrLn $ "dropWhile (< 5) [1..10] = " ++ show (dropWhile (< 5) [1..10])

    section "Summary"
    putStrLn "Haskell control flow:"
    putStrLn "  - if/then/else (expression, not statement)"
    putStrLn "  - Guards for multi-way branching"
    putStrLn "  - case for pattern matching"
    putStrLn "  - Recursion for looping"
    putStrLn "  - Tail recursion for efficiency"
    putStrLn "  - map, filter, foldl, foldr for iteration"
    putStrLn "  - List comprehensions for generation"
