# 백준 15829번 - Hashing

```java
public class Main {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int N = sc.nextInt();
        BigInteger bigInteger = new BigInteger("0");
        BigInteger mul = new BigInteger("31");
        BigInteger rem = new BigInteger("1234567891");
        String input = sc.next();

        for (int i = 0; i < N; i++) {
            BigInteger val = new BigInteger(String.valueOf((int) input.charAt(i) - 96));
            BigInteger add_val = new BigInteger("1");
            for (int j = 0; j < i; j++) {
                add_val = add_val.multiply(mul);
            }
            add_val = add_val.multiply(val);
            bigInteger = bigInteger.add(add_val);
            bigInteger = bigInteger.remainder(rem);
        }

        System.out.println(bigInteger);
    }
}

```

## 🚨 어려웠던 점

- 자바의 Math.pow를 사용하여 풀면 long 범위까지밖에 표현이 안되서 BigInteger 형태로 제곱을 해야했다.	

## 🔍 해결책

* 반복문을 돌려서 BigInterger에 계속 곱해주었다.

## 🌈 느낀점

- 이게 왜 브론즈 문제인지 모르겠다.

