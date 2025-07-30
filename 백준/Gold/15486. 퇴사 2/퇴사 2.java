import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = parseInt(br.readLine());

        int[] dp = new int[n + 2];
        int[][] counsel = new int[n + 2][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = parseInt(st.nextToken());
            int p = parseInt(st.nextToken());
            counsel[i][0] = t;
            counsel[i][1] = p;
        }
        int max = MIN_VALUE;
        for (int i = 1; i <= n + 1; i++) {

            // 현재까지 최대이익 갱신
            if(max < dp[i]) max = dp[i];

            // 상담진행 끝나는 날
            int nextTime = i + counsel[i][0];
            if(nextTime <= n + 1){
                dp[nextTime] = Math.max(dp[nextTime], max + counsel[i][1]);
            }
        }
        System.out.println(dp[n+1]);
    }
}
