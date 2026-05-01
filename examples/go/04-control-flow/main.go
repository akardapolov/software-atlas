package main

import (
	"fmt"
)

func classify(n int) string {
	if n < 0 {
		return "negative"
	} else if n == 0 {
		return "zero"
	}
	return "positive"
}

func main() {
	fmt.Println("--- if / else if / else ---")
	for _, n := range []int{-3, 0, 7} {
		fmt.Printf("%d → %s\n", n, classify(n))
	}

	fmt.Println("\n--- if with short statement ---")
	if x := 10; x%2 == 0 {
		fmt.Println("x is even:", x)
	}

	fmt.Println("\n--- switch ---")
	day := "sat"
	switch day {
	case "mon", "tue", "wed", "thu", "fri":
		fmt.Println("weekday")
	case "sat", "sun":
		fmt.Println("weekend")
	default:
		fmt.Println("unknown")
	}

	fmt.Println("\n--- for (classic) ---")
	total := 0
	for i := 1; i <= 5; i++ {
		total += i
	}
	fmt.Println("sum 1..5 =", total)

	fmt.Println("\n--- for (while-style) ---")
	x := 1
	for x < 20 {
		x *= 2
	}
	fmt.Println("first power of two >= 20:", x)

	fmt.Println("\n--- for range (foreach) ---")
	nums := []int{10, 20, 30}
	for i, v := range nums {
		fmt.Printf("nums[%d]=%d\n", i, v)
	}

	fmt.Println("\n--- break / continue ---")
	for i := 1; i < 10; i++ {
		if i%2 == 1 {
			continue
		}
		if i > 6 {
			break
		}
		fmt.Println("even <= 6:", i)
	}

	fmt.Println("\n--- labeled break ---")
outer:
	for i := 1; i <= 3; i++ {
		for j := 1; j <= 3; j++ {
			if i*j > 4 {
				fmt.Println("breaking outer at i,j =", i, j)
				break outer
			}
		}
	}

	fmt.Println("\n--- summary ---")
	fmt.Println("Go control flow: if, switch, for (all loops), break/continue, labels")
}
