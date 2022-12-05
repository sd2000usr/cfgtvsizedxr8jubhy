



val abc = "abcdefghijklmnopqrstuvwxyz"

val alphabet = "$abc${abc.uppercase()}"


fun main()
{

    fun part1(input: List<String>)
    {

        var scoreAcc = 0

        input.forEach ()
        { string ->

            val map0 = mutableMapOf<Char, Int>() // char, times appear
            val map1 = mutableMapOf<Char, Int>() // char, times appear

            val half = string.length / 2
            val slot0 = string.substring(0, half)
            val slot1 = string.substring(half, string.length)

            slot0.forEach()
            { char ->

                val target = map0[char]
                if (target == null)
                    map0[char] = 1
                else
                    map0[char] = map0[char]!! + 1
            }

            slot1.forEach()
            { char ->

                val target = map1[char]
                if (target == null)
                    map1[char] = 1
                else
                    map1[char] = map1[char]!! + 1
            }

            var mustAppearChar = Pair(' ', 0) // char, times appear

            map0.forEach()
            { (char, timesAppear) ->

                val timesAppearInMap1 = map1[char]

                if (timesAppearInMap1 != null)
                {
                    val timesAppearTogether = timesAppear + timesAppearInMap1
                    if (timesAppearTogether > mustAppearChar.second)
                        mustAppearChar = Pair(char, timesAppearTogether)

                }

            }

            val score = alphabet.indexOf(mustAppearChar.first) + 1
            scoreAcc += score

            println("must appear char: ${mustAppearChar.first}, times: ${mustAppearChar.second}, score: $score")


            //println(it)

        }

        println("scoreAcc: $scoreAcc")


    }

    fun part2(input: List<String>)
    {


        fun mapOfCharAndTimesAppears(string: String): Map<Char, Int>
        {
            val map = mutableMapOf<Char, Int>() // char, times appear

            string.forEach ()
            { char ->

                val target = map[char]
                if (target == null)
                    map[char] = 1
                else
                    map[char] = target + 1
            }

            return map

        }

        val cacheListCharTimesAppear = mutableListOf<Map<Char, Int>>()

        fun timesCharAppearsInLisOfMap(charTarget: Char, list: List<Map<Char, Int>>): Int?
        {

            var accTimes = 0

            for (map in list)
            {
                val timesAppears = map[charTarget]

                if (timesAppears != null)
                    accTimes += timesAppears
                else
                    return null
            }

            return accTimes
        }

        fun mustCommonCharAppearInCacheScore(): Int // must char appears score
        {

            ///fun otherContains

            val result = mutableMapOf<Char, Int>()

            //timesCharAppearsInLisOfMap()

            cacheListCharTimesAppear.forEach ()
            { map ->

                map.forEach ()
                { (char, times) ->

                    if (!result.containsKey(char))
                    {
                        val totalTimes = timesCharAppearsInLisOfMap(char, cacheListCharTimesAppear)

                        if (totalTimes != null)
                            result[char] = totalTimes
                    }

                }

            }

            val finalList = result.toList().sortedByDescending { (char, times) -> times }

            val score = alphabet.indexOf(finalList.first().first) + 1

            println("finalList: ${finalList.first()}, score: $score")

            return score

        }


        var totalScore = 0

        var index = 0
        while (index < input.size)
        {
            val string = input[index]
            val newMap = mapOfCharAndTimesAppears(string)


            if ((index + 1) % 3 == 0)
            {
                cacheListCharTimesAppear.add(newMap)
                totalScore += mustCommonCharAppearInCacheScore()
                cacheListCharTimesAppear.clear()
            }
            else
                cacheListCharTimesAppear.add(newMap)

            index ++
        }

        println("total score: $totalScore")


    }


    val input = readInput("Day03")
    //val input = readInput("testDay03")
    //part1(input)
    part2(input)


}