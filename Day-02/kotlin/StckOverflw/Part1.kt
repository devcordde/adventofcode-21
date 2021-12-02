fun main() {
    val input = {}.javaClass.getResource("/input.txt")?.readText()?.lines() ?: error("No input.txt in resources")

    val (horizontal, depth) = input.fold(Coordinates()) { coordinates, inputString ->
        val (instructionName, value) = inputString.split(' ')
        if (instructionName.equals("forward", true)) {
            coordinates.copy(horizontal = coordinates.horizontal + value.toInt())
        } else {
            coordinates.copy(depth = coordinates.depth + if (instructionName.equals("down", true)) value.toInt() else -value.toInt())
        }
    }

    println("Solution: " + (horizontal * depth))
}
