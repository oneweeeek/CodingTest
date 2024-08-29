import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

import java.awt.Point;

public class Main {
	static int n, m, sum, result;
	static int[][] map;
	static boolean[] visited;

	static ArrayList<Point> house, chickenHouse;

	static void calDistance() {
		sum = 0;
		for (int i = 0; i < house.size(); i++) {
			Point curHouse = house.get(i);
			int min = MAX_VALUE;
			for (int j = 0; j < chickenHouse.size(); j++) {
				if (visited[j] == true) {
					Point chicken = chickenHouse.get(j);
					int distance = Math.abs(curHouse.x - chicken.x) + Math.abs(curHouse.y - chicken.y);
					if (min > distance) {
						min = distance;
					}
				}
			}
			sum += min;
		}
		result = Math.min(sum, result);
	}

	static void combinationChicken(int start, int depth) {

		if (depth == m) { // 최대 M개까지 뽑을 수 있슴, 주의! 깊이 1부터 시작해 m이 1부터 시작하니까
			calDistance();
			return;
		}

		for (int i = start; i < chickenHouse.size(); i++) {
			visited[i] = true; // 치킨집 뽑기 -> 뽑은 치킨집 visited true체크
			combinationChicken(i + 1, depth + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		house = new ArrayList<Point>();
		chickenHouse = new ArrayList<Point>();
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = parseInt(st.nextToken());
				if (map[i][j] == 1) {
					house.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					chickenHouse.add(new Point(i, j));
				}
			}
		}
		visited = new boolean[chickenHouse.size()];
		result = MAX_VALUE;
		combinationChicken(0, 0);
		System.out.println(result);
	}

}
