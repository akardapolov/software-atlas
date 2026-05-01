// Concurrency in TypeScript (JavaScript)
// ======================================
// async/await, promises, event loop

async function main() {

    // ── Basic async/await ─────────────────────────

    console.log("--- Basic async/await ---");

    const delay = (ms: number) =>
        new Promise(resolve => setTimeout(resolve, ms));

    await delay(200);
    console.log("After 200ms delay");

    // ── Promise chain ───────────────────────────────

    console.log("\n--- Promise chain ---");

    const fetchUser = async (id: number) => {
        await delay(100);
        return { id, name: `User ${id}` };
    };

    const user = await fetchUser(1)
        .then(u => ({ ...u, email: `${u.name}@example.com` }))
        .then(u => ({ ...u, role: 'admin' }));

    console.log(user);

    // ── Promise.all ────────────────────────────────

    console.log("\n--- Promise.all ---");

    const tasks = [
        delay(100).then(() => "Task 1"),
        delay(150).then(() => "Task 2"),
        delay(80).then(() => "Task 3"),
    ];

    const results = await Promise.all(tasks);
    console.log("All completed:", results);

    // ── Promise.race ──────────────────────────────

    console.log("\n--- Promise.race ---");

    const raceResults = await Promise.race([
        delay(200).then(() => "Fast"),
        delay(500).then(() => "Slow"),
    ]);

    console.log("Winner:", raceResults);

    // ── Fetch API (async HTTP) ─────────────────────

    console.log("\n--- Fetch API ---");

    try {
        const response = await fetch('https://api.github.com');
        console.log("Status:", response.status);
    } catch (error) {
        console.log("Fetch failed:", error);
    }

    // ── Concurrent async operations ─────────────────────

    console.log("\n--- Concurrent operations ---");

    const start = Date.now();

    await Promise.all([
        (async () => {
            console.log("Starting task A");
            await delay(300);
            console.log("Task A done");
        })(),
        (async () => {
            console.log("Starting task B");
            await delay(200);
            console.log("Task B done");
        })(),
        (async () => {
            console.log("Starting task C");
            await delay(250);
            console.log("Task C done");
        })(),
    ]);

    const elapsed = Date.now() - start;
    console.log(`Total time: ${elapsed}ms`);

    // ── async generator ─────────────────────────────

    console.log("\n--- Async generator ---");

    async function* numberGenerator() {
        let n = 1;
        while (n <= 5) {
            await delay(100);
            yield n++;
        }
    }

    for await (const num of numberGenerator()) {
        console.log("Generated:", num);
    }

    // ── Summary ─────────────────────────────────────

    console.log("\n--- Summary ---");
    console.log("TypeScript (JavaScript) concurrency:");
    console.log("  - async/await: cooperative multitasking");
    console.log("  - Promise: representation of future value");
    console.log("  - Promise.all: wait for multiple promises");
    console.log("  - Promise.race: first completed wins");
    console.log("  - Event loop: single-threaded execution");
    console.log("  - Web Workers: true parallelism (browser)");
    console.log("  - Worker Threads: CPU parallelism (Node.js)");
}

main().catch(console.error);
