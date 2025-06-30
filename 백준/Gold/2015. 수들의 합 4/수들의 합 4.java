import java.io.*;
import java.util.*;

import static java.lang.Integer.*;
/*
투 포인터 알고리즘은 모든 수가 양수일때만 가능 why?
sum < S일 때 end++ 하면 sum이 증가
sum > S일 때 start++ 하면 sum이 감소
음수가 껴있으면 이 논리가 보장되지 않음

i부터 j까지의 합은 sum[j]-sum[i-1]이다. 이 값이 K
sum[j]-sum[i-1] = K
sum[j] - K = sum[i-1]
* */
public class Main {

    static long[] arr;
    static Map<Long, Long> map;
    static int n,k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        k = parseInt(st.nextToken());
        arr = new long[n+1];
        map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        long count = 0;
        // 아무것도 선택하지 않은 한가지 경우의 수
        map.put(0L, 1L);

        for (int i = 1; i <= n; i++) {
            int num = parseInt(st.nextToken());
            arr[i] = arr[i-1] + num;
            // arr[i]-k = B로, B가 되는 경우의 수로 K 가 되는 경우의 수를 찾는다.
            count += map.getOrDefault(arr[i] - k, 0L);
            // map에 (Key: 0부터 i까지의 누적합, Value: 누적합이 되는 경우의 수)를 업데이트 한다.
            map.put(arr[i], map.getOrDefault(arr[i], 0L) + 1);
        }
        System.out.println(count);
    }
}
