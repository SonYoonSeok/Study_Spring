# 백준 1701번 - Cubeditor

```java
package sonny.study;

import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String T = sc.nextLine();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < T.length(); i++) {
            String s = T.substring(i);

            int[] table = makeTable(s);

            for (int j : table) {
                if (max < j) {
                    max = j;
                }
            }
        }

        System.out.println(max);
    }

    static int[] makeTable(String string) {
        int n = string.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while (idx > 0 && string.charAt(i) != string.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (string.charAt(i) == string.charAt(idx)) {
                idx += 1;
                table[i] = idx;
            }
        }
        return table;
    }
}



```

## 🚨 어려웠던 점

- pi배열을 사용하여 배열에서 가장 큰 값을 출력하면 되는줄 알았지만 틀렸다고 했다.

## 🔍 해결책

- 질문에서 반례를 찾았는데, `qbqtzqqt`은 `qt`가 중간에 하나 끝에 하나 2번 반복된다. makeTable 메서드에서는 시작 인덱스가 0인 것만 배열로 저장하기 때문에 중간과 끝에 오는건 세지 않는다.
- 때문에 문자열 길이만큼의 반복하는 for문을 추가했다.
  - 이러면 가능한 모든 문자열을 모두 탐색하므로 가장 긴 문자열을 찾을 수 있다.


## 🌈 느낀점

- 중간과 끝에 두 번 오는 것을 생각하는거 자체가 어렵다고 생각한다.