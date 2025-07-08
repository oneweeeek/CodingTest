import java.awt.*;
import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

public class Main {
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    static Queue<Point> q = new ArrayDeque<>();

    static boolean isInRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    static int max;

    static void bfs(){

        while(!q.isEmpty()){
            Point cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            max = Math.max(map[x][y], max);
            for (int d = 0; d < 8; d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(!isInRange(nx,ny) || map[nx][ny] == 1) continue;

                if(map[nx][ny] == 0){
                    q.add(new Point(nx,ny));
                    map[nx][ny] = map[x][y] + 1;
                }else if(map[nx][ny] != 0){
                    if(map[x][y] + 1 < map[nx][ny]){
                        map[nx][ny] = map[x][y] + 1;
                        q.add(new Point(nx,ny));
                    }

                }
            }
        }
        System.out.println(max-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = parseInt(st.nextToken());
                if(map[i][j] == 1){
                    q.add(new Point(i, j));
                }
            }
        }
        bfs();
    }
}
