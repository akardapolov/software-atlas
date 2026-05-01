package org.example;

/**
 * Java Thread States Demonstration
 *
 * This class demonstrates all major thread states in Java:
 * - NEW: Thread created but not started
 * - RUNNABLE: Thread is running or ready to run
 * - BLOCKED: Thread waiting for a monitor lock (lock contention)
 * - WAITING: Thread waiting indefinitely for another thread's action
 * - TIMED_WAITING: Thread waiting with a specified timeout
 * - TERMINATED: Thread has completed execution
 */
public class Main {

    private static final Object sharedLock = new Object();
    private static boolean condition = false;
    private static int buffer;
    private static boolean available = false;
    private static int counter = 0;
    private static final int LIMIT = 5;

    public static void main(String[] args) {
        System.out.println("=== 1. BLOCKED State Example (Lock Contention) ===");
        blockedStateExample();

        System.out.println("\n=== 2. WAITING State Example (wait/notify) ===");
        waitingStateExample();

        System.out.println("\n=== 3. TIMED_WAITING Example (wait with timeout) ===");
        timedWaitingExample();

        System.out.println("\n=== 4. Producer-Consumer Example ===");
        producerConsumerExample();

        System.out.println("\n=== 5. Thread Coordination Example ===");
        threadCoordinationExample();
    }

    /**
     * BLOCKED State Example
     *
     * A thread enters BLOCKED state when it tries to acquire a monitor lock
     * that another thread currently holds. This typically happens with
     * synchronized blocks/methods when there is lock contention.
     */
    private static void blockedStateExample() {
        Runnable task = () -> {
            synchronized (sharedLock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                try {
                    Thread.sleep(1000); // TIMED_WAITING while holding lock
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start(); // Thread-2 becomes BLOCKED while Thread-1 holds the lock

        // Wait for threads to complete
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * WAITING State Example
     *
     * A thread enters WAITING state when it calls wait() without a timeout.
     * The thread waits indefinitely until another thread calls notify() or notifyAll().
     *
     * Pattern: Always use wait() inside a synchronized block and check condition
     * in a while loop to handle spurious wakeups.
     */
    private static void waitingStateExample() {
        Thread waiter = new Thread(() -> {
            synchronized (sharedLock) {
                while (!condition) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " waiting...");
                        sharedLock.wait(); // Enters WAITING state
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " condition met!");
            }
        }, "Waiter-Thread");

        Thread notifier = new Thread(() -> {
            synchronized (sharedLock) {
                try {
                    Thread.sleep(2000); // TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                condition = true;
                sharedLock.notify(); // Wake up one waiting thread
                System.out.println(Thread.currentThread().getName() + " notified waiter");
            }
        }, "Notifier-Thread");

        waiter.start();
        notifier.start();

        try {
            waiter.join();
            notifier.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * TIMED_WAITING State Example
     *
     * A thread enters TIMED_WAITING state when it calls a timed wait method:
     * - Thread.sleep(long millis)
     * - Object.wait(long timeout)
     * - Thread.join(long millis)
     *
     * The thread awakens when timeout expires or another thread notifies it.
     */
    private static void timedWaitingExample() {
        Thread timedWaiter = new Thread(() -> {
            synchronized (sharedLock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " timed waiting...");
                    sharedLock.wait(3000); // TIMED_WAITING for 3 seconds
                    System.out.println(Thread.currentThread().getName() + " awakened");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Timed-Waiter");

        timedWaiter.start();

        try {
            timedWaiter.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Producer-Consumer Pattern
     *
     * Classic synchronization pattern where:
     * - Producer produces items when buffer is empty and notifies consumer
     * - Consumer consumes items when buffer has data and notifies producer
     *
     * Both threads alternate between RUNNABLE and WAITING states.
     */
    private static void producerConsumerExample() {
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                synchronized (sharedLock) {
                    while (available) {
                        try {
                            sharedLock.wait(); // WAITING for consumer to consume
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    buffer = i;
                    available = true;
                    System.out.println("Produced: " + i);
                    sharedLock.notify(); // Notify consumer
                }
                try {
                    Thread.sleep(500); // TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                synchronized (sharedLock) {
                    while (!available) {
                        try {
                            sharedLock.wait(); // WAITING for producer to produce
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("Consumed: " + buffer);
                    available = false;
                    sharedLock.notify(); // Notify producer
                }
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Thread Coordination Example
     *
     * Demonstrates coordination between multiple threads using wait/notifyAll.
     * The incrementer thread signals the monitor thread periodically,
     * allowing the monitor to observe the shared counter state.
     */
    private static void threadCoordinationExample() {
        Thread incrementer = new Thread(() -> {
            while (true) {
                synchronized (sharedLock) {
                    if (counter >= LIMIT) {
                        sharedLock.notifyAll(); // Wake up all waiting threads
                        break;
                    }
                    counter++;
                    System.out.println("Incremented to: " + counter);
                    if (counter % 2 == 0) {
                        sharedLock.notifyAll(); // Signal monitor to check
                        try {
                            sharedLock.wait(); // WAITING for monitor acknowledgment
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                try {
                    Thread.sleep(300); // TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Incrementer");

        Thread monitor = new Thread(() -> {
            synchronized (sharedLock) {
                while (counter < LIMIT) {
                    try {
                        sharedLock.wait(); // WAITING for incrementer signal
                        System.out.println("Monitor sees: " + counter);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("Monitor: Final value = " + counter);
            }
        }, "Monitor");

        incrementer.start();
        monitor.start();

        try {
            incrementer.join();
            monitor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
