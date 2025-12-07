package day7

import java.io.File

class Day7 (fileName : String) {
    private val data = File(fileName).readLines()

    fun findS(map : List<String>) : Pair<Int, Int> {
        var result = Pair(-1, -1)

        for (row in 0 .. map.lastIndex) {
            for (col in 0 .. map[row].lastIndex) {
                if (map[row][col] == 'S') return Pair(row, col)
            }
        }

        return result
    }

    fun partOne() {
        var rays : MutableSet<Pair<Int, Int>> = mutableSetOf()

        val start = findS(data)

        rays.add(start)

        var splitted = 0

        val lastRow = data.lastIndex
        var currentRow = start.first

        while (currentRow < lastRow) {
            var raysTmp : MutableSet<Pair<Int, Int>> = mutableSetOf()

            for (ray in rays) {
                var newCoordsTmp = Pair(ray.first + 1, ray.second)

                currentRow = newCoordsTmp.first

                if (data[newCoordsTmp.first][newCoordsTmp.second] == '^') {
                    splitted++

                    raysTmp.add(Pair(newCoordsTmp.first, newCoordsTmp.second - 1))
                    raysTmp.add(Pair(newCoordsTmp.first, newCoordsTmp.second + 1))
                }
                else {
                    raysTmp.add(newCoordsTmp)
                }
            }

            rays = raysTmp
        }

        println(splitted)

    }

    fun quantumSimulation(position : Pair<Int, Int>, lastRow : Int, history : HashMap<Pair<Int, Int>, Long> ) : Long {
        if (position.first == lastRow) return 1

        val newPos = Pair(position.first + 1, position.second)

        if (data[newPos.first][newPos.second] == '^') {

            val left = if (history.contains(Pair(newPos.first, newPos.second - 1))) {
                history[Pair(newPos.first, newPos.second - 1)]!!
            }
            else {
                quantumSimulation(Pair(newPos.first, newPos.second - 1), lastRow, history)
            }

            val right = if (history.contains(Pair(newPos.first, newPos.second + 1))) {
                history[Pair(newPos.first, newPos.second + 1)]!!
            }
            else {
                quantumSimulation(Pair(newPos.first, newPos.second + 1), lastRow, history)
            }

            return  left + right
        }
        else {
            if (history.contains(newPos)) {
                return history[newPos]!!
            }

            val tmp = quantumSimulation(newPos, lastRow, history)
            history[newPos] = tmp

            return tmp
        }
    }

    fun partTwo() {
        var history : HashMap<Pair<Int, Int>, Long> = hashMapOf()

        println(quantumSimulation(findS(data), data.lastIndex, history))
    }
}

fun main() {
    val d = Day7("data/day7.txt")

    d.partTwo()
}