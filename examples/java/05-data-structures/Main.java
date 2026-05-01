/*
 * Data Structures in Java
 * =======================
 * Demonstrates arrays, Lists, Maps, Sets, and common operations.
 * Java Collections Framework provides rich data structure implementations.
 */

import java.util.*;

public class DataStructures {

    // ── Arrays (fixed-size sequences) ─────────────────

    static void demonstrateArrays() {
        System.out.println("--- Arrays (fixed size) ---");

        // Declaration and initialization
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("numbers = " + Arrays.toString(numbers));

        // Access
        System.out.println("numbers[0] = " + numbers[0]);      // 10
        System.out.println("numbers[4] = " + numbers[4]);      // 50

        // Array length
        System.out.println("array length = " + numbers.length);

        // Arrays as objects (String array)
        String[] fruits = {"apple", "banana", "cherry"};
        System.out.println("fruits = " + Arrays.toString(fruits));
        System.out.println("fruits[1] = " + fruits[1]);
    }

    // ── ArrayList (dynamic list) ─────────────────────────

    static void demonstrateArrayList() {
        System.out.println("\n--- ArrayList (dynamic list) ---");

        // Creation
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
        System.out.println("fruits = " + fruits);

        // Access
        System.out.println("fruits.get(0) = " + fruits.get(0));      // apple
        System.out.println("fruits.get(2) = " + fruits.get(2));      // cherry

        // Modification
        fruits.add("date");
        System.out.println("After add: " + fruits);

        fruits.add(1, "blueberry");
        System.out.println("After insert at 1: " + fruits);

        fruits.set(1, "coconut");
        System.out.println("After set at 1: " + fruits);

        fruits.remove("banana");
        System.out.println("After remove: " + fruits);

        // Index of element
        System.out.println("indexOf 'date' = " + fruits.indexOf("date"));

        // Contains
        System.out.println("contains 'apple'? " + fruits.contains("apple"));

        // Size
        System.out.println("size = " + fruits.size());

        // Iteration
        System.out.println("Iterate:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }

        // List to array conversion
        String[] array = fruits.toArray(new String[0]);
        System.out.println("as array: " + Arrays.toString(array));
    }

    // ── LinkedList ─────────────────────────────────────────

    static void demonstrateLinkedList() {
        System.out.println("\n--- LinkedList ---");

        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");

        System.out.println("linked list = " + list);

        // Add at beginning
        list.addFirst("zeroth");
        System.out.println("After addFirst: " + list);

        // Add at end
        list.addLast("fourth");
        System.out.println("After addLast: " + list);

        // Remove first/last
        list.removeFirst();
        list.removeLast();
        System.out.println("After removeFirst/Last: " + list);
    }

    // ── HashMap (key-value) ─────────────────────────────

    static void demonstrateHashMap() {
        System.out.println("\n--- HashMap (key-value) ---");

        // Creation
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);

        System.out.println("scores = " + scores);

        // Access
        System.out.println("Alice's score = " + scores.get("Alice"));

        // Default value
        System.out.println("David's score = " + scores.getOrDefault("David", 0));

        // Contains key
        System.out.println("contains 'Bob'? " + scores.containsKey("Bob"));

        // Remove
        scores.remove("Bob");
        System.out.println("After remove Bob: " + scores);

        // Size
        System.out.println("size = " + scores.size());

        // Iteration
        System.out.println("Keys:");
        for (String name : scores.keySet()) {
            System.out.println("  " + name + " -> " + scores.get(name));
        }

        // LinkedHashMap (preserves insertion order)
        Map<String, Integer> orderedScores = new LinkedHashMap<>();
        orderedScores.put("Alice", 95);
        orderedScores.put("Bob", 87);
        System.out.println("LinkedHashMap (ordered): " + orderedScores);
    }

    // ── HashSet (unique elements) ─────────────────────

    static void demonstrateHashSet() {
        System.out.println("\n--- HashSet (unique elements) ---");

        // Creation (duplicates removed)
        Set<String> tags = new HashSet<>(Arrays.asList("python", "typing", "python", "data"));
        System.out.println("tags = " + tags);
        System.out.println("size = " + tags.size());  // 3 (duplicate removed)

        // Contains
        System.out.println("contains 'typing'? " + tags.contains("typing"));

        // Add
        tags.add("algorithms");
        System.out.println("After add 'algorithms': " + tags);

        // Remove
        tags.remove("data");
        System.out.println("After remove 'data': " + tags);

        // Set operations
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> setB = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // Union
        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("union: " + union);

        // Intersection
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("intersection: " + intersection);

        // Difference
        Set<Integer> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        System.out.println("difference A-B: " + difference);
    }

    // ── PriorityQueue ─────────────────────────────────

