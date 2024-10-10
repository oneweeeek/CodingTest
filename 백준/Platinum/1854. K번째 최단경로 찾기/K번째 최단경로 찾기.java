import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
    static int n,m,k;
    static ArrayList<ArrayList<Node>> adjList;
    static PriorityQueue<Integer>[] resultPq;
    static class Node implements Comparable<Node>{
        int v,cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost < o.cost) {
                return -1;
            }
            return 1;
        }
    }
    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(0,0));
        resultPq[0].offer(0);
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int curV = cur.v;
            int curCost = cur.cost;
            for(int i =0; i<adjList.get(curV).size();i++) {
            	int nextV = adjList.get(curV).get(i).v;
            	int nextCost = adjList.get(curV).get(i).cost + curCost;
            	
            	if(resultPq[nextV].size() < k) {
            		resultPq[nextV].offer(-nextCost);
            		pq.offer(new Node(nextV, nextCost));
            	}else if(-resultPq[nextV].peek() > nextCost) {
            		resultPq[nextV].poll();
            		resultPq[nextV].offer(-nextCost);
            		pq.offer(new Node(nextV,nextCost));
            	}
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        k = parseInt(st.nextToken());
        resultPq = new PriorityQueue[n];
        adjList = new ArrayList<>();
        for(int i =0;i<n;i++) {
        	adjList.add(new ArrayList<Node>());
            resultPq[i] = new PriorityQueue<Integer>();
        }
        for(int i =0;i<m;i++) {
            
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken())-1;
            int to = parseInt(st.nextToken())-1;
            int cost = parseInt(st.nextToken());
            adjList.get(from).add(new Node(to,cost));
        }
        dijkstra();
        
        // 출력 i번째 줄에 1번도시에서 i번 도시로 가는 k번째 최단경로
	    for (int i = 0; i < n; i++) {
	        if (resultPq[i].size() != k) System.out.println(-1);
	        else System.out.println(-resultPq[i].peek());
	    }
    }
}