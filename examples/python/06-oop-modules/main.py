"""
OOP and Modules in Python
========================
Demonstrates classes, objects, inheritance, encapsulation,
polymorphism, and modules (namespaces).
"""

# ── Classes and Objects ───────────────────────

print("--- Classes and Objects ---")

# Class definition
class Animal:
    def __init__(self, name: str, species: str):
        self.name = name
        self.species = species

    def speak(self) -> str:
        return f"{self.name} makes a sound"

    def __str__(self) -> str:
        return f"Animal({self.name}, {self.species})"


# Object instantiation
dog = Animal("Buddy", "Dog")
cat = Animal("Whiskers", "Cat")

print(f"dog = {dog}")
print(f"dog.speak() = {dog.speak()}")
print(f"str(dog) = {str(dog)}")

# Class vs Instance attributes
class Counter:
    count = 0  # Class attribute (shared)

    def __init__(self):
        # Reset instance attribute
        self.value = 0

    def increment(self):
        self.count += 1
        self.value += 1

counter1 = Counter()
counter2 = Counter()
counter1.increment()
counter2.increment()

print(f"counter1.count = {counter1.count} (class attribute)")
print(f"counter2.count = {counter2.count} (class attribute)")
print(f"counter1.value = {counter1.value} (instance attribute)")
print(f"counter2.value = {counter2.value} (instance attribute)")


# Instance methods vs Class methods
class InstanceMethods:
    def instance_method(self):
        return "Instance method"

    @classmethod
    def class_method(cls):
        return "Class method"

obj = InstanceMethods()
print(f"obj.instance_method() = {obj.instance_method()}")
print(f"obj.class_method() = {obj.class_method()}")


# ── Inheritance ───────────────────────────────

print("\n--- Inheritance ---")

# Base class
class Vehicle:
    def __init__(self, brand: str, year: int):
        self.brand = brand
        self.year = year

    def start(self) -> str:
        return f"{self.brand} engine starting"

    def stop(self) -> str:
        return f"{self.brand} engine stopping"


# Subclass with method override
class Car(Vehicle):
    def __init__(self, brand: str, year: int, model: str):
        super().__init__(brand, year)
        self.model = model

    def start(self) -> str:
        return f"{self.brand} {self.model} starting"

    def stop(self) -> str:
        return f"{self.brand} {self.model} stopping"


# Subclass with additional methods
class ElectricCar(Car):
    def __init__(self, brand: str, year: int, model: str, battery_capacity: int):
        super().__init__(brand, year, model)
        self.battery_capacity = battery_capacity

    def charge(self) -> str:
        return f"{self.brand} {self.model} charging"

    def get_range(self) -> int:
        return self.battery_capacity * 3


# Inheritance demonstration
tesla = ElectricCar("Tesla", 2020, "Model S", 400)
f150 = Car("Ford", 2020, "F-150", None)

print(f"tesla.start() = {tesla.start()}")
print(f"tesla.charge() = {tesla.charge()}")
print(f"tesla.get_range() = {tesla.get_range()} miles")

print(f"f150.start() = {f150.start()}")
print(f"f150.charge() = N/A (no battery)")
print(f"f150.get_range() = N/A (no battery)")

# isinstance and issubclass checks
print(f"tesla is a Vehicle? {isinstance(tesla, Vehicle)}")
print(f"tesla is a Car? {isinstance(tesla, Car)}")
print(f"ElectricCar is subclass of Car? {issubclass(ElectricCar, Car)}")


# ── Encapsulation ───────────────────────────────

print("\n--- Encapsulation ---")

# Private attributes (name mangling)
class BankAccount:
    def __init__(self, owner: str, balance: float):
        self.__owner = owner  # Private (name mangled)
        self.__balance = balance  # Private

    @property
    def owner(self) -> str:
        return self.__owner  # Public getter

    @property
    def balance(self) -> float:
        return self.__balance  # Public getter

    def deposit(self, amount: float) -> None:
        if amount > 0:
            self.__balance += amount

    def withdraw(self, amount: float) -> bool:
        if 0 < amount <= self.__balance:
            self.__balance -= amount
            return True
        return False


