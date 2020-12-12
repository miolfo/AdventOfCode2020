import AdventOfCode.Day5
import org.junit.Test
import kotlin.test.assertEquals

class Day5Tests {

    @Test
    fun testBinarySearch() {
        val sample = "FBFBBFF"
        val row = Day5.binarySearch(sample, 'B', 'F', 127)
        assertEquals(44, row)

        val sample2 = "RLR"
        val col = Day5.binarySearch(sample2, 'R', 'L', 7)
        assertEquals(5, col)

        val sample3 = "LLR"
        val col2 = Day5.binarySearch(sample3, 'R', 'L', 7)
        assertEquals(1, col2)
    }
}