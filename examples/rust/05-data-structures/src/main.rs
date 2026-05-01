/*
Data Structures in Rust
======================
Demonstrates vectors, HashMaps, structs, enums, and common operations.
Rust provides memory-safe collections with ownership system.
*/

use std::collections::{HashMap, HashSet, VecDeque, BinaryHeap};
use std::fmt;

// ── Vectors (dynamic arrays) ─────────────────────

fn demonstrate_vectors() {
    println!("--- Vectors (dynamic arrays) ---");

    // Creation with macro
    let fruits = vec!["apple", "banana", "cherry"];
    println!("fruits = {:?}", fruits);

    // Indexing
    println!("fruits[0] = {}", fruits[0]);   // apple
    println!("fruits[2] = {}", fruits[2]);   // cherry

    // Slicing
    println!("fruits[1..3] = {:?}", &fruits[1..3]); // ["banana", "cherry"]

    // Iteration
    println!("Iterate:");
    for fruit in &fruits {
        println!("  {}", fruit);
    }

    // Mutable vector
    let mut numbers = Vec::new();
    numbers.push(1);
    numbers.push(2);
    numbers.push(3);
    println!("mutable numbers = {:?}", numbers);

    // Vec macro
    let squares: Vec<i32> = (1..=11).map(|x| x * x).collect();
    println!("squares (1-10): {:?}", squares);

    let evens: Vec<i32> = (1..=21).filter(|x| x % 2 == 0).collect();
    println!("evens (1-20): {:?}", evens);
}

// ── HashMap (key-value) ─────────────────────────────

fn demonstrate_hashmap() {
    println!("\n--- HashMap (key-value) ---");

    // Creation
    let mut person = HashMap::new();
    person.insert(String::from("name"), "Ada");
    person.insert(String::from("age"), 36);
    person.insert(String::from("city"), "London");

    println!("person = {:?}", person);

    // Access
    println!("name = {}", person.get("name").unwrap_or(&"unknown"));
    println!("age = {}", person.get("age").unwrap());

    // Contains
    println!("has 'email'? {}", person.contains_key("email"));

    // Remove
    person.remove("city");
    println!("After removing city: {:?}", person);

    // Entry API (get or insert)
    person.entry(String::from("count")).or_insert(0).and_modify(|e| *e += 1);
    println!("After incrementing count: {:?}", person);

    // Iteration
    println!("Keys and values:");
    for (key, value) in &person {
        println!("  {} -> {}", key, value);
    }

    // BTreeMap (ordered)
    let mut ordered_scores = std::collections::BTreeMap::new();
    ordered_scores.insert(String::from("Alice"), 95);
    ordered_scores.insert(String::from("Bob"), 87);
    println!("BTreeMap (ordered): {:?}", ordered_scores);
}

// ── HashSet (unique elements) ─────────────────────

fn demonstrate_hashset() {
    println!("\n--- HashSet (unique elements) ---");

    // Creation (duplicates removed)
    let tags: HashSet<&str> = ["python", "typing", "python", "data"].iter().cloned().collect();
    println!("tags = {:?}", tags);
    println!("size = {}", tags.len());  // 3

    // Contains
    println!("contains 'typing'? {}", tags.contains("typing"));

    // Insert
    let mut tags_mut = tags;
    tags_mut.insert("algorithms");
    println!("After insert: {:?}", tags_mut);

    // Remove
    tags_mut.remove("data");
    println!("After remove 'data': {:?}", tags_mut);

    // Union, intersection, difference
    let set_a: HashSet<i32> = (1..=6).collect();
    let set_b: HashSet<i32> = (4..=9).collect();

    let union: HashSet<_> = set_a.union(&set_b).cloned().collect();
    println!("union: {:?}", union);

    let intersection: HashSet<_> = set_a.intersection(&set_b).cloned().collect();
    println!("intersection: {:?}", intersection);

    let difference: HashSet<_> = set_a.difference(&set_b).cloned().collect();
    println!("difference A-B: {:?}", difference);
}

// ── Structs (custom types) ─────────────────────

struct Point {
    x: i32,
    y: i32,
}

// Debug trait for printing
impl fmt::Debug for Point {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "({}, {})", self.x, self.y)
    }
}

struct Person {
    name: String,
    age: u8,
    city: String,
}

fn demonstrate_structs() {
    println!("\n--- Structs ---");

    // Declaration and initialization
    let p1 = Point { x: 10, y: 20 };
    println!("Point: {:?}", p1);

    // Field names (struct init syntax)
    let p2 = Point { x: 10, y: 20 };
    println!("Point2: {:?}", p2);

    // Mutable struct
    let mut p3 = Point { x: 0, y: 0 };
    p3.x = 30;
    p3.y = 40;
    println!("Mutable Point: {:?}", p3);

    // Tuple struct (unit struct with named fields)
    struct Color(i32, i32, i32);

    let rgb = Color(255, 0, 128);
    println!("Color: {:?}", rgb);

    // Struct with methods
    impl Person {
        fn new(name: &str, age: u8, city: &str) -> Self {
            Person {
                name: String::from(name),
                age,
                city: String::from(city),
            }
        }

        fn describe(&self) {
            println!("Person: {}, {} years old from {}", self.name, self.age, self.city);
        }
    }

    let ada = Person::new("Ada", 36, "London");
    ada.describe();
}

