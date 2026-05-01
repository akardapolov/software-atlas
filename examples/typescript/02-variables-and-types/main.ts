/**
 * Variables and Types in TypeScript
 * =================================
 * TypeScript uses static, structural typing.
 * Types are checked at compile time and erased at runtime.
 */

// ── Basic types ──────────────────────────────────────

const count: number = 42;          // all numbers are IEEE 754 doubles
const pi: number =3.14159;
const active: boolean = true;
const name: string = "Software Atlas";
const nothing: null = null;
const missing: undefined = undefined;

console.log(`count = ${count} (${typeof count})`);
console.log(`pi = ${pi} (${typeof pi})`);
console.log(`active = ${active} (${typeof active})`);
console.log(`name = "${name}" (${typeof name})`);
console.log(`nothing = ${nothing} (${typeof nothing})`);

// ── Type inference ───────────────────────────────────

console.log("\n--- Type inference ---");

const x = 42;          // inferred as number
const y = "hello";     // inferred as string
const z = [1, 2, 3];   // inferred as number[]
const w = { a: 1 };    // inferred as { a: number }

// let vs const affects inference
let mutable = 42;      // inferred as number (can be any number)
const literal = 42;    // inferred as literal type 42 (can only be 42!)

console.log(`x = ${x}, y = ${y}`);
console.log(`z = [${z}]`);

// ── Structural typing ────────────────────────────────

console.log("\n--- Structural typing ---");

// TypeScript uses STRUCTURAL typing — compatibility is based on shape,
// not on declared names (unlike Java's nominal typing)

interface Quacker {
    quack(): string;
}

class Duck {
    quack(): string { return "Quack!"; }
}

class Person {
    constructor(public name: string) {}
    quack(): string { return `${this.name} quacks!`; }
}

// Neither Duck nor Person explicitly "implements Quacker"
// But both satisfy its shape → both are compatible!
function makeItQuack(q: Quacker): void {
    console.log(q.quack());
}

makeItQuack(new Duck());           // ✅ has quack(): string
makeItQuack(new Person("Bob"));    // ✅ has quack(): string
makeItQuack({ quack: () => "!" }); // ✅ object literal matches shape

// ── Union types ──────────────────────────────────────

console.log("\n--- Union types ---");

// A value can be one of several types
type StringOrNumber = string | number;

function describe(value: StringOrNumber): string {
    if (typeof value === "string") {
        return `String: "${value}" (length ${value.length})`;
        // TypeScript knows 'value' is a string here (narrowing)
    } else {
        return `Number: ${value} (doubled: ${value * 2})`;
        // TypeScript knows 'value' is a number here
    }
}

console.log(describe("hello"));
console.log(describe(42));

// ── Literal types ────────────────────────────────────

console.log("\n--- Literal types ---");

// Specific values as types
type Direction = "north" | "south" | "east" | "west";

function move(dir: Direction): string {
    return `Moving ${dir}`;
}

console.log(move("north"));
// move("up");  // COMPILE ERROR: "up" is not assignable to Direction

type HttpStatus = 200 | 301 | 404 | 500;

function statusMessage(code: HttpStatus): string {
    switch (code) {
        case 200: return "OK";
        case 301: return "Moved";
        case 404: return "Not Found";
        case 500: return "Server Error";
    }
    // TypeScript knows all cases are handled — no default needed!
}

console.log(`404 → ${statusMessage(404)}`);

// ── Discriminated unions (algebraic data types) ──────

console.log("\n--- Discriminated unions (ADTs) ---");

// TypeScript's version of sum types / algebraic data types
type Shape =
    | { kind: "circle"; radius: number }
    | { kind: "rectangle"; width: number; height: number }
    | { kind: "triangle"; a: number; b: number; c: number };

function area(shape: Shape): number {
    switch (shape.kind) {
        case "circle":
            return Math.PI * shape.radius ** 2;
        case "rectangle":
            return shape.width * shape.height;
        case "triangle": {
            const s = (shape.a + shape.b + shape.c) / 2;
            return Math.sqrt(s * (s - shape.a) * (s - shape.b) * (s - shape.c));
        }
    }
    // Exhaustive — TypeScript verifies all cases are handled
}

