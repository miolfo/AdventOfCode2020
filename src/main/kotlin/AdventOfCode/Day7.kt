package AdventOfCode

import java.io.File

object Day7 {

    data class ContainedBag(val color: String, val count: Int)
    data class BagRule(val color: String, val containedBags: List<ContainedBag>)

    final val BASE_BAGS_REGEX = "(.*) bags contain".toRegex()
    final val CONTAINING_BAGS_REGEX = "([0-9]) (.*?) bag".toRegex()

    fun stringToBagRule(str: String) : BagRule {
        val firstResult = BASE_BAGS_REGEX.find(str)
        val color = firstResult?.groupValues?.get(1)?.trim()
        val containingBags = mutableListOf<ContainedBag>()

        val containing = CONTAINING_BAGS_REGEX.findAll(str)
        for(match in containing) {
            val containingCount = match.groupValues.get(1)
            val containingColor = match.groupValues.get(2).trim()
            containingBags.add(ContainedBag(containingColor, containingCount.toInt()))
        }

        return BagRule(color.orEmpty(), containingBags)
    }

    fun countBagsInsideShinyGoldBags(rules: List<BagRule>) : Int {
        val shinyGoldRule : BagRule = findRuleForColor("shiny gold", rules)!!
        return countBagsInsideBag(shinyGoldRule, rules, 0)
    }

    fun countBagsInsideBag(rule: BagRule, allRules: List<BagRule>, accumulator: Int) : Int {
        if(rule.containedBags.size == 0) {
            return 0
        } else {
            var contains = 0
            rule.containedBags.forEach {
                contains += it.count
                val containingRule = findRuleForColor(it.color, allRules)!!
                contains += countBagsInsideBag(containingRule, allRules, accumulator) * it.count
            }
            return accumulator + contains
        }
    }

    fun countShinyGoldBagsInRules(rules: List<BagRule>) : Int = rules.filter { canContainShinyGoldBag(it, rules) }.count()

    fun canContainShinyGoldBag(rule: BagRule?, allRules: List<BagRule>) : Boolean {
        if(rule == null) return false
        val canInclude = rule.containedBags.find { it.color == "shiny gold" } != null
        if(canInclude) {
            return true
        } else if(rule.containedBags.size == 0) {
            return false
        } else {
            return rule.containedBags.find { canContainShinyGoldBag(findRuleForColor(it.color, allRules), allRules) } != null
        }
    }

    fun findRuleForColor(color: String, rules: List<BagRule> ) : BagRule? = rules.find { it.color == color }

    fun countValidLuggages() {
        val file = File("src/main/resources/day7.txt")
        val count = countShinyGoldBagsInRules(file.readLines().map { stringToBagRule(it) })
        println("Shiny gold can go into $count bags")
    }

    fun countContainingLuggages() {
        val file = File("src/main/resources/day7.txt")
        val count = countBagsInsideShinyGoldBags(file.readLines().map { stringToBagRule(it) })
        println("Shiny gold contains $count bags")
    }
}