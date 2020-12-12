import AdventOfCode.Day6
import org.junit.Test
import kotlin.test.assertEquals

class Day6Tests {

    @Test
    fun testUniqueCharCounts(){
        val sampleGroup1 = Day6.Group(listOf("abc"))
        assertEquals(3, Day6.countUniqueCharacters(sampleGroup1))

        val sampleGroup2 = Day6.Group(listOf("a", "b", "c"))
        assertEquals(3, Day6.countUniqueCharacters(sampleGroup2))

        val sampleGroup3 = Day6.Group(listOf("abc", "abc"))
        assertEquals(3, Day6.countUniqueCharacters(sampleGroup3))
    }

    @Test
    fun testCharsInAllAnswers() {
        val sampleGroup1 = Day6.Group(listOf("abc"))
        val sampleGroup2 = Day6.Group(listOf("a", "b", "c"))
        val sampleGroup3 = Day6.Group(listOf("ab", "ac"))
        val sampleGroup4 = Day6.Group(listOf("a", "a", "a", "a"))
        val sampleGroup5 = Day6.Group(listOf("b"))

        assertEquals(3, Day6.countCharsInAllAnswers(sampleGroup1))
        assertEquals(0, Day6.countCharsInAllAnswers(sampleGroup2))
        assertEquals(1, Day6.countCharsInAllAnswers(sampleGroup3))
        assertEquals(1, Day6.countCharsInAllAnswers(sampleGroup4))
        assertEquals(1, Day6.countCharsInAllAnswers(sampleGroup5))
    }
}