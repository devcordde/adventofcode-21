import java.nio.file.Path
import kotlin.io.path.readText

fun main() {
  val initialState = Path.of("./inputs/day6.txt").readText()
    .split(",").groupBy { it.trim().toLong() }.mapValues { it.value.size.toLong() }
  println("Part 1: ${live(80, initialState).map { it.value }.sum()}")
  println("Part 2: ${live(256, initialState).map { it.value }.sum()}")
}

fun live(days: Long, state: Map<Long, Long>): Map<Long, Long> {
  if (days == 0L) return state
  return live(days - 1,
    buildMap {
      val new = state.mapKeys { it.key - 1 }
      val pregnants = new.getOrDefault(-1, 0)
      putAll(new.filter { it.key >= 0 })
      put(8, pregnants)
      put(6, pregnants + getOrDefault(6, 0))
    }
  )
}

