{-
  Functions in Haskell
  ====================
  In Haskell, EVERYTHING is a function. Functions are the primary
  (and essentially only) abstraction mechanism. All functions are
  pure, curried by default, and first-class.
-}

module Main where

import Data.Char (toUpper, isAlpha)
import Data.List (sort, nub, intercalate)

-- ── Helper ──────────────────────────────────────────

section :: String -> IO ()
section title = putStrLn ("\n--- " ++ title ++ " ---")

-- ── Basic functions ─────────────────────────────────

-- Type signature (optional but encouraged)
greet :: String -> String
greet name = "Hello, " ++ name ++ "!"

-- Multiple parameters (automatically curried)
add :: Int -> Int -> Int
add x y = x + y

-- Guards (like if-else chains)
bmi :: Double -> String
bmi x
    | x < 18.5  = "underweight"
    | x < 25.0  = "normal"
    | x < 30.0  = "overweight"
    | otherwise  = "obese"

-- Pattern matching
factorial :: Integer -> Integer
factorial 0 = 1
factorial n = n * factorial (n - 1)

-- Where clause
circleArea :: Double -> Double
circleArea r = pi * r_squared
  where
    r_squared = r * r

-- Let expression
cylinderVolume :: Double -> Double -> Double
cylinderVolume r h =
    let baseArea = pi * r * r
        volume   = baseArea * h
    in  volume

-- ── Main ────────────────────────────────────────────

