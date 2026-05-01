/*
 * OOP and Modules in Java
 * =========================
 * Demonstrates classes, objects, inheritance, encapsulation,
 * polymorphism, interfaces, and packages.
 */

package com.example;

// ── Classes and Objects ────────────────────────────

public class ClassesAndObjects {
    public static void main(String[] args) {
        System.out.println("--- Classes and Objects ---");

        // Simple class
        class Person {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            // Getters
            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            @Override
            public String toString() {
                return "Person(name=" + name + ", age=" + age + ")";
            }
        }

        Person person = new Person("Ada", 36);
        System.out.println("person = " + person);
        System.out.println("person.getName() = " + person.getName());
        System.out.println("person.getAge() = " + person.getAge());

        // Static vs instance fields
        class Counter {
            private static int instanceCount = 0;
            private int count;

            public Counter() {
                count = 0;
                instanceCount++;
            }

            public void increment() {
                count++;
            }

            public int getCount() {
                return count;
            }

            public static int getInstanceCount() {
                return instanceCount;
            }
        }

        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.increment();
        c2.increment();

        System.out.println("c1.count = " + c1.getCount());
        System.out.println("c2.count = " + c2.getCount());
        System.out.println("instanceCount = " + Counter.getInstanceCount());
    }
}


// ── Inheritance ─────────────────────────────

public class Inheritance {
    public static void main(String[] args) {
        System.out.println("\n--- Inheritance ---");

        // Base class
        class Vehicle {
            protected String brand;
            protected int year;

            public Vehicle(String brand, int year) {
                this.brand = brand;
                this.year = year;
            }

            public void start() {
                System.out.println(brand + " engine starting");
            }

            public void stop() {
                System.out.println(brand + " engine stopping");
            }
        }

        // Subclass with method override
        class Car extends Vehicle {
            private String model;

            public Car(String brand, int year, String model) {
                super(brand, year);
                this.model = model;
            }

            @Override
            public void start() {
                System.out.println(brand + " " + model + " starting");
            }

            @Override
            public void stop() {
                System.out.println(brand + " " + model + " stopping");
            }
        }

        // Subclass with new methods
        class ElectricCar extends Car {
            private int batteryCapacity;

            public ElectricCar(String brand, int year, String model, int batteryCapacity) {
                super(brand, year, model);
                this.batteryCapacity = batteryCapacity;
            }

            public void charge() {
                System.out.println(brand + " " + model + " charging");
            }

            public int getRange() {
                return batteryCapacity * 3;
            }
        }

        Vehicle tesla = new ElectricCar("Tesla", 2020, "Model S", 400);
        Vehicle f150 = new Car("Ford", 2020, "F-150");

        tesla.start();
        System.out.println("tesla.charge() = " + tesla.getRange());
        f150.start();
        System.out.println("f150 has no battery");
    }
}


// ── Polymorphism ─────────────────────────────

public class Polymorphism {
    public static void main(String[] args) {
        System.out.println("\n--- Polymorphism ---");

        // Duck typing (via interfaces)
        interface Quackable {
            void quack();
            void swim();
        }

        class Duck implements Quackable {
            @Override
            public void quack() {
                System.out.println("Quack!");
            }

            @Override
            public void swim() {
                System.out.println("Duck swimming");
            }
        }

        class Robot implements Quackable {
            @Override
            public void quack() {
                System.out.println("Beep boop!");
            }

            @Override
            public void swim() {
                System.out.println("Robot swimming!");
            }
        }

        // Polymorphic method
        void interact(Quackable entity) {
            entity.quack();
            entity.swim();
        }

        Duck duck = new Duck();
        Robot robot = new Robot();

        System.out.println("\nDuck polymorphism:");
        interact(duck);

        System.out.println("\nRobot polymorphism:");
        interact(robot);
    }
}


// ── Abstract Classes ─────────────────────────

