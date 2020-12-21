package AdventOfCode

import java.io.File

object Day8 {

    fun stringToInstruction(string: String) : Pair<String, Int> {
        val cmd = string.split(" ")[0]
        val nString = string.split(" ")[1]
        if(nString.startsWith("+")) {
            return Pair(cmd, nString.removePrefix("+").toInt())
        } else {
            return Pair(cmd, -nString.removePrefix("-").toInt())
        }
    }

    /**
     * Apply each instruction, return a pair where first value is return code (0 for success), second is current value of accumulator
     */
    fun applyInstructions(commands: List<Pair<String, Int>>) : Pair<Int, Int> {
        //Apply each instruction until any instruction is hit twice. Use index of instruction as an identifier
        var accumulator = 0
        var commandIndex = 0
        val usedIndexes = mutableSetOf<Int>()
        while (!usedIndexes.contains(commandIndex) && commandIndex < commands.size) {
            val cmd = commands[commandIndex]
            usedIndexes.add(commandIndex)
            when (cmd.first) {
                "nop" -> {
                    commandIndex++
                }
                "acc" -> {
                    commandIndex++
                    accumulator += cmd.second
                }
                "jmp" -> {
                    commandIndex += cmd.second
                }
            }
        }
        return Pair(if (commandIndex == commands.size) 0 else -1, accumulator)
    }

    //Find the instruction that causes the program to terminate, and return the corresponding accumulator value
    fun fixInstructions(commands: List<Pair<String, Int>>) : Int {
        for (i in 0..commands.size) {
            val cmd = commands[i].first
            if(cmd == "jmp" || cmd == "nop") {
                val mutatedCommands = commands.toMutableList()
                val mutatedPair = Pair(if (commands[i].first == "jmp") "nop" else "jmp", commands[i].second)
                mutatedCommands[i] = mutatedPair
                val resultWithFix = applyInstructions(mutatedCommands)
                if(resultWithFix.first == 0) return resultWithFix.second
            }
        }
        return -1
    }

    fun calculateAccumulator() {
        val file = File("src/main/resources/day8.txt")
        val cmds = file.readLines().map { stringToInstruction(it) }
        val count = applyInstructions(cmds)
        println("Accumulator ended at ${count.second}")
    }

    fun fixCodeAndFindValue() {
        val file = File("src/main/resources/day8.txt")
        val cmds = file.readLines().map { stringToInstruction(it) }
        val count = fixInstructions(cmds)
        println("Accumulator with fixed results in $count")
    }
}