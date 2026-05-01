"""
Functions in Python
===================
Python has first-class functions: they are objects, can be passed around,
returned from other functions, and stored in data structures.
"""

from functools import reduce, partial


# ── Basic function definition ─────────────────────────

def greet(name: str) -> str:
    """A simple function with type hints and a docstring."""
    return f"Hello, {name}!"


print(greet("Atlas"))
print(f"greet is a {type(greet)}")  # <class 'function'>


# ── Default and keyword arguments ─────────────────────

def power(base: float, exponent: float = 2) -> float:
    """Default argument: exponent defaults to 2 (square)."""
    return base ** exponent


print(f"\npower(3) = {power(3)}")          # 9
print(f"power(2, 10) = {power(2, 10)}")    # 1024
print(f"power(base=5, exponent=3) = {power(base=5, exponent=3)}")  # 125


# ── *args and **kwargs ────────────────────────────────

def flexible(*args, **kwargs):
    """Accept any number of positional and keyword arguments."""
    print(f"  args = {args}")
    print(f"  kwargs = {kwargs}")


print("\nflexible(1, 2, 3, name='Ada', age=36):")
flexible(1, 2, 3, name="Ada", age=36)


# ── Functions are first-class objects ─────────────────

print("\n--- First-class functions ---")

def add(a, b):
    return a + b


def multiply(a, b):
    return a * b


# Functions can be assigned to variables
operation = add
print(f"operation(3, 4) = {operation(3, 4)}")  # 7

# Functions can be stored in data structures
ops = {"add": add, "mul": multiply}
print(f"ops['mul'](3, 4) = {ops['mul'](3, 4)}")  # 12


# ── Higher-order functions ────────────────────────────

print("\n--- Higher-order functions ---")

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# map — apply a function to each element
squares = list(map(lambda x: x ** 2, numbers))
print(f"squares = {squares}")

# filter — keep elements that satisfy a predicate
evens = list(filter(lambda x: x % 2 == 0, numbers))
print(f"evens = {evens}")

# reduce — combine all elements into one value
total = reduce(lambda acc, x: acc + x, numbers, 0)
print(f"sum = {total}")

product = reduce(lambda acc, x: acc * x, numbers, 1)
print(f"product = {product}")

# sorted with key function
words = ["banana", "apple", "cherry", "date"]
by_length = sorted(words, key=len)
print(f"sorted by length = {by_length}")


# ── Writing your own higher-order function ────────────

print("\n--- Custom higher-order function ---")


def apply_twice(f, x):
    """Apply function f to x, then apply f to result."""
    return f(f(x))


print(f"apply_twice(lambda x: x + 1, 5) = {apply_twice(lambda x: x + 1, 5)}")  # 7
print(f"apply_twice(lambda x: x * 2, 3) = {apply_twice(lambda x: x * 2, 3)}")  # 12


# ── Lambda (anonymous functions) ──────────────────────

print("\n--- Lambda ---")

# Lambda: single expression, no statements, no name
square = lambda x: x ** 2
print(f"square(5) = {square(5)}")

# Useful inline with higher-order functions
pairs = [(1, "one"), (3, "three"), (2, "two")]
sorted_pairs = sorted(pairs, key=lambda p: p[0])
print(f"sorted pairs = {sorted_pairs}")


# ── Closures ──────────────────────────────────────────

print("\n--- Closures ---")


def make_multiplier(factor):
    """Return a function that multiplies its argument by factor."""
    def multiplier(x):
        return x * factor  # factor is captured from enclosing scope
    return multiplier


double = make_multiplier(2)
triple = make_multiplier(3)

print(f"double(5) = {double(5)}")    # 10
print(f"triple(5) = {triple(5)}")    # 15

# The closure captures variable, not value
print(f"double is a {type(double)}")
print(f"double.__closure__[0].cell_contents = {double.__closure__[0].cell_contents}")


def make_counter(start=0):
    """Closure with mutable state (using a list as a box)."""
    count = [start]  # list because nonlocal reassignment needs Python 3

    def increment():
        count[0] += 1
        return count[0]

    return increment


counter = make_counter()
print(f"counter() = {counter()}")  # 1
print(f"counter() = {counter()}")  # 2
print(f"counter() = {counter()}")  # 3


# ── Partial application ──────────────────────────────

print("\n--- Partial application ---")


def log(level, message):
    print(f"[{level}] {message}")


# Fix first argument
info = partial(log, "INFO")
error = partial(log, "ERROR")

info("Server started")
error("Connection failed")


# ── Decorators ────────────────────────────────────────

print("\n--- Decorators ---")


def log_calls(func):
    """Decorator that logs function calls."""
    def wrapper(*args, **kwargs):
        print(f"  → Calling {func.__name__}({args}, {kwargs})")
        result = func(*args, **kwargs)
        print(f"  ← {func.__name__} returned {result}")
        return result
    return wrapper


@log_calls
def add_numbers(a, b):
    return a + b


result = add_numbers(3, 4)


def memoize(func):
    """Decorator that caches results."""
    cache = {}

    def wrapper(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]

    return wrapper


@memoize
def fibonacci(n):
    if n < 2:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)


print(f"\nfibonacci(10) = {fibonacci(10)}")
print(f"fibonacci(30) = {fibonacci(30)}")  # fast thanks to memoization


# ── Generators (lazy functions) ───────────────────────

print("\n--- Generators ---")


def fibonacci_gen():
    """Generate Fibonacci numbers lazily (infinite)."""
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b


# Take first 10 from infinite generator
from itertools import islice

fibs = list(islice(fibonacci_gen(), 10))
print(f"First 10 Fibonacci: {fibs}")

# Generator expression (like list comprehension but lazy)
squares_gen = (x ** 2 for x in range(1, 11))
print(f"Squares (generator): {list(squares_gen)}")


# ── Function composition ─────────────────────────────

print("\n--- Function composition ---")


def compose(*fns):
    """Compose functions right-to-left: compose(f, g)(x) = f(g(x))"""
    def composed(x):
        result = x
        for f in reversed(fns):
            result = f(result)
        return result
    return composed


shout = compose(str.upper, str.strip, lambda s: s + "!")
print(f'shout("  hello  ") = "{shout("  hello  ")}"')

# Pipeline style (left-to-right)
def pipe(*fns):
    """Pipe functions left-to-right: pipe(f, g)(x) = g(f(x))"""
    def piped(x):
        result = x
        for f in fns:
            result = f(result)
        return result
    return piped


process = pipe(
    str.strip,
    str.lower,
    str.title,
    lambda s: f"Hello, {s}!",
)
print(f'process("  alice  ") = "{process("  alice  ")}"')


# ── Summary ───────────────────────────────────────────

print("\n--- Summary ---")
print("Python functions:")
print("  - First-class: assignable, passable, storable")
print("  - Higher-order: map, filter, reduce, sorted(key=...)")
print("  - Lambda: anonymous single-expression functions")
print("  - Closures: capture enclosing scope")
print("  - Partial application: functools.partial")
print("  - Decorators: wrap functions with @syntax")
print("  - Generators: lazy evaluation with yield")
print("  - Composition: build pipelines of functions")
