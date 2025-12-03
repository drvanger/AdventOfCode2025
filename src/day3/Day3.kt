package day3

import java.io.File

class Day3 (fileName : String) {
    private val data = File(fileName).readLines().map { it.toCharArray().map { it.digitToInt() } }

    fun findMaxJoltageGeneral(bank : List<Int>, length : Int) : Long {
        var maxes = MutableList(length) {0}
        var maxPlaces = MutableList(length) {-1}

        var result = ""

        for (n in 0..<maxes.size) {
            var max = 0
            var maxPlace = -1

            var searchLastIndex = bank.lastIndex - (maxes.lastIndex - n)

            var searchFirstIndex = if (n == 0) {
                0
            }
            else {
                maxPlaces[n - 1] + 1
            }

            for (i in searchFirstIndex .. searchLastIndex) {
                if (bank[i] > max) {
                    max = bank[i]
                    maxPlace = i
                }
            }

            maxes[n] = max
            maxPlaces[n] = maxPlace

            result += max.toString()
        }

        return result.toLong()
    }

    fun partOne() {
        var result : Long = 0

        for (line in data) {
            result += findMaxJoltageGeneral(line, 2)
        }

        println(result)
    }

    fun partTwo() {
        var result : Long = 0

        for (line in data) {
            result += findMaxJoltageGeneral(line, 12)
        }

        println(result)
    }
}

fun main() {
    val d = Day3("data/day3_test.txt")

    d.partTwo()
}