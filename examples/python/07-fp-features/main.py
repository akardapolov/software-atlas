"""
FP Features in Python
====================
Demonstrates higher-order functions, purity, immutability,
closures, and functional programming patterns.
"""

# ── Higher-Order Functions ─────────────────────

print("--- Higher-Order Functions ---")

# Functions that take functions as arguments

# map, filter, reduce (built-in)
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
squares = list(map(lambda x: x * x, numbers))
evens = list(filter(lambda x: x % 2 == 0, numbers))
total = reduce(lambda acc, x: acc + x, numbers, 0)

print(f"numbers = {numbers}")
print(f"squares (1-10): {squares}")
print(f"evens (1-10): {evens}")
print(f"total (sum 1-10): {total}")

# Custom higher-order function
def apply_twice(f, x):
    """Apply function f twice to x."""
    return f(f(x))

print(f"apply_twice(lambda x: x + 1, 5): {apply_twice(lambda x: x + 1, 5)}")
print(f"apply_twice(add_two, 10): {apply_twice(add_two, 10)}")

# sort with key function
words = ["banana", "apple", "cherry", "date"]
sorted_by_length = sorted(words, key=len)
print(f"sorted_by_length: {sorted_by_length}")

# sorted function
sorted_numbers = sorted([3, 1, 4, 1, 5, 9])
print(f"sorted_numbers: {sorted_numbers}")


# ── Pure Functions ───────────────────────

print("\n--- Pure Functions ---")

# Function with no side effects
def pure_add(a, b):
    """Pure function - always returns same result for same inputs."""
    return a + b

