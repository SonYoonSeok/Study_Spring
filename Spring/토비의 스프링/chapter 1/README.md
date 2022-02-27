# 오브젝트와 의존 관계

> 두 가지 관례를 따라 만들어진 오브젝트를 Bean이라고 한다.
>
> * 디폴트 생성자 : 파라미터가 없는 생성자를 가져야 한다, 프레임워크에서 **리플렉션**을 이용해 오브젝트를 생성
>   * 리플렉션 : 클래스의 타입을 모를 때, Static 영역에 위치한 클래스 파일을 뒤져서 그 클래스에 접근하게 해주는 자바 API
> * 프로퍼티 : set으로 시작하는 수정자 메소드(setter)와 get으로 시작하는 접근자 메소드(getter)를 이용해 수정, 조회



> jdbc api를 이용한 Dao (책에는 mysql로 되어있지만 postgresql로 진행함)

```java
public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practice", "postgres", "1234");

        PreparedStatement ps = c.prepareStatement( // Statement보다 가독성, 효율성이 좋다.
                "insert into users(id, name, password) values(?, ?, ?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practice", "postgres", "1234");

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery(); // ResultSet 타입으로 반환해준다.
        rs.next(); // 선택되는 행을 바꿀 수 있다.
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
```

> 중복 코드, 객체지향 X 등 여러 문제를 가진 초난감 Dao가 완성된다.



## 관심사 분리

> 변경이 일어날 때 필요한 작업을 최소화하고, 그 변경이 다른 곳에 문제를 일으키지 않게 할 수 있게 해야한다.
>
> 위 dao에서는 db와 연결을 위한 커넥션을 가져오는 관심사가 중복되어 있고 여기저기 흩어져 있다.

#### 중복 코드의 메소드 추출

```java
public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        ... // 생략
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        ... // 생략
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practice", "postgres", "1234");

        return c;
    }
}
```

> 드라이버 클래스와 url을 바꿔야할 상황이 올 때, 메소드가 몇 백 몇 천개가 되더라도 getConnection 메소드만 수정하면 된다.



## 상속을 통한 확장

> 슈퍼클래스에 기본적인 로직의 흐름을 만들고, 일부 기능을 추상 메소드나 오버라이딩이 가능한 메소드로 만든 뒤 서브클래스에서 필요에 맞게 구현해서 사용한다. ***템플릿 메소드 패턴***
>
> 서브클래스에서 구체적인 오브젝트 생성 방법을 결정하게 하게 한다. ***팩토리 메소드 패턴***

```java
public abstract class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}

public class NUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // N 사의 DB connection 생성코드
    }
}

public class DUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // D 사의 DB connection 생성코드
    }
}
```

> 위 코드는 독립적으로 변경 또는 확장할 수 있도록 만들어졌지만 상속이라는 단점이 존재한다.
>
> UserDao외에 다른 DAO클래스들이 계속해서 만들어진다면 getConnection() 코드가 각 DAO 클래스마다 중복된다.



# DAO의 확장

> 클래스를 상하위 클래스로 분리한 상속관계가 아닌 완전히 독립적인 클래스로 만들 수 있다.

```java
public class SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException,
    SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practice", "postgres", "1234");

        return c;
    }
}
```

```java
public class UserDao {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDao() {
        simpleConnectionMaker = new SimpleConnectionMaker(); // 생성자로 인스턴스화 하여 사용
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.makeNewConnection();
        ...// 생략
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.makeNewConnection();
		...// 생략
    }
}
```

> 이렇게 분리한 경우에도 자유로운 확장이 가능하게 하기 위해서는 두 가지 문제를 해결해야 한다.
>
> * makeNewConnection()을 통해 DB 커넥션을 로드하게 하였는데, 만약 이름을 openConnection()이라는 메소드로 바꾸게 된다면, `Connection c = simpleConnectionMaker.makeNewConnection();` 로 일일이 변경해야 한다.
> * UserDao가 SimpleConnectionMaker에 종속적이다. 클래스 내 메소드의 이름까지 알고 있어야 하기 때문에 자유롭게 확장하기가 어려워졌다.



