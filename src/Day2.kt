import java.io.File

fun main() {
    //Possible values used in Part 1
    val red = 12
    val green = 13
    val blue = 14

    val part1 = mutableSetOf<Int>() //Using a set to ignore duplicates
    val part2 = mutableListOf<Int>()

    File("Day2.txt").forEachLine { line ->
        val game = line.split(":")
        val id = game.first().drop(5).toInt()

        val trimmedList = game.last().replace(" ", "")

        //Used to determine whether the rolls are over the limit, for Part 1
        var possible = true

        //Used for determining pigeon-hole values, for Part 2
        var currRed = 0
        var currGreen = 0
        var currBlue = 0

        //Semicolon split
        for (a in trimmedList.split(";")){
            //Comma split
            for (b in a.split(",")){
                val num = b.takeWhile{it.isDigit()}.toInt()
                val color = b.dropWhile{it.isDigit()}

                when (color){
                    "red" -> {
                        if (num > red) possible = false
                        if (num > currRed) currRed = num
                    }
                    "green" -> {
                        if (num > green) possible = false
                        if (num > currGreen) currGreen = num
                    }
                    "blue" -> {
                        if (num > blue) possible = false
                        if (num > currBlue) currBlue = num
                    }
                }
            }
        }

        if (possible) part1.add(id)
        part2.add(currRed * currGreen * currBlue)
    }

    println("Answer for part 1: ${part1.sum()}")
    println("Answer for part 2: ${part2.sum()}")
}
