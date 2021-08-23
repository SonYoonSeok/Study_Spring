# StringTokenizer 클래스

## StringTokenizer란?

* 말 그대로 문자열을 토큰화하는 클래스이다.
* StringTokenizer를 생성하는 방식은 총 3가지가 있다.
  * `StringTokenizer st = new StringTokenizer(문자열);`
    * 띄어쓰기를 기준으로 나눠진다.
  * `StringTokenizer st = new StringTokenizer(문자열, 구분자);`
  * `StringTokenizer st = new StringTokenizer(문자열, 구분자, true/false);`
    * 구분자도 토큰으로 포함할지 (true / false)

## StringTokenizer 메서드

| 리턴값  | 메서드명          | 역할                                           |
| ------- | ----------------- | ---------------------------------------------- |
| boolean | hasMoreTokens()   | 남아있는 토큰이 있으면 true, 없으면 false 반환 |
| String  | nextToken()       | 다음 토큰을 반환                               |
| String  | nextToken(구분자) | 구분자를 기준으로 다음 토큰을 반환             |
| boolean | hasMoreElements() | hasMoreTokens()와 동일                         |
| Object  | nextElement()     | nextToken()와 동일하지만 객체룰 반환           |
| int     | countTokens()     | 총 토큰의 개수 반환                            |



### 주로 사용하는 방식

```java
public static void main(String[] args) {
    String str = "Java is funny";
    StringTokenizer st = new StringTokenizer(str);
    
    while(st.hasMoreTokens()) {
        System.out.println(st.nextToken());
    }
}
```

* while문을 사용하여 처음 토큰부터 하나씩 비교하는 방식을 주로 사용한다.



### StringTokenizer는 있지만 split()은 없는 것

StringTokenizer는 구분자를 여러개를 사용할 수 있습니다.

```java
public static void main(String[] args) {
    String str = "Java-is:funny";
    StringTokenizer st = new StringTokenizer(str, "-:");
    // 결과값 : Java
    //         is
    //		   funny
    
    while(st.hasMoreTokens()) {
        System.out.println(st.nextToken());
    }
}
```

* "-"와 ":" 두개를 모두 이용하여 토큰화 할 수 있습니다.



### StringTokenizer와 split()의 차이

* StringTokenizer는 **클래스**이고 split()은 String클래스에 속해있는 **메소드**이다.
* StringTokenizer 문자 또는 문자열로 문자열을 구분하고, split()은 정규표현식으로 구분한다.
* StringTokenizer의 결과값은 문자열이고 split()은 문자열 배열을 반환하기 때문에 StringTokenizer를 사용할 경우, 반복문으로 처음부터 하나하나 뽑아야 한다.