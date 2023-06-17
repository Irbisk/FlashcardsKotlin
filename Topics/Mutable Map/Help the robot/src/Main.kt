fun helpingTheRobot(purchases: Map<String, Int>, addition: Map<String, Int>) : MutableMap<String, Int> {
    val map = purchases.toMutableMap()
    addition.forEach{
        if (map.containsKey(it.key)) {
            map.replace(it.key, it.value + map.getValue(it.key))
        } else {
            map += it.key to it.value
        }
    }
    return map

}