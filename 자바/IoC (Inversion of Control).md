# IoC (Inversion of Control)



### 컨테이너란?

> **컨테이너는 보통 인스턴스 생명주기를 관리, 생성된 인스턴스들에게 추가적인 기능을 제공하도록 하는 것이라고 한다**. 즉, 컨테이너란 작성한 코드의 처리과정을 위임받은 독립적인 존재라고 생각하면 된다.

* Servlet 컨테이너는 Servlet의 생성, 생성 후 초기화, 서비스 실행, 소멸에 관한 모든 권한을 가지고 있다.
* Servlet 컨테이너의 web.xml을 보면 JSP/Servlet 접근 권한에 대한 추가적인 서비스도 지원한다.
* 스프링 컨테이너는 스프링 프레임워크 핵심부에 위치하며, 종속객체 주입을 이용하여 애플리케이션을 구성하는 컴포넌트들을 관리한다.



### IoC의 개념

> 참고 https://www.youtube.com/watch?v=QrIp5zc6Bo4  
>
> IoC (Inversion of Control, 제어권의 역전) 이라고 한다.
> 객체의 생성, 생명주기의 관리까지 모든 객체에 대한 제어권이 바뀌었다는 것을 의미한다.

* Servlet Container, EJB Container을 사용하는 경우 제어권이 넘어가서 객체의 생명주기를 Container들이 전담하게 된다. 
* 즉, **인스턴스 생성부터 소멸까지의 인스턴스 생명주기 관리를 개발자가 아닌 컨테이너가 대신 해준다는 뜻이다.** 



### Ioc 컨테이너의 핵심 클래스

<img src="https://t1.daumcdn.net/cfile/tistory/212AF435589B0BA51F"  width=70%, height=70%>

#### 1) BeanFactory

* DI 기본사항을 제공
* Bean을 생성하고 분배하는 작업을 함
* getBean()이 호출되면, 의존성 주입을 통해 빈을 인스턴스화 하고 빈을 설정함

```java
// getBean 메소드
@Override
public <T> T getBean(Class<T> requiredType) throws BeansException {
    assertBeanFactoryActive();
    return getBeanFactory().getBean(requiredType);
} // getBeanFactory() 메소드를 호출 후 getBean() 메소드를 호출한다
// AbstractApplicationContext.getBeanFactory 메소드는 추상 메소드다
```



#### 2) ApplicationContext

* BeanFactory오 유사하지만 더 많은 기능을 제공한다.

* IoC 컨테이너의 기능과 EnvironmentCapable, ApplicationEventpublisher, MessageSource, ResourceLoader 등 기능을 가진다
  * EnvironmentCapable : 로컬, 개발, 운영 등을 구분해서 환경설정을 해주는 인터페이스이다.
  * ApplicationEventpublisher : ApplicationContext에 상속이 되어있다, 이벤트 프로그래밍을 할 때 사용된다.
  * MessageSource : 메세지 프로퍼티 파일을 만든 후, 메세지를 호출할 수 있다.
  * ResourceLoader :  ApplcationContext에 상속이 되어있다, getResource 메소드를 이용하여 Resource 객체를 반환받아 객체의 여러 정보를 받아 온다.