# 스코프 함수 (Functional Scope)

* 객체의 컨텍스트 내에서 특정 동작 (프로퍼티 초기화, 활용 등)을 실행하기 위한 목적을 가진 함수
* 람다 함수로 사용하개 되면 임시로 스코프를 형성
* 객체의 이름을 통해 일일히 참조할 필요 없이 객체를 접근하고 핸들링 할 수 있음
* **[ apply, run, with, also, let ]** 으로 총 5가지 함수를 제공



## apply

```kotlin
fun main(args: Array<String>) {
    var a = Book("해로의 모험", 10000).apply {
        name = "[폭탄세일중] " + name
        discount()
    }

    println("상품명 : {${a.name}}, 가격 : {${a.price}}")
}

class Book(var name: String, var price: Int) {
    fun discount(){
        price -= 2000
    }
}
```

* 인스턴스를 생성하고 변수에 할당하기 전 초기화 작업을 해줄 수 있음
* 모든 명령이 수행된 후 새로 생성된 인스턴스를 반환



## run / with

```kotlin
fun main(args: Array<String>) {
    var a = Book("해로의 모험", 10000).apply {
        name = "[폭탄세일중] " + name
        discount()
    }

    var bookCost = a.run {
        println("상품명 : ${name}, 가격 : ${price}")
        price + 2000 //return
    }
    
    var bookCost = with(a) {
        println("상품명 : ${name}, 가격 : ${price}")
        price + 2000 // return
    }

    println("원가는 $bookCost 입니다.")
}

class Book(var name: String, var price: Int) {
    fun discount(){
        price -= 2000
    }
}
```

* 스코프의 마지막 라인을 반환
* 특정 인스턴스의 프로퍼티를 출력하거나 계산값으로 활용할 때 사용

* run은 참조 연산자, with는 파라미터 형태로 선언



## also / let

also와 let은 각각 apply, run 과 동작이 같지만 `it` 키워드가 사용 가능한 특징이 있다.

```kotlin
fun main(args: Array<String>) {
    var price = 999999
    var a = Book("해로의 모험", 10000).apply {
        name = "[폭탄세일중] " + name
        discount()
    }

    a.run { println("상품명 : $name, 가격 $price") }
}

class Book(var name: String, var price: Int) {
    fun discount(){
        price -= 2000
    }
}
```

* 위 코드를 실행하였을 때 `상품명 : [폭탄세일중] 해로의 모험, 가격 : 999999` main() 스코프의 price를 참조하여 `999999`의 값을 출력하는 것을 볼 수 있다. 이 때 `let`의 `it` 키워드를 사용하여 이를 방지할 수 있다.

  

```kotlin
var price = 999999
    var a = Book("해로의 모험", 10000).apply {
        name = "[폭탄세일중] " + name
        discount()
    }

    a.let { println("상품명 : ${it.name}, 가격 ${it.price}") }
```

