{-# LANGUAGE TypeOperators #-}

-- OOP and Modules in Haskell
-- =========================
-- Demonstrates type classes, data types, modules, and basic Haskell OOP.
-- Haskell has no inheritance - uses type classes and ADTs instead.

module OOPAndModules where

import qualified Data.Map as Map
import qualified Data.Set as Set


-- ── Type Classes ─────────────────────────────

demonstrateTypeClasses :: IO ()
demonstrateTypeClasses = do
  putStrLn "--- Type Classes ---"

  -- Simple type class
  let person1 = Person "Ada" 36
  let person2 = Person "Bob" 42

  putStrLn $ "person1 = " ++ show person1
  putStrLn $ "person2 = " ++ show person2

  -- Compare (Eq instance derived automatically)
  putStrLn $ "person1 == person2? " ++ show (person1 == person2)
  putStrLn $ "person1 /= person2? " ++ show (person1 /= person2)


-- ── Data Types (ADTs) ─────────────────────

demonstrateDataTypes :: IO ()
demonstrateDataTypes = do
  putStrLn "\n--- Data Types (ADTs) ---"

  -- Sum type (tagged union)
  data Shape
    = Circle Float
    | Rectangle Float Float

  data Circle = Circle Float

  data Rectangle = Rectangle Float Float

  let shapes = [Circle 2.0, Rectangle 5.0 3.0]
  putStrLn $ "shapes = " ++ show shapes

  -- Pattern matching on ADTs
  let describeShape :: Shape -> String
  describeShape (Circle r) =
      "Circle with radius " ++ show r
  describeShape (Rectangle w h) =
      "Rectangle " ++ show w ++ "x" ++ show h

  mapM_ describeShape shapes
  putStrLn $ "\nDescriptions:"
  mapM_ describeShape shapes

  -- Record type (product type)
  data Vector a b = Vector a b

  let v1 = Vector 1 2
  let v2 = Vector 3 4

  putStrLn $ "v1 = " ++ show v1
  putStrLn $ "v2 = " ++ show v2
  putStrLn $ "v1 <> v2 = " ++ show (v1 <> v2)

  -- Using record syntax
  data Person = Person { name :: String, age :: Int }

  let ada = Person "Ada" 36
  putStrLn $ "Person: name=" ++ personName ada ++ ", age=" ++ personAge ada


-- ── Newtype (Type Wrapper) ─────────────────

demonstrateNewtype :: IO ()
demonstrateNewtype = do
  putStrLn "\n--- Newtype (Type Wrapper) ---"

  newtype UserId = UserId String

  let uid1 = UserId "user123"
  let uid2 = UserId "user456"

  putStrLn $ "uid1 = " ++ show uid1
  putStrLn $ "uid2 = " ++ show uid2

  -- Pattern matching on newtype
  case uid1 of
    UserId id -> putStrLn $ "ID: " ++ id
    UserId _ -> putStrLn $ "Invalid ID"


-- ── Type Signatures ─────────────────────

demonstrateTypeSignatures :: IO ()
demonstrateTypeSignatures = do
  putStrLn "\n--- Type Signatures ---"

  -- Simple function
  greet :: String -> String
  greet name = "Hello, " ++ name ++ "!"

  -- Function with type variable
  showPerson :: Show a => String
  showPerson p = "Person: name=" ++ personName p ++ ", age=" ++ personAge p

  putStrLn $ showPerson person1
  putStrLn $ showPerson person2

  -- Higher-order function
  twice :: (a -> a) -> a -> a
  apply :: (a -> a -> a) -> a

  putStrLn $ "twice 5 = " ++ show (twice 5)
  putStrLn $ "apply twice 5 = " ++ show (apply twice 5 5)
  putStrLn $ "apply thrice 5 = " ++ show (apply thrice 5 5)


-- ── Modules ─────────────────────────────

demonstrateModules :: IO ()
demonstrateModules = do
  putStrLn "\n--- Modules ---"

  -- Current module
  putStrLn $ "Current module: Main"

  -- Qualified import
  putStrLn $ "Importing Map from Data.Map:"
  let m = Map.fromList [(1, "one"), (2, "two"), (3, "three")]
  putStrLn $ "Map size: " ++ show (Map.size m)

  -- Importing Set
  let s = Set.fromList [1, 2, 3, 2, 3]
  putStrLn $ "Set size: " ++ show (Set.size s)
  putStrLn $ "Set 1: " ++ show (Set.member 1 s)
  putStrLn $ "Set 2: " ++ show (Set.member 2 s)


-- ── Deriving Show Type Classes ─────────────────

demonstrateDeriving :: IO ()
demonstrateDeriving = do
  putStrLn "\n--- Deriving Show for Type Classes ---"

  -- Person with custom Show
  data Person = Person { name :: String, age :: Int }

  instance Show Person where
    show p = "Person(name=" ++ personName p ++ ", age=" ++ personAge p ++ ")"

  let ada = Person "Ada" 36
  putStrLn $ "Custom show: " ++ show ada


-- ── Lazy Evaluation ───────────────────────────

demonstrateLazyEvaluation :: IO ()
demonstrateLazyEvaluation = do
  putStrLn "\n--- Lazy Evaluation ---"

  -- Infinite list
  let naturals = [1..]
  let firstFive = take 5 naturals
  putStrLn $ "First 5 of infinite: " ++ show firstFive

  -- Lazy map
  let lazySquares = map (^2) naturals
  let firstFiveLazySquares = take 5 lazySquares
  putStrLn $ "First 5 lazy squares: " ++ show firstFiveLazySquares


-- ── Summary ───────────────────────────────────

main :: IO ()
main = do
  putStrLn "OOP and Modules in Haskell"
  putStrLn "========================"

  demonstrateTypeClasses()
  demonstrateDataTypes()
  demonstrateNewtype()
  demonstrateTypeSignatures()
  demonstrateModules()
  demonstrateDeriving()
  demonstrateLazyEvaluation()

  putStrLn "\n--- Summary ---"
  putStrLn "Haskell OOP:"
  putStrLn "  - Type classes: constructors and methods"
  putStrLn "  - Data types: ADTs with pattern matching"
  putStrLn "  - Records: named tuples with field access"
  putStrLn "  - Newtype: zero-cost type wrapper"
  putStrLn "  - No inheritance: type classes only"
  putStrLn "  - Modules: qualified imports, module declarations"
  putStrLn "  - Lazy evaluation: by default, seq for strictness"
