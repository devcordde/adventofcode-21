import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries

fun main() {
  val measurements = readInput("day1").map { it.toInt() }
  println("First Answer: ${part1(measurements)}")
  println("Second Answer: ${part2(measurements)}")

}

fun part2(measurements: List<Int>): Int = part1(measurements.windowed(3, 1).map { it.sum() })

fun part1(measurements: List<Int>) = measurements.windowed(2, 1)
  .count { window -> window.first() < window.last() }
