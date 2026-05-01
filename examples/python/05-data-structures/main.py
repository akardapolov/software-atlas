"""
Data Structures in Python
========================
Demonstrates lists, tuples, dictionaries, sets, and common operations.
"""

# ── Lists ─────────────────────────────────────────────

print("--- Lists (mutable, ordered sequences) ---")

# Creation
fruits = ["apple", "banana", "cherry"]
print(f"fruits = {fruits}")

# Indexing and slicing
print(f"fruits[0] = {fruits[0]}")      # apple
print(f"fruits[-1] = {fruits[-1]}")     # cherry
print(f"fruits[1:3] = {fruits[1:3]}")    # ['banana', 'cherry']

# Modification
fruits.append("date")
print(f"After append: {fruits}")

fruits.insert(1, "blueberry")
print(f"After insert at index 1: {fruits}")

fruits.remove("banana")
print(f"After remove 'banana': {fruits}")

fruits[1] = "coconut"
print(f"After assignment to index 1: {fruits}")

# List comprehension (Pythonic way to create lists)
squares = [i * i for i in range(1, 11)]
print(f"squares (1-10): {squares}")

evens = [i for i in range(1, 21) if i % 2 == 0]
print(f"evens (1-20): {evens}")


# ── Tuples ─────────────────────────────────────────────

print("\n--- Tuples (immutable, ordered sequences) ---")

# Creation
point = (10, 20)
print(f"point = {point}")

# Tuples are immutable
# point[0] = 30  # TypeError!

# Tuple unpacking
x, y = point
print(f"x = {x}, y = {y}")

# Named tuples (from namedtuple)
from collections import namedtuple

Person = namedtuple("Person", ["name", "age"])
ada = Person("Ada", 36)
print(f"Person: name={ada.name}, age={ada.age}")


# ── Dictionaries ───────────────────────────────────────

print("\n--- Dictionaries (key-value mappings) ---")

# Creation
person = {"name": "Ada", "age": 36, "city": "London"}
print(f"person = {person}")

# Access
print(f"name = {person['name']}")
print(f"age = {person.get('age', 'unknown')}")  # get with default

# Modification
person["email"] = "ada@example.com"
print(f"After adding email: {person}")

del person["city"]
print(f"After deleting city: {person}")

# Iteration
print("Keys and values:")
for key, value in person.items():
    print(f"  {key}: {value}")

# Dictionary comprehension
squares_dict = {i: i * i for i in range(1, 6)}
print(f"squares_dict = {squares_dict}")


# ── Sets ────────────────────────────────────────────

print("\n--- Sets (unordered, unique elements) ---")

# Creation
tags = {"python", "typing", "python", "data"}  # duplicate removed
print(f"tags = {tags}")

# Set operations
colors_a = {"red", "green", "blue"}
colors_b = {"green", "yellow", "purple"}

print(f"colors_a = {colors_a}")
print(f"colors_b = {colors_b}")

# Union (elements in either)
print(f"union: {colors_a | colors_b}")

# Intersection (elements in both)
print(f"intersection: {colors_a & colors_b}")

# Difference (elements in A but not B)
print(f"difference A-B: {colors_a - colors_b}")

# Set methods
tags.add("algorithms")
print(f"After add: {tags}")

tags.remove("data")
print(f"After remove 'data': {tags}")


# ── Stacks (using lists) ────────────────────────────

print("\n--- Stacks (LIFO) ---")

stack = []

# Push
stack.append(1)
stack.append(2)
stack.append(3)
print(f"push 1, 2, 3: {stack}")

# Pop
top = stack.pop()
print(f"popped: {top}, stack now: {stack}")


# ── Queues (using deque) ─────────────────────────

print("\n--- Queues (FIFO) ---")

from collections import deque

queue = deque()

# Enqueue
queue.append("first")
queue.append("second")
queue.append("third")
print(f"enqueue: {list(queue)}")

# Dequeue
first = queue.popleft()
print(f"dequeued: {first}, queue now: {list(queue)}")


# ── Heaps (priority queues) ─────────────────────

print("\n--- Heaps (priority queues) ---")

import heapq

tasks = [(3, "high"), (1, "urgent"), (2, "medium")]

# Build heap (heapify in-place)
heapq.heapify(tasks)
print(f"heap: {tasks}")

# Pop smallest
priority, task = heapq.heappop(tasks)
print(f"next task: {task} (priority {priority})")


# ── Linked Lists (demonstration) ─────────────

print("\n--- Linked List (class implementation) ---")

class Node:
    """A node in a singly linked list."""
    def __init__(self, data):
        self.data = data
        self.next = None


class LinkedList:
    """A simple singly linked list."""
    def __init__(self):
        self.head = None

    def append(self, data):
        """Add node to end of list."""
        new_node = Node(data)
        if not self.head:
            self.head = new_node
            return

        current = self.head
        while current.next:
            current = current.next
        current.next = new_node

    def to_list(self):
        """Convert to Python list for display."""
        result = []
        current = self.head
        while current:
            result.append(current.data)
            current = current.next
        return result

    def display(self):
        """Print all elements."""
        print(self.to_list())


linked_list = LinkedList()
linked_list.append("first")
linked_list.append("second")
linked_list.append("third")

print("Linked list:")
linked_list.display()


# ── Binary Tree (demonstration) ────────────

print("\n--- Binary Search Tree ---")

class TreeNode:
    """A node in a binary search tree."""
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


class BST:
    """Binary Search Tree."""
    def __init__(self):
        self.root = None

    def insert(self, key):
        """Insert a key into BST."""
        if not self.root:
            self.root = TreeNode(key)
            return

        self._insert_recursive(self.root, key)

    def _insert_recursive(self, node, key):
        if key < node.key:
            if node.left is None:
                node.left = TreeNode(key)
            else:
                self._insert_recursive(node.left, key)
        else:
            if node.right is None:
                node.right = TreeNode(key)
            else:
                self._insert_recursive(node.right, key)

    def inorder(self):
        """Return keys in sorted order."""
        result = []
        self._inorder_recursive(self.root, result)
        return result

    def _inorder_recursive(self, node, result):
        if node:
            self._inorder_recursive(node.left, result)
            result.append(node.key)
            self._inorder_recursive(node.right, result)

    def display(self):
        """Print BST."""
        print(f"inorder: {self.inorder()}")


bst = BST()
for key in [50, 30, 70, 20, 40, 60, 80]:
    bst.insert(key)

print("BST:")
bst.display()


# ── Summary ───────────────────────────────────────────

print("\n--- Summary ---")
print("Python data structures:")
print("  - List: mutable, ordered, dynamic size")
print("  - Tuple: immutable, ordered, fixed size")
print("  - Dict: key-value mapping, hash table")
print("  - Set: unordered, unique elements")
print("  - Deque: efficient appends/pops from both ends")
print("  - Heap: priority queue (min/max heap)")
print("  - List comprehensions: concise list creation")
print("  - Dictionary comprehensions: concise dict creation")
