import java.io.*;
import java.util.*;
import static java.lang.Integer.*;


public class Main {

	static int n; // 구역수
	static int result;
	static int[] population;

	static boolean[] visited;
	
	static ArrayList<ArrayList<Integer>> gu;// 구역 그래프

	static Queue<Integer> q; //
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		n = parseInt(br.readLine());
		population = new int[n];
		gu = new ArrayList<>();
		visited = new boolean[n];
		
		q = new LinkedList<Integer>();
		
		// 인접리스트 초기화
		for (int i = 0; i < n; i++) {
			gu.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine()); // 인구수초기화
		for (int i = 0; i < n; i++) {
			population[i] = parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int count = parseInt(st.nextToken());

			for (int j = 0; j < count; j++) {
				int to = parseInt(st.nextToken());
				gu.get(i).add(to-1); // 리스트는 0부터 시작하니까는;; 밑에 구역이 연결되있는지 활용할려면 시작인덱스 맞춰야함
			}
		}
		result = MAX_VALUE;
		set(0);
		if(result == MAX_VALUE)
			result = -1;
		System.out.println(result);
	}

	static void set(int depth) {
		if (depth == n) {
			ArrayList<Integer> vo = new ArrayList<>();
			ArrayList<Integer> te = new ArrayList<>();
			
			for(int i = 0; i<n;i++) {
				if(visited[i]) {
					vo.add(i);
				}else {
					te.add(i);
				}
			}
			if(vo.size()==0||te.size()==0) { //공집합과 전체를 원소로 하면 나눌 수 없잖
				return;
			}
			if(isConnect(vo)&&isConnect(te)) { // 연결되었는지 bfs돌리고
//				for(int num : vo) {
//					System.out.print(num+" ");
//				}
//				System.out.println();
//				for(int num : te) {
//					System.out.print(num+" ");
//				}
//				System.out.println();
				// people에서 해당하는 인덱스 합쳐서 빼기
				int voSum = 0;
				int teSum = 0;
				
				for(int i = 0;i<n;i++) {
					if(visited[i]) {
						voSum +=population[i];
					}else {
						teSum +=population[i];
					}
				}
				int min = Math.abs(voSum-teSum);
				result = Math.min(result, min);
			}
			return;
		}
		// 부분집합으로 나누기
		visited[depth] = true; 
		set(depth + 1);
		visited[depth] = false;
		set(depth + 1);
	}

	static boolean isConnect(ArrayList<Integer> list) {
		
		int start = list.get(0); // start
		
		boolean[] selected = new boolean[n]; // 연결여부 체크 1- 2-4 -1 불가능하게
		
		selected[start] = true;
		
		q.add(start);
		
		int count = 1; // 시작점 카운트
		
		while(!q.isEmpty()) {
			
			int cur = q.poll();
			
			for(int i = 0; i < gu.get(cur).size();i++) {
				
				int next = gu.get(cur).get(i);
				
				if(list.contains(next) && !selected[next]) { //아직 방문안했고 현재 구역에서 다음구역으로 갈수있는 next를 포함하고잇다면
					selected[next] = true;
					q.add(next);
					count++;
				}
			}
			
		}
		// 임의의 집합의 구역과 카운트가 같다면 연결된것!
		if(count == list.size()) {
			return true;
		} 
		return false;
	}
}
