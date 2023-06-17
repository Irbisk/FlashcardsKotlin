fun addUser(userMap: Map<String, String>, login: String, password: String): MutableMap<String, String> {
    val map = userMap.toMutableMap()
    if (map.containsKey(login)) {
        println("User with this login is already registered!")

    } else {
        map[login] = password

    }
    return map
}