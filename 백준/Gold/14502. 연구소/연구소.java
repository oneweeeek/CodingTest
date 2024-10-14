import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
import java.awt.Point;
public class Main {
	static int n,m,map[][],copy[][],result;
	static boolean[][] visited;
	static boolean[] IsWall;
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	static ArrayList<Point> blankList = new ArrayList<Point>();
	static ArrayList<Point> virusList = new ArrayList<Point>();
	static Queue<Point> q;
	static boolean isInRange(int x, int y) {
		if(x<0 || n<=x||y<0||m<=y) {
			return false;
		}
		return true;
	}
	static void wall(int n, int depth) {
		
		if(depth == 3) {
			bfs();
			return;
		}
		
		for(int i = n; i<blankList.size();i++) {
			IsWall[i] = true;
			wall(i+1, depth+1);
			IsWall[i] = false;
		}
	}
	static void bfs() {
		copy = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                copy[i][j] = map[i][j];  
            }
        }
		// 벽세우기
		for(int i = 0;i<blankList.size();i++) {
			if(IsWall[i]) {
				Point wall = blankList.get(i);
				copy[wall.x][wall.y] = 1;
			}
		}
		q = new ArrayDeque<Point>();
		visited = new boolean[n][m];
		for(Point virus : virusList) {
			q.add(virus);
		}
		while(!q.isEmpty()) {
			Point virus = q.poll();
			copy[virus.x][virus.y] = 2;
			visited[virus.x][virus.y] = true;
			
			for(int d=0;d<4;d++) {
				int nx = virus.x+dx[d];
				int ny = virus.y+dy[d];
				
				if(!isInRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				if(copy[nx][ny] == 0) {
					q.add(new Point(nx,ny));
				}
			}
		}
		int count = 0;
		for(int i =0;i<n;i++) {
			for(int j = 0;j<m;j++) {
				if(copy[i][j] == 0) {
					count++;
				}
			}
		}
		if(count > result) result = count;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		map = new int[n][m];
		for(int i =0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<m;j++) {
				map[i][j] = parseInt(st.nextToken());
				if(map[i][j] == 0) {
					blankList.add(new Point(i,j));
				}else if(map[i][j] == 2) {
					virusList.add(new Point(i,j));
				}
			}
		}
		IsWall = new boolean[blankList.size()];
		wall(0,0);
		System.out.println(result);
	}

}
