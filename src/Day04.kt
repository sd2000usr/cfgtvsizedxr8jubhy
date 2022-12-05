

fun main()
{


    fun part1(input: List<String>)
    {
        var overlappingCount = 0

        input.forEach ()
        { line ->

            println("line: $line")

            val split = line.split(',')
            val (elfWithRange0Raw, elfWithRange1Raw) = split

            val elfRangeSplit0 = elfWithRange0Raw.split('-')
            val elfRangeSplit1 = elfWithRange1Raw.split('-')

            val elf0Range = IntRange(elfRangeSplit0[0].toInt(), elfRangeSplit0[1].toInt())
            val elf1Range = IntRange(elfRangeSplit1[0].toInt(), elfRangeSplit1[1].toInt())

            println("[elf 0] range: $elf0Range")
            println("[elf 1] range: $elf1Range")

            val isOverlapping =
                elf0Range.first >= elf1Range.first && elf0Range.last <= elf1Range.last ||
                        elf1Range.first >= elf0Range.first && elf1Range.last <= elf0Range.last
            println("[overlapping] $isOverlapping")
            println()

            if (isOverlapping)
                overlappingCount++

        }

        println("overlappingCount count: $overlappingCount")

    }

    fun part2(input: List<String>)
    {
        var overlappingCount = 0

        input.forEach ()
        { line ->

            println("line: $line")

            val split = line.split(',')
            val (elfWithRange0Raw, elfWithRange1Raw) = split

            val elfRangeSplit0 = elfWithRange0Raw.split('-')
            val elfRangeSplit1 = elfWithRange1Raw.split('-')

            val elf0Range = IntRange(elfRangeSplit0[0].toInt(), elfRangeSplit0[1].toInt())
            val elf1Range = IntRange(elfRangeSplit1[0].toInt(), elfRangeSplit1[1].toInt())

            println("[elf 0] range: $elf0Range")
            println("[elf 1] range: $elf1Range")

            /*val isOverlapping =
                elf0Range.first >= elf1Range.first && elf0Range.last <= elf1Range.last ||
                        elf1Range.first >= elf0Range.first && elf1Range.last <= elf0Range.last


            println("[overlapping] $isOverlapping")
            println()

            if (isOverlapping)
                overlappingCount++*/

            val overlappingRangeStart =
                if (elf0Range.first >= elf1Range.first)
                    elf0Range.first
                else
                    elf1Range.first


            val overlappingRangeEnd =
                if (elf0Range.last <= elf1Range.last)
                    elf0Range.last
                else
                    elf1Range.last

            println("overlappingRange: $overlappingRangeStart..$overlappingRangeEnd")

            if (overlappingRangeStart <= overlappingRangeEnd)
            {
                val overlappingSize = overlappingRangeEnd - overlappingRangeStart + 1
                //overlappingCount += overlappingSize
                overlappingCount ++
                println("[overlapping] size: $overlappingSize")
            }
            else
                println("[not overlapping]")

            println()
        }

        println("overlappingCount count: $overlappingCount")

    }



    val input = readInput("Day04")
    //val input = readInput("testDay04")

    //part1(input)
    part2(input)

}



