fun main(args: Array<String>) {
    do {
        val y = retrieveDate()
    } while (y != null)
}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}