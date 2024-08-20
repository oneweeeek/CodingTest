import java.io.*;
import java.util.*;

	/**자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
	 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
	 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 
	 * 각 수열은 공백으로 구분해서 출력해야 한다.
	 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
	 * */
public class Main {
	
	static int [] sequence; // 수열담을 배열
	static boolean [] visited; // 중복처리할 boolean 배열
	
	public static void main(String[] args) throws IOException{
		// 중복없이 수열은 공백구분 출력 1~N까지 자연수에서 M개를 고른 수열 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수열에 들어올 수 있는 자연수 1<=N<=8
		int M = Integer.parseInt(st.nextToken()); // 수열 길이
		sequence = new int[M];
		visited = new boolean[N]; // 1부터시작할거라
		// 처음 길이는 0이므로 인자를 0으로 넘겨주고
		dfs(N,M,0);
		
	}
	
	static void dfs(int N, int M, int depth) {
		if(depth == M) { // 깊이가 M과 같아지면 담았던 배열들 출력
			for (int num : sequence) {
				System.out.print(num+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) { // 방문하지 않았다면
				visited[i] = true; // 방문처리
				sequence[depth] = i+1; // 인덱스에 i값 초기화;
				dfs(N,M,depth+1); // 그 다음 깊이 재귀호출
				
				visited[i] = false; // 자식 노드 방문이 끝나면 방문노드를 방문x처리
			}
		}
	}
}
