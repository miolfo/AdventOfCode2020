package AdventOfCode

import java.io.File

object Day6 {
    data class Group(val answers: List<String>)

    fun groupAnswers(answersStrings: List<String>) : List<Group> {
        return answersStrings.map { stringToGroup(it) }
    }

    fun stringToGroup(str: String) : Group {
        val split = str.split(" ", "\\r\\n|\\n|\\r").filter { it.isNotEmpty() }
        return Group(split)
    }

    fun countUniqueCharacters(group: Group) : Int {
        return group.answers.joinToString(separator = "").toCharArray().distinct().count()
    }

    /**
     * Find out the count of characters that exists in all of the answers of a group
     */
    fun countCharsInAllAnswers(group: Group) : Int {
        var count = 0
        group.answers.first().forEach { char ->
            if(group.answers.all { it.contains(char)}) count ++
        }
        return count
    }

    fun countAnswers() {
        val file = File("src/main/resources/day6.txt")

        val answerStrings = mutableListOf<String>()
        var collected = ""
        file.readLines().forEach {
            if(it.isEmpty()) {
                answerStrings.add(collected)
                collected = ""
            } else {
                collected += " " + it
            }
        }
        answerStrings.add(collected)

        println(groupAnswers(answerStrings).map { countUniqueCharacters(it) }.sum())
        println(groupAnswers(answerStrings).map { countCharsInAllAnswers(it) }.sum())


    }
}