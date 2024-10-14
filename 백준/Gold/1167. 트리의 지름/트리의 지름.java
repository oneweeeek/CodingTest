import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
	static int v,max,maxVertex;
	static ArrayList<ArrayList<Node>> adjList;
	static boolean visited[];
	static class Node{
		int v,dist;

		public Node(int v, int dist) {
			super();
			this.v = v;
			this.dist = dist;
		}
		
	}
	static void dfs(int n, int dist) {
		
		if(max < dist) {
			max = dist;
			maxVertex = n;
		}
		
		ArrayList<Node> list = adjList.get(n);
		for(Node next :list) {
			if(!visited[next.v]) {
				visited[next.v] = true;
				dfs(next.v, next.dist + dist);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		v = parseInt(br.readLine());
		adjList = new ArrayList<>();
		for(int i =0;i<v;i++) {
			adjList.add(new ArrayList<>());
		}
		for(int t = 0; t<v; t++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken())-1;
			while(st.hasMoreTokens()) {
				int to = parseInt(st.nextToken())-1;
				if(to == -2) {
					break;
				}
				int weight = parseInt(st.nextToken());
				adjList.get(from).add(new Node(to,weight));
				adjList.get(to).add(new Node(from,weight));
			}
		}
		visited = new boolean[v];
		visited[0] = true;
		max = MIN_VALUE;
		dfs(0 ,0);
		
		visited = new boolean[v];
		visited[maxVertex] = true;
		dfs(maxVertex,0);
		System.out.println(max);
	}

}
