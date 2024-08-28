import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

import java.awt.Point;

public class Main {

	static int n, k, min,count;
	static Queue<Point> q;
	static int move[] = { -1, 1, 2 };
	static boolean visited[];

	static void bfs() {

		if(n==k) {
			System.out.println(0);
			return;
		}
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curPoint = cur.x;
			int count = cur.y;
			for (int d = 0; d < 3; d++) {
				int nPoint;
				if (d == 2) {
					nPoint = curPoint * move[d];
				} else {
					nPoint = curPoint + move[d];
				}
				if(nPoint == k) {
					System.out.println(count+1);
					return;
				}
				if (nPoint >= 0 && nPoint <= 100000 && !visited[nPoint]) {
					q.add(new Point(nPoint,count+1));
					visited[nPoint] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parseInt(st.nextToken());
		k = parseInt(st.nextToken());
		min = MAX_VALUE;
		count = 0;
		visited = new boolean[100001];
		q = new LinkedList<Point>();
		q.add(new Point(n,0));
		visited[n] = true;
		bfs();
	}
}
