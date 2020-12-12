package AdventOfCode

import java.io.File

object Day5 {

    data class Seat(val row: Int, val column: Int)

    fun countSeatIds() {
        val file = File("src/main/resources/day5.txt")
        println(file.readLines().map { findSeat(it) }.map { calculateId(it) }.maxOrNull())
    }

    fun findSeat() {
        val file = File("src/main/resources/day5.txt")
        val idList = file.readLines().map { findSeat(it) }.map { calculateId(it) }.sorted()
        val smallestId = idList.first()
        val largestId = idList.last()
        for(i in smallestId..largestId) {
            if(!idList.contains(i)) println("Missing id $i")
        }
    }

    private fun findSeat(seat: String) : Seat {
        val rowString = seat.substring(0, 7)
        val colString = seat.substring(7, 10)
        return Seat(binarySearch(rowString, 'B', 'F', 127), binarySearch(colString, 'R', 'L', 7))
    }

    private fun calculateId(seat: Seat) : Int {
        return seat.row * 8 + seat.column
    }

    fun binarySearch(input: String, higherPar: Char, lowerPar: Char, max: Int) : Int {
        var range = Pair(0, max)
        val startingInputs = input.substring(0, input.length -1)
        val endingInput = input.last()
        for(char in startingInputs) {
            if(char.equals(higherPar)) {
                range = Pair(range.first + ((range.second - range.first) / 2) + 1, range.second)
            } else if (char.equals(lowerPar)) {
                range = Pair(range.first, range.second - ((range.second - range.first)/ 2) - 1)
            }
        }
        if(endingInput.equals(higherPar)) return range.second
        return range.first
    }
}