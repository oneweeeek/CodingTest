import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

public class Main {
    static int n, m;
    static int[][] dp;
    static int combination(int n, int r) {

        if(dp[n][r] > 0) {
            return dp[n][r];
        }

        if(n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] =
                combination(n - 1, r - 1) + combination(n - 1, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = parseInt(br.readLine());
        dp = new int[30][30];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            m = parseInt(st.nextToken());
            sb.append(combination(m,n)).append('\n');
        }
        System.out.println(sb.toString());
    }
}
