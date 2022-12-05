fun main() {

    val mapping = mapOf(
        "A" to RPSMove.ROCK,
        "B" to RPSMove.PAPER,
        "C" to RPSMove.SCISSORS,
        "X" to RPSMove.ROCK,
        "Y" to RPSMove.PAPER,
        "Z" to RPSMove.SCISSORS
    )

    val beats = mapOf(
        RPSMove.ROCK to RPSMove.SCISSORS,
        RPSMove.PAPER to RPSMove.ROCK,
        RPSMove.SCISSORS to RPSMove.PAPER
    )

    fun getMoves(input: String): Pair<RPSMove, RPSMove> {
        return input.split(" ")
            .map { mapping.getValue(it) }
            .zipWithNext()
            .single()
    }

    fun part1(input: List<String>): Int {
        return input.asSequence()
            .map {
                val (theirs, ours) = getMoves(it)

                if (ours == theirs) {
                    3 + ours.score
                } else if (beats.getValue(ours) == theirs) {
                    6 + ours.score
                } else {
                    ours.score
                }
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day02TestInput")
    check(part1(testInput) == 15)

    // Solve Part 1
    val input = readInput("Day02Input")
    println("Part 1 solution: ${part1(input)}")

    // Test with provided example
//    check(part2(testInput, 3) == 45_000)

    // Solve Part 2
    println("Part 2 solution: ${part2(input)}")
}


enum class RPSMove(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}
