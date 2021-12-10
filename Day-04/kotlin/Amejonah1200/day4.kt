import java.nio.file.Path
import kotlin.io.path.readText

class BingoCard(val card: List<Int>) {
  val marked = mutableSetOf<Int>()
  val bingos = mutableSetOf<Int>()

  fun mark(number: Int): Boolean {
    if (number !in card) return false
    marked.add(number)
    card.chunked(5).forEachIndexed { index, nbs -> if (nbs.all { it in marked }) bingos.add(10 + index) }
    for (x in 0..4) {
      if ((0..4).all { y -> card[y * 5 + x] in marked }) bingos.add(x)
    }
    return bingos.isNotEmpty()
  }

  fun score(): Int {
    return card.filter { it !in marked }.sum() * marked.last()
  }

  fun clone(): BingoCard {
    return BingoCard(card)
  }
}

fun main() {
  val input = Path.of("./inputs/day4.txt").readText()
  val numbers = Regex("""(?:\A|,)(\d+)""").findAll(input).map { it.groupValues[1].toInt() }.toList()
  val bingoCards = Regex(""" ?(\d+) +(\d+) +(\d+) +(\d+) +(\d+)""").findAll(input).chunked(5) {
    BingoCard(it.flatMap { it.groups.drop(1).map { it!!.value.toInt() } })
  }
  println("Part 1: ${part1(numbers, bingoCards.map { it.clone() }.toList())}")
  println("Part 2: ${part2(numbers, bingoCards.map { it.clone() }.toList())}")
}

fun part1(numbers: List<Int>, bingoCards: List<BingoCard>): Int {
  numbers.first { n -> bingoCards.any { it.mark(n) } }
  return bingoCards.filter { it.bingos.isNotEmpty() }.map { it.score() }.first()
}

fun part2(numbers: List<Int>, bingoCards: List<BingoCard>): Int {
  return numbers.flatMap { n ->
    bingoCards.filter { it.bingos.isEmpty() }.filter { it.mark(n) }
  }.last().score()
}
