import java.io.File

val wordList = mutableListOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun main() {
    val part1 = mutableListOf<Int>()
    val part2 = mutableListOf<Int>()
    File("Day1.txt").forEachLine { line ->
        var currFirst = line.first{it.isDigit()}
        var currLast = line.last{it.isDigit()}

        part1.add("$currFirst$currLast".toInt())

        var firstIndex = line.indexOfFirst { it.isDigit() }
        var lastIndex = line.indexOfLast { it.isDigit() }

        for (i in wordList){
            if (line.contains(i)){
                val firstWord = line.indexOf(i)
                val lastWord = line.lastIndexOf(i)

                if (firstWord < firstIndex){
                    firstIndex = firstWord
                    currFirst = i.wordToNum().digitToChar()
                }
                if (lastWord > lastIndex){
                    lastIndex = lastWord
                    currLast = i.wordToNum().digitToChar()
                }
            }
        }

        part2.add("$currFirst$currLast".toInt())
    }

    println("Answer to part 1: ${part1.sum()}")
    println("Answer to part 2: ${part2.sum()}")
}

fun String.wordToNum() = wordList.indexOf(this) + 1
