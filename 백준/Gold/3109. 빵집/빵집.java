import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

import java.awt.Point;

public class Main {
	static int r, c, count;
	static char[][] map;
	static int dx[] = { -1, 0, 1 };
	static int dy[] = { 1, 1, 1 };
	static boolean[][] visited;
	static boolean isInRange(int x, int y) {
		if (x < 0 || r <= x || y < 0 || c <= y) {
			return false;
		}
		return true;
	}

	static boolean dfs(int x, int y) {
		
		if(y == c-1) {
			return true; 
		}
		
		for(int d=0;d<3;d++) {
			
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			if(!isInRange(nx, ny)||visited[nx][ny]||map[nx][ny]=='x') {
				continue;
			}
//			System.out.println(nx+" "+ny);
			visited[nx][ny] = true;
			if(dfs(nx,ny)) {
				return true;
			}
		}
		return false;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = parseInt(st.nextToken());
		c = parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for(int i=0;i<r;i++) {
			if(dfs(i, 0)) {
				count++;
			}
		}
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(visited[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(count);
	}
}
