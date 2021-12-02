fun main() {
    val input = {}.javaClass.getResource("/input.txt")?.readText() ?: error("No input.txt in resources")

    var horizontal = 0
    var depth = 0

    input.split("(\\r\\n|\\r|\\n)".toRegex()).forEach {
        val (instructionName, value) = it.split(' ')
        if (instructionName.equals("forward", true)) {
            horizontal += value.toInt()
        } else {
            depth += if (instructionName.equals("down", true)) value.toInt() else -value.toInt()
        }
    }

    println("Solution: " + (horizontal * depth))
}