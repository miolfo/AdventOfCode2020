package AdventOfCode

import java.io.File
import java.math.BigInteger

object Day3 {
    fun calculateSlope() {
        val file = File("src/main/resources/day3.txt")
        var forest = arrayOf<Array<Char>>()
        file.readLines().forEach { line ->
            forest += line.toCharArray().toTypedArray()
        }

        val step1answer = calculateTreesInSlope(3, 1, forest)
        println(step1answer)

        //Other slopes from assignment
        /* Right 1, down 1.
        Right 3, down 1.
        Right 5, down 1.
        Right 7, down 1.
        Right 1, down 2.*/

        val slope1 = calculateTreesInSlope(1, 1, forest)
        val slope2 = calculateTreesInSlope(3, 1, forest)
        val slope3 = calculateTreesInSlope(5, 1, forest)
        val slope4 = calculateTreesInSlope(7, 1, forest)
        val slope5 = calculateTreesInSlope(1, 2, forest)
        val step2answer: BigInteger = slope1.toBigInteger() * slope2.toBigInteger() * slope3.toBigInteger() * slope4.toBigInteger() * slope5.toBigInteger()
        println(step2answer)
    }

    fun calculateTreesInSlope(stepRight: Int, stepDown: Int, forest: Array<Array<Char>>) : Int {
        val arrayWidth = forest[0].size
        val arrayLength = forest.size

        var treeCount = 0
        for((i, j) in (0 until arrayLength step stepDown).withIndex()) {
            val charAt = forest[j][(i*stepRight)%arrayWidth]
            if(charAt == '#') treeCount++
        }
        return treeCount
    }
}