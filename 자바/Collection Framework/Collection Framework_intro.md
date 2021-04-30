# Collection Framework

### 컬렉션

- **요소**(객체)를 수집해서 저장하는 것

### 배열의 문제점

- 저장할 수 있는 객체 수가 배열을 생성할 때 결정

  -> **불특정 다수의 객체**를 저장하기에는 문제

- 객체를 삭제했을 때 해당 인덱스가 비게됨

  -> 객체를 저장하려면 어디가 비었는지 확인해야됨

```java
//길이 10인 배열 생성
Product[] array = new Product[10]; //불특정 다수의 객체를 저장하기에는 문제

//객체 추가
array[0] = new Product("Model1");
array[1] = new Product("Model2");

//객체 검색
Product model1 = array[0];
Product model2 = array[1];

//객체 삭제
array[0] = null;
array[1] = null;
```



### 컬렉션 프레임워크

- 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 제공되는 컬렉션 라이브러리
- **java.util** 패키지에 포함
- 인터페이스를 통해서 정형화된 방법으로 다양한 컬렉션 클래스 이용



### 주요 인터페이스

- **List - 배열과 유사하게 인덱스로 관리**

  - ArrayList
  - Vector
  - LinkedList

  >순서를 유지하고 저장
  >
  >중복 저장 가능

- **Set - 집합과 유사**

  - HashSet
  - TreeSet

  > 순서를 유지하지 않고 저장
  >
  > 중복 저장 안됨

- **Map - 키와 값의 쌍으로 관리**

  - HashMap
  - Hashtable
  - TreeMap
  - Properties

  > 키와 값이 쌍으로 저장
  >
  > 키는 중복 저장 안됨