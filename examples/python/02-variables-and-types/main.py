"""
Variables and Types in Python
=============================
Python uses dynamic, strong typing.
Variables are names that refer to objects — they have no type themselves.
"""

# ── Basic types ────────────────────────────────────────────

# Integers — arbitrary precision (no overflow)
count: int = 42
big_number: int = 10 ** 100  # googol — no overflow!
print(f"count = {count}, type = {type(count)}")
print(f"big_number has {len(str(big_number))} digits")

# Floats — IEEE 754 double precision
pi: float = 3.14159
print(f"pi = {pi}, type = {type(pi)}")

# Booleans — subclass of int (True == 1, False == 0)
active: bool = True
print(f"active = {active}, type = {type(active)}")
print(f"True + True = {True + True}")  # 2 — because bool is int

# Strings — immutable sequences of Unicode characters
name: str = "Software Atlas"
print(f"name = {name}, type = {type(name)}")
print(f"name[0] = {name[0]}, name[-1] = {name[-1]}")

# None — absence of a value (like null, but it's an object)
result: None = None
print(f"result = {result}, type = {type(result)}")


# ── Dynamic typing ─────────────────────────────────────────

# The same variable can refer to different types over time
x = 42
print(f"\nx = {x}, type = {type(x)}")

x = "hello"
print(f"x = {x}, type = {type(x)}")

x = [1, 2, 3]
print(f"x = {x}, type = {type(x)}")


# ── Strong typing ──────────────────────────────────────────

# Python does NOT implicitly convert between types
print("\n--- Strong typing ---")
try:
    result = "3" + 4  # TypeError!
except TypeError as e:
    print(f'"3" + 4 → TypeError: {e}')

# You must convert explicitly
result = int("3") + 4
print(f'int("3") + 4 = {result}')

result = "3" + str(4)
print(f'"3" + str(4) = "{result}"')


# ── Collections ────────────────────────────────────────────

print("\n--- Collections ---")

# List — mutable, ordered
fruits: list[str] = ["apple", "banana", "cherry"]
fruits.append("date")
print(f"fruits = {fruits}")

# Tuple — immutable, ordered
point: tuple[int, int] = (10, 20)
# point[0] = 30  # TypeError! Tuples are immutable
print(f"point = {point}")

# Dict — mutable, key-value pairs
person: dict[str, str | int] = {"name": "Ada", "age": 36}
print(f"person = {person}")

# Set — mutable, unordered, unique elements
tags: set[str] = {"python", "typing", "python"}  # duplicate removed
print(f"tags = {tags}")


# ── Type hints (PEP 484) ──────────────────────────────────

# Type hints are NOT enforced at runtime — they're for documentation
# and static analysis tools (mypy, pyright, IDE support)

def greet(name: str, times: int = 1) -> str:
    """Greet someone. Type hints document of expected types."""
    return (f"Hello, {name}! " * times).strip()

print(f"\n{greet('World')}")
print(greet("Atlas", 3))

# This works at runtime even though it violates the type hints!
# A type checker (mypy) would flag it, but Python itself doesn't care.
print(greet(42))  # Works! 42 has __str__, so f-string handles it


# ── Everything is an object ────────────────────────────────

print("\n--- Everything is an object ---")
print(f"(42).bit_length() = {(42).bit_length()}")
print(f'"hello".upper() = {"hello".upper()}')
print(f"type(type(42)) = {type(type(42))}")  # <class 'type'>


# ── Duck typing ────────────────────────────────────────────

print("\n--- Duck typing ---")


class Duck:
    def quack(self):
        return "Quack!"


class Person:
    def quack(self):
        return "I'm quacking like a duck!"


def make_it_quack(thing):
    """We don't check type — we just call quack().
    If it has a quack() method, it works. Duck typing."""
    print(thing.quack())


make_it_quack(Duck())
make_it_quack(Person())


# ── Comparison with other languages ───────────────────────

print("\n--- Summary ---")
print("Python: dynamic + strong typing")
print("  - No type declarations required (dynamic)")
print("  - No implicit coercion (strong)")
print("  - Type hints available but optional")
print("  - Everything is an object")
print("  - Duck typing: behaviour > declared type")
