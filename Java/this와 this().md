# this와 this()

### this 참조 변수

* 인스턴스가 자기 자신을 참조하는 데 사용하는 변수이다.
* this는 해당 인스턴스의 주소를 가리킨다.

```java
class Car {
    private String modelName;
    private int modelYear;
    private String color;
    private int maxSpeed;
    private int currentSpeed;

    Car(String modelName, int modelYear, String color, int maxSpeed) {
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0;
    }
}
```

* 생성자의 매개변수 이름과 인스턴스 변수의 이름이 같을 경우 인스턴스 변수 앞에 `this`키워드를 붙여 구분해야 한다.
* 모든 인스턴스 메소드에는 `this`참조 변수가 숨겨진 지역 변수로 존재하고 있다.
* `this` 참조 변수를 사용하여 인스턴스 변수에 접근할 수 있다.



### this() 메소드

```Java
class Car {
    private String modelName;
    private int modelYear;
    private String color;
    private int maxSpeed;
    private int currentSpeed;

    Car(String modelName, int modelYear, String color, int maxSpeed) {
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0;
    }
    
    Car() {
        this("소나타", 2012, "검정색", 160); // 다른 생성자를 호출함.
    }
    
    public String getModel() {
        return this.modelYear + "년식 " + this.modelName + " " + this.color;
    }
}

public class practice {
    public static void main(String[] args) {
        Car car = new Car(); System.out.println(car.getModel());
    }
}
```

* 매개변수를 가지지 않는 생성자에서 this() 메서드를 사용해 생성자를 호출하여 인스턴스 변수를 초기화 할 수 있습니다.