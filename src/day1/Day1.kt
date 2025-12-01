package day1

import java.io.File

class Day1 (fileName : String) {
    private val data = File(fileName).readLines()

    private val moves = data.map { Move(it)}

    class Move (val mv : String) {
        private val direction : Char = mv[0]
        private val steps : Int = mv.substring(1).toInt()

        fun getValue() : Int {
            var sign : Int = 1

            if (direction == 'L') sign = -1

            return sign * steps
        }

        fun getSteps() : Int {
            return steps
        }

        fun getSign() : Int {
            return if (direction == 'L') -1
            else +1
        }
    }

    class Dial (val start : Int, val end : Int, val iniNum : Int) {
        var currentPosition : Int = 0

        init {
            currentPosition = iniNum
        }

        fun performMove(mv : Move) {
            for (i in 0..<mv.getSteps()) {
                currentPosition += mv.getSign()

                if (currentPosition == 100) currentPosition = 0
                if (currentPosition == -1) currentPosition = 99
            }
        }

        fun performMoveWithCount(mv : Move) : Int {
            var result = 0

            for (i in 0..<mv.getSteps()) {
                currentPosition += mv.getSign()

                if (currentPosition == 100) currentPosition = 0
                if (currentPosition == -1) currentPosition = 99

                if (currentPosition == 0) result++
            }

            return result
        }

        fun getPosition() : Int {
            return currentPosition
        }
    }

    fun partOne() {
        var dial = Dial(0, 99, 50)

        var result = 0

        for (mv in moves) {
            dial.performMove(mv)

            if (dial.getPosition() == 0) result++
        }

        println(result)
    }

    fun partTwo() {
        var dial = Dial(0, 99, 50)

        var result = 0

        for (mv in moves) {
            result += dial.performMoveWithCount(mv)
        }

        print(result)
    }
}

fun main() {
    val d = Day1("data/day1.txt")

    d.partTwo()
}