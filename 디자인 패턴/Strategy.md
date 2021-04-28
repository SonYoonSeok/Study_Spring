# Strategy

## 전략 패턴이란

> 전략 패턴(strategy pattern) 은 실행 중에 알고리즘을 선택할 수 있게 해주는 디자인 패턴이다. 전략 패턴은 특정한 계열의 알고리즘들을 정의하고 각 알고리즘을 **캡슐화**하여 이 알고리즘들을 해당 계열 안에서 상호 교체가 가능하게 만든다.



### 예시

```java
public class MyProgram {
  private SearchButton searchButton = new SearchButton(this);

  public void setModeAll () { 
    searchButton.setSearchStrategy(new SearchStratagyAll());
  }
  public void setModeImage () {
    searchButton.setSearchStrategy(new SearchStratagyImage());
  }
  public void setModeNews () {
    searchButton.setSearchStrategy(new SearchStratagyNews());
  }
  public void setModeMap () {
    searchButton.setSearchStrategy(new SearchStratagyMap());
  }

  public void testProgram () {
    searchButton.onClick();   // &quot;SEARCH ALL&quot; 출력
    setModeImage();           // 이미지검색 모드로
    searchButton.onClick();   // &quot;SEARCH IMAGE&quot; 출력
    setModeNews();            // 뉴스검색 모드로
    searchButton.onClick();   // &quot;SEARCH NEWS&quot; 출력
    setModeMap();             // 지도검색 모드로
    searchButton.onClick();   // &quot;SEARCH MAP&quot; 출력
  }
}
```

- 위 프로그램은 버튼을 눌러 모드를 설정하고 선택된 모드에 따라서, 검색 버튼을 눌렀을 때 실행되는 검색의 방식이 결정되도록 하는 프로그램입니다.



```java
public class SearchButton {

  private MyProgram myProgram;

  public SearchButton (MyProgram _myProgram) {
    myProgram = _myProgram;
  }

  private SearchStrategy searchStrategy = new SearchStratagyAll();
  
  public void setSearchStrategy (SearchStrategy _searchStrategy) {
    searchStrategy = _searchStrategy;
  }

  public void onClick () {
    searchStrategy.search();
  }
}
```

```JAva
interface SearchStrategy {
  public void search ();
}

class SearchStratagyAll implements SearchStrategy {
  public void search () {
      System.out.println(&quot;SEARCH ALL&quot;);
      // 전체검색하는 코드
  }
}

class SearchStratagyImage implements SearchStrategy {
  public void search () {
      System.out.println(&quot;SEARCH IMAGE&quot;);
      // 이미지검색하는 코드
  }
}

class SearchStratagyNews implements SearchStrategy {
  public void search () {
      System.out.println(&quot;SEARCH NEWS&quot;);
      // 뉴스검색하는 코드
  }
}

class SearchStratagyMap implements SearchStrategy {
  public void search () {
      System.out.println(&quot;SEARCH MAP&quot;);
      // 지도검색하는 코드
  }
}
```

- SearchButton 클래스에서 setSearchStrategy라는 setter를 이용해서 SearchStrategy 인터페이스를 입은 다른 검색 전략으로 바꿀 수 있다.
- SearchButton 클래스에서는 버튼이 클릭되면 searchStrategy의 **search메소드**만 실행된다. 
- 검색 전략의 방식이 변경되면 각 검색 전략 클래스의 코드를 찾아 수정하면 되고 새 검색 방식이 추가되면 SearchStrategy 인터페이스를 implements한 새로운 클래스를 만들어주면 된다.

> 이처럼 옵션들마다 행동들을 **모듈화**해서 독립적이고 상호 교체 가능하게 만드는 것이 Strategy 패턴이다.



### 전략패턴의 장점

- 컨텍스트 코드의 변경 없이 **새로운 전략을 추가**할 수 있다는 점이 있다.
  - 기존코드는 else-if 문을 추가 해야한다.
  - 전략패턴이 적용되면 인터페이스를 implements한 새로운 클래스를 추가하면 된다.
- 기존의 코드를 변경하지 않고 새로운 전략에 대해서는 새로운 클래스를 통해 관리하기 때문에 **OCP의 원칙을 준수**할 수 있는 패턴이다.



### 전략패턴의 단점

- 컨텍스트에 적용되는 알고리즘이 한두개인 경우에는 컨텍스트 코드를 변경하는 것이 편한 경우도 있다.
- 전략패턴은 **추상화기법**이기 때문에 단순한 경우에도 전략패턴을 사용해야하는 경우도 있다.
  - 랜덤한 로직을 테스트하는 경우
  - Mock 객체를 생성하여 Controller를 테스트하는 경우