package flashcards

import java.io.File

class FileHandler {
    companion object {
        fun import(fileName: String) {
            val file = File(fileName)
            if (file.exists()) {
                val lines = file.readLines()
                lines.forEach {
                    val line = it.split(":")
                    cardMap.put(line[0], line[1])
                    if (line[2] != "0") {
                        mistakesCards.put(line[0], line[2].toInt())
                    }
                }
                printLine("${lines.size} cards have been loaded.")
            } else {
                printLine("File not found.")
            }
        }

        fun export(fileName: String) {
            val file = File(fileName)
            if (file.exists()) {
                file.delete()
            }
            cardMap.forEach {
                var line = "${it.key}:${it.value}:"
                line += if (mistakesCards.containsKey(it.key)) {
                    mistakesCards.getValue(it.key).toString()
                } else {
                    "0"
                }
                line += "\n"
                file.appendText(line)
            }
            printLine("${cardMap.size} cards have been saved.")
        }

        fun writeLog(fileName: String) {
            val file = File(fileName)
            if (file.exists()) {
                file.delete()
            }
            log.forEach { file.appendText("$it\n") }
            printLine("The log has been saved.")
        }
    }
}