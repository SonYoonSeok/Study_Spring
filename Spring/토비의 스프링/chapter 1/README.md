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



