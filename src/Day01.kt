
fun main()
{
    val elfList = mutableListOf<Int>()

    fun loadList(input: List<String>): Int
    {

        var tem = 0
        input.forEach()
        { line ->

            if (line.isEmpty())
            {
                elfList.add(tem)
                tem = 0
            }
            else
                tem += line.toInt()
        }

        elfList.add(tem)

        return input.size
    }

    fun principalElfCalories(elfAmount: Int): Int // index, calories
    {
        var calories = 0

        for (index in 0 until elfAmount)
        {
            calories += elfList[index]
        }

        return calories

    }

    fun debug()
    {
        println("elf list: ")
        elfList.forEachIndexed { index, i -> println("[$index] $i") }
    }

    val input = readInput("Day01")
    //val input = readInput("test")

    loadList(input)
    elfList.sortDescending()

    val elfWithMoreSnacksIndex = elfList[0]
    println("elf with more snacks has: $elfWithMoreSnacksIndex calories")

    val principalsElfAmount = 3
    val principalsCalories = principalElfCalories(principalsElfAmount)

    println("principals $principalsElfAmount elf's with more snacks has $principalsCalories, calories together.")


    println()
    //debug()

}
