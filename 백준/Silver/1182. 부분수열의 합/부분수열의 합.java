import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    static int n, s, result;
    static int[] arr;
    static void dfs(int sum, int depth){

        if(depth == n){
            if(sum == s) result ++;
            return;
        }
        dfs(sum + arr[depth], depth + 1);
        dfs(sum, depth + 1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        s = parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        dfs(0, 0);
        if(s == 0) result--;
        System.out.println(result);
    }
}
