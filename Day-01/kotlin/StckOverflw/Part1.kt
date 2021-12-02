fun main() {
    val input = {}.javaClass.getResource("/input.txt")?.readText() ?: error("No input.txt in resources")

    val numbers = input.lines().map { it.toInt() }

    println(numbers.zipWithNext().count { (a, b) -> b > a })
}