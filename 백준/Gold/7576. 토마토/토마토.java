import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int n, m, map[][], result;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean visited[][];

	static boolean inRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || m <= y) {
			return false;
		}
		return true;
	}

	static Deque<Point> q;

	static class Point {
		int x, y, day;

		public Point(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}

	}

	static void bfs() {
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				// 범위벗어나거나 방문했거나 익지않은 토마토가 아니면
				if (!inRange(nx, ny) || visited[nx][ny] || map[nx][ny] != 0) {
					continue;
				}
				// 방문체크하고 날짜 채워주고 result에 할당해주고 익은토마토 큐에 넣어주기
				visited[nx][ny] = true;
				map[nx][ny] = cur.day;
				result = cur.day;
				q.add(new Point(nx, ny, cur.day + 1));
			}
		}
		// 익지못할 경우가 있으면 result에 -1할당
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					result = -1;
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		// 1 익은토마토, 0은 익지 않은 토마토 , -1토마토없는칸
		/*
		 * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야
		 * 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
		 */
		q = new ArrayDeque<Point>();
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.add(new Point(i, j, 1));
					visited[i][j] = true;
				}
			}
		}
		bfs();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(result);
	}
}
