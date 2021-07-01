# 같은 자료형을 비교해도 false가 나오는 이유

```kotlin
var a: Int = 10000
var b: Int = 10000
println("a === b : ${a === b}")
println("a == b : ${a == b}")
```

* 일반적으로 a 와 b를 reference comparision을 하면 true가 나온다.
  * Java 플랫폼에서  숫자형은 JVM primitive type으로 저장된다.

#####  -> 이를 Java 코드로 바꿔보면

```java
// var a: Int = 10000
int a = 10000;
// var b: Int = 10000
int b = 10000;
```

* 따라서 a와 b를  reference comparision을 하면 true가 나온다



### 그러나

```kotlin
var a: Int? = 10000 // nullable로 선언
var b: Int = 10000
println("a === b : ${a === b}")
println("a == b : ${a == b}")
```

* nullable이나 제네릭으로 선언하면 박싱이 된다
* identity를 유지하지 않는다.

#####  -> 이를 Java 코드로 바꿔보면

```java
// var a: Int? = 10000
Integer a = 10000; // Integer 객체로 선언됨
// var b: Int = 10000
int b = 10000;
```

* 따라서 a와 b를 reference comparision을 하면 false가 나오게 된다.

