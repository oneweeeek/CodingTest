import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i=1;i<=n;i++) {
			q.add(i);
		}
		int rest = 0;
		int chuga = 0;
		while(true) {
			rest = q.poll();
			if(q.isEmpty()) {
				break;
			}
			chuga = q.poll();
			q.add(chuga);
			
		}
		System.out.println(rest);
	}
	
	
}
