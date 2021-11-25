fun main(args: Array<String>) {
    foo(strings = arrayOf("a", "b", "c"))
}

fun foo(vararg strings: String) {
    strings.forEach { print(it) }
}