import AdventOfCode.Day8
import org.junit.Test
import kotlin.test.assertEquals

class Day8Tests {

    private val sampleInput = "nop +0\n" +
            "acc +1\n" +
            "jmp +4\n" +
            "acc +3\n" +
            "jmp -3\n" +
            "acc -99\n" +
            "acc +1\n" +
            "jmp -4\n" +
            "acc +6"

    @Test
    fun testAccumulator() {
        val commands: List<Pair<String, Int>> = sampleInput.split("\n").map { Day8.stringToInstruction(it) }
        val res = Day8.applyInstructions(commands)
        assertEquals(5, res.second)
    }

    @Test
    fun testFixInstructions() {
        val commands: List<Pair<String, Int>> = sampleInput.split("\n").map { Day8.stringToInstruction(it) }
        assertEquals(8, Day8.fixInstructions(commands))
    }
}