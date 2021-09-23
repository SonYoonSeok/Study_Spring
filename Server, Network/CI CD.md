# CI/CD

<img src="https://blog.kakaocdn.net/dn/uoHsi/btqCX88hUI9/QYRT2aRll0K78yIs4a9IT0/img.png" height=60%, width=60%>

## CI/CD란

* CI는 **Continuous Integration (지속적 통합)**, CD는 **Continuous Deployment (지속적 배포)**를 줄인 말로 서로 개념이지만 보통 함께 구축되므로 묶어서 부른다.

* 프로그래머들이 개발을 끝낸 후 이를 **최종 배포**까지 하는 과정에서 진행되는 일들이다. 보통 이를 **자동화**한다는 의미로 사용한다.



### CI (지속적 통합)

![Git merge와 Git rebase](https://media.vlpt.us/images/gil0127/post/a8376b33-a60a-4734-bd8a-71f871033aab/11111111111.png)

여러 소프트웨어들은 팀에 의해 개발되는 경우가 많은데, 보통 Git과 같은 **버전 컨트롤 시스템**을 사용하여 각자가 작업한 코드를 하나의 결과물로 **통합**을 한다. 하지만, 코드를 합칠 때 코드에 문제가 없는지 확인을 하지 않으면 실제 서비스로 배포하고 나서 문제가 발생할 수 있다. 

하지만, 매번 모든 코드들을 하나하나 확인하고 오류를 확인하기에는 시간이 오래걸리고 불편하기 때문에 이를 자동화해주는 다양한 도구와 서비스들이 있다.



### CD (지속적 배포)

CI가 성공적으로 진행되고 나면, 소프트웨어를 최종사용자에게 실행 가능하도록 하여 넘겨주는 작업을 말한다. 이 역시 개발을 마칠 때마다 자동적으로 해주므로 **Continuous**가 붙는다.



## 주요 CI/CD 툴



## 젠킨스

<img src="https://nesoy.github.io/assets/logo/Jenkins.PNG" height=60%, width=60%>

* **설치형**으로, 컴퓨터나 서비스가 돌아갈 서버에 직접 다운받고 깔아서 쓰는 툴이다.
* **오픈소스**로 무료로 사용 가능하다.
* 보통 서버로 많이 쓰이는 **리눅스**에 자주 사용된다.

* 젠킨스를 설치하면 브라우저로 접속할 수 있는 웹사이트가 하나 열리는데, 이 사이트에 아이디와 비밀번호를 치고 접속하면 다음과 같이 다양한 CI/CD 작업을 세팅할 수 있는 페이지가 나온다.

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile7.uf.tistory.com%2Fimage%2F996E39425C5CE01106C7A5" height=70%, width=70%>



## Github Actions

<img src="https://blog.kakaocdn.net/dn/DmLnQ/btq93oLL7HQ/RXBkrkgX0YdE9Ziqv4H6FK/img.png">

* Github에서 자신의 레포지토리 상단에 Actions 탭에서 각 언어별로, 또는 AWS나 Azure 등의 배포처 별로 소스코드를 빌드, 테스트하고 배포 등을 할 수 있는 다양한 템플릿이 존재한다.
* 필요한 것들을 조합하거나 직접 yml 파일로 작성하여 설정할 수 있다.
* 클라우드 공간에서 CI/CD를 모두 할 수있는 툴이다.

