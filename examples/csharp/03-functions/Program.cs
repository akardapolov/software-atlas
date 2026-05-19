// Basic method
static int Add(int a, int b) => a + b;

// Method overloading
static double Add(double a, double b) => a + b;

// Default parameters
static string Greet(string name, string greeting = "Hello") =>
    $"{greeting}, {name}!";

// Named arguments
static void PrintOrder(string product, int quantity, decimal price) =>
    Console.WriteLine($"{quantity}x {product} at {price:C} each");

// Local function
static int Factorial(int n)
{
    static int Fact(int x) => x <= 1 ? 1 : x * Fact(x - 1);
    return Fact(n);
}

// Expression-bodied method
static bool IsEven(int n) => n % 2 == 0;

// Generic method
static void Swap<T>(ref T a, ref T b)
{
    (b, a) = (a, b);
}

// params
static int Sum(params int[] numbers) => numbers.Sum();

// ---- Demo ----

Console.WriteLine($"Add(2, 3) = {Add(2, 3)}");
Console.WriteLine($"Add(2.5, 3.5) = {Add(2.5, 3.5)}");

Console.WriteLine(Greet("Alice"));
Console.WriteLine(Greet("Bob", "Hi"));

PrintOrder(product: "Widget", quantity: 5, price: 9.99m);

Console.WriteLine($"Factorial(5) = {Factorial(5)}");
Console.WriteLine($"IsEven(4) = {IsEven(4)}");

var x = 10;
var y = 20;
Swap(ref x, ref y);
Console.WriteLine($"After swap: x={x}, y={y}");

Console.WriteLine($"Sum(1,2,3,4,5) = {Sum(1, 2, 3, 4, 5)}");
