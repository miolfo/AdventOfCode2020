package AdventOfCode

import java.io.File

object Day4 {

    data class Passport(val byr: Int, val iyr: Int, val eyr: Int, val hgt: String, val hcl: String, val ecl: String, val pid: String, val cid: String)

    private fun stringToPassport(str: String) : Passport {
        val split = str.split(" ", "\\r\\n|\\n|\\r").filter { it.isNotEmpty() }
        val mappedValues = mutableMapOf<String, String>()
        split.forEach {
            val key = it.split(":")[0]
            val value = it.split(":")[1]
            mappedValues[key] = value
        }
        return Passport(
            toIntOrDefault(mappedValues.getOrDefault("byr", "")),
            toIntOrDefault(mappedValues.getOrDefault("iyr", "")),
            toIntOrDefault(mappedValues.getOrDefault("eyr", "")),
            mappedValues.getOrDefault("hgt", ""),
            mappedValues.getOrDefault("hcl", ""),
            mappedValues.getOrDefault("ecl", ""),
            mappedValues.getOrDefault("pid", ""),
            mappedValues.getOrDefault("cid", ""))
    }

    private fun toIntOrDefault(value: String) : Int {
        val int = value.toIntOrNull()
        if(int == null) return -1
        return int
    }

    private fun isValid(passport: Passport) : Boolean {
        return  passport.byr >= 0
                && passport.iyr >= 0
                && passport.eyr >= 0
                && passport.hgt.isNotEmpty()
                && passport.hcl.isNotEmpty()
                && passport.ecl.isNotEmpty()
                && passport.pid.isNotEmpty()
    }

    private fun isValidHeight(height: String) : Boolean {
        if(height.endsWith("cm")) {
            val hgt = height.removeSuffix("cm").toInt()
            return hgt in 150..193
        } else if(height.endsWith("in")) {
            val hgt = height.removeSuffix("in").toInt()
            return hgt in 59..76
        } else {
            return false
        }
    }

    private fun isValidPid(pid: String) : Boolean {
        return pid.replace("[^0-9 ]".toRegex(), "").length == 9
    }

    private fun isValidEyeColor(ecl: String) : Boolean {
        return arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl)
    }

    private fun isValidHairColor(hcl: String) : Boolean {
        if(!hcl.startsWith("#")) return false
        return hcl.replace("[^A-Za-z0-9 ]".toRegex(), "").length == 6
    }

    private fun isValid2(passport: Passport) : Boolean {
        if(!isValid(passport)) {
            return false
        }

        if(passport.byr < 1920 || passport.byr > 2002) return false
        if(passport.iyr < 2010 || passport.iyr > 2020) return false
        if(passport.eyr < 2020 || passport.eyr > 2030) return false
        if(!isValidHeight(passport.hgt)) return false
        if(!isValidHairColor(passport.hcl)) return false
        if(!isValidEyeColor(passport.ecl)) return false
        if(!isValidPid(passport.pid)) return false

        return true
    }

    fun countValidPasswords() {
        val file = File("src/main/resources/day4.txt")

        val passportStrings = mutableListOf<String>()

        var collected: String = ""
        file.readLines().forEach { line ->
            if(line.isEmpty()) {
                passportStrings.add(collected)
                collected = ""
            } else {
                collected += " " + line
            }
        }

        passportStrings.map { stringToPassport(it) }.forEach { println(it) }
        println(passportStrings.map { stringToPassport(it) }.filter { isValid(it) }.count())
        println(passportStrings.map { stringToPassport(it) }.filter { isValid2(it) }.count())
    }
}