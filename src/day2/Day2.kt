package day2

import java.io.File

class Day2 (fileName : String) {
    private val data = File(fileName).readLines()[0].split(',').map { it.split('-') }
        .map { Pair<Long, Long>(it[0].toLong(), it[1].toLong()) }

    fun countInvalids(start : Long, end : Long, intervalStart : Long) : ArrayList<Long> {
        var result : ArrayList<Long> = arrayListOf()

        var current : Long = 0
        var currentHalf = start

        while (current < end) {
            current = (currentHalf.toString() + currentHalf.toString()).toLong()



            if (current >= intervalStart && current <= end) {
                result.add(current)
                //println(current)
            }

            currentHalf++
        }

        return result
    }

    fun concatParts(part : Long, times : Int) : Long {
        var tmp = ""

        for (i in 0..<times) {
            tmp += part.toString()
        }

        return tmp.toLong()
    }

    fun findInvalids2(start : Long, end : Long) : HashSet<Long> {
        //var result : ArrayList<Long> = arrayListOf()

        var result : HashSet<Long> = hashSetOf()

        var currentPart : Long = 1

        while ((currentPart.toString() + currentPart.toString()).toLong() <= end) {
            var times = 2

            while (times * currentPart.toString().length <= end.toString().length) {
                var tmp = concatParts(currentPart, times)

                //println(tmp)
                //println(times)

                if (tmp >= start && tmp <= end) {
                    //println(tmp)
                    result.add(tmp)
                }

                times++
            }

            currentPart++
        }

        return result
    }

    fun partOne() {
        var result : Long= 0

        for (range in data) {
            //println("Starting range " + range + " ------------------ ")

            var startHalf = if (range.first.toString().length % 2 == 0) {
                range.first.toString().substring(0, range.first.toString().length / 2).toLong()
            } else {
                ("0" + range.first.toString().substring(0, range.first.toString().length / 2)).toLong()
            }

            //println("Pair: " + range + ", start half: " + startHalf)

            var tmp = countInvalids(startHalf, range.second, range.first)
            result += tmp.sum()
        }

        println(result)

    }

    fun partTwo() {
        var result : Long = 0

        for (range in data) {
            //println("Starting range " + range + " ------------------ ")

            result += findInvalids2(range.first, range.second).sum()
        }

        println(result)
    }
}

fun main() {
    val d = Day2("data/day2.txt")

    d.partTwo()
}