main :: IO ()
main = do
    putStrLn (greet "Atlas")

    -- ── Currying ─────────────────────────────────

    section "Currying"

    -- All functions are curried: add :: Int -> Int -> Int
    -- is actually: add :: Int -> (Int -> Int)
    -- add 3 returns a function that adds 3

    let add3 = add 3         -- partial application
    putStrLn $ "add 3 4 = " ++ show (add 3 4)
    putStrLn $ "add3 4  = " ++ show (add3 4)   -- same thing
    putStrLn $ "add3 10 = " ++ show (add3 10)

    -- Operators can be partially applied too (sections)
    let double = (* 2)       -- right section
    let half   = (/ 2)
    let inc    = (+ 1)
    putStrLn $ "double 5 = " ++ show (double 5)
    putStrLn $ "half 10  = " ++ show (half (10 :: Double))
    putStrLn $ "inc 42   = " ++ show (inc 42)

    -- ── Higher-order functions ───────────────────

    section "Higher-order functions"

    let numbers = [1..10] :: [Int]

    -- map: apply a function to each element
    let squares = map (^ (2 :: Int)) numbers
    putStrLn $ "map (^2) [1..10] = " ++ show squares

    -- filter: keep elements satisfying a predicate
    let evens = filter even numbers
    putStrLn $ "filter even = " ++ show evens

    -- foldr / foldl: reduce a list
    let total = foldr (+) 0 numbers
    putStrLn $ "foldr (+) 0 = " ++ show total

    let productVal = foldl (*) 1 numbers
    putStrLn $ "foldl (*) 1 = " ++ show productVal

    -- zipWith: combine two lists element-wise
    let sums = zipWith (+) [1,2,3] [10,20,30]
    putStrLn $ "zipWith (+) [1,2,3] [10,20,30] = " ++ show sums

    -- takeWhile, dropWhile
    let prefix = takeWhile (< 5) numbers
    putStrLn $ "takeWhile (< 5) = " ++ show prefix

    -- any, all
    putStrLn $ "any even = " ++ show (any even numbers)
    putStrLn $ "all (> 0) = " ++ show (all (> 0) numbers)

    -- ── Function composition ─────────────────────

    section "Function composition"

    -- (.) composes functions: (f . g) x = f (g x)
    let shout = map toUpper . filter isAlpha
    putStrLn $ "shout \"Hello, World!\" = " ++ shout "Hello, World!"

    -- Pipeline of transformations
    let process = show . length . filter even . map (^ (2 :: Int))
    putStrLn $ "process [1..20] = " ++ process ([1..20] :: [Int])
    -- read right-to-left: square → filter even → count → show

    -- ($) operator: low-precedence application (avoids parentheses)
    putStrLn $ "length evens = " ++ show (length $ filter even numbers)

    -- Pointfree style
    let sumOfSquares = sum . map (^ (2 :: Int))
    putStrLn $ "sumOfSquares [1..5] = " ++ show (sumOfSquares ([1..5] :: [Int]))

    -- ── Lambda expressions ───────────────────────

    section "Lambda"

    let addLambda = \x y -> x + y :: Int
    putStrLn $ "(\\x y -> x + y) 3 4 = " ++ show (addLambda 3 4)

    let bigOnes = filter (\x -> x > 5) numbers
    putStrLn $ "filter (\\x -> x > 5) = " ++ show bigOnes

    -- ── Custom higher-order functions ────────────

    section "Custom HOF"

    let applyTwice f x = f (f x)
    putStrLn $ "applyTwice (+1) 5 = " ++ show (applyTwice (+ 1) (5 :: Int))
    putStrLn $ "applyTwice (*2) 3 = " ++ show (applyTwice (* 2) (3 :: Int))

    -- flip: swap arguments
    let divBy = flip div :: Int -> Int -> Int
    putStrLn $ "divBy 2 10 = " ++ show (divBy 2 10)  -- 10 `div` 2 = 5

    -- ── List comprehensions ──────────────────────

    section "List comprehensions"

    let pythag = [(a,b,c) | c <- [1..20],
                             b <- [1..c],
                             a <- [1..b],
                             a*a + b*b == c*c]
    putStrLn $ "Pythagorean triples up to 20: " ++ show pythag

    -- ── Infinite lists (laziness) ────────────────

    section "Infinite lists (laziness)"

    let fibs = 0 : 1 : zipWith (+) fibs (tail fibs)
    putStrLn $ "take 15 fibs = " ++ show (take 15 fibs :: [Integer])

    let naturals = [1..] :: [Int]
    putStrLn $ "take 5 [1..] = " ++ show (take 5 naturals)

    -- iterate: generate sequence by repeated application
    let powers2 = take 10 $ iterate (* 2) (1 :: Int)
    putStrLn $ "iterate (*2) 1 = " ++ show powers2

    -- ── Where and let ────────────────────────────

    section "Where and let"

    putStrLn $ "circleArea 5 = " ++ show (circleArea 5)
    putStrLn $ "cylinderVolume 3 10 = " ++ show (cylinderVolume 3 10)
    putStrLn $ "bmi 22.5 = " ++ bmi 22.5

    -- ── Pattern matching in functions ────────────

    section "Pattern matching"

    putStrLn $ "factorial 10 = " ++ show (factorial 10)

    -- head/tail via pattern matching
    let myHead (x:_) = x
        myHead []    = error "empty list"
    putStrLn $ "myHead [5,6,7] = " ++ show (myHead [5,6,7] :: Int)

    -- Tuple pattern matching
    let fst' (a, _) = a
        snd' (_, b) = b
    putStrLn $ "fst (1,2) = " ++ show (fst' (1 :: Int, 2 :: Int))

    -- ── Summary ──────────────────────────────────

    section "Summary"
    putStrLn "Haskell functions:"
    putStrLn "  - ALL functions are curried"
    putStrLn "  - Partial application is free"
    putStrLn "  - (.) for composition: (f . g) x = f (g x)"
    putStrLn "  - ($) for application: f $ g x = f (g x)"
    putStrLn "  - map, filter, foldr/foldl as core HOFs"
    putStrLn "  - Lambda: \\x -> x + 1"
    putStrLn "  - Pattern matching in function definitions"
    putStrLn "  - Guards for conditional logic"
    putStrLn "  - Lazy evaluation enables infinite data"
    putStrLn "  - Pointfree style for concise composition"
