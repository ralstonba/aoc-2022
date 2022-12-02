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

    fun part2(input: List<String>, k: Int): Int {
        // Given ["a", "", "a", "b", "", "c"] return the index of each ""
        // Yield [1, 4]
        val splitAt = input.withIndex()
            .filter { it.value.isBlank() }
            .map { it.index }.toMutableList()

        splitAt += input.size

        // Given [1, 4], the list of all split points, create the sublists
        // ["a", "", "a", "b", "", "c"] -> subList(0, 1) == ["a"], sublist(2, 4) == ["a", "b"], sublist(5, size) == ["c"]
        return (0 until splitAt.size)
            .asSequence()
            .map { input.subList(if (it == 0) it else splitAt[it - 1] + 1 , splitAt[it]) }
            // At this point every item in the list should be numbers, convert the strings to actual numbers
            .map { sublist -> sublist.sumOf { it.toInt() }}
            // Sort the sequence so that we can retrieve the k-largest
            .sortedDescending()
            // Get the sum
            .take(k)
            .sum()
    }

    // Cuts should be happening [0, 3], [4, 5], [6, 8], [9, 12], [13, 14]

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01TestInput")
    check(part1(testInput) == 24_000)

    // Solve Part 1
    val input = readInput("Day01Input")
    println("Part 1 solution: ${part1(input)}")

    // Test with provided example
    check(part2(testInput, 3) == 45_000)

    // Solve Part 2
    println("Part 2 solution: ${part2(input, 3)}")
}
