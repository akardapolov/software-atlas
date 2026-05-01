/*
Data Structures in TypeScript
========================
Demonstrates arrays, tuples, objects, Maps, Sets, and common operations.
TypeScript adds static typing to JavaScript.
*/

// ── Arrays (typed dynamic arrays) ─────────────────

function demonstrateArrays(): void {
  console.log("--- Arrays ---");

  // Typed arrays
  const numbers: number[] = [10, 20, 30, 40, 50];
  console.log("numbers =", numbers);

  // Indexing
  console.log("numbers[0] =", numbers[0]);      // 10
  console.log("numbers[4] =", numbers[4]);      // 50

  // Tuple arrays (heterogeneous)
  const mixed: (string | number)[] = ["hello", 42, "world", 99];
  console.log("mixed =", mixed);

  // Array methods
  numbers.push(60);
  console.log("After push:", numbers);

  numbers.pop();
  console.log("After pop:", numbers);

  // Spread operator
  const moreNumbers = [...numbers, 70, 80];
  console.log("spread:", moreNumbers);

  // Array.from (from iterable)
  const range = Array.from({ length: 5 }, (_, i) => i + 1);
  console.log("range 1-5:", range);

  // map, filter, reduce
  const squares = numbers.map(n => n * n);
  console.log("squares:", squares);

  const evens = numbers.filter(n => n % 2 === 0);
  console.log("evens:", evens);

  const sum = numbers.reduce((acc, n) => acc + n, 0);
  console.log("sum:", sum);
}

// ── Tuples (fixed-size, typed) ─────────────────────

function demonstrateTuples(): void {
  console.log("\n--- Tuples ---");

  // Type annotation for tuple
  const point: [number, number] = [10, 20];
  console.log("point =", point);

  // Destructuring
  const [x, y] = point;
  console.log(`x = ${x}, y = ${y}`);

  // Tuple with more elements
  const rgb: [number, number, number] = [255, 0, 128];
  console.log("RGB =", rgb);

  // Named tuples (objects as tuples)
  type Point3D = [number, number, number];
  const point3d: Point3D = [10, 20, 30];
  console.log("3D point =", point3d);

  const [, , z] = point3d;  // Ignore first two, get third
  console.log("z =", z);
}

// ── Objects (key-value mappings) ─────────────────────

function demonstrateObjects(): void {
  console.log("\n--- Objects ---");

  // Creation
  const person = {
    name: "Ada",
    age: 36,
    city: "London"
  };
  console.log("person =", person);

  // Access
  console.log("name =", person.name);
  console.log("age =", person["age"]);  // Bracket notation

  // Optional chaining (non-null assertion)
  console.log("email =", person.email ?? "N/A");

  // Modification
  person.email = "ada@example.com";
  console.log("After adding email:", person);

  delete person.city;
  console.log("After deleting city:", person);

  // Object spread (cloning)
  const person2 = { ...person, country: "UK" };
  console.log("cloned person =", person2);

  // Object keys/values
  console.log("Keys:", Object.keys(person));
  console.log("Values:", Object.values(person));
  console.log("Entries:", Object.entries(person));

  // Object.freeze (immutable object)
  const frozen = Object.freeze({ x: 10, y: 20 });
  // frozen.x = 30;  // Error in strict mode
  console.log("frozen =", frozen);
}

// ── Map (key-value with any key type) ───────────

function demonstrateMap(): void {
  console.log("\n--- Map ---");

  // Creation
  const scores = new Map<string, number>();
  scores.set("Alice", 95);
  scores.set("Bob", 87);
  scores.set("Charlie", 92);

  console.log("scores =", Array.from(scores.entries()));

  // Access
  console.log("Alice's score =", scores.get("Alice"));

  // Check existence
  console.log("has 'Bob'? =", scores.has("Bob"));

  // Delete
  scores.delete("Bob");
  console.log("After delete Bob:", Array.from(scores.entries()));

  // Size
  console.log("size =", scores.size);

  // Iteration
  console.log("Iteration:");
  scores.forEach((score, name) => {
    console.log(`  ${name} -> ${score}`);
  });

  // WeakMap (garbage collectable)
  const weak = new WeakMap<object, string>();
  const key = {};
  weak.set(key, "value");
  console.log("WeakMap has key?", weak.has(key));
}

// ── Set (unique values) ─────────────────────────────

