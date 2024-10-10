import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
	static int n, m;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	static Queue<Integer> q = new ArrayDeque<Integer>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		indegree = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken());
			int to = parseInt(st.nextToken());
			adjList.get(from).add(to);
			indegree[to]++;
		}
		topologicalSort();
		System.out.println(sb.toString());
	}

	static void topologicalSort() {
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int v = q.poll();
			sb.append(v + " ");
			ArrayList<Integer> list = adjList.get(v);
			for (int i = 0; i < list.size(); i++) {
				int nextV = list.get(i);
				
				indegree[nextV]--;

				if (indegree[nextV] == 0) {
					q.add(nextV);
				}
			}
		}
	}
}
