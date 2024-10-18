import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class Main {
	static int n, map[][], result;
	static boolean[][] visited1, visited2;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static Queue<Point> q;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static boolean isInRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || n <= y) {
			return false;
		}
		return true;
	}

	static void bfs1(int preColor) {
		while (!q.isEmpty()) {

			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (!isInRange(nx, ny) || visited1[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == preColor) {
					q.add(new Point(nx, ny));
					visited1[nx][ny] = true;
				}
			}
		}
	}

	static void bfs2(int preColor) {
		while (!q.isEmpty()) {

			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (!isInRange(nx, ny) || visited2[nx][ny]) {
					continue;
				}
				// 빨강 초록 같은 취급
				if (preColor == 0) {
					if (map[nx][ny] == preColor || map[nx][ny] == 1) {
						q.add(new Point(nx, ny));
						visited2[nx][ny] = true;
					}
				} else if (preColor == 1) {
					if (map[nx][ny] == preColor || map[nx][ny] == 0) {
						q.add(new Point(nx, ny));
						visited2[nx][ny] = true;
					}
				} else {
					if (map[nx][ny] == preColor) {
						q.add(new Point(nx, ny));
						visited2[nx][ny] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = parseInt(br.readLine());
		map = new int[n][n];
		q = new ArrayDeque<Point>();
		// R - 0 , G -1 , B-2
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				if (str.charAt(j) == 'G') {
					map[i][j] = 1;
				} else if (str.charAt(j) == 'B') {
					map[i][j] = 2;
				}
			}
		}
		visited1 = new boolean[n][n];
		visited2 = new boolean[n][n];
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited1[i][j]) {
					q.add(new Point(i, j));
					visited1[i][j] = true;
					bfs1(map[i][j]);
					count1++;
				} 
				if (!visited2[i][j]) {
					q.add(new Point(i, j));
					visited2[i][j] = true;
					bfs2(map[i][j]);
					count2++;

				}

			}
		}
		System.out.println(count1 + " " + count2);
	}
}
