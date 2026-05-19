// Value types
int age = 30;
double pi = 3.14159;
bool isActive = true;
char grade = 'A';

// Value type - struct
Point origin = new Point(0, 0);

// Reference types
string name = "Alice";
int[] numbers = { 1, 2, 3, 4, 5 };

// var - type inference
var count = 42;           // int
var message = "hello";    // string
var price = 19.99m;       // decimal

// Nullable value types
int? maybeCount = null;
int definiteCount = maybeCount ?? 0;  // null-coalescing

// Tuples
var person = (Name: "Bob", Age: 25);
Console.WriteLine($"{person.Name} is {person.Age}");

// Deconstruction
var (n, a) = person;
Console.WriteLine($"Deconstructed: {n}, {a}");

// dynamic
dynamic dyn = 42;
dyn = "now a string";
Console.WriteLine(dyn);

// Ranges and indexes
var last = numbers[^1];     // 5 (from end)
var slice = numbers[1..4];  // [2, 3, 4]
Console.WriteLine($"Last: {last}, Slice: [{string.Join(", ", slice)}]");

record struct Point(double X, double Y);
