{-
  Variables and Types in Haskell
  ==============================
  Haskell uses static, strong, inferred typing.
  All values are immutable. There are no "variables" in the
  imperative sense — only named bindings.
-}

module Main where

import Data.Char (toUpper, ord)

-- ── Helper ──────────────────────────────────────────────

section :: String -> IO ()
section title = putStrLn ("\n--- " ++ title ++ " ---")

-- ── Main ────────────────────────────────────────────────

main :: IO ()
main = do

    -- ── Basic types ──────────────────────────────────

    let count = 42 :: Int          -- fixed-width integer
    let big   = 42 :: Integer      -- arbitrary-precision integer
    let pi'   = 3.14159 :: Double  -- 64-bit float
    let active = True :: Bool
    let letter = 'A' :: Char
    let name   = "Software Engineering Atlas" :: String  -- String = [Char]

    putStrLn $ "count = " ++ show count
    putStrLn $ "big = " ++ show big
    putStrLn $ "pi = " ++ show pi'
    putStrLn $ "active = " ++ show active
    putStrLn $ "letter = " ++ show letter ++ " (ord " ++ show (ord letter) ++ ")"
    putStrLn $ "name = " ++ show name

    -- ── Type inference ───────────────────────────────

    section "Type inference"

    -- The compiler infers types — annotations are optional
    let x = 42          -- inferred as Integer (or Num a => a)
    let y = 3.14        -- inferred as Double (or Fractional a => a)
    let z = "hello"     -- inferred as String ([Char])
    let w = [1, 2, 3]   -- inferred as [Integer] (or Num a => [a])

    putStrLn $ "x = " ++ show x
    putStrLn $ "y = " ++ show y
    putStrLn $ "z = " ++ show z
    putStrLn $ "w = " ++ show w

    -- ── Immutability ─────────────────────────────────

    section "Immutability"

    -- There is no mutation. `let x = 42` is a permanent binding.
    -- You can shadow a name in a new scope, but the old value is unchanged.

    let a = 10
    let a' = a + 5  -- new binding, not mutation
    putStrLn $ "a = " ++ show a ++ ", a' = " ++ show a'

    -- ── No implicit conversions ──────────────────────

    section "No implicit conversions"

    -- Haskell does NOT implicitly convert between types
    let intVal = 42 :: Int
    let dblVal = 3.14 :: Double

    -- let sum = intVal + dblVal  -- TYPE ERROR! Int + Double

    let converted = fromIntegral intVal + dblVal  -- explicit conversion
    putStrLn $ "fromIntegral " ++ show intVal ++ " + " ++ show dblVal
             ++ " = " ++ show converted

    -- ── Tuples ───────────────────────────────────────

    section "Tuples"

    let point :: (Double, Double)
        point = (3.0, 4.0)

    putStrLn $ "point = " ++ show point
    putStrLn $ "fst = " ++ show (fst point) ++ ", snd = " ++ show (snd point)

    -- Destructuring
    let (px, py) = point
    let distance = sqrt (px * px + py * py)
    putStrLn $ "distance from origin = " ++ show distance

    -- ── Lists ────────────────────────────────────────

    section "Lists"

    let nums = [1, 2, 3, 4, 5] :: [Int]
    putStrLn $ "nums = " ++ show nums
    putStrLn $ "head = " ++ show (head nums)
    putStrLn $ "tail = " ++ show (tail nums)
    putStrLn $ "length = " ++ show (length nums)

    -- List operations (no mutation — returns new lists)
    putStrLn $ "0 : nums = " ++ show (0 : nums)        -- prepend
    putStrLn $ "nums ++ [6] = " ++ show (nums ++ [6])   -- append

    -- List comprehension
    let evens = [x' | x' <- [1..20], even x']
    putStrLn $ "evens 1..20 = " ++ show evens

    -- ── Maybe (no null!) ─────────────────────────────

    section "Maybe (no null)"

    -- Haskell has no null. Maybe represents "might not have a value"
    let found   = Just 42  :: Maybe Int
    let missing = Nothing  :: Maybe Int

    putStrLn $ "found = " ++ show found
    putStrLn $ "missing = " ++ show missing

    -- Pattern matching: you MUST handle both cases
    case found of
        Just n  -> putStrLn $ "Got: " ++ show n
        Nothing -> putStrLn "Got nothing"

    -- maybe: provide a default
    putStrLn $ "missing with default: " ++ show (maybe 0 id missing)

    -- ── Either (error handling) ──────────────────────

    section "Either (error handling)"

    -- Either is like Maybe but carries error information
    let ok  = Right 42   :: Either String Int
    let err = Left "oops" :: Either String Int

    putStrLn $ "ok = " ++ show ok
    putStrLn $ "err = " ++ show err

    case ok of
        Right n  -> putStrLn $ "Success: " ++ show n
        Left msg -> putStrLn $ "Error: " ++ msg

    -- ── Algebraic data types ─────────────────────────

    section "Algebraic data types"

    -- Sum type: a Shape is Circle OR Rectangle
    -- (defined at top level in a real program; shown here for clarity)
    putStrLn $ "Circle 5.0 → area = " ++ show (shapeArea (Circle 5.0))
    putStrLn $ "Rect 3 4   → area = " ++ show (shapeArea (Rect 3.0 4.0))

    -- ── Type classes ─────────────────────────────────

    section "Type classes"

    -- Type classes provide ad-hoc polymorphism
    -- show :: Show a => a -> String   (works for any type with Show)
    -- (==) :: Eq a => a -> a -> Bool  (works for any type with Eq)

    putStrLn $ "show 42 = " ++ show (42 :: Int)
    putStrLn $ "show True = " ++ show True
    putStrLn $ "show [1,2,3] = " ++ show ([1,2,3] :: [Int])
    putStrLn $ "42 == 42 = " ++ show ((42 :: Int) == 42)
    putStrLn $ "\"hello\" == \"world\" = " ++ show ("hello" == "world")

    -- ── Strings ──────────────────────────────────────

    section "Strings"

    -- String = [Char] (a list of characters — simple but inefficient)
    let greeting = "Hello, 世界"
    putStrLn $ "greeting = " ++ show greeting
    putStrLn $ "length = " ++ show (length greeting)
    putStrLn $ "head = " ++ show (head greeting)

    -- map over characters
    putStrLn $ "map toUpper = " ++ map toUpper greeting

    -- For efficient strings, use Data.Text (not shown here)

    -- ── Summary ──────────────────────────────────────

    section "Summary"
    putStrLn "Haskell: static + strong + inferred typing"
    putStrLn "  - Types checked at compile time (static)"
    putStrLn "  - No implicit conversions (strong)"
    putStrLn "  - Type inference: annotations optional (inferred)"
    putStrLn "  - Everything is immutable"
    putStrLn "  - No null: Maybe and Either instead"
    putStrLn "  - Algebraic data types (sum + product)"
    putStrLn "  - Type classes for polymorphism"
    putStrLn "  - Lazy evaluation"


-- ── ADT defined at module level ─────────────────────

data Shape = Circle Double | Rect Double Double
    deriving (Show)

shapeArea :: Shape -> Double
shapeArea (Circle r)  = pi * r * r
shapeArea (Rect w h) = w * h
