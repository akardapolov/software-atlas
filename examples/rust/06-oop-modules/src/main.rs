/*
 * OOP and Modules in Rust
 * =========================
 * Demonstrates structs, impl blocks, traits, associated types,
 * and modules. Rust uses composition instead of inheritance.
 */

// ── Structs and Methods ────────────────────────────

fn demonstrate_structs() {
    println!("--- Structs and Methods ---");

    // Basic struct
    struct Point {
        x: i32,
        y: i32,
    }

    let p = Point { x: 10, y: 20 };
    println!("Point: x={}, y={}", p.x, p.y);

    // Struct with methods (impl block)
    struct Counter {
        count: u32,
    }

    impl Counter {
        fn new() -> Self {
            Counter { count: 0 }
        }

        fn increment(&mut self) {
            self.count += 1;
        }

        fn get_count(&self) -> u32 {
            self.count
        }

        fn reset(&mut self) {
            self.count = 0;
        }
    }

    let mut counter = Counter::new();
    counter.increment();
    counter.increment();
    println!("Counter count: {}", counter.get_count());
    counter.reset();
    println!("After reset: {}", counter.get_count());

    // Method with self (mutable reference)
    impl Counter {
        fn add(&mut self, value: u32) {
            self.count += value;
        }

        fn count(&self) -> u32 {
            self.count
        }
    }

    let mut counter2 = Counter::new();
    counter2.add(5);
    println!("Counter2 count: {}", counter2.count());

    // Default implementation via Debug trait
    #[derive(Debug)]
    struct PointDebug {
        x: i32,
        y: i32,
    }

    println!("PointDebug with Debug: {:?}", PointDebug { x: 5, y: 10 });
}


// ── Traits (Interfaces) ─────────────────────────────

fn demonstrate_traits() {
    println!("\n--- Traits (Interfaces) ---");

    // Trait definition
    trait Shape {
        fn area(&self) -> f64;
        fn perimeter(&self) -> f64;
    }

    // Struct implementing trait
    struct Rectangle {
        width: f64,
        height: f64,
    }

    impl Shape for Rectangle {
        fn area(&self) -> f64 {
            self.width * self.height
        }

        fn perimeter(&self) -> f64 {
            2.0 * (self.width + self.height)
        }
    }

    struct Circle {
        radius: f64,
    }

    impl Shape for Circle {
        fn area(&self) -> f64 {
            std::f64::consts::PI * self.radius * self.radius
        }

        fn perimeter(&self) -> f64 {
            2.0 * std::f64::consts::PI * self.radius
        }
    }

    // Polymorphic function
    fn print_shape(shape: &dyn Shape) {
        println!("Area: {}", shape.area());
        println!("Perimeter: {}", shape.perimeter());
    }

    let rect = Rectangle { width: 5.0, height: 3.0 };
    let circle = Circle { radius: 2.0 };

    println!("Rectangle:");
    print_shape(&rect);

    println!("\nCircle:");
    print_shape(&circle);
}


// ── Default Implementations ─────────────────────

fn demonstrate_defaults() {
    println!("\n--- Default Implementations ---");

    trait WithDefault {
        fn default(&self) -> String;
    }

    // Default impl for bool
    impl WithDefault for bool {
        fn default(&self) -> String {
            if *self { "false" } else { "true" }
        }
    }

    // Default impl for Option
    impl<T: Clone> WithDefault for Option {
        fn default(&self) -> String {
            match self {
                Some(_) => String::from("Some value"),
                None => String::from("None"),
            }
        }
    }

    println!("bool default: {}", bool::default());
    println!("Option<string> default: {}", Option::<String>::default());
    println!("Option<i32> default: {}", Option::<i32>::default());
}


// ── Associated Types ─────────────────────

fn demonstrate_associated() {
    println!("\n--- Associated Types ---");

    // Trait with associated type
    trait Graph {
        type Node;
        fn create_node(&self, value: Self::Node) -> Self::Node;
    }

    // Struct implementing trait
    struct MyGraph;
    impl MyGraph {
        type Node = i32; // Associated type
    }

    impl Graph for MyGraph {
        fn create_node(&self, value: i32) -> i32 {
            value
        }
    }

    let graph = MyGraph;
    let node1 = graph.create_node(42);
    let node2 = graph.create_node(100);

    println!("Node1: {}", node1);
    println!("Node2: {}", node2);
}


// ── Trait Bounds ─────────────────────────────

fn demonstrate_bounds() {
    println!("\n--- Trait Bounds ---");

    // Trait requiring Display
    trait Summarize {
        fn summarize(&self) -> String;
    }

    // Struct with Display bound
    #[derive(Debug)]
    struct Article {
        title: String,
        words: u32,
    }

    impl std::fmt::Display for Article {
        fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
            write!(f, "{} ({} words)", self.title, self.words, f)
        }
    }

    // Trait with Display bound
    fn print_summary<T: Summarize + std::fmt::Display>(item: &T) {
        println!("Summary: {}", item.summarize());
    }

    let article = Article {
        title: String::from("Rust OOP"),
        words: 150,
    };

    print_summary(&article);
}


// ── Visibility ─────────────────────────────

fn demonstrate_visibility() {
    println!("\n--- Visibility (pub vs private) ---");

    // Public struct
    pub struct PublicStruct {
        public_field: i32,
    }

    impl PublicStruct {
        fn new(public_field: i32) -> Self {
            Self { public_field }
        }

        pub fn get_public_field(&self) -> i32 {
            self.public_field
        }
    }

    // Private struct (default)
    struct PrivateStruct {
        private_field: i32,
    }

    impl PrivateStruct {
        fn new(private_field: i32) -> Self {
            Self { private_field }
        }

        pub fn get_private_field(&self) -> i32 {
            self.private_field
        }
    }

    let pub_item = PublicStruct::new(42);
    let priv_item = PrivateStruct::new(42);

    println!("Public field: {}", pub_item.get_public_field());
    println!("Private field: {}", priv_item.get_private_field());
}


