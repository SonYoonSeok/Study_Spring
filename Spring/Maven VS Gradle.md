# Maven VS Gradle

### Maven 이란?

* Java를 사용한 프로젝트 빌드 자동화에 사용
* 빌드 중인 프로젝트, 빌드 순서, 다양한 외부 라이브러리 의존성 관게를 pom.xml에 명시
* 외부 저장소에 필요한 라이브러리와 플러그인들을 로컬 시스템의 캐시에 모두 저장



### Gradle 이란?

* Maven과 Ant에서 볼 수 있는 개념들을 사용하는 대안으로써 나온 빌드 관리 툴
* Groovy언어를 사용하여 Maven보다 코드가 간결
* 업데이트가 이미 반영된 부분은 더 이상 재실행하지 않음



## 무엇이 더 좋은가

![img](https://blog.kakaocdn.net/dn/Ya1l9/btqFrTBrToJ/W7jxLGAKQjmMdmj3Hburq0/img.png)

* Configuration Injection 방식을 사용한 Gradle은 공통 모듈을 상속해서 사용하는 Maven의 단점을 커버함으로써 속도가 빠름
* XML 특성 상 설정 내용이 길어지고 가독성이 떨어짐



### 왜 기업체에서는 Gradle을 사용하지 않는가

2004년에 출시한 Maven과 다르게 Gradle은 2012년에 출시하였다. 따라서, 상대적으로 오래된 Maven이 더욱 익숙한 것은 사실이다. 또한, Gradle을 사용하기 위해 프로젝트의 팀원들이 Groovy 문법을 익혀야 하는 것은 큰 걸림돌이 된다.

여전히 Maven의 사용률은 Gradle보다 앞서고 있으며 구글 트랜드 지수마저 앞서고 있다. 하지만 앞으로 Gradle이 더욱 발전하고 속도가 빨라진다면 Gradle을 사용하는 기업체들도 많이 생겨날 것 같다.

