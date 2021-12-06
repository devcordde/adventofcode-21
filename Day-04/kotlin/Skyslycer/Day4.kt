import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val instructions = Files.readAllLines(Paths.get("./inputs/day4/input.txt")).filter { it.isNotBlank() }
    val winnerNumbers = instructions[0].split(",").map { it.toInt() }
    val boards = mutableListOf<Board>()
    val winningBoards = mutableMapOf<Board, Int>()

    var first = false

    var board: Board? = null
    var counter = 0

    instructions.subList(1, instructions.size).forEach {
        if (board == null) {
            board = Board(arrayOf(arrayOf(), arrayOf(), arrayOf(), arrayOf(), arrayOf()))
        }

        board!!.numbers[counter] = matchNumbers(it).toTypedArray()

        counter++

        if (counter == 5) {
            counter = 0
            boards.add(board!!)
            board = null
        }
    }

    winnerNumbers.forEach outerForEach@ { number ->
        boards.forEach { loopBoard ->
            val uncalled = mutableListOf<Number>()

            loopBoard.numbers.forEach { arrayOfNumbers ->
                arrayOfNumbers.forEach {
                    if (it.value == number || it.called) {
                        it.called = true
                        uncalled.remove(it)
                    } else {
                        uncalled.add(it)
                    }
                }
            }

            if (checkBoard(loopBoard)) {
                if (winningBoards.containsKey(loopBoard)) {
                    return@forEach
                }

                val sum = if (uncalled.isEmpty()) {
                    0
                } else {
                    uncalled.map { it.value }.reduce { acc, number -> acc + number }
                }

                if (!first) {
                    println("First solution: ${sum * number}")
                    first = true
                }

                winningBoards[loopBoard] = sum * number

                return@forEach
            }
        }
    }

    println("Second solution: ${winningBoards.values.last()}")
}

fun matchNumbers(numbers: String): List<Number> {
    var subString = 0

    if (numbers.startsWith(" ")) {
        subString = 1
    }

    return numbers.substring(subString).split(" ").filter { it != " " && it != "" }.map { it.toInt() }.map { Number(false, it) }
}

fun checkBoard(board: Board): Boolean {
    board.numbers.forEach { row ->
        if (row.filter { it.called }.size == 5) {
            return true
        }
    }

    repeat(5) { outerIndex ->
        val column = mutableListOf<Number>()

        repeat(5) { innerIndex ->
            column.add(board.numbers[innerIndex][outerIndex])
        }

        if (column.filter { it.called }.size == 5) {
            return true
        }
    }

    return false
}

class Board(val numbers: Array<Array<Number>>)

class Number(var called: Boolean, val value: Int)