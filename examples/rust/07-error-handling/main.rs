// Error Handling in Rust
// ===================
// Result<T, E>, Option, ?, panic!, assert!

use std::fs::File;
use std::io::{self, Read, Write};
use std::num::ParseIntError;

// ── Custom error type ─────────────────────────────

#[derive(Debug)]
enum AppError {
    IoError(String),
    ParseError(String),
}

impl std::fmt::Display for AppError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            AppError::IoError(msg) => write!(f, "IO Error: {}", msg),
            AppError::ParseError(msg) => write!(f, "Parse Error: {}", msg),
        }
    }
}

impl std::error::Error for AppError {}

// ── Functions returning Result ───────────────────────

fn read_file(path: &str) -> Result<String, AppError> {
    let content = fs::read_to_string(path)
        .map_err(|e| AppError::IoError(e.to_string()))?;
    Ok(content)
}

fn parse_number(s: &str) -> Result<i32, ParseIntError> {
    s.parse::<i32>()
        .map_err(|_| AppError::ParseError("Invalid number".to_string()))
}

fn safe_divide(a: i32, b: i32) -> Result<i32, AppError> {
    if b == 0 {
        Err(AppError::ParseError("Division by zero".to_string()))
    } else {
        Ok(a / b)
    }
}

// ── Option examples (nullable values) ─────────────

fn find_by_id(items: &[i32], id: i32) -> Option<i32> {
    items.iter().find(|&&item| *item == id).copied()
}

fn get_first(items: &[i32]) -> Option<&i32> {
    items.first()
}

// ── ? operator (propagate error) ───────────────

fn process_file(path: &str) -> Result<String, AppError> {
    let content = read_file(path)?;
    Ok(content.trim().to_string())
}

// ── unwrap() vs unwrap_or() ─────────────────────────

fn demo_unwrap() {
    let result = safe_divide(10, 2).unwrap(); // Will panic on error!

    println!("Result: {}", result);
}

fn demo_unwrap_or() {
    let result = safe_divide(10, 0).unwrap_or(999);

    println!("Result (with default): {}", result);
}

// ── expect() ─────────────────────────────────────

fn demo_expect() {
    let result = safe_divide(10, 2);
    let value = result.expect("Division should succeed");

    println!("Expected result: {}", value);
}

// ── map()/and() for Result transformation ───────

fn add_one(value: Result<i32, AppError>) -> Result<i32, AppError> {
    value.map(|v| v + 1)
}

fn demo_map_and() {
    let values = vec
![Ok(1),
        Ok(2),
        Ok(3),
    ];

    let result: Result<Vec<i32>, _> = values.into_iter().collect();

    match result {
        Ok(nums) => {
            let sum: i32 = nums.iter().sum();
            println!("Sum: {}", sum);
        }
        Err(e) => {
            println!("Error: {}", e);
        }
    }
}

// ── assert! macros ───────────────────────────────────

fn demo_assert() {
    assert!(1 == 1, "One should equal one");
    assert_eq!(2 + 2, 4);
}

// ── panic! (unrecoverable) ─────────────────────────────

fn demo_panic() {
    panic!("This is a panic!");
}

// ── Main ─────────────────────────────────────────────

fn main() {

    // ── Basic Result usage
    println!("--- Result<T, E> ---");

    match safe_divide(10, 2) {
        Ok(result) => println!("10 / 2 = {}", result),
        Err(e) => println!("Error: {}", e),
    }


    // ── Option usage
    println!("\n--- Option<T> ---");

    let numbers = vec
![1, 2, 3, 4, 5];

    println!("Found 3: {:?}", find_by_id(&numbers, 3));
    println!("First: {:?}", get_first(&numbers));


    // ── ? operator (early return on error)
    println!("\n--- ? operator ---");

    let content = process_file("test.txt");
    match content {
        Ok(text) => println!("File content: {}", text),
        Err(e) => println!("Error: {}", e),
    }


    // ── unwrap() and unwrap_or()
    println!("\n--- unwrap() / unwrap_or() ---");

    demo_unwrap_or();


    // ── expect()
    println!("\n--- expect() ---");

    demo_expect();


    // ── map() and and()
    println!("\n--- map() and and() ---");

    let result: Result<i32, _> = add_one(Ok(10));
    println!("Added one: {:?}", result);


    // ── assert! macros
    println!("\n--- assert! macros ---");

    demo_assert();


    // ── Summary
    println!("\n--- Summary ---");
    println!("Rust error handling:");
    println!("  - Result<T, E>: Ok(value) or Err(error)");
    println!("  - Option<T>: Some(value) or None");
    println!("  - ? operator: propagate errors early");
    println!("  - unwrap(): get value or panic");
    println!("  - unwrap_or(): get value or default");
    println!("  - expect(): get value or panic with message");
    println!("  - assert!(): panic if condition false");
    println!("  - assert_eq!(): panic if values not equal");
    println!("  - panic!(): unrecoverable failure");
    println!("  - Custom errors: #[derive(Debug)] enum");
    println!("  - No exceptions: explicit error values only");
}
