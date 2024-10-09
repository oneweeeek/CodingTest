import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
    
    static int v,e,k;
    
    static class Edge{
        int to; //간선
        int cost; //가중치

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static int[] minEdge;
    static ArrayList<ArrayList<Edge>> adjList;
	static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        v = parseInt(st.nextToken()); // 정점의 개수
        e = parseInt(st.nextToken()); // 간선의 개수
        k = parseInt(br.readLine())-1; // 시작정점의 번호
        
        adjList = new ArrayList<ArrayList<Edge>>();
        for(int i =0;i<v;i++) {
        	adjList.add(new ArrayList<>());
        }
        for(int i=0;i<e;i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken())-1;
            int to = parseInt(st.nextToken())-1;
            int weight = parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, weight));
        }
        minEdge = new int[v];
        Arrays.fill(minEdge, INF);
        visited = new boolean[v];
        dijkstra(k);
        for(int i = 0; i<v;i++) {
        	System.out.println(minEdge[i] == MAX_VALUE ? "INF" : minEdge[i]);
        }
    }
    static final int INF = MAX_VALUE;
	static void dijkstra(int start) {
		PriorityQueue<Edge> q = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
		minEdge[start] = 0;
		q.add(new Edge(start,0));
		
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			
			if(!visited[cur.to]) {
				visited[cur.to] = true;
			}
			
			for(Edge next : adjList.get(cur.to)) {
				
				if(!visited[next.to] && minEdge[next.to] > cur.cost+next.cost) {
					minEdge[next.to] = cur.cost +next.cost;
					q.add(new Edge(next.to, minEdge[next.to]));
				}
			}
		}
		
	}
}