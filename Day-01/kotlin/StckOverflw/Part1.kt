fun main() {
    val input = {}.javaClass.getResource("/input.txt")?.readText() ?: error("No input.txt in resources")

    val numbers = input.split("\\s+".toRegex()).mapTo(ArrayList()) { it.toInt() }

    println(numbers.zipWithNext().count { (a, b) -> b > a })
}