    static void demonstratePriorityQueue() {
        System.out.println("\n--- PriorityQueue ---");

        // Min heap (smallest has highest priority)
        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.offer("high priority");
        queue.offer("medium");
        queue.offer("low priority");

        System.out.println("queue = " + queue);

        // Poll (remove and return smallest)
        while (!queue.isEmpty()) {
            System.out.println("next: " + queue.poll());
        }

        // Max heap (using reverse comparator)
        PriorityQueue<String> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxQueue.offer("A");
        maxQueue.offer("C");
        maxQueue.offer("B");

        System.out.println("\nmax heap:");
        while (!maxQueue.isEmpty()) {
            System.out.println("next: " + maxQueue.poll());
        }
    }

    // ── Stack (LIFO) ─────────────────────────────────

    static void demonstrateStack() {
        System.out.println("\n--- Stack (LIFO) ---");

        Stack<Integer> stack = new Stack<>();

        // Push
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("stack = " + stack);

        // Peek (view top without removing)
        System.out.println("peek = " + stack.peek());

        // Pop
        System.out.println("popped: " + stack.pop());
        System.out.println("stack after pop: " + stack);

        // Search
        System.out.println("search for 1: " + stack.search(1));  // Index from top
    }

    // ── Queue (FIFO) ─────────────────────────────────

    static void demonstrateQueue() {
        System.out.println("\n--- Queue (FIFO) ---");

        Queue<Integer> queue = new LinkedList<>();

        // Enqueue (offer - returns boolean, add - throws)
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println("queue = " + queue);

        // Peek (view front)
        System.out.println("peek = " + queue.peek());

        // Dequeue (poll)
        System.out.println("dequed: " + queue.poll());
        System.out.println("queue after poll: " + queue);
    }

    // ── Deque (double-ended) ─────────────────────

    static void demonstrateDeque() {
        System.out.println("\n--- Deque (double-ended) ---");

        Deque<Integer> deque = new ArrayDeque<>();

        // Add to front
        deque.addFirst(1);
        System.out.println("After addFirst(1): " + deque);

        // Add to back
        deque.addLast(2);
        System.out.println("After addLast(2): " + deque);

        // Remove from front
        deque.removeFirst();
        System.out.println("After removeFirst: " + deque);

        // Remove from back
        deque.removeLast();
        System.out.println("After removeLast: " + deque);
    }

    // ── TreeSet (sorted unique elements) ───────────

    static void demonstrateTreeSet() {
        System.out.println("\n--- TreeSet (sorted) ---");

        // Sorted, unique elements
        Set<Integer> numbers = new TreeSet<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7, 6, 4));
        System.out.println("TreeSet = " + numbers);  // Already sorted!

        // Range operations
        System.out.println("headSet(5): " + numbers.headSet(5));
        System.out.println("tailSet(5): " + numbers.tailSet(5));
        System.out.println("subSet(3, 8): " + numbers.subSet(3, 8));

        // First and last
        System.out.println("first: " + numbers.first());
        System.out.println("last: " + numbers.last());
    }

    // ── Custom Class (Point example) ─────────────────

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static void demonstrateCustomClass() {
        System.out.println("\n--- Custom Class ---");

        Point p = new Point(10, 20);
        System.out.println("Point: " + p);

        // Array of custom objects
        Point[] points = {new Point(10, 20), new Point(30, 40), new Point(50, 60)};
        System.out.println("points: " + Arrays.toString(points));
    }

    // ── Records (Java 14+) ─────────────────────────

    // Record classes are immutable data carriers

    static record PersonRecord(String name, int age) {}

    static void demonstrateRecord() {
        System.out.println("\n--- Record (Java 14+) ---");

        PersonRecord ada = new PersonRecord("Ada", 36);
        System.out.println("PersonRecord: " + ada);

        // Accessors auto-generated
        System.out.println("name: " + ada.name());
        System.out.println("age: " + ada.age());
    }

    // ── Summary ───────────────────────────────────────────

    public static void main(String[] args) {
        System.out.println("Data Structures in Java");
        System.out.println("========================");

        demonstrateArrays();
        demonstrateArrayList();
        demonstrateLinkedList();
        demonstrateHashMap();
        demonstrateHashSet();
        demonstratePriorityQueue();
        demonstrateStack();
        demonstrateQueue();
        demonstrateDeque();
        demonstrateTreeSet();
        demonstrateCustomClass();
        demonstrateRecord();

        System.out.println("\n--- Summary ---");
        System.out.println("Java Collections Framework:");
        System.out.println("  - List: ArrayList, LinkedList (ordered sequences)");
        System.out.println("  - Set: HashSet, TreeSet (unique elements)");
        System.out.println("  - Map: HashMap, TreeMap (key-value pairs)");
        System.out.println("  - Queue: LinkedList as Queue (FIFO)");
        System.out.println("  - Stack: Stack class (LIFO)");
        System.out.println("  - Deque: ArrayDeque (double-ended)");
        System.out.println("  - PriorityQueue: heap-based priority queue");
        System.out.println("  - Generics: type-safe collections");
        System.out.println("  - Records: immutable data carriers (Java 14+)");
    }
}
