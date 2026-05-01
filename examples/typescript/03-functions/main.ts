/**
 * Functions in TypeScript
 * =======================
 * TypeScript adds type annotations to JavaScript's first-class functions,
 * closures, arrow functions, and higher-order patterns.
 */

// ── Basic functions ──────────────────────────────────

function greet(name: string): string {
    return `Hello, ${name}!`;
}

console.log(greet("Atlas"));

// Arrow function (concise syntax)
const add = (a: number, b: number): number => a + b;
console.log(`add(3, 4) = ${add(3, 4)}`);

// ── Function types ───────────────────────────────────

console.log("\n--- Function types ---");

// Explicit function type
type BinaryOp = (a: number, b: number) => number;

const multiply: BinaryOp = (a, b) => a * b;
console.log(`multiply(3, 4) = ${multiply(3, 4)}`);

// Functions in data structures
const ops: Record<string, BinaryOp> = {
    add: (a, b) => a + b,
    sub: (a, b) => a - b,
    mul: (a, b) => a * b,
};

for (const [name, fn] of Object.entries(ops)) {
    console.log(`${name}(10, 3) = ${fn(10, 3)}`);
}

// ── Optional and default parameters ──────────────────

console.log("\n--- Optional and default parameters ---");

function power(base: number, exponent: number = 2): number {
    return base ** exponent;
}

console.log(`power(3) = ${power(3)}`);
console.log(`power(2, 10) = ${power(2, 10)}`);

function greetOptional(name: string, greeting?: string): string {
    return `${greeting ?? "Hello"}, ${name}!`;
}

console.log(greetOptional("Atlas"));
console.log(greetOptional("Atlas", "Howdy"));

// ── Rest parameters (variadic) ───────────────────────

console.log("\n--- Rest parameters ---");

function sum(...nums: number[]): number {
    return nums.reduce((acc, n) => acc + n, 0);
}

console.log(`sum(1, 2, 3, 4, 5) = ${sum(1, 2, 3, 4, 5)}`);

// ── Higher-order functions ───────────────────────────

console.log("\n--- Higher-order functions ---");

const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// map
const squares = numbers.map(x => x * x);
console.log(`squares = [${squares}]`);

// filter
const evens = numbers.filter(x => x % 2 === 0);
console.log(`evens = [${evens}]`);

// reduce
const total = numbers.reduce((acc, x) => acc + x, 0);
console.log(`sum = ${total}`);

const product = numbers.reduce((acc, x) => acc * x, 1);
console.log(`product = ${product}`);

// Chaining
const sumOfSquaresOfEvens = numbers
    .filter(x => x % 2 === 0)
    .map(x => x * x)
    .reduce((acc, x) => acc + x, 0);
console.log(`sum of squares of evens = ${sumOfSquaresOfEvens}`);

// find, some, every
console.log(`find(>5) = ${numbers.find(x => x > 5)}`);
console.log(`some(>5) = ${numbers.some(x => x > 5)}`);
console.log(`every(>0) = ${numbers.every(x => x > 0)}`);

// flatMap
const nested = [[1, 2], [3, 4], [5, 6]];
const flat = nested.flatMap(x => x);
console.log(`flatMap = [${flat}]`);

// ── Closures ─────────────────────────────────────────

console.log("\n--- Closures ---");

function makeMultiplier(factor: number): (x: number) => number {
    return (x: number) => x * factor;  // captures factor
}

const double = makeMultiplier(2);
const triple = makeMultiplier(3);
console.log(`double(5) = ${double(5)}`);
console.log(`triple(5) = ${triple(5)}`);

// Counter with mutable closure
function makeCounter(start = 0): () => number {
    let count = start;
    return () => ++count;
}

const counter = makeCounter();
console.log(`counter() = ${counter()}`);
console.log(`counter() = ${counter()}`);
console.log(`counter() = ${counter()}`);

// ── Generic functions ────────────────────────────────

console.log("\n--- Generics ---");

function first<T>(arr: T[]): T | undefined {
    return arr[0];
}

console.log(`first([1,2,3]) = ${first([1, 2, 3])}`);
console.log(`first(["a","b"]) = ${first(["a", "b"])}`);

function applyTwice<T>(f: (x: T) => T, x: T): T {
    return f(f(x));
}

console.log(`applyTwice(x+1, 5) = ${applyTwice((x: number) => x + 1, 5)}`);
console.log(`applyTwice(x*2, 3) = ${applyTwice((x: number) => x * 2, 3)}`);

// ── Function composition ────────────────────────────

console.log("\n--- Composition ---");

function compose<A, B, C>(f: (b: B) => C, g: (a: A) => B): (a: A) => C {
    return (x: A) => f(g(x));
}

function pipe<T>(...fns: Array<(x: T) => T>): (x: T) => T {
    return (x: T) => fns.reduce((acc, f) => f(acc), x);
}

const shout = compose(
    (s: string) => s + "!",
    (s: string) => s.toUpperCase(),
);
console.log(`shout("hello") = "${shout("hello")}"`);

const process = pipe(
    (s: string) => s.trim(),
    (s: string) => s.toLowerCase(),
    (s: string) => s.replace(/\b\w/g, c => c.toUpperCase()),
);
console.log(`process("  hello world  ") = "${process("  hello world  ")}"`);

// ── Partial application / currying ───────────────────

console.log("\n--- Currying ---");

// Manual currying
function curriedAdd(a: number): (b: number) => number {
    return (b: number) => a + b;
}

const add5 = curriedAdd(5);
console.log(`add5(10) = ${add5(10)}`);

// Generic curry for binary functions
function curry<A, B, C>(f: (a: A, b: B) => C): (a: A) => (b: B) => C {
    return (a: A) => (b: B) => f(a, b);
}

const curriedMul = curry(multiply);
const timesThree = curriedMul(3);
console.log(`timesThree(7) = ${timesThree(7)}`);

// ── Discriminated unions as function returns ─────────

console.log("\n--- Result type ---");

type Result<T, E> =
    | { ok: true; value: T }
    | { ok: false; error: E };

function safeDivide(a: number, b: number): Result<number, string> {
    if (b === 0) return { ok: false, error: "division by zero" };
    return { ok: true, value: a / b };
}

const r1 = safeDivide(10, 3);
const r2 = safeDivide(10, 0);

function showResult<T, E>(r: Result<T, E>): string {
    return r.ok ? `Ok(${r.value})` : `Err(${r.error})`;
}

console.log(`safeDivide(10, 3) = ${showResult(r1)}`);
console.log(`safeDivide(10, 0) = ${showResult(r2)}`);

// ── Overloaded function signatures ───────────────────

console.log("\n--- Overloaded signatures ---");

function format(value: string): string;
function format(value: number): string;
function format(value: string | number): string {
    if (typeof value === "string") return `"${value}"`;
    return value.toFixed(2);
}

console.log(`format("hello") = ${format("hello")}`);
console.log(`format(3.14159) = ${format(3.14159)}`);

// ── Summary ──────────────────────────────────────────

console.log("\n--- Summary ---");
console.log("TypeScript functions:");
console.log("  - First-class: values, assignable, passable");
console.log("  - Arrow functions: (x) => x * 2");
console.log("  - Typed: parameter types, return types, function types");
console.log("  - Closures: capture enclosing scope");
console.log("  - Generics: function<T>(arg: T): T");
console.log("  - Array methods: map, filter, reduce, find, some, every");
console.log("  - Composition: manual compose/pipe");
console.log("  - Currying: manual or with utility");
console.log("  - Optional/default/rest parameters");
console.log("  - Overloaded signatures");
