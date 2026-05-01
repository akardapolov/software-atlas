# Concurrency in Java

## Overview

Java provides multiple concurrency models: threads, executors, futures,
completable futures, and reactive streams. The modern approach emphasizes
the ExecutorService and CompletableFuture API over raw threads.

## Code

See `Main.java` in this directory.

## How to Run

```bash
javac Main.java && java Main
```

## Key Concepts

- **Thread class** — basic thread creation with `extends Thread`
- **Runnable interface** — preferred over extending Thread
- **ExecutorService** — thread pool management
- **Future** — handle asynchronous computation results
- **CompletableFuture** — chainable async operations
- **synchronized** — intrinsic locking for mutual exclusion
- **volatile** — ensures visibility across threads
- **Lock/Condition** — explicit locks with ReentrantLock
- **Concurrent collections** — thread-safe collections from `java.util.concurrent`

## Historical Context

Java 1.0 (1995) included basic thread support. Java 5 (2004) added
the `java.util.concurrent` package with executors and futures. Java 8 (2014)
added `CompletableFuture` for composable async operations. Influenced by CSP
and actor models, Java's concurrency has evolved toward safer abstractions.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
