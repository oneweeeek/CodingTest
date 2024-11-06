import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
 
public class Solution {
    static int n, k, maxLen, map[][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
 
    static boolean inRange(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
 
    static boolean visited[][];
 
    static void dfs(int x, int y, int depth, int curHeight, boolean chance) {
 
        maxLen = Math.max(depth, maxLen);
 
        for (int dir = 0; dir < 4; dir++) {
 
            int nx = x + dx[dir];
            int ny = y + dy[dir];
 
            if (!inRange(nx, ny) || visited[nx][ny]) continue;
                
            // 사방탐색 결과 높이
            int nextHeight = map[nx][ny];
 
            // 그냥 갈 수 있는 경우
            if (nextHeight < curHeight) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, nextHeight, chance);
                visited[nx][ny] = false;
            }
            // 기회 쓰고 가는 경우
            for (int i = 1; i <= k; i++) {
                if (chance && nextHeight - i < curHeight) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, depth + 1, nextHeight - i, false);
                    visited[nx][ny] = false;
                }
            }
 
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            k = parseInt(st.nextToken());
 
            map = new int[n][n];
            visited = new boolean[n][n];
            int startPoint = 0;
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = parseInt(st.nextToken());
                    startPoint = Math.max(startPoint, map[i][j]);
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == startPoint) {
                        visited[i][j] = true;
                        dfs(i, j, 1, map[i][j], true);
                        visited[i][j] = false;
                    }
                }
            }
            System.out.println("#" + tc + " " + maxLen);
            maxLen = 0;
        }
    }
 
}