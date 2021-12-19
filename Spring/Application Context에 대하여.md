# Application Context에 대하여

>  Application Context를 알기 전 Ioc에 대해 먼저 알아야 한다.



## Ioc (제어의 역전)

* Ioc란 간단하게 객체의 생성, 생명주기의 관리 등 객체에 대한 모든 제어권이 바뀌었다는 것을 의미한다. 
* 자바 기반의 어플리케이션에서 자바 객체를 생성하고 의존관계를 연결시키는 작업을 여러 Container들에게 생명주기를 분담하는 것이다. ex) Servlet, EJB를 사용하는 경우 Servlet Container, EJB Container
* **인스턴스의 생성부터 소멸까지 개발자가 아닌 Container가 대신 해준다**



## ApplicationContext

* 객체를 생성하고 DI를 처리해주!는 BeanFactory를 확장하여 구현한 보다 추가적인 기능을 제공하는 컨테이너이다.
* BeanFactory는 객체를 가지고있다가 getBean()을 호출해야 해당 빈을 인스턴스화 하는 LazyLoading이지만 ApplicationContext는 컨텍스트 초기화 시점에 모든 싱글톤 빈을 인스턴스화 한 후 지연 없이 Bean을 얻을 수 있다. (PreLoading)

* 따라서 대부분의 상황에서는 ApplicationContext를 사용한다.