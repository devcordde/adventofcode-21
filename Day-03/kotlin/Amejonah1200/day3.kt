import kotlin.math.roundToInt

fun main() {
  val input = readInput("day3")
  println("Part 1: ${part1(input)}")
  println("Part 2: ${part2(input)}")
}

fun part1(input: List<String>): Int {
  val count = input.fold(IntArray(12)) { acc, line ->
    line.trim().forEachIndexed { index, c -> if (c == '1') acc[index]++ }
    acc
  }
  val gamma = count.map { if (it > input.size / 2) 1 else 0 }.joinToString("").toInt(2)
  val epsilon = gamma.inv() and 0b111111111111
  return gamma * epsilon
}

fun part2(input: List<String>): Int {
  val oxy = part2(0, input, '1') { count, size -> count >= (size / 2.0).roundToInt() }.first().toInt(2)
  val co2 = part2(0, input, '0') { count, size -> count > (size / 2) }.first().toInt(2)
  return oxy * co2
}

fun part2(pos: Int, input: List<String>, c: Char, criteria: (Int, Int) -> Boolean): List<String> {
  if (input.size <= 1 || pos > 11) return input
  return part2(pos + 1,
    (if (criteria(input.count { it[pos] == c }, input.size)) '1' else '0').let { c -> input.filter { it[pos] == c } },
    c, criteria
  )
}
