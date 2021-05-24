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