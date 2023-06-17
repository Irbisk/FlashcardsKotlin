data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        return age == other.age
    }
}

fun main() {
    //implement your code here
    val client1 = Client(readln(), readln().toInt(), readln().toInt())
    val client2 = Client(readln(), readln().toInt(), readln().toInt())
    println(client1 == client2)
}