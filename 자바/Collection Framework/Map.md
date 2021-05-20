# Map

### 맵은 무엇인가

- key와 value로 이루어짐
- key로 검색을 하면 key에 대한 value값이 출력됨
- key는 중복X



```java
public static void main(String[] args){
    HashMap<String, Integer> a = new HashMap<String, Integer>(); //key : String, value : Integer
    a.put("one", 1);
    a.put("two", 2);
    a.put("three", 3);
    a.put("four", 4);
    System.out.println(a.get("one")); //1을 리턴
    System.out.println(a.get("two")); //2를 리턴
    System.out.println(a.get("three")); //3을 리턴
}
```

