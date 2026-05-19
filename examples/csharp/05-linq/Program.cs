var numbers = new List<int> { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
var words = new List<string> { "apple", "banana", "avocado", "blueberry", "cherry" };

// Method syntax
var evens = numbers.Where(n => n % 2 == 0);
Console.WriteLine($"Even numbers: {string.Join(", ", evens)}");

var squares = numbers.Select(n => n * n);
Console.WriteLine($"Squares: {string.Join(", ", squares)}");

// Aggregation
var sum = numbers.Sum();
var max = numbers.Max();
var avg = numbers.Average();
Console.WriteLine($"Sum: {sum}, Max: {max}, Avg: {avg:F2}");

// Query syntax
var longWords = from w in words
                where w.Length > 5
                orderby w.Length
                select w.ToUpper();
Console.WriteLine($"Long words: {string.Join(", ", longWords)}");

// Grouping
var grouped = words.GroupBy(w => w[0]);
foreach (var group in grouped)
{
    Console.WriteLine($"  [{group.Key}]: {string.Join(", ", group)}");
}

// First, Any, All
Console.WriteLine($"First even > 5: {numbers.First(n => n > 5 && n % 2 == 0)}");
Console.WriteLine($"Any negative: {numbers.Any(n => n < 0)}");
Console.WriteLine($"All positive: {numbers.All(n => n > 0)}");

// Take / Skip
Console.WriteLine($"First 3: {string.Join(", ", numbers.Take(3))}");
Console.WriteLine($"Skip 5: {string.Join(", ", numbers.Skip(5))}");

// Deferred execution demo
var query = numbers.Where(n => n > 5);  // Not executed yet
Console.WriteLine("Query defined (not executed)");
numbers.Add(11);  // Query will see this!
Console.WriteLine($"Query result: {string.Join(", ", query)}");  // Includes 11

// Complex pipeline
var result = numbers
    .Where(n => n % 2 == 0)
    .Select(n => n * n)
    .OrderByDescending(n => n)
    .Take(3)
    .ToList();
Console.WriteLine($"Pipeline result: {string.Join(", ", result)}");
