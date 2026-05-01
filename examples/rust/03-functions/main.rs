/// Functions in Rust
/// =================
/// Rust has first-class functions, closures with ownership semantics,
/// iterators as the primary functional abstraction, and trait-based
/// polymorphism for function-like objects.

fn main() {
    // ── Basic functions ──────────────────────────────

    fn greet(name: &str) -> String {
        format!("Hello, {}!", name)
    }

    println!("{}", greet("Atlas"));

    // ── Multiple return via tuples ───────────────────

    println!("\n--- Multiple return values ---");

    fn min_max(nums: &[i32]) -> (i32, i32) {
        let mut min = nums[0];
        let mut max = nums[0];
        for &n in &nums[1..] {
            if n < min { min = n; }
            if n > max { max = n; }
        }
        (min, max)
    }

    let (min, max) = min_max(&[3, 1, 4, 1, 5, 9, 2, 6]);
    println!("min = {min}, max = {max}");

    // ── Result for error handling ────────────────────

    println!("\n--- Result (error handling) ---");

    fn divide(a: f64, b: f64) -> Result<f64, String> {
        if b == 0.0 {
            Err("division by zero".to_string())
        } else {
            Ok(a / b)
        }
    }

    match divide(10.0, 3.0) {
        Ok(v) => println!("10 / 3 = {v:.4}"),
        Err(e) => println!("Error: {e}"),
    }

    match divide(10.0, 0.0) {
        Ok(v) => println!("10 / 0 = {v}"),
        Err(e) => println!("Error: {e}"),
    }

    // ── First-class functions ────────────────────────

    println!("\n--- First-class functions ---");

    fn add(a: i32, b: i32) -> i32 { a + b }
    fn mul(a: i32, b: i32) -> i32 { a * b }

    // Function pointers
    let op: fn(i32, i32) -> i32 = add;
    println!("op(3, 4) = {}", op(3, 4));

    let ops: Vec<(&str, fn(i32, i32) -> i32)> = vec![
        ("add", add),
        ("mul", mul),
    ];

    for (name, f) in &ops {
        println!("{name}(3, 4) = {}", f(3, 4));
    }

    // ── Closures ─────────────────────────────────────

    println!("\n--- Closures ---");

    // Closures capture their environment
    let factor = 3;
    let triple = |x: i32| x * factor;  // captures `factor` by reference
    println!("triple(5) = {}", triple(5));

    // Closure that moves ownership
    let name = String::from("Atlas");
    let greet_owned = move || println!("Hello, {name}!"); // name moved into closure
    greet_owned();
    // println!("{name}");  // ERROR: name was moved

    // Mutable closure
    let mut count = 0;
    let mut counter = || {
        count += 1;
        count
    };
    println!("counter() = {}", counter());
    println!("counter() = {}", counter());
    println!("counter() = {}", counter());

    // ── Higher-order functions (iterators) ───────────

    println!("\n--- Iterators (higher-order functions) ---");

    let numbers = vec![1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

    // map
    let squares: Vec<i32> = numbers.iter().map(|x| x * x).collect();
    println!("squares = {squares:?}");

    // filter
    let evens: Vec<&i32> = numbers.iter().filter(|x| *x % 2 == 0).collect();
    println!("evens = {evens:?}");

    // fold (reduce)
    let total: i32 = numbers.iter().fold(0, |acc, x| acc + x);
    println!("sum = {total}");

    let product: i32 = numbers.iter().fold(1, |acc, x| acc * x);
    println!("product = {product}");

    // Chaining — idiomatic Rust style
    let result: i32 = (1..=10)
        .filter(|x| x % 2 == 0)
        .map(|x| x * x)
        .sum();
    println!("sum of squares of evens 1..10 = {result}");

    // find, any, all
    let has_five = numbers.iter().any(|&x| x == 5);
    let all_positive = numbers.iter().all(|&x| x > 0);
    println!("has 5? {has_five}, all positive? {all_positive}");

    // ── Returning closures ───────────────────────────

    println!("\n--- Returning closures ---");

    fn make_multiplier(factor: i32) -> impl Fn(i32) -> i32 {
        move |x| x * factor
    }

    let double = make_multiplier(2);
    let triple_fn = make_multiplier(3);
    println!("double(5) = {}", double(5));
    println!("triple(5) = {}", triple_fn(5));

    // ── Generic functions ────────────────────────────

    println!("\n--- Generic functions ---");

    fn apply_twice<F: Fn(i32) -> i32>(f: F, x: i32) -> i32 {
        f(f(x))
    }

    println!("apply_twice(|x| x+1, 5) = {}", apply_twice(|x| x + 1, 5));
    println!("apply_twice(|x| x*2, 3) = {}", apply_twice(|x| x * 2, 3));

    // Generic over type
    fn first<T: Clone>(items: &[T]) -> Option<T> {
        items.first().cloned()
    }

    println!("first([1,2,3]) = {:?}", first(&[1, 2, 3]));
    println!("first([\"a\",\"b\"]) = {:?}", first(&["a", "b"]));
    let empty: &[i32] = &[];
    println!("first([]) = {:?}", first(empty));

    // ── Function composition ─────────────────────────

    println!("\n--- Composition ---");

    fn compose<A, B, C>(f: impl Fn(B) -> C, g: impl Fn(A) -> B) -> impl Fn(A) -> C {
        move |x| f(g(x))
    }

    let add_one = |x: i32| x + 1;
    let double_fn = |x: i32| x * 2;
    let double_then_add = compose(add_one, double_fn);
    println!("double_then_add(5) = {}", double_then_add(5)); // (5*2)+1 = 11

    // Method chaining as practical composition
    let processed: String = "  hello world  "
        .trim()
        .split_whitespace()
        .map(|w| {
            let mut c = w.chars();
            match c.next() {
                None => String::new(),
                Some(f) => f.to_uppercase().to_string() + c.as_str(),
            }
        })
        .collect::<Vec<_>>()
        .join(" ");
    println!("processed = \"{processed}\"");

    // ── Three closure traits ─────────────────────────

    println!("\n--- Closure traits: Fn, FnMut, FnOnce ---");
    println!("  Fn     — borrows captured variables (can call many times)");
    println!("  FnMut  — mutably borrows (can call many times)");
    println!("  FnOnce — consumes captured variables (can call only once)");

    // Fn: borrows
    let data = vec![1, 2, 3];
    let print_data = || println!("  data = {data:?}");
    print_data(); // can call multiple times
    print_data();

    // FnOnce: consumes
    let name2 = String::from("Atlas");
    let consume = move || {
        println!("  Consuming: {name2}");
        drop(name2); // name2 is consumed
    };
    consume();
    // consume();  // ERROR: already moved

    // ── Summary ──────────────────────────────────────

    println!("\n--- Summary ---");
    println!("Rust functions:");
    println!("  - First-class: function pointers and closures");
    println!("  - Closures capture by reference, mutable ref, or move");
    println!("  - Three traits: Fn, FnMut, FnOnce");
    println!("  - Iterators: map, filter, fold, chain, collect");
    println!("  - Generics with trait bounds");
    println!("  - impl Fn(...) for returning closures");
    println!("  - Result<T, E> for error handling");
    println!("  - Method chaining as practical composition");
}