public class AbstractClasses {
    public static void main(String[] args) {
        System.out.println("\n--- Abstract Classes ---");

        // Abstract class (cannot be instantiated)
        abstract class Shape {
            public abstract double area();
            public abstract double perimeter();
        }

        // Concrete class
        class Rectangle extends Shape {
            private double width;
            private double height;

            public Rectangle(double width, double height) {
                this.width = width;
                this.height = height;
            }

            @Override
            public double area() {
                return width * height;
            }

            @Override
            public double perimeter() {
                return 2 * (width + height);
            }
        }

        class Circle extends Shape {
            private double radius;

            public Circle(double radius) {
                this.radius = radius;
            }

            @Override
            public double area() {
                return Math.PI * radius * radius;
            }

            @Override
            public double perimeter() {
                return 2 * Math.PI * radius;
            }
        }

        // Use concrete classes
        Shape rectangle = new Rectangle(5, 3);
        Shape circle = new Circle(2);

        System.out.println("rectangle.area() = " + rectangle.area());
        System.out.println("circle.area() = " + circle.area());

        // Cannot instantiate abstract class
        // Shape shape = new Shape();  // Compile error!
    }
}


// ── Interfaces ─────────────────────────────

public class Interfaces {
    public static void main(String[] args) {
        System.out.println("\n--- Interfaces ---");

        // Interface definition
        interface Drawable {
            void draw();
        void resize(double factor);
        }

        // Class implementing interface
        class Circle implements Drawable {
            private double radius;

            public Circle(double radius) {
                this.radius = radius;
            }

            @Override
            public void draw() {
                System.out.println("Drawing circle with radius " + radius);
            }

            @Override
            public void resize(double factor) {
                this.radius *= factor;
            }
        }

        class Rectangle implements Drawable {
            private double width;
            private double height;

            public Rectangle(double width, double height) {
                this.width = width;
                this.height = height;
            }

            @Override
            public void draw() {
                System.out.println("Drawing rectangle " + width + "x" + height);
            }

            @Override
            public void resize(double factor) {
                this.width *= factor;
                this.height *= factor;
            }
        }

        // Polymorphic usage
        Drawable shape1 = new Circle(5);
        Drawable shape2 = new Rectangle(10, 5);

        shape1.draw();
        shape2.draw();

        shape1.resize(2);
        System.out.println("Resized circle radius: " + ((Circle)shape1).radius);
    }
}


// ── Encapsulation ─────────────────────────────

