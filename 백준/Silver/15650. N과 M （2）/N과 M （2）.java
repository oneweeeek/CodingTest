import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int [] sequence; // 수열담을 배열
	static boolean [] visited; // 중복처리할 boolean 배열

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		
		// 수열
		// 1. 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
		// 2. 오름차순이어야 한다.
		sequence = new int[M];
		visited = new boolean[N]; 
		dfs(N,M,0);
	}

	 static void dfs(int n, int m, int depth) {
		if(depth == m) { // 깊이가 M과 같아지면 담았던 배열들 출력
			for (int num : sequence) {
				System.out.print(num+" ");
			}
			System.out.println();
			return;
		}
		
		 for(int i=0;i<n;i++) {
			 if(!visited[i]) {
				 // 시작 인덱스가 아니고 + 이전수열이 지금보다 크지 않다면 진행
				 if(depth!=0 && sequence[depth-1]>i) continue;
				 visited[i] = true;
				 sequence[depth] = i+1;
				 
				 dfs(n,m,depth+1); // 그 다음 깊이 재귀호출
				 
				 visited[i] = false;
			 }
		 }
		
	}

}
