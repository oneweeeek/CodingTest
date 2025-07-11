import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

public class Main {
    static int n, m, t, s, g, h;
    static int ghDist;
    static ArrayList<ArrayList<Edge>> list;
    static int[] distS, distG, distH;
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();
    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int v, weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int v, dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return compare(this.dist, o.dist);
        }
    }

    static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.v] < cur.dist) continue;

            for (Edge next : list.get(cur.v)) {
                if (dist[next.v] > dist[cur.v] + next.weight) {
                    dist[next.v] = dist[cur.v] + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken()); // 교차로
            m = parseInt(st.nextToken()); // 도로
            t = parseInt(st.nextToken()); // 목적지 후보
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            s = parseInt(st.nextToken()) - 1; // 예술가 출발지
            g = parseInt(st.nextToken()) - 1; // g ~ h 도로 사용함
            h = parseInt(st.nextToken()) - 1;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = parseInt(st.nextToken()) - 1;
                int to = parseInt(st.nextToken()) - 1;
                int weight = parseInt(st.nextToken());
                list.get(from).add(new Edge(to, weight));
                list.get(to).add(new Edge(from, weight));
                if ((from == g && to == h) || (from == h && to == g)) {
                    ghDist = weight;
                }
            }
            distS = new int[n];
            distG = new int[n];
            distH = new int[n];
            Arrays.fill(distS, MAX_VALUE);
            Arrays.fill(distG, MAX_VALUE);
            Arrays.fill(distH, MAX_VALUE);
            distS[s] = 0;
            distG[g] = 0;
            distH[h] = 0;
            dijkstra(s, distS);
            dijkstra(g, distG);
            dijkstra(h, distH);
            // 출발 -> g + h -> 도착지 + g~h == 출발 -> 도착지 최단경로
            // 반대도 가능
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int end = parseInt(br.readLine()) - 1;
                if (distS[g] + distH[end] + ghDist == distS[end] ||
                        distS[h] + distG[end] + ghDist == distS[end]) {
                    result.add(end + 1);
                }
            }
            Collections.sort(result);

            for (int num : result) {
                sb.append(num).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}