// ── Enums ─────────────────────────────────────────

enum Status {
    Active,
    Inactive,
    Pending { message: String },
}

fn demonstrate_enums() {
    println!("\n--- Enums ---");

    // Basic variants
    let status = Status::Active;
    println!("status = {:?}", status);

    // Enum with data
    let pending = Status::Pending { message: String::from("Waiting for approval") };
    println!("pending = {:?}", pending);

    // Pattern matching
    match status {
        Status::Active => println!("System is active!"),
        Status::Inactive => println!("System is inactive!"),
        Status::Pending { message } => println!("Pending: {}", message),
    }
}

// ── VecDeque (double-ended queue) ─────────────────

fn demonstrate_vecdeque() {
    println!("\n--- VecDeque (double-ended) ---");

    let mut deque = VecDeque::new();

    // Push back
    deque.push_back(1);
    deque.push_back(2);
    println!("After push_back(1, 2): {:?}", deque);

    // Push front
    deque.push_front(0);
    println!("After push_front(0): {:?}", deque);

    // Pop back
    deque.pop_back();
    println!("After pop_back: {:?}", deque);

    // Pop front
    deque.pop_front();
    println!("After pop_front: {:?}", deque);
}

// ── BinaryHeap (priority queue) ─────────────────────

#[derive(Debug)]
struct Task {
    priority: u8,
    name: String,
}

// Ord for BinaryHeap
impl Ord for Task {
    fn cmp(&self, other: &Self) -> std::cmp::Ordering {
        other.priority.cmp(&self.priority)  // Min heap: lower priority = higher
    }
}

impl PartialOrd for Task {
    fn partial_cmp(&self, other: &Self) -> Option<std::cmp::Ordering> {
        Some(self.cmp(other))
    }
}

fn demonstrate_binaryheap() {
    println!("\n--- BinaryHeap (priority queue) ---");

    let mut heap = BinaryHeap::new();

    heap.push(Task { priority: 3, name: String::from("high") });
    heap.push(Task { priority: 1, name: String::from("urgent") });
    heap.push(Task { priority: 2, name: String::from("medium") });

    println!("heap: {:?}", heap);

    // Pop smallest
    let task = heap.pop();
    println!("next task: {:?}", task);

    let task = heap.pop();
    println!("next task: {:?}", task);
}

// ── String (UTF-8 string type) ─────────────────

fn demonstrate_strings() {
    println!("\n--- Strings ---");

    let name = String::from("Software Atlas");
    println!("name = {}", name);

    // Indexing (returns char, not string!)
    println!("name[0] = {}", name.chars().next().unwrap());
    println!("name.len() = {}", name.len());

    // Slicing
    println!("name[0..8] = {}", &name[0..8]);

    // Methods
    println!("uppercase = {}", name.to_uppercase());
    println!("contains 'Software'? {}", name.contains("Software"));
}

// ── Option (nullable values) ───────────────────────

fn demonstrate_option() {
    println!("\n--- Option (nullable) ---");

    fn find_first(vec: &Vec<i32>, target: i32) -> Option<usize> {
        vec.iter().position(|&x| x == target)
    }

    let numbers = vec![10, 20, 30, 40, 50];

    // Some(value) when found
    match find_first(&numbers, 30) {
        Some(index) => println!("30 found at index {}", index),
        None => println!("30 not found"),
    }

    // Option methods
    let value: Option<i32> = Some(42);
    println!("value = {:?}", value);
    println!("unwrap = {}", value.unwrap());
    println!("unwrap_or default = {}", value.unwrap_or(0));
}

// ── Result (error handling) ─────────────────────────

fn demonstrate_result() {
    println!("\n--- Result (error handling) ---");

    fn divide(a: f64, b: f64) -> Result<f64, String> {
        if b == 0.0 {
            Err(String::from("Division by zero"))
        } else {
            Ok(a / b)
        }
    }

    // Successful result
    let result = divide(10.0, 2.0);
    println!("10.0 / 2.0 = {:?}", result);

    match result {
        Ok(value) => println!("Result: {}", value),
        Err(error) => println!("Error: {}", error),
    }

    // Error result
    let error = divide(10.0, 0.0);
    println!("10.0 / 0.0 = {:?}", error);
}

// ── Summary ───────────────────────────────────────────

fn main() {
    println!("Data Structures in Rust");
    println!("======================");

    demonstrate_vectors();
    demonstrate_hashmap();
    demonstrate_hashset();
    demonstrate_structs();
    demonstrate_enums();
    demonstrate_vecdeque();
    demonstrate_binaryheap();
    demonstrate_strings();
    demonstrate_option();
    demonstrate_result();

    println!("\n--- Summary ---");
    println!("Rust data structures:");
    println!("  - Vec: dynamic array with automatic resizing");
    println!("  - HashMap/BTreeMap: hash table / ordered map");
    println!("  - HashSet/BTreeSet: hash set / ordered set");
    println!("  - VecDeque: double-ended queue");
    println!("  - BinaryHeap: priority queue");
    println!("  - Structs: custom data types with named fields");
    println!("  - Enums: algebraic data types with variants");
    println!("  - Option: nullable values (no null)");
    println!("  - Result: error handling (value or error)");
    println!("  - Ownership: memory safety without GC (borrow checker)");
}
