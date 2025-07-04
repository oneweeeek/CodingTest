import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {

    static int t,n,m;
    static int[] A,B;
    static HashMap<Integer, Integer> subB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());}

        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // B의 모든 부배열 합을 Map에 저장: 합 -> 개수
        subB = new HashMap<>();
        for(int i = 0; i < m; i++) {
            int sum = 0;
            for(int j = i; j < m; j++) {
                sum += B[j];
                subB.put(sum, subB.getOrDefault(sum, 0) + 1);
            }
        }

        // A의 부배열을 돌면서 t - sumA가 B에 몇 개 있는지 카운트
        long answer = 0;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += A[j];
                answer += subB.getOrDefault(t - sum, 0);
            }
        }

        System.out.println(answer);
    }
}