# Concurrency in Python

## Overview

Python provides **threading**, **multiprocessing**, and **asyncio** for
concurrency. Due to the GIL (Global Interpreter Lock), threading is limited
to I/O-bound tasks; true parallelism requires multiprocessing.

## Code

See `main.py` in this directory.

## How to Run

```bash
python3 main.py
```

## Key Concepts

- **threading module** — OS threads (limited by GIL)
- **multiprocessing module** — true parallelism with separate processes
- **asyncio (3.4+)** — cooperative multitasking with async/await
- **async/await syntax** — coroutines for async programming
- **Queue** — thread-safe queue for inter-thread communication
- **Lock/RLock** — mutex and read-write lock primitives
- **ThreadPoolExecutor** — thread pool from concurrent.futures
- **ProcessPoolExecutor** — process pool for CPU-bound parallelism
- **GIL** — Global Interpreter Lock limits threading parallelism

## Historical Context

Python (1991) added threading early but the GIL limits parallelism.
Python 3.2 (2011) improved concurrent.futures. Python 3.4 (2014)
added asyncio. Python 3.5 (2015) added async/await syntax.
Multiprocessing (Python 2.6, 2008) provides true parallelism
by bypassing the GIL.

For more on Python, see [docs/languages/python/](../../../docs/languages/python/)
