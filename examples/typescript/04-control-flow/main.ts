/**
 * Control Flow in TypeScript
 * ==========================
 * TypeScript has the same control flow as JavaScript,
 * plus type narrowing in conditional blocks.
 */

// ── if / else ────────────────────────────────────────

function classify(n: number): string {
    if (n < 0) return "negative";
    if (n === 0) return "zero";
    return "positive";
}

console.log("--- if/else ---");
for (const n of [-3, 0, 7]) {
    console.log(`${n} → ${classify(n)}`);
}

// ── switch ───────────────────────────────────────────

console.log("\n--- switch ---");
function grade(score: number): string {
    const tier = Math.floor(score / 10);
    switch (tier) {
        case 10:
        case 9:  return "A";
        case 8:  return "B";
        case 7:  return "C";
        case 6:  return "D";
        default: return "F";
    }
}

console.log(`grade(95) = ${grade(95)}`);
console.log(`grade(82) = ${grade(82)}`);
console.log(`grade(45) = ${grade(45)}`);

// ── for loop ─────────────────────────────────────────

console.log("\n--- for loop ---");
let total = 0;
for (let i = 1; i <= 5; i++) {
    total += i;
}
console.log(`sum 1..5 = ${total}`);

// ── while loop ───────────────────────────────────────

console.log("\n--- while loop ---");
let x = 1;
while (x < 20) {
    x *= 2;
}
console.log(`first power of two >= 20: ${x}`);

// ── do-while loop ────────────────────────────────────

console.log("\n--- do-while ---");
let j = 0;
do {
    console.log(`j = ${j}`);
    j++;
} while (j < 3);

// ── for-of (iterables) ───────────────────────────────

console.log("\n--- for-of ---");
const colors = ["red", "green", "blue"];
for (const color of colors) {
    console.log(`color: ${color}`);
}

// ── break / continue ─────────────────────────────────

console.log("\n--- break / continue ---");
for (let i = 1; i < 10; i++) {
    if (i % 2 === 1) continue;
    if (i > 6) break;
    console.log(`even <= 6: ${i}`);
}

// ── Type guards (TypeScript-specific) ────────────────

console.log("\n--- type guards ---");

function describe(value: string | number | string[]): string {
    if (typeof value === "string") {
        return `string(${value.length})`;
    } else if (typeof value === "number") {
        return `number(${value})`;
    } else if (Array.isArray(value)) {
        return `array[${value.length}]`;
    }
    return "unknown";
}

console.log(describe("hello"));
console.log(describe(42));
console.log(describe(["a", "b", "c"]));

// ── Summary ──────────────────────────────────────────

console.log("\n--- Summary ---");
console.log("TypeScript control flow:");
console.log("  - if/else for conditionals");
console.log("  - switch for multi-way branching");
console.log("  - for, while, do-while for loops");
console.log("  - break / continue for loop control");
console.log("  - for-of for iterating iterables");
console.log("  - Type guards narrow types in blocks");
