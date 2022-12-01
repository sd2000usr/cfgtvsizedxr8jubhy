
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


        return input.size
    }

    // get higher index
    fun elfWithMoreSnacksIndex(input: List<String>): Int
    {
        var elfIndex = 0
        var acc = 0

        elfList.forEachIndexed ()
        { index, calories ->

            if (calories > acc)
            {
                elfIndex = index
                acc = calories
            }

        }

        return elfIndex
    }

    fun elfCaloriesCarryOf(index: Int): Int
    {
        return elfList[index]
    }

    fun debug()
    {
        println("elf list: ")
        elfList.forEachIndexed { index, i -> println("[$index] $i") }
    }

    // test if implementation meets criteria from the description, like:
    /*val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)*/

    val input = readInput("Day01")
    loadList(input)

    val elfWithMoreSnacksIndex = elfWithMoreSnacksIndex(input)
    println("whoGotMoreSnacks index: ${elfWithMoreSnacksIndex}, calories: ${elfCaloriesCarryOf(elfWithMoreSnacksIndex)}")

    debug()

}
