import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
	
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int[][] map;
	static Queue<Coordinate> queue;
	static int n;
	static boolean[][] visited;
	static int dangeNum;
	
    static boolean isRange(int x,int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }
    static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		dangeNum =0;
		queue = new LinkedList<>();
		list = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<n;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					queue.add(new Coordinate(i,j)); // 현좌표를 방문처리하고 큐에 추가 후 너비탐색 
					visited[i][j] = true; // 맵을 전체 탐색하다가 방문되지 않은곳에 1이면 아파트 단지 개수 검색
					bfs();
					dangeNum++;
				}
			}
		}
		System.out.println(dangeNum);
		Collections.sort(list); // 오름차순 정렬 추가
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}

	private static void bfs() {
		int cnt =0;
		while(!queue.isEmpty()) {
			
			Coordinate coordinate = queue.poll();
			cnt+=1;
			int curX = coordinate.x;
			int curY = coordinate.y;
			
			for(int i=0;i<4;i++) {
				int nx = curX+dx[i];
				int ny = curY+dy[i];
				// 다음좌표가 맵 범위안에 있고 방문되지 않았으며 값이 1이면 큐에 추가
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny] ==1) {
					visited[nx][ny] = true;
					queue.add(new Coordinate(nx, ny));
				}
			}
		}
		list.add(cnt);
	}

	public static class Coordinate {
		
		int x;
		int y;
		
		public Coordinate(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}
