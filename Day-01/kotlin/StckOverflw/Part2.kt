fun main() {
    val input = {}.javaClass.getResource("/input.txt")?.readText() ?: error("No input.txt in resources")

    val numbers = input.lines().map { it.toInt() }

    val increasing = numbers.windowed(3).mapTo(ArrayList()) {
        it[0] + it[1] + it[2]
    }.zipWithNext().count { (a, b) -> b > a }

    println(increasing)
}