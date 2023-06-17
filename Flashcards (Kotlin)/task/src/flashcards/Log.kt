package flashcards

val log = mutableListOf<String>()

fun printLine(line: String) {
    println(line)
    log.add(line)
}

fun readLine(): String {
    val line = readln()
    log.add(line)
    return line
}