# Basic Syntax 

* ### 패키지 정의

  * 파일 최상단에 위치
  * 디렉터리와 패키지를 일치시키지 않아도 됨

```kotlin
pakage my.demo

import java.util.* // pakage, import 모두 자바와 비슷함
```



* ### 함수 정의

  * 함수는 `fun` 키워드로 정의

    ``` kotlin
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
    ```

  * 함수 자체가 식인 경우 return 생략 가능

  * return type이 추론됨

    ```kotlin
    fun sum(a: Int, b: Int) = a + b
    ```

    

  * 리턴 할 값이 없는 경우 Unit으로 리턴함

  * Java의 void와 같음

    ```kotlin
    fun printKotlin(): Unit { // Unit 생략 가능
    	println("hello Kotlin")
    }
    ```

    

* ### 지역 변수 정의

  * val: 읽기전용 변수

  * 1회만 할당 가능, Java의 final과 유사함

    ```kotlin
    val a: Int = 1 // 즉시 할당
    val b = 2 // type 추론
    val c: Int // 초기화 필요
    ```

  * var: Mutable 변수

    ```kotlin
    var x = 5
    x += 1
    ```

    

* ### 문자열 템플릿

  ```kotlin
  var a = 1
  var s1 = "a is $a" // $를 사용하여 a에 직접 접근 가능
  a = 2
  val s2 = "${s1.replace("is", "was")}, but now is $a" // 문자열 보간법
  ```

  

* ### 조건문

  ```kotlin
  fun maxOf(a: Int, b: Int): Int {
  	if(a > b) {
  		return a
  	} else {
  		return b
  	}
  }
  ```

  * 조건식으로 사용가능

  ```kotlin
  fun maxOf(a: Int, b: Int) = if (a > b) a else b
  ```

  

* ### nullable

  * 값이 null일 수 있는 경우 타입에 nullable 마크를 명시해야 함

    ```kotlin
    fun parseInt(str: String): Int? {
    	// 정수가 아닌 경우 null을 리턴
    }
    ```

    

* ### 자동 타입 변환

  * 타입 체크만 해도 자동으로 타입 변환 됨

    ```kotlin
    fun getStringLength(obj: Any): Int?{
    	if (obj is String) {
    		// obj가 자동으로 String으로 변환
    		return obj.length
    	}
    	
    	return null
    }
    ```

    

* ### when expression

  ```kotlin
  fun describe(obj: Any): String =
      when (obj) {
          1 -> "One"
          "Hello" -> "Greeting"
          is Long -> "Long"
          !is String -> "Not a String"
          else -> "Unknown"
      }
      // ex) println(describe(1)) -> "One" 출력
  ```

  

* ### ranges

  * In 연산자를 이용해서 숫자 범위를 체크 가능

  ```kotlin
  val x = 3
  if (x in 1..10) { // x 가 1~10 사이에 있는지 확인
  	println("fits in range")
  }
  ```

  * range를 이용한 for loop

  ```kotlin
  for (x in 1..5) {
  	print(x)
  }
  ```

  

* ### collections

  * 컬렉션도 in으로 loop가능

    ```kotlin
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
    ```

  * in으로 해당 값이 colleciton에 포함되는지 체크 가능

    ```Kotlin
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
    ```

  * 람다식을 이용해서 컬렉션에 filter, map 등의 연산 가능

    ```Kotlin
    val items = listOf("banana", "avocade", "apple", "kiwi")
    items
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
    ```

    

