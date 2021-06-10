# TDD(Test-Driven-Development)

### TDD란?

- TDD란 Test Driven Development의 약자로 '**테스트 주도 개발**'이라고 한다. 
- 짧은 개발 주기의 반복에 의존하는 개발 프로세스이며 애자일 방법론중 하나인 **eXtream Programming(XP)의 'Test-First'** 개념에 기반을 둔 단순한 설계를 중요시한다.

![img](https://blog.kakaocdn.net/dn/mG0Pb/btqBZMj04hL/iFrPHyeudxXYfxkWANylY0/img.png)

### TDD 개발주기

<Red> 단계에서는 실패하는 코드를 먼저 작성한다.

<Green> 단계에서는 테스트 코드를 성공시키기 위한 실제 코드를 작성한다.

<Yellow> 단계에서는 중복 코드 제거, 일반화 등의 리팩토링을 수행한다.

#### 중요한점

* 실패하는 테스트 코드를 작성할 때까지 실제 코드를 작성하지 않는 것
* 실패하는 테스트를 통과할 정도의 최소 실제 코드를 작성할 것



### 일반 개발 방식과 TDD 개발 방식의 비교



![img](https://blog.kakaocdn.net/dn/cXoUol/btqBWrI2z2l/ezsoBHstfTXgZfzGKQzOwK/img.png)

#### 🔹 **일반 개발 방식** 

보통의 개발 방식은 **'요구사항 분석 -> 설계 -> 개발 -> 테스트 -> 배포'**의 형태의 개발 주기를 갖는데 이러한 방식은 소프트웨어 개발을 느리게 하는 잠재적 위험이 존재한다.

* 소비자의 요구사항이 처음부터 명확하지 않을 수 있다.
* 따라서 처음부터 완벽한 설계는 어렵다.
* 자체 버그 검출 능력 저하 또는 소스코드의 품질이 저하될 수 있다.
* 자체 테스트 비용이 증가할 수 있다.

**작은 부분의 기능 수정에도 모든 부분을 테스트해야 하므로 전체적인 버그 검출이 어려워진다.**



![img](https://blog.kakaocdn.net/dn/n7tzF/btqBYtFtylr/PNz12MTxKTiEblbhdCCDKK/img.png)



#### 🔹 TDD 개발 방식

TDD와 일반적인 개발 방식의 가장 큰 차이점은 테스트 코드를 작성한 뒤에 실제 코드를 작성한다는 점이다.