## 인터페이스의 도입

> 두 클래스가 긴밀하게 연결되어 있으면서 중간에 **추상적인 연결고리**를 만들어주는 것이다.
>
> 이를 위한 자바가 추상화를 위해 제공하는 **인터페이스**를 사용한다. 인터페이스를 통해 접근하면 실제 구현 클래스를 바꿔도 신경쓰지 않아도 된다.

```java
public interface ConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, 
    SQLException;
} 
```

```java
public class DConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practice", "postgres", "1234");

        return c;
    }
} // ConnectionMaker 인터페이스 구현 클래스
```

```java
public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao() {
        connectionMaker = new DConnectionMaker(); // 아직 DConnectionMaker클래스에 의존적이다.
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeNewConnection(); // 인터페이스에 정의한 이름으로 사용하므로 클래스가 바뀌어도 이름이 변경될 걱정은 없다.
		...// 생략
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeNewConnection();
        ...// 생략
    }
}
```

> ConnectionMaker 인터페이스를 사용했음에도 UserDao가 DConnectionMaker를 알고 있기 때문에 불필요한 의존관계가 생긴다.
>
> 때문에 UserDao의 모든 코드는 ConnectionMaker 인터페이스 외에는 어떤 클래스와도 관계를 가지게 해서는 안된다.
> 	-> UserDao의 클라이언트인 main()메소드에서 파라미터로 특정 ConnectionMaker 구현체를 넘겨준다.

```java
public class UserDao {
    ...//
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
```

```java
public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao dao = new UserDao(connectionMaker);
        ...// 생략
    }
```

> main() 메소드에 UserDao와 ConnectionMaker 구현체 사이에 의존관계를 연결 시켜주는 책임을 전가시켰다.

<hr/>

## 원칙과 패턴

> 지금까지 해온 작업들을 이론을 통해 어떤 장점이 있는지 설명할 수 있다.

### 개방 폐쇄 원칙

**개방 패쇄 원칙 (OCP, Open-Closed Principle)**은 객체지향 설계 원칙(SOLID) 중 하나이다. 이 원칙은 '클래스나 모듈은 확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다.'라고 정의할 수 있다. UserDao에 전혀 영향을 주지 않고 DB 연결 방법이라는 기능을 확장할 수 있기 때문이다. 또한 인터페이스를 사용하는 클래스들은  자신의 변화가 불필요하게 일어나지 않도록 폐쇄되어 있다.

### 높은 응집도와 낮은 결합도

> 개방 폐쇄 원칙은 **높은 응집도와 낮은 결합도**라는 원리로도 설명이 가능하다. 
>
> * **높은 응집도**
>   응집도가 높다는 것은 하나의 모듈, 클래스가 하나의 책임 또는 관심사에만 집중되어 있다는 뜻이다. 변화가 일어날 대 해당 모듈애서 변하는 부분이 크고 변경이 일어날 때 모듈의 많은 부분이 함께 바뀐다면 응집도가 높다고 할 수 있다.
> * **낮은 결합도**
>   높은 응집도보다 더 민감한 원칙으로 책임과 관심사가 다른 오브젝트 또는 모듈과 느슨하게 연결된 형태를 유지하는 것이다. 느슨한 연결은 관계를 유지하는 데 꼭 필요한 최소한의 방법만 간접적인 형태로 제공하고 나머지는 독립적으로 만들어주는 것이다.

### 전략 패턴

> UserDaoTest-UserDao-ConnectionMaker 구조를 **전략 패턴** 이라고 볼 수 있다. 자신의 기능 맥락(context)에서, 필요에 따라 변경이 필요한 알고리즘을 인터페이스를 통해 외부로 분리시키고, 이를 구현한 구체적인 알고리즘 클래스를 필요에 따라 바꿔서 사용할 수 있게 한다.



<hr/>

# 제어의 역전 (IoC)

> IoC라는 약자로 많이 사용되는 제어의 역전(Inversion of Control)은 상당히 오래전부터 있던 개념으로 스프링을 통해 일반 개발자들에게 많이 알려졌다.



## 오브젝트 팩토리





