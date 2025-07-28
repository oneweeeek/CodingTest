import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

public class Main {

    static void sieveOfEratosthenes(int m, int n) {

        boolean[] prime = new boolean[n + 1];

        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            // prime[i]가 소수라면
            if (!prime[i]) {
                // prime[j] 소수가 아닌 표시
                for (int j = i * i; j <= n; j += i) prime[j] = true;
            }
        }

        for (int i = m; i <= n; i++) {
            if (!prime[i]) System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = parseInt(st.nextToken());
        int n = parseInt(st.nextToken());
        sieveOfEratosthenes(m, n);
    }
}
