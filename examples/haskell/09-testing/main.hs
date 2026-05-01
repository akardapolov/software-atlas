-- Testing in Haskell
-- =====================
-- Hspec, QuickCheck, property-based testing

import Test.Hspec

-- ── Basic test (Hspec) ─────────────────────────

spec :: Spec
spec = do
    describe "addition" $ do
        it "adds two numbers" $ do
            (5 + 3) `shouldBe` (8 :: Int)

        it "returns zero for 0+0" $ do
            (0 + 0) `shouldBe` (0 :: Int)

    describe "string operations" $ do
        it "reverses a string" $ do
            reverse "hello" `shouldBe` "olleh"

-- ── Property-based test (QuickCheck) ─────────────

-- Note: QuickCheck needs to be installed
-- Use: cabal install QuickCheck

{- 

prop_reverse_reverse :: String -> Bool
prop_reverse_reverse xs = reverse (reverse xs) == xs

-- To run properties with Hspec:
-- main :: IO ()
-- main = hspec $ do
--     describe "list properties" $ do
--         it "reverse twice is identity" $ property $
--             \\xs -> reverse (reverse xs) == xs
--}

-}

-- ── Edge cases ─────────────────────────────────────

spec' :: Spec
spec' = do
    describe "edge cases" $ do
        it "handles empty list" $ do
            length ([] :: [Int]) `shouldBe` 0

        it "handles single element" $ do
            head [42] `shouldBe` 42

-- ── Summary ─────────────────────────────────────

spec'' :: Spec
spec'' = do
    describe "Haskell testing" $ do
        it "uses Hspec for BDD-style" $ do
            True `shouldBe` True

        it "QuickCheck for property-based testing" $ do
            True `shouldBe` True

-- ── Main ───────────────────────────────────────────

main :: IO ()
main = hspec $ do
    spec
    spec'
    spec''

    putStrLn "\n--- Summary ---"
    putStrLn "Haskell testing:"
    putStrLn "  - Hspec: describe, it, shouldBe"
    putStrLn "  - QuickCheck: property-based testing"
    putStrLn "  - Properties: tested against random inputs"
    putStrLn "  - Type safety: catches errors at compile time"
    putStrLn "  - Purity: no side effects in tests"
    putStrLn "  - Coverage: HPC (Haskell Program Coverage)"
