package flashcards

import kotlin.random.Random

val cardMap = mutableMapOf<String, String>()
val mistakesCards = mutableMapOf<String, Int>()

fun main(args: Array<String>) {
    for (i in args.indices) {
        if (args[i] == "-import") {
            FileHandler.import(args[i + 1])
        }
    }
    menu()
    for (i in args.indices) {
        if (args[i] == "-export") {
            FileHandler.export(args[i + 1])
        }
    }
}

fun menu() {
    while (true) {
        printLine("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        val command = readLine()
        when (command) {
            "add" -> addCard()
            "remove" -> remove()
            "import" -> import()
            "export" -> export()
            "ask" -> ask()
            "hardest card" -> hardestCard()
            "log" -> log()
            "reset stats" -> resetStats()
            "exit" -> break
        }
    }
    printLine("Bye bye!")
}

fun import() {
    printLine("File name:")
    val fileName = readLine()
    FileHandler.import(fileName)
}

fun export() {
    printLine("File name:")
    val fileName = readLine()
    FileHandler.export(fileName)
}

fun log() {
    printLine("File name:")
    val fileName = readLine()
    FileHandler.writeLog(fileName)
}

fun remove() {
    printLine("Which card?")
    val term = readLine()
    if (cardMap.containsKey(term)) {
        cardMap.remove(term)
        printLine("The card has been removed.")
    } else {
        printLine("Can't remove \"$term\": there is no such card.")
    }
}

fun addCard() {
    printLine("The card:")
    val term = readLine()
    if (cardMap.containsKey(term)) {
        printLine("The card \"$term\" already exists.")
        return
    }
    printLine("The definition of the card:")
    val definition = readLine()
    if (cardMap.containsValue(definition)) {
        printLine("The definition \"$definition\" already exists.")
        return
    }
    cardMap[term] = definition
    printLine("The pair (\"$term\":\"$definition\") has been added.")
}

fun addCards(size: Int) {
    for (i in 1..size) {
        var term = ""
        var definition = ""
        printLine("Card #$i:")
        while (true) {
            term = readln()
            if (cardMap.containsKey(term)) {
                printLine("The term \"$term\" already exists. Try again:")
            } else break
        }
        printLine("The definition for card #$i:")
        while (true) {
            definition = readln()
            if (cardMap.containsValue(definition)) {
                printLine("The term \"$definition\" already exists. Try again:")
            } else break
        }
        cardMap.put(term, definition)
    }
}

fun ask() {
    printLine("How many times to ask?")
    var number = readLine().toInt()

    for (card in cardMap) {
        printLine("The definition of \"${card.key}\":")
        val definition = readLine()
        checkAnswer(card.key, definition)
        number--
        if (number == 0) break
    }
}

fun checkAnswer(term: String, answer: String) {
    if (answer == cardMap.getValue(term)) {
        printLine("Correct!")
    } else {
        if (cardMap.containsValue(answer)) {
            val key = cardMap.entries.find { it.value == answer }?.key
            printLine("Wrong. The right answer is \"${cardMap.getValue(term)}\", but your definition is correct for \"$key\".")
        } else {
            printLine("Wrong. The right answer is \"${cardMap.getValue(term)}\".")
        }
        if (mistakesCards.containsKey(term)) {
            var value = 0
            value = mistakesCards[term]!!.plus(1)
            mistakesCards.replace(term, value)
        } else {
            mistakesCards[term] = 1
        }
    }
}

fun hardestCard() {
    if (mistakesCards.isNotEmpty()) {
        val maxValue = mistakesCards.maxOf { it.value }
        val list = mistakesCards.filter { it.value == maxValue }.map { it.key }.toList()
        if (list.size == 1) {
            printLine("The hardest card is \"${list[0]}\". You have $maxValue errors answering it.")
        } else {
            val mistakes = list.joinToString { "\"$it\"" }
            printLine("The hardest cards are $mistakes. You have $maxValue errors answering them.")
        }
    } else {
        printLine("There are no cards with errors.")
    }
}



fun resetStats() {
    mistakesCards.clear()
    printLine("Card statistics have been reset.")
}
