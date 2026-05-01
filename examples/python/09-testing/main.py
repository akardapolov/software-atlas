# Testing in Python
# =====================
# unittest, pytest, doctest

import unittest
import pytest


# ── Basic unittest test ───────────────────────────

class TestBasic(unittest.TestCase):

    def test_addition(self):
        self.assertEqual(5 + 3, 8)

    def test_string_operations(self):
        s = "hello"
        self.assertEqual(len(s), 5)
        self.assertEqual(s.upper(), "HELLO")

    def test_list_operations(self):
        nums = [1, 2, 3]
        self.assertEqual(sum(nums), 6)
        self.assertEqual(len(nums), 3)


# ── Exception testing ──────────────────────────────

class TestExceptions(unittest.TestCase):

    def test_division_by_zero(self):
        with self.assertRaises(ZeroDivisionError):
            _ = 10 / 0

    def test_key_error(self):
        d = {"a": 1}
        with self.assertRaises(KeyError):
            _ = d["b"]


# ── Fixtures ──────────────────────────────────────

class TestFixtures(unittest.TestCase):

    def setUp(self):
        self.data = [1, 2, 3, 4, 5]

    def tearDown(self):
        self.data = []

    def test_with_fixture(self):
        self.assertEqual(sum(self.data), 15)


# ── Parameterized test (pytest) ──────────────────────

@pytest.mark.parametrize("input,expected", [
    (1, 1),
    (2, 4),
    (3, 9),
    (-5, 25),
])
def test_squares(input, expected):
    assert input * input == expected


# ── Pytest fixtures ─────────────────────────────────

@pytest.fixture
def sample_data():
    return {"name": "Alice", "age": 30}


def test_with_fixture(sample_data):
    assert sample_data["name"] == "Alice"
    assert sample_data["age"] == 30


# ── Mocking example ─────────────────────────────────

from unittest.mock import Mock, patch


class TestMocking(unittest.TestCase):

    def test_mock_function(self):
        mock_func = Mock(return_value=42)
        result = mock_func()
        self.assertEqual(result, 42)
        mock_func.assert_called_once()

    @patch("builtins.print")
    def test_mock_print(self, mock_print):
        print("test message")
        mock_print.assert_called_with("test message")


# ── Doctest ───────────────────────────────────────

def multiply(a, b):
    """
    Multiply two numbers.

    >>> multiply(2, 3)
    6
    >>> multiply(-2, 3)
    -6
    >>> multiply(0, 5)
    0
    """
    return a * b


# ── Summary test ─────────────────────────────────────

class TestSummary(unittest.TestCase):

    def test_python_testing_features(self):
        self.assertTrue(True)  # Always passes
        self.assertFalse(False)


# ── Running tests ───────────────────────────────────
# Run with:
# python -m unittest
# pytest
# pytest --cov=mymodule --cov-report=html
# python -m doctest main.py -v
