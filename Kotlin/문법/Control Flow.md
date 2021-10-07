# Control Flow



## If else 문



### 식으로 사용되는 If 문

```kotlin
val max = if (a > b) a else b
```

* else를 동반해야 함

* 값을 반환해야 함

  

```kotlin
val max = if (a > b) {
    print("Choose a")
    a
} else {
    print("Choose b")
    b
}
```

* if 식이 블록을 가질 수 있음
* 마지막 구문이 반환 값이 됨



#### 삼항연산자가 없음

```kotlin
val max = if (a > b) a else b
```

* if문이 삼항연산자 역할을 해냄



## when 문

```kotlin
val x = 1

when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> {
        print("x is neither 1 nor 2")
    }
} // "x == 1" 출력
```

* C계열 언어의 switch문을 대체함
* 스코프 위에서부터 순차적으로 인자를 비교함



### 식으로 사용되는 when 문

```kotlin
var res = when (x) {
    100 -> "A"
    90 -> "B"
    80 -> "c"
    else -> "F"
}
```

* else문이 필수임

* else문이 없어도 된다는 것이 입증할 수 있는 경우 else 생략 가능

  ```kotlin
  var res = when(x) {
      true -> "맞다"
      false -> "틀리다"
      // else 생략 가능
  }
  ```

* 여러 조건들이 같은 방식으로 처리될 경우, branch 조건문에 콤마를 사용하여 표기

  ```kotlin
  var res = when(x) {
      0, 1 -> print("x == 0 or x == 1")
      else -> print("otherwise")
  }
  ```

* Branch 조건문에 함수나 식을 사용할 수 있음

  ```kotlin
  when (x) {
      parseInt(x) -> print("s encodes x")
      1 + 3 -> print("4")
      else -> print("s does not encode x")
  }
  ```

* range나 collection에 in이나 !in으로 범위 검사 가능

  ```Kotlin
  when (x) {
      in validNumbers -> print("x is valid")
      in 1..10 -> print("x is in the range")
      !in 10..20 -> print("x is outside the range")
      else -> print("none of the above")
  }
  ```

* 타입 검사 가능 (스마트 캐스트가 적용됨)

  ```Kotlin
  fun hasPrefix(x: Any) = when (x) {
      is String -> x.startsWith("prefix")
      else -> false
  }
  ```

* if-else if 체인을 대체할 수 있음
* when에 인자가 없으면, 논리연산으로 처리됨



## For 문

* iterator를 제공하는 모든 것을 반복할 수 있음

  ```Kotlin
  for (item in collection) {
      print(item)
  }
  ```

  * interator 조건
    * iterator()을 반환 하는 것이 있는 경우
      * next()를 가지는 경우
      * hasNext()가 Boolean을 가지는 경우

* 배열이나 리스트를 반복할 때, index를 이용하고 싶다면 indices를 사용하면 됨

  ```Kotlin
  val array = arrayOf("가", "나", "다")
  for (i in array.indices) {
      println("$i: ${array[i]}")
  }
  ```

* withIndex()를 사용해도 됨

  ```kotlin
  val array = arrayOf("가", "나", "다")
  for ((index, value) in array.withIndex()) {
      println("$index: ${value}")
  }
  ```

  

## while 문

* 자바와 거의 같음
* do-while문에서 body의 지역변수를 do-while문의 조건문이 참조 할 수 있음

```kotlin
do {
     	val y = retrieveDate()
    } while (y != null)
```

