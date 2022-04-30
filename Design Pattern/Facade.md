# Facade

> 비즈니스 로직을 상위 레벨의 인터페이스로 캡슐화하여 하위 시스템에서 쉽게 접근할 수 있게 한다.



##  퍼사드 패턴이란?

* 인터페이스를 하나 정의해놓고 메서드를 만든 후 여러 다른 클래스에서 상속 받은 후 오버라이딩을 한다.
* 하나의 메서드로 여러 클래스에서 정의한 메서드를 실행한다.

<img src="https://thebook.io/img/006880/EE_054.jpg" alt="img" style="zoom:70%;" />



```java
public class WashingMachine {
 
    public void heavilySoiled() {
        setWaterTemperature(100);
        setWashCycleDuration(90);
        setSpinCycleDuration(10);
        addDetergent();
        addBleach();
        addFabricSoftener();
        heatWater();
        startWash();
    }

    public void lightlySoiled() {
        setWaterTemperature(40);
        setWashCycleDuration(20);
        setSpinCycleDuration(10);
        addDetergent();
        heatWater();
        startWash();
    }
}
 
// 퍼사드는 이렇게 사용합니다.
new WashingMachine().lightlySoiled();
```

* 퍼사드에 있는 메서드만 호출하고 복잡한 로직은 퍼사드 안에 감춘다.
* 구현체를 바꾸어도 서비스에 접근하는 클라이언트에는 아무 영향이 없다.



## 퍼사드 패턴의 장점

* 클라이언트가 하위시스템을 신경쓰지 않아도 돼서 결합도가 낮다.
* 유지보수성, 관리성이 좋아진다.
* 로직을 다시 재용할 수 있다.
* 연관된 메소드들을 하나의 메소드에 묶어서 호출해서 로직이 덜 복잡하다.

