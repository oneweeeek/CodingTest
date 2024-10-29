import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
    static int n, m, result;
    static char[][] map;
    static boolean alphabet[];
    static boolean visited[][];
    static boolean isInRange(int x, int y) {
        if (x < 0 || n <= x || y < 0 || m <= y) {
            return false;
        }
        return true;
    }
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new char[n][m];
        alphabet = new boolean[26];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        dfs(0, 0, 1);
        System.out.println(result);
    }

    static void dfs(int x, int y, int depth) {
//        System.out.println("x : "+x+" y : "+y+" depth : "+depth);
        visited[x][y] = true;
        alphabet[map[x][y]-'A'] = true;
        
        for(int d=0;d<4;d++) {
            int nx = x+dx[d];
            int ny = y+dy[d];
            
            if(!isInRange(nx, ny)||visited[nx][ny]) {
                continue;
            }
            
            if(!alphabet[map[nx][ny]-'A']) {
            	dfs(nx,ny,depth+1);
            }
        }
        alphabet[map[x][y]-'A'] = false;
        visited[x][y] = false;
        result = Math.max(result, depth);
    }
}
