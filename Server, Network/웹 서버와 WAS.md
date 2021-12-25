# 웹 서버와 WAS



## 웹 서버

> HTTP를 통해 웹 브라우저에서 요청하는 HTML 문서나 오브젝트(이미지 파일 등)을 전송해주는 서비스 프로그램

<img src="https://upload.wikimedia.org/wikipedia/commons/1/1e/Web_server_serving_static_content.png">

* 클라이언트가 브라우저로 웹 서버에 요청한 요청을 받아 **정적 컨텐츠**를 제공
  * HTML, CSS, javascript, 이미지, 파일 등 즉시 응답가능한 컨텐츠
* 동적 컨텐츠 요청을 받으면 WAS에 요청을 **넘겨**주고 그 결과를 **클라이언트**에 **전달**해주는 역할



## WAS (Web Application Server)

> 웹 애플리케이션과 서버 환경을 만들어 동작시키는 기능을 제공하는 프레임워크
>
> HTTP를 통해 사용자 컴퓨터나 장치에 애플리케이션을 수행해 주는 미들웨어라고 볼 수 있음,,

<img src="https://media.vlpt.us/images/dbfudgudals/post/e26f266c-f304-4a49-bfa9-4e0a8c0d00d8/image.png" width = 70%, height = 70%>

* 웹 서버 단독으로 **처리할 수 없는** 동적 컨텐츠를 제공
  * 데이터베이스 조회
  * 로직 처리

* JSP, Servlet 구동환경을 제공해주기 때문에 **웹 컨테이너**, **서블릿 컨테이너**라고도 불린다



## 효율적인 웹 서비스 

<img src="https://gmlwjd9405.github.io/images/web/web-service-architecture.png">



* WAS는 DB 조회, 로직 처리 등을 처리하는데에 집중해야 함
* 단순한 정적 컨텐츠는 Web Server가 담당하게 하여 **서버 부하 방지**