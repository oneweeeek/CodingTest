import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
import java.awt.Point;

public class Main {
	static int n, m, map[][], copy[][], result;
	static boolean[] IsVirus;
	static boolean[][] visited;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static ArrayList<Point> virusList = new ArrayList<Point>();
	static Queue<Virus> q;

	static boolean isInRange(int x, int y) {
		if (x < 0 || n <= x || y < 0 || n <= y) {
			return false;
		}
		return true;
	}

	static class Virus {
		int x, y, level;

		public Virus(int x, int y, int level) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

	static void virus(int start, int depth) {

		if (depth == m) {
			bfs();
			return;
		}

		for (int i = start; i < virusList.size(); i++) {
			IsVirus[i] = true;
			virus(i + 1, depth + 1);
			IsVirus[i] = false;
		}
	}

	static void bfs() {
		
		copy = new int[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					visited[i][j] = true;
				}
			}
		}
		// 바이러스 놓기
		q = new ArrayDeque<Virus>();
		for (int i = 0; i < virusList.size(); i++) {
			if (IsVirus[i]) {
				Point cur = virusList.get(i);
				q.add(new Virus(cur.x, cur.y, 0));
				visited[cur.x][cur.y] = true;
			}
		}
		int lastLevel = 0;
		while (!q.isEmpty()) {
			Virus virus = q.poll();
			int curLevel = virus.level;
			lastLevel = Math.max(lastLevel, curLevel);

			for (int d = 0; d < 4; d++) {
				int nx = virus.x + dx[d];
				int ny = virus.y + dy[d];
				if (!isInRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				q.add(new Virus(nx, ny, curLevel+1));
				visited[nx][ny] = true;
			}
		}
		boolean all = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					all = false;
	                break;
				}
			}
		}
	    if (all) {
	        result = Math.min(result, lastLevel);
	    }
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusList.add(new Point(i, j));
				}
			}
		}
		IsVirus = new boolean[virusList.size()];
		result = MAX_VALUE;
		virus(0, 0);
		if(result == MAX_VALUE) result = -1;
		System.out.println(result);
	}
}
