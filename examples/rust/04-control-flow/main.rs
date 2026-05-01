fn classify(n: i32) -> &'static str {
    if n < 0 { "negative" }
    else if n == 0 { "zero" }
    else { "positive" }
}

fn main() {
    println!("--- if / else if / else (if is an expression) ---");
    for n in [-3, 0, 7] {
        println!("{n} → {}", classify(n));
    }

    println!("\n--- match (exhaustive) ---");
    let day = "sat";
    let kind = match day {
        "mon" | "tue" | "wed" | "thu" | "fri" => "weekday",
        "sat" | "sun" => "weekend",
        _ => "unknown",
    };
    println!("{day} → {kind}");

    println!("\n--- loop / break with value ---");
    let mut x = 1;
    let first_ge_20 = loop {
        x *= 2;
        if x >= 20 {
            break x; // break returns a value
        }
    };
    println!("first power of two >= 20: {first_ge_20}");

    println!("\n--- while ---");
    let mut i = 1;
    let mut sum = 0;
    while i <= 5 {
        sum += i;
        i += 1;
    }
    println!("sum 1..5 = {sum}");

    println!("\n--- for (iterator) ---");
    for (idx, v) in [10, 20, 30].iter().enumerate() {
        println!("idx={idx}, v={v}");
    }

    println!("\n--- break / continue ---");
    for n in 1..10 {
        if n % 2 == 1 {
            continue;
        }
        if n > 6 {
            break;
        }
        println!("even <= 6: {n}");
    }

    println!("\n--- if let / while let (pattern-based control flow) ---");
    let maybe = Some(42);
    if let Some(v) = maybe {
        println!("if let: got {v}");
    }

    let mut stack = vec![1, 2, 3];
    while let Some(v) = stack.pop() {
        println!("popped {v}");
    }

    println!("\n--- summary ---");
    println!("Rust control flow: if-expr, match, loop/while/for, break value, pattern forms");
}
