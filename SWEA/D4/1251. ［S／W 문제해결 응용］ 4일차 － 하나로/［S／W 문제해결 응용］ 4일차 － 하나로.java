import java.io.*;
import java.util.*;

import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.awt.Point;

public class Solution {
	// 64비트 integer 및 double로 처리하지 않을 경우, overflow가 발생
	static int n, parents[]; // 섬 개수
	static double cost;
	static Point[] island; //섬의 좌표를 저장할 배열
	static ArrayList<Edge> edges; // 간선을 저장할 리스트
	
	static void make() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = -1;
		}
		// Arrays.fill(parents, -1);
	}

	static int findSet(int a) {
		if (parents[a] < 0)
			return a;

		return parents[a] = findSet(parents[a]); // 집합의 대표자를 자신의 부모로 변경 : 패스 압축!
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		// 편의상 a집합에 b집합을 붙임(집합의 크기에따라 붙이도록 처리 가능!!)
		parents[aRoot] += parents[bRoot]; // 집합크기관리(절대값을 사용하면 집합의 크기가 됨)
		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = parseInt(br.readLine());
			island = new Point[n];

//			for(int i = 0;i<n;i++) {
//				island.add(new ArrayList<>());
//			}

			st = new StringTokenizer(br.readLine());
			
			// 좌표 저장
			for (int i = 0; i < n; i++) {
				int x = parseInt(st.nextToken());
				island[i] = new Point(x, 0);
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
				int y = parseInt(st.nextToken());
				island[i].y = y;
			}
			
			double E = parseDouble(br.readLine());
			
	          // 모든 간선의 비용을 저장
			edges = new ArrayList<Edge>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) { // 중복 간선 방지
                	int fromX = island[i].x;
                	int fromY = island[i].y;
                	int toX = island[j].x;
                	int toY = island[j].y;
                    long distX = Math.abs(fromX - toX);
                    long distY = Math.abs(fromY - toY);
                    
                    edges.add(new Edge(i, j, distX*distX+distY*distY));
                }
            }
            
            // 간선 기준 오름차순 정렬
            edges.sort(null);
            
            // 정점 초기화
            make();
            
            int cnt = 0;
            for(Edge edge : edges) {
            	if(union(edge.from,edge.to)) {
            		cost+=edge.weight;
            		if(++cnt == n-1) break; // 최소신장트리 완성!!
            	}
            }
			System.out.println("#" + tc + " " + Math.round(cost*E));
			cost = 0;
		}
	}
}
