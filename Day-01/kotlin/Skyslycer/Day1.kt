import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val measurements = Files.readAllLines(Paths.get("./inputs/day1/input.txt")).mapNotNull { it.toIntOrNull() }

    // Part 1
    var increasedValues = 0

    measurements.forEachIndexed { position, measurement ->
        measurements.getOrNull(position + 1)?.let {
            if (measurement < it) {
                increasedValues++
            }
        }
    }

    println("First solution: $increasedValues")

    // Part 2
    var increasedTrippleValues = 0

    repeat(measurements.size) {
        if (it == 0) return@repeat

        if (getThreeMeasurementSum(measurements, it - 1) < getThreeMeasurementSum(measurements, it)) {
            println("$it yes")
            increasedTrippleValues++
        } else {
            println("$it no")
        }
    }

    println("Second solution: $increasedTrippleValues")
}

fun getThreeMeasurementSum(measurements: List<Int>, index: Int): Int {
    var sum = 0
    var counter = 0

    repeat(3) {
        measurements.getOrNull(index + counter)?.let {
            sum += it
        }

        counter++
    }

    return sum
}