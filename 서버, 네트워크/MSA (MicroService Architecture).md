# MSA (MicroService Architecture)

> ***하나의 큰 어플리케이션을 여러개 작은 어플리케이션으로 나누어 만든 아케텍쳐***



## MSA의 등장 배경

MicroService 이전, 많은 소프트웨어들은 Monolithic Architecture로구현되어 있다는 것을 알아야 합니다. Monolithic Architecture는 소프트웨어의 모든 구성요소가 한 프로젝트에 통합되어 있는 Architecture입니다.

### Monolithic Architecture

Monolithic Architecture는 소규모 프로젝트에 적합하고, 유지보수에 용이합니다. 그러나, 일정 규모 이상 커진 프로젝트, 수백명 이상의 개발자가 투입되는 프로젝트에는 Monolithic Architecture는 한계를 보입니다.

* 빌드 시간 및 테스트 시간, 배포시간이 크게 늘어납니다.
* 작은 수정도 전체를 다시 빌드하고 배포해야 합니다.
* 일부분의 오류가 전체에 영향을 미칩니다.



### MicroService Architecture

MicroService Architecture는 서비스 단위로 독립적 배포를 할 수 있다는 강점이 있는 Architecture입니다.

**장점**

* 서비스 단위로 작업할당을 하면 개발자가 그 부분에 대해 온전히 이해할 수 있습니다.
* 수정사항이 있는 서비스만 따로 빠르게 빌드, 배포가 가능합니다.
* 오류가 발생하면 해당 서비스에 오류만 수정하면 됩니다.



**단점**

* 서로 서비스를 호출해야하기 때문에 무조건 다른 서비스를 호출하는 코드가 추가되야합니다.
* 서비스끼리 통신 오류가 잦습니다.
* 작은 여러 서비스가 분산되어 있기 때문에 모니터링이 힘듭니다.



