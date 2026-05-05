// Concurrency in Go
// =================
// Go uses goroutines + channels (CSP model)
// "Share memory by communicating, don't communicate by sharing"

package main

import (
	"fmt"
	"runtime"
	"sync"
	"time"
)

// ── Basic goroutine ───────────────────────────────

func printMessage(msg string) {
	fmt.Println("Goroutine:", msg)
}

// ── Worker with channels ─────────────────────────────

func worker(id int, jobs <-chan int, results chan<- int) {
	for job := range jobs {
		fmt.Printf("Worker %d: processing job %d\n", id, job)
		time.Sleep(100 * time.Millisecond)
		results <- job * 2
	}
}

// ── Producer ────────────────────────────────────────

func producer(jobs chan<- int, count int) {
	for i := 1; i <= count; i++ {
		jobs <- i
		fmt.Printf("Produced: %d\n", i)
		time.Sleep(50 * time.Millisecond)
	}
	close(jobs)
}

// ── Consumer ────────────────────────────────────────

func consumer(results <-chan int) {
	for result := range results {
		fmt.Printf("Consumed: %d\n", result)
	}
}

// ── Fan-in pattern ────────────────────────────────

func inputChan(name string) <-chan string {
	out := make(chan string)
	go func() {
		for i := 0; i < 3; i++ {
			out <- fmt.Sprintf("%s: %d", name, i)
			time.Sleep(50 * time.Millisecond)
		}
		close(out)
	}()
	return out
}

func fanIn(ch1, ch2 <-chan string) <-chan string {
	out := make(chan string)
	go func() {
		for {
			select {
			case s, ok := <-ch1:
				if !ok {
					ch1 = nil
				} else {
					out <- s
				}
			case s, ok := <-ch2:
				if !ok {
					ch2 = nil
				} else {
					out <- s
				}
			}
			if ch1 == nil && ch2 == nil {
				close(out)
				return
			}
		}
	}()
	return out
}

// ── Rate limiting with ticker ────────────────────────

func rateLimitedRequest(id int, ticker <-chan time.Time) {
	for range ticker {
		fmt.Printf("Request %d\n", id)
	}
}

// ── Timeout with select ─────────────────────────────

func doWork(ch <-chan int, done <-chan struct{}) {
	for {
		select {
		case val, ok := <-ch:
			if !ok {
				fmt.Println("Channel closed, stopping")
				return
			}
			fmt.Printf("Working on: %d\n", val)
		case <-done:
			fmt.Println("Timeout/cancel received, stopping")
			return
		}
	}
}

// ── Mutex for shared state ──────────────────────────

type SafeCounter struct {
	mu    sync.Mutex
	value int
}

func (c *SafeCounter) Increment() {
	c.mu.Lock()
	defer c.mu.Unlock()
	c.value++
}

func (c *SafeCounter) Get() int {
	c.mu.Lock()
	defer c.mu.Unlock()
	return c.value
}

func unsafeIncrement(counter *int, wg *sync.WaitGroup) {
	for i := 0; i < 1000; i++ {
		*counter++
	}
	wg.Done()
}

func safeIncrement(counter *SafeCounter, wg *sync.WaitGroup) {
	for i := 0; i < 1000; i++ {
		counter.Increment()
	}
	wg.Done()
}

// ── Main ───────────────────────────────────────────

