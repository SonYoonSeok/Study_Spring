# 자바의 멀티 스레드

* 필요에 따라 작업 스레드를 만들어 병렬로 코드를 실행할 수 있습니다.
* 메인 스레드가 종료되더라도 실행 중인 스레드가 있으면 프로세스는 종료되지 않습니다.



## 자바의 Thread 생성

### Runnable로 구현

```java
class Task implements Runnable { // implements로 상속받아 구현

    @Override
    public void run() {

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
            System.out.println(sum);
        }
        System.out.println(Thread.currentThread() + "최종 합 : " + sum);
    }
}
```



### Thread로 구현

```java
class Task extends Thread { // extends로 상속받아 구현
    
    @Override
    public void run() {
        int sum = 0;
        for (int index = 0; index < 10; index++) {
            sum += index;
            System.out.println(sum);
        }
        System.out.println( Thread.currentThread() + "최종 합 : " + sum);

    }
}
```

### 🚨 다른 클래스를 상속 받을 수 없다 🚨



## 데몬 스레드

* 메인 스레드의 작업을 보조하는 역할을 한다.
* 메인 스레드가 종료되면 데몬 스레드는 강제 종료된다.
* setDaemon(true) 메소드를 호출해 데몬 스레드로 생성한다.

```java
public static void main(String[] args) throws IOException {

        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("데몬 스레드가 실행 중입니다.");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```



### **실행결과**

```java
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.
데몬 스레드가 실행 중입니다.

// 무한 반복문 이지만 10번 실행 후 메인 스레드가 종료되어 자동 종료됨
```



## 동기화 메소드, 동기화 블록

* 멀티스레드 환경에서는 스레드들이 객체를 공유하기 때문에  공유하는 객체가 서로의 작업에 영향을 미치면 안되기 때문에 동기화 메소드와 동기화 블록을 한다.

### 동기화 메소드

```java
public synchronized void setValue(int value) {
        this.value = value;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "의 value는 " + this.value + "입니다.");

    }

```



### 동기화 블록

```java
public void setValue(int value) {

        synchronized (this) {
            this.value = value;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "의 value는 " + this.value + "입니다.");
        }
    }
```

* 메소드 내의 일부만 동기화 시키고 싶으면 동기화 블록을 사용하는게 좋다.
* 동기화를 남발하면 성능저하가 일어나기 때문에 꼭 필요한 곳에 필요할 때만 써야한다.