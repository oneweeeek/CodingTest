import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
	static int v,e,parents[],sum;
	static void make() {
		parents = new int[v+1];
		Arrays.fill(parents, -1);
	}
	
	static int find(int a) {
		if(parents[a] < 0) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot]; // 집합의 크기 활용가능
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int start,end,weight;
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = parseInt(st.nextToken());
		e = parseInt(st.nextToken());
		Edge[] edges = new Edge[e];
		for(int i = 0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken());
			int to = parseInt(st.nextToken());
			int weight = parseInt(st.nextToken());
			edges[i] = new Edge(from,to,weight);
		}
		make();
		Arrays.sort(edges);
		int cnt = 0;
		for(Edge edge :edges) {
			if(union(edge.start, edge.end)) {
				sum += edge.weight;
				if(++cnt == v-1) break;
			}
		}
		System.out.println(sum);
	}
}
