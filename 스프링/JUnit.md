# JUnit



## JUnit이란?

* CUnit, PyUnit 등 xUnit으로 된 자바의 단위 테스팅 프레임워크이다.

* Spring Boot 2.2.x 버전 이후 JUnit5를 자체적으로 제공한다. (이전에는 JUnit4를 제공)

  ### 테스트는 두 가지 method로 나뉜다

  * **Test Method** : `@Test`, `@RepeatedTest`, `@ParameterizedTest`, `@TestFactory`, `@TestTemplate` 로 선언되어있고 실제 테스트를 넣는 메서드

  * **Lifecycle Method** : `@BeforeAll`, `@AfterAll`, `@BeforeEach`, `@AfterEach` 로 선언되어 있고 Test의 라이프사이클에 따라 실행되는 메서드

    **🚨 두 메서드 모두 public으로 선언해야 한다.**



### 라이프사이클 메서드

* @BeforeAll
  * 테스트 클래스가 실행되기 전 실행
* @BeforeEach
  * 각 테스트 메서드가 실행되기 전 실행
* @AfterAll
  * 테스트 클래스가 실행된 후 실행
* @AfterEach
  * 각 테스트 메서드가 실행된 후 실행

<img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Frpuwi%2Fbtq27UJl4H9%2FHQ6kYAJ9k1UKLnKc1wFIQK%2Fimg.png" alt="JUnit 대표적 단정(Assert) 메서드, 라이프사이클(Lifecycle) 메서드 :: 으뜸별" style="zoom: 80%;" />

## Assertions

> 테스트 케이스의 수행 결과를 판별하는 메서드이다



### 주요 메서드

* **assertNotNull()**: 해당 객체가 null이 아닌지 검사하는 메서드이다.

* **assertEquals()**: 객체 둘을 비교하여 같은 값인지 확인하는 메서드이다.

* **assertTrue()**: 조건이 참인지 확인하는 메서드이다.

* **assertAll()**: 모든 테스트들을 확인하는 메서드이다.

  * ```java
    @Test
    @DisplayName("공부안하러가자")
    void 공부안하러가자() {
        String study = "";
        assertAll(
            () -> assertEquals("study", study, "study는 \"study\"와 같다."), // 첫번째 executable
            () -> assertTrue(study.length() != 0, "공부 주제가 없다.") // 두번째 executable
        );
    }
    ```

    <img src="https://blog.kakaocdn.net/dn/me348/btq27m7cmai/G8xEEZL8ZyteTAdOaBkfL0/img.png" alt="img" style="zoom: 67%;" />

  * 람다식을 사용해 파라미터에 메서드들을 담는다.