function demonstrateSet(): void {
  console.log("\n--- Set ---");

  // Creation (duplicates removed)
  const tags = new Set<string>(["python", "typing", "python", "data"]);
  console.log("tags =", Array.from(tags));
  console.log("size =", tags.size);  // 3

  // Methods
  console.log("has 'typing'? =", tags.has("typing"));

  tags.add("algorithms");
  console.log("After add 'algorithms':", Array.from(tags));

  tags.delete("data");
  console.log("After delete 'data':", Array.from(tags));

  // Set operations
  const setA = new Set<number>([1, 2, 3, 4, 5]);
  const setB = new Set<number>([4, 5, 6, 7, 8]);

  const union = new Set([...setA, ...setB]);
  console.log("union =", Array.from(union));

  const intersection = new Set([...setA].filter(x => setB.has(x)));
  console.log("intersection =", Array.from(intersection));

  const difference = new Set([...setA].filter(x => !setB.has(x)));
  console.log("difference A-B =", Array.from(difference));
}

// ── Interfaces (type contracts) ─────────────────────

interface Point {
  x: number;
  y: number;
}

interface Person {
  name: string;
  age: number;
}

function demonstrateInterfaces(): void {
  console.log("\n--- Interfaces ---");

  const point: Point = { x: 10, y: 20 };
  console.log("Point:", point);

  const ada: Person = { name: "Ada", age: 36 };
  console.log("Person:", ada);

  // Type guards
  function isPoint(value: unknown): value is Point {
    return typeof value === "object" && "x" in value && "y" in value;
  }

  const value = { x: 10, y: 20 };
  if (isPoint(value)) {
    console.log("value is a Point:", value);
  }
}

// ── Type Aliases ─────────────────────────────────────

type ID = string | number;
type Coordinates = [number, number];

function demonstrateTypeAliases(): void {
  console.log("\n--- Type Aliases ---");

  const userId: ID = 123;
  console.log("userId =", userId);  // string | number

  const coords: Coordinates = [10, 20];
  console.log("coords =", coords);
}

// ── Union Types ─────────────────────────────────────

function demonstrateUnionTypes(): void {
  console.log("\n--- Union Types ---");

  type Result = number | string;

  function process(value: Result): void {
    if (typeof value === "number") {
      console.log("Number result:", value * 2);
    } else {
      console.log("String result:", value.toUpperCase());
    }
  }

  process(42);
  process("hello");
}

// ── Optional chaining ─────────────────────────────────

function demonstrateOptional(): void {
  console.log("\n--- Optional chaining ---");

  interface User {
    name: string;
    email?: string;  // Optional
    phone?: string;  // Optional
  }

  const user: User = {
    name: "Ada"
  };

  console.log("name:", user.name);
  console.log("email (with default):", user.email ?? "N/A");
  console.log("phone (with default):", user.phone ?? "N/A");
}

// ── Readonly (immutable) ─────────────────────────────

function demonstrateReadonly(): void {
  console.log("\n--- Readonly ---");

  const mutable = [1, 2, 3];
  const readonly: ReadonlyArray<number> = [1, 2, 3] as readonly;

  console.log("mutable:", mutable);
  // mutable.push(4);  // OK

  console.log("readonly:", readonly);
  // readonly.push(4);  // Error!

  // Readonly object
  const config = { port: 8080, host: "localhost" } as const;
  // config.port = 9090;  // Error!
}

// ── Summary ───────────────────────────────────────────

function main(): void {
  console.log("Data Structures in TypeScript");
  console.log("==============================");

  demonstrateArrays();
  demonstrateTuples();
  demonstrateObjects();
  demonstrateMap();
  demonstrateSet();
  demonstrateInterfaces();
  demonstrateTypeAliases();
  demonstrateUnionTypes();
  demonstrateOptional();
  demonstrateReadonly();

  console.log("\n--- Summary ---");
  console.log("TypeScript data structures:");
  console.log("  - Arrays: typed, with spread operator");
  console.log("  - Tuples: fixed-size, typed");
  console.log("  - Objects: key-value with optional chaining");
  console.log("  - Map: any key type, ordered insertion");
  console.log("  - Set: unique values, with operations");
  console.log("  - Interfaces: type contracts and duck typing");
  console.log("  - Type aliases: named types");
  console.log("  - Union types: multiple possible types");
  console.log("  - Optional: nullable with ?");
  console.log("  - Readonly: immutable with const/as readonly");
}
