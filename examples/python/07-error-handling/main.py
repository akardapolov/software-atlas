"""
Error Handling in Python
========================
Demonstrates exceptions, try/except, context managers, logging,
and assertion validation.
"""

# ── Exceptions ───────────────────────────

print("--- Exceptions ---")

# Built-in exceptions
try:
    result = 10 / 0  # ZeroDivisionError
except ZeroDivisionError:
    print(f"Caught ZeroDivisionError: {result}")

# Custom exceptions
class InsufficientFundsError(Exception):
    """Raised when not enough money."""

    def __init__(self, amount: float, balance: float):
        self.amount = amount
        self.balance = balance

    def __str__(self) -> str:
        return f"InsufficientFunds: {self.amount} (balance={self.balance:.2f})"


# Raise custom exception
raise InsufficientFundsError(50.0)

# Catch and reraise
try:
    raise InsufficientFundsError(100.0)
except InsufficientFundsError as e:
    print(f"Caught and re-raised: {e}")


# Exception hierarchy
class ApplicationError(Exception):
    """Base exception for application errors."""

class DatabaseError(Exception):
    """Base exception for database errors."""

class NetworkError(Exception):
    """Base exception for network errors."""


# ── Try/Except ───────────────────────────

print("\n--- Try/Except ---")

# Basic usage
try:
    result = int("not a number")
except ValueError as e:
    print(f"Caught ValueError: {e}")
except (TypeError, ValueError) as e:
    print(f"Caught multiple exceptions: {e}")


# As clause (catch multiple exception types)
try:
    data = {}
    key = "nonexistent"
    if key not in data:
        raise KeyError(f"Key '{key}' not found")
except KeyError:
    data[key] = "value"
    print(f"Accessing key '{key}': {data[key]}")


# Finally clause (always runs)
resource = None

try:
    resource = open("nonexistent.txt")
    result = resource.read()
except FileNotFoundError as e:
    print(f"File not found: {e}")
finally:
    if resource:
        resource.close()
    print("Resource closed in finally")


# ── Context Managers ─────────────────────

print("\n--- Context Managers ---")

# Suppress context with contextlib
from contextlib import suppress

try:
    with suppress(ValueError, "Expected value"):
        result = int("invalid")
except ValueError as e:
    print(f"ValueError suppressed")
print(f"Result: {result}")  # Should not print


# ── Logging ────────────────────────────

import logging

logging.basicConfig(level=logging.INFO)

logger = logging.getLogger(__name__)

logger.info("Info message")
logger.warning("Warning message")
logger.error("Error message")

logger.debug("Debug message")


# ── Assertions ─────────────────────

print("\n--- Assertions ---")

# Simple assert
value = 42
assert value > 0, f"Value must be positive"
print(f"Assertion passed")

# With message
assert value > 0, f"Value must be positive, not zero"

# AssertionError for logical checks
name = "Alice"
assert len(name) >= 2, f"Name too short"
assert isinstance(name, str), f"Name must be a string"


# ── Summary ───────────────────────────

print("\n--- Summary ---")
print("Python error handling:")
print("  - Built-in exceptions: Exception, ValueError, etc.")
print("  - Custom exceptions: inherit from Exception")
print("  - Try/except/else/finally for error handling")
print("  - Exceptions can be chained (as or)")
print("  - Finally clause always executes")
print("  - Context managers: suppress context in blocks")
print("  - Logging: standard library for structured output")
print("  - Assertions: assert for validation and invariants")
