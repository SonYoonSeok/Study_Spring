# 프로그래머스 Lv.2 - JadenCase 문자열 만들기

```java
class Solution {
    public String solution(String s) {
        String[] str = s.split(" ");
        Stream<String> ch = Arrays.stream(str).map(ss -> ss.toLowerCase());
        List<String> res = ch.collect(Collectors.toList());

        for (int i = 0; i < res.size(); i++) {
            String var = res.get(i);
            String[] s_var = var.split("");
            s_var[0] = s_var[0].toUpperCase();
            String to = String.join("", s_var);
            res.set(i, to);
        }
        String answer = String.join(" ", res);
        if (s.substring(s.length() - 1).equals(" ")) {
            answer += " ";
        } // 공백추가

        return answer;
    }
}
```

## 🚨 어려웠던 점

- 케이스8번 에서만 오류가 발생했다.

## 🔍 해결책

- 찾아보니 케이스 8번은 문자열 마지막에 공백이 있어서 나중에 출력할 때 공백도 포함해야 했다.
- 매개변수 s의 마지막 인덱스를 검사해서 공백이 있으면 최종 answer문자열 마지막에 공백을 추가했다.

## 🌈 느낀점

- Stream Api를 적용해보았는데 **너무 좋다.**

