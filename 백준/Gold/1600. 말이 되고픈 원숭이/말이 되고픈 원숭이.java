import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
public class Main {
	static int k,w,h,map[][],min;
	static Queue<Coordinate> q = new ArrayDeque<Coordinate>();
	static boolean visited[][][];
	
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int horseX[] = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int horseY[] = {-1, 1, -2, 2, -2, 2,-1, 1};
	static boolean inRange(int x, int y) {
		if (x < 0 || w <= x || y < 0 || h <= y)
			return false;
		return true;
	}
	static class Coordinate{
		int x,y,level,chance;

		public Coordinate(int x, int y, int level, int chance) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
			this.chance = chance;
		}
		
	}
	static void bfs() {
		while(!q.isEmpty()) {
			Coordinate cur = q.poll();
			
			if(cur.x == w-1 && cur.y == h-1) {
				min = Math.min(min, cur.level);
				return;
			}
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(!inRange(nx, ny) || visited[nx][ny][cur.chance] || map[nx][ny] == 1) {
					continue;
				}
				visited[nx][ny][cur.chance] = true;
				q.add(new Coordinate(nx,ny,cur.level+1, cur.chance));
			}
			if(cur.chance > 0) {
				for(int d=0;d<8;d++) {
					int nx = cur.x+horseX[d];
					int ny = cur.y+horseY[d];
					if(!inRange(nx, ny) || visited[nx][ny][cur.chance-1] || map[nx][ny] == 1) {
						continue;
					}
					visited[nx][ny][cur.chance-1] = true;
					q.add(new Coordinate(nx,ny,cur.level+1, cur.chance-1));
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		h = parseInt(st.nextToken());
		w = parseInt(st.nextToken());
		map = new int[w][h];
		for(int i=0;i<w;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<h;j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		min = MAX_VALUE;
		visited = new boolean[w][h][k+1];
		q.add(new Coordinate(0,0,0,k));
		visited[0][0][k] = true;
		bfs();
		if(min == MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
}
