# Arrays.sort, Collections.sort 비교



## Arrays.sort()

```java
public static void sort(int[] a) {
    DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
}
// DualPivotQuickSort를 사용한다.
```

### DualPivotQuicksort

* 삽입 정렬(Insertion Sort)와 퀵 정렬(Quick Sort)를 합친 것이다.

* 시간 복잡도가 n log (n) 이다.

  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqkyqI%2Fbtq0Ojy018S%2FaFT4WgPljjR4URPB52wej0%2Fimg.png">

  * 양 끝 숫자를 lp, rp로 지정한 후 lp > rp이면 두 숫자를 서로 바꾼다.
  * 이 두 피벗을 기준으로 3 분할을 한다.
  * 영역 별로 정렬을 한다.



## Collections.sort()

```java
    public static <T> void sort(T[] a, Comparator<? super T> c) {
        if (c == null) {
            sort(a);
        } else {
            if (LegacyMergeSort.userRequested)
                legacyMergeSort(a, c);
            else
                TimSort.sort(a, 0, a.length, c, null, 0, 0);
        }
    }
// TimSort를 사용한다.
```

## TimSort

> 시간복잡도가 n log (n)인 알고리즘의 실제 동작 시간은 C x n log n + a 라는 의미이다. C라는 상수가 곱해져 있어 이 값에 따라 실제 동작 시간에 큰 차이가 생긴다. 이 C 는 알고리즘이 ***참조 지역성***을 원리를 얼마나 만족하냐에 따라 달라진다.

#### **참조 지역성(Locality of reference) 이란?**

* cpu가 원하는 데이터를 예측하여 캐시 메모리에 담아 두는데 이때 예측률을 높이기 위하여 사용하는 원리이다.
* 최근에 참조한 메모리나 인접한 메모리를 다시 참조할 확률이 높다는 이론을 기반으로 캐시 메모리에 담아두는 것이다.

### 따라서

* 참조 지역성의 원리를 잘 만족하고 C 의 값이 작은 삽입정렬을 이용하여 전체를 작은 덩어리로 잘라 정렬하면 훨씬 효율적인 병합 정렬이 될 수 있다.

* List기준으로 봤을 때 메모리 주소가 인접한 ArrayList도 있지만 메모리 주소가 붙어있지 않는 LinkedList도 있기 때문에 참조 인접성이 좋지 않아 C의 값이 높습니다.
* 이럴 경우 Tim 정렬을 사용하는게 훨씬 좋은 성능을 기대할 수 있다.



## Arrays.sort() vs Collections.sort()

* Key가 여러 개의 값을 가질 때 ( ex) 구조체 함수 데이터 )는 stable한 정렬이 필요하므로 Collections.sort()를 많이 쓰고 그 외에는 Arrays.sort()를 사용하는게 좋다.

