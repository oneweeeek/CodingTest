import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import static java.lang.Integer.*;
public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int dp[][];
	static boolean inRange(int x, int y) {
		if(x<0|| m<=x || y<0|| n<=y) {
			return false;
		}
		return true;
	}
	static int n,m,map[][],count;
	static int dfs(int x, int y) {
		if(x == m-1 && y == n-1) {
			return 1;
		}
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		
		dp[x][y] = 0; // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화.
		
		for(int d=0;d<4;d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			if(!inRange(nx, ny)) {
				continue;
			}
			if(map[nx][ny] < map[x][y]) {
				dp[x][y] += dfs(nx,ny);
			}
		}
		return dp[x][y];
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = parseInt(st.nextToken());
		n = parseInt(st.nextToken());
		map = new int[m][n];
		dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		count = dfs(0,0);
		System.out.println(count);
	}

}
