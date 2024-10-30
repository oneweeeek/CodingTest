import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
	static int n, m;
	static int[] minEdge, predecessor;

	static class Node {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>();

	static void dijkstra(int start, int end) {
		minEdge[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.cost > minEdge[cur.v]) {
				continue;
			}
			for (Node next : adjList.get(cur.v)) {

				if (minEdge[next.v] > cur.cost + next.cost) {
					minEdge[next.v] = cur.cost + next.cost;
					predecessor[next.v] = cur.v;
					pq.add(new Node(next.v, minEdge[next.v]));
				}
			}
		}
		System.out.println(minEdge[end]);
		ArrayList<Integer> path = new ArrayList<>();
		int order = end;
		while (order != start) {
			path.add(order + 1);
			order = predecessor[order];
		}
		path.add(start + 1);
		Collections.reverse(path);

		System.out.println(path.size());

		for (int city : path) {
			System.out.print(city + " ");
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = parseInt(br.readLine());
		m = parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<Node>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken()) - 1;
			int to = parseInt(st.nextToken()) - 1;
			int cost = parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		int start = parseInt(st.nextToken()) - 1;
		int end = parseInt(st.nextToken()) - 1;

		minEdge = new int[n];
		predecessor = new int[n];
		Arrays.fill(minEdge, MAX_VALUE);
		dijkstra(start, end);
	}
}