# Purity demonstration
print("Pure function results:")
print(f"pure_add(2, 3) = {pure_add(2, 3)}")
print(f"pure_add(2, 3) = {pure_add(2, 3)}  # Same inputs = same result

# Idempotent function
def idempotent_append(lst, item):
    """Returns new list (doesn't modify original)."""
    return lst + [item]

original = [1, 2, 3]
result1 = idempotent_append(original, 4)
print(f"Original: {original}")
print(f"After append: {result1}")
print(f"Original unchanged: {original}")  # lst not modified in place

# Side effect function for comparison
def append_with_log(lst, item):
    """Returns new list and logs the operation."""
    print(f"Appending {item} to {lst}")
    return lst + [item]


# ── Immutability ─────────────────────

print("\n--- Immutability ---")

# Strings are immutable
greeting = "Hello"
print(f"greeting: {greeting}")
print(f"greeting[0] = {greeting[0]}")

# Cannot modify strings
# greeting[0] = "H"  # This creates a new string
print(f"Original greeting: {greeting}")
print(f"After trying to modify: {greeting}")  # greeting unchanged

# Tuples are immutable
point = (10, 20)
print(f"point = {point}")
print(f"point[0] = {point[0]}")  # x is 10
# point = (50, 100)
print(f"After assignment: {point}")
print(f"Original point: {point}")  # Original tuple unchanged

# Lists are mutable but can be made immutable
original_list = [1, 2, 3]
print(f"original_list = {original_list}")

# Create new list (copy + [])
new_list = original_list[:]
print(f"new_list = {new_list}")
print(f"original_list is {original_list}")  # Original unchanged

# Append to new list (doesn't affect original)
new_list.append(4)
print(f"After new_list append: {new_list}")
print(f"original_list after append: {original_list}")

# Immutable alternative (tuple)
immutable_list = tuple(original_list)
print(f"immutable_list = {immutable_list}")
print(f"Appending to immutable_list:")
immutable_list.append(5)
print(f"immutable_list after append: {immutable_list}")
print(f"Original list still: {original_list}")  # Tuple not modified

# Using freeze for lists (completely immutable)
frozen_list = [1, 2, 3]
frozen = frozenset(frozen_list)
print(f"frozen_list = {frozen_list}")
print(f"Trying to modify:")
# frozen_list[0] = 10  # Error with set - raises TypeError
print(f"frozen_list after error: {frozen_list}")


# ── Closures ─────────────────────────────

print("\n--- Closures ---")

# Basic closure
def make_multiplier(factor):
    """Returns a function that multiplies by factor."""
    def multiply(x):
        return x * factor

double = make_multiplier(2)
triple = make_multiplier(3)

print(f"double(5) = {double(5)}")
print(f"triple(5) = {triple(5)}")

# Closure captures enclosing scope
def make_counter():
    """Returns (count, increment, reset) as closure."""
    count = 0

    def increment():
        nonlocal count
        count += 1
        return count

    def reset():
        nonlocal count
        count = 0

    def get_count():
        return count

    counter = make_counter()
print(f"Counter: count={counter.get_count()}")
counter.increment()
print(f"Counter: count={counter.get_count()}")

# Closure with default argument (using nonlocal)
def make_counter_with_default(start=0):
    """Counter with default starting value."""
    def increment():
        nonlocal count
        count += 1
        return count

    def reset():
        nonlocal count
        count = start

    def get_count():
        return count

    counter_with_default = make_counter_with_default()
print(f"Counter with default: count={counter_with_default.get_count()}")
counter_with_default.increment()
print(f"Counter with default: count={counter_with_default.get_count()}")
counter_with_default.reset()
print(f"Counter with default after reset: count={counter_with_default.get_count()}")


# Nested closures
def outer_function():
    """Function that returns an inner function."""
    x = 10

    def inner_function():
        # Captures x from outer scope
        return x + x

    return inner_function

# Create closure
closure = outer_function()
print(f"Closure result: {closure()}")
print(f"Closure still has access to x: {closure.__closure__['x']}")


# Function closure (function with state)
def make_power():
    """Returns (power) function with memoization."""
    power_cache = {}

    def power(n):
        if n in power_cache:
            return power_cache[n]
        result = n ** n
        power_cache[n] = result
        return result

power_of_2 = power(2)
power_of_3 = power(3)
power_of_4 = power(4)

print(f"power(2) = {power_of_2}")  # Uses cache
print(f"power(2) = {power_of_2}")  # Computes again

# Closure with nonlocal variable
def make_accumulator():
    """Returns (sum, reset) as closure."""
    total = 0

    def add(n):
        nonlocal total
        total += n

    def reset():
        nonlocal total
        total = 0

    return add, reset, lambda total: total()

accumulator = make_accumulator()
print(f"Accumulator: total={accumulator.add(5)}")
accumulator.add(10)
print(f"Accumulator total={accumulator.get_value()}")

# Closure as class method
class Stateful:
    """Class with closure as method."""
    def __init__(self):
        self._state = []

    def set_state(self, value):
        def inner():
            self._state = value

        self.set_state = inner

    def get_state(self):
        return self._state

stateful = Stateful()
stateful.set_state(42)
print(f"Stateful.state={stateful.get_state()}")

# Stateful with reset
stateful.reset()
print(f"Stateful.state after reset: {stateful.get_state()}")

# Default mutable vs immutable closure
print("\n--- Default mutable vs immutable closure ---")
def make_default_list_mutable(lst=None):
    """Mutable list closure (default mutable [])."""
    lst = [] if lst is None else lst[:]

    def add(item):
        lst.append(item)

    return add, lambda lst=lst

default_mutable = make_default_list_mutable()
default_mutable.add(1)
default_mutable.add(2)
print(f"Mutable closure list: {default_mutable.get_value()}")

def make_default_list_immutable():
    """Immutable list closure (tuple conversion)."""
    return lambda lst: tuple(lst)

default_immutable = make_default_list_immutable()
original_list = [1, 2, 3]
immutable_list = default_immutable(original_list)
print(f"Immutable list: {immutable_list}")
print(f"Original list: {original_list}")  # Original unchanged


# ── Functional Programming Patterns ─────────────

print("\n--- Functional Programming Patterns ---")

# Map, Filter, Reduce are functional patterns
# Already demonstrated above

# Partial application (currying)
def greet_person(greeting):
    """Curry greeting function with person name."""
    def formatted_greeting(name):
        return f"{greeting}, {name}!"

    return formatted_greeting

greet_alice = greet_person("Hello")
greet_bob = greet_person("Hi")
print(f"Greets:")
print(f"  {greet_alice}")
print(f"  {greet_bob}")


# Compose functions
from functools import reduce

def compose(f, g):
    """Composes two functions: f(g(x)) result."""
    return lambda x: g(f(x))

# String reversal pipeline
def to_upper(s):
    return s.upper()

def trim(s):
    return s.strip()

def clean(s):
    return trim(s)

pipeline = compose(to_upper, trim, clean)
print(f"Pipeline result: {pipeline('  hello, world! ')}")


# Lazy evaluation (using generators)
import sys

def infinite_numbers():
    """Infinite generator of numbers."""
    n = 1
    while True:
        yield n
        n += 1

# Take first 10 (lazy, only computes as needed)
print(f"First 10 numbers: {list(take(10, infinite_numbers()))}")


# ── Summary ───────────────────────────────────

print("\n--- Summary ---")
print("Python FP Features:")
print("  - Higher-order functions: map, filter, reduce, sorted")
print("  - Pure functions: no side effects, deterministic")
print("  - Immutability: strings/tuples immutable, use tuples/freeze")
print("  - Lists: mutable, copy on write (new list)")
print("  - Closures: inner functions capture scope")
print("  - Default arguments: *args, **kwargs")
print("  - Nonlocal: inner function scope")
print("  - Function objects: state via closures")
print("  - @lru_cache: memoization pattern")
print("  - Partial application: currying (functools.partial)")
print("  - Compose: function composition pipeline")
print("  - Generators: lazy evaluation (yield, only compute as needed)")
