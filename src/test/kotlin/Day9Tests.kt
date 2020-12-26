import AdventOfCode.Day9
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class Day9Tests {
    @Test
    fun findFirstNonMatchingNumber() {
        val sampleData = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"

        val asNumbers = sampleData.split("\n").map { it.toBigInteger() }
        assertEquals("127".toBigInteger(), Day9.findFirstNonMatchingNumber(asNumbers, 5))
    }

    @Test
    fun findContiguousNumber() {
        val sampleData = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"

        val asNumbers = sampleData.split("\n").map { it.toBigInteger() }
        assertEquals(BigInteger.valueOf(62), Day9.findContiguousNumber(asNumbers, BigInteger.valueOf(127)))
    }
}