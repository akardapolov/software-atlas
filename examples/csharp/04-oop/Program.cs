// Class with properties
class Animal
{
    public string Name { get; set; }
    public int Age { get; set; }

    public Animal(string name, int age)
    {
        Name = name;
        Age = age;
    }

    public virtual void Speak() =>
        Console.WriteLine($"{Name} makes a sound");

    public override string ToString() =>
        $"Animal{{Name='{Name}', Age={Age}}}";
}

// Inheritance
class Dog : Animal
{
    public string Breed { get; set; }

    public Dog(string name, int age, string breed) : base(name, age)
    {
        Breed = breed;
    }

    public override void Speak() =>
        Console.WriteLine($"{Name} the {Breed} barks!");
}

// Interface
interface IFlyable
{
    void Fly();
}

// Class implementing interface
class Bird : Animal, IFlyable
{
    public Bird(string name, int age) : base(name, age) { }

    public void Fly() =>
        Console.WriteLine($"{Name} flies away");

    public override void Speak() =>
        Console.WriteLine($"{Name} chirps");
}

// Struct (value type)
readonly struct Point
{
    public double X { get; }
    public double Y { get; }

    public Point(double x, double y)
    {
        X = x;
        Y = y;
    }

    public double DistanceFromOrigin => Math.Sqrt(X * X + Y * Y);

    public override string ToString() => $"({X}, {Y})";
}

// Record (immutable reference type)
public record Person(string Name, int Age);

// Record with inheritance
public record Employee(string Name, int Age, string Department) : Person(Name, Age);

// ---- Demo ----

var dog = new Dog("Buddy", 3, "Golden Retriever");
dog.Speak();
Console.WriteLine(dog);

var bird = new Bird("Tweety", 1);
bird.Speak();
bird.Fly();

var point = new Point(3, 4);
Console.WriteLine($"Point: {point}, Distance: {point.DistanceFromOrigin}");

// Record
var alice = new Person("Alice", 30);
var bob = new Employee("Bob", 25, "Engineering");
Console.WriteLine(alice);
Console.WriteLine(bob);

// Value equality for records
var alice2 = new Person("Alice", 30);
Console.WriteLine($"alice == alice2: {alice == alice2}");  // True

// Non-destructive mutation
var olderAlice = alice with { Age = 31 };
Console.WriteLine(olderAlice);

// Deconstruction
var (name, age) = alice;
Console.WriteLine($"Deconstructed: {name}, {age}");
