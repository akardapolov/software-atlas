-- Data Structures in Haskell
-- =========================
-- Demonstrates lists, Data.Map, Data.Set, custom data types.
-- Haskell provides persistent, lazy data structures with type safety.

module Main where

import qualified Data.Map as Map
import qualified Data.Set as Set

-- ── Lists (linked, lazy) ─────────────────────

demonstrateLists :: IO ()
demonstrateLists = do
  putStrLn "--- Lists (linked, lazy) ---"

  -- List literal
  let fruits = ["apple", "banana", "cherry"]
  putStrLn $ "fruits = " ++ show fruits

  -- Indexing (head, tail, !! operator)
  putStrLn $ "head = " ++ show (head fruits)        -- apple
  putStrLn $ "tail = " ++ show (tail fruits)        -- ["banana", "cherry"]
  putStrLn $ "fruits !! 0 = " ++ show (fruits !! 0)  -- apple

  -- Pattern matching (destructuring)
  let (first:rest) = fruits
  putStrLn $ "first = " ++ show first
  putStrLn $ "rest = " ++ show rest

  -- Cons (prepend)
  let fruits2 = "date" : fruits
  putStrLn $ "After cons 'date': " ++ show fruits2

  -- List comprehension
  let squares = [x * x | x <- [1..10]]
  putStrLn $ "squares (1-10): " ++ show squares

  let evens = [x | x <- [1..20], even x]
  putStrLn $ "evens (1-20): " ++ show evens

  -- map, filter, fold
  let doubled = map (*2) [1..5]
  putStrLn $ "doubled (1-5): " ++ show doubled

  let sumList = foldl (+) 0 [1..10]
  putStrLn $ "sum (1-10): " ++ show sumList

  let product = foldl (*) 1 [1..5]
  putStrLn $ "product (1-5): " ++ show product

-- ── Tuples (fixed-size, heterogenous) ─────────────

demonstrateTuples :: IO ()
demonstrateTuples = do
  putStrLn "\n--- Tuples (fixed-size) ---"

  -- Pair
  let point = (10, 20)
  putStrLn $ "point = " ++ show point

  -- Destructuring
  let (x, y) = point
  putStrLn $ "x = " ++ show x ++ ", y = " ++ show y

  -- Triple
  let rgb = (255, 0, 128)
  putStrLn $ "RGB = " ++ show rgb

  -- Nested tuples
  let nested = ((1, 2), (3, 4))
  putStrLn $ "nested = " ++ show nested

-- ── Maps (key-value) ─────────────────────────────

demonstrateMaps :: IO ()
demonstrateMaps = do
  putStrLn "\n--- Maps (key-value) ---"

  -- Create map from list
  let person = Map.fromList [("name", "Ada"), ("age", 36), ("city", "London")]
  putStrLn $ "person = " ++ show (Map.toList person)

  -- Access (Maybe for missing keys)
  putStrLn $ "name = " ++ show (Map.lookup "name" person)
  putStrLn $ "age = " ++ show (Map.lookup "age" person)
  putStrLn $ "email = " ++ show (Map.lookup "email" person)

  -- Insert
  let person2 = Map.insert "email" "ada@example.com" person
  putStrLn $ "After insert email: " ++ show (Map.toList person2)

  -- Delete
  let person3 = Map.delete "city" person2
  putStrLn $ "After delete city: " ++ show (Map.toList person3)

  -- Check membership
  putStrLn $ "has 'email'? " ++ show (Map.member "email" person3)

-- ── Sets (unique elements) ─────────────────────

demonstrateSets :: IO ()
demonstrateSets = do
  putStrLn "\n--- Sets (unique elements) ---"

  -- Create set (duplicates removed)
  let tags = Set.fromList ["python", "typing", "python", "data"]
  putStrLn $ "tags = " ++ show (Set.toList tags)
  putStrLn $ "size = " ++ show (Set.size tags)

  -- Operations
  let setA = Set.fromList [1..5]
  let setB = Set.fromList [4..8]

  let union = Set.union setA setB
  putStrLn $ "union: " ++ show (Set.toList union)

  let intersection = Set.intersection setA setB
  putStrLn $ "intersection: " ++ show (Set.toList intersection)

  let difference = Set.difference setA setB
  putStrLn $ "difference A-B: " ++ show (Set.toList difference)

  -- Subset check
  let subset = Set.fromList [2, 3, 4]
  putStrLn $ "is [2,3,4] subset of [1..5]? " ++ show (Set.isSubsetOf subset setA)