public class Encapsulation {
    public static void main(String[] args) {
        System.out.println("\n--- Encapsulation ---");

        // Public
        class PublicExample {
            public int value = 42;
        }

        // Private
        class PrivateExample {
            private int value;

            public PrivateExample(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        // Protected
        class ProtectedExample {
            protected int value;

            public ProtectedExample(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        // Package-private (default)
        class PackagePrivateExample {
            int value;

            public PackagePrivateExample(int value) {
                this.value = value;
            }
        }

        // Access demonstration
        PublicExample pub = new PublicExample(42);
        PrivateExample priv = new PrivateExample(100);
        ProtectedExample prot = new ProtectedExample(50);
        PackagePrivateExample pkg = new PackagePrivateExample(75);

        System.out.println("Public: " + pub.getValue());
        System.out.println("Private: " + priv.getValue());
        System.out.println("Protected: " + prot.getValue());
        System.out.println("Package-private (same class): " + pkg.getValue());
    }
}


// ── Packages and Imports ────────────────────

public class PackagesAndImports {
    public static void main(String[] args) {
        System.out.println("\n--- Packages and Imports ---");

        // Import class from same package
        import com.example.ClassesAndObjects.Person;

        Person ada = new Person("Ada", 36);
        System.out.println("Imported Person: " + ada);

        // Import from java.util package
        import java.util.List;
        import java.util.ArrayList;

        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");

        System.out.println("Names: " + names);

        // Static import
        import static java.lang.Math.PI;
        System.out.println("PI (static import): " + Math.PI);
    }
}


// ── Nested Classes ─────────────────────────

public class NestedClasses {
    public static void main(String[] args) {
        System.out.println("\n--- Nested Classes ---");

        // Static nested class
        class Outer {
            private int value = 10;

            static class NestedStatic {
                public static int getOuterValue() {
                    return value;  // Can only access static members
                }
            }

            // Inner (non-static) class
            class Inner {
                public void display() {
                    System.out.println("Inner class accessing: " + value);
                }
            }

            Outer outer = new Outer();
            Inner inner = outer.new Inner();
            inner.display();
        }
    }
}


// ── Enums (enumerations) ─────────────────────

public class Enums {
    public static void main(String[] args) {
        System.out.println("\n--- Enums (enumerations) ---");

        // Simple enum
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        }

        Day today = Day.FRIDAY;
        System.out.println("Today is " + today);
        System.out.println("today.ordinal() = " + today.ordinal());
        System.out.println("Day name: " + today.name());

        // Enum with values and methods
        enum Status {
            ACTIVE("Active", 200),
            PENDING("Pending", 400),
            COMPLETED("Completed", 0);
        }

        Status[] all = Status.values();
        for (Status status : all) {
            System.out.println(status + " -> code=" + status.getCode());
        }
    }
}


// ── Anonymous Classes ─────────────────────

public class AnonymousClasses {
    public static void main(String[] args) {
        System.out.println("\n--- Anonymous Classes ---");

        // Runnable interface
        interface Runnable {
            void run();
        }

        // Anonymous class implementing interface
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running anonymous class");
            }
        };

        runner.run();

        // Anonymous class for event listener (simplified)
        java.awt.Button button = new java.awt.Button("Click me") {
            // Anonymous inner class for action listener
        };

        System.out.println("Button created with anonymous action listener");
    }
}


// ── Final Classes ─────────────────────

public class FinalClasses {
    public static void main(String[] args) {
        System.out.println("\n--- Final Classes ---");

        // Final class (cannot be extended)
        final class ImmutablePoint {
            private final int x;
            private final int y;

            public ImmutablePoint(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() { return x; }
            public int getY() { return y; }
        }

        // Final method (cannot be overridden)
        class Parent {
            public final void finalMethod() {
                System.out.println("Parent final method");
            }
        }

        class Child extends Parent {
            // Cannot override final method
            // public void finalMethod() {}  // Compile error!
        }

        Parent parent = new Parent();
        Child child = new Child();

        parent.finalMethod();
        // child.finalMethod();  // Not accessible
    }
}


// ── Method Overriding vs Overloading ─────────

public class OverridingOverloading {
    public static void main(String[] args) {
        System.out.println("\n--- Method Overriding vs Overloading ---");

        class Calculator {
            // Method overloading (same name, different params)
            public int add(int a, int b) {
                return a + b;
            }

            public double add(double a, double b) {
                return a + b;
            }

            public String add(String a, String b) {
                return a + b;
            }
        }

        Calculator calc = new Calculator();
        System.out.println("calc.add(1, 2) = " + calc.add(1, 2));
        System.out.println("calc.add(1.5, 2.5) = " + calc.add(1.5, 2.5));
        System.out.println("calc.add(\"A\", \"B\") = " + calc.add("A", "B"));
    }
}


// ── Generics ─────────────────────────────

public class Generics {
    public static void main(String[] args) {
        System.out.println("\n--- Generics ---");

        // Generic class
        class Box {
            private T value;

            public Box(T value) {
                this.value = value;
            }

            public T getValue() {
                return value;
            }
        }

        // Usage with different types
        Box<Integer> intBox = new Box<>(42);
        Box<String> strBox = new Box<>("Hello");

        System.out.println("intBox.getValue() = " + intBox.getValue());
        System.out.println("strBox.getValue() = " + strBox.getValue());

        // Generic interface
        interface Container {
            T get();
            void set(T value);
        }

        class GenericContainer implements Container {
            private T value;

            public GenericContainer(T value) {
                this.value = value;
            }

            @Override
            public T get() {
                return value;
            }

            @Override
            public void set(T value) {
                this.value = value;
            }
        }

        GenericContainer<Integer> intContainer = new GenericContainer<>(100);
        intContainer.set(200);
        System.out.println("intContainer.get() = " + intContainer.get());
    }
}


// ── Exceptions ─────────────────────────────

public class Exceptions {
    public static void main(String[] args) {
        System.out.println("\n--- Exceptions ---");

        // Custom exception
        class InsufficientFundsException extends RuntimeException {
            public InsufficientFundsException(String message) {
                super(message);
            }
        }

        // Try-catch
        try {
            throw new InsufficientFundsException("Not enough money");
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught generic: " + e.getMessage());
        }

        // Finally block
        boolean success = false;
        try {
            success = true;
        } finally {
            System.out.println("Finally block executed. Success = " + success);
        }
    }
}
