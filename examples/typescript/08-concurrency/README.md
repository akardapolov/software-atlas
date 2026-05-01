# Concurrency in TypeScript

## Overview

TypeScript inherits JavaScript's **single-threaded event loop** model with
asynchronous callbacks, promises, and async/await. True parallelism
requires Web Workers (browser) or Worker Threads (Node.js).

## Code

See `main.ts` in this directory.

## How to Run

```bash
tsc main.ts && node main.js
# Or with ts-node
ts-node main.ts
```

## Key Concepts

- **Async/await** — syntactic sugar for promises
- **Promise** — representation of eventual value
- **Promise.all** — wait for multiple promises
- **Promise.race** — first completed promise wins
- **Fetch API** — asynchronous HTTP requests
- **setTimeout/setInterval** — delayed execution
- **Event loop** — single-threaded cooperative multitasking
- **Web Workers** — true parallelism in browser
- **Worker Threads** — Node.js CPU parallelism
- **No shared memory** — all data must be copied to workers

## Historical Context

JavaScript (1995) was designed as single-threaded for browser simplicity.
Promises (ES6 2015) and async/await (ES8 2017) made
async code readable. TypeScript (2012) added type safety to async
code but maintains the same event-loop model.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
