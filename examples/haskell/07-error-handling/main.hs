-- Error Handling in Haskell
-- ========================
-- Maybe, Either, IO monads, pattern matching

import Data.Maybe (fromMaybe, isJust, fromMaybe)
import Data.Either (Left, Right, fromEither)
import System.IO

-- ── Maybe monad (optional values) ───────────────────

safeDivide :: Int -> Int -> Maybe Int
safeDivide _ 0 = Nothing
safeDivide a b = Just (a `div` b)

maybeLength :: Maybe String -> Int
maybeLength (Just s) = length s
maybeLength Nothing = 0

-- ── Either monad (result or error) ───────────────────

data CalcError = DivisionByZero | NegativeInput

safeDivideEither :: Int -> Int -> Either CalcError Int
safeDivideEither _ 0 = Left DivisionByZero
safeDivideEither n d
    | n < 0     = Left NegativeInput
    | otherwise   = Right (n `div` d)

-- ── IO monad (side effects with errors) ───────────────

readFile :: FilePath -> IO String
readFile path = do
    contents <- readFile path
    return contents

readFileSafe :: FilePath -> IO (Either String String)
readFileSafe path = do
    result <- try (readFile path)
    case result of
        Left e   -> return (Left $ "Failed to read: " ++ show e)
        Right txt -> return (Right txt)

-- ── Pattern matching for error handling ───────────────────

processResult :: Maybe Int -> String
processResult Nothing = "No result"
processResult (Just n) = "Result: " ++ show n

maybeDivideAndPrint :: Int -> Int -> IO ()
maybeDivideAndPrint a b = putStrLn (processResult $ safeDivide a b)

eitherDivideAndPrint :: Int -> Int -> IO ()
eitherDivideAndPrint a b = case safeDivideEither a b of
    Left DivisionByZero -> putStrLn "Error: Division by zero"
                         Left NegativeInput -> putStrLn "Error: Negative input"
                         Right result          -> putStrLn ("Result: " ++ show result)

-- ── Custom error type ───────────────────────────────

data ValidationError = InvalidEmail | InvalidPassword | TooShort

validate :: String -> Either ValidationError String
validate email
    | '@' `isInfixOf` email = Right email
    | length email < 6 = Left TooShort
    | otherwise = Left InvalidEmail

-- ── Monad sequencing with do ───────────────────────

validateAndProcess :: String -> String -> IO (Either ValidationError Int)
validateAndProcess email = do
    case validate email of
        Left err -> return (Left err)
        Right _ -> return (Right $ length email)

-- ── Exception (rare in Haskell) ───────────────────────

-- Note: Exceptions are rarely used in Haskell
-- This is just for demonstration
exceptionExample :: IO ()
exceptionExample = do
    result <- try (error "Simulated error" :: String)
    case result of
        Left e -> putStrLn $ "Caught error: " ++ e
        Right _ -> putStrLn "No error"

-- ── Main ───────────────────────────────────────────

main :: IO ()
main = do

    putStrLn "--- Maybe monad ---"
    maybeDivideAndPrint 10 2
    maybeDivideAndPrint 10 0
    putStrLn $ "Maybe length: " ++ show (maybeLength "test")

    putStrLn "\n--- Either monad ---"
    eitherDivideAndPrint 10 2
    eitherDivideAndPrint 10 0
    putStrLn "Either length: " ++ show (eitherDivideAndPrint "test")

    putStrLn "\n--- IO monad ---"
    result <- readFileSafe "/proc/version"
    case result of
        Left err -> putStrLn err
        Right version -> putStrLn $ "Version: " ++ version

    putStrLn "\n--- Custom error types ---"
    putStrLn $ "Validation: " ++ show (validateAndProcess "test@ex.com")

    putStrLn "\n--- Pattern matching errors ---"
    putStrLn "Invalid password"
    putStrLn "Invalid email"

    putStrLn "\n--- Summary ---"
    putStrLn "Haskell error handling:"
    putStrLn "  - Maybe: Just value or Nothing for optional values"
    putStrLn "  - Either: Left err or Right value for errors"
    putStrLn "  - IO monad: wrap side effects in IO"
    putStrLn "  - Pattern matching: exhaustive case handling"
    putStrLn "  - No exceptions: use algebraic data types instead"
    putStrLn "  - do-notation: monadic sequencing"
    putStrLn "  - Custom types: data ValidationError = ..."
    putStrLn "  - Type safety: compiler ensures all cases handled"
