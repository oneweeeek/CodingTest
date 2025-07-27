import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    static int[] arr, cnt;
    static int n, k, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        k = parseInt(st.nextToken());

        arr = new int[n];
        cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;

        while (right < n) {
            if (cnt[arr[right]] < k) {
                cnt[arr[right]]++;
                right++;
                result = Math.max(right - left, result);
            } else {
                cnt[arr[left]]--;
                left++;
            }
        }
        System.out.println(result);
    }
}
