package de.dertev

fun day1() {
    //Part 1
    var firstNumber = true
    var currentDeep = 0
    var points = 0

    inputFile.forEachLine {
        if (!firstNumber) {
            if (it.toInt() > currentDeep) {
                points += 1
            }
        } else {
            firstNumber = false
        }
        currentDeep = it.toInt()
    }

    println("Part 1: $points")

    //Part 2
    val window = inputFile.readLines().windowed(size = 3)
    val sumList = mutableListOf<Int>()
    firstNumber = true
    currentDeep = 0
    points = 0

    window.forEach { thisWindow ->
        sumList.add(thisWindow.sumOf { it.toInt() })
    }

    sumList.forEach {
        if (!firstNumber) {
            if (it > currentDeep) {
                points += 1
            }
        } else {
            firstNumber = false
        }
        currentDeep = it
    }

    println("Part 2: $points")
}