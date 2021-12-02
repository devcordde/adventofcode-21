enum class Action {
  FORWARD, UP, DOWN
}

data class Submarine(var horizontal: Long, var depth: Long, var aim: Long)

fun main() {
  val actions = readInput("day2").map {
    val (action, amount) = Regex("""(\w+) (\d+)""").matchEntire(it.trim())!!.destructured
    Action.valueOf(action.uppercase()) to amount.toLong()
  }
  println("First: " + actions.fold(Submarine(0, 0, 0), ::part1).let { "${it.horizontal * it.depth}" })
  println("Second: " + actions.fold(Submarine(0, 0, 0), ::part2).let { "${it.horizontal * it.depth}" })
}

fun part1(submarine: Submarine, action: Pair<Action, Long>): Submarine {
  val (actionType, amount) = action
  when (actionType) {
    Action.FORWARD -> submarine.horizontal += amount
    Action.UP -> submarine.depth -= amount
    Action.DOWN -> submarine.depth += amount
  }
  return submarine
}

fun part2(submarine: Submarine, action: Pair<Action, Long>): Submarine {
  val (actionType, amount) = action
  when (actionType) {
    Action.FORWARD -> {
      submarine.horizontal += amount
      submarine.depth += submarine.aim * amount
    }
    Action.UP -> submarine.aim -= amount
    Action.DOWN -> submarine.aim += amount
  }
  return submarine
}

