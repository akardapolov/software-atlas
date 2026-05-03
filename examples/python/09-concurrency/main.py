# Concurrency in Python
# ======================
# threading, multiprocessing, asyncio

import threading
import multiprocessing
import asyncio
import concurrent.futures
import time


# ── Basic thread ───────────────────────────────

print("--- Basic thread ---")

def hello_thread():
    print("Hello from thread!")

t = threading.Thread(target=hello_thread)
t.start()
t.join()

# ── Shared state with lock ──────────────────────

print("\n--- Shared state with lock ---")

counter = 0
lock = threading.Lock()

def increment():
    global counter
    for _ in range(1000):
        with lock:
            counter += 1

threads = [threading.Thread(target=increment) for _ in range(10)]

for t in threads:
    t.start()

for t in threads:
    t.join()

print(f"Counter: {counter}")

# ── ThreadPoolExecutor ───────────────────────────

print("\n--- ThreadPoolExecutor ---")

with concurrent.futures.ThreadPoolExecutor(max_workers=3) as executor:
    futures = [executor.submit(lambda n: n * n, i) for i in range(1, 6)]

    for f in concurrent.futures.as_completed(futures):
        print(f"Completed: {f.result()}")

# ── ProcessPoolExecutor (true parallelism) ────────

print("\n--- ProcessPoolExecutor (bypass GIL) ---")

def cpu_bound(n):
    """CPU-intensive task benefits from multiprocessing"""
    total = 0
    for i in range(1_000_000):
        total += i
    return total

with concurrent.futures.ProcessPoolExecutor(max_workers=4) as executor:
    results = list(executor.map(cpu_bound, [10, 20, 30, 40]))
    print(f"Results: {results}")

# ── async/await ───────────────────────────────

print("\n--- async/await ---")

async def fetch_data(id):
    await asyncio.sleep(0.2)
    return f"Data {id}"

async def main_async():
    tasks = [fetch_data(i) for i in range(1, 4)]
    results = await asyncio.gather(*tasks)
    print(f"Async results: {results}")

asyncio.run(main_async())

# ── Queue for producer-consumer ─────────────────

print("\n--- Queue (producer-consumer) ---")

queue = multiprocessing.Queue()

def producer():
    for i in range(1, 11):
        queue.put(i)
        print(f"Produced: {i}")
        time.sleep(0.05)

def consumer():
    while True:
        try:
            item = queue.get(timeout=1)
            print(f"Consumed: {item}")
            time.sleep(0.1)
            queue.task_done()
        except:
            break

p = multiprocessing.Process(target=producer)
c = multiprocessing.Process(target=consumer)

p.start()
c.start()

p.join()
queue.close()

# ── Summary ─────────────────────────────────────

print("\n--- Summary ---")
print("Python concurrency:")
print("  - threading: OS threads (limited by GIL)")
print("  - multiprocessing: true parallelism")
print("  - asyncio: cooperative multitasking")
print("  - concurrent.futures: high-level abstractions")
print("  - GIL: limits threading parallelism")
print("  - Use processes for CPU-bound tasks")
print("  - Use asyncio for I/O-bound tasks")
