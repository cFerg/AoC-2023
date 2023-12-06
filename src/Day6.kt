import java.io.File

fun main(){
    val lines = File("Day6.txt").readLines()
    
    part1(lines)
    part2(lines)
}

fun part1(input:List<String>){
    //Max time lasted for each race
    val time = input.first().split(":").last().replace("\\s+".toRegex(), " ").trim().split(" ").map{it.toInt()}.toList()

    //The max distance ran for each race
    val distance = input.last().split(":").last().replace("\\s+".toRegex(), " ").trim().split(" ").map{it.toInt()}.toList()

    val maxPossibilities = mutableListOf<Int>()

    for ((i,t) in time.withIndex()){
        //Run each race individually
        val maxDistance = distance[i]

        var currentOptions = 0

        for (s in 0..t){
            if ((t-s)*s > maxDistance){
                currentOptions++
            }
        }

        maxPossibilities.add(currentOptions)
    }

    println("Part 1 solution: ${maxPossibilities.reduce { acc, i ->  acc * i }}")
}

fun part2(input:List<String>) {
    //Max time lasted for each race
    val time = input.first().split(":").last().replace("\\s+".toRegex(), "").trim().toLong()

    //The max distance ran for each race
    val maxDistance = input.last().split(":").last().replace("\\s+".toRegex(), "").trim().toLong()

    var currentOptions = 0

    for (s in 0..time){
        if ((time-s)*s > maxDistance){
            currentOptions++
        }
    }


    println("Part 2 solution: $currentOptions")
}
