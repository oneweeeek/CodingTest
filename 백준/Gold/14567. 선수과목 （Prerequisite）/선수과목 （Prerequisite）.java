import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
import java.awt.Point;
public class Main {
	static int n,m;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	static Queue<Point> q = new ArrayDeque<Point>();
	static int result[];
	static void topologicalSort() {
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) {
				q.add(new Point(i,1));
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int v = cur.x;
			int level = cur.y;
			result[v] = level;
			ArrayList<Integer> list = adjList.get(v);
			for(int i=0;i<list.size();i++) {
				int nextV = list.get(i);
				
				indegree[nextV]--;
				
				if(indegree[nextV] == 0) {
					q.add(new Point(nextV,level+1));
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		result = new int[n+1];
		for(int i=0;i<=n;i++) {
			adjList.add(new ArrayList<Integer>());
		}
		indegree = new int[n+1];
		for(int i =0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken());
			int to = parseInt(st.nextToken());
			adjList.get(from).add(to);
			indegree[to]++;
		}
		topologicalSort();
		for(int i=1;i<=n;i++) {
			System.out.print(result[i]+" ");
		}
	}
}
