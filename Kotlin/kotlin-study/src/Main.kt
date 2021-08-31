import java.util.*

fun main(args: Array<String>) {


}

fun printKotlin() {
    println("hello Kotlin")
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}