# orphanRemoval vs CascadeType.REMOVE

> JPA 연관 관계에서 삭제에 관련된 옵션이다.



### orphanRemoval

<hr/>

```Java
@Entity
public class School {
    
    @Column(name = "school_id")
    private Long id;

    @OneToMany(mappedBy = "school", orphanRemoval = true)
    private List<Teacher> teachers = new ArrayList<>();
}

@Entity
public class Teacher {

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
```

* 부모 엔티티와 관계가 끊어진 자식 엔티티를 자동으로 삭제해주는 옵션
* `@OneToMany`와 `@OneToOne` 에서 지원
* 연관관계의 주인인 엔티티에서 사용하는 옵션이므로 `@ManyToOne`에는 존재하지 않음
* 부모 엔티티의 컬렉션에서 자신 엔티티를 삭제할 때 참조가 끊어지면서 DB 에서도 삭제



### CascadeType.REMOVE

<hr/>

```java
@Entity
public class School {
    
    @Column(name = "school_id")
    private Long id;

    @OneToMany(mappedBy = "school", cascade = CascadeType.REMOVE)
    private List<Teacher> teachers = new ArrayList<>();
}

@Entity
public class Teacher {

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
```

* 총 3번의 SQL문을 거쳐서 삭제 (참조 삭제 -> 자식 객체 삭제 -> 부모 객체 삭제)
* 연관관계 주인이 아니더라도 해당 엔티티를 지우면 관련된 모든 엔티티들이 삭제



### 둘다 사용하면 어떻게 될까

<hr/>

```java
@OneToMany(mappedBy = "school", cascade = CascadeType.REMOVE, orphanRemoval = true)
private List<Teacher> teachers = new ArrayList<>();
```

* 부모 엔티티를 통해 자식의 생명주기까지 관리할 수 있음
* 자식을 저장하려면 부모에 등록하면 됨

