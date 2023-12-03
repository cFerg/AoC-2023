import java.io.File

//A few helper methods - They make iterations go faster
fun Any.display(prefix:String) = println(prefix+this)
fun Char.isSymbol() = !(isDigit() || equals('.'))

inline fun <T> Set<T>.fastForEach(action: (T) -> Unit) {
    for (index in indices){
        action(elementAt(index))
    }
}

inline fun <T> List<T>.fastForEachIndexed(action: (Int, T) -> Unit) {
    for (index in indices) {
        val item = get(index)
        action(index, item)
    }
}

fun main() {
    val board = File("Day3.txt").readLines() //Saved for multiple lookups

    val parts = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

    val symbolLocation = mutableSetOf<Pair<Int, Int>>()

    //Does a first lookup of just symbols, and their location, for a second pass-through, later.
    for (row in board.indices) {
        for (col in board.indices) {
            if (board[row][col].isSymbol()) {
                symbolLocation.add(Pair(row, col))
            }
        }
    }

    //Use regex to find all groups of numbers, to alleviate back-tracing
    val regex = Regex("\\d+")

    board.fastForEachIndexed { i, row ->
        //Finds all full numbers, then compares it to symbol locations
        regex.findAll(row).forEach{ num ->
            val next = mutableSetOf<Pair<Int, Int>>()

            //Uses offset lookups, based on current locations
            for (j in -1..1) {
                for (k in -1..1) {
                    num.range.mapTo(next) { l -> Pair(i + j, l + k) }
                }
            }
            //If a symbol location matches an offset location, remove the parent location, otherwise add the value
            next.intersect(symbolLocation).fastForEach{
                if (!parts.containsKey(it)) {
                    parts[it] = mutableListOf()
                }
                parts[it]?.add(num.value.toInt())
            }
        }
    }

    parts.values.sumOf { it.sum() }.display("Output for part 1: ")
    parts.values.filter { it.size == 2 }.sumOf { it.reduce { i, j -> i * j } }.display("Output for part 2: ")
}
