fun main() {
    fun part1(input: List<String>): Int {
        var currentMax = Int.MIN_VALUE
        var runningSum = 0

        for (i in input) {
            // Check that the current value is a number
            if (i.toIntOrNull() != null) {
                runningSum += i.toInt()
            } else {
                // Space is delimiter
                currentMax = currentMax.coerceAtLeast(runningSum)
                runningSum = 0
            }
        }

        return currentMax
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01TestInput")
    check(part1(testInput) == 24_000)

    // Solve Part 1
    val part1Input = readInput("Day01Input")
    println("Part 1 solution: ${part1(part1Input)}")
}
