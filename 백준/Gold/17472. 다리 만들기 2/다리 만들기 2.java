import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

/*, 다리의 방향은 가로 또는 세로가 될 수 밖에 없다.
 *  방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 하고,
 *   방향이 세로인 다리는 다리의 양 끝이 세로 방향으로 섬과 인접해야 한다.
 *   리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 
 *   또, 다리의 길이는 2 이상이어야 한다.
7 8
0 0 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
1 1 0 0 0 1 1 0
0 0 0 0 0 1 1 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
 * */
public class Main {

	static int n, m, map[][], answer,numbering;
	static int parents[];
	static boolean visited[][];
	static ArrayList<Edge> edges;
	static Queue<Coordinate> q;

	static class Coordinate {
		int x, y, level;

		public Coordinate(int x, int y, int level) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int[] 가로x = { -1, 1 };
	static int[] 세로y = { -1, 1 };

	static boolean inRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || m <= y)
			return false;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return compare(this.weight, o.weight);
		}

	}

	// 대륙 넘버링 지어주기
	static void continent() {

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

	static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	static void bridge(int preContinent, boolean direction) {

		int weight = 0;
		while (!q.isEmpty()) {

			Coordinate cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int level = cur.level;

			if (map[x][y] != 0) {
				weight = level;
				if (level != 1) {
					edges.add(new Edge(preContinent, map[x][y], weight));
				}
				q.clear();
				return;
			}

			// 세로
			if (direction) {
				for (int d = 0; d < 2; d++) {

					int ny = y + 세로y[d];
					int nl = level + 1;
					if (inRange(x, ny) && !visited[x][ny] && map[x][ny] != preContinent) {
						visited[x][ny] = true;
						q.add(new Coordinate(x, ny, nl));
					}
				}
				// 가로
			} else {
				for (int d = 0; d < 2; d++) {
					int nx = x + 가로x[d];
					int nl = level + 1;
					if (inRange(nx, y) && !visited[nx][y] && map[nx][y] != preContinent) {
						visited[nx][y] = true;
						q.add(new Coordinate(nx, y, nl));
					}
				}
			}

		}
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
			}
		}
		q = new ArrayDeque<Coordinate>();
		visited = new boolean[n][m];
		// 대륙 넘버링
		numbering = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					q.add(new Coordinate(i, j, 0));
					continent();
					numbering++;
				}

			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		edges = new ArrayList<Edge>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {

					boolean direction;

					for (int d = 0; d < 2; d++) {
						int nx = i + 가로x[d];
						if (inRange(nx, j) && map[nx][j] == 0) {
							visited = new boolean[n][m];
							visited[nx][j] = true;
							q.add(new Coordinate(nx, j, 0));
							direction = false;
							bridge(map[i][j], direction);
						}
						int ny = j + 세로y[d];
						if (inRange(i, ny) && map[i][ny] == 0) {
							visited = new boolean[n][m];
							visited[i][ny] = true;
							q.add(new Coordinate(i, ny, 0));
							direction = true;
							bridge(map[i][j], direction);
						}
					}
				}
			}
		}

		Collections.sort(edges);
		if (edges.size() == 0) {
			System.out.println(-1);
			return;
		}
//		for (Edge edge : edges) {
//			System.out.print(edge.from + " ");
//			System.out.print(edge.to + " ");
//			System.out.print(edge.weight);
//			System.out.println();
//		}

		parents = new int[numbering+1];
		for (int i = 2; i <= numbering; i++) {
			parents[i] = -1;
		}

		int cnt = 0, cost = 0;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				cost += edge.weight;
				if(++cnt == numbering-3) break;
			}
		}
		if (cnt != numbering - 3) {  
		    System.out.println(-1);  // MST를 만들 수 없는 경우 -1 출력
		} else {
		    answer = cost;  // 총 비용 저장
		    System.out.println(answer);  // 결과 출력
		}
//		int a = findSet(2);
//		for (int i = 2; i < numbering; i++) {
//			if (a != findSet(i)) {
//				answer = -1;
//				break;
//			}
//		}
	}
}