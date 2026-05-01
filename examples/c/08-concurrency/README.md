# Concurrency in C

## Overview

C concurrency is built on **POSIX threads (pthreads)** — a low-level API
for creating and managing threads. C has no built-in concurrency; everything
is library-provided. Thread management is manual and requires careful
synchronization.

## Code

See `main.c` in this directory.

## How to Run

```bash
gcc -pthread main.c -o concurrency && ./concurrency
```

## Key Concepts

- **pthread_create** — spawn new threads
- **pthread_join** — wait for thread completion
- **pthread_mutex** — mutual exclusion lock for shared data
- **pthread_cond** — condition variables for signaling
- **Race conditions** — bugs from unsynchronized access
- **Deadlocks** — threads waiting on each other
- **Thread safety** — writing code that works correctly with threads
- **Atomic operations** — lock-free operations (C11 `stdatomic.h`)

## Historical Context

POSIX threads (1995) standardized thread programming on Unix.
C11 (2011) added `stdatomic.h` and `_Thread_local` for better
concurrency support, but pthreads remain the most widely used API.
C's threading influenced C++11's `std::thread` and later languages.

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
