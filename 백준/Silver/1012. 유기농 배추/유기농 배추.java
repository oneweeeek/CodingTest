import java.awt.*;
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    static int n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Point> q = new ArrayDeque<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean InRange(int y, int x){
        return 0 <= x && x < m && 0 <= y && y < n;
    }
    static void solve(int count) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    q.add(new Point(i, j));
                    bfs();
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.x + dx[d];
                int nx = cur.y + dy[d];

                if(!InRange(ny,nx) || visited[ny][nx]) continue;

                if(map[ny][nx] == 1){
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            m = parseInt(st.nextToken());
            n = parseInt(st.nextToken());
            k = parseInt(st.nextToken());
            map = new int[n][m];
            visited = new boolean[n][m];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = parseInt(st.nextToken());
                int y = parseInt(st.nextToken());
                map[y][x] = 1;
            }
            solve(0);
        }
    }
}
