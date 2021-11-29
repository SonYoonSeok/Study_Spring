fun main(args: Array<String>) {
    var price = 999999
    var a = Book("해로의 모험", 10000).apply {
        name = "[폭탄세일중] " + name
        discount()
    }

    a.let { println("상품명 : ${it.name}, 가격 ${it.price}") }
}

class Book(var name: String, var price: Int) {
    fun discount(){
        price -= 2000
    }
}