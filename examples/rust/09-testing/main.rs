// Testing in Rust
// =============
// #![test] attribute, assert macros, doc tests

#[test]
fn test_addition() {
    let result = 5 + 3;
    assert_eq!(result, 8);
}

#[test]
fn test_string_length() {
    let s = "hello";
    assert_eq!(s.len(), 5);
}

// ── Various assertion macros ──────────────────────────

#[test]
fn test_assertions() {
    // assert_eq! and assert_ne!
    assert_eq!(1 + 1, 2);
    assert_ne!(1 + 1, 3);

    // assert! is always true
    assert!(true);

    // panic test
}

#[test]
#[should_panic]
fn test_panics() {
    let _v = vec![1, 2, 3][10]; // Out of bounds
}

// ── Struct and enum testing ────────────────────────

#[derive(Debug, PartialEq)]
struct Point {
    x: i32,
    y: i32,
}

#[test]
fn test_struct_equality() {
    let p1 = Point { x: 1, y: 2 };
    let p2 = Point { x: 1, y: 2 };
    assert_eq!(p1, p2);
}

// ── Vec and collection testing ───────────────────────

#[test]
fn test_vec_operations() {
    let mut v = vec![1, 2, 3];
    v.push(4);
    assert_eq!(v, vec![1, 2, 3, 4]);

    let sum: i32 = v.iter().sum();
    assert_eq!(sum, 10);
}

// ── Error handling testing ──────────────────────────

#[derive(Debug)]
enum MathError {
    DivisionByZero,
}

fn divide(a: i32, b: i32) -> Result<i32, MathError> {
    if b == 0 {
        Err(MathError::DivisionByZero)
    } else {
        Ok(a / b)
    }
}

#[test]
fn test_error_handling() {
    assert!(divide(10, 2).is_ok());
    assert!(divide(10, 0).is_err());

    match divide(10, 2) {
        Ok(result) => assert_eq!(result, 5),
        Err(_) => panic!("unexpected error"),
    }
}

// ── Iterator testing ───────────────────────────────

#[test]
fn test_iterators() {
    let numbers: Vec<i32> = (1..=5).collect();
    assert_eq!(numbers.len(), 5);

    let doubled: Vec<i32> = numbers.iter().map(|x| x * 2).collect();
    assert_eq!(doubled, vec![2, 4, 6, 8, 10]);
}

// ── Summary test ─────────────────────────────────────

#[test]
fn test_summary() {
    // This test demonstrates summary points
    let _ = 42; // Use to avoid unused warning
}

// ── Doc test (in documentation) ──────────────

/// Adds two numbers.
///
/// # Examples
///
/// ```
/// let result = add(2, 3);
/// assert_eq!(result, 5);
/// ```
fn add(a: i32, b: i32) -> i32 {
    a + b
}
