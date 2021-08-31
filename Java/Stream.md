# Java 스트림 (Stream)

> 자바 8에서 추가한 스트림(*Streams*)은 람다를 활용할 수 있는 기술 중 하나입니다. 자바 8 이전에는 배열 또는 컬렉션 인스턴스를 다루는 방법은 `for` 또는 `foreach` 문을 돌면서 요소 하나씩을 꺼내서 다루는 방법이었습니다. 간단한 경우라면 상관없지만 로직이 복잡해질수록 코드의 양이 많아져 여러 로직이 섞이게 되고, 메소드를 나눌 경우 루프를 여러 번 도는 경우가 발생합니다.



## 스트림의 장점

* 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된  결과를 얻을 수 있다.
* 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있다.
* 병렬처리가 가능하다.



## 생성

보통 배열과 컬렉션을 사용해 스트림을 만들지만 외에도 다양한 방법이 있다.



### 배열 스트림

`Array.stream` 메소드를 사용해서 생성할 수 있다.

```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
```



### 컬렉션 스트림

컬렉션 타입의 경우 인터페이스에 추가된 디폴트 메소드 `stream` 을 이용해서 스트림을 만들 수 있다.

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
```



### 비어 있는 스트림

비어있는 스트림도 생성할 수 있다. 빈 스트림은 요소가 없을 때 `null` 대신 사용할 수 있다.

```java
public Stream<String> streamOf(List<String> list) {
  return list == null || list.isEmpty() 
    ? Stream.empty() 
    : list.stream();
}
```



### Stream.builder()

빌더를 사용하면 스트림에 직접적으로 원하는 값을 넣을 수 있다. 마지막에 `build` 메소드로 리턴한다.

```java
Stream<String> builderStream = 
  Stream.<String>builder()
    .add("Eric").add("Elena").add("Java")
    .build(); // [Eric, Elena, Java]
```



### Stream.generate()

`generate` 메소드를 이용하면 원하는 람다 값을 넣을 수 있다.

```java
Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
// "gen"이 5개 들어간 스트림 생성
```

