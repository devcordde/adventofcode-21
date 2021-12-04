import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val instructions = Files.readAllLines(Paths.get("./inputs/day3/input.txt")).filter { it.isNotBlank() }
    val values = HashMap<Int, MutableList<String>>()

    var gammaBinaryNumber = ""
    var epsilonBinaryNumber = ""

    var oxygenFilter = ""
    var scrubberFilter = ""

    var oxygenNumber = ""
    var scrubberNumber = ""

    // First and second part
    instructions.forEach { string ->
        string.forEachIndexed { index, char ->
            if (!values.containsKey(index)) {
                values[index] = mutableListOf()
            }

            values[index]!!.add(char.toString())
        }
    }

    values.forEach { (_, chars) ->
        val mostUsed = chars.groupingBy { it }.eachCount()
        val mostUsedFilteredOxygen = chars.filterIndexed { index, _ -> instructions[index].startsWith(oxygenFilter) }.groupingBy { it }.eachCount()
        val mostUsedFilteredScrubber = chars.filterIndexed { index, _ -> instructions[index].startsWith(scrubberFilter) }.groupingBy { it }.eachCount()
        var mostUsedNumber = "0"
        var leastUsedNumber = "1"

        if ((mostUsed["1"] ?: 0) >= (mostUsed["0"] ?: 0)) {
            mostUsedNumber = "1"
            leastUsedNumber = "0"
        }

        gammaBinaryNumber += mostUsedNumber
        epsilonBinaryNumber += leastUsedNumber

        mostUsedNumber = "0"
        leastUsedNumber = "1"

        if ((mostUsedFilteredOxygen["1"] ?: 0) >= (mostUsedFilteredOxygen["0"] ?: 0)) {
            mostUsedNumber = "1"
        }

        oxygenFilter += mostUsedNumber

        if ((mostUsedFilteredScrubber["1"] ?: 0) >= (mostUsedFilteredScrubber["0"] ?: 0)) {
            leastUsedNumber = "0"
        }

        scrubberFilter += leastUsedNumber

        if (instructions.filter { it.startsWith(oxygenFilter) }.size == 1) {
            oxygenNumber = instructions.filter { it.startsWith(oxygenFilter) }[0]
            oxygenFilter = oxygenNumber
        }

        if (instructions.filter { it.startsWith(scrubberFilter) }.size == 1) {
            scrubberNumber = instructions.filter { it.startsWith(scrubberFilter) }[0]
            scrubberFilter = scrubberNumber
        }
    }

    println("First solution: ${Integer.parseInt(gammaBinaryNumber, 2) * Integer.parseInt(epsilonBinaryNumber, 2)}")
    println("Second solution: ${Integer.parseInt(oxygenNumber, 2) * Integer.parseInt(scrubberNumber, 2)}")
}