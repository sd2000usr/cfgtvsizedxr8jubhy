enum class AnswerRequest
{
    WIN,
    DRAW,
    LOSE
}

fun main()
{

    val input = readInput("Day02")
    //val input = readInput("testDay02")

    fun commonChar(char: Char): Char
    {
        return when (char)
        {
            'X' -> 'A'
            'Y' -> 'B'
            'Z' -> 'C'
            else -> char
        }

    }

    fun reveal(char: Char): String
    {
        return when (char)
        {
            'A', 'X' -> "Rock"
            'B', 'Y' -> "Paper"
            else -> "Scissors"
        }
    }

    fun pointsOf(my: Char): Int
    {
        return when (my)
        {
            'A' -> 1
            'B' -> 2
            else -> 3
        }
    }

    fun fight(enemy: Char, my: Char): Int
    {

        return when (enemy)
        {
            'A' ->
            {
                when (my)
                {
                    'A' -> 3
                    'B' -> 6
                    else -> 0
                }
            }

            'B' ->
            {
                when (my)
                {
                    'A' -> 0
                    'B' -> 3
                    else -> 6
                }
            }

            else ->
            {
                when (my)
                {
                    'A' -> 6
                    'B' -> 0
                    else -> 3
                }
            }

        }


    }

    fun fightResultToString(pointsByFight: Int): String
    {
        return when (pointsByFight)
        {
            6 -> "Win"
            3 -> "Draw"
            else -> "Lose"
        }
    }

    fun pointsByAnswer(myAnswer: Char): Int
    {
        return when (myAnswer)
        {
            'X' -> 0
            'Y' -> 3
            else -> 6
        }
    }

    fun rightAnswerTo(enemy: Char): Char
    {
        return when (enemy)
        {
            'A' -> 'B'
            'B' -> 'C'
            else -> 'A'
        }
    }

    fun answerByRequest(enemyAnswer: Char, request: Char): Char
    {
        return when (request)
        {
            'A' ->
            {
                when (enemyAnswer)
                {
                    'A' -> 'C'
                    'B' -> 'A'
                    else -> 'B'
                }
            }

            'B' ->
            {
                when (enemyAnswer)
                {
                    'A' -> 'A'
                    'B' -> 'B'
                    else -> 'C'
                }
            }

            else ->
            {
                rightAnswerTo(enemyAnswer)
            }
        }
    }

    fun part1()
    {
        var score = 0

        input.forEach ()
        { line ->
            val enemyAnswer = commonChar(line[0])
            val myAnswer = commonChar(line[2])

            val pointsByAnswer = pointsOf(myAnswer)
            //val pointsByAnswer = pointsOf(answerByRequest(enemyAnswer, myAnswer))
            //val pointsByFight = pointsByAnswer(commonChar(myAnswer))
            val pointsByFight = fight(enemyAnswer, myAnswer)
            val resultInString = fightResultToString(pointsByFight)

            score += pointsByAnswer + pointsByFight
            println("enemyAnswer: ${reveal(enemyAnswer)}, myAnswer: ${reveal(myAnswer)}, pointsByAnswer: $pointsByAnswer, pointsByFight: $pointsByFight, result: $resultInString")

        }

        println("score: $score")
    }

    fun part2()
    {
        var score = 0

        input.forEach ()
        { line ->
            val enemyAnswer = commonChar(line[0])
            val myAnswer = commonChar(line[2])

            //val pointsByAnswer = pointsOf(myAnswer)
            val pointsByAnswer = pointsOf(answerByRequest(enemyAnswer, myAnswer))
            //val pointsByFight = pointsByAnswer(commonChar(myAnswer))
            val pointsByFight = fight(enemyAnswer, answerByRequest(enemyAnswer, myAnswer))
            val resultInString = fightResultToString(pointsByFight)

            score += pointsByAnswer + pointsByFight
            println("enemyAnswer: ${reveal(enemyAnswer)}, myAnswer: ${reveal(myAnswer)}, pointsByAnswer: $pointsByAnswer, pointsByFight: $pointsByFight, result: $resultInString")

        }

        println("score: $score")
    }
    part1()
    //part2()

}