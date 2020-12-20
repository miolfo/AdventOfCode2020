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

    fun countValidLuggages() {
        val file = File("src/main/resources/day7.txt")
        val count = file.readLines().map { stringToBagRule(it) }.filter {
            //TODO: Also need to check if each of the containing bags can contain the shiny gold bag!! 
            var found = false
            it.containedBags.forEach {
                if(it.color == "shiny gold") found = true
            }
            found
        }.forEach { println(it) }
        println("Shiny gold can go into $count bags")
    }
}