const circle: Shape = { kind: "circle", radius: 5 };
const rect: Shape = { kind: "rectangle", width: 3, height: 4 };

console.log(`Circle area = ${area(circle).toFixed(2)}`);
console.log(`Rectangle area = ${area(rect).toFixed(2)}`);

// ── Null safety (strictNullChecks) ───────────────────

console.log("\n--- Null safety ---");

// With strictNullChecks, null/undefined must be explicitly handled
function findUser(id: number): { name: string } | null {
    if (id === 1) return { name: "Ada" };
    return null;
}

const user = findUser(1);
// console.log(user.name);  // COMPILE ERROR: 'user' is possibly 'null'

if (user !== null) {
    console.log(`Found: ${user.name}`);  // ✅ narrowed to non-null
}

// Optional chaining
const maybeName = findUser(999)?.name ?? "(unknown)";
console.log(`Maybe: ${maybeName}`);

// ── Generics ─────────────────────────────────────────

console.log("\n--- Generics ---");

// Functions that work with any type while preserving type info
function first<T>(arr: T[]): T | undefined {
    return arr[0];
}

const firstNum = first([1, 2, 3]);      // inferred as number | undefined
const firstStr = first(["a", "b"]);     // inferred as string | undefined

console.log(`first([1,2,3]) = ${firstNum}`);
console.log(`first(["a","b"]) = ${firstStr}`);

// Generic type
type Result<T, E> =
    | { ok: true; value: T }
    | { ok: false; error: E };

function parseNumber(s: string): Result<number, string> {
    const n = Number(s);
    if (isNaN(n)) return { ok: false, error: `"${s}" is not a number` };
    return { ok: true, value: n };
}

const parsed = parseNumber("42");
if (parsed.ok) {
    console.log(`Parsed: ${parsed.value}`);
} else {
    console.log(`Error: ${parsed.error}`);
}

// ── Readonly and immutability ────────────────────────

console.log("\n--- Readonly ---");

// readonly makes properties immutable
interface Point {
    readonly x: number;
    readonly y: number;
}

const p: Point = { x: 3, y: 4 };
// p.x = 5;  // COMPILE ERROR: cannot assign to 'x' because it is a read-only property

// Readonly arrays
const nums: readonly number[] = [1, 2, 3];
// nums.push(4);  // COMPILE ERROR: Property 'push' does not exist on readonly number[]

// as const — deep readonly
const config = {
    host: "localhost",
    port: 8080,
    features: ["auth", "logging"],
} as const;
// config.port = 3000;  // COMPILE ERROR
// config.features.push("x");  // COMPILE ERROR

console.log(`config = ${JSON.stringify(config)}`);

// ── Type narrowing ───────────────────────────────────

console.log("\n--- Type narrowing ---");

// TypeScript narrows types based on control flow
function process(input: string | number | boolean): string {
    if (typeof input === "string") {
        return input.toUpperCase();  // known to be string
    }
    if (typeof input === "number") {
        return (input * 2).toString();  // known to be number
    }
    return input ? "yes" : "no";  // known to be boolean
}

console.log(`process("hello") = ${process("hello")}`);
console.log(`process(21) = ${process(21)}`);
console.log(`process(true) = ${process(true)}`);

// ── Summary ──────────────────────────────────────────

console.log("\n--- Summary ---");
console.log("TypeScript: static + structural typing");
console.log("  - Types checked at compile time (erased at runtime)");
console.log("  - Structural typing: shape determines compatibility");
console.log("  - Type inference: explicit annotations often optional");
console.log("  - Union types: string | number");
console.log("  - Literal types: 'north' | 'south'");
console.log("  - Discriminated unions ≈ algebraic data types");
console.log("  - Generics for parameterised types");
console.log("  - strictNullChecks for null safety");
console.log("  - readonly for immutability");
