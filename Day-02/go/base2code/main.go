package main

func main() {
	instructionArray, err := readLines("input-day-2")
	if err != nil {
		panic(err)
	}

	depth := 0
	horizontal := 0

	for i := 1; i < len(instructionArray); i++ {
		lineSplit := strings.Split(instructionArray, " ")
		instruction := lineSplit[0]
		value, _ := strconv.Atoi(lineSplit[1])
		if instruction == "forward" {
			horizontal += value
		} else if instruction == "up" {
			depth += value
		} else if instruction == "down" {
			depth -= value
		}
	}
}

// readLines reads a whole file into memory
func readLines(path string) ([]string, error) {
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

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}