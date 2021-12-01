package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	numberArray, err := readLines("input-day-1")
	if err != nil {
		panic(err)
	}

	increased := 0

	for i := 1; i < len(numberArray); i++ {
		currentDepth := numberArray[i]
		previousDepth := numberArray[i-1]
		if currentDepth > previousDepth {
			increased++
		}
	}

	fmt.Println("Part 1: " + fmt.Sprint(increased))

	// Reset previous variables
	increased = 0
	for i := 2; i < len(numberArray)-1; i++ {
		currentDepthSum := numberArray[i-1] + numberArray[i] + numberArray[i+1]
		previousDepthSum := numberArray[i-2] + numberArray[i-1] + numberArray[i]
		if currentDepthSum > previousDepthSum {
			increased++
		}
	}

	fmt.Println("Part 2: " + fmt.Sprint(increased))
}

// readLines reads a whole file into memory
func readLines(path string) ([]int, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer func(file *os.File) {
		err := file.Close()
		if err != nil {
			panic(err)
		}
	}(file)

	var lines []int
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		number, _ := strconv.Atoi(scanner.Text())
		lines = append(lines, number)
	}
	return lines, scanner.Err()
}
