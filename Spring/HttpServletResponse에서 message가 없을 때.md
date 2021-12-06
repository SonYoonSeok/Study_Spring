# HttpServletResponse에서 message가 없을 때



```json
{
    "timestamp": "2021-11-30T13:26:51.321+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/user/find"
}
```

* 개발을 하던 중 에러 Response가 message가 없이 돌아옴
* 스프링 2.3 버전 이후 클라이언트에서 정보가 새어나가는 리스크를 줄이기 위해 포함하지 않음 (참고: https://www.baeldung.com/spring-response-status-exception)
* `application.properties`에 `server.error.include-message=always`를 추가해주면 됨

