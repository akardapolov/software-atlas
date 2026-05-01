/// Variables and Types in Rust
/// ===========================
/// Rust uses static, strong typing with ownership and no null.
/// Variables are immutable by default.

fn main() {
    // ── Basic types ──────────────────────────────────────

    // Immutable by default
    let count: i32 = 42;
    println!("count = {count}, type = i32");

    // Type inference — compiler figures out type
    let pi = 3.14159; // inferred as f64
    println!("pi = {pi}, type = f64 (inferred)");

    let active = true; // inferred as bool
    println!("active = {active}");

    let name = "Software Atlas"; // inferred as &str (string slice)
    println!("name = {name}");

    // ── Immutability by default ──────────────────────────

    println!("\n--- Immutability ---");

    // count = 43;  // COMPILE ERROR: cannot assign twice to immutable variable

    // Use `mut` to allow mutation
    let mut score = 0;
    println!("score = {score}");
    score += 10;
    println!("score = {score} (after += 10)");

    // Shadowing — re-declare with `let` (even change type!)
    let x = 42;
    println!("\nx = {x} (i32)");
    let x = "hello"; // shadows previous x — different type!
    println!("x = {x} (&str) — shadowed, different type");

    // ── Integer types ────────────────────────────────────

    println!("\n--- Integer types ---");

    let small: i8 = 127;   // -128 to 127
    let medium: i32 = 2_000_000_000;
    let large: i64 = 9_000_000_000_000_000_000;
    let unsigned: u8 = 255; // 0 to 255
    let arch: isize = 42;  // pointer-sized integer

    println!("i8:    {small}");
    println!("i32:   {medium}");
    println!("i64:   {large}");
    println!("u8:    {unsigned}");
    println!("isize: {arch}");

    // Overflow is a compile error in debug mode, wraps in release
    // let overflow: u8 = 256;  // COMPILE ERROR

    // ── No implicit conversions ──────────────────────────

    println!("\n--- No implicit conversions ---");

    let a: i32 = 10;
    let b: i64 = 20;

    // let c = a + b;  // COMPILE ERROR: mismatched types

    let c = i64::from(a) + b; // explicit conversion
    println!("i64::from({a}) + {b} = {c}");

    // Or use `as` (but be careful — it can truncate!)
    let d = a as i64 + b;
    println!("{a} as i64 + {b} = {d}");

    // ── Strings ──────────────────────────────────────────

    println!("\n--- Strings ---");

    // Two string types:
    // &str — string slice (borrowed, immutable, stack or static)
    // String — owned, heap-allocated, growable

    let greeting: &str = "Hello, 世界"; // string slice (static)
    println!("&str: {greeting}");
    println!("len (bytes) = {}", greeting.len());
    println!("chars count = {}", greeting.chars().count());

    // Iterate over chars (Unicode scalar values)
    for (i, ch) in greeting.chars().enumerate() {
        println!("  [{i}] {ch} (U+{:04X})", ch as u32);
    }

    let mut owned = String::from("Hello");
    owned.push_str(", World!"); // String is growable
    println!("\nString: {owned}");

    // ── Tuples ───────────────────────────────────────────

    println!("\n--- Tuples ---");

    let point: (f64, f64) = (3.0, 4.0);
    println!("point = {:?}", point);
    println!("x = {}, y = {}", point.0, point.1);

    // Destructuring
    let (x, y) = point;
    let distance = (x * x + y * y).sqrt();
    println!("distance from origin = {distance:.2}");

    // ── Arrays and vectors ───────────────────────────────

    println!("\n--- Arrays and vectors ---");

    // Array — fixed size, stack-allocated
    let arr: [i32; 3] = [10, 20, 30];
    println!("array = {:?}, len = {}", arr, arr.len());

    // Vec — dynamic, heap-allocated (like Go slices, Python lists)
    let mut fruits = vec!["apple", "banana", "cherry"];
    fruits.push("date");
    println!("vec = {:?}, len = {}", fruits, fruits.len());

    // ── Option<T> — no null! ─────────────────────────────

    println!("\n--- Option<T> (no null) ---");

    // Rust has no null. Instead, Option<T> represents "maybe a value"
    let some_number: Option<i32> = Some(42);
    let no_number: Option<i32> = None;

    println!("some_number = {:?}", some_number);
    println!("no_number = {:?}", no_number);

    // You MUST handle both cases — compiler enforces it
    match some_number {
        Some(n) => println!("Got: {n}"),
        None => println!("Got nothing"),
    }

    // if let — for when you only care about one case
    if let Some(n) = some_number {
        println!("if let: {n}");
    }

    // unwrap_or — provide a default
    let value = no_number.unwrap_or(0);
    println!("no_number.unwrap_or(0) = {value}");

    // ── Enums (algebraic data types) ─────────────────────

    println!("\n--- Enums (algebraic data types) ---");

    // Enums can carry data — this is a sum type
    #[derive(Debug)]
    enum Shape {
        Circle(f64),           // radius
        Rectangle(f64, f64),   // width, height
    }

    fn area(shape: &Shape) -> f64 {
        match shape {
            Shape::Circle(r) => std::f64::consts::PI * r * r,
            Shape::Rectangle(w, h) => w * h,
            // No default needed — match is exhaustive!
            // If you add a new variant, the compiler forces you
            // to handle it everywhere.
        }
    }

    let circle = Shape::Circle(5.0);
    let rect = Shape::Rectangle(3.0, 4.0);
    println!("{:?} → area = {:.2}", circle, area(&circle));
    println!("{:?} → area = {:.2}", rect, area(&rect));

    // ── Result<T, E> — error handling ────────────────────

    println!("\n--- Result<T, E> ---");

    // Result is like Option but carries error information
    let parsed: Result<i32, _> = "42".parse();
    let failed: Result<i32, _> = "abc".parse();

    println!("\"42\".parse() = {:?}", parsed);
    println!("\"abc\".parse() = {:?}", failed);

    match parsed {
        Ok(n) => println!("Parsed: {n}"),
        Err(e) => println!("Error: {e}"),
    }

    // ── Ownership preview ────────────────────────────────

    println!("\n--- Ownership (preview) ---");

    let s1 = String::from("hello");
    let s2 = s1; // s1 is MOVED to s2 — s1 is no longer valid!

    // println!("{s1}");  // COMPILE ERROR: value used after move

    println!("s2 = {s2}");

    // Clone to make a copy
    let s3 = s2.clone();
    println!("s2 = {s2}, s3 = {s3} (cloned)");

    // References (borrowing) — use without taking ownership
    let s4 = String::from("world");
    let len = calculate_length(&s4); // borrow s4
    println!("'{s4}' has length {len}"); // s4 still valid!

    // ── Summary ──────────────────────────────────────────

    println!("\n--- Summary ---");
    println!("Rust: static + strong typing");
    println!("  - Types checked at compile time (static)");
    println!("  - No implicit conversions (strong)");
    println!("  - Type inference (let x = 42)");
    println!("  - Immutable by default (use mut for mutation)");
    println!("  - No null — Option<T> instead");
    println!("  - Algebraic data types (enums with data)");
    println!("  - Ownership system for memory safety");
    println!("  - Pattern matching with exhaustiveness checking");
}

fn calculate_length(s: &str) -> usize {
    s.len()
}
