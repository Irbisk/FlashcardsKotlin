fun main() {
    val studentsMarks = mutableMapOf<String, Int>()
    while (true) {
        val key = readln()
        if (key == "stop") break
        val value = readln().toInt()
        if (!studentsMarks.containsKey(key)) {
            studentsMarks += key to value
        }
    }
    println(studentsMarks)
}