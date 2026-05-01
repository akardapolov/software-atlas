// FP Features in Rust
// ===================
// First-class functions, closures, iterators, Option/Result

fn main() {

    // ── First-class functions ─────────────────────────

    println!("--- First-class functions ---");

    let add = |a: i32, b: i32| a + b;
    let multiply = |a: i32, b: i32| a * b;

    // Function as variable
    let op: fn(i32, i32) -> i32 = add;
    println!("op(5, 3) = {}", op(5, 3));

    // Function as parameter
    let result = apply_op(multiply, add, 5, 3);
    println!("multiply(add(5, 3)) = {}", result);


    // ── Closures ─────────────────────────────────────

    println!("\n--- Closures ---");

    let multiplier = 3;

    // Move closure (captures by value)
    let times_three = move |x: i32| x * multiplier;
    println!("times_three(5) = {}", times_three(5));

    // Borrow closure (captures by reference)
    let mut value = 0;
    let add_to_value = |x: i32| value += x;
    add_to_value(10);
    println!("value after add: {}", value);


    // ── Iterators (lazy evaluation) ───────────────────

    println!("\n--- Iterators (lazy) ---");

    let numbers: Vec<i32> = (1..=11).collect();

    // map
    let squares: Vec<i32> = numbers.iter().map(|x| x * x).collect();
    println!("Squares: {:?}", squares);

    // filter
    let evens: Vec<i32> = numbers.iter().filter(|x| x % 2 == 0).collect();
    println!("Evens: {:?}", evens);

    // fold (reduce)
    let sum: i32 = numbers.iter().fold(0, |acc, x| acc + x);
    println!("Sum: {}", sum);

    // take (lazy evaluation)
    let first_five: Vec<i32> = numbers.iter().take(5).collect();
    println!("First 5: {:?}", first_five);


    // ── Higher-order functions ───────────────────────────

    println!("\n--- Higher-order functions ---");

    fn apply_twice<F>(f: F, x: F::Output) -> F::Output {
        f(f(x))
    }

    let result = apply_twice(|x: i32| x + 1, 5);
    println!("apply_twice(x+1, 5) = {}", result);


    // ── Option (null safety) ───────────────────────────

    println!("\n--- Option ---");

    fn find_or_none(vec: &Vec<i32>, predicate: impl Fn(&i32) -> bool) -> Option<i32> {
        vec.iter().find(|&x| predicate(x))
    }

    let is_even = |&x: &i32| x % 2 == 0;
    println!("Found even: {:?}", find_or_none(&numbers, is_even));
    println!("Found odd: {:?}", find_or_none(&numbers, |&x| x % 2 != 0));


    // ── Result (error handling) ─────────────────────────

    println!("\n--- Result ---");

    fn parse_or_error(s: &str) -> Result<i32, &'static str> {
        s.trim().parse::<i32>().map_err(|_| "invalid number")
    }

    match parse_or_error("42") {
        Ok(n) => println!("Parsed: {}", n),
        Err(e) => println!("Error: {}", e),
    }


    // ── Chaining iterators ─────────────────────────────

    println!("\n--- Chaining iterators ---");

    let result: i32 = numbers
        .iter()
        .filter(|x| x % 2 == 0)  // keep evens
        .map(|x| x * x)               // square
        .take(3)                     // first 3
        .sum();

    println!("Sum of squares of first 3 evens: {}", result);


    // ── Collect into different types ─────────────────────

    println!("\n--- Collect ---");

    // Into Vec
    let nums_vec: Vec<i32> = (1..=6).map(|x| x).collect();

    // Into HashSet (unique)
    use std::collections::HashSet;
    let nums_set: HashSet<i32> = (1..=6).map(|x| x).collect();

    println!("Vec: {:?}", nums_vec);
    println!("Set: {:?}", nums_set);


    // ── Summary ─────────────────────────────────────

    println!("\n--- Summary ---");
    println!("Rust FP features:");
    println!("  - First-class functions: assignable, passable, returnable");
    println!("  - Closures: move, ref, or copy capture");
    println!("  - Iterators: lazy evaluation with .map(), .filter(), .fold()");
    println!("  - Option: null-safe value wrapper");
    println!("  - Result: error handling without exceptions");
    println!("  - Pattern matching: match expressions");
    println!("  - Immutable by default: mut required for mutation");
    println!("  - No GC: memory safety through ownership");
    println!("  - Fn traits: Fn, FnMut, FnOnce for capture semantics");
    println!("  - Iter methods: .map(), .filter(), .collect()");
}
