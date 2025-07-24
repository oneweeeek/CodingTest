import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int v,e;
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;
	static int cnt;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		v = Integer.parseInt(br.readLine());
		e = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i = 0;i<v;i++) {
			list.add(new ArrayList<>());
		}
		StringTokenizer st;
		visited = new boolean[v];
		for(int i = 0; i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			list.get(from).add(to);
			list.get(to).add(from);
		}
		virus(0);
		System.out.println(cnt-1);
	}

	static void virus(int start) {
		
		visited[start] = true;
		cnt++;
		
		for(int next : list.get(start)) {
			if(!visited[next]) {
				virus(next);
			}
		}
	}
}
