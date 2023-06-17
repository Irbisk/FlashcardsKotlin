// write your data class here
data class Student(val name: String, val money: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (name != other.name) return false
        return money < 1500 && other.money < 1500
    }
}