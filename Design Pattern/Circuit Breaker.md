# Circuit Breaker

### MSA 패턴의 문제점

MSA 패턴은 시스템을 여러 개의 서비스 컴포넌트로 나눠서 호출하는 개념을 가진다. 이 아키텍쳐는 하나의 컴포넌트가 느려지거나 장애가 발생하면 컴포넌트를  호출하는 종속된 컴포넌트까지 장애가 전파되는 특성을 지닌다.

<img src="https://t1.daumcdn.net/cfile/tistory/99E6754C5AC39FAA08" height=60%, width=60%>

### Circuit Breaker 패턴

이런 문제를 해결하는 디자인 패턴이 Circuit Breaker라는 패턴이 있다. Service A와 Service B에 Circuit Breaker를 설치하면 Service B로의 모든 호출은 Circuit Breaker를 통하게 되고 정상적인 상황에서는 bypass를 하게 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/99427C475AC39FAA08" height=60%, width=60%>

Service B의 문제를 Circuit Breaker가 감지한다면 Service B로의 호출을 강제적으로 끊어서 Service A에서 발생하는 전파 장애를 방지 한다.

<img src="https://t1.daumcdn.net/cfile/tistory/993FD73B5AC39FAA17" height=60%, width=60%>

