-- FP Features in Haskell
-- ========================
-- Pure functions, type classes, lazy evaluation, monads

import Data.List (nub, sort)
import Data.Char (toUpper)


-- ── Basic pure functions ──────────────────────────

double :: Int -> Int
double x = x * 2

square :: Int -> Int
square x = x * x


-- ── Higher-order functions ─────────────────────────

applyTwice :: (Int -> Int) -> Int -> Int
applyTwice f x = f (f x)

result1 :: Int
result1 = applyTwice (+ 1) 5  -- (5+1)+1 = 7

result2 :: Int
result2 = applyTwice (* 2) 3  -- (3*2)*2 = 12


-- ── Composition (.) ─────────────────────────────

shout :: String -> String
shout = map toUpper

greet :: String -> String
greet name = "Hello, " ++ name

greetLoud :: String -> String
greetLoud = shout . greet  -- (.) composes right-to-left: shout(greet(name))


-- ── Currying (partial application) ─────────────

add :: Int -> Int -> Int
add x y = x + y

addFive :: Int -> Int
addFive = add 5  -- Curried: waits for one argument


-- ── Map/Filter/Reduce ─────────────────────────

squaresOf :: [Int] -> [Int]
squaresOf xs = map square xs

evens :: [Int] -> [Int]
evens = filter even

sum :: [Int] -> Int
sum xs = foldl (+) 0 xs

product :: [Int] -> Int
product xs = foldl (*) 1 xs


-- ── List comprehension ───────────────────────────

squaresGT25 :: [Int] -> [Int]
squaresGT25 xs = [x*x | x <- xs, x*x > 25]


-- ── Pattern matching ─────────────────────────────

describe :: Int -> String
describe n
    | n < 0     = "negative"
    | n == 0    = "zero"
    | n == 1    = "one"
    | otherwise  = "many"


factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)


fibonacci :: Int -> Int
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = fibonacci (n - 1) + fibonacci (n - 2)


-- ── Lazy evaluation ─────────────────────────────

naturals :: [Int]
naturals = [1..]  -- Infinite list!

take10Naturals :: [Int]
take10Naturals = take 10 naturals

evensLazy :: [Int]
evensLazy = filter even naturals


-- ── Custom higher-order functions ─────────────────

allPredicate :: (a -> Bool) -> [a] -> Bool
allPredicate p xs = foldl (\acc x -> acc && p x) True xs

anyPredicate :: (a -> Bool) -> [a] -> Bool
anyPredicate p xs = foldl (\acc x -> acc || p x) False xs


-- ── Summary ─────────────────────────────────────

main :: IO ()
main = do

    putStrLn "--- Pure functions ---"
    putStrLn $ "double(5) = " ++ show (double 5)
    putStrLn $ "square(4) = " ++ show (square 4)

    putStrLn "\n--- Higher-order functions ---"
    putStrLn $ "applyTwice(+1, 5) = " ++ show result1
    putStrLn $ "applyTwice(*2, 3) = " ++ show result2

    putStrLn "\n--- Composition ---"
    putStrLn $ "greetLoud \"alice\" = " ++ show (greetLoud "alice")

    putStrLn "\n--- Currying ---"
    putStrLn $ "addFive 10 = " ++ show (addFive 10)

    putStrLn "\n--- Map/Filter/Reduce ---"
    let nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    putStrLn $ "Squares: " ++ show (squaresOf nums)
    putStrLn $ "Evens: " ++ show (evens nums)
    putStrLn $ "Sum: " ++ show (sum nums)
    putStrLn $ "Product: " ++ show (product nums)

    putStrLn "\n--- List comprehension ---"
    putStrLn $ "Squares > 25: " ++ show (squaresGT25 nums)

    putStrLn "\n--- Pattern matching ---"
    putStrLn $ "describe(-5): " ++ describe (-5)
    putStrLn $ "describe(0): " ++ describe 0
    putStrLn $ "factorial(5): " ++ show (factorial 5)
    putStrLn $ "fibonacci(10): " ++ show (fibonacci 10)

    putStrLn "\n--- Lazy evaluation ---"
    putStrLn $ "First 10 naturals: " ++ show (take10Naturals)

    putStrLn "\n--- All/Any predicates ---"
    putStrLn $ "All evens in [2,4,6]: " ++ show (allPredicate even [2, 4, 6])
    putStrLn $ "Any even in [1,3,5]: " ++ show (anyPredicate even [1, 3, 5])

    putStrLn "\n--- Summary ---"
    putStrLn "Haskell FP features:"
    putStrLn "  - Pure functions: no side effects"
    putStrLn "  - Type inference: Hindley-Milner algorithm"
    putStrLn "  - Type classes: ad hoc polymorphism (Eq, Show, Num)"
    putStrLn "  - Higher-order: map, filter, fold, take, drop"
    putStrLn "  - Composition: (.) operator"
    putStrLn "  - Currying: partial application"
    putStrLn "  - Lazy evaluation: infinite lists, lazy streams"
    putStrLn "  - Pattern matching: case expressions"
    putStrLn "  - Recursion: primary control flow (no loops)"
    putStrLn "  - Monads: IO, Maybe, Either type classes"
    putStrLn "  - List comprehensions: [x*2 | x <- xs]"
    putStrLn "  - do-notation: monadic sequencing"
