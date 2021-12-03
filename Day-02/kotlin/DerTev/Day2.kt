package de.dertev

fun day2() {
    //Part 1
    var horizontal = 0
    var depth = 0

    inputFile.forEachLine { currentCommand ->
        val currentNumber = currentCommand.split(" ")[1].toInt()

        when (currentCommand.dropLast(2)) {
            "forward" -> horizontal += currentNumber
            "down" -> depth += currentNumber
            "up" -> depth -= currentNumber
        }
    }

    println("Part 1: ${horizontal*depth}")

    //Part 2
    var aim = 0
    depth = 0
    horizontal = 0

    inputFile.forEachLine { currentCommand ->
        val currentNumber = currentCommand.split(" ")[1].toInt()

        when (currentCommand.dropLast(2)) {
            "forward" -> {
                horizontal += currentNumber
                depth += aim*currentNumber
            }
            "down" -> aim += currentNumber
            "up" -> aim -= currentNumber
        }
    }

    println("Part 2: ${horizontal*depth}")
}