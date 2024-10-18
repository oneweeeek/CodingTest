import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

import java.awt.Point;

public class Main {
	static int n, m, map[][],result;
	static boolean visited[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static Queue<Coordinate> q;

	static boolean isInRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || m <= y) {
			return false;
		}
		return true;
	}
	static class Coordinate{
		int x,y,level;

		public Coordinate(int x, int y, int level) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}
	static void bfs() {
		int time = 0;
		while(!q.isEmpty()) {
			Coordinate cur = q.poll();
			time = cur.level;
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(!isInRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				if(map[nx][ny] == 1) {
					q.add(new Coordinate(nx, ny, cur.level+1));
					visited[nx][ny] = true;
				}
			}
		}
		result = Math.max(result, time);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		map = new int[n][m];
		q = new ArrayDeque<Coordinate>();
		// L 육지, W 바다
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == 'L') {
					map[i][j] = 1;
				}
			}
		}
		result = MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					visited = new boolean[n][m];
					q.add(new Coordinate(i, j, 0));
					visited[i][j] = true;
					bfs();
				}
			}
		}
		System.out.println(result);
	}
}
