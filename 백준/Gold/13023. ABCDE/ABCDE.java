import static java.lang.Integer.parseInt;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m,result;
    static ArrayList<ArrayList<Integer>> friends;
    static boolean[] visited;
	static void dfs(int node, int depth) {
		
		visited[node] = true;  // 현재 노드를 방문 처리
		
        if (depth == 5) {
        	result = 1;
            return;
        }

        
		for(int i : friends.get(node)) { // 현재노드에 모든 친구 노드에 대해서
			if(!visited[i]) { // 해당 친구 노드가 방문x면
				dfs(i, depth + 1); // 친구의 친구를 찾으러~
			}
		}
		visited[node] = false; // 현재 노드의 탐색이 끝나면 방문처리 풀기
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
        friends = new ArrayList<>();
        // 인접리스트 초기화
        for (int i = 0; i < n; i++) {
            friends.add(new ArrayList<>());
        }
		// 인접리스트로 노드, 간선 표시
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken());
			int to = parseInt(st.nextToken());
			
			// 친구는 양방향~
			friends.get(from).add(to);
			friends.get(to).add(from);
		}
		
        // 시작 노드 dfs탐색
		for (int i = 0; i < n; i++) {
			visited = new boolean[n];
			visited[i] = true;
			dfs(i, 1);
		}

		System.out.println(result);
	}
}
