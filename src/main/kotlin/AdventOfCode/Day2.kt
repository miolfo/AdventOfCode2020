package AdventOfCode

import java.io.File

object Day2 {
    fun countValidPasswords() {
        val file = File("src/main/resources/day2.txt")
        println(file.readLines().map { lineToPwLine(it) }.filter { validPass(it) }.count())
    }

    fun countValidPasswords2() {
        val file = File("src/main/resources/day2.txt")
        println(file.readLines().map { lineToPwLine(it) }.filter { validPass2(it) }.count())
    }

    data class PasswordLine (val min: Int, val max: Int, val char: Char, val password: String)

    fun validPass(pwl: PasswordLine) : Boolean {
        val count = pwl.password.filter { it.equals(pwl.char) }.count()
        return count >= pwl.min && count <= pwl.max
    }

    fun validPass2(pwl: PasswordLine) : Boolean {
        return (pwl.password[pwl.min - 1].equals(pwl.char) && !pwl.password[pwl.max - 1].equals(pwl.char))
                || (!pwl.password[pwl.min - 1].equals(pwl.char) && pwl.password[pwl.max - 1].equals(pwl.char))
    }

    fun lineToPwLine(line: String) : PasswordLine {
        val split = line.split(" ")
        val min = split[0].split("-")[0].toInt()
        val max = split[0].split("-")[1].toInt()
        val char = split[1][0]
        val pass = split[2]
        return PasswordLine(min,max, char, pass)
    }
}