func main() {
	fmt.Printf("Running on %d CPUs\n", runtime.NumCPU())

	// ── Basic goroutine ────────────────────────────

	fmt.Println("\n--- Basic goroutines ---")

	for i := 1; i <= 3; i++ {
		go printMessage(fmt.Sprintf("goroutine %d", i))
	}
	time.Sleep(100 * time.Millisecond)

	// ── Worker pool pattern ───────────────────────

	fmt.Println("\n--- Worker pool ---")

	jobs := make(chan int, 10)
	results := make(chan int, 10)

	// Start 3 workers
	for w := 1; w <= 3; w++ {
		go worker(w, jobs, results)
	}

	// Send 9 jobs and close channel
	go func() {
		for j := 1; j <= 9; j++ {
			jobs <- j
		}
		close(jobs)
	}()

	// Collect results
	for a := 1; a <= 9; a++ {
		<-results
	}
	fmt.Println("All jobs processed")

	// ── Producer-consumer ───────────────────────────

	fmt.Println("\n--- Producer-consumer ---")

	taskQueue := make(chan int, 5)
	resultQueue := make(chan int, 5)

	go producer(taskQueue, 10)
	go consumer(resultQueue)

	// Process pipeline
	for job := range taskQueue {
		resultQueue <- job * 2
	}
	close(resultQueue)

	// ── Fan-in pattern ─────────────────────────────

	fmt.Println("\n--- Fan-in pattern ---")

	out := fanIn(inputChan("Alice"), inputChan("Bob"))
	for msg := range out {
		fmt.Println(msg)
	}

	// ── Rate limiting ───────────────────────────────

	fmt.Println("\n--- Rate limiting ---")

	ticker := time.NewTicker(100 * time.Millisecond)
	defer ticker.Stop()

	go rateLimitedRequest(1, ticker.C)
	go rateLimitedRequest(2, ticker.C)

	time.Sleep(350 * time.Millisecond)
	ticker.Stop()

	// ── Timeout/cancellation ─────────────────────────

	fmt.Println("\n--- Timeout/cancellation with select ---")

	workCh := make(chan int, 5)
	done := make(chan struct{})

	go func() {
		for i := 0; i < 3; i++ {
			workCh <- i
		}
	}()

	// Simulate timeout
	go func() {
		time.Sleep(150 * time.Millisecond)
		close(done)
	}()

	doWork(workCh, done)

	// ── WaitGroup ───────────────────────────────────

	fmt.Println("\n--- WaitGroup ---")

	var wg sync.WaitGroup
	for i := 0; i < 5; i++ {
		wg.Add(1)
		go func(id int) {
			defer wg.Done()
			fmt.Printf("Task %d completed\n", id)
			time.Sleep(50 * time.Millisecond)
		}(i)
	}
	wg.Wait()
	fmt.Println("All tasks completed")

	// ── Mutex: unsafe vs safe ──────────────────────

	fmt.Println("\n--- Mutex: unsafe vs safe ---")

	// Unsafe
	var unsafe int
	var wgUnsafe sync.WaitGroup
	for i := 0; i < 10; i++ {
		wgUnsafe.Add(1)
		go unsafeIncrement(&unsafe, &wgUnsafe)
	}
	wgUnsafe.Wait()
	fmt.Printf("Unsafe counter: %d (may be wrong!)\n", unsafe)

	// Safe
	safe := SafeCounter{}
	var wgSafe sync.WaitGroup
	for i := 0; i < 10; i++ {
		wgSafe.Add(1)
		go safeIncrement(&safe, &wgSafe)
	}
	wgSafe.Wait()
	fmt.Printf("Safe counter: %d (always correct)\n", safe.Get())

	// ── Buffered channel ─────────────────────────────

	fmt.Println("\n--- Buffered channel ---")

	buffed := make(chan int, 3)

	buffed <- 1
	buffed <- 2
	buffed <- 3
	fmt.Println("Channel full? No, still can send 4")
	buffed <- 4

	// This would block
	// buffed <- 5  // BLOCKS until space available

	for v := range buffed {
		fmt.Printf("Received: %d\n", v)
		if v == 4 {
			close(buffed)
		}
	}

	// ── Select with default (non-blocking) ───────────

	fmt.Println("\n--- Non-blocking select ---")

	nonBlockingCh := make(chan int, 1)

	select {
	case val := <-nonBlockingCh:
		fmt.Printf("Received: %d\n", val)
	default:
		fmt.Println("Channel empty, doing something else")
	}

	nonBlockingCh <- 42
	select {
	case val := <-nonBlockingCh:
		fmt.Printf("Received: %d\n", val)
	default:
		fmt.Println("Channel empty, doing something else")
	}

	// ── Summary ─────────────────────────────────────

	fmt.Println("\n--- Summary ---")
	fmt.Println("Go concurrency:")
	fmt.Println("  - goroutines: lightweight threads, go func() {...}")
	fmt.Println("  - channels: typed communication <- and <-")
	fmt.Println("  - select: wait on multiple channel operations")
	fmt.Println("  - mutex: sync.Mutex for shared state")
	fmt.Println("  - WaitGroup: wait for goroutines to finish")
	fmt.Println("  - context: cancellation and deadlines")
	fmt.Println("  - CSP model: share by communicating")
}
