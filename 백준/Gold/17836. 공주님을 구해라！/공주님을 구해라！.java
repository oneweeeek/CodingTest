import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class Main {

	static int n, m, t, map[][], result;
	static boolean[][] visited, visitedWithSword;

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static boolean isInRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || m <= y) {
			return false;
		}
		return true;
	}

	static Queue<Coordinate> q;

	static class Coordinate {
		int x, y, level;
		boolean sword;

		public Coordinate(int x, int y, int level, boolean sword) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
			this.sword = sword;
		}
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Coordinate cur = q.poll();
			if (map[cur.x][cur.y] == 2) {
				cur.sword = true;
				visitedWithSword[cur.x][cur.y]=true;
			}
			if(cur.level > t) {
				continue;
			}
			if (cur.x == n - 1 && cur.y == m - 1) {
				if (cur.level <= t) {
					result = Math.min(result, cur.level);
					return;
				}
			}
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (!isInRange(nx, ny)) {
					continue;
				}
				if (cur.sword) {
					if (!visitedWithSword[nx][ny]) {
						q.add(new Coordinate(nx, ny, cur.level + 1, true));
						visitedWithSword[nx][ny] = true;
					}
				} else {
					if (!visited[nx][ny]) {
						if (map[nx][ny] == 0 || map[nx][ny] == 2) {
							q.add(new Coordinate(nx, ny, cur.level + 1, false));
							visited[nx][ny] = true;
						}
					}
				}

			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		t = parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		visitedWithSword = new boolean[n][m];
		q = new ArrayDeque<Coordinate>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		q.add(new Coordinate(0, 0, 0, false));
		visited[0][0] = true;
		result = MAX_VALUE;
		bfs();
		if (result == MAX_VALUE) {
			System.out.println("Fail");
		} else {
			System.out.println(result);
		}
	}

}