-- ── Custom Data Types ─────────────────────────────

data Point = Point { x :: Int, y :: Int }

data Person = Person { name :: String, age :: Int }

data Status = Active | Inactive | Pending String

demonstrateCustomTypes :: IO ()
demonstrateCustomTypes = do
  putStrLn "\n--- Custom Data Types ---"

  -- Record syntax for constructing
  let p = Point 10 20
  putStrLn $ "Point = " ++ show p

  -- Field access
  putStrLn $ "x = " ++ show (x p)
  putStrLn $ "y = " ++ show (y p)

  -- Person with string field
  let ada = Person "Ada" 36
  putStrLn $ "Person = " ++ show ada

  -- Enum variants
  let status1 = Active
  let status2 = Inactive
  let status3 = Pending "Waiting for approval"

  putStrLn $ "status1 = " ++ show status1
  putStrLn $ "status2 = " ++ show status2
  putStrLn $ "status3 = " ++ show status3

  -- Pattern matching on enums
  case status3 of
    Pending msg -> putStrLn $ "Pending: " ++ msg
    _ -> putStrLn $ "Not pending"

-- ── Lazy Evaluation ─────────────────────────────────

demonstrateLazy :: IO ()
demonstrateLazy = do
  putStrLn "\n--- Lazy Evaluation ---"

  -- Infinite list
  let naturals = [1..]
  putStrLn $ "First 5 of infinite: " ++ show (take 5 naturals)

  -- Lazy map
  let squares = map (^2) [1..]
  putStrLn $ "First 5 of lazy squares: " ++ show (take 5 squares)

  -- Lazy filter
  let evens = filter even [1..]
  putStrLn $ "First 5 of lazy evens: " ++ show (take 5 evens)

  putStrLn "Lazy sequences are computed on demand!"

-- ── Newtype (type wrapper) ─────────────────────

newtype UserId = UserId String

demonstrateNewtype :: IO ()
demonstrateNewtype = do
  putStrLn "\n--- Newtype (type wrapper) ---"

  let uid = UserId "user123"
  putStrLn $ "UserId = " ++ show uid

  -- Unwrap (pattern matching)
  case uid of
    UserId id -> putStrLn $ "id = " ++ id

-- ── Maybe (optional values) ─────────────────────────

demonstrateMaybe :: IO ()
demonstrateMaybe = do
  putStrLn "\n--- Maybe (optional values) ---"

  -- Just value
  let maybe1 = Just 42
  putStrLn $ "maybe1 = " ++ show maybe1

  -- Nothing (no value)
  let maybe2 = Nothing
  putStrLn $ "maybe2 = " ++ show maybe2

  -- Pattern matching
  case maybe1 of
    Just n -> putStrLn $ "Just contains: " ++ show n
    Nothing -> putStrLn $ "Nothing, no value"

  -- Maybe monadic operations
  let safeDivide _ 0 = Nothing
  let safeDivide x y = Just (x `div` y)

  putStrLn $ "10 / 0 = " ++ show (safeDivide 10 0)
  putStrLn $ "10 / 3 = " ++ show (safeDivide 10 3)

-- ── Summary ───────────────────────────────────────────

main :: IO ()
main = do
  putStrLn "Data Structures in Haskell"
  putStrLn "========================"

  demonstrateLists()
  demonstrateTuples()
  demonstrateMaps()
  demonstrateSets()
  demonstrateCustomTypes()
  demonstrateLazy()
  demonstrateNewtype()
  demonstrateMaybe()

  putStrLn "\n--- Summary ---"
  putStrLn "Haskell data structures:"
  putStrLn "  - List: linked, lazy, cons for prepend"
  putStrLn "  - Map: Data.Map (ordered balanced tree)"
  putStrLn "  - Set: Data.Set (ordered balanced tree)"
  putStrLn "  - Tuples: fixed-size heterogenous"
  putStrLn "  - Algebraic data types: sum types with pattern matching"
  putStrLn "  - Records: field access with dot syntax"
  putStrLn "  - Maybe: optional values (no null pointer)"
  putStrLn "  - Lazy evaluation: computed on demand"
  putStrLn "  - Type classes: ad-hoc polymorphism"
  putStrLn "  - Newtype: zero-overhead wrapper"
