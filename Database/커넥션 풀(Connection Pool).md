# Connection Pool



## Connection Pool 이란?

WAS(Web Application Service)컨테이너가 실행 될 때 Connection Pool에 일정 수의 Connection 객체를 만들어 담아두고 사용자의 요청이 발생하면 **Pool에 생성되어 있는 Connection 객체를 넘겨**주고 사용자가 **사용이 끝나면 Connection 객체를 반환**하여 보관하는 기법

#### 사용이유

자바에서 DB에 직접 연결해서 처리는 경우 JDBC 드라이버를 로드하고 Connection 객체를 받아와야 한다. 그러면 사용자 요청마다 드라이버를 로드하고 Connection 객체를 **생성하여 연결하고 종료**해야 하기 때문에 **매우 비효율적**

때문에 Connection Pool을 사용하면 매 요청마다 Connection을 맺고 끊을 필요가 없기에 **성능이 향상되고**, **데이터를 처리하는 속도**가 빨리짐



## Connection Pool 동작 과정

<img src="https://server.happykoo.net/uploads/202007/blob1595512113487" >

#### 1. 서버와 DB가 일정 수의 Connection을 맺은 후 Pool에 저장

#### 2. 사용자의 요청이 발생하면 서버가 Pool에 Connection을 요청

#### 3. Connection을 얻은 후 쿼리를 실행하여 데이터를 read/write

#### 4. Connection을 Pool에 반납



## Connection Pool 주의사항

* Pool에 최대로 저장되는 Connection 수는 정해져 있기 때문에 Connection이 모두 사용 중 이라면 반납될 때까지 대기해야함
* Connection 수를 너무 많이 설정하면 메모리를 많이 차지하는 Connection Pool 특성 상 성능을 떨어뜨릴 수 있음
* Connection Pool은 유저 수에 따라 Connection 수를 적절하게 사용해야함
* 자바와 같은 multi thread 환경에서는 thread가 task를 실행하는데 필요한 Connection은 많은데에 비해 Pool에 저장된 Connection 수가 적다면, 교착상태에 빠질수 있음

