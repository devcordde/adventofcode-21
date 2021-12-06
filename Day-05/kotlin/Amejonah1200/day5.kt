import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int)
class Line(val start: Point, val end: Point) {
  fun points(): List<Point> {
    val (x1, y1) = start
    val (x2, y2) = end
    return if (y1 == y2) {
      (if (x1 < x2) x1..x2 else x1 downTo x2).map { Point(it, y1) }
    } else if (x1 == x2) {
      (if (y1 < y2) y1..y2 else y1 downTo y2).map { Point(x1, it) }
    } else (0..(x2 - x1).absoluteValue).map { Point(if (x1 < x2) x1 + it else x1 - it, if (y1 < y2) y1 + it else y1 - it) }

  }

  override fun toString(): String {
    return "${start.x},${start.y} -> ${end.x},${end.y}"
  }

  fun isVertical() = start.x == end.x
  fun isHorizontal() = start.y == end.y
}

fun main() {
  val lines = Regex("""(\d+),(\d+) -> (\d+),(\d+)""").findAll(
    Path.of("./inputs/day5.txt").readText()
  ).map { it.destructured }.map { (x, y, x2, y2) -> Line(Point(x.toInt(), y.toInt()), Point(x2.toInt(), y2.toInt())) }.toList()
  println("Part 1: ${part1(lines)}")
  println("Part 2: ${part2(lines)}")
}

fun part1(lines: List<Line>) = heatMap(lines.filter { it.isVertical() || it.isHorizontal() }).filter { it.value >= 2 }.count()
fun part2(lines: List<Line>) = heatMap(lines).filter { it.value >= 2 }.count()

fun heatMap(lines: List<Line>): Map<Point, Int> {
  val points = mutableMapOf<Point, Int>()
  for (line in lines) {
    line.points().forEach { point ->
      points[point] = points.getOrDefault(point, 0) + 1
    }
  }
  return points
}
