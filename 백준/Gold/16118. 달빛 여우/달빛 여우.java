import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {

	static class Node implements Comparable<Node> {
		int v, cost, hp;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		public Node(int v, int cost, int hp) {
			this.v = v;
			this.cost = cost;
			this.hp = hp;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}

	static int n, m, result, destination;
	static int[] foxDist;
	static int[][] wolfDist;
	static final int INF = MAX_VALUE;
	static ArrayList<ArrayList<Node>> aList;
	static PriorityQueue<Node> pq;

	static void init() {
		pq = new PriorityQueue<>();
		foxDist = new int[n];
		wolfDist = new int[2][n];
		Arrays.fill(foxDist, INF);
		for (int i = 0; i < 2; i++) {
			Arrays.fill(wolfDist[i], INF);
		}
	};

	static void foxDijkstra() {

		foxDist[0] = 0;
		pq.add(new Node(0, 0));

		while (!pq.isEmpty()) {

			Node cur = pq.poll();
			if (foxDist[cur.v] < cur.cost)
				continue;

			ArrayList<Node> list = aList.get(cur.v);

			for (int i = 0; i < list.size(); i++) {
				Node next = list.get(i);

				if (foxDist[next.v] > foxDist[cur.v] + next.cost) {
					foxDist[next.v] = foxDist[cur.v] + next.cost;
					pq.offer(new Node(next.v, foxDist[next.v]));
				}
			}
		}
	}

	static void wolfDijkstra() {

		wolfDist[0][0] = 0;
		pq.add(new Node(0, 0, 0));

		while (!pq.isEmpty()) {

			Node cur = pq.poll();
			if (wolfDist[cur.hp][cur.v] < cur.cost)
				continue;
			
			ArrayList<Node> list = aList.get(cur.v);

			for (int i = 0; i < list.size(); i++) {
				Node next = list.get(i);
				
				int nextHp = cur.hp  == 0 ? 1 : 0;
				int nextCost = wolfDist[cur.hp][cur.v] + (cur.hp == 0 ? (next.cost / 2) : (next.cost * 2));
				
				if(wolfDist[nextHp][next.v] > nextCost) {
					wolfDist[nextHp][next.v] = nextCost;
					pq.add(new Node(next.v, nextCost, nextHp));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		aList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			aList.add(new ArrayList<Node>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken()) - 1;
			int to = parseInt(st.nextToken()) - 1;
			int dist = parseInt(st.nextToken()) * 2;
			aList.get(from).add(new Node(to, dist));
			aList.get(to).add(new Node(from, dist));
		}
		init();
		foxDijkstra();
		wolfDijkstra();
		for (int i = 1; i < n; i++) {
//			System.out.println(foxDist[i] + " " + wolfDist[0][i] + " " + wolfDist[1][i]);
			if (foxDist[i] < Math.min(wolfDist[0][i], wolfDist[1][i])) {
				result++;
			}
		}
		System.out.println(result);
	}
}
