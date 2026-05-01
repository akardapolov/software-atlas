// FP Features in TypeScript
// ======================
// Arrow functions, array methods, generics, async/await

function main() {

    // ── Arrow functions ───────────────────────────────────

    console.log("--- Arrow functions ---");

    const add = (a: number, b: number): number => a + b;
    const square = (x: number): number => x * x;
    const multiply = (a: number, b: number): number => a * b;

    console.log(`add(5, 3) = ${add(5, 3)}`);
    console.log(`square(4) = ${square(4)}`);


    // ── Array methods (map, filter, reduce) ───────────────

    console.log("\n--- Array methods ---");

    const numbers: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

    // map
    const squares = numbers.map(x => x * x);
    console.log("Squares:", squares);

    // filter
    const evens = numbers.filter(x => x % 2 === 0);
    console.log("Evens:", evens);

    // reduce
    const sum = numbers.reduce((acc, x) => acc + x, 0);
    console.log("Sum:", sum);

    // find
    const firstEven = numbers.find(x => x % 2 === 0);
    console.log("First even:", firstEven);

    // some/any
    const hasOdd = numbers.some(x => x % 2 !== 0);
    console.log("Has odd?", hasOdd);

    const allPositive = numbers.every(x => x > 0);
    console.log("All positive?", allPositive);


    // ── Method references ─────────────────────────────

    console.log("\n--- Method references ---");

    const str = "  hello  ";
    console.log("trim:", str.trim());
    console.log("toUpperCase:", str.toUpperCase());

    const trimToUpperCase = str.trim().toUpperCase;
    console.log("trimToUpperCase:", trimToUpperCase);


    // ── Chaining methods ───────────────────────────────

    console.log("\n--- Chaining methods ---");

    const result = numbers
        .filter(x => x % 2 === 0)
        .map(x => x * x)
        .slice(0, 3);

    console.log("First 3 even squares:", result);


    // ── Function composition ─────────────────────────────

    console.log("\n--- Function composition ---");

    const inc = (x: number) => x + 1;
    const triple = (x: number) => x * 3;

    // Manual composition
    const incThenTriple = (x: number) => triple(inc(x));
    console.log("incThenTriple(5) =", incThenTriple(5));

    // Compose helper
    function compose<A, B, C>(f: (x: A) => B, g: (y: B) => C): (x: A) => C(g(f(x)));
    const tripleThenInc = compose(triple, inc);
    console.log("compose(triple, inc)(5) =", tripleThenInc(5));


    // ── Optional chaining ───────────────────────────────

    console.log("\n--- Optional chaining ---");

    const maybeValue: number | null = Math.random() > 0.5 ? Math.floor(Math.random() * 100) : null;

    const doubled = maybeValue?.toString().repeat(2) ?? "no value";
    console.log("Doubled:", doubled);


    // ── Generics ───────────────────────────────────────

    console.log("\n--- Generics ---");

    function first<T>(items: T[]): T | undefined {
        return items[0];
    }

    function identity<T>(value: T): T {
        return value;
    }

    console.log("First of [1, 2, 3]:", first([1, 2, 3]));
    console.log("Identity(42):", identity(42));


    // ── Destructuring ───────────────────────────────

    console.log("\n--- Destructuring ---");

    const point = { x: 3, y: 4 };
    const { x, y } = point;
    console.log(`Point: (${x}, ${y})`);

    const [first, second] = [1, 2];
    console.log(`First: ${first}, Second: ${second}`);


    // ── Spread operator ─────────────────────────────

    console.log("\n--- Spread operator ---");

    function sumAll(...nums: number[]): number {
        return nums.reduce((a, b) => a + b, 0);
    }

    console.log("Sum of 1..5:", sumAll(1, 2, 3, 4, 5));

    function maxAll(...nums: number[]): number {
        return Math.max(...nums);
    }

    console.log("Max of 1, 5, 3:", maxAll(1, 5, 3));


    // ── Async/await with arrays ─────────────────────────────

    console.log("\n--- Async/await arrays ---");

    async function fetchAll(urls: string[]): Promise<string[]> {
        return Promise.all(urls.map(async url => {
            await new Promise(r => setTimeout(() => r(`fetched ${url}`), 100));
        }));
    }

    fetchAll(["url1", "url2", "url3"]).then(results => {
        console.log("Fetched:", results);
    });


    // ── Summary ─────────────────────────────────────

    console.log("\n--- Summary ---");
    console.log("TypeScript FP features:");
    console.log("  - Arrow functions: const f = (x) => {}");
    console.log("  - Array methods: map, filter, reduce, find");
    console.log("  - Method references: obj.method");
    console.log("  - Optional chaining: ?. operator");
    console.log("  - Generics: <T>(x: T)");
    console.log("  - Destructuring: const {x, y} = obj");
    console.log("  - Spread operator: ...array, ...rest");
    console.log("  - Chaining: methods().method().method()");
    console.log("  - Async/await: Promise.all, async functions");
    console.log("  - Type inference: explicit types optional");
    console.log("  - Function composition: manual or pipe libraries");
}
