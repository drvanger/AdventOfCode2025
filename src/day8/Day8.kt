package day8

import java.io.File
import utils.Point3D

class Day8 (fileName : String) {
    private val data = File(fileName).readLines().map { it.split(',') }.map { Point3D(it[0].toInt(), it[1].toInt(), it[2].toInt())}

    fun partOneTwo() {
        var distances : MutableMap<Pair<Point3D, Point3D>, Double> = mutableMapOf()

        for (i in 0 .. data.lastIndex) {
            for (j in i + 1 .. data.lastIndex) {
                distances[Pair(data[i], data[j])] = data[i].distanceFromEuclidean(data[j])
            }
        }

        distances = distances.toList().sortedBy { (key, value) -> value }.toMap().toMutableMap()

        var groupNumbers : MutableMap<Point3D, Int> = mutableMapOf()
        var groups : MutableMap<Int, MutableSet<Point3D>> = mutableMapOf()
        var numberSet : MutableSet<Int> = mutableSetOf()

        for (i in 0 .. data.lastIndex) {
            groupNumbers[data[i]] = i
            groups[i] = mutableSetOf()
            groups[i]!!.add(data[i])

            numberSet.add(i)
        }

        val iterations = 1000
        var currentIteration = 0

        for (pair in distances) {
            currentIteration++

            if (currentIteration == iterations + 1) {
                var sizes : MutableList<Int> = mutableListOf()

                for (group in groups) {
                    sizes.add(group.value.size)
                }

                sizes.sortDescending()

                println(sizes[0] * sizes[1] * sizes[2])
            }

            val p1 = pair.key.first
            val p2 = pair.key.second

            // Merge the groups of p1 and p2

            // Let's merge the group of p2 into the group of p1
            val recipientGroup = groupNumbers[p1]!!
            val fromGroup = groupNumbers[p2]!!


            if (recipientGroup == fromGroup) continue

            for (element in groups[fromGroup]!!) {
                groups[recipientGroup]!!.add(element)

                groupNumbers[element] = recipientGroup
            }

            groups[fromGroup] = mutableSetOf()
            numberSet.remove(fromGroup)

            // Test if there's only one circuit in the system

            if (numberSet.size == 1) {
                println(pair.key.first.x * pair.key.second.x)
                break
            }
        }


    }
}

fun main() {
    val d = Day8("data/day8.txt")

    d.partOneTwo()
}