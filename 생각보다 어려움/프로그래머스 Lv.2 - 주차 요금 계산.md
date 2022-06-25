# 프로그래머스 Lv.2 - 주차 요금 계산

```java
class Solution {
    static Map<Integer, Integer> cars = new TreeMap<>();
    static Map<Integer, Integer> inTime = new TreeMap<>();
    
    static int toTime(String time) {
        String[] clockTime = time.split(":");
        return Integer.parseInt(clockTime[0]) * 60 + Integer.parseInt(clockTime[1]);
    }
    
    public static int[] solution(int[] fees, String[] records) {

        for (int i = 0; i < records.length; i++) {
            String[] car = records[i].split(" ");

            int time = toTime(car[0]);
            if (car[2].equals("IN")) {
                inTime.put(Integer.parseInt(car[1]), time);
                if (!cars.containsKey(Integer.parseInt(car[1]))) {
                    cars.put(Integer.parseInt(car[1]), 0);
                }
            } else {
                int chTime = time - inTime.get(Integer.parseInt(car[1]));
                cars.put(Integer.parseInt(car[1]), cars.get(Integer.parseInt(car[1])) + chTime);
                inTime.put(Integer.parseInt(car[1]), -1);
            }
        }
        
        int[] result = new int[cars.size()];

        int idx = 0;
        for (Integer car : cars.keySet()) {
            if (inTime.get(car) != -1) {
                cars.put(car, cars.get(car) + (1439 - inTime.get(car)));
                inTime.put(car, -1);
            }

            if (cars.get(car) <= fees[0]) {
                result[idx] = fees[1];
            } else {
                result[idx] = fees[1] + (int) Math.ceil((double) (cars.get(car) - fees[0]) / fees[2]) * fees[3];
            }
            idx++;
        }

        return result;
    }
}
```

## 🚨 어려웠던 점

- 입차 출차를 어떻게 구분해야 할지 정답을 도출해내는 것이 어려웠다.
- Map을 차량 번호로 정렬해야 한다는 것.

## 🔍 해결책

- 입차가 등록되는 Map, 총 주차된 시간을 등록하는 Map을 따로 만들어서 계산하기 편하게 구분하였다.
- TreeMap을 사용하여 자동으로 정렬되게 하였다.

## 🌈 느낀점

- 간단해 보이지만 여러 조건이 붙어서 어려울 것 같았지만 생각보다 어렵지는 않았다.