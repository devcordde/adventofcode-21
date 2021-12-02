import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val instructions = Files.readAllLines(Paths.get("./inputs/day2/input.txt"))

    var position = 0
    var depth = 0

    // First part
    instructions.map { it.split(" ") }.forEach {
        when (it[0]) {
            "forward" -> position += it[1].toInt()
            "up" -> depth -= it[1].toInt()
            "down" -> depth += it[1].toInt()
        }
    }

    println("First solution: ${position * depth}")

    // Second part
    var aim = 0
    position = 0
    depth = 0

    instructions.map { it.split(" ") }.forEach {
        when (it[0]) {
            "forward" -> {
                position += it[1].toInt()
                depth += aim * it[1].toInt()
            }
            "up" -> aim -= it[1].toInt()
            "down" -> aim += it[1].toInt()
        }
    }

    println("Second solution: ${position * depth}")
}