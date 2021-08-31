# CORS



## CORS 란?

> CORS 정책을 위반할 때 발생하는 오류이다.

![access-control-allow-origin](https://beomy.github.io/assets/img/posts/browser/access-control-allow-origin.png)

* CORS는 Cross-Origin Resource Sharing의 약자이다. 
* ex) www.a.com인데 Ajax를 할 경우 www.b.com과 같은 다른 도메인에 보낼 경우 CORS 관련 에러가 난다.



### URL의 구조

![URL 구조](https://beomy.github.io/assets/img/posts/browser/url.png)



### 출처(Origin)란?

개발자도구 콘솔창에 `location.origin` 을 실행하면 출처를 확인할 수 있다.

![image-20210831225338462](C:\Users\sting\AppData\Roaming\Typora\typora-user-images\image-20210831225338462.png)

### 같은 출처 vs 다른 출처

| URL                                             | 결과      | 이유                      |
| ----------------------------------------------- | --------- | ------------------------- |
| https://github.com/SonYoonSeok?tab=repositories | 같은 출처 | Protocal, Host, Port 동일 |
| https://github.com/SonYoonSeok/Study_Spring     | 같은 출처 | Protocal, Host, Port 동일 |
| http://github.com/SonYoonSeok                   | 다른 출처 | Protocal 다름             |
| http://github.com/SonYoonSeok:81                | 다른 출처 | Port 다름                 |
| http://github.net/SonYoonSeok                   | 다른 출처 | Host 다름                 |

