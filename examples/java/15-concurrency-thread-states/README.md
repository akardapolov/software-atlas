# Java Thread States Demonstration

This package demonstrates all six thread states in Java and how threads transition between them.

## Thread States Overview

| State | Description | Trigger |
|-------|-------------|---------|
| **NEW** | Thread created but not started | `new Thread()` |
| **RUNNABLE** | Thread is running or ready to run | `start()` called |
| **BLOCKED** | Waiting for a monitor lock | Lock contention on synchronized |
| **WAITING** | Waiting indefinitely | `wait()`, `join()`, `LockSupport.park()` |
| **TIMED_WAITING** | Waiting with timeout | `sleep()`, `wait(timeout)`, `join(timeout)` |
| **TERMINATED** | Thread has finished execution | `run()` completed or uncaught exception |

## State Transition Diagram

```
NEW
  ↓
RUNNABLE
  ↓                   ↖ BLOCKED
  ↘                  (waiting for lock)
WAITING              (lock acquired)
  ↑                   ↓
  └────── TIMED_WAITING (timeout or notified)
  ↑
WAITING (notified)
  ↓
RUNNABLE
  ↓
TERMINATED
```

## Examples

### 1. BLOCKED State (Lock Contention)
Thread-2 becomes BLOCKED while trying to acquire a lock held by Thread-1.

### 2. WAITING State (wait/notify)
A thread waits indefinitely using `wait()` until another thread calls `notify()`.
Important: Always use `wait()` inside a synchronized block and check condition in a while loop.

### 3. TIMED_WAITING (wait with timeout)
A thread waits for a specified period using `wait(timeout)` or `Thread.sleep()`.
The thread awakens when timeout expires or when notified.

### 4. Producer-Consumer Pattern
Classic synchronization pattern where producer and consumer coordinate using wait/notify.
Demonstrates how threads alternate between RUNNABLE and WAITING states.

### 5. Thread Coordination
Multiple threads coordinate using `wait()` and `notifyAll()` to share state.

## Running the Examples

```bash
javac Main.java
java Main
```

## Key Best Practices

1. **Always use while loops with wait()** to handle spurious wakeups
2. **Always hold the monitor lock when calling wait()**, `notify()`, or `notifyAll()`
3. **Prefer notifyAll() over notify()** unless you're certain only one thread is waiting
4. **Handle InterruptedException** properly by restoring the interrupt status
5. **Keep synchronized blocks as short as possible** to minimize lock contention
