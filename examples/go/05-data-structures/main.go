/*
Data Structures in Go
======================
Demonstrates slices, maps, structs, and common operations.
Go provides built-in collections with efficient implementations.
*/

package main

import (
	"fmt"
	"sort"
)

// ── Slices (dynamic arrays) ─────────────────────

func demonstrateSlices() {
	fmt.Println("--- Slices (dynamic arrays) ---")

	// Creation
	fruits := []string{"apple", "banana", "cherry"}
	fmt.Printf("fruits = %v\n", fruits)

	// Indexing
	fmt.Printf("fruits[0] = %s\n", fruits[0])   // apple
	fmt.Printf("fruits[-1] = %s\n", fruits[len(fruits)-1]) // cherry

	// Slicing (creates new slice)
	fmt.Printf("fruits[1:3] = %v\n", fruits[1:3]) // [banana cherry]

	// Append
	fruits = append(fruits, "date")
	fmt.Printf("After append: %v\n", fruits)

	// Prepend
	fruits = append([]string{"blueberry"}, fruits...)
	fmt.Printf("After prepend: %v\n", fruits)

	// Copy
	moreFruits := make([]string, len(fruits))
	copy(moreFruits, fruits)
	fmt.Printf("copied: %v\n", moreFruits)

	// Slice literal
	squares := []int{i * i for i := 1; i <= 10}
	fmt.Printf("squares (1-10): %v\n", squares)

	evens := []int{}
	for i := 1; i <= 20; i++ {
		if i%2 == 0 {
			evens = append(evens, i)
		}
	}
	fmt.Printf("evens (1-20): %v\n", evens)
}

// ── Maps (key-value) ─────────────────────────────

func demonstrateMaps() {
	fmt.Println("\n--- Maps (key-value) ---")

	// Creation
	person := map[string]interface{}{
		"name":  "Ada",
		"age":   36,
		"city":  "London",
	}
	fmt.Printf("person = %v\n", person)

	// Access
	fmt.Printf("name = %s\n", person["name"])
	fmt.Printf("age = %d\n", person["age"])

	// Check existence
	fmt.Printf("has 'email'? %t\n", person["email"] != nil)

	// Modification
	person["email"] = "ada@example.com"
	fmt.Printf("After adding email: %v\n", person)

	// Deletion
	delete(person, "city")
	fmt.Printf("After deleting city: %v\n", person)

	// Iteration
	fmt.Println("Keys and values:")
	for key, value := range person {
		fmt.Printf("  %s: %v\n", key, value)
	}

	// Map literal
	squaresMap := map[int]int{}
	for i := 1; i <= 5; i++ {
		squaresMap[i] = i * i
	}
	fmt.Printf("squaresMap = %v\n", squaresMap)
}

// ── Structs (custom types) ─────────────────────

type Point struct {
	X, Y int
}

type Person struct {
	Name string
	Age  int
	City string
}

func demonstrateStructs() {
	fmt.Println("\n--- Structs ---")

	// Declaration and initialization
	p1 := Point{X: 10, Y: 20}
	fmt.Printf("Point: X=%d, Y=%d\n", p1.X, p1.Y)

	// Field names can be omitted (positional)
	p2 := Point{10, 20}
	fmt.Printf("Point2: X=%d, Y=%d\n", p2.X, p2.Y)

	// Zero values (default initialization)
	var p3 Point
	fmt.Printf("Zero Point: X=%d, Y=%d\n", p3.X, p3.Y)

	// Array of structs
	points := []Point{{10, 20}, {30, 40}, {50, 60}}
	fmt.Printf("points[1] = (%d, %d)\n", points[1].X, points[1].Y)

	// Pointer to struct
	pPtr := &p1
	fmt.Printf("Via pointer: X=%d, Y=%d\n", pPtr.X, pPtr.Y)

	// Struct with methods (receiver)
	ada := Person{Name: "Ada", Age: 36}
	fmt.Printf("Person: Name=%s, Age=%d\n", ada.Name, ada.Age)
}

// ── Stacks (using slices) ─────────────────────

type Stack []int

func (s *Stack) Push(v int) {
	*s = append(*s, v)
}

func (s *Stack) Pop() (int, bool) {
	if len(*s) == 0 {
		return 0, false
	}
	index := len(*s) - 1
	v := (*s)[index]
	*s = (*s)[:index]
	return v, true
}

func demonstrateStack() {
	fmt.Println("\n--- Stack (LIFO) ---")

	var stack Stack
	stack.Push(1)
	stack.Push(2)
	stack.Push(3)

	fmt.Printf("stack: %v\n", []int(stack))

	if v, ok := stack.Pop(); ok {
		fmt.Printf("popped: %d, stack now: %v\n", v, []int(stack))
	}
}

// ── Queues (using slices) ─────────────────────

type Queue []int

func (q *Queue) Enqueue(v int) {
	*q = append(*q, v)
}

func (q *Queue) Dequeue() (int, bool) {
	if len(*q) == 0 {
		return 0, false
	}
	v := (*q)[0]
	*q = (*q)[1:]
	return v, true
}

func demonstrateQueue() {
	fmt.Println("\n--- Queue (FIFO) ---")

	var queue Queue
	queue.Enqueue(10)
	queue.Enqueue(20)
	queue.Enqueue(30)

	fmt.Printf("queue: %v\n", []int(queue))

	if v, ok := queue.Dequeue(); ok {
		fmt.Printf("dequed: %d, queue now: %v\n", v, []int(queue))
	}
}

