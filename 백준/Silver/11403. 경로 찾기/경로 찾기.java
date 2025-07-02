import java.io.*;
import java.util.*;

import static java.lang.Integer.*;
public class Main {
    static int n;
    static int[][] map;

    static void solve(){
        for (int k = 0; k < n; k++) { // 경유노드
            for (int i = 0; i < n; i++) { // 출발노드
                for (int j = 0; j <n; j++) { // 도착노드
                    if(map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }
        solve();
    }
}
