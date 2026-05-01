// Concurrency in Rust
// ===================
// threads, Mutex, channels, Send/Sync traits

use std::sync::{Arc, Mutex, RwLock, mpsc};
use std::thread;
use std::time::Duration;

fn main() {

    // ── Basic thread spawn ──────────────────────────

    println!("--- Basic thread spawn ---");

    let handle = thread::spawn(|| {
        println!("Hello from thread!");
    });

    handle.join().unwrap();

    // ── Thread with move closure ─────────────────────

    println!("\n--- Move closure ---");

    let data = vec![1, 2, 3];
    let handle = thread::spawn(move || {
        println!("Data in thread: {:?}", data);
    });

    handle.join().unwrap();

    // ── Mutex for shared state ─────────────────────

    println!("\n--- Mutex ---");

    let counter = Arc::new(Mutex::new(0));
    let mut handles = vec![];

    for _ in 0..10 {
        let counter = Arc::clone(&counter);
        let handle = thread::spawn(move || {
            let mut num = counter.lock().unwrap();
            *num += 100;
        });
        handles.push(handle);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    println!("Counter: {}", *counter.lock().unwrap());

    // ── RwLock (many readers, one writer) ─────────

    println!("\n--- RwLock ---");

    let data = Arc::new(RwLock::new(vec
![1, 2, 3, 4, 5]));

    // Multiple readers
    for i in 0..3 {
        let data = Arc::clone(&data);
        thread::spawn(move || {
            let r = data.read().unwrap();
            println!("Reader {}: {:?}", i, *r);
        });
    }

    // One writer
    let data = Arc::clone(&data);
    thread::spawn(move || {
        thread::sleep(Duration::from_millis(50));
        let mut w = data.write().unwrap();
        w.push(6);
        println!("Writer: added 6");
    });

    thread::sleep(Duration::from_millis(100));

    // ── MPSC channels ─────────────────────────────

    println!("\n--- MPSC channels ---");

    let (tx, rx) = mpsc::channel();

    // Spawn sender
    thread::spawn(move || {
        for i in 1..=5 {
            tx.send(i).unwrap();
            println!("Sent: {}", i);
        }
    });

    // Receive in main
    for received in rx {
        println!("Received: {}", received);
        if received == 5 { break; }
    }

    // ── Multiple producers ───────────────────────────

    println!("\n--- Multiple producers ---");

    let (tx, rx) = mpsc::channel();

    for i in 0..3 {
        let tx = tx.clone();
        thread::spawn(move || {
            for j in 0..3 {
                tx.send((i, j * 10)).unwrap();
            }
        });
    }

    drop(tx);  // Drop original sender

    for received in rx {
        println!("Received from {}: {}", received.0, received.1);
    }

    // ── Summary ────────────────────────────────────

    println!("\n--- Summary ---");
    println!("Rust concurrency:");
    println!("  - thread::spawn: create threads");
    println!("  - Arc: shared ownership");
    println!("  - Mutex: mutual exclusion");
    println!("  - RwLock: read-write lock");
    println!("  - mpsc: channels for message passing");
    println!("  - Send/Sync: compile-time thread safety");
    println!("  - move closures: explicit capture semantics");
}
