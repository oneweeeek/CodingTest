import static java.lang.Integer.*;

import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
	static int n, m, map[][],count;
	static boolean visited[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean isInRange(int x, int y) {
		if(x<0||n<=x||y<0||m<=y) {
			return false;
		}
		return true;
	}
	static Queue<Point> q = new ArrayDeque<Point>();
	static void melt() {
		while(true) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==0) {
						q.add(new Point(i,j));
					}
				}
			}
			while(!q.isEmpty()) {
				Point cur = q.poll();
				
				for(int d=0;d<4;d++) {
					int nx = cur.x+dx[d];
					int ny = cur.y+dy[d];
					
					if(!isInRange(nx, ny)) {
						continue;
					}
					if(map[nx][ny] > 0) {
						map[nx][ny] -= 1;
					}
				}
			}
			
			count++;
			visited = new boolean[n][m];
			int part = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j] !=0 && !visited[i][j]) {
						q.add(new Point(i,j));
						visited[i][j] = true;
						bingsan();
						part++;
					}
				}
			}
			if(part >= 2) {
				return;
			}
			if(part == 0) {
				count = 0;
				return;
			}
		}
	}
	static void bingsan() {
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(!isInRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				if(map[nx][ny] != 0) {
					q.add(new Point(nx,ny));
					visited[nx][ny] = true;
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
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		melt();
		System.out.println(count);
	}
}