// ── Sets (using maps with bool values) ─────────

type Set map[string]struct{}

func (s *Set) Add(v string) {
	(*s)[v] = struct{}{}
}

func (s *Set) Contains(v string) bool {
	_, exists := (*s)[v]
	return exists
}

func demonstrateSet() {
	fmt.Println("\n--- Sets (unique elements) ---")

	tags := Set{"python": {}, "typing": {}}
	tags.Add("algorithms")
	tags.Add("data")
	fmt.Printf("tags = %v\n", tags)

	fmt.Printf("has 'typing'? %t\n", tags.Contains("typing"))
	fmt.Printf("has 'go'? %t\n", tags.Contains("go"))
}

// ── Heaps (priority queues) ─────────────────────

// Go has heap algorithm in container/heap package

import "container/heap"

type Task struct {
	Priority int
	Name     string
}

// TaskHeap implements heap.Interface
type TaskHeap []Task

func (h TaskHeap) Len() int           { return len(*h) }
func (h TaskHeap) Less(i, j int) bool { return (*h)[i].Priority < (*h)[j].Priority }
func (h TaskHeap) Swap(i, j int)      { (*h)[i], (*h)[j] = (*h)[j], (*h)[i] }
func (h *TaskHeap) Push(x Task)       { *h = append(*h, x) }
func (h *TaskHeap) Pop() Task {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func demonstrateHeap() {
	fmt.Println("\n--- Heap (priority queue) ---")

	tasks := &TaskHeap{
		{Priority: 3, Name: "high"},
		{Priority: 1, Name: "urgent"},
		{Priority: 2, Name: "medium"},
	}
	heap.Init(tasks)

	fmt.Printf("tasks: %v\n", *tasks)

	priority, task := heap.Pop(tasks)
	fmt.Printf("next task: %s (priority %d)\n", task.Name, priority)

	priority, task = heap.Pop(tasks)
	fmt.Printf("next task: %s (priority %d)\n", task.Name, priority)
}

// ── Linked Lists ─────────────────────────────────

type Node struct {
	Data int
	Next *Node
}

type LinkedList struct {
	Head *Node
}

func (ll *LinkedList) Append(data int) {
	newNode := &Node{Data: data, Next: nil}

	if ll.Head == nil {
		ll.Head = newNode
		return
	}

	current := ll.Head
	for current.Next != nil {
		current = current.Next
	}
	current.Next = newNode
}

func (ll *LinkedList) ToSlice() []int {
	result := []int{}
	current := ll.Head
	for current != nil {
		result = append(result, current.Data)
		current = current.Next
	}
	return result
}

func demonstrateLinkedList() {
	fmt.Println("\n--- Linked List ---")

	ll := LinkedList{}
	ll.Append(10)
	ll.Append(20)
	ll.Append(30)

	fmt.Printf("linked list: %v\n", ll.ToSlice())
}

// ── Binary Search Tree ─────────────────────────────

type TreeNode struct {
	Key   int
	Left  *TreeNode
	Right *TreeNode
}

type BST struct {
	Root *TreeNode
}

func (bst *BST) Insert(key int) {
	if bst.Root == nil {
		bst.Root = &TreeNode{Key: key}
		return
	}
	bst.insert(bst.Root, key)
}

func (bst *BST) insert(node *TreeNode, key int) {
	if key < node.Key {
		if node.Left == nil {
			node.Left = &TreeNode{Key: key}
			return
		}
		bst.insert(node.Left, key)
	} else {
		if node.Right == nil {
			node.Right = &TreeNode{Key: key}
			return
		}
		bst.insert(node.Right, key)
	}
}

func (bst *BST) Inorder() []int {
	result := []int{}
	bst.inorder(bst.Root, &result)
	return result
}

func (bst *BST) inorder(node *TreeNode, result *[]int) {
	if node == nil {
		return
	}
	bst.inorder(node.Left, result)
	*result = append(*result, node.Key)
	bst.inorder(node.Right, result)
}

func demonstrateBST() {
	fmt.Println("\n--- Binary Search Tree ---")

	bst := BST{}
	keys := []int{50, 30, 70, 20, 40, 60, 80}
	for _, key := range keys {
		bst.Insert(key)
	}

	fmt.Printf("inorder traversal: %v\n", bst.Inorder())
}

// ── Summary ───────────────────────────────────────────

func main() {
	fmt.Println("Data Structures in Go")
	fmt.Println("======================")

	demonstrateSlices()
	demonstrateMaps()
	demonstrateStructs()
	demonstrateStack()
	demonstrateQueue()
	demonstrateSet()
	demonstrateHeap()
	demonstrateLinkedList()
	demonstrateBST()

	fmt.Println("\n--- Summary ---")
	fmt.Println("Go data structures:")
	fmt.Println("  - Slices: dynamic arrays with automatic resizing")
	fmt.Println("  - Maps: hash tables for key-value pairs")
	fmt.Println("  - Structs: custom data types with named fields")
	fmt.Println("  - Pointers: explicit memory references")
	fmt.Println("  - Built-in: slices, maps, channels for concurrency")
	fmt.Println("  - container/heap: priority queue implementation")
	fmt.Println("  - make(): efficient pre-allocation of slices and maps")
}
