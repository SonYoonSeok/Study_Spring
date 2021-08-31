# Servlet (서블릿)

* 서블릿은 JVM 기반에서 웹 개발을 하기 위한 명세이자 API이다.
* 자바를 실행하려면 JRE가 필요한 것처럼 서블릿을 실행하려면 웹 애플리케이션 컨테이너가 필요하다.
* 자바의 스펙들은 JCP(Java Community Process)에서 각 스펙마다 고유의 넘버를 부여한다, 서블릿은 현재 3.x 버전까지 진행되었고, 3.1 버전을 기준으로 스펙에 대한 내용은 JSR 315에서 확인할 수 있다.

* 서블릿은 자신만의 생명주기를 가지고 있다.

![Servlet 라이프사이클 (simple)](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F2465F13F587110C111)

​	-> 컨테이너에서 콘텍스트가 초기회되면 생명주기가 시작된다.

* init() - 로드한 서블릿의 인스턴스를 생성하고 리소스를 로드하는 등 클래스 생성자의 초기화 작업괴 동일한 역할을 수행한다.
* serivce() - 클라이언트 요청에 따라서 호출할 메서드를 결정한다.
* destory() - 서블릿이 언로드된다. 이때, 서블릿이 언로드되어 서블릿의 메서드 호출 결과가 정상적으로 표출되지 않는다.

```java
@WebServlet("/init")
public class InitServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        System.out.println("init call");
    }
}
```

* 추상클래스 HttpServlet을 상속받는다.
* 실행하면 콘솔창에 "init call"을 확인할 수 있다.
* 초기화 시점에 init메서드가 호출되기 때문에 F5를 연타해도 결과가 반복되지 않는다.



## HTTP 요청과 응답

### GET 요청 처리

> 서블릿에서는 doGet메서드를 이용해서 GET 메서드 방식의 요청을 응답받을 수 있다. doGet은 HttpServletRequest와 HttpServletResponse를 파라미터로 전달받도록 되어 있다.

```java
@WebServlet(name = "HelloServlet", urlPatterns = {"/helloget"})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet 메소드 호출");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        resp.setContentType("text/html");
        writer.println("<html>");
        writer.println("<head><title>jpub java webservice</title></head>");
        writer.println("<body> get 요청 예제입니다. </body>");
        writer.println("</html>");
    }
}
```

* 웹브라우저에서 /helloget을 입력하면 doGet 메서드가 호출된다. 
* response 객체에 printWriter 인스턴스를 얻어서 HTML 내용을 출력한다.



### POST 요청 처리

```java
@WebServlet(name = "HelloServlet2", urlPatterns = {"/hellopost"})
public class HelloServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost 호출");
    }
}
```

* doPost는 post 요청에 대해서만 처리할 수 있는 메서드이기 때문에 url이 일치해도 405에러가 발생한다.



## 멀티파트

> 멀티파트는 바이너리 데이터 전송을 위해 사용한다. 서블릿 3.0 이후부터는 서블릿 스펙에 'multipart'가 추가되어 별도의 라이브러리 없이 구현이 가능해졌다.

| Annotation명       | 설명                                                         |
| :----------------- | ------------------------------------------------------------ |
| @fileSizeThreshold | fileUpload 시에 메모리에 저장되는 임시파일의 크기를 정의한다. [int] |
| @location          | 파일 업로드 시에 임시 저장 디렉토리를 지정한다. [String]     |
| @maxFileSize       | 업로드할 파일의 최대 크기를 지정한다. [long]                 |
| @maxRequestSize    | request 시에 최대 크기를 지정한다. [long]                    |



## 필터

> C언어에서는 전처리기를 이용해 컴파일 전에 필요한 작업을 미리 처리할 수 있다. 서블릿은 요청/응답 모델이므로 컴파일 전에 무엇을 할 필요는 없지만, 요청에 대해서 전처리 작업이 필요한 경우가 있다.



### 웹 필터

> init과 destory메서드를 가지고 있다.

```java
@WebFilter("*.jsp") //URL에 상관없이 *.jsp파일에 적용하기 위함
public class FilterEx implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.println("필터 동작 전");
        chain.doFilter(req, res);
        out.println("필터 동작 후");
    } // 필터의 실제작업은 doFilter 안에서 이루어진다.

    @Override
    public void destroy() {

    }
```



## 쿠키

> 쿠키(cookie)는 사용자가 사이트를 방문했을 때, 사용자의 컴퓨터에 저장되는 정보를 말한다,

* 이름 : 각각의 쿠키의 값을 식별하기 위한 키
* 값 : 특정 이름으로 쿠키에 지정된 값
* 유효 시간 : 쿠키의 유지 시간
* 도메인 : 쿠키를 전송할 도메인
* 경로 : 쿠키를 전송할 요청 경로

=> 쿠키는 HTTP 헤더 정보에 포함되어 전달된다. HTTP 프로토콜은 비연결지향으로 상태 정보를 저장하지 않는다. 이때 상태 정보를 저장할 공간이 필요하게 되며 사용할 수 있는 메커니즘 중 하나가 쿠키다.



### 쿠키 생성

> Cookie 생성자를 이용해 생성 가능하다

```Java
Cookie jcookie = new Cookie(name, value);
```

* map을 사용할 때처럼 key, value 형태로 사용하고 도메인과 최대 유효 기간 등을 설정할 수 있다.



> 쿠키를 변경하려면 같은 이름으로 쿠키를 생성해서 새로운 값을 지정하면 된다.

```java
Cookie modifiedCookie = new Cookie(name, 새로운 값);
```

* 쿠키 자체를 삭제하는 API는 존재하지 않기 때문에 유효 시간을 0으로 설정함으로써 쿠키값을 무효화 할 수 있다.

* 쿠키 값 한글 입력 시에는 URLEncoder를 이용해서 문자열을 감싸 준다.
  * new Cookie("kor", URLEncoder("데이터", "UTF-8"))



## 세션

### 세션의 구성

* 세션은 서버와 클라이언트의 유효한 커넥션을 식별하는 정보이다.

* 서버는 클라이언트가 요청을 보내면 식별할 수 있는 ID를 부여한다 -> **세션ID**

* 세션ID는 JSESSIONID라는 쿠키로 저장되고, 클라이언트가 재접속할 때 해당 쿠키를 이용해 세션 ID 값을 서버에 전달한다.

* HttpSession 인터페이스로 정의되어 있다.

  

### 세션의 생성

> request 객체에서 꺼내서 사용할 수 있다.

```java
request,getSession()
```



### 세션정보 출력

* getId(): 세션의 고유 아이디를 얻을 수 있는 메서드이다.
* getCreationTime(): 세션이 생성된 시간을 얻을 수 있는 메서드다.
* getLastAccessTime(): 웹 브라우저가 가장 마지막에 세션에 접근한 시간을 얻을 수 있는 메서드다.

