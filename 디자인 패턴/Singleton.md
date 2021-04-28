# Singleton

## 싱글턴 패턴이란

	* 전역변수를 사용하지 않고 **객체를 하나만 생성**하도록 하며, 생성된 객체를 **어디에서든지 참조할 수 있도록** 하는 패턴
* 여러 스레드가 **동시에** 해당 인스턴스를 공유하여 사용하게끔 할 수 있으므로, **요청이 많은 곳**에서 사용하면 효율을 높일 수 있음
* 싱글턴을 만들때 **동시성** 문제를 고려해서 설계해야함

## 자바의 싱글턴 패턴

> 싱글턴 패턴의 공통적인 특징은 **private constructor** 를 가지는 것과, **static method** 를 사용한다는 점입니다.

### 싱글턴 패턴 구현에 사용되는 이디엄 방식

####  - Eager Initialization(이른 초기화, Thread-safe)

```java
public class Singleton {
    // Eager Initialization
    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
      return uniqueInstance; 
    } 
}
```

- 이른 초기화 방식은, static 키워드의 특징을 이용해서 클래스 로더가 초기화 하는 시점에서 **정적 바인딩(static binding) (컴파일 시점에서 성격이 결정됨)** 을 통해 해당 인스턴스를 메모리에 등록해서 사용하는 것이다.
- 클래스 로더에 의해 클래스가 최초로 로딩 될 때 객체가 생성되기 때문에 **Thread-safe** 합니다.
  - **Thread-safe** : 멀티 스레드 프로그래밍에서 어떤 함수나 변수, 혹은 객체가 여러 스레드로부터 동시에 접근이 이루어져도 프로그램 실행에 문제가 없음
- 싱글턴 구현 시 중요한 점이, 멀티 스레딩 환경에서도 동작 가능하게끔 구현해야 한다는 것입니다. 즉 **Thread-safe** 가 보장되어야 합니다.



#### - Lazy Initialization with synchronized(동기화 블럭, Thread-safe)

```java
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

    // Lazy Initailization
    public static synchronzied Singleton getInstance() {
      if(uniqueInstance == null) {
         uniqueInstance = new Singleton();
      }
      return uniqueInstance;
    }
}
```

- **synchronized** 키워드를 이용한 게으른 초기화 방식, 메서드에 동기화 블럭을 지정해서 **Thread-safe** 를 보장합니다.
- 컴파일 시점에 인스턴스를 생성하는 것이 아닌, 인스턴스가 필요한 시점에 요청하여 **동적 바인딩(dynamic binding) (런타임시에 성격이 결정됨)** 을 통해 인스턴스를 생성하는 방식을 말합니다.
- 인스턴스가 생성되었든,  안되었든 무조건 동기화 블록을 거치게 된다.
- **synchronized** 키워드를 사용하면 성능이 약 100배 가량 떨어집니다
  - **synchronized** : 여러 개의 쓰레드가 하나의 자원에 접근하려 할 때 주어진 순간에는 오직 하나의 쓰레드만이 접근 가능하도록 가는 키워드



#### - Lazy Initialization.Double Checking Locking(DCL, Thread-safe)

```Java
public class Singleton {
    private volatile static Singleton uniqueInstance; //volatile

    private Sigleton() {}

    // Lazy Initialization. DCL
    public Singleton getInstance() {
      if(uniqueInstance == null) {
         synchronized(Singleton.class) {
            if(uniqueInstance == null) {
               uniqueInstance = new Singleton(); 
            }
         }
      }
      return uniqueInstance;
    }
}
```

- **volatile** 키워드를 사용하면 멀티스레딩을 쓰더라도 uniqueInstance 변수가 Singleton 인스턴스로 초기화 되는 과정이 올바르게 진행되도록 할 수 있습니다.

  - **volatile** : Java 변수를 **Main Memory**에 저장하겠다라는 것을 명시한다.

    > - 매번 변수의 값을 Read할 때마다 **CPU cache**에 저장된 값이 아닌 **Main Memory**에서 읽는 것입니다.
    >
    > - 변수의 값을 Write할 때마다 **Main memory**에 까지 작성하는 것입니다.

  - 사용 이유

    > - volalite 를 사용하고 있지 않는 변수는 MultiThread 어플리케이션에서 Task를 수행하는 동안 성능 향상을 위해 Main Memory에서 읽은 변수 값을 CPU Cache에 저장하게 됩니다.
    > - Multi Thread환경에서 Thread가 변수 값을 읽어올 때 각각의 CPU Cache에 저장된 값이 다르기 때문에 **변수 값 불일치 문제**가 발생합니다.

<u>즉, **volalite**변수는 **Main Memory**에 값을 저장하고 읽어오기 때문에 **변수 값 불일치 문제**가 생기지 않습니다.</u>



#### - Lazy Initialization.Enum(열거 상수 클래스, Thread-safe)

```java
public enum Singleton {
    INSTANCE; 
}
```

- **Enum 인스턴스**의 생성은 기본적으로 Thread-safe 합니다. 따라서 스레드 관련된 코드가 없어져서 코드가 간단해집니다. 하지만 Enum 내의 다른 메서드가 있는 경우, 해당 메서드가 Thread-safe 한지는 개발자가 책임져야 합니다.
- Enum 방식을 사용하면 아주 복잡한 직렬화 상황이나, 리플렉션 공격에도 제 2의 인스턴스가 생성되는 것을 막아줍니다.
- Enum 외의 클래스를 **상속해야 하는 경우**에는 사용할 수 없습니다.



#### - Lazy Initialization.LazyHolder(게으른 홀더, Thread-safe)

```java
public class Singleton {

    private Singleton() {}

    /**
     * static member class
     * 내부클래스에서 static변수를 선언해야하는 경우 static 내부 클래스를 선언해야만 한다.
     * static 멤버, 특히 static 메서드에서 사용될 목적으로 선언
     */
    private static class InnerInstanceClazz() {
        // 클래스 로딩 시점에서 생성
        private static final Singleton uniqueInstance = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerInstanceClazz.instance;
    }
    
}
```

- **LazyHolder**방식은 가장 많이 사용되는 싱글턴 구현 방식입니다.

- 싱글턴 클래스에는 InnerInstanceClazz 클래스의 변수가 없기 때문에, static 멤버 클래스더라도, 클래스 로더가 초기화 과정을 진행할때 InnerInstanceClazz 메서드를 초기화 하지 않고, getInstance() 메서드를 호출할때 초기화 됩니다.
- **동적바인딩(Dynamic Binding)** 의 특징을 이용하여 **Thread-safe** 하면서 성능이 뛰어납니다.
- InnerInstanceClazz 내부 인스턴스는 static 이기 때문에 클래스 로딩 시점에 한번만 호출된다는 점을 이용한 것이며, **final**을 써서 다시 값이 할당되지 않도록 합니다.



