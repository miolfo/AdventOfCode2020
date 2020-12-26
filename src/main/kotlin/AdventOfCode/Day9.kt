package AdventOfCode

import java.io.File
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.exp

object Day9 {

    fun findFirstNonMatchingNumber(numbers: List<BigInteger>, preambleSize: Int) : BigInteger {

        val numbersToValidate = numbers.subList(preambleSize, numbers.size)

        for((i, validated) in numbersToValidate.withIndex()) {
            val preambleNumbers = numbers.subList(i, i + preambleSize)
            var valid = false
            preambleNumbers.forEach { preamble1 ->
                preambleNumbers.forEach { preamble2 ->
                    if(preamble1 != preamble2 && preamble1 + preamble2 == validated) {
                        valid = true
                    }
                }
            }
            if(!valid) {
                return validated
            }
        }

        return BigInteger.valueOf(0)
    }

    fun calculateSumOfSmallestAndLargest(numbers: List<BigInteger>) : BigInteger {
        return numbers.minOrNull()!! + numbers.maxOrNull()!!
    }

    fun findContiguousNumber(numbers: List<BigInteger>, expectedSum: BigInteger) : BigInteger {
        for(rangeLength in 2..numbers.size) {
            for(i in 0..numbers.size - rangeLength) {
                val numbersInRange = numbers.subList(i, i + rangeLength)
                val sum = numbersInRange.fold(BigInteger.ZERO, {acc, bigInteger ->  acc + bigInteger})
                if(sum == expectedSum) {
                    return calculateSumOfSmallestAndLargest(numbersInRange)
                }
            }
        }

        return BigInteger.valueOf(0)
    }

    fun findFirstNonMatching() {
        val file = File("src/main/resources/day9.txt")
        val nonMatching = findFirstNonMatchingNumber(file.readLines().map { it.toBigInteger() }, 25)
        println("First not matching is $nonMatching")
        val contiguous = findContiguousNumber(file.readLines().map { it.toBigInteger() }, nonMatching)
        println("Contiguous sum is $contiguous")
    }

}