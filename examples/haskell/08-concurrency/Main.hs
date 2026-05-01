-- Concurrency in Haskell
-- ======================
-- forkIO, MVar, STM, async

import Control.Concurrent
import Control.Concurrent.MVar
import Control.Concurrent.STM
import Control.Concurrent.STM.TVar
import Control.Monad (void, forM_)

main :: IO ()
main = do

    -- ── Basic forkIO ──────────────────────────────

    putStrLn "--- Basic forkIO ---"

    forkIO $ putStrLn "Hello from forkIO thread!"
    threadDelay 100000  -- 100ms

    -- ── MVar (shared state) ─────────────────────

    putStrLn "\n--- MVar (shared mutable state) ---"

    counter <- newMVar 0

    let increment = do
            modifyMVar counter (+ 1)

    forkIO $ replicateM_ 1000 increment
    forkIO $ replicateM_ 1000 increment
    forkIO $ replicateM_ 1000 increment

    threadDelay 200000
    final <- readMVar counter
    putStrLn $ "Counter value: " ++ show final

    -- ── STM (Software Transactional Memory) ─────────

    putStrLn "\n--- STM (transactional updates) ---"

    account1 <- newTVarIO 100
    account2 <- newTVarIO 100

    let transfer amount = do
            from <- readTVar account1
            to <- readTVar account2
            check (from >= amount)
            writeTVar account1 (from - amount)
            writeTVar account2 (to + amount)

    forkIO $ atomically $ transfer 50
    forkIO $ atomically $ transfer 30

    threadDelay 100000

    balance1 <- readTVarIO account1
    balance2 <- readTVarIO account2
    putStrLn $ "Account 1: " ++ show balance1
    putStrLn $ "Account 2: " ++ show balance2

    -- ── Async / wait ─────────────────────────────

    putStrLn "\n--- Async / wait ---"

    async $ do
        threadDelay 200000
        putStrLn "Async task completed"

    putStrLn "Main continuing..."
    threadDelay 300000

    -- ── STM with retry ─────────────────────────────

    putStrLn "\n--- STM with retry ---"

    box <- newTVarIO (Nothing :: Maybe Int)

    let putter = do
            threadDelay 50000
            atomically $ writeTVar box (Just 42)

    let taker = do
            val <- atomically $ do
                v <- readTVar box
                case v of
                    Just x -> return x
                    Nothing -> retry  -- block until value available
            putStrLn $ "Got value: " ++ show val

    forkIO taker
    threadDelay 100000  -- delay before putting
    forkIO putter

    threadDelay 200000

    -- ── Summary ───────────────────────────────────

    putStrLn "\n--- Summary ---"
    putStrLn "Haskell concurrency:"
    putStrLn "  - forkIO: spawn lightweight threads"
    putStrLn "  - MVar: shared state with blocking"
    putStrLn "  - STM: transactional memory for composable updates"
    putStrLn "  - TVar: transactional variables"
    putStrLn "  - async/wait: capture async results"
    putStrLn "  - retry: block until STM condition satisfied"
    putStrLn "  - Pure functions + STM = no data races"
