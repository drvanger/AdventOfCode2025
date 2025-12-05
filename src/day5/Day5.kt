package day5

import java.io.File

class Day5 (fileName : String) {
    private val data = File(fileName).readLines()

    private var intervals : HashSet<Pair<Long, Long>> = hashSetOf()
    private var ids : ArrayList<Long> = arrayListOf()
    private var intervalsSorted : List<Pair<Long, Long>>

    init {
        var readNumbers = false

        for (str in data) {
            if (readNumbers) {
                ids.add(str.toLong())
            }
            else if (str == "") {
                readNumbers = true
            }
            else {
                var tmp = str.split('-').map { it.toLong() }
                intervals.add(Pair(tmp[0], tmp[1]))
            }
        }

        intervalsSorted = intervals.sortedBy { pair -> pair.first }
    }

    fun isFresh(id : Long) : Boolean {
        for (interval in intervals) {
            if (interval.first <= id && interval.second >= id) return true
        }

        return false
    }

    fun partOne() {
        var result = 0

        for (id in ids) {
            if (isFresh(id)) result++
        }

        println(result)
    }

    fun partTwo() {
        var condensedIntervals : MutableList<Pair<Long, Long>> = mutableListOf()

        for (interval in intervalsSorted) {
            if (condensedIntervals.isEmpty()) condensedIntervals.add(interval)
            else {
                if (interval.first <= condensedIntervals.last().second && interval.second >= condensedIntervals.last().second) {
                    var tmp = Pair(condensedIntervals.last().first, interval.second)
                    condensedIntervals[condensedIntervals.lastIndex] = tmp
                }
                else if (interval.first > condensedIntervals.last().second){
                    condensedIntervals.add(interval)
                }
            }
        }

        var result = 0.toLong()

        for (interval in condensedIntervals) {
            result += (interval.second - interval.first + 1)
        }

        println(result)
    }
}

fun main() {
    val d = Day5("data/day5.txt")

    d.partTwo()
}