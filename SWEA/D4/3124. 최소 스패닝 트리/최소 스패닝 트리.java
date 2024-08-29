import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Solution {
	static int V,E,parents[];
	static long sum;
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = -1;
		}
		// Arrays.fill(parents, -1);
	}
	static int findSet(int a) {
		if (parents[a] < 0) return a;
			
		return parents[a] = findSet(parents[a]); // 집합의 대표자를 자신의 부모로 변경 : 패스 압축!
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		// 편의상 a집합에 b집합을 붙임(집합의 크기에따라 붙이도록 처리 가능!!)
		parents[aRoot] += parents[bRoot]; // 집합크기관리(절대값을 사용하면 집합의 크기가 됨)
		parents[bRoot] = aRoot;
		return true;
	}
	static class Edge implements Comparable<Edge>{
		int start,end,weight;

		public Edge(int start, int ed, int weight) {
			super();
			this.start = start;
			this.end = ed;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			V = parseInt(st.nextToken());
			E = parseInt(st.nextToken());
			
			Edge[] edges = new Edge[E]; 
			
			// 간선 저장
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int from = parseInt(st.nextToken())-1;
				int to = parseInt(st.nextToken())-1;
				int weight = parseInt(st.nextToken());
				edges[i] = new Edge(from,to,weight);
			}
			
			Arrays.sort(edges); // 간선의 가중치가 적은 순서대로 정렬..
			
			make(); // 모든 정점을 분리집합으로..(단위 서로소 집합<트리> 생성)
			
			
			int cnt = 0;
			for(Edge edge : edges) {
				if(union(edge.start, edge.end)) {
					sum += edge.weight;
					if(++cnt == V-1) break; // 최소신장트리 완성!!
				}
			}
			System.out.println("#"+tc+" "+sum);
			sum = 0;
		}
		
	}
}
