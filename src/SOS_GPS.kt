

data class File(val name: String)

class Folder(var name: String, var distanceToFile: Int, action: Folder.() -> Unit = {})
{
    companion object
    {
        var mustCloserFiles = mutableListOf<File>()
        var mustCloserDeep = -1
        //var steps = 0
        var stepCount = 0

        var fileCount = 0
        var fileSize = 0

        private var mustCloserDeepFile = -1

        fun mustCloserDeepFile(): Int
        {
            val tem = mustCloserDeepFile
            mustCloserDeepFile = -1
            return tem
        }

    }

    private val files = mutableListOf<File>()
    private val folders = mutableListOf<Folder>()

    init
    {
        action(this)
    }

    fun containsFiles(): Boolean
    {
        return files.isNotEmpty()
    }

    fun containsSubFolders(): Boolean
    {
        return folders.isNotEmpty()
    }

    fun addFile(fileName: String)
    {
        //println("[add file] $fileName in ${this.name}")
        files.add(File(fileName))
        fileSize ++
    }

    fun addFolder(folderName: String, distanceToFile: Int, action_: Folder.() -> Unit ={}): Folder
    {
        val newFolder = Folder(folderName, distanceToFile, action_)
        folders.add(newFolder)
        return newFolder
    }

    fun inFolder(name: String, distanceToFile: Int, action: Folder.() -> Unit = {}): Folder
    {
        val folder = folders.firstOrNull { it.name == name} ?: addFolder(name, distanceToFile)
        if (folder.distanceToFile > distanceToFile)
            folder.distanceToFile = distanceToFile

        action(folder)
        return folder
    }

    fun createFileInPath(fileName: String, path: String)
    {
        //println("file: $fileName, in path: $path")

        var folderTarget = this
        var distanceToFile = path.length - 1

        path.forEach ()
        { char ->

            val folderName = "$char"
            folderTarget = folderTarget.inFolder(folderName, distanceToFile)
            distanceToFile --

        }

        folderTarget.addFile(fileName)

    }

    fun collapseSingleFolders()
    {
        while (folders.size == 1)
        {
            name += folders.first().name // merger my name with first sub folder

            folders.addAll(folders.first().folders) // add all sub folders of my first folder
            files.addAll(folders.first().files) // add all sub files of my first folder

            folders.removeAt(0) // remove my first folder
            distanceToFile --
        }

        folders.forEach ()
        { folder ->
            folder.collapseSingleFolders()
        }

    }

    fun deepOfCloserFile(deep: Int)
    {

        if (containsFiles() && (deep < mustCloserDeepFile || mustCloserDeepFile == -1))
            mustCloserDeepFile = deep
        else
        {
            for (folder in folders)
            {
                folder.deepOfCloserFile(deep + 1)
            }
        }


        println("[Deep to return] $mustCloserDeepFile")

    }

    fun orderFoldersByMustCloserFileDeep(folder0: Folder, folder1: Folder): Pair<Pair<Folder, Int>, Pair<Folder, Int>>
    {
        folder0.deepOfCloserFile(0)
        val folder0MustCloserFileDeep = mustCloserDeepFile()

        folder1.deepOfCloserFile(0)
        val folder1MustCloserFileDeep = mustCloserDeepFile()

        return if (folder0MustCloserFileDeep <= folder1MustCloserFileDeep)
            Pair(Pair(folder0, folder0MustCloserFileDeep), Pair(folder1, folder1MustCloserFileDeep))
        else
            Pair(Pair(folder1, folder1MustCloserFileDeep), Pair(folder0, folder0MustCloserFileDeep))

    }

    /*fun isFoldersOrderedByMustCloserFileDeep(folders: List<Folder>): Boolean
    {
        return if (folders.size == 1)
            true
        else
            orderFoldersByMustCloserFileDeep(folders[0], folders[1]).first == folders[0]

    }*/

    fun debug(deep: Int = 0)
    {

        println("folder open [$name] ->")

        print(" -folder -{ $name }- folders[${folders.size}] files [${files.size}] distanceToCloserFile [$distanceToFile]")
        if (containsFiles())
        {
            //println(" steps: ${steps ++}")
            //println(" steps: [${steps - 1}]")

            if (mustCloserDeep == -1 || deep < mustCloserDeep)
            {
                mustCloserDeep = deep
                mustCloserFiles.clear()
                mustCloserFiles.addAll(files)
            }

            println(" --deep [$deep] files: ${files.joinToString (", "){ it.name }}")

            fileCount ++


        }
        else
            println()

        if (containsSubFolders())
        {
            val isReversed = folders.first().name.startsWith("R") // first is R

            var index = 0

            while (index < folders.size)
            {
                val safeIndex =
                    if (isReversed)
                        folders.size - index - 1
                    else
                        index


                folders[safeIndex].debug(deep + 1)


                index ++
            }

            // part 1
            /*if (folders.first().name.startsWith("L"))
                folders.forEach { it.debug(deep + 1) }
            else
                folders.reversed().forEach { it.debug(deep + 1) }*/


        }

        println("folder close [$name] <-")
        //steps ++
        println()

    }

    fun santaTravel()
    {

        if (containsFiles())
            println("[santa travel] file ${files.joinToString (", "){ it.name }}, steps: [$stepCount]")

        stepCount ++



        when (folders.size)
        {
            2 ->
            {
                val folder0 = folders[0]
                val folder1 = folders[1]

                val folder0DistanceToFile = folder0.distanceToFile
                val folder1DistanceToFile = folder1.distanceToFile

                when
                {
                    folder0DistanceToFile == folder1DistanceToFile ->
                    {
                        if (folder0.name.first() == 'L')
                        {
                            folder0.santaTravel()
                            folder1.santaTravel()
                        }
                        else
                        {
                            folder1.santaTravel()
                            folder0.santaTravel()
                        }
                    }

                    folder0DistanceToFile < folder1DistanceToFile ->
                    {
                        folder0.santaTravel()
                        folder1.santaTravel()
                    }

                    else ->
                    {
                        folder1.santaTravel()
                        folder0.santaTravel()
                    }

                }

            }

            1 ->
            {
                folders[0].santaTravel()
            }

            /*folders.size == 0 -> // in folder with file
            {
                println("[santa travel] file ${files.first().name}, steps: [$stepCount]")
            }*/
            //else -> println("[Error] {santaTravel}")
        }


        stepCount ++


    }

}



fun main()
{
    val file = "testSOS_GPS"
    //val file = "SOS_GPS"

    val root = Folder("root", -1)
    {
        readInput(file).forEach()
        { line ->
            //println("line: $line")

            val (kidName, route) = line.split(" - ")
            //println("kid: $kidName, route: $route")

            createFileInPath(kidName, route)

        }

        collapseSingleFolders()

        //tree()

        debug()

        println("------------[santa's travel]------------")

        santaTravel()

        //println("[santa travel] steps: [${Folder.stepCount}]")

        println("------------[results]------------")

        println("must closer kids: [${Folder.mustCloserFiles.joinToString (", "){ it.name }}], deep: [${Folder.mustCloserDeep}]")

        /*println("total steps: [${Folder.steps}]")
        println("step count: [${Folder.stepCount }]")*/


    }



}