# AOP

## AOP란

* OOP를 보완하는 수단으로, 흩어진 Aspect들을 모듈화하는 기법이다.

### 주요 개념

* Aspect

  * 여러 곳에서 쓰는 코드를 모듈화한 것

* Target

  * Aspect 가 적용되는 곳

* Joint point

  * Advice 가 Target 에 적용되는 시점

* Point cut

  * Joint point 의 상세 스펙을 정의한 것

  

## 특징

* 프록시 기반이 AOP 구현체
* 스프링 빈에만 AOP를 적용할 수 있다.
* 동적 프록시 빈을 만들어 등록시켜준다.



## Annotation

* `@Pointcut` : aspectJ를 적용할 타겟을 정의한다. 전체 컨트롤러의 함수 대상, 특정 어노테이션을 설정한 함수대상, 특정 메소드 대상 등 개발자가 적용하길 원하는 범위를 정의한다.
* `@Before` : aspectJ를 적용할 타겟 메소드가 실행되기 전 수행된다.
* `@AfterReturning` : aspectJ를 적용할 타겟 메소드가 실행된 후 수행된다.
* `@Around` : aspectJ를 적용할 타겟 메소드 실행 전, 실행 후 처리를 모두 할 수 있다.