account = BankAccount("Alice", 1000.0)
print(f"account.owner = {account.owner}")
print(f"account.balance = {account.balance}")

account.deposit(500.0)
print(f"After deposit: {account.balance}")

account.withdraw(200.0)
print(f"After withdraw: {account.balance}")

# Direct attribute access (still possible with name mangling)
# account.__balance = 999999  # "Private" but accessible


# ── Polymorphism ─────────────────────────────

print("\n--- Polymorphism ---")

# Duck typing (Python's polymorphism style)
class Duck:
    def quack(self) -> str:
        return "Quack!"

    def swim(self) -> str:
        return "Duck swimming"


class Person:
    def quack(self) -> str:
        return "I'm quacking like a duck!"

    def swim(self) -> str:
        return "Person swimming (can't swim)"


class Robot:
    def quack(self) -> str:
        return "Beep boop!"

    def swim(self) -> str:
        return "Robot swimming!"


# Polymorphic function
def interact(entity):
    entity.quack()
    entity.swim()


print("Duck polymorphism:")
interact(Duck())    # Outputs: Quack! and Duck swimming

print("\nPerson polymorphism:")
interact(Person())  # Outputs: I'm quacking like a duck! and Person swimming (can't swim)

print("\nRobot polymorphism:")
interact(Robot())  # Outputs: Beep boop! and Robot swimming!


# ── Abstract Base Classes ─────────────────────

print("\n--- Abstract Base Classes (ABC) ---")

from abc import ABC, abstractmethod


