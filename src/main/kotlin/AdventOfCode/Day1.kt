package AdventOfCode

import java.io.File

object Day1 {
    fun calculate2020() {
        val file = File("src/main/resources/day1.txt")
        val lines = file.readLines().map { it.toInt() }
        lines.forEach { it ->
            lines.forEach { it2 ->
                if(it + it2 == 2020) println(it * it2)
            }
        }
    }

    fun calculate2020_2() {
        val file = File("src/main/resources/day1.txt")
        val lines = file.readLines().map { it.toInt() }
        lines.forEach { it ->
            lines.forEach { it2 ->
                lines.forEach { it3 ->
                    if(it + it2 + it3 == 2020) println(it * it2 * it3)
                }
            }
        }
    }
}