// ── Modules ─────────────────────────────

fn demonstrate_modules() {
    println!("\n--- Modules ---");

    // In this file (current module)
    println!("main() is in current module");

    // Mod structure
    mod shapes {
        pub fn circle_area(radius: f64) -> f64 {
            std::f64::consts::PI * radius * radius
        }
    }

    println!("shapes::circle_area(2) = {}", shapes::circle_area(2.0));
}


// ── Generics ─────────────────────────────

fn demonstrate_generics() {
    println!("\n--- Generics ---");

    // Generic struct
    #[derive(Debug)]
    struct Box {
        value: T,
    }

    // Generic function
    fn create_box(value: T) -> Box {
        Box { value }
    }

    // Generic trait
    trait Container {
        fn get(&self) -> T;
        fn set(&mut self, value: T);
    }

    // Generic impl
    #[derive(Debug)]
    struct VecContainer {
        items: Vec,
    }

    impl Container for VecContainer {
        fn get(&self) -> T {
            self.items.get(0).cloned().unwrap_or(&Default::default())
        }

        fn set(&mut self, value: T) {
            self.items.clear();
            self.items.push(value);
        }
    }

    let mut container = VecContainer { items: Vec::new() };
    container.set(String::from("Hello"));
    println!("Container items: {:?}", container.items);
    println!("First item: {}", container.get());
}


// ── Deref Trait ─────────────────────

fn demonstrate_deref() {
    println!("\n--- Deref Trait ---");

    // Smart pointer struct
    struct MyBox {
        value: i32,
    }

    // Implement Deref for operator overloading (*)
    impl std::ops::Deref for MyBox {
        type Target = i32;

        fn deref(&self) -> Self::Target {
            self.value
        }
    }

    let boxed = MyBox { value: 42 };
    println!("Dereferenced: {}", *boxed);
}


// ── Drop Trait ─────────────────────

fn demonstrate_drop() {
    println!("\n--- Drop Trait (destructor) ---");

    // Custom type with Drop
    struct CustomResource {
        id: u32,
    }

    impl Drop for CustomResource {
        fn drop(&mut self) {
            println!("Dropping CustomResource with id: {}", self.id);
        }
    }

    {
        let _resource = CustomResource { id: 1 };
        println!("Resource goes out of scope here");
    }

    let _resource2 = CustomResource { id: 2 };
    println!("Resource 2 goes out of scope here");
    }

    // Automatic Drop for Vec
    {
        let _data = Vec::from([1, 2, 3]);
        println!("Vec data goes out of scope here");
    }
}


// ── Clone Trait ─────────────────────

fn demonstrate_clone() {
    println!("\n--- Clone Trait ---");

    #[derive(Debug, Clone)]
    struct Point {
        x: i32,
        y: i32,
    }

    let p1 = Point { x: 10, y: 20 };
    let p2 = p1.clone();

    println!("p1: {:?}", p1);
    println!("p2: {:?}", p2);

    // Manual Clone implementation
    #[derive(Debug)]
    struct ManualPoint {
        x: i32,
        y: i32,
    }

    impl Clone for ManualPoint {
        fn clone(&self) -> Self {
            ManualPoint {
                x: self.x,
                y: self.y,
            }
        }
    }

    let mp1 = ManualPoint { x: 5, y: 15 };
    let mp2 = mp1.clone();

    println!("mp1: {:?}", mp1);
    println!("mp2: {:?}", mp2);
}


// ── Operator Overloading ─────────────────────

fn demonstrate_operators() {
    println!("\n--- Operator Overloading (via traits) ---");

    trait Add {
        fn add(&self, other: Self) -> Self;
    }

    #[derive(Debug, Clone)]
    struct Point {
        x: i32,
        y: i32,
    }

    impl Add for Point {
        fn add(&self, other: Point) -> Point {
            Point {
                x: self.x + other.x,
                y: self.y + other.y,
            }
        }
    }

    let p1 = Point { x: 1, y: 2 };
    let p2 = Point { x: 3, y: 4 };
    let p3 = p1.add(&p2);

    println!("p1: {:?}", p1);
    println!("p2: {:?}", p2);
    println!("p1 + p2 = {:?}", p3);
}


// ── Summary ───────────────────────────────────

fn main() {
    println!("OOP and Modules in Rust");
    println!("========================");

    demonstrate_structs();
    demonstrate_traits();
    demonstrate_defaults();
    demonstrate_associated();
    demonstrate_bounds();
    demonstrate_visibility();
    demonstrate_modules();
    demonstrate_generics();
    demonstrate_deref();
    demonstrate_drop();
    demonstrate_clone();
    demonstrate_operators();

    println!("\n--- Summary ---");
    println!("Rust OOP:");
    println!("  - Structs: data types with impl blocks");
    println!("  - Traits: interfaces with default implementations");
    println!("  - Composition: use composition, not inheritance");
    println!("  - Visibility: pub for public, no keyword for private");
    println!("  - Modules: mod keyword, use with mod::name");
    println!("  - Generics: <T> type parameters");
    println!("  - Associated types: scoped to traits");
    println!("  - Ownership: compile-time memory safety");
    println!("  - Borrow checker: references with lifetime tracking");
}