class Shape(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

    @abstractmethod
    def perimeter(self) -> float:
        pass


class Rectangle(Shape):
    def __init__(self, width: float, height: float):
        self.width = width
        self.height = height

    def area(self) -> float:
        return self.width * self.height

    def perimeter(self) -> float:
        return 2 * (self.width + self.height)


class Circle(Shape):
    def __init__(self, radius: float):
        self.radius = radius

    def area(self) -> float:
        import math
        return math.pi * self.radius ** 2

    def perimeter(self) -> float:
        return 2 * math.pi * self.radius


# Cannot instantiate abstract class
# shape = Shape()  # TypeError!

# Instantiate concrete subclasses
rectangle = Rectangle(5, 3)
circle = Circle(2)

print(f"rectangle.area() = {rectangle.area()}")
print(f"circle.area() = {circle.area()}")


# ── Multiple Inheritance ─────────────────────

print("\n--- Multiple Inheritance ---")


class Flyable:
    def fly(self) -> str:
        return "Flying!"


class Swimmable:
    def swim(self) -> str:
        return "Swimming!"


class Duck(Flyable, Swimmable):
    def __init__(self, name: str):
        self.name = name

    def fly(self) -> str:
        return f"{self.name} is flying!"

    def swim(self) -> str:
        return f"{self.name} is swimming!"

    def quack(self) -> str:
        return "Quack!"


class Penguin(Swimmable):
    def __init__(self, name: str):
        self.name = name

    def swim(self) -> str:
        return f"{self.name} is swimming!"

    def quack(self) -> str:
        return "No quack for penguin!"


# Multiple inheritance demonstration
duck = Duck("Donald")
penguin = Penguin("Penny")

print(f"duck.fly() = {duck.fly()}")
print(f"duck.swim() = {duck.swim()}")
print(f"duck.quack() = {duck.quack()}")

print(f"\npenguin.fly() = N/A (no Flyable)")
print(f"penguin.swim() = {penguin.swim()}")
print(f"penguin.quack() = {penguin.quack()}")


# ── Method Resolution Order ─────────────

print("\n--- Method Resolution Order (MRO) ---")


class A:
    def method(self) -> str:
        return "Method from A"


class B(A):
    def method(self) -> str:
        return f"Method from B (extends A)"


class C(B):
    def method(self) -> str:
        return f"Method from C (extends B)"


# Diamond problem
class D(B, C):
    def method(self) -> str:
        return f"Method from D (extends B and C)"


d = D()
print(f"d.method() = {d.method()}")
print(f"MRO of D: {[cls.__name__ for cls in D.__mro__]}")


# ── Properties ─────────────────────────────────────

print("\n--- Properties (getters/setters) ---")


class Temperature:
    def __init__(self, celsius: float):
        self._celsius = celsius

    @property
    def celsius(self) -> float:
        return self._celsius

    @celsius.setter
    def celsius(self, value: float):
        self._celsius = value

    @property
    def fahrenheit(self) -> float:
        return self._celsius * 9 / 5 + 32

    @fahrenheit.setter
    def fahrenheit(self, value: float):
        self._celsius = (value - 32) * 5 / 9


temp = Temperature()
temp.celsius = 25

print(f"temp.celsius = {temp.celsius}")
print(f"temp.fahrenheit = {temp.fahrenheit}")

temp.fahrenheit = 77
print(f"After setting F=77, C = {temp.celsius}")


# ── Static Methods and Class Methods ─────────

print("\n--- Static and Class Methods ---")


class Math:
    @staticmethod
    def add(a: float, b: float) -> float:
        return a + b

    @classmethod
    def multiply(cls, a: float, b: float) -> float:
        return a * b

    def divide(self, a: float, b: float) -> float:
        return a / b if b != 0 else 0.0


print(f"Math.add(3, 4) = {Math.add(3, 4)}")
print(f"Math.multiply(3, 4) = {Math.multiply(3, 4)}")
print(f"Math.divide(10, 2) = {Math.divide(10, 2)}")
print(f"Math.divide(10, 0) = {Math.divide(10, 0)}")


# ── Magic Methods ─────────────────────────────

print("\n--- Magic Methods (__dunder__) ---")


class Vector:
    def __init__(self, x: float, y: float):
        self.x = x
        self.y = y

    def __str__(self) -> str:
        return f"Vector({self.x}, {self.y})"

    def __repr__(self) -> str:
        return f"Vector(x={self.x}, y={self.y})"

    def __add__(self, other):
        return Vector(self.x + other.x, self.y + other.y)

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y


v1 = Vector(3, 4)
v2 = Vector(3, 4)
v3 = v1 + v2

print(f"v1 = {v1}")
print(f"v2 = {v2}")
print(f"v1 == v2: {v1 == v2}")
print(f"v1 + v2 = {v3}")
print(f"str(v1 + v2) = {str(v3)}")


# ── Dataclasses (Python 3.7+) ─────────────

print("\n--- Dataclasses (Python 3.7+) ---")

from dataclasses import dataclass


@dataclass
class Point:
    x: float
    y: float

    def distance_to(self, other: 'Point') -> float:
        return ((self.x - other.x) ** 2 + (self.y - other.y) ** 2) ** 0.5)


@dataclass
class PersonDataclass:
    name: str
    age: int
    email: str = ""


point = Point(0, 0)
person = PersonDataclass("Alice", 30)
print(f"point = {point}")
print(f"person = {person}")

# Dataclasses provide __init__, __repr__, __eq__ automatically


# ── Summary ───────────────────────────────────

print("\n--- Summary ---")
print("Python OOP:")
print("  - Classes: class keyword, __init__, self")
print("  - Objects: instantiate classes, dot notation")
print("  - Inheritance: extends base class, method override")
print("  - Multiple inheritance: comma-separated base classes")
print("  - Encapsulation: private (__attr) via name mangling")
print("  - Polymorphism: duck typing (no interfaces needed)")
print("  - Abstract base classes: ABC module, @abstractmethod")
print("  - Method Resolution Order: MRO for diamond problem")
print("  - Properties: @property decorator for getters/setters")
print("  - Static methods: @staticmethod, @classmethod")
print("  - Magic methods: __str__, __repr__, __eq__, etc.")
print("  - Dataclasses: type-safe data containers (3.7+)")
