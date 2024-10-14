import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

import java.awt.Point;

public class Main {

	static int n, map[][], result;

	static boolean isInRange(int x, int y) {
		if (n <= x || n <= y) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		dfs(0, 1, 0);
		System.out.println(result);

	}

	// direction 0,가로 1,세로 2,대각선
	static void dfs(int x, int y, int direction) {

		if (x == n - 1 && y == n - 1) {
			result++;
			return;
		}

		if (direction == 0) {
			if (!isInRange(x, y + 1) || map[x][y + 1] == 1) {
				return;
			} else {
				dfs(x, y + 1, 0);
				if (!isInRange(x + 1, y + 1) || map[x + 1][y + 1] == 1 || map[x + 1][y] == 1) {
					return;
				} else {
					dfs(x + 1, y + 1, 2);
				}
			}
		} else if (direction == 1) {
			// 세로상태
			if (!isInRange(x + 1, y) || map[x + 1][y] == 1) {
				return;
			} else {
				dfs(x + 1, y, 1);
				if (!isInRange(x + 1, y + 1) || map[x + 1][y + 1] == 1 || map[x][y + 1] == 1) {
					return;
				} else {
					dfs(x + 1, y + 1, 2);
				}
			}
		} else if (direction == 2) {
			// 대각선상태
			if (!isInRange(x+1, y + 1) || map[x][y + 1] == 1 || map[x + 1][y + 1] == 1 || map[x+1][y] == 1) {
				
			} else {
				dfs(x+1, y + 1, 2);
			}
			if (!isInRange(x + 1, y) || map[x + 1][y] == 1) {
			} else {
				dfs(x + 1, y, 1);
			}
			if (!isInRange(x, y + 1) || map[x][y + 1] == 1) {
			} else {
				dfs(x, y + 1, 0);
			}
		}
	}

}
