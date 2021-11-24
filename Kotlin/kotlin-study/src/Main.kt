fun main(args: Array<String>) {
    foo("asddd")
    foo("sdsd", "sdaga", "gdhjhl", "as")
}

fun foo(vararg strings:String) {
    strings.forEach {
        print(it)
    }
}