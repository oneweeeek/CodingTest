import java.io.*;
import java.util.*;
import java.awt.Point;
import static java.lang.Integer.*;

public class Main {

	/*
	 * bfs로 육지 파악하기 육지 하나하나가 그래프에 정점이라고 생각했을때 정점간에 최단거리?
	 */
	static Queue<Coordinate> q;
	static boolean visited[][];
	static int n, value, result, map[][];

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static boolean inRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || n <= y)
			return false;
		return true;
	}

	static class Coordinate {
		int x, y, level;

		public Coordinate(int x, int y, int level) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = parseInt(br.readLine());
		map = new int[n][n];
		q = new ArrayDeque<Coordinate>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}

		visited = new boolean[n][n];
		int numbering = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					q.add(new Coordinate(i, j, 0));
					continent(numbering++);
				}

			}
		}
		result = MAX_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 현좌표는 0이아닌데 사방탐색에서 그 다음좌표 0 -> 바다를 큐에 넣고 육지가 나올떄까지 bfs
				// 0이나 그 해당수가 아닌 다른수가 나올때까지 탐색한 count?
				if (map[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (inRange(nx, ny) && map[nx][ny] == 0) {
							visited = new boolean[n][n];
							visited[nx][ny] = true;
							q.add(new Coordinate(nx, ny, 0));
							bridge(map[i][j]);
						}
					}
				}

			}
		}

		System.out.println(result);

	}

	static void continent(int numbering) {

		while (!q.isEmpty()) {

			Coordinate cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			map[x][y] = numbering;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.add(new Coordinate(nx, ny, 0));
				}
			}

		}

	}

	// 탐색결과가 0이나 자기수는 괜찮지만 다른 수나오면 종료
	static void bridge(int preContinent) {

		int count = 0;

		while (!q.isEmpty()) {

			Coordinate cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int level = cur.level;
			if (map[x][y] != 0) {
				count = level;
				result = Math.min(count, result);
				q.clear();
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nl = level + 1;
				// 바다기준 저번대륙을 제외하고 다 사방탐색 해서 0이아닌게 걸리면!?
				if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != preContinent) {
					visited[nx][ny] = true;
					q.add(new Coordinate(nx, ny, nl));
				}
			}

		}
		
	}
}