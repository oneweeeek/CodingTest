import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Solution {
	static int n, m, k;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int map[][];
	static Deque<Cell> q;
	static ArrayList<Cell> list;
	static Map<String, Cell> positionMap; // 세포 위치 관리

	static class Cell {

		int x, y, status, life, activeTime;

		public Cell(int x, int y, int status, int life, int activeTime) {
			super();
			this.x = x;
			this.y = y;
			this.status = status; // -1 비활성화, 1 활성화
			this.life = life; // 생명력
			this.activeTime = activeTime; // 활성화되고 받은 생명력
		}

		@Override
		public String toString() {
			return "Cell [x=" + x + ", y=" + y + ", status=" + status + ", life=" + life + ", activeTime=" + activeTime
					+ "]";
		}
	}

	static void bfs() {
		for (int time = 1; time <= k; time++) {

			positionMap = new HashMap<>();
			for (int i = 0; i < list.size(); i++) {
				Cell cell = list.get(i);

				// 상태 업데이트
				// 비활성인데 활성화시간 == 시간
				if (cell.status == -1 && time == cell.activeTime) { // 비활성인데 시간과 생명력이 같으면 활성화
					cell.status = 1;
					cell.activeTime = time + cell.life;
					// 활성화 상태
				} else if (cell.status == 1) {
					if (time == cell.activeTime - cell.life + 1) {
						for (int d = 0; d < 4; d++) {
							int nx = cell.x + dx[d];
							int ny = cell.y + dy[d];

							if (map[nx][ny] != 0) {
								continue;
							}

							String key = nx + "," + ny;
							if (!positionMap.containsKey(key) || positionMap.get(key).life < cell.life) {
								positionMap.put(key, new Cell(nx, ny, -1, cell.life, time + cell.life)); // 새로운 Cell 생성
							}
						}
					} 
					if (time == cell.activeTime) {
						list.remove(i);
						i--;
						map[cell.x][cell.y] = -1;
					}
				}
			}
			for (Cell newCell : positionMap.values()) {
				map[newCell.x][newCell.y] = newCell.life;
				list.add(newCell);
			}
//			System.out.println("\n" + list.size());
//			System.out.println(time + "시간");
//			for (int i = 0; i < n + k * 2; i++) {
//				for (int j = 0; j < m + k * 2; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = parseInt(st.nextToken());
			m = parseInt(st.nextToken());
			k = parseInt(st.nextToken());

			map = new int[n + k * 2][m + k * 2];
			q = new ArrayDeque<Cell>();
			list = new ArrayList<Cell>();
			int life = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					life = parseInt(st.nextToken());
					if (life > 0) {
						map[i + k][j + k] = life;
						list.add(new Cell(i + k, j + k, -1, life, life));
					}
				}
			}
			bfs();
			System.out.println("#" + tc + " " + list.size());
		}
	}
}