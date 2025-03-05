import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;

import static java.lang.Integer.*;

public class Main {

    static int n,m;
    static char[][] map;
    static Queue<Point> q;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isEdge(int x, int y){
        return x ==0 || x == n-1 || y == 0 || y == m -1;
    }
    static boolean isInRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    static boolean[][] visited;
    static List<int[]> exits = new ArrayList<>();
    static int startY, startX, endY, endX;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int x, int y){

            if(x == endX && y == endY){
                map[x][y] = '.';
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        sb.append(map[i][j]);
                    }
                    sb.append("\n");
                }
                System.out.println(sb.toString());
                System.exit(0);
            }

            for (int d = 0; d < 4; d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(!isInRange(nx,ny) || map[nx][ny] == '+') continue;

                if(map[nx][ny] == '@'){
                    map[nx][ny] = '.';
                    dfs(nx, ny);
                    map[nx][ny] = '@';
                }
            }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == '.'){
                    map[i][j] = '@';
                    if(isEdge(i,j)){
                        exits.add(new int[]{i, j});
                    }
                }

            }
        }

        startX= exits.get(0)[0];
        startY = exits.get(0)[1];
        endX = exits.get(1)[0];
        endY = exits.get(1)[1];
        map[startX][startY] = '.';
        dfs(startX, startY);
    }
}
