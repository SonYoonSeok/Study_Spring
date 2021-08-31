## 제네릭이란?

> 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법



```java
class Person<T> {
    public T info;
}
Person<String> p1 = new Person<String>(); 
//T가 String이 된다, String info

Person<StringBuilder> p2 = new Person<StringBuilder>(); 
//T가 StringBuilder가 된다, StringBuilder info
```



## 제네릭의 사용이유

```Java
class StudentInfo{
    public int grade;
    StudentInfo(int grade){ this.grade = grade; }
}
class StudentPerson{
    public StudentInfo info;
    StudentPerson(StudentInfo info){ this.info = info; }
}
class EmployeeInfo{
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
}
class EmployeePerson{
    public EmployeeInfo info;
    EmployeePerson(EmployeeInfo info){ this.info = info; }
}
public class GenericDemo {
    public static void main(String[] args) {
        StudentInfo si = new StudentInfo(2); //StudentInfo의 grade = 2
        StudentPerson sp = new StudentPerson(si); //si는 StudentPerson의 인자로 들어감
        System.out.println(sp.info.grade); // 2
        EmployeeInfo ei = new EmployeeInfo(1); //EmployeeInfo의 rank = 1
        EmployeePerson ep = new EmployeePerson(ei); //ei는 EmplaoyeePerson의 인자로 들어감
        System.out.println(ep.info.rank); // 1
    }
}
```

- StudentPerson과 EmployeePerson은 같은 형태의 클래스이다 (중복)
  - 하나의 클래스로 만들어줄 수 있다.



```java
class StudentInfo{
    public int grade;
    StudentInfo(int grade){ this.grade = grade; }
}
class EmployeeInfo{
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
}
class Person{
    public Object info; //어떠한 클래스의 인스턴스도 올 수 있다.
    Person(Object info){ this.info = info; }
}
public class GenericDemo {
    public static void main(String[] args) {
        Person p1 = new Person("부장");
        EmployeeInfo ei = (EmployeeInfo)p1.info; // 형변환을 위해 (EmployeeInfo)를 붙여준다
        System.out.println(ei.rank);
    }
}
```



## 와일드카드 <?>

>  객체를 메소드의 매개변수로 받을 때, 객체의 타입 변수를 제한하는 것

```java
public void myMethod(ArrayList<? extends Number> list) { . . . }
```

* Number클래스와 Number클래스를 상속받은 클래스들의 객체만 받는다



### 제한 종류

* <? extends T> 와일드 카드의 상한 제한 - T와 그 자손들을 구현한 객체들만 매개변수로 가능
* <? super T> 와일드 카드의 하한 제한 - T와 그 조상들을 구현한 객체들만 매개변수로 가능
* <?> 제한 없음

