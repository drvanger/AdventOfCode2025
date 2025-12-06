package day6

import java.io.File

class Day6 (fileName : String) {
    private val rawData = File(fileName).readLines()

    private val data = rawData.map { it.split(" ")}.map { it.filter { it.isNotEmpty() } }

    private val numbers = data.subList(0, data.lastIndex).map { it.map { it.toLong() } }
    private val operators = data.subList(data.lastIndex, data.lastIndex + 1)[0]

    private fun performOperator(numbers : List<Long>, operator : String) : Long {
        if (operator == "+") {
            return numbers.sum()
        }
        else {
            var result = 1.toLong()

            for (nr in numbers) {
                result *= nr
            }

            return result
        }
    }

    fun partTwo() {
        var partTwoNumbers = rawData.subList(0, rawData.lastIndex).toMutableList()

        var numbers : MutableList<MutableList<Long>> = mutableListOf()

        var columns = 0

        for (str in partTwoNumbers) {
           if (str.length > columns) {
               columns = str.length
           }
        }

        for (i in 0 ..  partTwoNumbers.lastIndex) {
            partTwoNumbers[i] = partTwoNumbers[i].padEnd(columns, ' ')
        }

        for (col in columns - 1 downTo 0) {
            var tmp = ""

            for (str in partTwoNumbers) {
                tmp += str[col]
            }

            if (tmp.filter { it != ' ' }.length == 0 || numbers.size == 0) {
                // It's a new problem

                var tmpList = mutableListOf<Long>()

                if (tmp.filter { it != ' ' }.length > 0) tmpList.add(tmp.trim().toLong())

                numbers.add(tmpList)
                continue
            }

            numbers[numbers.lastIndex].add(tmp.trim().toLong())
        }

        var result = 0.toLong()
        val currOpList = operators.reversed()

        for (i in 0 .. numbers.lastIndex) {
            result += performOperator(numbers[i], currOpList[i])
        }

        println(result)
    }

    fun partOne() {
        var result = 0.toLong()

        for (i in 0 .. numbers[0].lastIndex) {
            val currentOperator = operators[i]
            var tmpList : MutableList<Long> = mutableListOf()

            for (j in 0 .. numbers.lastIndex) {
                tmpList.add(numbers[j][i])
            }

            result += performOperator(tmpList, currentOperator)
        }

        println(result)
    }
}

fun main() {
    val d = Day6("data/day6.txt")

    d.partTwo()
}