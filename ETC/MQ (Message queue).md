# MQ (Message queue)



## MQ란?

> 메시지 지향 미들웨어 (Message Oriented Middleware:MOM)을 구현한 시스템을 **메세지 큐**라 한다.

MQ는 프로세스 또는 프로그램 인스턴스가 데이터를 서로 교환할 때 사용하는 방법입니다. 서로 다른 프로셋나 프로그램 사이에 메세지를 교환할 때 **AMQP(Advanced Message Queuing Protocol) **를 이용합니다. AMQP는 메세지 지향 미들웨어(MOM)를 위한 open standard application layer protocol입니다. 



### MQ의 구조

<img src="https://blog.kakaocdn.net/dn/bI9sqz/btqG4bsU0gr/V0ZoAlm3hK6RM3CKNnx0j0/img.png" height = 60%, width = 60%>

* Producer, Consumer가 존재하며 Producer는 메세지를 큐에 전송하고 Consumer가 큐의 메세지를 처리한다.
* 비동기로 요청을 처리하고 Queue에 저장한다.



### 사용 이유

<img src="https://www.cloudamqp.com/img/blog/message-queue-small.png" height = 80%, width = 80%>

* 시스템 장애가 일어나서 서버가 다운될 동안. 서버로 보내진 요청들이 Queue에 보관이 된다.
* 보관된 Message들은 나중에 서버가 다시 안정화 되면 **Consumer**에서 메세지를 **소비**하면서 처리하게 된다.



### 장점

* **비동기, 비동조, 탄력적, 과잉, 보증, 확장성**
  * **비동기** :**Queue**에 넣기 때문에 나중에 처리 가능
  * **비동조** : 애플리케이션과 분리 가능
  * **탄력성** : 일부가 실패 시 전체에 영향 받지 않음
  * **과잉** : 실패할 경우 재실행 가능
  * **보증** : 작업이 처리된 것을 확인 가능
  * **확장성** : 다수의 프로세스들이 큐에 메세지를 보낼 수 있음
* 부하 분산
  * 분산되어 있던 데이터 처리를 한 곳에 집중하면서 메세지 브로커를 두어서 필요한 프로그램에 작업을 분산
* 데이터 손실 방지
* Failover 커버 가능